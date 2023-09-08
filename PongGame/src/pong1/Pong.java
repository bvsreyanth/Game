package pong1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends JPanel implements ActionListener, KeyListener {
    
	/**
	 * 
	 */
	//private static final long serialVersionUID = 8476189777639136654L;
	private int ballX = 150, ballY = 150;  // Initial ball position
    private int ballXSpeed = 2, ballYSpeed = 5;  // Ball speed
    private int paddle1Y = 100, paddle2Y = 100;  // Initial paddle positions
    private int player1Score = 0, player2Score = 0;  // Scores for players

    public Pong() {
        Timer timer = new Timer(10, this);
        JFrame frame = new JFrame("Pong Game");
        frame.add(this);
        frame.setSize(400, 300);// Increase the size for a better display
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        ballX += ballXSpeed; // Move the ball horizontally by its X speed
        ballY += ballYSpeed; // Move the ball vertically by its Y speed

        if (ballY <= 0 || ballY >= 260) {
            ballYSpeed = -ballYSpeed; // Reverse the Y direction if the ball hits the top or bottom boundary
        }

        if (ballX <= 0) {
            ballXSpeed = -ballXSpeed; // Reverse the X direction if the ball hits the left boundary
            player2Score++; // Increment player 2's score because player 1 missed the ball
        }

        if (ballX >= 380) {
            ballXSpeed = -ballXSpeed; // Reverse the X direction if the ball hits the right boundary
            player1Score++; // Increment player 1's score because player 2 missed the ball
        }	

        if (ballX <= 20 && ballY > paddle1Y && ballY < paddle1Y + 50 ||
            ballX >= 370 && ballY > paddle2Y && ballY < paddle2Y + 50) {
            ballXSpeed = -ballXSpeed; // Reverse the X direction if the ball hits a paddle
        }

        if (player1Score >= 5 || player2Score >= 5) {
            // Game over condition, you can customize this further
            JOptionPane.showMessageDialog(this, "Game Over"); // Display a message dialog
            System.exit(0); // Exit the game when it's over
        }

        repaint(); // Redraw the game screen
    }


    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W && paddle1Y > 0) {
            paddle1Y -= 10;
        }
        if (keyCode == KeyEvent.VK_S && paddle1Y < 200) {
            paddle1Y += 10;
        }
        if (keyCode == KeyEvent.VK_UP && paddle2Y > 0) {
            paddle2Y -= 10;
        }
        if (keyCode == KeyEvent.VK_DOWN && paddle2Y < 200) {
            paddle2Y += 10;
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 300);

        g.setColor(Color.WHITE);
        g.fillRect(0, paddle1Y, 10, 50);
        g.fillRect(380, paddle2Y, 10, 50);

        g.fillOval(ballX, ballY, 10, 10);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Player 1: " + player1Score, 20, 20);
        g.drawString("Player 2: " + player2Score, 300, 20);
    }

    public static void main(String[] args) {
        new Pong();
    }
}