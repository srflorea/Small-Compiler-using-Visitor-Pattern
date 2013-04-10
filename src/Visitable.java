
/**
 * Interfata ce va fi implementata de clasa Node si astfel de toate clasele copil ale clasei Node.
 * @author Razvan
 *
 */
public interface Visitable {
/**
 * Metoda ce apeleaza apleaza visit din vizitatorul primit csa parametru.
 * @param v
 */
	public void accept(Visitor v);
}
