package alex.carcar.kenken;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int[][] rightCells = new int[][]{
            {R.id.rightCell00, R.id.rightCell01, R.id.rightCell02, R.id.rightCell03, R.id.rightCell04},
            {R.id.rightCell10, R.id.rightCell11, R.id.rightCell12, R.id.rightCell13, R.id.rightCell14},
            {R.id.rightCell20, R.id.rightCell21, R.id.rightCell22, R.id.rightCell23, R.id.rightCell24},
            {R.id.rightCell30, R.id.rightCell31, R.id.rightCell32, R.id.rightCell33, R.id.rightCell34},
            {R.id.rightCell40, R.id.rightCell41, R.id.rightCell42, R.id.rightCell43, R.id.rightCell44},
            {R.id.rightCell50, R.id.rightCell51, R.id.rightCell52, R.id.rightCell53, R.id.rightCell54}
    };

    private static final int[][] rightHeaders = new int[][]{
            {R.id.rightHeader00, R.id.rightHeader01, R.id.rightHeader02, R.id.rightHeader03, R.id.rightHeader04},
            {R.id.rightHeader10, R.id.rightHeader11, R.id.rightHeader12, R.id.rightHeader13, R.id.rightHeader14},
            {R.id.rightHeader20, R.id.rightHeader21, R.id.rightHeader22, R.id.rightHeader23, R.id.rightHeader24},
            {R.id.rightHeader30, R.id.rightHeader31, R.id.rightHeader32, R.id.rightHeader33, R.id.rightHeader34},
            {R.id.rightHeader40, R.id.rightHeader41, R.id.rightHeader42, R.id.rightHeader43, R.id.rightHeader44},
            {R.id.rightHeader50, R.id.rightHeader51, R.id.rightHeader52, R.id.rightHeader53, R.id.rightHeader54}
    };

    private static final int[][] headers = new int[][]{
            {R.id.header00, R.id.header01, R.id.header02, R.id.header03, R.id.header04, R.id.header05},
            {R.id.header10, R.id.header11, R.id.header12, R.id.header13, R.id.header14, R.id.header15},
            {R.id.header20, R.id.header21, R.id.header22, R.id.header23, R.id.header24, R.id.header25},
            {R.id.header30, R.id.header31, R.id.header32, R.id.header33, R.id.header34, R.id.header35},
            {R.id.header40, R.id.header41, R.id.header42, R.id.header43, R.id.header44, R.id.header45},
            {R.id.header50, R.id.header51, R.id.header52, R.id.header53, R.id.header54, R.id.header55}
    };

    private static final int[][] footers = new int[][]{
            {R.id.footer00, R.id.footer01, R.id.footer02, R.id.footer03, R.id.footer04, R.id.footer05},
            {R.id.footer10, R.id.footer11, R.id.footer12, R.id.footer13, R.id.footer14, R.id.footer15},
            {R.id.footer20, R.id.footer21, R.id.footer22, R.id.footer23, R.id.footer24, R.id.footer25},
            {R.id.footer30, R.id.footer31, R.id.footer32, R.id.footer33, R.id.footer34, R.id.footer35},
            {R.id.footer40, R.id.footer41, R.id.footer42, R.id.footer43, R.id.footer44, R.id.footer45}
    };

    private static final int[][] rightFooters = new int[][]{
            {R.id.rightFooter00, R.id.rightFooter01, R.id.rightFooter02, R.id.rightFooter03, R.id.rightFooter04},
            {R.id.rightFooter10, R.id.rightFooter11, R.id.rightFooter12, R.id.rightFooter13, R.id.rightFooter14},
            {R.id.rightFooter20, R.id.rightFooter21, R.id.rightFooter22, R.id.rightFooter23, R.id.rightFooter24},
            {R.id.rightFooter30, R.id.rightFooter31, R.id.rightFooter32, R.id.rightFooter33, R.id.rightFooter34},
            {R.id.rightFooter40, R.id.rightFooter41, R.id.rightFooter42, R.id.rightFooter43, R.id.rightFooter44}
    };

    private static int selected = -1;
    private static int cellX = -1;
    private static int cellY = -1;
    private static boolean useLetters = false;
    private MediaPlayer FXPlayer;
    private LinearLayout successLayout;
    private LinearLayout numberPad;
    private LinearLayout incompleteLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNewGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void playSound(int _id) {
        try {
            if (FXPlayer != null) {
                FXPlayer.stop();
                FXPlayer.release();
            }
            FXPlayer = MediaPlayer.create(this, _id);
            if (FXPlayer != null)
                FXPlayer.start();
        } catch (Exception e) {
            System.err.println("playSound: " + e);
        }
    }

    public void onCellTouch(View a) {
        if (selected == -1) return;
        Button currentCell = (Button) a;
        getCellCoordinates(currentCell.getId());
        if (!Sudoku.isHidden(cellX, cellY)) return;

        switch (selected) {
            case 1:
                if (useLetters) {
                    currentCell.setText(R.string.number1l);
                } else {
                    currentCell.setText(R.string.number1);
                }
                break;
            case 2:
                if (useLetters) {
                    currentCell.setText(R.string.number2l);
                } else {
                    currentCell.setText(R.string.number2);
                }
                break;
            case 3:
                if (useLetters) {
                    currentCell.setText(R.string.number3l);
                } else {
                    currentCell.setText(R.string.number3);
                }
                break;
            case 4:
                if (useLetters) {
                    currentCell.setText(R.string.number4l);
                } else {
                    currentCell.setText(R.string.number4);
                }
                break;
            case 5:
                if (useLetters) {
                    currentCell.setText(R.string.number5l);
                } else {
                    currentCell.setText(R.string.number5);
                }
                break;
            case 6:
                if (useLetters) {
                    currentCell.setText(R.string.number6l);
                } else {
                    currentCell.setText(R.string.number6);
                }
                break;
            case 0:
                currentCell.setText(R.string.blank_cell);
                break;
        }
        currentCell.setTextColor(ContextCompat.getColor(this, R.color.text_color));
        checkBoard(cellX, cellY, selected);
    }

    private void checkBoard(int x, int y, int value) {
        Sudoku.put(x, y, value);
        if (Sudoku.complete()) {
            if (Sudoku.win()) {
                incompleteLayout.setVisibility(View.INVISIBLE);
                successLayout.setVisibility(View.VISIBLE);
                numberPad.setVisibility(View.INVISIBLE);
                playSound(R.raw.clapping);
            } else {
                incompleteLayout.setVisibility(View.VISIBLE);
                playSound(R.raw.buzzer);
            }
        } else {
            successLayout.setVisibility(View.INVISIBLE);
            numberPad.setVisibility(View.VISIBLE);
        }
    }

    private void getCellCoordinates(int id) {
        switch (id) {
            case R.id.cell00:
                cellX = 0;
                cellY = 0;
                break;
            case R.id.cell01:
                cellX = 0;
                cellY = 1;
                break;
            case R.id.cell02:
                cellX = 0;
                cellY = 2;
                break;
            case R.id.cell03:
                cellX = 0;
                cellY = 3;
                break;
            case R.id.cell04:
                cellX = 0;
                cellY = 4;
                break;
            case R.id.cell05:
                cellX = 0;
                cellY = 5;
                break;
            case R.id.cell10:
                cellX = 1;
                cellY = 0;
                break;
            case R.id.cell11:
                cellX = 1;
                cellY = 1;
                break;
            case R.id.cell12:
                cellX = 1;
                cellY = 2;
                break;
            case R.id.cell13:
                cellX = 1;
                cellY = 3;
                break;
            case R.id.cell14:
                cellX = 1;
                cellY = 4;
                break;
            case R.id.cell15:
                cellX = 1;
                cellY = 5;
                break;
            case R.id.cell20:
                cellX = 2;
                cellY = 0;
                break;
            case R.id.cell21:
                cellX = 2;
                cellY = 1;
                break;
            case R.id.cell22:
                cellX = 2;
                cellY = 2;
                break;
            case R.id.cell23:
                cellX = 2;
                cellY = 3;
                break;
            case R.id.cell24:
                cellX = 2;
                cellY = 4;
                break;
            case R.id.cell25:
                cellX = 2;
                cellY = 5;
                break;
            case R.id.cell30:
                cellX = 3;
                cellY = 0;
                break;
            case R.id.cell31:
                cellX = 3;
                cellY = 1;
                break;
            case R.id.cell32:
                cellX = 3;
                cellY = 2;
                break;
            case R.id.cell33:
                cellX = 3;
                cellY = 3;
                break;
            case R.id.cell34:
                cellX = 3;
                cellY = 4;
                break;
            case R.id.cell35:
                cellX = 3;
                cellY = 5;
                break;
            case R.id.cell40:
                cellX = 4;
                cellY = 0;
                break;
            case R.id.cell41:
                cellX = 4;
                cellY = 1;
                break;
            case R.id.cell42:
                cellX = 4;
                cellY = 2;
                break;
            case R.id.cell43:
                cellX = 4;
                cellY = 3;
                break;
            case R.id.cell44:
                cellX = 4;
                cellY = 4;
                break;
            case R.id.cell45:
                cellX = 4;
                cellY = 5;
                break;
            case R.id.cell50:
                cellX = 5;
                cellY = 0;
                break;
            case R.id.cell51:
                cellX = 5;
                cellY = 1;
                break;
            case R.id.cell52:
                cellX = 5;
                cellY = 2;
                break;
            case R.id.cell53:
                cellX = 5;
                cellY = 3;
                break;
            case R.id.cell54:
                cellX = 5;
                cellY = 4;
                break;
            case R.id.cell55:
                cellX = 5;
                cellY = 5;
                break;
        }
    }

    private void unhighlightLast() {
        if (selected != -1) {
            int lastId = 0;
            switch (selected) {
                case 1:
                    lastId = R.id.choose1;
                    break;
                case 2:
                    lastId = R.id.choose2;
                    break;
                case 3:
                    lastId = R.id.choose3;
                    break;
                case 4:
                    lastId = R.id.choose4;
                    break;
                case 5:
                    lastId = R.id.choose5;
                    break;
                case 6:
                    lastId = R.id.choose6;
                    break;
                case 0:
                    lastId = R.id.choose_delete;
                    break;
            }
            Button lastButton = (Button) findViewById(lastId);
            lastButton.setBackgroundResource(R.drawable.off_button);
        }
    }

    public void onChoice(View a) {
        unhighlightLast();
        if (a.getId() == R.id.choose1) selected = 1;
        if (a.getId() == R.id.choose2) selected = 2;
        if (a.getId() == R.id.choose3) selected = 3;
        if (a.getId() == R.id.choose4) selected = 4;
        if (a.getId() == R.id.choose5) selected = 5;
        if (a.getId() == R.id.choose6) selected = 6;
        if (a.getId() == R.id.choose_delete) selected = 0;
        a.setBackgroundResource(R.drawable.on_button);
    }

    public void onAboutClick(MenuItem item) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void onNewGameClick(MenuItem item) {
        createNewGame();
    }

    public void onNewClick(View view) {
        createNewGame();
    }

    public void createNewGame() {
        setContentView(R.layout.activity_main);
        selected = -1;
        Sudoku.create();
        Sudoku.hide(2);
        displayBoard(Sudoku.board, Sudoku.hide);
        displayKenBoarders(Sudoku.ken);
        displayKenHeaders(Sudoku.kenHeaders);
        successLayout = (LinearLayout) findViewById(R.id.successLayout);
        numberPad = (LinearLayout) findViewById(R.id.numberPad);
        incompleteLayout = (LinearLayout) findViewById(R.id.puzzleIncomplete);

        successLayout.setVisibility(View.INVISIBLE);
        numberPad.setVisibility(View.VISIBLE);
        incompleteLayout.setVisibility(View.INVISIBLE);
    }

    private void displayKenHeaders(String[][] kenHeaders) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                TextView headerLabel = (TextView) findViewById(headers[i][j]);
                headerLabel.setText(kenHeaders[i][j]);
            }
        }
    }

    private void displayKenBoarders(int[][] ken) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                boolean isMinorColor = ken[i][j] == ken[i][j + 1];
                colorCell(findViewById(rightHeaders[i][j]), isMinorColor);
                colorCell(findViewById(rightCells[i][j]), isMinorColor);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                boolean isMinorColor = ken[i + 1][j] == ken[i][j];
                colorCell(findViewById(footers[i][j]), isMinorColor);
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boolean isMinorColor = ken[i][j] == ken[i][j + 1] && ken[i][j] == ken[i + 1][j] && ken[i][j] == ken[i + 1][j + 1];
                colorCell(findViewById(rightFooters[i][j]), isMinorColor);
            }
        }
    }

    private void colorCell(View viewById, boolean isMinorColor) {
        viewById.setBackgroundColor(ContextCompat.getColor(this, isMinorColor ? R.color.minorDivider : R.color.mainDivider));
    }

    private void displayBoard(int[][] board, boolean[][] hide) {
        displayCell(findViewById(R.id.cell00), board[0][0], hide[0][0]);
        displayCell(findViewById(R.id.cell01), board[0][1], hide[0][1]);
        displayCell(findViewById(R.id.cell02), board[0][2], hide[0][2]);
        displayCell(findViewById(R.id.cell03), board[0][3], hide[0][3]);
        displayCell(findViewById(R.id.cell04), board[0][4], hide[0][4]);
        displayCell(findViewById(R.id.cell05), board[0][5], hide[0][5]);
        displayCell(findViewById(R.id.cell10), board[1][0], hide[1][0]);
        displayCell(findViewById(R.id.cell11), board[1][1], hide[1][1]);
        displayCell(findViewById(R.id.cell12), board[1][2], hide[1][2]);
        displayCell(findViewById(R.id.cell13), board[1][3], hide[1][3]);
        displayCell(findViewById(R.id.cell14), board[1][4], hide[1][4]);
        displayCell(findViewById(R.id.cell15), board[1][5], hide[1][5]);
        displayCell(findViewById(R.id.cell20), board[2][0], hide[2][0]);
        displayCell(findViewById(R.id.cell21), board[2][1], hide[2][1]);
        displayCell(findViewById(R.id.cell22), board[2][2], hide[2][2]);
        displayCell(findViewById(R.id.cell23), board[2][3], hide[2][3]);
        displayCell(findViewById(R.id.cell24), board[2][4], hide[2][4]);
        displayCell(findViewById(R.id.cell25), board[2][5], hide[2][5]);
        displayCell(findViewById(R.id.cell30), board[3][0], hide[3][0]);
        displayCell(findViewById(R.id.cell31), board[3][1], hide[3][1]);
        displayCell(findViewById(R.id.cell32), board[3][2], hide[3][2]);
        displayCell(findViewById(R.id.cell33), board[3][3], hide[3][3]);
        displayCell(findViewById(R.id.cell34), board[3][4], hide[3][4]);
        displayCell(findViewById(R.id.cell35), board[3][5], hide[3][5]);
        displayCell(findViewById(R.id.cell40), board[4][0], hide[4][0]);
        displayCell(findViewById(R.id.cell41), board[4][1], hide[4][1]);
        displayCell(findViewById(R.id.cell42), board[4][2], hide[4][2]);
        displayCell(findViewById(R.id.cell43), board[4][3], hide[4][3]);
        displayCell(findViewById(R.id.cell44), board[4][4], hide[4][4]);
        displayCell(findViewById(R.id.cell45), board[4][5], hide[4][5]);
        displayCell(findViewById(R.id.cell50), board[5][0], hide[5][0]);
        displayCell(findViewById(R.id.cell51), board[5][1], hide[5][1]);
        displayCell(findViewById(R.id.cell52), board[5][2], hide[5][2]);
        displayCell(findViewById(R.id.cell53), board[5][3], hide[5][3]);
        displayCell(findViewById(R.id.cell54), board[5][4], hide[5][4]);
        displayCell(findViewById(R.id.cell55), board[5][5], hide[5][5]);
        displayCell(findViewById(R.id.choose1), 1, false);
        displayCell(findViewById(R.id.choose2), 2, false);
        displayCell(findViewById(R.id.choose3), 3, false);
        displayCell(findViewById(R.id.choose4), 4, false);
        displayCell(findViewById(R.id.choose5), 5, false);
        displayCell(findViewById(R.id.choose6), 6, false);
    }

    private void displayCell(View v, int x, boolean hide) {
        Button currentCell = (Button) v;
        if (hide) {
            currentCell.setText(R.string.blank_cell);
        } else {
            switch (x) {
                case 1:
                    if (useLetters) {
                        currentCell.setText(R.string.number1l);
                    } else {
                        currentCell.setText(R.string.number1);
                    }
                    break;
                case 2:
                    if (useLetters) {
                        currentCell.setText(R.string.number2l);
                    } else {
                        currentCell.setText(R.string.number2);
                    }
                    break;
                case 3:
                    if (useLetters) {
                        currentCell.setText(R.string.number3l);
                    } else {
                        currentCell.setText(R.string.number3);
                    }
                    break;
                case 4:
                    if (useLetters) {
                        currentCell.setText(R.string.number4l);
                    } else {
                        currentCell.setText(R.string.number4);
                    }
                    break;
                case 5:
                    if (useLetters) {
                        currentCell.setText(R.string.number5l);
                    } else {
                        currentCell.setText(R.string.number5);
                    }
                    break;
                case 6:
                    if (useLetters) {
                        currentCell.setText(R.string.number6l);
                    } else {
                        currentCell.setText(R.string.number6);
                    }
                    break;
            }
            currentCell.setTypeface(null, Typeface.BOLD);
        }
    }

    /*
    public void onSwitchSymbolsClick(MenuItem item) {
        useLetters = !useLetters; // toggle between numbers and letters
        if (useLetters) {
            item.setIcon(R.drawable.ic_action_numbers);
        } else {
            item.setIcon(R.drawable.ic_action_letters);
        }
        createNewGame();
    }*/
}
