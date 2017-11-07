import java.awt.*;
import javax.swing.*;

class GameLauncher{
	private JFrame frame = new JFrame("Game Launcher");
	private JPanel mainPanel = new JPanel();
	private JPanel input1Panel = new JPanel();
	private JPanel input2Panel = new JPanel();
	private JPanel radioButtonGroupPanel= new JPanel();
	private JPanel textFieldGroupPanel= new JPanel();

	private JLabel youAreALabel = new JLabel("You are a:");
	private JLabel enterLabel = new JLabel("Enter:");
	private JLabel hostIPLabel = new JLabel("Host IP");
	private JLabel portNoLabel = new JLabel("Port no.");

	private JRadioButton clientRadioButton = new JRadioButton("Client");
	private JRadioButton serverRadioButton = new JRadioButton("Server");

    private JFormattedTextField hostIPField = new JFormattedTextField("localhost");	//i-set mo sa 000.000.000.000
	private JTextField portNoField = new JTextField("2000"); //2000 yung default

	private JButton launchGameButton = new JButton("LAUNCH GAME");

	private final int FRAME_WIDTH = 270;
	private final int FRAME_HEIGHT = 210;

	public GameLauncher() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		setInput1Panel();
		setInput2Panel();

		launchGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(launchGameButton);

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

		// radioButtonGroupPanel
		textFieldGroupPanel.setLayout(new GridLayout(2, 2));
		textFieldGroupPanel.add(hostIPLabel);
		textFieldGroupPanel.add(hostIPField);
		textFieldGroupPanel.add(portNoLabel);
		textFieldGroupPanel.add(portNoField);

		// setting up constraints for radioButtonGroupPanel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		input2Panel.add(textFieldGroupPanel,c);

		mainPanel.add(input2Panel);
	}

	public static void main(String[] args) {
		new GameLauncher();
	}
}