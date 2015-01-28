package fr.ProjetGame2.Elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
 
public class World {
 
    /** Les blocs qui composent le monde **/
    Array<Block> blocks = new Array<Block>();
 
    // Getters -----------
    public Array<Block> getBlocks() {
        return blocks;
    }
    
    // --------------------
 
    public World() {
        createDemoWorld();
    }
 
    private void createDemoWorld() {
 
        for (int col = 0; col < 10; col++) {
        	for (int lig = 0; lig < 10; lig++) {
        	blocks.add(new Block(new Vector2(col, lig)));              
        	}
            
        }
 
    }
	public void setBlocks(Array<Block> blocks) {
		this.blocks = blocks;
	}
}