package com.lessons;

import java.io.*;
import java.net.Socket;

public class TCPClient extends TCPEcho {


    protected TCPClient(int port, String hostName) {
        super(port, hostName);
    }

    @Override
    public void run() {
        try {
            try (Socket clientSocket = new Socket(hostName, port);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            ) {
                System.out.println("Echo to send, and stop to exit");
                String word = reader.readLine();
                out.write(word + "\n");
                out.flush();
                String serverWord = in.readLine();
                System.out.println(serverWord);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
