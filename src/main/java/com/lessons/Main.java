package com.lessons;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws UnknownHostException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 Client. 2 Server");
        int choice = scanner.nextInt();
        System.out.println("Enter port number");
        int port = scanner.nextInt();
        Thread thread = new Thread(TCPEcho.of(choice, port));
        thread.start();

    }


}