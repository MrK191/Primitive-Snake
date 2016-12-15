
package wormgame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.game.WormGame;


public class DrawingBoard extends JPanel implements Updatable {
    private WormGame game;
    private int pixelSize;

    public DrawingBoard(WormGame game,int pieceLength ) {
        this.game=game;
        pixelSize=pieceLength;
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        Worm worm = game.getWorm();
        List<Piece> pieces = worm.getPieces();
        
        for(Piece i:pieces){
            g.setColor(Color.BLACK);
            g.fill3DRect(i.getX()*pixelSize, i.getY()*pixelSize, pixelSize, pixelSize, false);
        }
        
        
        Apple apple = game.getApple();
        g.setColor(Color.red);
        g.fillOval(apple.getX()*pixelSize, apple.getY()*pixelSize, pixelSize, pixelSize);
        
        
        
    }

    @Override
    public void update() {
        repaint();
    }
    
    
}
