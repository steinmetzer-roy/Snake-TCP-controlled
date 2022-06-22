import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Controller {
    public static void main(String[] args) throws Exception{
        JFrame frame = new JFrame("Snake Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(3,3);
        panel.setLayout(layout);

        Socket s = new Socket(InetAddress.getLocalHost(), 5005);
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        Button up = new Button("UP");
        up.addActionListener(e -> {
            out.println("UP");
        });
        Button down = new Button("DOWN");
        down.addActionListener(e -> {
            out.println("DOWN");
        });
        Button right = new Button("RIGHT");
        right.addActionListener(e -> {
            out.println("RIGHT");
        });
        Button left = new Button("LEFT");
        left.addActionListener(e -> {
            out.println("LEFT");
        });
        Button exit = new Button("EXIT");
        exit.addActionListener(e -> {
            out.println("EXIT");
            try {
                s.close();
                out.close();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        panel.add(new Panel());
        panel.add(up);
        panel.add(new Panel());
        panel.add(left);
        panel.add(exit);
        panel.add(right);
        panel.add(new Panel());
        panel.add(down);
        panel.add(new Panel());
        frame.getContentPane().add(panel); // Adds Button to content pane of frame
        frame.setVisible(true);
    }
}