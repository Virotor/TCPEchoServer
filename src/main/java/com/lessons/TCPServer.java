package com.lessons;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class TCPServer extends TCPEcho {


    protected TCPServer(int port, String hostName) {
        super(port, hostName);
    }

    private String getConnect(ServerSocket serverSocket)  {
        try (Socket socket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
            String inString = in.readLine();
            out.write("echo " + inString + "\n");
            out.flush();
            String res = "Connect client with address " + socket.getInetAddress();
            socket.close();
            return res;
        }
        catch (IOException e ){
            return e.getMessage();
        }
    }


    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(this.port, 20, InetAddress.getByName(hostName))) {
            System.out.printf("Server run on %s, port is %s%n", serverSocket.getInetAddress(), serverSocket.getLocalPort());

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            do {
                CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> getConnect(serverSocket),executor );
                System.out.println(result.get());
            } while (!Thread.currentThread().isInterrupted());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
