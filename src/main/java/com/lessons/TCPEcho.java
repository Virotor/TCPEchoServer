package com.lessons;

public abstract class TCPEcho implements  Runnable{

    protected int port;

    protected TCPEcho(int port){
        this.port = port;
    }

    public static TCPEcho of(int type, int port){
        return  switch (type) {
            case 1 -> new TCPClient(port);
            case 2 -> new TCPServer(port);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
