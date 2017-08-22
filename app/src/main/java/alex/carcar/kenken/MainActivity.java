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

public class MainActivity extends AppCompatActivity {

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
        //Sudoku.hide(0.55);
        displayBoard(Sudoku.board, Sudoku.hide);
        successLayout = (LinearLayout) findViewById(R.id.successLayout);
        numberPad = (LinearLayout) findViewById(R.id.numberPad);
        incompleteLayout = (LinearLayout) findViewById(R.id.puzzleIncomplete);

        successLayout.setVisibility(View.INVISIBLE);
        numberPad.setVisibility(View.VISIBLE);
        incompleteLayout.setVisibility(View.INVISIBLE);
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

    public void onSwitchSymbolsClick(MenuItem item) {
        useLetters = !useLetters; // toggle between numbers and letters
        if (useLetters) {
            item.setIcon(R.drawable.ic_action_numbers);
        } else {
            item.setIcon(R.drawable.ic_action_letters);
        }
        createNewGame();
    }
}
