package Model;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private Clip clip1, clip2;

    public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL audioFile1URL = getClass().getResource("/sounds/Explosion2.wav");
        AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(audioFile1URL);
        clip1 = AudioSystem.getClip();
        clip1.open(audioIn1);

        URL audioFile2URL = getClass().getResource("/sounds/tadaa-47995.wav");
        AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(audioFile2URL);
        clip2 = AudioSystem.getClip();
        clip2.open(audioIn2);
    }

    public void playExplodeSound() {
        clip1.start();
    }

    public void playWinningSound() {
        clip2.start();
    }
}
