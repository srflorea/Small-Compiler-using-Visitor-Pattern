
/**
 * Clasa ce reprezinta valoare intreaga sau logica si deriva din clasa Node.
 * @author Razvan
 *
 */
public class Valoare extends Node {

	/**
	 * Constructorul acestei clase.
	 * @param nume Reprezinta numele valorii, adica valoarea propiu-zisa.
	 * @param linie Reprezinta linia de pe care s-a citit caracterul
	 * @param coloana Reprezinta coloana de pe care s-a citit caracterul din linia respectiva.
	 * @param tip Aceasta clasa va avea tipul intotdeauna null.
	 */
	public Valoare(String nume,int linie,int coloana,String tip)
	{
		super(nume,linie,coloana,tip);
	}
	
	/**
	 * Metoda ce apeleaza metoda 'visit' din vizitatorul primit ca parametru
	 */
	public void accept(Visitor v) {
        v.visit(this);          
}
}
