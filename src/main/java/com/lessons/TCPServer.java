package com.lessons;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TCPServer extends TCPEcho {


    protected TCPServer(int port) {
        super(port);
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(this.port, 20, InetAddress.getByName("localhost"))) {
            System.out.printf("Server run on %s, port is %s%n", serverSocket.getInetAddress(), serverSocket.getLocalPort());
            String[] commands;
            do {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String inString = in.readLine();
                commands = inString.split(" ");
                if (commands.length < 2) {
                    out.write("Ошибка команды: " + commands[0] + "\n");
                    out.flush();
                } else {
                    out.write("echo " + inString + "\n");
                    out.flush();
                }
            } while (!commands[0].equals("stop") || !Thread.currentThread().isInterrupted());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
