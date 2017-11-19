import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.io.*;
import java.net.*;

public class GameLobby extends Window{
	// protected JFrame frame = new JFrame("What the Tank?!");
    private JPanel mainPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    protected JTextArea chatArea;
    protected JTextField chatInputField = new JTextField();

    protected JButton sendGameButton = new JButton("SEND");
    protected JButton startGameButton = new JButton("START");
    protected JButton instructionGameButton = new JButton("INSTRUCTIONS");

	private JLabel jl = new JLabel();

	private final int FRAME_WIDTH = 600;
	private final int FRAME_HEIGHT = 600;

    private Window gameWindow;

    private OutputStream outputStream;

	public GameLobby(OutputStream outputStream) {

        // setup server (if chosen) and client here siguro?
        super(600, 600, "What the tank?!");
        this.outputStream = outputStream;
        initialize();

		frame.setContentPane(mainPanel);
		frame.add(buttonPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.cyan);
    }

    public void initialize() {
        mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        //jl.setIcon(new ImageIcon("Tank.jpg"));
        //mainPanel.add(jl);

        // setting up constraints for chatArea
        chatArea = new JTextArea(10, 10);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        chatArea.setEditable(false);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 6;
		mainPanel.add(scrollPane,c);

        // setting up constraints for chatInputField
        chatInputField.setColumns(30);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 4;
        c.insets = new Insets(5, 0, 5, 0);
		mainPanel.add(chatInputField,c);

        // setting up constraints for startGameButton
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
        c.gridy = 1;
        c.gridwidth = 1;
        mainPanel.add(sendGameButton,c);

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

        startGameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                gameWindow = new GameWindow();
                Window.setWindow(gameWindow);
                }
        });
		instructionGameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
				frame.dispose();
				JFrame frame = new JFrame("What the Tank?!");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                JButton button = new JButton("BACK");
                inputpanel.add(button);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
                button.addActionListener(new ActionListener(){
				    @Override
				    public void actionPerformed(ActionEvent e){
						frame.setContentPane(mainPanel);
						frame.add(buttonPanel);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						// frame.pack();
						frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
						frame.setVisible(true);
						frame.getContentPane().setBackground(Color.cyan);
                    }
                });
            }
        });

        sendGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputStream.println(chatInputField.getText().trim());
                System.out.println(chatInputField.getText().trim());
            }
        });
    }

    public void update(){}
    public void render(Graphics g){}









	// private static Socket clientSocket = null;
	// private static PrintStream outputStream = null;
	// private static DataInputStream inputStream = null;
	// private static BufferedReader inputLine = null;
	// private static boolean closed = false;

	// public void run() {
	// 	// stops listening for response when "Sayonara" is recieved from Server
	// 	String responseLine;
	// 	try {
	// 		while ((responseLine = inputStream.readLine()) != null) {
	// 			System.out.println(responseLine);
    //             // i-print dun sa chatbox
    //             chatArea.setText(chatArea.getText().trim() + "\n" + responseLine);
	// 			if (responseLine == "*** Sayonara ***")
	// 				break;
	// 		}
	// 		closed = true;
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
	// }

    // public static void main(String[] args) {

	// 	try {
	// 		String host = args[0];
	// 		int portNumber = Integer.valueOf(args[1]).intValue();

	// 		clientSocket = new Socket(host, portNumber);
	// 		inputLine = new BufferedReader(new InputStreamReader(System.in));
	// 		outputStream = new PrintStream(clientSocket.getOutputStream());
	// 		inputStream = new DataInputStream(clientSocket.getInputStream());
	// 	} catch (UnknownHostException e) {
	// 		System.err.println("Unknown host");
	// 	} catch(IOException e){
	// 		System.out.println("Cannot find (or disconnected from) Server");
	// 	} catch(ArrayIndexOutOfBoundsException e){
	// 		System.out.println("Usage: java Client <server ip> <port no.>");
	// 	}

	// 	if (clientSocket != null && outputStream != null && inputStream != null) {
	// 		try {
	// 			// creates thread that reads input from the server
	// 			new Thread(new Client()).start();
	// 			while (!closed) {
    //                 //if send button is pressed
    //                 //read from the send box
	// 				// outputStream.println(inputLine.readLine().trim());
	// 			}
	// 			// closing time
	// 			outputStream.close();
	// 			inputStream.close();
	// 			clientSocket.close();
	// 		} catch (IOException e) {
	// 			e.printStackTrace();
	// 		}
	// 	}
    // }

}
