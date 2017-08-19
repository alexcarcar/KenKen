package alex.carcar.kenken;

public class KenTest {
	public static void main(String[] args) {
        int group = 1;
        KenGroups kg = new KenGroups();
        do {
            KenHood kh = new KenHood(kg, kg.pick());
            if (kh.set(group)) group++;
        } while (kg.emptyCount() > 0);
        kg.print();
    }
}