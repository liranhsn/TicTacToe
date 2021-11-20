package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    //Declare variables
    private ImageView[][] ClickImage = new ImageView[3][3];
    private TextView textViewPlayer1, textViewPlayer2;
    private int player1Points, player2Points;
    private boolean player1Turn = true;
    private int roundCount;
    ImageView ImageViewPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.main_text_view_p1);
        textViewPlayer2 = findViewById(R.id.main_text_view_p2);

        ImageViewPlayer = findViewById(R.id.main_user_imageV);
        ImageViewPlayer.setImageResource(R.drawable.xplay);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String ClickImageID = "main_click_iV" + i + j;
                int resID = getResources().getIdentifier(ClickImageID, "id", getPackageName());
                ClickImage[i][j] = findViewById(resID);

                ClickImage[i][j].setOnClickListener(this);
            }
        }
    }

    public  void onClick(View v) {

        if (player1Turn) {
            ((ImageView) v).setImageResource(R.drawable.x);
            ((ImageView) v).setTag("X");
            ImageViewPlayer.setImageResource(R.drawable.oplay);
        } else {
            ((ImageView) v).setImageResource(R.drawable.o);
            ((ImageView) v).setTag("O");
            ImageViewPlayer.setImageResource(R.drawable.xplay);
        }
        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }
    private boolean checkForWin() {

        for (int i = 0; i < 3; i++) {
            if (ClickImage[i][0].getTag().equals(ClickImage[i][1].getTag())
                    && ClickImage[i][0].getTag().equals(ClickImage[i][2].getTag())
                    && !ClickImage[i][0].getTag().equals("")) {

                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (ClickImage[0][i].getTag().equals(ClickImage[1][i].getTag())
                    && ClickImage[0][i].getTag().equals(ClickImage[2][i].getTag())
                    && !ClickImage[0][i].getTag().equals("")) {
                return true;
            }
        }

        if (ClickImage[0][0].getTag().equals(ClickImage[1][1].getTag())
                && ClickImage[0][0].getTag().equals(ClickImage[2][2].getTag())
                && !ClickImage[0][0].getTag().equals("")) {
            return true;
        }

        if (ClickImage[0][2].getTag().equals(ClickImage[1][1].getTag())
                && ClickImage[0][2].getTag().equals(ClickImage[2][0].getTag())
                && !ClickImage[0][2].getTag().equals("")) {
            return true;
        }
        return false;
    }

    private void player1Wins() {
        player1Points++;
        ImageViewPlayer.setImageResource(R.drawable.xwin);
        updatePointsText();
        resetBoard();

    }

    private void player2Wins() {
        player2Points++;
        ImageViewPlayer.setImageResource(R.drawable.owin);
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        ImageViewPlayer.setImageResource(R.drawable.nowin);
        resetBoard();
    }

    private void resetGame() {

        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        ImageViewPlayer.setImageResource(R.drawable.xplay);
        resetBoard();

    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        ClickImage[i][j].setImageResource(R.drawable.empty);
                        ClickImage[i][j].setTag("");
                    }
                }

                roundCount = 0;
                player1Turn = true;
                ImageViewPlayer.setImageResource(R.drawable.xplay);

            }
        }, 2000);


    }

}