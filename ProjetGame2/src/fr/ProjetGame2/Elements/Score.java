package fr.ProjetGame2.Elements;

import java.util.ArrayList;


public class Score{
	public ArrayList<Score> Scores = new ArrayList<Score>();
	private int score;
	public Score(int score){
		this.score = score;
	}
	public ArrayList<Score> getScores() {
		return Scores;
	}
	public void setScores(ArrayList<Score> scores) {
		Scores = scores;
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
