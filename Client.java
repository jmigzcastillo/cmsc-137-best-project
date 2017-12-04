import java.io.*;
import java.net.*;
import game.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.nio.charset.StandardCharsets;

public class Client implements Runnable {

	private static Socket clientSocket = null;
	private static PrintStream outputStream = null;
	private static DataInputStream inputStream = null;
	private static BufferedReader inputLine = null;
	private static boolean closed = false;

	public static Game game;

	public void run() {
		// stops listening for response when "Sayonara" is recieved from Server
		String responseLine;
		try {
			while ((responseLine = inputStream.readLine()) != null) {
				System.out.println(responseLine);

				// i-print dun sa chatbox
				if (responseLine == "*** Sayonara ***")
					break;
			}
			closed = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		game = new Game("What the tank?!", 800, 600);
		
		try {
			String host = args[0];
			int portNumber = Integer.valueOf(args[1]).intValue();

			clientSocket = new Socket(host, portNumber);
			String name = game.display.name;
			InputStream stream = new ByteArrayInputStream(name.getBytes(StandardCharsets.UTF_8.name()));
			inputLine = new BufferedReader(new InputStreamReader(stream));
			outputStream = new PrintStream(clientSocket.getOutputStream());
			inputStream = new DataInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Unknown host");
		} catch(IOException e){
			System.out.println("Cannot find (or disconnected from) Server");
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Usage: java Client <server ip> <port no.>");
		}

		if (clientSocket != null && outputStream != null && inputStream != null) {
			try {
				// creates thread that reads input from the server
				new Thread(new Client()).start();
				while (!closed) {
					//read from the send box
					outputStream.println(inputLine.readLine().trim());
					String conversation = game.display.chatArea.getText();
					System.out.println("Conversation:" + conversation);
					conversation = conversation.concat(inputLine.readLine().trim());
					game.display.chatArea.setText(conversation);
					// outputStream.println(inputLine.readLine().trim());
					// outputStream.println(<ilagay mo rito yung sasabihin>);
				}
				// closing time
				outputStream.close();
				inputStream.close();
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// gameLobby.sendGameButton.addActionListener(new ActionListener() {
		// 	@Override
		// 	public void actionPerformed(ActionEvent e) {
		// 		outputStream.println(gameLobby.chatInputField.getText().trim());
		// 		System.out.println(gameLobby.chatInputField.getText().trim());
		// 	}
		// });
		// GameLobby gameLobby = new GameLobby(outputStream);
	}

}