package com.blaszczyk.dominik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Window extends JFrame implements ActionListener, KeyListener{
    static boolean flag = false;
    JPanel panel,panel2;
    JScrollPane pan1;
    JTextArea textArea1,hint;
    JButton sendButton;
    JTextField textField1;
    static DataInputStream msIn;
    static DataOutputStream msOut;
    static String myNick;
    static String guestNick = "";

    public Window()
    {
        setTitle("Let's talk! v1.0");
        panel = new JPanel();
        panel2 = new JPanel();
        textArea1 = new JTextArea();
        textArea1.setMargin(new Insets(5,5,0,0));
        textArea1.setPreferredSize(new Dimension(580,390));
        pan1 = new JScrollPane();

        textArea1.setEditable(false);
        sendButton = new JButton("Send!");
        sendButton.setPreferredSize(new Dimension(100,25));
        sendButton.addActionListener(this);
        textField1 = new JTextField(38);
        textField1.setPreferredSize(new Dimension(50,25));
        textField1.addKeyListener(this);
        pan1 = new JScrollPane(textArea1);
        hint = new JTextArea("Enter your message: ");
        hint.setBackground(null);
        panel2.add(pan1);
        panel.add(hint,BorderLayout.BEFORE_FIRST_LINE);
        panel.add(textField1,BorderLayout.AFTER_LAST_LINE);
        panel.add(sendButton);
        add(panel2);
        add(panel,BorderLayout.SOUTH);

        setBounds(200,300,640,480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (flag == true) {
            String message = textField1.getText();
            try {
                msOut.writeUTF(message);
                textArea1.append(myNick + ": " + message + "\n");
                textField1.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if ((e.getKeyChar() == '\n') && flag == true) {
            String message = textField1.getText();
            try {
                msOut.writeUTF(message);
                textArea1.append(myNick + ": " + message + "\n");
                textField1.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
