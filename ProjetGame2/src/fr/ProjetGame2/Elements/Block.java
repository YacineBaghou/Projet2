package fr.ProjetGame2.Elements;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
 
public class Block {
 
    static final float SIZE = 1f;
 
    private int decalage = 0;
    
    Vector2     position = new Vector2();
    Rectangle   bounds = new Rectangle();
 
    public Block(Vector2 pos) { //Vector2 class Java ayant pour paramétres x et y
        this.position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
        this.bounds.x = this.position.x;
        this.bounds.y = this.position.y;
        this.decalage = 0;
    }

    public Block(Vector2 pos, int decalage) { //Vector2 class Java ayant pour paramétres x et y
        this.position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
        this.bounds.x = this.position.x;
        this.bounds.y = this.position.y;
        this.decalage = decalage;
    }
    
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public static float getSize() {
		return SIZE;
	}
	
	public int getDecalage(){
		return decalage;
	}
	
	public void setDecalage(){
		if(decalage>0)
			decalage = decalage-3;
	}
	
	public void resetDecalage(){
		decalage = 36;
	}
	
	public void resetDecalage(int k){
		decalage = k*36;
	}
	
	public void setDecalage(int decalage){
		this.decalage = decalage;
	}
}
