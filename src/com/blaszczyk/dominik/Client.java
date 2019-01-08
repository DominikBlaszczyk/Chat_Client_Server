package com.blaszczyk.dominik;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Window {

    static Socket socket;

    public Client() {
        try {
            socket = new Socket("127.0.0.1", 4000);
            flag = true;
            textArea1.append("Connected!\n");
            myNick = JOptionPane.showInputDialog("Enter your nick:");
        }
            catch (IOException e) {
        textArea1.append("Connection fail!\n");
        }
        }


    public static void main(String[] args) throws IOException {

        Client client = new Client();
        client.textField1.selectAll();
        try {
            msIn = new DataInputStream(socket.getInputStream());
            msOut = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        String message = "";
        guestNick = msIn.readUTF();
        msOut.writeUTF(myNick);
        client.textArea1.append("Connected with: " + guestNick + "!  \nSay \"Hello!\"\n");
        client.textArea1.append("-----------------------------------------------------------------------------------------------------------------------------------------------\n");
        while(true)
        {
            try {
                message = msIn.readUTF();
                client.textArea1.append(guestNick + ": " + message + "\n");
            }
            catch(IOException e)
            {
                client.textArea1.append("Connection lost!");
                break;
            }
        }

    }
}
