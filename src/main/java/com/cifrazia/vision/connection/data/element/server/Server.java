package com.cifrazia.vision.connection.data.element.server;

public class Server {
    /*{
       "id": 28,
    "name": "Heaven 1",
    "modpack": 17,
    "ip": "185.137.235.34",
    "port": 20008,
    "online": 0,
    "max": 75,
    "status": true,
    "players": [],
    "priority": 0,
    "picture_url": null
    }*/
    private int id;
    private String name;
    private int modpack;
    private String ip;
    private short port;
    private int online;
    private int max;
    private boolean status;
    private CifraziaPlayer[] players;
    private int priority;
    private String picture_url;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getModpack() {
        return modpack;
    }

    public String getIp() {
        return ip;
    }

    public short getPort() {
        return port;
    }

    public int getOnline() {
        return online;
    }

    public int getMax() {
        if(max == 0){
            max = 1;
        }
        return max;
    }

    public boolean isStatus() {
        return status;
    }

    public CifraziaPlayer[] getPlayers() {
        return players;
    }

    public int getPriority() {
        return priority;
    }
}
