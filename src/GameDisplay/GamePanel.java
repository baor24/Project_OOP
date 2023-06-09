package GameDisplay;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GameControl.World;
import Model.GameMenuGUI;
import Model.Sound;

public class GamePanel extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private PanelNotification p1;
	private PanelPlayer p2;
	private JButton menuButton;

	private GameFrame gameFrame;

	private World world;

	private int w;
	private int h;
	private int boom;

	public GamePanel(int w, int h, int boom, GameFrame gameFrame) {

		this.gameFrame = gameFrame;

		this.boom = boom;
		this.w = w;
		this.h = h;

		world = new World(w, h, boom, this);

		setLayout(new BorderLayout(20, 20));

		add(p1 = new PanelNotification(this), BorderLayout.NORTH);
		p1.add(menuButton = new JButton("Menu"), BorderLayout.SOUTH);
		menuButton.setPreferredSize(new Dimension(35, 35));
		menuButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameFrame.setVisible(false);
				new GameMenuGUI().setVisible(true);
			}
		});
		
		add(p2 = new PanelPlayer(this), BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		getP1().getBt().setStage(ButtonSmile.wow);
		getP1().getBt().repaint();
		ButtonPlay[][] arrayButton = p2.getArrayButton();
		for (int i = 0; i < arrayButton.length; i++) {
			for (int j = 0; j < arrayButton[i].length; j++) {
				if (e.getButton() == 1 && e.getSource() == arrayButton[i][j] && !world.getArrayCamCo()[i][j]) {

					if (!getP1().getTime().isRunning()) {
						getP1().getTime().start();
					}

					if (!world.open(i, j)) {

						if (world.isComplete()) {

							getP1().getTime().stop();
							getP1().getBt().setStage(ButtonSmile.lose);
							getP1().getBt().repaint();
							
							try {
								new Sound().playExplodeSound();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							int option = JOptionPane.showConfirmDialog(this, "You lost, play again?", "Notification",
									JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								gameFrame.setVisible(false);
								new GameFrame(w, h, boom);
							} else {
								world.fullTrue();
							}
						} else if (world.isEnd()) {

							getP1().getTime().stop();
							getP1().getBt().setStage(ButtonSmile.win);
							getP1().getBt().repaint();
							
							try {
								new Sound().playWinningSound();
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							int option = JOptionPane.showConfirmDialog(this, "You win, play again ?", "Notification",
									JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								gameFrame.setVisible(false);
								new GameFrame(w, h, boom);
							}
						}
					}
				} else if (e.getButton() == 3 && e.getSource() == arrayButton[i][j]) {
					world.flagged(i, j);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		getP1().getBt().setStage(ButtonSmile.now);
		getP1().getBt().repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public int getBoom() {
		return boom;
	}

	public void setBoom(int boom) {
		this.boom = boom;
	}

	public PanelNotification getP1() {
		return p1;
	}

	public void setP1(PanelNotification p1) {
		this.p1 = p1;
	}

	public PanelPlayer getP2() {
		return p2;
	}

	public void setP2(PanelPlayer p2) {
		this.p2 = p2;
	}
}
