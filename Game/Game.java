import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class Game {
    public static void main(String[] args) throws Exception {
        String previousCommand = "RIGHT";
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        DrawPanel panel = new DrawPanel();
        panel.startTimer();
        frame.getContentPane().add(panel); // Adds Button to content pane of frame
        frame.setVisible(true);

        ServerSocket server = new ServerSocket(5005);
        Socket s = server.accept();
        System.out.println("Connection successful");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String newCommand;
        while ((newCommand = in.readLine()) != null) {
            System.out.println(newCommand);
            if (!newCommand.equals(previousCommand) && !newCommand.equals("EXIT")) {
                previousCommand = newCommand;
                panel.direction = newCommand;
            } else if (newCommand.equals("EXIT")) {
                server.close();
                s.close();
                in.close();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                return;
            }
        }
    }
}