package com.cifrazia.vision.core.client.misc;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.data.element.ServerInfo;
import com.cifrazia.vision.connection.data.element.server.Server;
import com.cifrazia.vision.core.misc.MessageParser;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Future;

public class MinecraftServerPing {
    private Server server;
    private Future<String> lastServerPing;
    private int timeout = 50000;
    private ServerInfo serverInfo = null;

    public MinecraftServerPing(Server server) {
        this.server = server;
    }

    public ServerInfo getServerInfo() {
        if (serverInfo == null && lastServerPing.isDone()) {
            try {
                serverInfo = MessageParser.parseServerInfo(lastServerPing.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return serverInfo;
    }

    public void serverPing() {
        lastServerPing = Vision.getInstance().getExecutorService().submit(this::getServerStatus);
    }

    private String getServerStatus() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(server.getIp(), server.getPort()), timeout);

            try (DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                 //> Handshake
                 ByteArrayOutputStream handshake_bytes = new ByteArrayOutputStream();
                 DataOutputStream handshake = new DataOutputStream(handshake_bytes)) {

                handshake.writeByte(0x00);
                writeVarInt(handshake, 4);
                writeVarInt(handshake, server.getIp().length());
                handshake.writeBytes(server.getIp());
                handshake.writeShort(server.getPort());
                writeVarInt(handshake, 1);

                writeVarInt(out, handshake_bytes.size());
                out.write(handshake_bytes.toByteArray());

                //> Status request
                out.writeByte(0x01); // Size of packet
                out.writeByte(0x00);

                //< Status response
                readVarInt(in); // Size
                int id = readVarInt(in);

                io(id == -1, "Server prematurely ended stream.");
                io(id != 0x00, "Server returned invalid packet.");

                int length = readVarInt(in);
                io(length == -1, "Server prematurely ended stream.");
                io(length == 0, "Server returned unexpected value.");

                byte[] data = new byte[length];
                in.readFully(data);
                String json = new String(data, StandardCharsets.UTF_8);

                //> Ping
                out.writeByte(0x09); // Size of packet
                out.writeByte(0x01);
                out.writeLong(System.currentTimeMillis());

                //< Ping
                readVarInt(in); // Size
                id = readVarInt(in);
                io(id == -1, "Server prematurely ended stream.");
                io(id != 0x01, "Server returned invalid packet.");

                return json;
            }
        } catch (IOException e) {
            Vision.getInstance().logger.info("Error while ping {}:{} {}", server.getIp(), server.getPort(), e.getMessage());
        }
        return null;
    }

    private void writeVarInt(DataOutputStream out, int value) throws IOException {
        while ((value & 0xFFFFFF80) != 0) {
            out.writeByte((value & 0x7F) | 0x80);
            value >>>= 7;
        }
        out.writeByte(value & 0x7F);
    }

    private int readVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = in.readByte();

            i |= (k & 0x7F) << j++ * 7;

            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }

            if ((k & 0x80) != 128) {
                break;
            }
        }

        return i;
    }

    private void io(final boolean b, final String m) throws IOException {
        if (b) {
            throw new IOException(m);
        }
    }
}