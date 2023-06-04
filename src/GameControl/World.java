package GameControl;

import java.util.Random;

import GameDisplay.ButtonPlay;
import GameDisplay.ButtonSmile;
import GameDisplay.GamePanel;
import GameDisplay.LableNumber;

public class World {

	private Random rd;

	private ButtonPlay[][] arrayButton;
	private int[][] arrayMin;

	private boolean[][] arrayBoolean;
	private boolean[][] arrayFlag;
	private int Flag;

	private boolean isComplete;
	private boolean isEnd;

	private ButtonSmile buttonSmile;
	private LableNumber lbTime, lbBoom;

	private int boom;

	private GamePanel game;

	public World(int w, int h, int boom, GamePanel game) {

		this.game = game;
		this.boom = boom;

		arrayButton = new ButtonPlay[w][h];
		arrayMin = new int[w][h];
		arrayBoolean = new boolean[w][h];
		arrayFlag = new boolean[w][h];

		rd = new Random();

		createArrayMin(boom, w, h);
		FillintheArray();
		
//		System.out.println("number of bombs "+ boom);
//		
//		for (int i=0;i<arrayButton.length;i++) {
//			for (int j=0; j<arrayButton[i].length;j++) {
//				System.out.print(arrayMin[i][j]+" ");
//			}
//			System.out.println();
//		}
	}

	public void flagged(int i, int j) {
		if (!arrayBoolean[i][j]) {
			if (arrayFlag[i][j]) {
				Flag--;
				arrayFlag[i][j] = false;
				arrayButton[i][j].setNumber(-1);
				arrayButton[i][j].repaint();
				game.getP1().updateLbBoom();
			} else if (Flag < boom) {
				Flag++;
				arrayFlag[i][j] = true;
				arrayButton[i][j].setNumber(9);
				arrayButton[i][j].repaint();
				game.getP1().updateLbBoom();
			}
		}

	}

	public boolean open(int i, int j) {

		if (!isComplete && !isEnd) {
			if (!arrayBoolean[i][j]) {
				if (arrayMin[i][j] == 0) {

					arrayBoolean[i][j] = true;
					arrayButton[i][j].setNumber(0);
					arrayButton[i][j].repaint();

					if (checkWin()) {
						isEnd = true;

						return false;
					}

					for (int l = i - 1; l <= i + 1; l++) {
						for (int k = j - 1; k <= j + 1; k++) {
							if (l >= 0 && l <= arrayMin.length - 1 && k >= 0 && k <= arrayMin[i].length - 1) {
								if (!arrayBoolean[l][k] && !arrayFlag[l][k]) { // open unflagged squares
									open(l, k);
								}
							}
						}
					}

					if (checkWin()) {
						isEnd = true;

						return false;
					}

				} else {

					int number = arrayMin[i][j];

					if (number != -1) {

						arrayBoolean[i][j] = true;

						arrayButton[i][j].setNumber(number);
						arrayButton[i][j].repaint();

						if (checkWin()) {
							isEnd = true;

							return false;
						}

						return true;
					}
				}
			}

			if (arrayMin[i][j] == -1) {
				arrayButton[i][j].setNumber(11);
				arrayButton[i][j].repaint();
				isComplete = true;

				for (int j2 = 0; j2 < arrayBoolean.length; j2++) {
					for (int k = 0; k < arrayBoolean[i].length; k++) {
						if (arrayMin[j2][k] == -1 && !arrayBoolean[j2][k]) {
							if (j2 != i || k != j) {
								arrayButton[j2][k].setNumber(10);
								arrayButton[j2][k].repaint();
							}
						}
					}
				}

				return false;
			} else {

				if (checkWin()) {
					isEnd = true;

					return false;
				}

				return true;
			}
		} else

			return false;

	}

	public boolean checkWin() {
		int count = 0;
		for (int i = 0; i < arrayBoolean.length; i++) {
			for (int j = 0; j < arrayBoolean[i].length; j++) {
				if (!arrayBoolean[i][j]) {
					count++;
				}
			}
		}
		if (count == boom)
			return true;
		else
			return false;
	}

	public void FillintheArray() {
		for (int i = 0; i < arrayMin.length; i++) {
			for (int j = 0; j < arrayMin[i].length; j++) {
				if (arrayMin[i][j] == 0) {
					int count = 0;
					for (int l = i - 1; l <= i + 1; l++) {
						for (int k = j - 1; k <= j + 1; k++) {
							if (l >= 0 && l <= arrayMin.length - 1 && k >= 0 && k <= arrayMin[i].length - 1)
								if (arrayMin[l][k] == -1) {
									count++;
								}
						}
					}
					arrayMin[i][j] = count;
				}
			}
		}
	}

	public void createArrayMin(int boom, int w, int h) {
		int locationX = rd.nextInt(w);
		int locationY = rd.nextInt(h);

		arrayMin[locationX][locationY] = -1;
		int count = 1;
		while (count != boom) {
			locationX = rd.nextInt(w);
			locationY = rd.nextInt(h);
			if (arrayMin[locationX][locationY] != -1) {

				arrayMin[locationX][locationY] = -1;

				count = 0;
				for (int i = 0; i < arrayMin.length; i++) {
					for (int j = 0; j < arrayMin[i].length; j++) {
						if (arrayMin[i][j] == -1)
							count++;
					}
				}
			}
		}

	}

	public void fullTrue() {
		for (int i = 0; i < arrayBoolean.length; i++) {
			for (int j = 0; j < arrayBoolean[i].length; j++) {
				if (!arrayBoolean[i][j]) {
					arrayBoolean[i][j] = true;
				}
			}
		}
	}

	public ButtonPlay[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(ButtonPlay[][] arrayButton) {
		this.arrayButton = arrayButton;
	}

	public ButtonSmile getButtonSmile() {
		return buttonSmile;
	}

	public void setButtonSmile(ButtonSmile buttonSmile) {
		this.buttonSmile = buttonSmile;
	}

	public LableNumber getLbTime() {
		return lbTime;
	}

	public void setLbTime(LableNumber lbTime) {
		this.lbTime = lbTime;
	}

	public LableNumber getLbBoom() {
		return lbBoom;
	}

	public void setLbBoom(LableNumber lbBoom) {
		this.lbBoom = lbBoom;
	}

	public boolean[][] getArrayBoolean() {
		return arrayBoolean;
	}

	public void setArrayBoolean(boolean[][] arrayBoolean) {
		this.arrayBoolean = arrayBoolean;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public boolean[][] getArrayCamCo() {
		return arrayFlag;
	}

	public void setArrayFlag(boolean[][] arrayCamCo) {
		this.arrayFlag = arrayCamCo;
	}

	public int getFlag() {
		return Flag;
	}

	public void setFlag(int Flag) {
		this.Flag = Flag;
	}

}
