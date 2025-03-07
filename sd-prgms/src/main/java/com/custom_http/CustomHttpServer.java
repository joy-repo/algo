package com.custom_http;

import java.io.*;
import java.net.*;

// Custom HTTP-like Server
public class CustomHttpServer {
    public static void main(String[] args) {
        int port = 8080; // Server will listen on this port

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Custom HTTP Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Handles client requests
class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String request = in.readLine(); // Read request line
            System.out.println("Received: " + request);

            if (request != null && request.startsWith("CUSTOMGET")) {
                out.println("CUSTOMHTTP/1.0 200 OK");
                out.println("Content-Type: text/plain");
                out.println();
                out.println("Hello from Custom HTTP Server!");
            } else {
                out.println("CUSTOMHTTP/1.0 400 BAD REQUEST");
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Custom HTTP Client
