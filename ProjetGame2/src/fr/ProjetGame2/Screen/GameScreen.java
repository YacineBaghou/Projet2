package fr.ProjetGame2.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import fr.ProjetGame2.Elements.Block;
import fr.ProjetGame2.Elements.Joueur;
import fr.ProjetGame2.Elements.Plateau;
import fr.ProjetGame2.Elements.World;
import fr.ProjetGame2.Game.ProjetGame2;
import fr.ProjetGame2.View.WorldRenderer;
	 
	public class GameScreen implements Screen, InputProcessor {
		 
	    private World           world;
	    private WorldRenderer   renderer;
	    private Stage stage;
		private Skin skin;
		private Label titre;
		private Label score;
		private Label point;
		private TextButton boutonRetour;
	    private Texture fondEcran;
	    private Image fond;
	    private int nbpoint =0;
	 
	
	    
	    private Joueur joueur1;
	    private Joueur joueur2;
	    Plateau monPlateau;
	    private ProjetGame2 game;
	    
	    private Block blockDejaJoue = null;
	    
	    
	    
	 
	    public GameScreen(ProjetGame2 game) {
	        this.game = game;
	        
	    }
	    
	    
	    @Override
	    public void show() {

	    	//Ajout de nouveau joueur et instanciation du plateau
			joueur1 = new Joueur("Alex");
			joueur2 = new Joueur("Paulo");
			monPlateau = new Plateau(joueur1, joueur2, 10, 10);
			System.out.println("Creation d'une partie");
	    	
	        world = new World();
	        renderer = new WorldRenderer(world, false, monPlateau);
	        Gdx.input.setInputProcessor(this);
	        stage = new Stage();
	    	Gdx.input.setInputProcessor(stage);
	    	skin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));
	    	
	    	//Ajout Du fond d'�cran
	    	fondEcran = new Texture(Gdx.files.internal("images/fond.jpg"));
	    	fond = new Image(fondEcran);
			stage.addActor(fond);
			
			//ajout du label titre
	    	titre = new Label("Menu Jeux",skin);
			titre.setPosition(Gdx.app.getGraphics().getWidth()/4-titre.getWidth()/2, Gdx.app.getGraphics().getHeight()-50);
			stage.addActor(titre);
			
			//Affichage du score
			score = new Label("Score:",skin);
			score.setPosition(Gdx.app.getGraphics().getWidth()/4-score.getWidth()/2, Gdx.app.getGraphics().getHeight()-100);
			stage.addActor(score);
			point = new Label("0",skin);
			point.setPosition(Gdx.app.getGraphics().getWidth()/4-point.getWidth()/2, Gdx.app.getGraphics().getHeight()-150);
			stage.addActor(point);
			
			//Ajout du bouton pour quitter l'�cran
			boutonRetour = new TextButton("Menu",skin);
			boutonRetour.setPosition(100, 20);
			boutonRetour.setColor(Color.CYAN);
			stage.addActor(boutonRetour);
			 

	    }
	 
	    @Override
	    public void render(float delta) {
	    	

	    	
//	    	if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)||Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
	    	Gdx.input.setInputProcessor(this);
//	    	}
	 
	    	//incrementation du label score(test)

	        nbpoint++;
	        point.setText(String.valueOf(nbpoint));
	        //Action du bouton retour au menu
	       
	        
	        stage.act();
	        stage.draw();
	        renderer.render();
	    	
	        /*if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
		    	if(RetourMenu(Gdx.input.getX(), Gdx.input.getY()) == true){
		    		game.setScreen(new MenuScreen(game));
		    		System.out.println("branlette"+ boutonRetour.getX() + boutonRetour.getY());
		    	}
	        }*/
	     
	        
	    }
	 
	    @Override
	    public void resize(int width, int height) {
	    	//definition de la taille de la grille
	        renderer.setSize(width*3/5, height*3/5); 
	    }
	 
	    @Override
	    public void hide() {
	        Gdx.input.setInputProcessor(null);
	    }
	 
	    @Override
	    public void pause() {
	        
	    }
	 
	    @Override
	    public void resume() {
	        // TODO Auto-generated method stub
	    }
	 
	    @Override
	    public void dispose() {
	        Gdx.input.setInputProcessor(null);
	    }

	 
	    // * M�thode InputProcessor ***************************//
	 
	    @Override
	    public boolean keyDown(int keycode) {
	        
	        return false;
	    }
	 
	    @Override
	    public boolean keyUp(int keycode) {
	       
	        return false;
	    }
	 
	    @Override
	    public boolean keyTyped(char character) {
	        // TODO Auto-generated method stub
	        return false;
	    }
	 
	    @Override
	    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	    	//action sur les clicks
	    	if (button == Buttons.LEFT){
	    		
	    		if((screenX>=boutonRetour.getX() && screenX<=boutonRetour.getX()+boutonRetour.getWidth()) && 
		    			(screenY>= stage.getHeight() - boutonRetour.getY()-35 && screenY<= stage.getHeight() - boutonRetour.getY()-35+boutonRetour.getHeight())){
	    				game.setScreen(new MenuScreen(game));
	    				System.out.println("branlette"+ boutonRetour.getX() + boutonRetour.getY());
	    				return false;
	    		}
	    		
	    		//System.out.println("x"+ screenX +"y"+screenY);
	    		Block unBlock = trouverClick(screenX, screenY);
	    		//System.out.println(unBlock.getPosition().x + "" + unBlock.getPosition().y );
	    		
	    		if(unBlock.getPosition().x<10 && unBlock.getPosition().x >= 0 && unBlock.getPosition().y<10 && unBlock.getPosition().y >= 0 && blockDejaJoue != null){
	    			monPlateau.jouer(joueur1, 9-(int)unBlock.getPosition().y, (int)unBlock.getPosition().x, 9 - (int)blockDejaJoue.getPosition().y, (int)blockDejaJoue.getPosition().x);
	    			
	    			System.out.println("Click�  X:"+ unBlock.getPosition().x +"  Y:"+unBlock.getPosition().y );
	    			blockDejaJoue = null;
	    		}else{
	    			blockDejaJoue = unBlock;
	    			System.out.println("Click�  X:"+ blockDejaJoue.getPosition().x +"  Y:"+blockDejaJoue.getPosition().y );
	    		}
    			
	    	}
	    	if (button == Buttons.RIGHT){
	    		System.out.println("x"+ screenX +"y"+screenY);	
	    	}
	    	
	    	RetourMenu(screenX,screenY);
	    	return false;
	    	
	    }
	 
	    public boolean touchUp(int x, int y, int pointer, int button) {
	        
	        return false;
	    }
	    public boolean RetourMenu(int screenX, int screenY){
	    	if((screenX>=boutonRetour.getX() && screenX<=boutonRetour.getX()+boutonRetour.getWidth()) && 
	    			(screenY>= stage.getHeight() - boutonRetour.getY()-35 && screenY<= stage.getHeight() - boutonRetour.getY()-35+boutonRetour.getHeight())){
	    		return true;
	    	}
	    	return false;
	    }
	    @Override
	    public boolean touchDragged(int x, int y, int pointer) {
	        // TODO Auto-generated method stub
	        return false;
	    }
	 
	    @Override
	    public boolean mouseMoved(int x, int y) {
	        // TODO Auto-generated method stub
	        return false;
	    }
	 
	    @Override
	    public boolean scrolled(int amount) {
	        // TODO Auto-generated method stub
	        return false;
	    }
	    
	    
	    private Block trouverClick(int x, int y){
	    	int coordX, coordY;   	
	    	
	    	
	    	coordX = (int)((x-200)/36);
	    	coordY = (int)(((int)stage.getHeight() - y - 40)/34.285);
	    	
	    	Block retour = new Block(new Vector2(coordX,coordY));
	    	
	    	return retour;
	    }
	    
	    
	    
	    private Block getBlock(int x, int y){
	    	for(Block block:world.getBlocks()){
	    		if(block.getPosition().x == x && block.getPosition().y == y){
	    			return block;
	    		}
	    	}
	    	
	    	return null;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}