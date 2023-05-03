package ran;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class FrameScreen extends JFrame{	
	GameScreen gameScreen;	
	public FrameScreen() {
		setSize(850,700);
		setTitle(" Ran San Moi ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameScreen = new GameScreen();	
		add(gameScreen);	
		this.addKeyListener(new handler());	
		setVisible(true);
	}
	public static void main (String as[]) {
		new FrameScreen();
	}
	private class handler implements KeyListener{
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {		
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			    GameScreen.isPlaying=!GameScreen.isPlaying;
			   if(GameScreen.isGameOver) 
				   GameScreen.isGameOver=false; 	
			}
			if(e.getKeyCode()== KeyEvent.VK_UP) {
				gameScreen.ran.setVector(ConRan.GO_UP);
			}
			if(e.getKeyCode()== KeyEvent.VK_DOWN) {
				gameScreen.ran.setVector(ConRan.GO_DOWN);	
			}
			if(e.getKeyCode()== KeyEvent.VK_LEFT) {
				gameScreen.ran.setVector(ConRan.GO_LEFT);
			}
			if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
				gameScreen.ran.setVector(ConRan.GO_RIGHT);
			}
		}	
		public void keyReleased(KeyEvent e) {}	
	}		
}