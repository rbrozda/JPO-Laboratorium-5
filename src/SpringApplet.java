import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpringApplet extends JApplet implements MouseListener, MouseMotionListener, ActionListener{ //Informacja o implementacji interfejsu MouseListener i interfejsu MouseMotionListener przez
	//klasê SpringApplet (s³owo kluczowe “implements”).
	
	private SimEngine poleSimEngine; //Prywatne pole do przechowywania obiektu klasy SimEngine.
	private SimTask poleSimTask; //Prywatne pole do przechowywania obiektu klasy SimTask.
	private Timer poleTimer; //Prywatne pole do przechowywania obiektu klasy Timer.
	
	//Prywatne pole do przechowywania wartoœci logicznej mówi¹cej czy w danym momencie
	//nastêpuje “przeci¹ganie” kursora myszy
	private boolean dragging;
	
	//Zmienne do okreœlenia po³o¿enia po zastosowaniu myszki
	private double dragx,dragxx;
	private double dragy,dragyy;
	
	//pole przycisku
    private Button przyciskReset;
    //pola przechowujace podstawowe informacje o uk³adzie
    private TextField M,K,C,G,L0;

	
	
	//Metody konieczne do implementacji interfejsów MouseListener i MouseMotionListener.

	@Override
	public void mouseDragged(MouseEvent dragged) {
		// TODO Auto-generated method stub
		
		if(dragging == true) //sprawdzenie czy nastêpuje “przeci¹ganie” kursora myszy
		{
			//odczytanie pozycji kursora myszy
			dragy = dragged.getY();
			
			
			//ustawienie pozycji masy w obiekcie klasy SimEngine zgodnie z pozycj¹ kursora,
			dragyy = dragy + poleSimEngine.getPolozenieMasyY();
			
			repaint();  //wywo³anie metody repaint()
		}
		
		dragged.consume(); //wywo³anie metody consume() dla obiektu typu MouseEvent.
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
		
		//odczytanie po³o¿enia kursora
		this.x = pressed.getX();
		this.y = pressed.getY();	
		
		//sprawdzenie czy pozycja kursora znajduje siê w obrêbie ko³a przedstawiaj¹cego masê
		if((x>=480) && (x<=520) && (y>=10+(int)(poleSimEngine.getMassY())) && (y<=50+(int)(poleSimEngine.getMassY())))
		{
			poleTimer.cancel(); //wy³¹czenie timera
			poleSimEngine.reset(); //zresetowanie symulacji
			dragging = true; //ustawienie wartoœci logicznej 1 dla pola stanu myszy
		}
		
		pressed.consume(); //wywo³anie metody consume() dla obiektu typu MouseEvent.
	}

	@Override
	public void mouseReleased(MouseEvent released) {
		// TODO Auto-generated method stub
		
		if(dragging == true)//sprawdzenie czy wyst¹pi³o “przeci¹ganie” kursora myszy
		{
			poleSimTask = new SimTask(poleSimEngine,this,1.5); 
			
			poleTimer = new Timer();
			poleTimer.scheduleAtFixedRate(poleSimTask,0,20); //w³¹czenie timera
			dragging = false; //ustawienie wartoœci logicznej 0 dla pola stanu myszy
		}
		
		released.consume(); //wywo³anie metody consume() dla obiektu typu MouseEvent
	}
	
	
	//Przeci¹¿ona, publiczna, bezparametrowa metoda init():
		public void init( )
		{		
			setSize(1000,1000);//Rozmiar okienka apletu
			
			//utworzenie nowego obiektu klasy SimEngine za pomoc¹ konstruktora z parametrami
			//i przypisanie go do pola
			SimEngine obiektSimEngine = new SimEngine(20,0.8,1,50,10,5,5,5,4,5,9.81);
			poleSimEngine = obiektSimEngine;
			
			//utworzenie nowego obiektu klasy SimTask za pomoc¹ konstruktora z parametrami
			//i przypisanie go do pola
			poleSimTask = new SimTask(obiektSimEngine,this,1.5);
			
			//Utworzenie obiektu klasy Timer
			poleTimer = new Timer();
			poleTimer.scheduleAtFixedRate(poleSimTask,0,20);
			
			
			dragging = false;
			addMouseListener(this); //dodanie “nas³uchiwacza” myszy do appletu – addMouseListener()
			addMouseMotionListener(this); //dodanie “nas³uchiwacza” ruchu myszy do appletu – addMouseMotionListener()
		
			
			//przypisanie przycisku
	        przyciskReset=new Button("Reset");
	        
	        //dodanie nas³uchiwacza przycisku
	        przyciskReset.addActionListener(this);
	        
	        //dodanie przycisku do appletu
	        add(przyciskReset);
	        
	        //inicjalizacja stworzonych pól nowymi obiektami
	      	M = new TextField("20",1);
	        K = new TextField("0.8",1);
	        C = new TextField("1",1);
	        L0 = new TextField("50",1);
	        G = new TextField("9.81",1);
	        
	        
	        //dodanie elementów GUI do appletu – metoda add()	        
	        add(M);
	        add(K);
	        add(C);
	        add(L0);
	        add(G);	        	        
		}
	

		//Przeci¹¿ona, publiczna, metoda paint() z parametrem typu Graphics
		public void paint(Graphics g)
		{
			Graphics2D g2 =  (Graphics2D) g;
			
			if(dragging)
			{
				g.setColor(Color.GRAY); //Kolor t³a
				g.fillRect(0, 0, 1000, 1000); 
			
				g.setColor(Color.RED);//Kolor linki - czerwony
			
				g2.setStroke(new BasicStroke(4));//Gruboœæ linki - 4
				g.drawLine(500,10,500,(int)(dragyy)-50); //narysowanie linii reprezentuj¹cej sprê¿ynê + symulacja zmiany po³o¿enia za pomoc¹ myszy
				
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
			
			
	        //narysowanie pól do wprowadzania danych
	        g.setColor(Color.YELLOW);
	        
	        g.drawString("Masa M", 30, 30);
	        M.setBounds(30,40,50,30);	       
	        g.drawString("Wspó³czynnik sprê¿ystoœci K", 30, 110);
	        K.setBounds(30,120,50,30);
	        g.drawString("Wspó³czynnik t³umienia C", 30, 190);
	        C.setBounds(30,200,50,30); 
	        g.drawString("D³ugoœæ swobodna sprê¿yny L0", 30, 270);
	        L0.setBounds(30,280,50,30);
	        g.drawString("Przyspieszenie ziemskie G", 30, 350);
	        G.setBounds(30,360,50,30);
	        
	        przyciskReset.setBounds(250,30,80,60);//narysowanie przycisku resetuj¹cego symulacjê
		}

		
		
		//Metoda konieczna do implementacji interfejsu ActionListener.
		@Override
		public void actionPerformed(ActionEvent a) {
			// TODO Auto-generated method stub
			
			if(a.getSource() == przyciskReset) //sprawdzenie czy Ÿród³em akcji jest stworzony przycisk
			{
		        	//wy³¹czenie timera
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
		            
		            //w³¹czenie timera
		            poleTimer = new Timer();
		            poleTimer.scheduleAtFixedRate(poleSimTask, 0, 20);
		            repaint();
			}			
		}
}









