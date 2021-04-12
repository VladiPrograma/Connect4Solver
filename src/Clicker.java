import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Clicker extends JPanel implements MouseListener{
    static GameIA ia = new GameIA("4");
    static boolean win =false;
    public Clicker(){
        this.setLayout(null);
        this.setBounds(0,0,700,700);
        this.addMouseListener(this);

        this.setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!win) {
            int x = 50;
            for (int i = 1; i < 9; i++) {
                if (e.getX() > x && e.getX() < x + 50 && e.getY() > 100) {
                    if (ia.node.t.team() == 1) {
                        ia = new GameIA(ia.node.t.move(i).table);
                        Background.repaint(ia.node);
                        if (Clicker.ia.node.t.getScore() != 0) {
                            win=true;
                            Background.button.setVisible(true);
                            Background.button.setEnabled(true);
                            Background.score.setText("Ganas no sabes ni como xd");
                            Background.score.setForeground(new Color(0xF3F3F3));
                            Background.score.setOpaque(false);
                        }
                        repaint();
                    }
                }
                x += 75;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
