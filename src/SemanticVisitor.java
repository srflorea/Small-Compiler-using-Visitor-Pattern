
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SemanticVisitor implements Visitor {

	Map<String,String> noduri=new HashMap<String,String>();
	static String tip=null;
	static int ok=0;
	String[] rezultat=new String[100];
	int i=0;
	@Override
	public void visit(Node nod) {
	
	}

	@Override
	public void visit(OperatorEgal op) {
		Node nod;
		if(!(op.list.get(1) instanceof Variabila))
		{
				rezultat[i]="membrul stang nu este o variabila la linia "+op.linie+" coloana 1";
				i++;
			
			if((op.list.get(0) instanceof Variabila)&&!noduri.containsKey(op.list.get(0).nume))
		{
			rezultat[i]=op.list.get(0).nume+" nedeclarata la linia "+op.list.get(0).linie+" coloana "+op.list.get(0).coloana;
			i++;
		}}
		else if((op.list.get(0) instanceof Variabila)||(op.list.get(0) instanceof Valoare))
			{
				noduri.put(op.list.get(1).nume, op.list.get(0).tip); 
				nod=op.list.get(1);
				nod.tip=op.list.get(0).tip;
				op.list.set(1, nod);
			}
		else {
				if(ok==0&&!tip.equals("naspa"))
					{
						noduri.put(op.list.get(1).nume, tip); 
						//op.list.get(1).tip=tip;
					}
				else ok=0;
		}
	}

	@Override
	public void visit(OperatorPlus op) {
			if(tip.equals("naspa")&&ok!=1)
				{
					rezultat[i]="+ intre tipuri incompatibile la linia "+op.linie+" coloana "+op.coloana;
					i++;
				}
	}

	@Override
	public void visit(OperatorInmultire op) {
		if(tip.equals("naspa"))
			{
				rezultat[i]="* intre tipuri incompatibile la linia "+op.linie+" coloana "+op.coloana;
				i++;
				ok=1;
			}
	}

	@Override
	public void visit(Valoare val) {
		//System.out.println(tip);
		if(tip==null)
			tip=val.tip;
		else
		{
			if(!tip.equals(val.tip))
				tip="naspa";
				
		}
			
	}

	@Override
	public void visit(Variabila var) {
		if(!noduri.containsKey(var.nume))
			{
				rezultat[i]=var.nume+" nedeclarata la linia "+var.linie+" coloana "+var.coloana;
				i++;
				ok=1;
			}
		else	
			if(tip==null)
				tip=noduri.get(var.nume);
			else
			{
				if(!tip.equals(noduri.get(var.nume)))
					tip="naspa";
					
			}
	}
	/**
	 * Metoda ce ace afisarea la adresa primita ca parametru.
	 * @param out
	 * @return
	 */
	public boolean afis(BufferedWriter out)
	{
		if(i==0)
			return true;
		try {
			for(int j=0;j<i;j++)
				{
					out.write(rezultat[j]);
					out.newLine();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
