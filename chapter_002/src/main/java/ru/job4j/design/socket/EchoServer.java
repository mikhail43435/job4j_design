package ru.job4j.design.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final String OUT = "Bye";
    private static final String GREETING = "Hello";

    public static void main(String[] args) throws IOException {
        System.out.println("Начало работы");
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean isRunning = true;
            while (isRunning) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    System.out.println("======= Новое сообщение");
                    String answer = "What";
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println("Строка сообщения: " + str);
                        if (isMessage(str) && str.contains(OUT)) {
                            isRunning = false;
                        }
                        if (isMessage(str) && str.contains(GREETING)) {
                            answer = "Hello";
                        }
                    }
                    out.write(("HTTP/1.1 200 OK\r\n").getBytes());
                    out.write((answer + "\n").getBytes());
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.lineSeparator() + "Работа завершена");
        }
    }

    private static boolean isMessage(String string) {
        return (string.startsWith("GET"));
    }
}