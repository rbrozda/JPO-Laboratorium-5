import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpringApplet extends JApplet implements MouseListener, MouseMotionListener, ActionListener{ //Informacja o implementacji interfejsu MouseListener i interfejsu MouseMotionListener przez
	//klas� SpringApplet (s�owo kluczowe �implements�).
	
	private SimEngine poleSimEngine; //Prywatne pole do przechowywania obiektu klasy SimEngine.
	private SimTask poleSimTask; //Prywatne pole do przechowywania obiektu klasy SimTask.
	private Timer poleTimer; //Prywatne pole do przechowywania obiektu klasy Timer.
	
	//Prywatne pole do przechowywania warto�ci logicznej m�wi�cej czy w danym momencie
	//nast�puje �przeci�ganie� kursora myszy
	private boolean dragging;
	
	//Zmienne do okre�lenia po�o�enia po zastosowaniu myszki
	private double dragx,dragxx;
	private double dragy,dragyy;
	
	//pole przycisku
    private Button przyciskReset;
    //pola przechowujace podstawowe informacje o uk�adzie
    private TextField M,K,C,G,L0;

	
	
	//Metody konieczne do implementacji interfejs�w MouseListener i MouseMotionListener.

	@Override
	public void mouseDragged(MouseEvent dragged) {
		// TODO Auto-generated method stub
		
		if(dragging == true) //sprawdzenie czy nast�puje �przeci�ganie� kursora myszy
		{
			//odczytanie pozycji kursora myszy
			dragy = dragged.getY();
			
			
			//ustawienie pozycji masy w obiekcie klasy SimEngine zgodnie z pozycj� kursora,
			dragyy = dragy + poleSimEngine.getPolozenieMasyY();
			
			repaint();  //wywo�anie metody repaint()
		}
		
		dragged.consume(); //wywo�anie metody consume() dla obiektu typu MouseEvent.
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	private double x, y;
	
	@Override
	public void mousePressed(MouseEvent pressed) {
		// TODO Auto-generated method stub
		
		//odczytanie po�o�enia kursora
		this.x = pressed.getX();
		this.y = pressed.getY();	
		
		//sprawdzenie czy pozycja kursora znajduje si� w obr�bie ko�a przedstawiaj�cego mas�
		if((x>=480) && (x<=520) && (y>=10+(int)(poleSimEngine.getMassY())) && (y<=50+(int)(poleSimEngine.getMassY())))
		{
			poleTimer.cancel(); //wy��czenie timera
			poleSimEngine.reset(); //zresetowanie symulacji
			dragging = true; //ustawienie warto�ci logicznej 1 dla pola stanu myszy
		}
		
		pressed.consume(); //wywo�anie metody consume() dla obiektu typu MouseEvent.
	}

	@Override
	public void mouseReleased(MouseEvent released) {
		// TODO Auto-generated method stub
		
		if(dragging == true)//sprawdzenie czy wyst�pi�o �przeci�ganie� kursora myszy
		{
			poleSimTask = new SimTask(poleSimEngine,this,1.5); 
			
			poleTimer = new Timer();
			poleTimer.scheduleAtFixedRate(poleSimTask,0,20); //w��czenie timera
			dragging = false; //ustawienie warto�ci logicznej 0 dla pola stanu myszy
		}
		
		released.consume(); //wywo�anie metody consume() dla obiektu typu MouseEvent
	}
	
	
	//Przeci��ona, publiczna, bezparametrowa metoda init():
		public void init( )
		{		
			setSize(1000,1000);//Rozmiar okienka apletu
			
			//utworzenie nowego obiektu klasy SimEngine za pomoc� konstruktora z parametrami
			//i przypisanie go do pola
			SimEngine obiektSimEngine = new SimEngine(20,0.8,1,50,10,5,5,5,4,5,9.81);
			poleSimEngine = obiektSimEngine;
			
			//utworzenie nowego obiektu klasy SimTask za pomoc� konstruktora z parametrami
			//i przypisanie go do pola
			poleSimTask = new SimTask(obiektSimEngine,this,1.5);
			
			//Utworzenie obiektu klasy Timer
			poleTimer = new Timer();
			poleTimer.scheduleAtFixedRate(poleSimTask,0,20);
			
			
			dragging = false;
			addMouseListener(this); //dodanie �nas�uchiwacza� myszy do appletu � addMouseListener()
			addMouseMotionListener(this); //dodanie �nas�uchiwacza� ruchu myszy do appletu � addMouseMotionListener()
		
			
			//przypisanie przycisku
	        przyciskReset=new Button("Reset");
	        
	        //dodanie nas�uchiwacza przycisku
	        przyciskReset.addActionListener(this);
	        
	        //dodanie przycisku do appletu
	        add(przyciskReset);
	        
	        //inicjalizacja stworzonych p�l nowymi obiektami
	      	M = new TextField("20",1);
	        K = new TextField("0.8",1);
	        C = new TextField("1",1);
	        L0 = new TextField("50",1);
	        G = new TextField("9.81",1);
	        
	        
	        //dodanie element�w GUI do appletu � metoda add()	        
	        add(M);
	        add(K);
	        add(C);
	        add(L0);
	        add(G);	        	        
		}
	

		//Przeci��ona, publiczna, metoda paint() z parametrem typu Graphics
		public void paint(Graphics g)
		{
			Graphics2D g2 =  (Graphics2D) g;
			
			if(dragging)
			{
				g.setColor(Color.GRAY); //Kolor t�a
				g.fillRect(0, 0, 1000, 1000); 
			
				g.setColor(Color.RED);//Kolor linki - czerwony
			
				g2.setStroke(new BasicStroke(4));//Grubo�� linki - 4
				g.drawLine(500,10,500,(int)(dragyy)-50); //narysowanie linii reprezentuj�cej spr�yn� + symulacja zmiany po�o�enia za pomoc� myszy
				
				g.setColor(Color.GREEN);//Kolor kulki - zielony		
				g.fillOval(480,(int)(dragyy-75),40,40);

			} 
			else
			{
				g.setColor(Color.GRAY); 
				g.fillRect(0, 0, 1000, 1000); 
				
				g.setColor(Color.RED);
				
				g2.setStroke(new BasicStroke(4));
				g.drawLine(500,10,500,10+(int)(poleSimEngine.getMassY())); 
				
				g2.setStroke(new BasicStroke(2));
				g.setColor(Color.GREEN);
				g.fillOval((int)(480),10+(int)(poleSimEngine.getMassY()),40,40); 
			}
			
			
	        //narysowanie p�l do wprowadzania danych
	        g.setColor(Color.YELLOW);
	        
	        g.drawString("Masa M", 30, 30);
	        M.setBounds(30,40,50,30);	       
	        g.drawString("Wsp�czynnik spr�ysto�ci K", 30, 110);
	        K.setBounds(30,120,50,30);
	        g.drawString("Wsp�czynnik t�umienia C", 30, 190);
	        C.setBounds(30,200,50,30); 
	        g.drawString("D�ugo�� swobodna spr�yny L0", 30, 270);
	        L0.setBounds(30,280,50,30);
	        g.drawString("Przyspieszenie ziemskie G", 30, 350);
	        G.setBounds(30,360,50,30);
	        
	        przyciskReset.setBounds(250,30,80,60);//narysowanie przycisku resetuj�cego symulacj�
		}

		
		
		//Metoda konieczna do implementacji interfejsu ActionListener.
		@Override
		public void actionPerformed(ActionEvent a) {
			// TODO Auto-generated method stub
			
			if(a.getSource() == przyciskReset) //sprawdzenie czy �r�d�em akcji jest stworzony przycisk
			{
		        	//wy��czenie timera
		            poleTimer.cancel();
		            
		            //zamiana tekstu na typ double
		            double g = Double.parseDouble(G.getText());
		            double m = Double.parseDouble(M.getText());
		            double l = Double.parseDouble(L0.getText());
		            double k = Double.parseDouble(K.getText());
		            double c = Double.parseDouble(C.getText());
		            
		            //stworzenie nowego obiektu typu SimEngine
		            poleSimEngine = new SimEngine(m,k,c,l,10,5,5,5,4,5,10);
		            
		            //stworzenie nowego obiektu typu SimTask
		            poleSimTask = new SimTask(poleSimEngine, this, 1.5);
		            
		            //ustawienie przyspieszenia ziemskiego
		            poleSimEngine.setPrzyspieszenieG(g);
		            
		            //w��czenie timera
		            poleTimer = new Timer();
		            poleTimer.scheduleAtFixedRate(poleSimTask, 0, 20);
		            repaint();
			}			
		}
}









