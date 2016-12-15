package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm ;
    private Apple apple;

    public WormGame(int width, int height) {
        super(1000, null);
        
        this.width = width;
        this.height = height;
        this.continues = true;
        worm = new Worm(width/2, height/2, Direction.DOWN);
        
        Random random = new Random();
        apple = new Apple(ThreadLocalRandom.current().nextInt(0, width),ThreadLocalRandom.current().nextInt(0, width));
        
        if(apple.getX()==width/2&apple.getY()==height/2){
            apple = new Apple(ThreadLocalRandom.current().nextInt(4, width),ThreadLocalRandom.current().nextInt(0, width));
        }
        
        addActionListener(this);
        setInitialDelay(2000);

    }
    
    public Worm getWorm(){
        return worm;
    }
    
    public void setWorm(Worm worm){
        this.worm=worm;
    }
    
    public Apple getApple(){
        return apple;
    }
    
    public void setApple(Apple apple){
        this.apple=apple;
    }


    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
         if (!continues) {
            return;
        }
        
        
        List<Piece> pieces = worm.getPieces();
        worm.move();
        
        if(worm.runsInto(apple)){
           worm.grow();
           this.appleCreate();
        }
        
        if(worm.runsIntoItself()){
           
            continues = false;
        }
        
        for(Piece i:pieces){
            
            if(i.getX()>=width||i.getY()>=height||i.getX()==-1||i.getY()==-1){
                continues = false;
            }
        }
        updatable.update();
        this.setDelay(1000 / worm.getLength());
        
    }
    
    private void appleCreate(){
        this.apple = new Apple(ThreadLocalRandom.current().nextInt(0, width),
                ThreadLocalRandom.current().nextInt(0, width));
    }

}
