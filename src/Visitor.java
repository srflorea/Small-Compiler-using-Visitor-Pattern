
/**
 * Interfata ce contine metode visit pentru fiecare clasa ce reprezinta un nod.
 * @author Razvan
 *
 */
public interface Visitor {

	/**
	 * Metoda ce va vizita nodurile de tip Node.
	 * @param nod Parametru ce specifica tipul nodului ce urmeaza ce va fi vizitat.
	 */
	public void visit(Node nod);
	/**
	 * Metoda ce va vizita nodurile de tip OperatorEgal.
	 * @param nod Parametru ce specifica tipul nodului ce urmeaza ce va fi vizitat.
	 */
	public void visit(OperatorEgal op);
	/**
	 * Metoda ce va vizita nodurile de tip OperatorPlus.
	 * @param nod Parametru ce specifica tipul nodului ce urmeaza ce va fi vizitat.
	 */
	public void visit(OperatorPlus op);
	/**
	 * Metoda ce va vizita nodurile de tip OperatorInmultire.
	 * @param nod Parametru ce specifica tipul nodului ce urmeaza ce va fi vizitat.
	 */
	public void visit(OperatorInmultire op);
	/**
	 * Metoda ce va vizita nodurile de tip Valoare.
	 * @param nod Parametru ce specifica tipul nodului ce urmeaza ce va fi vizitat.
	 */
	public void visit(Valoare val);
	/**
	 * Metoda ce va vizita nodurile de tip Variabila.
	 * @param nod Parametru ce specifica tipul nodului ce urmeaza ce va fi vizitat.
	 */
	public void visit(Variabila var);
	
}
