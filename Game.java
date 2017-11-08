import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

class Game{
	protected JFrame frame = new JFrame("Game Launcher");
	private JPanel mainPanel = new JPanel();
	private JPanel input1Panel = new JPanel();
	private JPanel input2Panel = new JPanel();
	private JPanel radioButtonGroupPanel= new JPanel();
	private JPanel textFieldGroupPanel= new JPanel();

	private JLabel youAreALabel = new JLabel("You are a:");
	private JLabel enterLabel = new JLabel("Enter:");
	private JLabel hostIPLabel = new JLabel("Host IP");
	private JLabel portNoLabel = new JLabel("Port no.");
	private JLabel usernameLabel = new JLabel("Username");

	private ButtonGroup csButtonGroup = new ButtonGroup();
	private JRadioButton clientRadioButton = new JRadioButton("Client", true);
	private JRadioButton serverRadioButton = new JRadioButton("Server", false);

	private MaskFormatter mask = null;

    private JFormattedTextField hostIPField;
	private JTextField portNoField = new JTextField("2000"); //2000 yung default
	private JTextField usernameField = new JTextField("Username");

	public JButton launchGameButton = new JButton("LAUNCH GAME");

	private final int FRAME_WIDTH = 270;
	private final int FRAME_HEIGHT = 220;

	public Game() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		setInput1Panel();
		setInput2Panel();

		launchGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(launchGameButton);

		launchGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				Config userConfig = new Config();
				if (serverRadioButton.isSelected()) {
					userConfig.userType = "Server";
				} else {
					userConfig.userType = "Client";
				}
				userConfig.hostIP = hostIPField.getText();
				userConfig.portNumber = portNoField.getText();
				userConfig.username = usernameField.getText();

				// close window
				frame.dispose();

				// open Game Lobby and pass the user configuration
				new GameLobby(userConfig);
            }
		});

		frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
	}

	private void setInput1Panel() {
		input1Panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// setting up constraints for youAreALabel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		input1Panel.add(youAreALabel,c);

		// adding client and server radio buttons to button group to maintain
		// selected state of buttons
		csButtonGroup.add(clientRadioButton);
		csButtonGroup.add(serverRadioButton);

		// radioButtonGroupPanel
		radioButtonGroupPanel.setLayout(new BoxLayout(radioButtonGroupPanel, BoxLayout.PAGE_AXIS));
		radioButtonGroupPanel.add(clientRadioButton);
		radioButtonGroupPanel.add(serverRadioButton);

		// setting up constraints for radioButtonGroupPanel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		input1Panel.add(radioButtonGroupPanel,c);

		mainPanel.add(input1Panel);
	}

	private void setInput2Panel() {
		input2Panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// setting up constraints for youAreALabel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		input2Panel.add(enterLabel,c);

		// sets mask for JFormattedTextField
		try{
			mask = new MaskFormatter("###.###.###.###");
			mask.setPlaceholder("192.168.001.008");
		} catch (Exception e) {
			e.printStackTrace();
		}
		hostIPField = new JFormattedTextField(mask);

		// textFieldGroupPanel
		textFieldGroupPanel.setLayout(new GridLayout(3, 3));
		textFieldGroupPanel.add(hostIPLabel);
		textFieldGroupPanel.add(hostIPField);
		textFieldGroupPanel.add(portNoLabel);
		textFieldGroupPanel.add(portNoField);
		textFieldGroupPanel.add(usernameLabel);
		textFieldGroupPanel.add(usernameField);

		// setting up constraints for radioButtonGroupPanel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		input2Panel.add(textFieldGroupPanel,c);

		mainPanel.add(input2Panel);
	}

}