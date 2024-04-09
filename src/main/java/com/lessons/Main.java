package com.lessons;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String defaultHostName = "localhost";

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 Client. 2 Server");
        int choice = scanner.nextInt();
        System.out.println("Enter port number");
        int port = scanner.nextInt();
        Thread thread = new Thread(TCPEcho.of(choice == 2 ? TCPType.SERVER : TCPType.CLIENT, port, defaultHostName));
        if (choice == 1) {
            while (true) {
                thread = new Thread(TCPEcho.of(TCPType.CLIENT, port, defaultHostName));
                thread.start();
            }
        }

        thread.start();


    }


}