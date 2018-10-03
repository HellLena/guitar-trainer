package GUI;

import javax.swing.*;
import java.awt.*;
/**
 * Created by Елена on 01.06.2016.
 */
public class JFret extends JButton {

    private int midiNum;
    private String name;
    private Color color;

    public JFret(String name) {
        super();
        this.name = name;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setFont(new Font("Arial", Font.PLAIN, 10));
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setForeground(Color.WHITE);
        if(name.contains("2")){
            this.color = Color.BLUE;
        } else if(name.contains("3")){
            this.color = Color.RED;
        } else if(name.contains("4")){
            this.color = Color.GREEN;
        } else if(name.contains("5")){
            this.color = Color.PINK;
        } else if(name.contains("6")){
            this.color = Color.YELLOW;
        } else {
            this.color = Color.GRAY;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public int getMidiNum() {
        return midiNum;
    }

    public void setMidiNum(int midiNum) {
        this.midiNum = midiNum;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
