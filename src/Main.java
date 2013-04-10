import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

/**
 * clasa ce contine functia Main
 * @author Razvan
 *
 */
public class Main {

	/**Aceasta este functia Main in care se vor apela toate metodele ce rezolva cerinta temei.
	 * @param args Reprezinta siruld e caractere ce va reprezenta fisierul din care se vor citi intructiunile
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		Arbore arb=new Arbore();
		arb.constructie_arbore(args[0]);
		Node nod=new Node("r",0,0,null);
		Node nod2,nod3=new Node("r",0,0,null),nod4=new Node("r",0,0,null);
		Stack<Node> stack=new Stack<Node>();
		while(!arb.getStiva_rezultat().isEmpty())
			stack.push(arb.getStiva_rezultat().pop());
		while(!stack.isEmpty())
		{
			nod2=stack.pop();
			nod.list.add(nod2);
			nod3.list.add(nod2);
			nod4.list.add(nod2);
		}
		nod.display(nod,args[0]);
		
		//ex2
		boolean b=false;
		try{
			  FileWriter fstream1 = new FileWriter(args[0]+"_sv");
			  BufferedWriter out1 = new BufferedWriter(fstream1);
		Visitor sv=new SemanticVisitor();
		Visitor uv=new UnVizitator(sv);
		nod3.accept(uv);
		b=((SemanticVisitor)sv).afis(out1);
		out1.close();
		  }catch (Exception e){
		  System.err.println("Error: " + e.getMessage());
		  }
		//ex3
		if(b==true)
		{
			try{
			  FileWriter fstream = new FileWriter(args[0]+"_rv");
			  BufferedWriter out = new BufferedWriter(fstream);
			  Visitor rv=new ResultVisitor();
				Visitor uv2=new UnVizitator(rv);
					nod4.accept(uv2);
				((ResultVisitor)rv).afis(out);
		out.close();
	  }catch (Exception e){
	  System.err.println("Error: " + e.getMessage());
	  }
	}
		
	}

}
