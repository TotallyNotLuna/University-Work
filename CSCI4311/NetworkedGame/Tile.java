package NetworkedGame;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tile extends JPanel{

    private static final long serialVersionUID = 1L;
    
    private int position;
    private Color pColor;
    private boolean king;
    private boolean pDraw;
    

    public Tile(int position, Color color, Color pColor, Boolean king, Boolean draw) {

        this.position = position;
        this.setBackground(color);

        
        this.pColor = pColor;
        this.king = false;
        this.pDraw = draw;

        addMouseListener(new MouseAdapter(){

            public void mousePressed(MouseEvent me){
                
                Client.sendData("M/"+position);
            } 
        });
    }

    public void setPColor(Color color){

        this.pColor = color;
    }

    public void setPKing(boolean king){

        this.king = king;
    }

    public void setPDraw(boolean draw){

        this.pDraw = draw;
    }

    public int getPosition(){

        return this.position;
    }

    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        if (this.pDraw){

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.BLACK);
            g2.fillOval((int)((getWidth() - (getWidth()/1.2))/2), (int)((getHeight() - (getHeight()/1.2))/2 + 4), (int)(getWidth()/1.2), (int)(getHeight()/1.2));
            g2.setColor(this.pColor);
            g2.fillOval((int)((getWidth() - (getWidth()/1.2))/2), (int)((getHeight() - (getHeight()/1.2))/2 - 4), (int)(getWidth()/1.2), (int)(getHeight()/1.2));

            if(this.king){

                g2.setColor(Color.BLACK);
                g2.fillOval((int)((getWidth() - (getWidth()*0.2))/2), (int)((getHeight() - (getHeight()*0.2))/2 - 4), (int)(getWidth()*0.2), (int)(getHeight()*0.2));
            }
        }
    }
}