import java.util.TimerTask;

public class SimTask extends TimerTask
{
	private SimEngine poleSimEngine; //Prywatne pole do przechowywania obiektu klasy SimEngine.
	private SpringApplet poleSpringApplet;//Prywatne pole do przechowywania obiektu klasy SpringApplet
	private double odstepCzasowy;//Prywatne pole do przechowywania odst�pu czasowego pomi�dzy kolejnymi krokami symulacji
	
	
	//Konstruktor z parametrami pozwalaj�cy na przypisanie do p�l klasy obiektu klasy SimEngine,
	//obiektu klasy SpringApplet i odst�pu czasowego pomi�dzy kolejnymi krokami symulacji.

	public SimTask (SimEngine poleSimEngine, SpringApplet poleSpringApplet, double odstepCzasowy)
	{
		this.poleSimEngine = poleSimEngine;
		this.poleSpringApplet = poleSpringApplet;
		this.odstepCzasowy = odstepCzasowy;	
	}


	//Przeci��ona, publiczna, bezparametrowa metoda run():
	public void run() 
	{
		poleSimEngine.symulacja(odstepCzasowy);	//uruchomienie obliczenia kolejnego kroku symulacji		
		poleSpringApplet.repaint();//od�wie�enie wy�wietlania powierzchni appletu (
	}
}
