public class Vector2D {
	public double wspolrzednaX, wspolrzednaY; //publiczne pola wsp�rz�dnych wektora (x, y).
	
	public Vector2D() //konstruktor domy�lny inicjuj�cy pola klasy zerami
	{
		wspolrzednaX = 0;
		wspolrzednaY = 0;
	}
	
	public Vector2D (double x, double y) //konstruktor z parametrami pozwalajacy na nadanie dowolnych warto�ci polom klasy.
	{
		wspolrzednaX = x;
		wspolrzednaY = y;
	}
	
	public double getX() //akcesor parametru 'wspolrzednaX'
	{
		return wspolrzednaX;
	}
	
	public double getY() //akcesor parametru 'wspolrzednaY'
	{
		return wspolrzednaY;
	}
	
	
	//Metoda z parametrem zwracaj�ca nowy obiekt klasy Vector2D, b�d�cy sum� wektora, dla
	//kt�rego zosta�a wywo�ana i wektora podanego jako parametr.
	
	Vector2D sumaWektorow(double x, double y)
	{
		double sumaX = getX() + x;
		double sumaY = getY() + y;
		
		Vector2D wynik = new Vector2D(sumaX,sumaY);
		
		return wynik; //zwr�cenie obiektu
	}
	
	
	//Metoda z parametrem zwracaj�ca nowy obiekt klasy Vector2D, b�d�cy r�nic� wektora, dla
	//kt�rego zosta�a wywo�ana i wektora podanego jako parametr.
	
	Vector2D roznicaWektorow(double x, double y)
	{
		double roznicaX = getX() - x;
		double roznicaY = getY() - y;
		
		Vector2D wynik = new Vector2D(roznicaX,roznicaY);
		
		return wynik; //zwr�cenie obiektu
	}
	
	
	//Metoda z parametrem zwracaj�ca nowy obiekt klasy Vector2D, b�d�cy przemno�onym przez
	//sta�� wektorem, dla kt�rego zosta�a wywo�ana.
	
	Vector2D iloczynWektora(double stala)
	{
		double iloczynX = stala * getX();
		double iloczynY = stala * getY();
		
		Vector2D wynik = new Vector2D(iloczynX,iloczynY);
		
		return wynik; //zwr�cenie obiektu
	}
	
	
	//Metoda bez parametr�w zwracaj�ca d�ugo�� wektora
	public double getDlugosc() 
	{
		double dlugosc = Math.sqrt((getX()*getX()) + (getY()*getY()));
		return dlugosc;
	}
	
	
	//Metoda bez parametr�w zwracaj�ca nowy obiekt klasy Vector2D, b�d�cy
	//znormalizowanym wektorem, dla kt�rego zosta�a wywo�ana.
	Vector2D wektorNormalny()
	{
		double normalnyX = getX() / getDlugosc();
		double normalnyY = getY() / getDlugosc();
		
		Vector2D wynik = new Vector2D(normalnyX,normalnyY);
		return wynik; //zwr�cenie obiektu
	}
}
	






