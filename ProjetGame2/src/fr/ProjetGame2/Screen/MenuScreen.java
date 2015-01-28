package fr.ProjetGame2.Screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.ProjetGame2.Game.ProjetGame2;

public class MenuScreen implements Screen, InputProcessor {

	private Stage stage;
	private Image fond;
	private Texture fondEcran;
	private Label titre;
	private Skin skin;
	private TextButton boutonQuitter;
	private TextButton boutonJouer;
	private TextButton boutonScore;
	private ProjetGame2 game;

    public MenuScreen(ProjetGame2 game) {
        this.game = game;
    }

	
    @Override
    public void render(float delta) {
        if (boutonJouer.isPressed())
        	game.setScreen(new GameScreen(game));
        if (boutonScore.isPressed())
        	game.setScreen(new ScoreScreen(game));
        stage.act();
        stage.draw();
        
       
    }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		fondEcran = new Texture(Gdx.files.internal("images/jewel.jpg"));
		skin = new Skin(Gdx.files.internal("uiskin/uiskin.json"));
		
		//image de fond d'écran
		fond = new Image(fondEcran);
		stage.addActor(fond);
		
		//Bouton qui lance l'écran de jeu
		titre = new Label("Menu principale du jeu",skin);
		titre.setPosition(Gdx.app.getGraphics().getWidth()/2-titre.getWidth()/2, Gdx.app.getGraphics().getHeight()-50);
		stage.addActor(titre);
		//3eme widget
		boutonJouer = new TextButton("Jouer",skin);
		boutonJouer.setPosition(Gdx.app.getGraphics().getWidth()/2-boutonJouer.getWidth()/2, Gdx.app.getGraphics().getHeight()-100);
		stage.addActor(boutonJouer);
		
		//Bouton qui lance l'écran des scores
		boutonScore = new TextButton("Scores",skin);
		boutonScore.setPosition(Gdx.app.getGraphics().getWidth()/2-boutonScore.getWidth()/2, Gdx.app.getGraphics().getHeight()-150);
		stage.addActor(boutonScore);
		
		//bouton qui quitte le jeu
		boutonQuitter = new TextButton("Quitter",skin);
		boutonQuitter.setPosition(Gdx.app.getGraphics().getWidth()/2-boutonQuitter.getWidth()/2, Gdx.app.getGraphics().getHeight()-200);
		boutonQuitter.addListener(new ClickListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int bouton){
				Gdx.app.exit();
				return false;
			}
		});
		stage.addActor(boutonQuitter);
		 
	}

	@Override
	public void hide() {
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
	public void dispose() {
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

}

