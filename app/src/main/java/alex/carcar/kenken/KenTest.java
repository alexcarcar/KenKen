package alex.carcar.kenken;

public class KenTest {
	public static void main(String[] args) {
            KenGroups kg = new KenGroups();
            KenSquare ks = kg.pick();
            kg.set(ks, 1);
            ks = kg.pick();
            kg.set(ks, 2);
            kg.print();
    }
}