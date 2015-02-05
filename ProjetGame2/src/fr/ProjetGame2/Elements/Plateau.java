package fr.ProjetGame2.Elements;

import java.util.ArrayList;


public class Plateau {

	private Joueur joueur1;
	private Joueur joueur2;
	
	private Joueur joueurCourant;
	
	private Cristal[][] tabCristaux; 	//tableau contenant les cristaux de la grille

	private int tailleX, tailleY;
	
	private boolean flagInit = false;
	
	public Plateau(Joueur joueur1, Joueur joueur2, int tailleX, int tailleY){ //Constructeur du plateau
		
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.joueurCourant = joueur1;
		
		this.tabCristaux = new Cristal[tailleX][tailleY];
		
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		
		for(int x = 0; x<tailleX; x++){
			for(int y = 0; y<tailleY; y++){
				Cristal unCristal = new Cristal((int)(Math.random()*5)+1);
				tabCristaux[x][y] = unCristal;
			}
		}
		
		/*for(int i=6; i<10; i++){ //Trucage du tableau pour les tests
			tabCristaux[9][i] = new Cristal(0);
			tabCristaux[i][9] = new Cristal(1);
		}
		tabCristaux[9][9] = new Cristal(0);
		tabCristaux[9][8] = new Cristal(1);*/
		trouverCombo();
		flagInit = true;
		
	}
	
	public String toString(){   //toString pour faciliter l'affichage et le debogage
		
		String maChaineRetour="";
		
		for(Cristal[] ligne: tabCristaux){
			for(Cristal colonne: ligne){
				if(colonne == null)
					maChaineRetour = maChaineRetour + "0" ;
				else
					maChaineRetour = maChaineRetour + colonne.toString();
			}
			maChaineRetour = maChaineRetour + "\n";
		}
		return maChaineRetour;
	}
	
	public void jouer(Joueur joueur, int posX1, int posY1, int posX2, int posY2){ // Lorsque le joueur doit jouer
		
		if(joueur == joueurCourant){					//On vérifie si c'est son tour de jouer
			if( verifVoisin(posX1, posY1, posX2, posY2)){				//On vérifie si les cristaux joués sont voisins
				switchCristals(posX1, posY1, posX2, posY2);				//On switch les cristaux dans le tableau 
				ArrayList<ArrayList<Integer>> tabs = chercheBlock(posX1, posY1);	//On cherche si des blocks de cristaux de même couleur ont été formés et on stocks leurs coordonnées dans tab
				ArrayList<ArrayList<Integer>> tabs2 = chercheBlock(posX2, posY2);
				
				/*for (ArrayList<Integer> arrayList : tabs) {  //Affichage des coordonnées stockées dans tab
					for(Integer i : arrayList){
						System.out.print(i);
					}
					System.out.println();
				}*/
				
				faireLeMenage(tabs2);
				faireLeMenage(tabs);		//On "supprime" les cristaux listés dans tab
				restructurer();				//On réagence le tableau en supprimant les cases vides, les cristaux au dessus glissent vers le bas, les cases hautes laissées vides sont remplies aléatoirement
				trouverCombo();
				
				if(tabs.size() == 0 && tabs2.size()==0)		//Aucun block n'a été renvoyé
				{
					System.out.println("reswitch");
					switchCristals(posX1, posY1, posX2, posY2);
				}
			}else{
				System.out.println("Ces deux cristaux ne sont pas voisins");
			}
				
		}else
		{
			System.out.println("Ce n'est pas à " + joueur.getNom() + " de jouer...");
			System.out.println("C'est au tour de " + joueurCourant.getNom() + " de jouer...");
		}
		
	}
	
	
	
	
	public Cristal pickCristal(int posX, int posY){ //Sert à recupérer facilement un objet dans tabCristaux
		return tabCristaux[posX][posY];
	}
	
	
	
	
	
	private boolean verifVoisin( int posX1, int posY1, int posX2, int posY2){  //Vérifie si les deux cristaux joués sont voisins dans tabCristaux
		if(posX1 == posX2 + 1 && posY1 == posY2 || posX1 == posX2 - 1 && posY1 == posY2 || posX1 == posX2 && posY1 == posY2 + 1 || posX1 == posX2 && posY1 == posY2 - 1)
			return true;
		else
			return false;
	}
	
	
	
	
	
	
	private boolean verifMemeCouleur( int posX1, int posY1, int posX2, int posY2){   		//Renvoi true si les cristaux dont les coordonnées sont passées en parametre sont de la même couleur
		
		if ( posX1<tailleX && posX2 < tailleX && posY1<tailleY && posY2 < tailleY && posX1>=0 && posX2>=0 && posY1>=0 && posY2 >= 0 && pickCristal(posX1, posY1).getCouleur() == pickCristal(posX2, posY2).getCouleur())
			return true;
		else
			return false;	
	}

	
	
	
	
	
	
	private  ArrayList<ArrayList<Integer>> chercheBlock(int posX1, int posY1){   //recoit les coordonnées de deux cristaux et cherche si ils appartiennent à des blocks en ligne de même couleur et les renvois dans une liste de int à 2 dimmensions contenant leur coordonnées
		
		ArrayList<ArrayList<Integer>> maListe = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> maPetiteListe = new ArrayList<Integer>();
		
		
		maPetiteListe.add(posX1);
		maPetiteListe.add(posY1);
		maListe.add(maPetiteListe);
		
		
		boolean breakPositif = true, breakNegatif = true;
		boolean stopFinPositifX = true;
		boolean stopFinNegatifX = true;
		boolean stopFinPositifY = true;
		boolean stopFinNegatifY = true;
	
		// Recherche d'un block pour x1,y1
		
		for(int i = 1 ; i<10 ; i++){				//Verif horizontale
			
			
			if(posX1+i > tailleX -1)
				stopFinPositifX = false;
			if(posX1 - i <0)
				stopFinNegatifX = false;

			
			
			if(breakPositif && stopFinPositifX && tabCristaux[posX1+i][posY1] != null && verifMemeCouleur(posX1 + i, posY1, posX1, posY1)){  //Sens positif 
				maPetiteListe = new ArrayList<Integer>();
				maPetiteListe.add(posX1 + i);
				maPetiteListe.add(posY1);
				maListe.add(maPetiteListe);
			}else
				breakPositif = false;
				
			
			  if(breakNegatif && stopFinNegatifX && tabCristaux[posX1 - i][posY1] != null && verifMemeCouleur(posX1 - i, posY1, posX1, posY1)){ //Sens negatif
				maPetiteListe = new ArrayList<Integer>();
				maPetiteListe.add(posX1 - i);
				maPetiteListe.add(posY1);
				maListe.add(maPetiteListe);
			}else
				breakNegatif = false;
		}
		
		breakNegatif = breakPositif = true;
		
		
		for(int i = 1 ; i<10 ; i++){				//Verif verticale
			

			if(posY1+i > tailleY -1)
				stopFinPositifY = false;
			if(posY1 -i < 0)
				stopFinNegatifY = false;
			
			
			if(breakPositif && stopFinPositifY && tabCristaux[posX1][posY1 + i] != null && verifMemeCouleur(posX1, posY1+i, posX1, posY1)){  //Sens positif 
				maPetiteListe = new ArrayList<Integer>();
				maPetiteListe.add(posX1);
				maPetiteListe.add(posY1 + i);
				maListe.add(maPetiteListe);
			}else
				breakPositif = false;
				
			if(breakNegatif && stopFinNegatifY && tabCristaux[posX1][posY1 - i] != null && verifMemeCouleur(posX1, posY1-i, posX1, posY1)){ //Sens negatif
				maPetiteListe = new ArrayList<Integer>();
				maPetiteListe.add(posX1);
				maPetiteListe.add(posY1 - i);
				maListe.add(maPetiteListe);
			}else
				breakNegatif = false;
		}
		
	/*	ArrayList<ArrayList<Integer>> listeDejaTrouves = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> arrayList : maListe){
			listeDejaTrouves = chercheBlock(arrayList.get(0), arrayList.get(1));
		}*/
	
		if(maListe.size() > 2){			//Si la liste du cristal a assez d'élément on renvoi la liste
			System.out.println(maListe);
			return maListe;
		}
		
			maListe.clear();         //Si aucun block assez grand n'est détecté, on renvoi une liste vide
			return maListe;
		
	}
	
	private void switchCristals(int posX1, int posY1, int posX2, int posY2){//switch deux cristaux dans le tableau, les voisins doivent êtres vérifiés avant de lancer switch
		Cristal cristalTemp = tabCristaux[posX1][posY1];
		tabCristaux[posX1][posY1] = tabCristaux[posX2][posY2];
		tabCristaux[posX2][posY2] = cristalTemp;
	}
	
	private void faireLeMenage(ArrayList<ArrayList<Integer>> maListe){		//Parcour une liste de coordonnées et remplace les cristaux correspondant par des vide (valeur 6)
		for (ArrayList<Integer> arrayList : maListe){
			tabCristaux[arrayList.get(0)][arrayList.get(1)] = new Cristal(6);
		}
	}
	
	private void restructurer(){ // Restructurer parcour tabCristaux, quand le cristal courant vaut 6(valeur désignant un espace vide), les cristaux au dessus glisse d'une case vers le bas, les cases laissées vides au dessus sont replies aléatoirement
		for (int i = 0; i<tailleX; i++) {
			for (int j = 0; j<tailleY; j++) { 
				
				if ( tabCristaux[i][j].getCouleur() == 6){
					for(int k = 0; i-k >= 0; k++){
						if (i-k <= 0){
							tabCristaux[i-k][j] =  new Cristal((int)(Math.random()*5)+1);
						}else{
							tabCristaux[i-k][j] = tabCristaux[i-k-1][j];
						}
					}
				}
				
			}
		}
	}
	
	
	private void trouverCombo(){
		boolean trouveQuelquChose = false;
		
    	for(int i = 0; i<tailleX; i++){
    		for(int j = 0; j<tailleY; j++){
    			if(tabCristaux[i][j].getCouleur()!=6){
    				ArrayList<ArrayList<Integer>> maListe = chercheBlock(i, j);
    				faireLeMenage(maListe);
    				if (maListe.size()>0){
    					trouveQuelquChose =true;
    				}
    			}
    		}
    	}
    	if(trouveQuelquChose){
    		if (flagInit){
    			restructurer();
    			System.out.println("Combo!!");
    			trouverCombo();
    		}else{
    			randomVoisin();
    		}
    	}
    }
	
	private void randomVoisin(){
		for(int i = 0; i<tailleX; i++){
    		for(int j = 0; j<tailleY; j++){
    			
    			if(tabCristaux[i][j].getCouleur() == 6){
    				do{
    					tabCristaux[i][j] = new Cristal((int)(Math.random()*5)+1);
    					System.out.println("Random!!");
    				}while(verifMemeCouleur(i, j, i-1, j) || verifMemeCouleur(i, j, i+1, j) || verifMemeCouleur(i, j, i, j-1)||verifMemeCouleur(i, j, i, j+1));
    			}
    		}
		}
	}
	
}
