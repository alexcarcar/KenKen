package alex.carcar.kenken;

public class KenTest {
	public static void main(String[] args) {
		KenGroups kenGroups = new KenGroups();
		kenGroups.print();
		System.out.println(kenGroups.isEmpty(0, 0));

		KenSquare kenSquare = kenGroups.pick();
		kenSquare.print();
		System.out.println(kenGroups.isEmpty(kenSquare));

		System.out.println(kenGroups.emptyCount());

		KenSquare next = kenGroups.nextEmptySquare();
		if (next != null) {
			next.print();
		}
	}
}