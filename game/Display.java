package game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Display{

	private JPanel cardHolder;
	private JPanel cards;
	private JPanel lobby;
	private JPanel chatPanel;
	private JPanel instructions;

	private JButton playButton;
	private JButton instructionsButton;
	private JButton mainMenuButton;

	public Display(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));

		chatPanel = new JPanel();
		chatPanel.setSize(200, 600);
		chatPanel.setBackground(Color.RED);

		frame.add(chatPanel);


		cards = new JPanel(new CardLayout());
		cardHolder = new JPanel();
		cardHolder.setSize(800, 600);

		initLobby();
		initInstructions();

		// cards.add(game, "Game");
		cards.add(lobby, "Lobby");
		cards.add(instructions, "Instructions");

		cardHolder.add(cards);
		frame.add(cardHolder);





		frame.add(game);

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	void initLobby() {
		lobby = new JPanel();
		playButton = new JButton("Play");
		instructionsButton = new JButton("Instructions");
		lobby.add(playButton);
		playButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout)(cards.getLayout());
				// cardLayout.show(cards, "Game");
				cardHolder.setVisible(false);
			}
		});
		lobby.add(instructionsButton);
		instructionsButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout)(cards.getLayout());
				cardLayout.show(cards, "Instructions");
			}
		});
	}

	void initInstructions() {
		instructions = new JPanel();
		mainMenuButton = new JButton("Go back to Main Menu");
		instructions.add(mainMenuButton);
		mainMenuButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout)(cards.getLayout());
				cardLayout.show(cards, "Lobby");
			}
		});
	}
}