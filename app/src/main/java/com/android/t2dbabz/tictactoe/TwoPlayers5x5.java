/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.t2dbabz.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TwoPlayers5x5 extends AppCompatActivity {

    //Declaring the variables as class variables(fields)
    private boolean turn = false;
    private char table[][] = new char[5][5];
    int count = 0;

    private int scoreX;
    private int scoreO;

    String first = "";
    String second = "";

    private Button setX;
    private Button setO;

    private char firstC;
    private char secondC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_5x5);

        //Casting and referencing views by their Ids
        TableLayout T = findViewById(R.id.tableLayout);
        playAgain(findViewById(R.id.tableLayout));


        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow r = (TableRow) T.getChildAt(y);
                for (int x = 0; x < r.getChildCount(); x++) {
                    View V = r.getChildAt(x);
                    V.setOnClickListener(new playcode(x, y));
                    disableAllButtons();

                }
            }
        }
        //Button set for new game
        Button c = findViewById(R.id.playAgain);
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View n) {
                playAgain(n);
            }

        });

        Button three = findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TwoPlayers.class));
            }
        });

        Button five = findViewById(R.id.five);
        five.setEnabled(false);

        //choose X
        setX = findViewById(R.id.setX);
        setX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first = "X";
                second = "O";

                firstC = 'X';
                secondC = 'O';

                setX.setEnabled(false);
                setO.setEnabled(true);
                enableAllButtons();

            }
        });

        //choose O
        setO = findViewById(R.id.setO);
        setO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first = "O";
                second = "X";

                firstC = 'O';
                secondC = 'X';

                setO.setEnabled(false);
                setX.setEnabled(true);
                enableAllButtons();
            }
        });


    }


    //the class handles the game
    private class playcode implements View.OnClickListener {

        private int x = 0;
        private int y = 0;

        playcode(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View view) {
            if (view instanceof Button) {
                switch ((count % 2)) {
                    case 0:
                        Button B = (Button) view;
                        table[x][y] = firstC;
                        B.setText(first);
                        B.setEnabled(false);
                        turn = !turn;
                        declareWinner();
                        count++;
                        break;
                    case 1:
                        Button C = (Button) view;
                        table[x][y] = secondC;
                        C.setText(second);
                        C.setEnabled(false);
                        turn = !turn;
                        declareWinner();
                        count++;
                        break;
                }

            }
        }
    }

    //method that is triggered when the Play Again
    //button is clicked
    public void playAgain(View view) {
        turn = false;
        table = new char[5][5];
        resetButtons();
    }

    //Resets the game board and not the score board
    private void resetButtons() {
        TableLayout T = findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow Re = (TableRow) T.getChildAt(y);
                for (int x = 0; x < Re.getChildCount(); x++) {
                    if (Re.getChildAt(x) instanceof Button) {
                        Button B = (Button) Re.getChildAt(x);
                        B.setText("");
                        B.setEnabled(true);
                        B.setTextColor(Color.parseColor("#000080"));

                    }
                }
            }
        }
    }

    //Checks if there is a win and it updates it on the screen
    //with a toast message
    //Updates the score board
    private void declareWinner() {

        TextView scoreXTextX = findViewById(R.id.xResult);
        TextView scoreXTextO = findViewById(R.id.oResult);

        //checking for X
        //diagonal '\'
        if (table[0][0] == firstC) {
            if ((table[1][1] == firstC) && (table[3][3] == firstC) && (table[4][4] == firstC) && (table[2][2] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //diagonal '/'
        if (table[4][0] == firstC) {
            if ((table[3][1] == firstC) && (table[1][3] == firstC) && (table[0][4] == firstC) && (table[2][2] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //horizontal1
        if (table[0][0] == firstC) {
            if ((table[1][0] == firstC) && (table[3][0] == firstC) && (table[4][0] == firstC) && (table[2][0] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //horizontal2
        if (table[0][1] == firstC) {
            if ((table[1][1] == firstC) && (table[3][1] == firstC) && (table[4][1] == firstC) && (table[2][1] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //horizontal3
        if (table[0][2] == firstC) {
            if ((table[1][2] == firstC) && (table[3][2] == firstC) && (table[4][2] == firstC) && (table[2][2] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //horizontal4
        if (table[0][3] == firstC) {
            if ((table[1][3] == firstC) && (table[2][3] == firstC) && (table[3][3] == firstC) && (table[4][3] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //horizontal5
        if (table[0][4] == firstC) {
            if ((table[1][4] == firstC) && (table[2][4] == firstC) && (table[3][4] == firstC) && (table[4][4] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //vertical1
        if (table[0][0] == firstC) {
            if ((table[0][1] == firstC) && (table[0][3] == firstC) && (table[0][4] == firstC) && (table[0][2] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //vertical2
        if (table[1][0] == firstC) {
            if ((table[1][1] == firstC) && (table[1][3] == firstC) && (table[1][4] == firstC) && (table[1][2] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //vertical3
        if (table[2][0] == firstC) {
            if ((table[2][1] == firstC) && (table[2][3] == firstC) && (table[2][4] == firstC) && (table[2][2] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //vertical3
        if (table[3][0] == firstC) {
            if ((table[3][1] == firstC) && (table[3][3] == firstC) && (table[3][4] == firstC) && (table[3][2] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //vertical3
        if (table[4][0] == firstC) {
            if ((table[4][1] == firstC) && (table[4][3] == firstC) && (table[4][4] == firstC) && (table[4][2] == firstC)) {
                scoreX++;
                scoreXTextX.setText(" " + scoreX);
                Toast.makeText(getApplicationContext(), "X wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //checking secondC
        //diagonal '\'
        if (table[0][0] == secondC) {
            if ((table[1][1] == secondC) && (table[3][3] == secondC) && (table[4][4] == secondC) && (table[2][2] == secondC)) {
                scoreO++;
                scoreXTextO.setText(" " + scoreO);
                Toast.makeText(getApplicationContext(), "O wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //diagonal '/'
        if (table[4][0] == secondC) {
            if ((table[3][1] == secondC) && (table[1][3] == secondC) && (table[2][2] == secondC) && (table[0][4] == secondC)) {
                scoreO++;
                scoreXTextO.setText(" " + scoreO);
                Toast.makeText(getApplicationContext(), "O wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //horizontal1
        if (table[0][0] == secondC) {
            if ((table[1][0] == secondC) && (table[3][0] == secondC) && (table[4][0] == secondC) && (table[2][0] == secondC)) {
                scoreO++;
                scoreXTextO.setText(" " + scoreO);
                Toast.makeText(getApplicationContext(), "O wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //horizontal2
        if (table[0][1] == secondC) {
            if ((table[1][1] == secondC) && (table[3][1] == secondC) && (table[4][1] == secondC) && (table[2][1] == secondC)) {
                scoreO++;
                scoreXTextO.setText(" " + scoreO);
                Toast.makeText(getApplicationContext(), "O wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //horizontal3
        if (table[0][2] == secondC) {
            if ((table[1][2] == secondC) && (table[3][2] == secondC) && (table[4][2] == secondC) && (table[2][2] == secondC)) {
                scoreO++;
                scoreXTextO.setText(" " + scoreO);
                Toast.makeText(getApplicationContext(), "O wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //vertical1
        if (table[0][0] == secondC) {
            if ((table[0][1] == secondC) && (table[0][3] == secondC) && (table[0][4] == secondC) && (table[0][2] == secondC)) {
                scoreO++;
                scoreXTextO.setText(" " + scoreO);
                Toast.makeText(getApplicationContext(), "O wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //vertical2
        if (table[1][0] == secondC) {
            if ((table[1][1] == secondC) && (table[1][3] == secondC) && (table[1][4] == secondC) && (table[1][2] == secondC)) {
                scoreO++;
                scoreXTextO.setText(" " + scoreO);
                Toast.makeText(getApplicationContext(), "O wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }
        //vertical3
        if (table[2][0] == secondC) {
            if ((table[2][1] == secondC) && (table[2][3] == secondC) && (table[2][4] == secondC) && (table[2][2] == secondC)) {
                scoreO++;
                scoreXTextO.setText(" " + scoreO);
                Toast.makeText(getApplicationContext(), "O wins", Toast.LENGTH_LONG).show();
                disableAllButtons();
            }
        }


    }


    private void disableAllButtons() {
        TableLayout T = findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    if (R.getChildAt(x) instanceof Button) {
                        Button B = (Button) R.getChildAt(x);
                        B.setEnabled(false);
                    }
                }
            }
        }
    }

    private void enableAllButtons() {
        TableLayout T = findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    if (R.getChildAt(x) instanceof Button) {
                        Button B = (Button) R.getChildAt(x);
                        B.setEnabled(true);
                    }
                }
            }
        }
    }

    public void reset(View view) {
        onRestart();
    }

    @Override
    protected void onRestart() {
        this.recreate();
        super.onRestart();
    }


}