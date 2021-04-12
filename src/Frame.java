import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
        static LayeredPane lp = new LayeredPane();
        Timer timer = new Timer(500, this);
    public Frame(){
        this.setLayout(null);
        this.setSize(700,700);
        lp.setBounds(0,0,700,700);
        timer.start();
        this.add(lp);
        this.addMouseListener(lp.cliker);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();

            if (e.getSource().equals(timer)) {
                if (!Clicker.win) {
                if (Clicker.ia.node.t.team() == -1) {
                    Clicker.ia = new GameIA(Clicker.ia.getBestMove().t.table);
                    Background.repaint(Clicker.ia.node);
                    if (Clicker.ia.node.t.getScore() != 0) {
                        Clicker.win=true;
                        Background.button.setVisible(true);
                        Background.button.setEnabled(true);
                        lp.background.score.setText("Perdiste Tonto xd");
                        lp.background.score.setForeground(new Color(0xF3F3F3));
                        lp.background.score.setOpaque(false);
                    }
                    repaint();
                }
            }
        }
    }
}
