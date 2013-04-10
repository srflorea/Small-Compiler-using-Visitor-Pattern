import java.io.*;
import java.util.*;

/**
 * In aceasta clasa se contruieste arborele de parsare.
 * @author Razvan
 *
 */
public class Arbore {

	private Stack<Node> stiva_rezultat=new Stack<Node>();
	private Stack<Node> stiva_operatori=new Stack<Node>();
	/**
	 * Metoda ce contruieste arborele pe baza algoritmului recomandat si il retine in stiva_rezultat
	 * @param fis Reprezinta sirul de caractere ce reprezinta numele fisierului de intrare.
	 * @throws IOException Exceptii de Input/Output
	 */
	public void constructie_arbore(String fis) throws IOException
	{
		String tip=null;
		Node node,rez1,rez2,op;
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String strLine;
		int linie=0;
		int coloana=1;
		try {
			while ((strLine = br.readLine()) != null)
			{
				coloana=1;
				String string=strLine;
				linie++;
				String spliter=" ";
				String[] str=strLine.split(spliter);
				for(int i=0;i<str.length;i++)
				{
					if(!str[i].equals("+")&&!str[i].equals("*")&&!str[i].equals("="))
						{
							if(Character.isDigit(str[i].charAt(0))||str[i].equals("false")||str[i].equals("true"))
								{
								if(Character.isDigit(str[i].charAt(0)))
									tip="integer";
								else tip="boolean";
								node=new Valoare(str[i],linie,string.indexOf(str[i])+1,tip);
								}
							else node=new Variabila(str[i],linie,string.indexOf(str[i])+1,tip);
							stiva_rezultat.push(node);
						}
					else
					{
						if(str[i].equals("+"))
							{
								coloana+=strLine.indexOf(str[i]);
								node=new OperatorPlus(str[i],linie,coloana,null);
								strLine=strLine.substring(strLine.indexOf(str[i]));
							}
						else if(str[i].equals("*"))
							{
								coloana+=strLine.indexOf(str[i]);
								node=new OperatorInmultire(str[i],linie,coloana,null); 
								strLine=strLine.substring(strLine.indexOf(str[i]));
							}
						else {
							node=new OperatorEgal(str[i],linie,strLine.indexOf(str[i])+1,null); 
						}
						if(stiva_operatori.isEmpty())
						{
							stiva_operatori.push(node);
						}
						else
						{
							for(;;)
							if(prioritate(node,stiva_operatori.peek())==true)
								{
									stiva_operatori.push(node);
									break;
								}
							else
							{
								rez1=stiva_rezultat.pop();
								rez2=stiva_rezultat.pop();
								op=stiva_operatori.pop();
								op.list.add(rez1);
								op.list.add(rez2);
								stiva_rezultat.push(op);
							}
						}
					}
				}
				while(!stiva_operatori.isEmpty())
				{
					rez1=stiva_rezultat.pop();
					rez2=stiva_rezultat.pop();
					op=stiva_operatori.pop();
					op.list.add(rez1);
					op.list.add(rez2);
					stiva_rezultat.push(op);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		in.close();
	}
	/**
	 * Metoda ce stabileste prioritatea intre doi operatori
	 * @param nod1 Primul operator.
	 * @param nod2 Al doilea operator.
	 * @return Intoarce true daca primul este prioritar celui de-al doilea sau false altfel.
	 */
	public boolean prioritate(Node nod1,Node nod2)
	{
		if(nod1 instanceof OperatorInmultire)
			return true;
		else if(nod1 instanceof OperatorEgal)
				if(nod2 instanceof OperatorEgal)
					return true;
				else return false;
		else
		{
			if((nod2 instanceof OperatorEgal)||(nod2 instanceof OperatorPlus))
				return true;
			else return false;
		}
	}
	
	/**
	 * Getter pentru stiva_rezultat
	 * @return 
	 */
	public Stack<Node> getStiva_rezultat() {
		return stiva_rezultat;
	}
	/**
	 * Setter pentru stiva_rezultat
	 * @param stiva_rezultat
	 */
	public void setStiva_rezultat(Stack<Node> stiva_rezultat) {
		this.stiva_rezultat = stiva_rezultat;
	}
	/**
	 * Getter pentru stiva_operatori
	 * @return
	 */
	public Stack<Node> getOperator_stack() {
		return stiva_operatori;
	}
	/**
	 * Setter pentru stiva_operatori
	 * @param stiva_operatori
	 */
	public void setStiva_operatori(Stack<Node> stiva_operatori) {
		this.stiva_operatori = stiva_operatori;
	}
	
	
}
