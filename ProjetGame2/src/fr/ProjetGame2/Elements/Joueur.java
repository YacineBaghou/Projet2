package fr.ProjetGame2.Elements;

public class Joueur {

	private String nom;
	private int score;
	
	public Joueur(String nom){
		this.nom = nom;
		this.score = 0;
	}
	
	public Joueur(String nom, int score){ //Constructeur avec score (peut être innutile)
		this.nom = nom;
		this.score = score;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public int getScore(){
		return this.getScore();
	}	
	
	public void setScore(){
		score += 10;
	}
	
}
