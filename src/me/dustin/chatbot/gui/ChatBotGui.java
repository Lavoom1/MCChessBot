package me.dustin.chatbot.gui;

import me.dustin.chatbot.network.ClientConnection;
import me.dustin.chatbot.network.packet.c2s.play.ServerBoundChatPacket;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatBotGui {
    private JFrame frame;
    private JPanel panel;
    private JButton sendButton;
    private JTextArea output;
    private JTextField input;
    private JList<String> playerList;
    private JScrollPane outputScroll;
    private DefaultListModel<String> model;

    private ClientConnection clientConnection;

    public ChatBotGui() {
        this.frame = new JFrame("ChatBot");
        this.frame.setSize(800, 600);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.setResizable(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.add(this.panel);
        this.output.setText("");
        this.output.setLineWrap(true);
        this.output.setWrapStyleWord(true);

        model = new DefaultListModel<>();
        playerList.setModel(model);

        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {//they pressed enter
                    clientConnection.sendPacket(new ServerBoundChatPacket(input.getText()));
                    input.setText("");
                }
            }
        });
        sendButton.addActionListener(actionEvent -> {
            this.clientConnection.sendPacket(new ServerBoundChatPacket(input.getText()));
            this.input.setText("");
        });
        setLookAndFeel();
    }

    public void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            updateComponents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateComponents() {
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public JTextArea getOutput() {
        return output;
    }

    public DefaultListModel<String> getPlayerList() {
        return model;
    }

    public void setClientConnection(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }
}
