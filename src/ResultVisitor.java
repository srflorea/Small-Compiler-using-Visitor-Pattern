import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class ResultVisitor implements Visitor {

	Map<String,String> noduri=new TreeMap<String,String>();
	static boolean valoare_boolean=true;
	static int valoare_integer=1;
	int ok=0;
	@Override
	public void visit(Node nod) {

	}

	@Override
	public void visit(OperatorEgal op) {

		if(op.list.get(0) instanceof Valoare)
			noduri.put(op.list.get(1).nume,op.list.get(0).nume); 
		else if(op.list.get(0) instanceof Variabila)
			noduri.put(op.list.get(1).nume, noduri.get(op.list.get(0).nume));
		else {
			if(ok==0)
				noduri.put(op.list.get(1).nume, Integer.toString(valoare_integer));
			else{
				String str=new Boolean(valoare_boolean).toString();
				noduri.put(op.list.get(1).nume, str);
				ok=0;
			}
		}
	}

	@Override
	public void visit(OperatorPlus op) {
		boolean value;
		if(valoare_integer==1)
			valoare_integer=0;
		if(op.list.get(0) instanceof Variabila)
			if(Character.isDigit(noduri.get(op.list.get(0).nume).charAt(0)))
				valoare_integer+=Integer.parseInt(noduri.get(op.list.get(0).nume));
			else{
				ok=1;
				if(noduri.get(op.list.get(0).nume).equals("false"))
					value=false;
				else value=true;
				valoare_boolean=valoare_boolean||value;
				}
		else if(op.list.get(0) instanceof Valoare){
			if(Character.isDigit((op.list.get(0).nume).charAt(0)))
				valoare_integer+=Integer.parseInt((op.list.get(0).nume));
			else{
				ok=1;
				if((op.list.get(0).nume).equals("false"))
					value=false;
				else value=true;
				valoare_boolean=valoare_boolean||value;
				}	
			}
		if(op.list.get(1) instanceof Variabila)
			if(Character.isDigit(noduri.get(op.list.get(1).nume).charAt(0)))
				valoare_integer+=Integer.parseInt(noduri.get(op.list.get(1).nume));
			else{
				ok=1;
				if(noduri.get(op.list.get(1).nume).equals("false"))
					value=false;
				else value=true;
				valoare_boolean=valoare_boolean||value;
				}
		else if(op.list.get(1) instanceof Valoare){
			if(Character.isDigit((op.list.get(1).nume).charAt(0)))
				valoare_integer+=Integer.parseInt((op.list.get(1).nume));
			else{
				ok=1;
				if((op.list.get(1).nume).equals("false"))
					value=false;
				else value=true;
				valoare_boolean=valoare_boolean||value;
				}	
			}
			
	}

	@Override
	public void visit(OperatorInmultire op) {
	
		boolean value;
		if(op.list.get(0) instanceof Variabila)
				if(Character.isDigit(noduri.get(op.list.get(0).nume).charAt(0)))
					valoare_integer*=Integer.parseInt(noduri.get(op.list.get(0).nume));
				else{
					ok=1;
					if(noduri.get(op.list.get(0).nume).equals("false"))
						value=false;
					else value=true;
					valoare_boolean=valoare_boolean&&value;
					}
			else if(op.list.get(0) instanceof Valoare){
				if(Character.isDigit(noduri.get(op.list.get(0).nume).charAt(0)))
					valoare_integer*=Integer.parseInt(op.list.get(0).nume);
				else{
					ok=1;
					if((op.list.get(0).nume).equals("false"))
						value=false;
					else value=true;
					valoare_boolean=valoare_boolean&&value;
					}	
				}
			if(op.list.get(1) instanceof Variabila)
				if(Character.isDigit(noduri.get(op.list.get(1).nume).charAt(0)))
					valoare_integer*=Integer.parseInt(noduri.get(op.list.get(1).nume));
				else{
					ok=1;
					if(noduri.get(op.list.get(1).nume).equals("false"))
						value=false;
					else value=true;
					valoare_boolean=valoare_boolean&&value;
					}
			else if(op.list.get(1) instanceof Valoare){
				if(Character.isDigit((op.list.get(1).nume).charAt(0)))
					valoare_integer*=Integer.parseInt(op.list.get(1).nume);
				else{
					ok=1;
					if((op.list.get(1).nume).equals("false"))
						value=false;
					else value=true;
					valoare_boolean=valoare_boolean&&value;
					}	
				}
		}

	@Override
	public void visit(Valoare val) {

	}

	@Override
	public void visit(Variabila var) {

	}
	/**
	 * Metoda ce face afisarea la adresa primita ca parametru.
	 * @param out
	 */
	public void afis(BufferedWriter out)
	{
		Iterator<String> hashIterator = noduri.keySet().iterator();
		while(hashIterator.hasNext()) {
		String variable=hashIterator.next();
		 try {
			out.write(variable+" = ");
			out.write(noduri.get(variable));
			 out.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}
