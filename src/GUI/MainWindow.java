package GUI;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Елена on 02.06.2016.
 */
public class MainWindow extends JFrame {
    public JLabel playingNoteTitle;

    public MainWindow(String title, int width, int height, URL iconPath) throws HeadlessException {
        super(title);
        setPreferredSize(new Dimension(width, height));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) (dim.getWidth() - width) / 2, (int) (dim.getHeight() - height) / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon icon = new ImageIcon(iconPath);
        setIconImage(icon.getImage());

        playingNoteTitle = new JLabel();
        playingNoteTitle.setBounds(120,10,100,50);
    }

    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        Dimension m = getMaximumSize();
        boolean resize = d.width > m.width || d.height > m.height;
        d.width = Math.min(m.width, d.width);
        d.height = Math.min(m.height, d.height);

        if (resize) {
            Point p = getLocation();
            setVisible(false);
            setSize(d);
            setLocation(p);
            setVisible(true);
        }
        super.paint(g);
    }
}
