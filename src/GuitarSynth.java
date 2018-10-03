import javax.sound.midi.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Елена on 30.05.2016.
 */
public class GuitarSynth {
    private MidiChannel channel;
    private Instrument instrument;
    String current_note;

    GuitarSynth() throws MidiUnavailableException {
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        if(!synthesizer.isOpen())
        {
            synthesizer.open();
        }

        this.channel = synthesizer.getChannels()[0]; // Midi Channel #0
//        Instrument[] instrs = synthesizer.getDefaultSoundbank().getInstruments();
//        for(int i = 0; i < instrs.length; i++){
//            System.out.println(i + " - " + instrs[i].getName());
//        }
        this.instrument = synthesizer.getDefaultSoundbank().getInstruments()[25]; // Steel acoustic guitar

        this.channel.programChange(0, 25); // bank #0, instrument #29 (Steel acoustic guitar)
        synthesizer.loadInstrument(this.instrument);
    }

    public GuitarSynth(int channelNum, int instrumentNum) throws MidiUnavailableException {
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();

        this.channel = synthesizer.getChannels()[channelNum];
        this.instrument = synthesizer.getDefaultSoundbank().getInstruments()[instrumentNum];

        this.channel.programChange(0, instrumentNum);
        synthesizer.loadInstrument(this.instrument);
    }

    void playNote(int note, int duration)
    {
        this.channel.noteOn(note, 70); // 70 is the volume level
        final MidiChannel channel = this.channel;
        final int midiNote = note;
        Timer timer = new Timer(duration*1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer = (Timer) e.getSource();
                channel.noteOff(midiNote);
                timer.stop();
            }
        });
        timer.start();
    }
}
