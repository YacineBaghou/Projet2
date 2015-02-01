package fr.ProjetGame2.Elements;


public class Cristal {
	
	private int couleur;
	
	public Cristal(int couleur){
		this.couleur = couleur;
	}
	
	public int getCouleur(){
		return this.couleur;
	}
	
	public String toString(){
		return Integer.toString(this.couleur);
	}
	
	
}