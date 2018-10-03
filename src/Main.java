import Enums.FretBounds;
import Enums.Notes;
import GUI.JFret;
import GUI.MainWindow;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws MidiUnavailableException, IOException {

        final GuitarSynth guitar = new GuitarSynth();
        final MainWindow mainWindow = new MainWindow("Guitar Trainer", 1024, 525, Main.class.getResource("resources/images/GuitarTrainerIcon.png"));

        final JPanel pane = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                URL iconURL = Main.class.getResource("resources/images/guitar_1024x525.png");
                Image bgimage = new ImageIcon(iconURL).getImage();
                g.drawImage(bgimage, 0, 0, getWidth(), getHeight(), null);
            }
        };
        pane.setLayout(null);

        pane.add(mainWindow.playingNoteTitle);

        final Map<String, JFret> frets = new HashMap<>();

        // Guitar range - 5 octaves
        final FretBounds[] fretBounds = FretBounds.values();
        for(int i = 0; i < fretBounds.length; i++){
            JFret fret = new JFret(fretBounds[i].getNote());
            fret.setBounds(fretBounds[i].getX(), fretBounds[i].getY(), fretBounds[i].getWidth(), fretBounds[i].getHeight());
            fret.setMidiNum(Notes.valueOf(fretBounds[i].getNote()).getMidiNum());
            pane.add(fret);

            fret.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    final JFret fret = (JFret) event.getSource();
                    guitar.playNote(fret.getMidiNum(), 2); // 1 sec duration
                    if (guitar.current_note != null) {
                        mainWindow.playingNoteTitle.setText(guitar.current_note);
                        if (fret.getName().equals(guitar.current_note)) {
                            fret.setBackground(Color.GREEN);
                            mainWindow.playingNoteTitle.setForeground(Color.GREEN);
                        } else {
                            fret.setBackground(Color.RED);
                            mainWindow.playingNoteTitle.setForeground(Color.RED);
                        }
                        fret.setText(fret.getName());
                        fret.setContentAreaFilled(true);
                        final ArrayList<JFret> correctFrets = new ArrayList<JFret>();
                        for(Map.Entry<String, JFret> fr : frets.entrySet()) {
                            JFret f = fr.getValue();
                            if(f.getName().equals(guitar.current_note)){
                                f.setBackground(Color.GREEN);
                                f.setText(guitar.current_note);
                                f.setContentAreaFilled(true);
                                correctFrets.add(f);
                            }
                        }
                        final Timer timer = new Timer(2000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Timer timer = (Timer) e.getSource();
                                fret.setText("");
                                fret.setContentAreaFilled(false);
                                for(int i = 0; i < correctFrets.size(); i++){
                                    JFret f = correctFrets.get(i);
                                    f.setText("");
                                    f.setContentAreaFilled(false);
                                }
                                mainWindow.playingNoteTitle.setText("");
                                timer.stop();
                            }
                        });
                        timer.start();
                    }
                    guitar.current_note = null;
                }
            });

            frets.put(fretBounds[i].toString(), fret);
        }

        final JButton showNotes = new JButton("Show notes");
        showNotes.setBounds(10,60,100,50);
        showNotes.setMargin(new Insets(10,10,10,10));
        showNotes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                if(btn.getText().equals("Show notes")){
                    btn.setText("Hide notes");
                    for(Map.Entry<String, JFret> fret : frets.entrySet()) {
                        JFret f = fret.getValue();
                        f.setBackground(f.getColor());
                        f.setText(f.getName());
                        f.setContentAreaFilled(true);
                    }
                } else {
                    btn.setText("Show notes");
                    for(Map.Entry<String, JFret> fret : frets.entrySet()) {
                        JFret f = fret.getValue();
                        f.setText("");
                        f.setContentAreaFilled(false);
                    }
                }
            }
        });
        pane.add(showNotes);

        JButton earTrainer = new JButton("Ear Trainer");
        earTrainer.setBounds(10,10,100,50);
        earTrainer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showNotes.getText().equals("Hide notes")){
                    showNotes.setText("Show notes");
                    for(Map.Entry<String, JFret> fret : frets.entrySet()) {
                        JFret f = fret.getValue();
                        f.setText("");
                        f.setContentAreaFilled(false);
                    }
                }
                Random random = new Random(System.currentTimeMillis());
                int i = random.nextInt(fretBounds.length);
                guitar.current_note = fretBounds[i].getNote();
                guitar.playNote(Notes.valueOf(guitar.current_note).getMidiNum(), 1);
            }
        });
        pane.add(earTrainer);

        mainWindow.getContentPane().add(pane);
        mainWindow.pack();
        mainWindow.setVisible(true);

    }
}
