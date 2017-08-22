package alex.carcar.kenken;

class KenGroups {
	private static final int BOARD_SIZE = 6;
	private int[][] board = new int [BOARD_SIZE][BOARD_SIZE];

	KenGroups() {
		this.clear();
		int group = 1;
		do {
			KenHood kh = new KenHood(this, this.pick());
			if (kh.set(group)) group++;
		} while (this.emptyCount() > 0);
	}

	public int[][] getBoard() {
		return board;
	}

	public void clear() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = 0;
			}
		}
	}

	public int emptyCount() {
		int count = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == 0) count++;
			}
		}
		return count;
	}

	public KenSquare nextEmptySquare() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == 0) {
					KenSquare square = new KenSquare();
					square.x = i;
					square.y = j;
					return square;
				}
			}
		}
		return null;
	}

	public boolean isEmpty(int i, int j) {
		if (i < 0 || j < 0 || i >= BOARD_SIZE || j >= BOARD_SIZE) return false;
		return board[i][j] == 0;
	}
			
	public boolean isEmpty(KenSquare square) {
		return isEmpty(square.x, square.y);
	}

	public boolean set(int i, int j, int group) {
		if (i < 0 || j < 0 || i >= BOARD_SIZE || j >= BOARD_SIZE) return false;
		if (!isEmpty(i, j)) return false;
		board[i][j] = group;
		return true;
	}

	public boolean set(KenSquare square, int group) {
		return set(square.x, square.y, group);
	}

	public void print() {
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				char c = (char) (board[i][j] == 0 ? '-' : 'A' + board[i][j] - 1);
				System.out.print(c);
			}
			System.out.print("\n");
		}
	}

	public KenSquare pick() {
		KenSquare kenSquare = new KenSquare();
		kenSquare.x = (int) Math.floor(Math.random()*BOARD_SIZE);
		kenSquare.y = (int) Math.floor(Math.random()*BOARD_SIZE);
		return kenSquare;
	}
}