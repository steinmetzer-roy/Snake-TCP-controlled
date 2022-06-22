import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawPanel extends JPanel {

    ArrayList<Point2D> snake = new ArrayList<>();
    Point2D food = null;
    String direction = "RIGHT";

    Timer t = new Timer(100, e -> {
        moveSnake(direction);
        repaint();
    });

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (food != null) {
            g.setColor(Color.black);
            g.fillOval(food.x, food.y, 5, 5);
        } else {
            food = spawnFood();
        }
        if (snake.size() != 0) {
            for (int i = 0; i < snake.size(); i++) {
                if (i == 0) {
                    g.setColor(Color.red);
                    g.fillRect(snake.get(i).x, snake.get(i).y, 5, 5);
                } else {
                    g.setColor(Color.BLUE);
                    g.fillRect(snake.get(i).x, snake.get(i).y, 5, 5);
                }
            }
        }
    }

    public Point2D spawnFood() {
        Point2D p = new Point2D((int) (Math.random() * getHeight() + 1), (int)(Math.random() * getWidth() + 1));
        //Point2D p = new Point2D(10, 0);
        return p;
    }

    public void moveSnake(String direction) {
        Point2D snakeHead = snake.get(0);
        Point2D snakeTail = snake.get(snake.size() - 1);
        switch (direction) {
            case "UP":
                for (int i = snake.size() - 1; i >= 1; i--) {
                    snake.set(i, new Point2D(snake.get(i - 1)));
                }
                snakeHead.y -= 2;
                snake.set(0, new Point2D(snakeHead));
                break;
            case "DOWN":
                for (int i = snake.size() - 1; i >= 1; i--) {
                    snake.set(i, new Point2D(snake.get(i - 1)));
                }
                snakeHead.y += 2;
                snake.set(0, new Point2D(snakeHead));
                break;
            case "LEFT":
                for (int i = snake.size() - 1; i >= 1; i--) {
                    snake.set(i, new Point2D(snake.get(i - 1)));
                }
                snakeHead.x -= 2;
                snake.set(0, new Point2D(snakeHead));
                break;
            case "RIGHT":
                for (int i = snake.size() - 1; i >= 1; i--) {
                    snake.set(i, new Point2D(snake.get(i - 1)));
                }
                snakeHead.x += 2;
                snake.set(0, new Point2D(snakeHead));
                break;
            default:
                break;
        }
        if (eatFoodCheck()) {
            snake.add(new Point2D(snakeTail));
        }
    }

    public boolean eatFoodCheck() {
        if (Point2D.distPoints(snake.get(0), food) < 3) {
            food = null;
            return true;
        }
        return false;
    }

    public void startTimer() {
        snake.add(new Point2D(5, 0));
        snake.add(new Point2D(0, 0));
        t.start();
    }
}
