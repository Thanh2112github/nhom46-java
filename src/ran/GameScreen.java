package ran;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class GameScreen extends JPanel implements Runnable {
	static int [] [] bg = new int [30][30];
	static int padding = 10;
	static int WIDTH = 600;
	static int HEIGHT =600;
	static boolean isPlaying = 	false;
	static boolean enableTextStartGame = true;
	static boolean isGameOver =false;
	
	ConRan ran;
	Thread thread ;
	
	static int CurrentLevel =1;
	static int diem = 0; 
	public GameScreen() {	
		ran  = new ConRan();
		Data.loadImage();
		bg[10][10]=2;	
		thread = new Thread(this);
		thread.start();		
	}
	public void paintBg(Graphics g) {
		g.setColor(Color.BLUE); 
		g.fillRect(0, 0, WIDTH+padding+200, HEIGHT+padding);
		for(int i=0;i<30;i++) {
			for(int j=0;j<30;j++) {
				if(bg[i][j]==2) {
					g.drawImage(Data.imageWorm, i*20, j*20, null);
				}
			}
		}
	}
	public void veKhung(Graphics g) {
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, WIDTH+padding*2-10, HEIGHT+padding*2-10);
	    g.drawRect(1, 1, WIDTH+padding*2-12, HEIGHT+padding*2-12);
	    g.drawRect(2, 2, WIDTH+padding*2-14, HEIGHT+padding*2-14 );
	    
	    g.drawRect(0, 0, WIDTH+padding*2-10+200, HEIGHT+padding*2-10);
	    g.drawRect(1, 1, WIDTH+padding*2-12+200, HEIGHT+padding*2-12);
	    g.drawRect(2, 2, WIDTH+padding*2-14+200, HEIGHT+padding*2-14 );
	}
	public void paint(Graphics g) {
		paintBg(g);
		ran.veRan(g);
		veKhung(g);	
		
		if(!isPlaying) {
			if(enableTextStartGame) {
				g.setColor(Color.WHITE);
				g.setFont(g.getFont().deriveFont(18.0f)); 
				g.drawString("Nhấn phím Enter! ", 60, 200 );
			}	
		}
		if(isGameOver) {
		
				g.setColor(Color.WHITE);
				g.setFont(g.getFont().deriveFont(28.0f)); 
				g.drawString("Game Over! ", 100, 260);	
		}
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(28.0f)); 
		g.drawString("Level :  "+CurrentLevel, 650, 100);
		
		g.setFont(g.getFont().deriveFont(20.0f)); 
		g.drawString("Diem :  "+diem, 650, 150);
	}
	public void run() {
		long t=0;
		long t2=0;
		while(true) {
			if(System.currentTimeMillis()-t2>500) {
				enableTextStartGame=!enableTextStartGame;
				t2= System.currentTimeMillis( );
			}
			if(isPlaying) {
				if(System.currentTimeMillis()-t>200) {
					Data.update();
					t=System.currentTimeMillis();
			}
				ran.update();
			}
			repaint();
			try {
				thread.sleep(20);
			} catch (InterruptedException e ) {}
		}
	}
}