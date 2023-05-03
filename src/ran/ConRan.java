package ran;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class ConRan {
	int doDai = 3;
	int x[];
	int y[];
	
	public static int GO_UP = 1;
	public static int GO_DOWN = -1;
	public static int GO_LEFT = 2; 
	public static int GO_RIGHT = -2;
	
	int vector = ConRan.GO_DOWN;
	long t1 = 0;
	long t2 = 0;	
	int speed =  100;
	int maxLen = 12;
	int  currentImage =0;	
	boolean udAfterChangeVt = true;
	public ConRan() {
		x = new int[300];
		y = new int[300];
		
		x[0]=5;
		y[0]=4;
		
		x[1]=5;
     	y[1]=3;
     	
     	x[2]=5;
     	y[2]=2;		
	}
	public void resertGame() {
		x = new int[300];
		y = new int[300];
		
		x[0]=9;
		y[0]=8;
		
		x[1]=9;
     	y[1]=7;
     	
     	x[2]=9;
     	y[2]=6;
     	
     	doDai = 3;
     	vector = ConRan.GO_DOWN; 	
	}
	public void setVector(int v) {
			if(vector != -v ) {
				vector = v;
			}
		}
	public boolean toaDoCoNamTrongThanRan(int x1 , int y1) {
		for(int i=0;i<doDai;i++)
			if(x[i]==x1&&y[i]==y1) return true;
		return false;
	}
	public Point layToaDoMoi() {
		Random r = new Random();
		int x;
		int y;
		do {
		 x = r.nextInt(19);
		 y = r.nextInt(19);
		}while(toaDoCoNamTrongThanRan(x,y));
		
		return new Point(x,y);
	}
	public int getCurrentSpeed() {
		 int speed = 100;
		for(int i = 0 ; i<GameScreen.CurrentLevel;i++) 
			speed*=0.8;
		return speed;  	
	}
	public void update() {	
		if(doDai == maxLen) {
			GameScreen.isPlaying=false;
			resertGame();
			GameScreen.CurrentLevel++;
			maxLen += 10; 
			speed = getCurrentSpeed();
		}
		for(int i=1;i<doDai;i++) {
			if(x[0]==x[i]&&y[0]==y[i]) {
				GameScreen.isPlaying=false;
				GameScreen.isGameOver=true;				
				resertGame();
				GameScreen.diem=0;
				GameScreen.CurrentLevel=1;
			}			
		}
		if(System.currentTimeMillis()-t1>speed) {		
			currentImage++;
			if(currentImage>=2) currentImage=0;			
			if(GameScreen.bg[x[0]][y[0]]==2) {
				doDai++;
				GameScreen.bg[x[0]][y[0]]=0;
				GameScreen.bg[layToaDoMoi().x][layToaDoMoi().y]=2;
				GameScreen.diem+=5;
			}
			for(int i=doDai-1;i>0;i--) {
				x[i]=x[i-1];
				y[i]=y[i-1];
			}		
			if(vector == ConRan.GO_UP) y[0]--;
			if(vector == ConRan.GO_DOWN) y[0]++;
			if(vector == ConRan.GO_LEFT) x[0]--;
			if(vector == ConRan.GO_RIGHT) x[0]++;
			
			if(x[0]<0) x[0]=29;
			if(x[0]>29) x[0]=0;
			if(y[0]<0) y[0]=29;
			if(y[0]>29) y[0]=0;
				
		t1 = System.currentTimeMillis();	
		}
	}
	public void veRan(Graphics g) {
		for(int i=0;i<doDai;i++)
			g.drawImage(Data.imageBody, x[i]*20, y[i]*20, null);
		g.drawImage(Data.imageHead, x[0]*20, y[0]*20, null);		
	}
}

