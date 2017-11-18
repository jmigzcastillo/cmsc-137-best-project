
import javax.swing.JFrame;
import java.awt.Graphics;
public class Window{
	protected JFrame frame;
	private int width;
	private int height;
	private String title;
	public static Window currentWindow;
	public Window(int width, int height, String title){
		this.frame = new JFrame(title);
		this.width = width;
		this.height = height;
		this.title = title;
	}



	public int getWidth(){
		return this.width;
	}

	public int getHeight(){
		return this.height;
	}

	public String getTitle(){
		return this.title;
	}

	public Window getWindow(){
		return currentWindow;
	}

	public void toggleWindowVisibility(){
		this.frame.setVisible(!this.frame.isVisible());
	}

	public static void setWindow(Window w){
		currentWindow = w;
	}

	public abstract void render();
	public abstract void update();
}