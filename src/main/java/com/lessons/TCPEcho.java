package com.lessons;

public abstract class TCPEcho implements Runnable {

    protected int port;

    protected String hostName;

    protected TCPEcho(int port, String hostName) {
        this.port = port;
        this.hostName = hostName;
    }

    public static TCPEcho of(TCPType type, int port, String hostName) {
        return switch (type) {
            case CLIENT -> new TCPClient(port, hostName);
            case SERVER -> new TCPServer(port, hostName);
        };
    }
}

