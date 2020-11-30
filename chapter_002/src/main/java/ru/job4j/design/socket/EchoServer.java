package ru.job4j.design.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean isRunning = true;
            while (isRunning) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    System.out.println("======= Новое сообщение");
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println("Сообщение: " + str);
                        if (str.contains("msg=Bye")) {
                            isRunning = false;
                        }
                        out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                    }
                }
            }
            System.out.println(System.lineSeparator() + "Работа завершена");
        }
    }
}