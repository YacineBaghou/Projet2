package fr.ProjetGame2.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
	  //Ceci est un test
	  //Ceci est un test
	  //Ceci est un test
	  //Ceci est un test
	  //Ceci est un test
		
	    
	    private Joueur joueur1;
	    private Joueur joueur2;
	    Plateau monPlateau;
	    private ProjetGame2 game;
	    
	    
	    
	 
	    public GameScreen(ProjetGame2 game) {
	        this.game = game;
	    }
	    
	    
	    @Override
	    public void show() {


			joueur1 = new Joueur("Alex");
			joueur2 = new Joueur("Paulo");
			
			Plateau monPlateau = new Plateau(joueur1, joueur2, 10, 10);
			
			System.out.println("Creation d'une partie");
	    	
	    	
	    	
	        world = new World();
	        renderer = new WorldRenderer(world, false, monPlateau);
	        Gdx.input.setInputProcessor(this);
	        stage = new Stage();
	    	Gdx.input.setInputProcessor(stage);;
	    	skin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));
	    	
	    	//Ajout Du fond d'écran
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
			
			//Ajout du bouton pour quitter l'écran
			boutonRetour = new TextButton("Menu",skin);
			boutonRetour.setPosition(100, 20);
			stage.addActor(boutonRetour);
			 

	    }
	 
	    @Override
	    public void render(float delta) {
	    	
	    	/*/A voir?
	    	if(boutonRetour.isPressed())
	    		game.setScreen(new MenuScreen(game));
	    		*/
	    	if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)||Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
	    		Gdx.input.setInputProcessor(this);
	    	}
	 
	    		
	    	Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        nbpoint++;
	        point.setText(String.valueOf(nbpoint));
	        
	        stage.act();
	        stage.draw();
	        
	        renderer.render();
	    	
		 if (boutonRetour.isPressed())
			 game.setScreen(new MenuScreen(game));
	     
	        
	    }
	 
	    @Override
	    public void resize(int width, int height) {
	        renderer.setSize(width*3/5, height*3/5); //modif
	    }
	 
	    @Override
	    public void hide() {
	        Gdx.input.setInputProcessor(null);
	    }
	 
	    @Override
	    public void pause() {
	        // TODO Auto-generated method stub
	    }
	 
	    @Override
	    public void resume() {
	        // TODO Auto-generated method stub
	    }
	 
	    @Override
	    public void dispose() {
	        Gdx.input.setInputProcessor(null);
	    }

	 
	    // * Méthode InputProcessor ***************************//
	 
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
	    	
	    	if (button == Buttons.LEFT){
	    		//System.out.println("x"+ screenX +"y"+screenY);
	    		Block unBlock = trouverClick(screenX, screenY);
	    		System.out.println(unBlock.getPosition().x + "" + unBlock.getPosition().y );
	    	}
	    	if (button == Buttons.RIGHT){
	    		System.out.println("x"+ screenX +"y"+screenY);	
	    	}
	    	return false;
	    	
	    }
	 
	    public boolean touchUp(int x, int y, int pointer, int button) {
	        
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
	    	int Xref = 0,Yref = 0,coordX, coordY;   	
	    	
	    	for(Block block: world.getBlocks()){
	    		if(block.getPosition().x == 0 && block.getPosition().y == 9){
	    			Xref = Math.round(block.getPosition().x);
	    			Yref = Math.round(block.getPosition().y);
	    		}
	    	}
	    	
	    	//System.out.println(world.getBlocks().get(0).getPosition().x + ""+ world.getBlocks().get(0).getPosition().y);
	    	coordX = (x - Xref) /20;
	    	coordY = (y - Yref) /20;
	    	
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