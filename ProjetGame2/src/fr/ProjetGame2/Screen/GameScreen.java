package fr.ProjetGame2.Screen;




import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.Format;
import java.util.ArrayList;
import java.util.Scanner;

//import javax.swing.JFrame;
















import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.sun.glass.ui.Window;

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
		private Skin skin2;
		private Label titre;
		private Label temps;
		private Label point;
		private TextButton boutonRetour;
	    private Texture fondEcran;
	    private Texture fondEcran2;
	    private Image fond;
	    private Image fond2;
	    private int score = 0;
	    private int sec = 0;
    	private int min = 3;
    	private Timer timer;
    	
    	private TextButton boutonRejouer;
    	static BufferedWriter out = null;
    	private String chaineMeilleurScore;
	
	    
	    private Joueur joueur1;
	    private Joueur joueur2;
	    private Plateau monPlateau;
	    private ProjetGame2 game;
	    
	    private Block blockDejaJoue = null;
	    
	    
	    private boolean flagFindePartie = false;
	    
	    private TextField monChamp;
	    
	 
	    public GameScreen(ProjetGame2 game) {
	        this.game = game;
			flagFindePartie = false;
	    }
	    
	    
	    @Override
	    public void show() {

	    	//Ajout de nouveau joueur et instanciation du plateau
			joueur1 = new Joueur("Alex");
			joueur2 = new Joueur("Paulo");
			monPlateau = new Plateau(joueur1, joueur2, 10, 10, this);
			System.out.println("Creation d'une partie");
	    	
	        world = new World();
	        renderer = new WorldRenderer(world, false, monPlateau);
	        Gdx.input.setInputProcessor(this);
	        stage = new Stage();
	    	skin = new Skin(Gdx.files.internal("assets/uiskin.json"));
	        //skin2 = new Skin( Gdx.files.internal( "assets/ui/defaultskin.json" ));
	    	
	    	//Ajout Du fond d'écran
	    	fondEcran = new Texture(Gdx.files.internal("assets/fond.jpg"));
	    	fondEcran2 = new Texture(Gdx.files.internal("assets/fond2.jpg"));
	    	fond2 = new Image(fondEcran2);
	    	fond = new Image(fondEcran);
			stage.addActor(fond);
			stage.addActor(fond2);
			
			//ajout du label titre
	    	titre = new Label("Menu Jeux",skin);
			titre.setPosition(50, Gdx.app.getGraphics().getHeight()-50);
			stage.addActor(titre);
			
			//Affichage du score
			point = new Label("0",skin);
			point.setPosition(50, Gdx.app.getGraphics().getHeight()-150);
			stage.addActor(point);
			
			//Affichage du temps
			temps = new Label("Temps:",skin);
			temps.setPosition(50, Gdx.app.getGraphics().getHeight()-200);
			stage.addActor(temps);
			
			//Ajout du bouton pour quitter l'écran
			boutonRetour = new TextButton("Menu",skin);
			boutonRetour.setPosition(100, 20);
			boutonRetour.setColor(Color.CYAN);
			stage.addActor(boutonRetour);
			
			//ajout bouton Rejouer
			boutonRejouer = new TextButton("Rejouer",skin);
			boutonRejouer.setPosition(Gdx.app.getGraphics().getWidth()/2 - boutonRejouer.getWidth()/2 , Gdx.app.getGraphics().getHeight()-250);
			boutonRejouer.setColor(Color.CYAN);
			boutonRejouer.setVisible(false);
			stage.addActor(boutonRejouer);
			
			//permet de réinitialiser l'écran tout les 5 sec

			 monChamp = new TextField("", skin);
			monChamp.setPosition(250, Gdx.app.getGraphics().getHeight()-200);
			
			
			//ajout Chrono
			timer = createTimer();
			timer.start();

	    }
	 
	    
	    public void render(float delta) {
	    	stage.act();
	        stage.draw();
	        fond2.setVisible(false);
	    	Gdx.input.setInputProcessor(this);

	    	
			if(!flagFindePartie){
					point.setText(String.valueOf("Score: " +score));
					temps.setText(String.valueOf(min + ":" + sec));			}else{
			//affichage de fin d'écran et enregistrement du score
			titre.setText("Fin de partie");
			point.setText("Vous avez effectuer un score de "+ score);
						titre.setPosition(Gdx.app.getGraphics().getWidth()/2- titre.getWidth()/2, Gdx.app.getGraphics().getHeight()-50);
						point.setPosition(Gdx.app.getGraphics().getWidth()/2- titre.getWidth(), Gdx.app.getGraphics().getHeight()-150);
						temps.setVisible(false);
						Gdx.input.setInputProcessor(stage);
						boutonRejouer.setVisible(true);
						if (boutonRejouer.isPressed()){
		    	    		mettreAJourMeilleursScores(lireLesMeilleursScore());
				        	game.setScreen(new GameScreen(game));
						}
				        if (boutonRetour.isPressed()){
		    	    		mettreAJourMeilleursScores(lireLesMeilleursScore());
					       	game.setScreen(new MenuScreen(game));
				        }
					    fond.setVisible(false);
						fond2.setVisible(true);
						boutonRetour.setPosition(Gdx.app.getGraphics().getWidth()/2 - boutonRejouer.getWidth()/2 , Gdx.app.getGraphics().getHeight()-300);
						
					}
				renderer.render();
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
	    	//action sur les clicks
	    	if (button == Buttons.LEFT){
	    		
	    		if((screenX>=boutonRetour.getX() && screenX<=boutonRetour.getX()+boutonRetour.getWidth()) && 
		    			(screenY>= stage.getHeight() - boutonRetour.getY()-35 && screenY<= stage.getHeight() - boutonRetour.getY()-35+boutonRetour.getHeight())){
    	    		game.setScreen(new MenuScreen(game));
    	    		return false;
	    		}
	    		
	    		//System.out.println("x"+ screenX +"y"+screenY);
	    		Block unBlock = trouverClick(screenX, screenY);
	    		//System.out.println(unBlock.getPosition().x + "" + unBlock.getPosition().y );
	    		
	    		if(unBlock.getPosition().x<10 && unBlock.getPosition().x >= 0 && unBlock.getPosition().y<10 && unBlock.getPosition().y >= 0 && blockDejaJoue != null){
	    			monPlateau.jouer(joueur1, 9-(int)unBlock.getPosition().y, (int)unBlock.getPosition().x, 9 - (int)blockDejaJoue.getPosition().y, (int)blockDejaJoue.getPosition().x);
	    			
	    			System.out.println("Clické  X:"+ unBlock.getPosition().x +"  Y:"+unBlock.getPosition().y );
	    			
	    			blockDejaJoue = null;
	    			
	    		}else{
	    			blockDejaJoue = unBlock;
	    			System.out.println("Clické  X:"+ blockDejaJoue.getPosition().x +"  Y:"+blockDejaJoue.getPosition().y );
	    			
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
	 
	    public void Score(int point){
	    	score=score + point;
	    	
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
	    
	    
	    public World getWorld(){
	    	return world;
	    }
	    
	    
	    
	    public int getScore(){
	    	return score;
	    }
	

		
		public Timer createTimer ()
		{
		  // Création d'une instance de listener 
		  // associée au timer
		  ActionListener action = new ActionListener ()
		    {
		      // Méthode appelée à chaque tic du timer
		  public void actionPerformed (ActionEvent event)
		  {
		  	
				if(min !=0){
		    	 if (sec == 0) {
		             min--;
		             sec = 59;
		          } else{
		        	  sec--;         
		          }
				}else{
					 sec--;   
					if(sec == 0){
						flagFindePartie=true;
						stage.addActor(monChamp);
					}
				}
					
		    	temps.setText(String.valueOf(min + ":" + sec));	        	 
		      }
		    };
		    
		  // Création d'un timer qui génère un tic chaque seconde
		  return new Timer (1000, action);
		}  

	 
		public boolean getFlagFinDePartie(){
			return this.flagFindePartie;
		}
		
		public ArrayList<String> lireLesMeilleursScore(){
			
			ArrayList<String> listeDesScores = new ArrayList<String>();
			
			BufferedReader br;
		    try{
		    	br = new BufferedReader(new FileReader(new File("fichierDeSauvegardeDiamond.txt")));
			    
		    	String line = br.readLine();
		    	
		    	while(line!=null){
		    		listeDesScores.add(line);
		    		line = br.readLine();
		    	}
		    	
		    	br.close();
		    	
		    }catch(IOException e){
		    	System.out.print(e);
		    }
		    
		    return listeDesScores;		    
		}
		
		public void mettreAJourMeilleursScores(ArrayList<String> listeDesScores){
			boolean dejaInsere = false;
			ArrayList<String> nouvelleListe = new ArrayList<String>();
			for(String s:listeDesScores){
				if(Integer.parseInt(s.split(":") [0]) > score){
					nouvelleListe.add(s);
				}else{
				
					if(!dejaInsere){
						String nom;
						if(monChamp.getText()!=""){
							nom = monChamp.getText();
						}else{  
							nom="Anonymous";
						}
						
						nouvelleListe.add(String.valueOf(score) + ":"+nom);
						dejaInsere = true;
					}
					nouvelleListe.add(s);

				}
			}
			
			if(!dejaInsere){
				String nom;
				if(monChamp.getText()!=""){
					nom = monChamp.getText();
				}else{  
					nom="Anonymous";
				}
			
				nouvelleListe.add(String.valueOf(score) + ":"+nom);
			}
			
			
			File f = new File("fichierDeSauvegardeDiamond.txt");
			try{
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
				int count = 0;
				for(String s:nouvelleListe){
					if(count<5){
						count ++;
						pw.println(s);
					}
				}
				pw.close();
			}catch(IOException ex){
				System.out.print(ex);
			}
						
		}
		
}