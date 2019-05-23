import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorSnake {
public static void main(String[] args) {
	int x=0;
	int y=0;
	int xSpeed=50;
	int ySpeed=40;
	Random r = new Random();
for (int i = 1; i > 0;) {
	x+=xSpeed;
	y+=ySpeed;
	int randX = r.nextInt(700);
	int randY = r.nextInt(500);
    final JOptionPane pane = new JOptionPane("ERROR WITH DIRECTORIES: user/input-manager/computer/ \n HOST_AUTHORIZATION_REQUIRED", JOptionPane.ERROR_MESSAGE);
    final JDialog d = pane.createDialog((JFrame)null, "CRITICAL_ERROR");
    final JDialog e = pane.createDialog((JFrame)null, "HACKING DETECTED");
    d.setModal(false);
    e.setModal(false);
    e.setLocation(randX, randY);
    e.setVisible(true);
    d.setLocation(x,y);
    d.setVisible(true);
    new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            d.dispose();
            e.dispose();
        }

    }).start();
    if(y>500) {
    	ySpeed=-ySpeed;
    }else if(y==0) {
    	ySpeed=-ySpeed;
    }else if(x==0) {
    	xSpeed=-xSpeed;
    }else if(x>=700) {
    	xSpeed=-xSpeed;
    }
}
}
}

