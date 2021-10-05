package com.example.tictactoe;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //Store the current turn of player
    Boolean firstPlayerTurn = true;
    //Store the values of filled grid
    int[] currStatus = new int[10];
    //Store the game condition if game is still running or completed
    boolean gameStatus = true;
    //Total number of grid filled at any time
    int filled = 0;

    //On any player turn
    public void dropIn(View view) {
        ImageView imgView = (ImageView) view;
        // get tag position at which the new turn is to be added
        int pos = parseInt(imgView.getTag().toString());
        if (firstPlayerTurn && gameStatus && currStatus[pos] == 0) {
            imgView.setImageResource(R.drawable.x_image);
            firstPlayerTurn = false;
            currStatus[pos] = 1;
            filled++;
        } else if (!firstPlayerTurn && gameStatus && currStatus[pos] == 0) {
            imgView.setImageResource(R.drawable.o_image);
            firstPlayerTurn = true;
            currStatus[pos] = 2;
            filled++;
        }
        status();
    }

    //checking the status after current turn
    private void status() {
        boolean status = WinningCondition.check(currStatus);
        String result = "";
        if (status) {
            gameStatus = false;
            if (!firstPlayerTurn) {
                result = "first player won!";
            } else {
                result = "second player won!";
            }
        } else if (filled == 9) {
            result = "Match draw";
        }
        if (status || filled == 9) {
            TextView textView = findViewById(R.id.result);
            textView.setText(result);
            Button button = findViewById(R.id.button);
            textView.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        }
    }

    // When reset button is clicked
    public void reset(View view) {
        TextView textView = findViewById(R.id.result);
        Button button = findViewById(R.id.button);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        GridLayout grid = findViewById(R.id.gridlayout);
        for (int i = 0; i < grid.getChildCount(); i++) {
            ImageView img = (ImageView) grid.getChildAt(i);
            img.setImageDrawable(null);
        }
        firstPlayerTurn = true;
        Arrays.fill(currStatus, 0);
        gameStatus = true;
        filled = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}