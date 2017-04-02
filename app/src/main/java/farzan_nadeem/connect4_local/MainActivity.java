package farzan_nadeem.connect4_local;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    public static int boardSize = R.drawable.smallest;
    public static int playerOne = R.drawable.white;
    public static int playerTwo = R.drawable.black;

    private RelativeLayout relativeLayout;
    public String playerOneStr = "PLAYER ONE";
    public String playerTwoStr = "PLAYER TWO";
    public String currPlay = this.playerOneStr;

    GameLoop gameLoop;      Timer mainTimer;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        this.relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        this.relativeLayout.getLayoutParams().width = 1430;
        this.relativeLayout.getLayoutParams().height = 1800;

        //This will be changed using a if statement, to select which one to set as the background resoure
        relativeLayout.setBackgroundResource(boardSize);

        TextView currentPlayer = new TextView(getApplicationContext());
        currentPlayer.setY(30f);
        currentPlayer.setText("CURRENT PLAYER:   " + this.currPlay);
        currentPlayer.setTextColor(Color.BLACK);
        currentPlayer.setTextSize(25f);
        currentPlayer.setX(40f);
        relativeLayout.addView(currentPlayer);


        Button resetButton = new Button(getApplicationContext());
        resetButton.setText("RESET GAME");
        resetButton.setTextSize(24);
        resetButton.setTextColor(Color.BLACK);
        resetButton.setBackgroundColor(Color.TRANSPARENT);
        resetButton.setX(120f);
        resetButton.setY(200f);
        relativeLayout.addView(resetButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(GameLoop.gamePieces.size()!= 0){

                    for(int i = 0 ; i < GameLoop.gamePieces.size() ; i++) {
                        relativeLayout.removeView(GameLoop.gamePieces.get(i));
                        GameLoop.gamePieces.remove(i);
                    }

                }

                GameLoop.resetTriggered = true;
            }
        });


        gameLoop = new GameLoop(this, getApplicationContext(), relativeLayout, currentPlayer);        //Instantiates the GameLoop

        mainTimer = new Timer();                      //Schedules the gameLoop to be the main timer of the game and everything will run synchronous to it
        mainTimer.schedule(gameLoop, 10, 10);

    }

    private void disengage(){
        gameLoop.cancel();
        mainTimer.cancel();
        mainTimer.purge();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onPause() {
        super.onPause();
        disengage();
        finish();
    }

}