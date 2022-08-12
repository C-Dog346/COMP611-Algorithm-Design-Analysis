/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADA_Assignment_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author callumclow
 */
public class Server {

    private ArrayList<ChatConnection> connections;
    private boolean requested;
    public static final int PORT = 7777; // some unused port number
    public ThreadPool pool;

    public Server() {
        connections = new ArrayList<>();
        requested = false;
        pool = new ThreadPool(5);
    }

    public void startServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started at "
                    + InetAddress.getLocalHost() + " on port " + PORT);
        }
        catch (IOException e) {
            System.err.println("Server can't listen on port: " + e);
            System.exit(-1);
        }
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection made with "
                        + socket.getInetAddress());
                ChatConnection connection = new ChatConnection(socket, this);
                connections.add(connection);
                Thread thread = new Thread(connection);
                thread.start();
            }
        }
        catch (IOException e) {
            System.out.println("Can't accept client connection: " + e);
        }

        System.out.println("Server finishing");
    }

    private class ChatConnection implements Runnable, TaskObserver {

        private Socket socket;
        private Server server;

        public ChatConnection(Socket socket, Server server) {
            this.socket = socket;
            this.server = server;
        }

        @Override
        public void run() {
            try {  // create an autoflush output stream for the socket
                // create a buffered input stream for this socket
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                String clientMessage = null;
                sendMessage("Send a message to all clients (Enter \"QUIT\" to finish): ");
                do {
                    clientMessage = br.readLine();
                    String response;
                    if ("QUIT".equals(clientMessage)) {
                        response = "Quitting!";
                    }
                    else {
                        MessageSender message = new MessageSender(clientMessage);

                        for (ChatConnection c : connections) {
                            if (!c.socket.isClosed()) {
                                message.addListener(c);
                            }
                        }
                        
                        pool.perform(message);
                        
                       // remove the listeners and break the code :)
//                        for (ChatConnection c : connections) {
//                            if (!c.socket.isClosed()) {
//                                message.removeListener(c);
//                            }
//                        }
                    }
                }
                while (!"QUIT".equals(clientMessage));
                br.close();
                System.out.println("Closing connection with "
                        + socket.getInetAddress());
                socket.close();
            }
            catch (IOException e) {
                System.err.println("Server error 2: " + e);
            }
        }

        public void sendMessage(String message) {
            try {  // create an autoflush output stream for the socket
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                // create a buffered input stream for this socket
                // play the game until value is correctly guessed
                pw.println(message);
            }
            catch (IOException e) {
                System.err.println("Server error 3: " + e);
            }
        }
        
        @Override
        public void update(Task o, Object arg) {
            //System.out.println("Notified");
            sendMessage((String) arg);
        }
    }

    public void broadcastMessage(String message) {
        for (ChatConnection c : connections) {
            if (!c.socket.isClosed()) {
                c.sendMessage(message);
            }
        }
        System.out.println("Broadcast finished");
    }

    // driver main method to test the class
    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

}
