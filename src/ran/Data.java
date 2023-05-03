package ran;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Data {	
	public static final String Worm = null;
	public static Image imageHead;
	public static Image imageBody;
	public static Image imageWorm;
	
	public static void loadImage() {
		try {
			imageHead = ImageIO.read(new File("res/head.png"));
			imageBody = ImageIO.read(new File("res/BODY.png"));
	        imageWorm = ImageIO.read(new File("res/MOI.png"));	
 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void update() {}
}


