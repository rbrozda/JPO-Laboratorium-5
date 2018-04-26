import java.util.Scanner;

public class SimEngine {

		public void kontrola(double parametr)//kontrola warto�ci
		{
			while(parametr<0)
			{
				System.out.print("Podano z�� warto��. Spr�buj ponownie. ");
				Scanner input = new Scanner(System.in);
				parametr=input.nextDouble();
				System.out.print("\n");
			}
		}
	
		private double masa;
		public void setMasa(double wartosc)
		{
			kontrola(wartosc);
			masa = wartosc;
		}
		
		public double getMasa()
		{
			return masa;
		}
		
		
		private double wspolSprez;
		public void setWspolSprez(double wartosc)
		{
			kontrola(wartosc);
			wspolSprez = wartosc;
		}
		
		public double getWspolSprez()
		{
			return wspolSprez;
		}
		
		
		private double wspolTlum;
		public void setWspolTlum(double wartosc)
		{
			kontrola(wartosc);
			wspolTlum = wartosc;
		}
		
		public double getwspolTlum()
		{
			return wspolTlum;
		}
		
		
		private double dlugosc;
		public void setDlugosc(double wartosc)
		{
			kontrola(wartosc);
			dlugosc = wartosc;
		}
		
		private double polozenieMasyX;
		public void setPolozenieMasyX(double wartosc)
		{
			kontrola(wartosc);
			polozenieMasyX = wartosc;
		}
		
		public double getPolozenieMasyX()
		{
			return polozenieMasyX;
		}
		
		
		private double polozenieMasyY;
		public void setPolozenieMasyY(double wartosc)
		{
			kontrola(wartosc);
			polozenieMasyY = wartosc;
		}
		
		public double getPolozenieMasyY()
		{
			return polozenieMasyY;
		}
		
		
		
		private double predkoscMasyX;
		public void setPredkoscX(double wartosc)
		{
			kontrola(wartosc);
			predkoscMasyX = wartosc;
		}
		
		public double getPredkoscMasyX()
		{
			return predkoscMasyX;
		}
		
		private double predkoscMasyY;
		public void setPredkoscY(double wartosc)
		{
			kontrola(wartosc);
			predkoscMasyY = wartosc;
		}
		
		public double getPredkoscMasyY()
		{
			return predkoscMasyY;
		}
		
		
		
		private double polozeniePunktuX;
		public void setPolozeniePunktuX(double wartosc)
		{
			kontrola(wartosc);
			polozeniePunktuX = wartosc;
		}
		
		
		public double getPo�ozeniePunktuX()
		{
			return polozeniePunktuX;
		}
		
		private double polozeniePunktuY;
		public void setPolozeniePunktuY(double wartosc)
		{
			kontrola(wartosc);
			polozeniePunktuY = wartosc;
		}
		
		public double getPo�ozeniePunktuY()
		{
			return polozeniePunktuY;
		}
		
		
		
		private double przyspieszenieG;
		public void setPrzyspieszenieG(double wartosc)
		{
			kontrola(wartosc);
			przyspieszenieG = wartosc;
		}
		
		public double getG()
		{
			return przyspieszenieG;
		}
		
	
		
		//Konstruktor z parametrami pozwalaj�cy na nadanie warto�ci pocz�tkowych wszystkim
		//parametrom symulacji.
		public SimEngine (double m, double wS, double wT, double d, double polMX, double polMY, double predMX, double predMY, double pPX, double pPY,double g)
		{
			setMasa(m);
			setWspolSprez(wS);
			setWspolTlum(wT);
			setDlugosc(d);
			setPolozenieMasyX(polMX);
			setPolozenieMasyY(polMY);
			setPredkoscX(predMX);
			setPredkoscY(predMY);
			setPolozeniePunktuX(pPX);
			setPolozeniePunktuY(pPY);
			setPrzyspieszenieG(g);
		}

		
		//obecne po�o�enie masy
		private Vector2D polozenieMasy;
		
		//ustawienie wsp�rz�dnych masy
		public void setXY_Masy(double x, double y)
		{
			this.polozenieMasy = new Vector2D(x,y);
		}
		
		
		Vector2D pozycjaMasy = new Vector2D();
		
		double Vy=predkoscMasyY;

		public double getMassY() 
		{	
			return pozycjaMasy.wspolrzednaY;
		}
		
		//Metoda z parametrem - krokiem czasowym
		public void symulacja(double krokCzasowy)
		{
			double ay = (masa*przyspieszenieG - wspolSprez*pozycjaMasy.getY() - wspolTlum*Vy)/masa;
			Vy = Vy + ay*krokCzasowy;
			pozycjaMasy.wspolrzednaY = pozycjaMasy.wspolrzednaY + (Vy*krokCzasowy + ay*krokCzasowy*krokCzasowy/2);
		}
		
		//Metoda bez parametr�w resetuj�ca symulacj�
		public void reset()
		{
			predkoscMasyY=0;
		}
		
		
		
}
