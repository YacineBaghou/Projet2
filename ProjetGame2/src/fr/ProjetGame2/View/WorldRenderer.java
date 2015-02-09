package fr.ProjetGame2.View;

import java.util.ArrayList;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import fr.ProjetGame2.Elements.Block;
import fr.ProjetGame2.Elements.Plateau;
import fr.ProjetGame2.Elements.World;
 
public class WorldRenderer {
	 
    private static final float CAMERA_WIDTH = 10f;
    private static final float CAMERA_HEIGHT = 7f;
 
    private World world;
    private OrthographicCamera cam;
 
    /** 
     * ShapeRenderer permet de dessiner facilement les
     * formes de base
     * Sera utilisé pour des fins de débogage
     * **/
    ShapeRenderer debugRenderer = new ShapeRenderer();
 
    /** Textures **/
    private Texture blanc;
    private Texture bleu;
    private Texture rouge;
    private Texture orange;
    private Texture violet;
    private Texture vert;
    private Texture alea;
 
    private SpriteBatch spriteBatch;
    private boolean debug = false;
    private int width;
    private int height;
    private float ppuX; // pixels par unité pour X
    private float ppuY;
    
    ArrayList<Texture> gemmes;
    
    
    private Plateau monPlateau;
    
 
    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }
 
    public WorldRenderer(World world, boolean debug, Plateau monPlateau) {
        this.monPlateau = monPlateau;
    	
    	this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        this.cam.update();
        this.debug = debug;
        spriteBatch = new SpriteBatch();
      
        
        
   	  	gemmes = new ArrayList<Texture>();
    	blanc = new Texture(Gdx.files.internal("assets/gemWhite.png"));
    	gemmes.add(blanc);
    	vert = new Texture(Gdx.files.internal("assets/gemGreen.png"));
    	gemmes.add(vert);
    	orange = new Texture(Gdx.files.internal("assets/gemOrange.png"));
    	gemmes.add(orange);
    	rouge = new Texture(Gdx.files.internal("assets/gemRed.png"));
    	gemmes.add(rouge);
    	violet = new Texture(Gdx.files.internal("assets/gemPurple.png"));
    	gemmes.add(violet);
    	bleu = new Texture(Gdx.files.internal("assets/gemBlue.png"));
    	gemmes.add(bleu);
        
        
        
    }
 
    public void render() {
        spriteBatch.begin();
            drawBlocks();
        spriteBatch.end();
        if (debug)
            drawDebug();
    }
 
    private void drawBlocks() {
    	boolean testDessinFinis = true;
    	
    	for(Block block : world.getBlocks()){
    		//System.out.println("X:" + block.getPosition().x + "  Y:" + block.getPosition().y);
    		int couleurTemp = monPlateau.pickCristal( 9 - Math.round(block.getPosition().y),Math.round(block.getPosition().x)).getCouleur();
    		alea = gemmes.get(couleurTemp);
    		   spriteBatch.draw(
    	                alea, 
    	                block.getPosition().x * ppuX + 200, 
    	                block.getPosition().y * ppuY + 40+block.getDecalage(), 
    	                Block.getSize() * ppuX, 
    	                Block.getSize() * ppuY);
    		   block.setDecalage();
    		   if(block.getDecalage()>0)
    			   testDessinFinis = false;
    	}
    	
    	if (testDessinFinis){
    		monPlateau.setCoeffCombo();
    		monPlateau.trouverCombo();
    	}
    }
 
  

 
    private void drawDebug() {
        // Démarrage du renderer
        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeType.Filled);
 
        // render blocks
        for (Block block : world.getBlocks()) {
            Rectangle rect = block.getBounds();
            float x1 = block.getPosition().x + rect.x;
            float y1 = block.getPosition().y + rect.y;
            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
        }
     
    }
}
