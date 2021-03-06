package alex.carcar.kenken;

import java.util.ArrayList;
import java.util.HashSet;

import alex.carcar.kenken.create.KenGroups;

class Sudoku {
    static int board[][];

    static int ken[][];
    static String kenHeaders[][];
    static boolean hide[][];
    private static int BOARD_SIZE;
    private static int GROUP_WIDTH;
    private static int GROUP_HEIGHT;
    private static boolean exclude[];
    private static boolean checker[];

    private static void defineBoard(int size, int width) {
        board = null;
        exclude = null;
        checker = null;
        hide = null;
        BOARD_SIZE = size;
        GROUP_WIDTH = width;
        GROUP_HEIGHT = size / width;
    }

    private static void clearBoard() {
        if (board == null) {
            board = new int[BOARD_SIZE][BOARD_SIZE];
            exclude = new boolean[BOARD_SIZE];
            checker = new boolean[BOARD_SIZE];
            hide = new boolean[BOARD_SIZE][BOARD_SIZE];
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }

    private static void clearChecker() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            checker[i] = false;
        }
    }

    private static boolean isComplete() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!checker[i]) {
                return false;
            }
        }
        return true;
    }

    private static void mark(int value) {
        if (value == 0) return; // square is not set
        checker[value - 1] = true;
    }

    static void hide(double percent) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (Math.random() < percent) {
                    hide[i][j] = true;
                    board[i][j] = 0;
                } else {
                    hide[i][j] = false;
                }
            }
        }
    }

    private static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }

    private static int fillBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int v = fillSquare(i, j);
                if (v == -1) return -1;
                board[i][j] = v;
            }
        }
        return 1;
    }

    private static int fillSquare(int i, int j) {
        int exclusions = fillExcluded(i, j);
        if (exclusions == BOARD_SIZE) {
            return -1; // Stuck, try again
        }
        int n;
        do {
            n = (int) Math.floor(Math.random() * BOARD_SIZE) + 1;
        } while (isExcluded(n));
        return n;
    }

    /* Methods to exclude numbers from selection */
    /* ================================================ */
    private static int fillExcluded(int i, int j) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            exclude[x] = false;
        }
        for (int x = 0; x < BOARD_SIZE; x++) {
            doExclude(board[i][x]);
            doExclude(board[x][j]);
        }
        excludeGroup(i, j);

        int n = 0;
        for (int x = 0; x < BOARD_SIZE; x++) {
            if (exclude[x]) n++;
        }
        return n;
    }

    private static boolean checkGroup(int i, int j) {
        clearChecker();
        int x = (j / GROUP_WIDTH) * GROUP_WIDTH;
        int y = (i / GROUP_HEIGHT) * GROUP_HEIGHT;
        for (int dy = 0; dy < GROUP_HEIGHT; dy++)
            for (int dx = 0; dx < GROUP_WIDTH; dx++)
                mark(board[y + dy][x + dx]);
        return isComplete();
    }

    private static boolean checkGroups() {
        for (int i = 0; i < BOARD_SIZE; i += GROUP_HEIGHT)
            for (int j = 0; j < BOARD_SIZE; j += GROUP_WIDTH)
                if (!checkGroup(i, j)) return false;
        return true;
    }

    private static void excludeGroup(int i, int j) {
        int x = (j / GROUP_WIDTH) * GROUP_WIDTH;
        int y = (i / GROUP_HEIGHT) * GROUP_HEIGHT;
        for (int dy = 0; dy < GROUP_HEIGHT; dy++)
            for (int dx = 0; dx < GROUP_WIDTH; dx++)
                doExclude(board[y + dy][x + dx]);
    }

    private static void doExclude(int n) {
        if (n != 0) exclude[n - 1] = true;
    }

    static boolean isHidden(int x, int y) {
        return hide[x][y];
    }

    private static boolean isExcluded(int n) {
        return exclude[n - 1];
    }

	/* ================================================ */

    static void create() {
        defineBoard(6, 3);
        do {
            clearBoard();
        } while (fillBoard() == -1);
        KenGroups kg = new KenGroups();
        ken = kg.getBoard();
        HashSet<Integer> marker = new HashSet<>();
        if (kenHeaders == null) {
            kenHeaders = new String[6][6];
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                Integer key = ken[i][j];
                if (marker.contains(key)) {
                    kenHeaders[i][j] = "";
                } else {
                    kenHeaders[i][j] = createHeader(key, ken, board);
                    marker.add(key);
                }
            }
        }
        printBoard();
    }

    private static String createHeader(Integer key, int[][] ken, int[][] board) {
        ArrayList<Integer> listOfNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (ken[i][j] == key) {
                    listOfNumbers.add(board[i][j]);
                }
            }
        }

        if (listOfNumbers.size() == 0) return "";
        if (listOfNumbers.size() == 1) return " " + listOfNumbers.get(0).toString();
        if (listOfNumbers.size() == 2) {
            int possibilities = 3; // "+, -, x" are possible
            int a = listOfNumbers.get(0);
            int b = listOfNumbers.get(1);
            if (a % b == 0 || b % a == 0) possibilities++; // Divide (/) is also possible
            switch ((int) Math.floor(Math.random() * possibilities)) {
                case 0:
                    return " x" + (a * b);
                case 1:
                    return " +" + (a + b);
                case 2:
                    return " -" + Math.abs(a - b);
                case 3:
                    return " \u00F7" + (a < b ? (b / a) : (a / b));
            }
        }

        boolean isSum = Math.floor(Math.random() * 2) == 0; // for larger sets, only sum and product possible
        int answer = isSum ? 0 : 1;
        for (int i = 0; i < listOfNumbers.size(); i++) {
            if (isSum) {
                answer += listOfNumbers.get(i);
            } else {
                answer *= listOfNumbers.get(i);
            }
        }
        return " " + (isSum ? "+" : "x") + answer;
    }

    static void put(int x, int y, int value) {
        board[x][y] = value;
    }

    static boolean complete() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean win() {

        // check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            clearChecker();
            for (int j = 0; j < BOARD_SIZE; j++) {
                mark(board[i][j]);
            }
            if (!isComplete()) {
                return false;
            }
        }

        // check columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            clearChecker();
            for (int j = 0; j < BOARD_SIZE; j++) {
                mark(board[j][i]);
            }
            if (!isComplete()) {
                return false;
            }
        }

        // check groups
        return checkGroups();
    }
}
