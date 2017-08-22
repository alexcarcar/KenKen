package alex.carcar.kenken;

import java.util.Arrays;

public class KenTest {
	public static void main(String[] args) {
        KenGroups kg = new KenGroups();
        kg.print();

        int[][] board = kg.getBoard();
        System.out.println(Arrays.deepToString(board));
    }
}