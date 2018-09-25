
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;

public class SaveTheBall extends Applet implements Runnable,KeyListener{
   
    int score=0;
    private Thread t;
    int x=(int) (Math.random() * ( 500 - 0 )),y=0;
    int height=500,width=500;
    String xoperation="add",yoperation="add";
    int Max=9,Min=1;
    int randx=10,randy=10;
    int sleepval=100;
    int rectx=1;
    String status="Not Over";
    Image bgImage=null;
    public void init()            // link the KeyListener with Applet
    {
      addKeyListener(this);
      //bg=getImage(getCodeBase(),"bg.jpg");
      bgImage = getImage(getDocumentBase(), "../../game/src/game/bbgg.jpg");
    } 
    public void start()
    {
        t = new Thread(this,"Animation");
        t.start();
    }
    
    
    public void run()
    {
        for(;;)
        {
            try{
                Thread.sleep(sleepval);
            }catch(InterruptedException ex){}
            if(x>=490) {
            	xoperation="minus";
            	randx=(int) (Math.random() * ( Max - Min ));
            	sleepval=(randx+randy)*3;
            		   
            }
            if(y>380) {
            	if(x>=rectx-6 && x<=(rectx+70)) {
            		yoperation="minus";
                	randy=(int) (Math.random() * ( Max - Min ));
                	sleepval=(randx+randy)*3;
                	score+=1;
            	}
            	else {
            	System.out.println("Game over");
            	status="Over";
            	repaint();
            	t.stop();
            	}
            }
            if(x<=0) {
            	xoperation="add";
            	randx=(int) (Math.random() * ( Max - Min ));
            	sleepval=(randx+randy)*3;
            }
            if(y<=0) {
            	yoperation="add";
            	randy=(int) (Math.random() * ( Max - Min ));
            	sleepval=(randx+randy)*3;
            }
            if(xoperation.equals("minus"))
            {
            	x-=randx;
            }
            else {
            	x+=randx;
            }
            if(yoperation.equals("minus"))
            {
            	y-=randy;
            }
            else {
            	y+=randy;
            }
            System.out.println(x+","+y);
            
            repaint();
        }
    }
    
    public void stop()
    {
        t.stop();
    }
    @Override
	public void keyPressed(KeyEvent arg0) {
    	System.out.println("key pressed");
		// TODO Auto-generated method stub
		if(arg0.getKeyChar()=='k'){
			if(rectx<=0) {
				rectx=0;
			}
			else {
			rectx-=10;
			}
			repaint();
		}
		else if(arg0.getKeyChar()=='l') {
			if(rectx>=430) {
				rectx=430;
			}
			else {
			rectx+=10;
			}
			repaint();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
    public void paint(Graphics g)
    {
        setBackground(Color.CYAN);
    	g.drawImage(bgImage,0,0,this); 
        Font currentFont = new Font("Impact", Font.BOLD, 22);
        g.setFont(currentFont);
        g.drawString("Score:", 380, 30);
        g.drawString(Integer.toString(score), 450, 30);
        g.fillOval(x,y,20,20);
        this.setSize(height, width);
        Rectangle r = new Rectangle(rectx, 400, 70, 10);
        g.fillRect(
           (int)r.getX(),
           (int)r.getY(),
           (int)r.getWidth(),
           (int)r.getHeight()
        );
        if(status.equals("Over")) {
        	g.drawString("GAME OVER", 200, 250);
        }
    }
	
}
