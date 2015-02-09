package fr.ProjetGame2.Game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ProjetGame2.Screen.GameScreen;
import fr.ProjetGame2.Screen.MenuScreen;
import fr.ProjetGame2.Screen.ScoreScreen;
 
public class ProjetGame2 extends Game {
	MenuScreen monMenu;
	GameScreen menuJeux;
	ScoreScreen score;
    @Override
    public void create(){
    	monMenu = new MenuScreen(this);
    	score = new ScoreScreen(this);
    	menuJeux = new GameScreen(this);
    	setScreen(monMenu);
    }
	public AssetManager getAssetManager() {
		
		AssetManager _assetManager = new AssetManager();
		return _assetManager ;
	}
	public SpriteBatch getSpriteBatch() {
		
		SpriteBatch _batch= new SpriteBatch();
		return _batch;
	}
	
	public GameScreen getGameScreen(){
		return menuJeux;
	}
 
}