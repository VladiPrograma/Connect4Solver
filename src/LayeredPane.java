import javax.swing.*;

public class LayeredPane extends JLayeredPane {
    Background background = new Background();
    Clicker cliker = new Clicker();
    public LayeredPane(){
        background.setBounds(0,0,700,700);
        cliker.setBounds(0,0,700,700);
        this.add(background, Integer.valueOf(0));
        this.add(cliker, Integer.valueOf(1));
        this.addMouseListener(cliker);
    }
}
