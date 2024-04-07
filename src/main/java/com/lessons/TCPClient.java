package com.lessons;

import java.io.*;
import java.net.Socket;

public class TCPClient extends TCPEcho {


    protected TCPClient(int port) {
        super(port);
    }

    @Override
    public void run() {
        try {
            try (Socket clientSocket = new Socket("localhost", port);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            ) {

                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
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
