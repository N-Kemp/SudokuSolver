import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class is for the screen of the game, can be thought of the board of the game.
 * Assets are rendered onto the screen and this si what the user will see.
 *
 * @author Noah Kemp
 * @version 0.1
 * */

public class Screen extends JPanel implements ActionListener, KeyListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int DELAY = 25;
    private Timer timer;
    int size = 20;

    public Screen(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(255,255,255));

        setLayout(null);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e){

        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        drawBackground(g);

        Graphics2D g2d = (Graphics2D) g;

        for(int i = 0; i < 10; i++) {
            if (i == 0 || i == 3 || i == 6 || i == 9){
                g2d.fillRect(i * 40 + 15, 16, 3, 31 * 10 + 5);
                g2d.fillRect(16, i * 35 + 15, 35 * 10 + 10, 3);
            }
            else {
                g2d.drawLine(i * 40 + 16, 16, i * 40 + 16, 33 * 10 + 1);
                g2d.drawLine(16, i * 35 + 16, 37 * 10 + 5, i * 35 + 16);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawBackground(Graphics g){
        g.setColor(new Color(0,0,0));

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
