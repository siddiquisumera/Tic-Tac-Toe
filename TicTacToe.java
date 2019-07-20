package TicTacToe;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToe extends JFrame implements ActionListener {

	private int board_size = 3;

	private JButton[][] buttons = new JButton[board_size][board_size];
	private boolean xMove = true;

	public static enum gameStatus {

		Incomplete, Tie, XWins, ZWins
	}

	public TicTacToe() {

		super.setTitle("My First Game");
		super.setSize(500, 700);
		super.setVisible(true);

		GridLayout grid = new GridLayout(board_size, board_size);
		Font font = new Font("Comic Sans", 1, 200);
		super.setLayout(grid);
		super.setResizable(false);
		for (int row = 0; row < board_size; row++) {
			for (int col = 0; col < board_size; col++) {
				JButton button = new JButton();
				buttons[row][col] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton clockedButton = (JButton) arg0.getSource();
		makeMove(clockedButton);

		gameStatus status = showGameStatus();
		if (status.equals("Incomplete")) {
			return;
		} else if (!status.equals("Tie")) {
			declareResult(status);
		} else {

			JOptionPane.showMessageDialog(this, "It is a tie");

		}

	}

	public void makeMove(JButton clickedButton) {

		String buttonText = clickedButton.getText();

		if (buttonText.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid move");

		} else {
			if (xMove) {
				clickedButton.setText("x");
			} else {
				clickedButton.setText("0");

			}

			xMove = !xMove;

		}

	}

	private gameStatus showGameStatus() {

		String text1 = "", text2 = "";
		int row = 0, col = 0;

		// check horizontal
		while (row < board_size) {

			col = 0;
			while (col < board_size - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col + 1].getText();

				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}

				col++;
			}

			if (col == board_size - 1) {
				if (text1.equals("x")) {
					return gameStatus.XWins;
				} else {
					return gameStatus.ZWins;

				}
			}
			row++;
		}

		row = 0;
		col = 0;
		// check vertical
		while (col < board_size) {

			row = 0;
			while (row < board_size - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row + 1][col].getText();

				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}

				row++;
			}

			if (row == board_size - 1) {
				if (text1.equals("x")) {
					return gameStatus.XWins;
				} else {
					return gameStatus.ZWins;

				}
			}
			col++;
		}

		row = 0;
		col = 0;
		// check diagonal1
		while (row < board_size - 1) {

			text1 = buttons[row][col].getText();
			text2 = buttons[row + 1][col + 1].getText();

			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}

			col++;
			row++;

		}

		if (row == board_size - 1) {
			if (text1.equals("x")) {
				return gameStatus.XWins;
			} else {
				return gameStatus.ZWins;
			}
		}
		row = 0;
		col = board_size - 1;

		// check diagonal2
		while (row < board_size - 1) {

			text1 = buttons[row][col].getText();
			text2 = buttons[row + 1][col - 1].getText();

			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}

			col--;
			row++;

		}

		if (row == board_size - 1) {
			if (text1.equals("x")) {
				return gameStatus.XWins;
			} else {
				return gameStatus.ZWins;
			}
		}

		// check for incomplete status
		for (row = 0; row < board_size; row++) {

			for (col = 0; col < board_size; col++) {
				String text = buttons[row][col].getText();

				if (text.length() == 0) {
					return gameStatus.Incomplete;
				}
			}
		}

		return gameStatus.Tie;

	}

	private void declareResult(gameStatus status) {

		if (status == gameStatus.XWins) {
			JOptionPane.showMessageDialog(this, "X wins");
		} else if (status == gameStatus.ZWins) {
			JOptionPane.showMessageDialog(this, "Z wins");
		}

	}
}
