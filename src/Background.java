import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Background extends JPanel {
    static JLabel score;
    static JButton button;
    static JLabel[] tableboard= new JLabel[56];
    static ImageIcon blanco = new ImageIcon("src\\Images\\blanco.png");
    static ImageIcon rojo = new ImageIcon("src\\Images\\rojo.png");
    static ImageIcon amarillo = new ImageIcon("src\\Images\\amarillo.png");
    public Background(){
        this.setLayout(null);
        this.setBackground(new Color(0x2A2A2A));
        score = new JLabel();
        score.setBounds(250,30,150,50);
        score.setBackground(new Color(0xADADAD));

        button = new JButton("Jugar de Nuevo");
        button.setBounds(500,30, 150,50);
        button.setForeground(new Color(0xEFEFEF));
        button.setBackground(new Color(0x555555));
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setVisible(false);
        button.setEnabled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Clicker.win){
                Clicker.ia = new GameIA("4");
                repaint(Clicker.ia.node);
                button.setVisible(false);
                button.setEnabled(false);
                Clicker.win = false;
                score.setText("");
                repaint();
                }
            }
        });
        this.add(score);
        this.add(button);
        int x=0; int y=0;
        for (int i=0; i<56; i++){
            if (i%8==0&&i!=0){y+=75; x=0;}
            tableboard[i]= new JLabel();
            tableboard[i].setBounds(50+x,100+y, 50,50);
            tableboard[i].setIcon(blanco);
            this.add(tableboard[i]);
            x+=75;
        }
        repaint(Clicker.ia.node);
    }

    public static void repaint(GameIA.Node node){
        int[] arr = node.t.toArray();
        for (int i=0; i<56; i++){
            if (arr[i]==1){tableboard[i].setIcon(rojo);}
            if (arr[i]==-1){tableboard[i].setIcon(amarillo);}
            if (arr[i]==0){tableboard[i].setIcon(blanco);}
        }
    }
}
