package Model;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner; 

public class Sound {
	private static Clip clip1,clip2;
	
	private static final String explode_sound = "Explosion2.wav";
	private static final String winning_sound = "tadaa-47995.wav";

	public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		 File soundFile1 = new File(explode_sound);  // Replace with the actual file path
         AudioInputStream audioIn1 = AudioSystem.getAudioInputStream(soundFile1);
         clip1 = AudioSystem.getClip();
         clip1.open(audioIn1);
         
         File soundFile2 = new File(winning_sound);  // Replace with the actual file path
         AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(soundFile2);
         clip2 = AudioSystem.getClip();
         clip2.open(audioIn2);
	}
	
	public void playExplodeSound() {
		this.clip1.start();
	}
	
	public void playWinningSound() {
		this.clip2.start();
	}
}

