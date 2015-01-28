package ProjetGame2;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fr.ProjetGame2.Game.ProjetGame2;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ProjetGame2";
		cfg.width = 600;
		cfg.height = 400;
		
		new LwjglApplication(new ProjetGame2(), cfg);
	}
}
