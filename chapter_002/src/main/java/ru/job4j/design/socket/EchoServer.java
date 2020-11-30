package ru.job4j.design.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.design.log.UsageLog4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;

public class EchoServer {
    private static final String OUT = "Bye";
    private static final String GREETING = "Hello";
    private static final Logger LOG =
            LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.info("Start {}", Instant.now());
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
        } catch (Exception e) {
            LOG.error("Server socket error", e);
        }
    }

    private static boolean isMessage(String string) {
        return (string.startsWith("GET"));
    }
}