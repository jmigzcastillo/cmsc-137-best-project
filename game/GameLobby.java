package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class GameLobby{
	protected JFrame frame = new JFrame("What the Tank?!");
    private JPanel mainPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JButton startGameButton = new JButton("START");
    private JButton instructionGameButton = new JButton("INSTRUCTIONS");

	private JLabel jl = new JLabel();

	private final int FRAME_WIDTH = 600;
	private final int FRAME_HEIGHT = 600;

	public GameLobby() {

        // super(600, 600, "What the tank?!");
        initialize();

		// frame.setContentPane(mainPanel);
		// frame.add(buttonPanel);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// // frame.pack();
		// frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        // frame.setVisible(true);
        // frame.getContentPane().setBackground(Color.cyan);
    }

    public void initialize() {
        mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 3;
        mainPanel.add(startGameButton, c);

        c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 3;
        mainPanel.add(instructionGameButton, c);
	});
		// instructionGameButton.addActionListener(new ActionListener(){
        //     @Override
        //     public void actionPerformed(ActionEvent e){
		// 		frame.dispose();
		// 		JFrame frame = new JFrame("What the Tank?!");
        //         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //         JPanel panel = new JPanel();
        //         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //         panel.setOpaque(true);
        //         JPanel inputpanel = new JPanel();
        //         inputpanel.setLayout(new FlowLayout());
        //         JButton button = new JButton("BACK");
        //         inputpanel.add(button);
        //         panel.add(inputpanel);
        //         frame.getContentPane().add(BorderLayout.CENTER, panel);
        //         frame.pack();
        //         frame.setLocationByPlatform(true);
        //         frame.setVisible(true);
        //         frame.setResizable(false);
        //         button.addActionListener(new ActionListener(){
		// 		    @Override
		// 		    public void actionPerformed(ActionEvent e){
		// 				frame.setContentPane(mainPanel);
		// 				frame.add(buttonPanel);
		// 				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 				// frame.pack();
		// 				frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		// 				frame.setVisible(true);
		// 				frame.getContentPane().setBackground(Color.cyan);
        //             }
        //         });
        //     }
        // });
    }

    public void update(){}
    public void render(Graphics g){}

}
