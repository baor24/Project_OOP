package Model;
import javax.swing.*;

import GameDisplay.GameFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenuGUI extends JFrame {
    private JButton startButton;
    private JButton optionsButton;
    private JButton exitButton;

    public GameMenuGUI() {
		ImageIcon title = new ImageIcon(getClass().getResource("/images/bomb.png"));
		
		setIconImage(title.getImage());
		setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        startButton = new JButton("Start Game");
        optionsButton = new JButton("Options");
        exitButton = new JButton("Exit");

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new GameFrame(16,16,40).setVisible(true);;    
                setVisible(false);
            }
        });

        optionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
            	String[] options = { "Beginner","Easy", "Medium", "Hard","Expert" };
                int selectedOption = JOptionPane.showOptionDialog(null, "Select Difficulty", "Options", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
               
            	switch (selectedOption) {
                case 0:
                	new GameFrame(5,8,6).setVisible(true);;    
                    setVisible(false);
                    break;
                case 1:
                	new GameFrame(9,9,10).setVisible(true);;    
                    setVisible(false);
                    break;
                case 2:
                	new GameFrame(16,16,40).setVisible(true);;    
                    setVisible(false);
                    break;
                case 3:
                	new GameFrame(16,30,99).setVisible(true);;    
                    setVisible(false);
                    break;
                case 4:
                	new GameFrame(30,30,150).setVisible(true);;    
                    setVisible(false);
                    break;
                default:
                    break;
        }
                
        }});

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add code to exit the game here
                JOptionPane.showMessageDialog(null, "Exiting the game...");
                System.exit(0);
            }
        });

        panel.add(startButton);
        panel.add(optionsButton);
        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }

}
