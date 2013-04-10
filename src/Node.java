import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

/**
 * Clasa ce implementeaza interfaza Vizitable si reprezinta clasa de baza din care vor deriva toate celelalte clase.
 * @author Razvan
 *
 */
public class Node implements Visitable {

	protected String nume;
	protected int linie;
	protected int coloana;
	protected String tip;
	List<Node> list=new ArrayList<Node>();
	/**
	 * Constructorul acestei clase.
	 * @param nume Reprezinta numele nodului.
	 * @param linie Reprezinta linia de pe care s-a citit caracterul
	 * @param coloana Reprezinta coloana de pe care s-a citit caracterul din linia respectiva.
	 * @param tip Aceasta clasa va avea tipul intotdeauna null.
	 */
	public Node(String nume,int linie,int coloana,String tip)
	{
		this.nume=nume;
		this.linie=linie;
		this.coloana=coloana;
		this.tip=tip;
	}
	/**
	 * Metoda de afisare a arborelui construit.
	 * @param node Reprezinta radacina arborelui ce urmeaza a fi afisat.
	 * @param str Numele fisierului de intrare , din care se va contrui numele fisierului de iesire.
	 */
	public void display(Node node,String str)
	{
		Node nod;
		str+="_pt";
		try{
			  FileWriter fstream = new FileWriter(str);
			  BufferedWriter out = new BufferedWriter(fstream);
		out.write("r");
		out.newLine();
		while(!node.list.isEmpty())
		{
			nod=node.list.get(0);
			node.list.remove(0);
			parcurgere(nod,1,out);
		}
		out.close();
	  }catch (Exception e){
	  System.err.println("Error: " + e.getMessage());
	  }
	}
	/**
	 * Metoda de parcurgere a arborelui ce va fi apelata recursiv pentru fiecare nod.
	 * @param nod Reprezinta nodul curent.
	 * @param nr Reprezinta adancimea in arbore pentru afisarea de taburi.
	 * @param out Reprezinta adresa unde trebuie sa scrie metoda.
	 */
	public void parcurgere(Node nod,int nr,BufferedWriter out)
	{
		try{
		for(int i=0;i<nr;i++)
			out.write('\t');
		out.write(nod.nume);
		out.newLine();}
		catch (Exception e){
			  System.err.println("Error: " + e.getMessage());
			  }
		if(!nod.list.isEmpty())
		{
			parcurgere(nod.list.get(1),nr+1,out);
			parcurgere(nod.list.get(0),nr+1,out);
		}
	}
	
	/**
	 * Metoda ce apeleaza metoda 'visit' din vizitatorul primit ca parametru
	 */
	public void accept(Visitor v) {
		v.visit(this);          
}
}
