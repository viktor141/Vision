package com.cifrazia.vision;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.buffer.ByteBuf;
import java.util.List;

public class CustomPingClient {

    public static void sendCustomPing(String host, int port, String message) {
        EventLoopGroup group = new NioEventLoopGroup();
        System.out.println("1");

        try {
            Bootstrap bootstrap = new Bootstrap();
            System.out.println("2");
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new CustomRequestEncoder());
                            pipeline.addLast(new CustomResponseDecoder());
                            pipeline.addLast(new CustomClientHandler(message));
                        }
                    });

            System.out.println("3");

            ChannelFuture future = bootstrap.connect(host, port).sync();
            System.out.println("4");
            future.channel().closeFuture().sync();
            System.out.println("5");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static class CustomRequestEncoder extends MessageToByteEncoder<PacketCustomRequest> {
        @Override
        protected void encode(ChannelHandlerContext ctx, PacketCustomRequest msg, ByteBuf out) throws Exception {
            byte[] bytes = msg.getMessage().getBytes();
            out.writeInt(bytes.length);
            out.writeBytes(bytes);
            System.out.println("Encoded custom request");
        }
    }

    public static class CustomResponseDecoder extends ByteToMessageDecoder {
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            int length = in.readInt();
            byte[] bytes = new byte[length];
            in.readBytes(bytes);
            out.add(new PacketCustomResponse(new String(bytes)));
            System.out.println("Decoded custom response");
        }
    }

    public static class CustomClientHandler extends SimpleChannelInboundHandler<PacketCustomResponse> {
        private final String message;

        public CustomClientHandler(String message) {
            this.message = message;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            PacketCustomRequest request = new PacketCustomRequest(message);
            ctx.writeAndFlush(request);
            System.out.println("Sent custom request");
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, PacketCustomResponse msg) throws Exception {
            System.out.println("Received response from server: " + msg.getMessage());
            ctx.close();
        }
    }
}
