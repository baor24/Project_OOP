package GameDisplay;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Model.GameMenuGUI;
import Model.LoadData;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private LoadData loadData;

	private GamePanel gamePanel;

	public GameFrame(int w, int h, int boom) {

		loadData = new LoadData();

		add(gamePanel = new GamePanel(w, h, boom, this));

		ImageIcon title = new ImageIcon("bomb.png");
		
		setIconImage(title.getImage());
		setTitle("Minesweeper");
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GameMenuGUI();
	}

	public LoadData getLoadData() {
		return loadData;
	}

	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

}
