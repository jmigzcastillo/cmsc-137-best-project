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
import java.awt.event.*;
import java.awt.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

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
	public JTextField textField;
	public JTextArea chatArea;

	public String name = "";
	public String message = "";

	public BufferedImage bg;

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
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER && !textField.getText().equals('\n')) { // empty string 
					message = textField.getText();
					// System.out.println(message);
					textField.setText("");
				}
			}
		});
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

		try {
			bg = ImageIO.read(ImageLoader.class.getResource("../sprites/instructions.jpg"));
		} catch (Exception e) {}
		// Image scaledImage = originalImage.getScaledInstance(jPanel.getWidth(),jPanel.getHeight(),Image.SCALE_SMOOTH);
		Image bgbg = bg.getScaledInstance(800, 560, Image.SCALE_SMOOTH);
		cardHolder = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);
					g.drawImage(bgbg, 0, 0, null);
			}
		};
		cardHolder.setSize(800, 600);
		cardHolder.setBackground(Color.RED);

		initLobby();
		// initInstructions();

		// cards.add(game, "Game");
		cards.add(lobby, "Lobby");
		// cards.add(instructions, "Instructions");

		cardHolder.add(cards);
		frame.add(cardHolder);



		// frame.add(lobby);

		frame.add(game);

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	void initLobby() {
		lobby = new JPanel();
		// lobby.setBackground(Color.YELLOW);
		// lobby.setSize(800, 600);
		lobby.setLayout(new BoxLayout(lobby, BoxLayout.Y_AXIS));
		playButton = new JButton("Play");
		// playButton.setPreferredSize(new Dimension(300, 30));
		playButton.setBackground(Color.GREEN);
		playButton.setForeground(Color.WHITE);		
		// instructionsButton = new JButton("Instructions");
		// instructionsButton.setPreferredSize(new Dimension(300, 30));
		// instructionsButton.setBackground(Color.BLUE);
		// instructionsButton.setForeground(Color.WHITE);	
		lobby.add(Box.createVerticalGlue());
		lobby.add(playButton);
		playButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cardLayout = (CardLayout)(cards.getLayout());
				// cardLayout.show(cards, "Game");
				cardHolder.setVisible(false);
				// lobby.setVisible(false);
			}
		});
		// lobby.add(instructionsButton);
		// instructionsButton.addActionListener(new ActionListener(){
		// 	@Override
		// 	public void actionPerformed(ActionEvent arg0) {
		// 		CardLayout cardLayout = (CardLayout)(cards.getLayout());
		// 		cardLayout.show(cards, "Instructions");
		// 	}
		// });
		// lobby.add(Box.createVerticalGlue());
	}

	// void initInstructions() {
	// 	instructions = new JPanel();
	// 	instructions.setBackground(Color.YELLOW);
	// 	mainMenuButton = new JButton("Go back to Main Menu");
	// 	instructions.add(mainMenuButton);
	// 	mainMenuButton.addActionListener(new ActionListener(){
	// 		@Override
	// 		public void actionPerformed(ActionEvent arg0) {
	// 			CardLayout cardLayout = (CardLayout)(cards.getLayout());
	// 			cardLayout.show(cards, "Lobby");
	// 		}
	// 	});
	// }
}