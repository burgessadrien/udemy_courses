package com.burgessadrien.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean player = false;
    private int[] gameState = {2,2,2,2,2,2,2,2,2};
    private int[][] winningPositions = {
            {0,1,2},{3,4,5},{6,7,8}, // horizontal wins
            {0,3,6},{1,4,7},{2,5,8}, // vertical wins
            {0,4,8},{2,4,6} // diagonal wins
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private boolean checkWin() {
        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[0]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2   ) {
                return true;
            }
        }
        return false;
    }

    private void showWin() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        TextView winnerMessage = (TextView) findViewById(R.id.playAgainText);
        String winner = Integer.toString( (player) ? 2 : 1);
        winnerMessage.setText("Player " + winner + " is the winner!");
        layout.setVisibility(View.VISIBLE);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1000f);
        int tag = Integer.parseInt(counter.getTag().toString());
        if(gameState[tag] == 2) {
            if (player) {
                counter.setImageResource(R.drawable.yellow);

            } else {
                counter.setImageResource(R.drawable.red);
            }
            counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
            gameState[tag] = (player) ? 1 : 0 ;
            player = !player;
            if (checkWin()) {
                showWin();
            }
        }
    }

    public void playAgain(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        player = false;
        for(int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        layout.setVisibility(View.INVISIBLE);
    }
}
