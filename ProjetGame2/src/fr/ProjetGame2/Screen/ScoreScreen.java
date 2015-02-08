package fr.ProjetGame2.Screen;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import fr.ProjetGame2.Game.ProjetGame2;
import fr.ProjetGame2.Elements.Score;

public class ScoreScreen implements Screen, InputProcessor{
	
	private ProjetGame2 game;
	private Stage stage;
	private Skin skin;
	private Label titre;
	private Label score;
	private Label point;
	private TextButton boutonRetour;
    private Texture fondEcran;
    private Image fond;
    private ArrayList<Score>listeScores = new ArrayList<Score>();
    private FileHandle fichier = Gdx.files.internal("scores.txt");
    
    
    public ArrayList<Score> getListeScores() {
		return listeScores;
	}



	public void setListeScores(ArrayList<Score> listeScores) {
		this.listeScores = listeScores;
	}



	public ScoreScreen(ProjetGame2 game) {
        this.game = game;
    }
    
    
    
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void show() {
    	
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    	skin = new Skin(Gdx.files.internal("assets/uiskin.json"));
//	    fichier.writeString("aaaaaaa", false);
		
    	//fond d'écran
    	fondEcran = new Texture(Gdx.files.internal("assets/fond2.jpg"));
    	fond = new Image(fondEcran);
		stage.addActor(fond);
    	
    	//label titre
    	titre = new Label("Menu Jeux",skin);
		titre.setPosition(Gdx.app.getGraphics().getWidth()/2-titre.getWidth()/2, Gdx.app.getGraphics().getHeight()-50);
		stage.addActor(titre);
		
		//différents scores
		score = new Label("",skin);
		score.setPosition(Gdx.app.getGraphics().getWidth()/4-score.getWidth()/2, Gdx.app.getGraphics().getHeight()-100);
		stage.addActor(score);
//		point = new Label("Score 2 : 984595",skin);
//		point.setPosition(Gdx.app.getGraphics().getWidth()/2-point.getWidth()/2, Gdx.app.getGraphics().getHeight()-150);
//		stage.addActor(point);
		
		//bouton retour au menu
		boutonRetour = new TextButton("Menu",skin);
		boutonRetour.setPosition(100, 20);
		boutonRetour.setColor(Color.CYAN);
		stage.addActor(boutonRetour);
		
		
		
	}

	@Override
	public void render(float delta) {
		stage.act();
        stage.draw();
        if(fichier.exists()==true){
        	score.setText(fichier.readString());
        }else{
        	score.setText("Il n'y a aucun score enregistre");
        }
        //action du bouton retour menu
        if (boutonRetour.isPressed())
	       	game.setScreen(new MenuScreen(game));
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
