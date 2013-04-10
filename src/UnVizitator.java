
/**
 * Aceasta clasa reprezinta un vizitator ce ajuta la parcurgearea arborelui , asfel incat fiecare nod sa fie vizitat de vizitatorii SemanticVizitor si ResultVisitor.
 * Implementeaza clasa Visitor.
 * @author Razvan
 *
 */
public class UnVizitator implements Visitor {

	Visitor visitor;
	/**
	 * Construcorul clasei ce seteaza vizitatorul ce va fi folosit.
	 * @param visitor
	 */
	public UnVizitator(Visitor visitor)
	{
		this.visitor=visitor;
	}
	@Override
	public void visit(Node rad) {
	
		for (Node nod : rad.list)
		{
			//SemanticVisitor.tip=null;
			nod.accept(this);
		}
		rad.accept(visitor);
	}

	@Override
	public void visit(OperatorEgal op) {
		
		SemanticVisitor.tip=null;
		ResultVisitor.valoare_integer=1;
		ResultVisitor.valoare_boolean=true;
		for (Node nod : op.list)
		{
			if((nod instanceof Valoare)||(nod instanceof Variabila))
				continue;
			nod.accept(this);
		}	
		op.accept(visitor);
	}

	@Override
	public void visit(OperatorPlus op) {
		
		for (Node nod : op.list)
		{
			nod.accept(this);
		}	
		op.accept(visitor);
	}

	@Override
	public void visit(OperatorInmultire op) {
		
		for (Node nod : op.list)
		{
			nod.accept(this);
		}	
		op.accept(visitor);
	}

	@Override
	public void visit(Valoare val) {
		
		val.accept(visitor);
	}

	@Override
	public void visit(Variabila var) {
	
		var.accept(visitor);
	}

}
