package fr.ProjetGame2.Elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temps implements ActionListener {
    private int timeMinute;
    private int timeSeconde;

    public Temps(int initMinute, int initSeconde) {
          super();
          this.timeMinute = initMinute;
          this.timeSeconde = initSeconde;
    }

    public void actionPerformed(ActionEvent e) {
          if (this.timeSeconde == 0) {
                this.timeMinute--;
                this.timeSeconde = 59;
          } else
                this.timeSeconde--;
          
    }
    public String toString(){
    	
    	return this.timeMinute + " : " + this.timeSeconde;
    }

}