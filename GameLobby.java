import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

class GameLobby{
	protected JFrame frame = new JFrame("Game Lobby");
    private JPanel mainPanel = new JPanel();

    private JTextArea chatArea;
    private JTextField chatInputField = new JTextField();

    private JButton startGameButton = new JButton("GAME!");

	private final int FRAME_WIDTH = 600;
	private final int FRAME_HEIGHT = 400;

	public GameLobby(Config config) {
        /**
         * config contains:
         *   - userType
         *   - hostIP
         *   - portNumber
         *   - username
         */

        // setup server (if chosen) and client here siguro?

        initialize();

		frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    public void initialize() {
        mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

        // setting up constraints for chatArea
        chatArea = new JTextArea(17, 30);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        chatArea.setEditable(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 6;
		mainPanel.add(scrollPane,c);

        // setting up constraints for chatInputField
        chatInputField.setColumns(30);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
		mainPanel.add(chatInputField,c);

        // setting up constraints for startGameButton
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
        c.gridy = 1;
        c.gridwidth = 1;
		mainPanel.add(startGameButton,c);
    }

}