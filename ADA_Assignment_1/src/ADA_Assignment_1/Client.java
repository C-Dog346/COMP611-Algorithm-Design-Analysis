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
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author callumclow
 */
public class Client {

    public static final String HOST_NAME = "127.0.0.1";
    public static final int HOST_PORT = 7777; // host port number
    public Thread[] threads;
    public boolean finished;

    public Client() {
        threads = new Thread[2];
        finished = false;
    }

    public void startClient() {

        Socket socket = null;
        try {
            socket = new Socket(HOST_NAME, HOST_PORT);

            // Read
            threads[0] = new Thread(new Writer(socket));
            threads[0].start();
            // Write
            threads[1] = new Thread(new Reader(socket));
            threads[1].start();

        }
        catch (IOException e) {
            System.err.println("Client could not make connection: " + e);
            System.exit(-1);
        }
    }

    private class Writer implements Runnable {

        private Socket socket;
        private PrintWriter pw;
        private Scanner sc;

        public Writer(Socket socket) {
            this.socket = socket;
            try {
                pw = new PrintWriter(socket.getOutputStream(), true);
            }
            catch (IOException e) {
                System.out.println("Client error: " + e);
            }
            sc = new Scanner(System.in);
        }

        @Override
        public void run() {
            while (!finished) {
                String message = sc.nextLine();
                pw.println(message);
                if (message.equals("QUIT")) {
                    finished = true;
                }
            }
        }

    }

    private class Reader implements Runnable {

        private Socket socket;
        private BufferedReader br;
        private Scanner sc;

        public Reader(Socket socket) {
            this.socket = socket;
            try {
                br = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
            }
            catch (IOException e) {
                System.out.println("Client error: " + e);
            }
            sc = new Scanner(System.in);
        }

        @Override
        public void run() {
            try {
                while (!finished) {
                    String serverResponse = br.readLine();

                    if (serverResponse != null) {
                        System.out.println(serverResponse);
                    }
                }
            }
            catch (IOException e) {
                System.out.println("Client error: " + e);
            }
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }
}
