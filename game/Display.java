package game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
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

	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextArea chatArea;

	public String name;

	public Display(int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));

		name = JOptionPane.showInputDialog(null, "Enter your name: ");

		chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());
		chatPanel.setSize(200, 600);
		chatPanel.setBackground(Color.BLACK);

		textField = new JTextField(20);
		textField.setPreferredSize(new Dimension(20, 200));
		chatArea = new JTextArea(30,4);
		chatArea.setPreferredSize(new Dimension(200, 520));
		chatArea.setBackground(Color.BLACK);
		chatArea.setForeground(Color.WHITE);
		
		scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        chatArea.setEditable(false);

		chatPanel.add(chatArea, BorderLayout.PAGE_START);
		chatPanel.add(textField, BorderLayout.CENTER);

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
		lobby.setLayout(new BoxLayout(lobby, BoxLayout.Y_AXIS));
		playButton = new JButton("Play");
		playButton.setPreferredSize(new Dimension(300, 30));
		playButton.setBackground(Color.GREEN);
		playButton.setForeground(Color.WHITE);		
		instructionsButton = new JButton("Instructions");
		instructionsButton.setPreferredSize(new Dimension(300, 30));
		instructionsButton.setBackground(Color.BLUE);
		instructionsButton.setForeground(Color.WHITE);	
		// lobby.add(Box.createVerticalGlue());
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
		// lobby.add(Box.createVerticalGlue());
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