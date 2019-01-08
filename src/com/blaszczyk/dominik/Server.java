package com.blaszczyk.dominik;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Server extends Window {

    static ServerSocket server_socket;
    static Socket socket;


    public Server() {
        this.textArea1.append("Hello in Let's Talk!\nWaiting for a client...\n");
        try {
            server_socket = new ServerSocket(4000);
            socket = server_socket.accept();
            flag = true;
            myNick = JOptionPane.showInputDialog("Enter your nick:");
            this.textArea1.append("Client connected!\n");
        } catch (IOException e) {
            this.textArea1.append("An error has occured\n");
        }
    }
        public static void main(String[] args) throws IOException {
            Server server = new Server();
            try {
                msIn = new DataInputStream(socket.getInputStream());
                msOut = new DataOutputStream(socket.getOutputStream());
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            String message = "";
            msOut.writeUTF(myNick);
            guestNick = msIn.readUTF();
            server.textArea1.append("Connected with: " + guestNick + "! \nSay \"Hello!\"\n");
            server.textArea1.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
            while(true)
            {
                try {
                    message = msIn.readUTF();
                    server.textArea1.append(guestNick + ": " + message + "\n");
                }
                catch(IOException e)
                {
                    server.textArea1.append("Connection lost!");
                    break;
                }
            }

    }

    }


