package fr.ProjetGame2.Elements;

import java.util.ArrayList;


public class Score{
	private int score;
	public Score(int score){
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String toString(){
		return String.valueOf(score);
	}
}
