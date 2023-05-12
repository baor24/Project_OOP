package Audio;

import java.io.File;
import javax.sound.sampled.*;
import java.util.*;

public class Audio {

    public static void main(String[] args) throws Exception {
    	Scanner inp = new Scanner(System.in);
    	
        // Get an audio input stream from the file.
    	File file = new File("C:\\Users\\DELL\\Desktop\\Bao Truong\\Coding\\Java\\TermProject\\AudioFiles\\emcuangayhomqua.wav");
//    	File file = new File("/Audio/src/Files/emcuangayhomqua.wav");
    	
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        
        String command = "";
         
        while(!command.equals("Q")) {
        	System.out.println("P = play, S = stop, Q = quit, R = reset");
        	System.out.print("Enter your command: ");
        	
        	command = inp.next();
        	command = command.toUpperCase();
        	
//        	If else :)))
        	switch(command) {
        	case ("P"): clip.start(); // Start the clip
        	break;
        	case ("S"): clip.stop(); // Stop the audio
        	break;
        	case ("R"): clip.setMicrosecondPosition(0);
        	break;
        	case ("Q"): clip.close();
        	break;
        	
//        	Can be understand as the else
        	default: System.out.println("Not a valid response");
        	}
        	
        }
        
        System.out.print("Bye");
        // Wait for the clip to finish playing.
//        clip.drain();
    }
}