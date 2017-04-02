package farzan_nadeem.connect4_local;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.LinkedList;
import java.util.TimerTask;

/**
 * Created by Nadeem Amin on 2017-03-22.
 */

public class GameLoop extends TimerTask{
    private Activity myActivity;     private Context myContext;     private RelativeLayout relativeLayout;
    private TextView playerDisplay;

    private final int smallestDimenX = 7;       private final int mediumDimenX = 8;     private final int largestDimenX = 10;


    private LinkedList<GameButton> gameButtons = new LinkedList<>();
    public static LinkedList<GamePiece> gamePieces = new LinkedList<>();

    public static boolean[][] smallestGrid;        public static boolean[][] mediumGrid;      public static boolean[][] largestGrid;


    private float tempButtonDispl = -40;       private float tempSecondaryButtonDispl  = -60;   private  float tempTertiaryButtonDispl = -80f;

    public static int columnTriggered = 0;

    private boolean isPlayerOne = true;

    public static boolean resetTriggered = false;

    private boolean madeOnce = true;
    private boolean firstMove;
    private boolean changeText = true;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public GameLoop(Activity activity, Context context, RelativeLayout rL, TextView textView){
        this.myActivity = activity; this.myContext = context;   this.relativeLayout = rL;
        this.playerDisplay = textView;

        switch(MainActivity.boardSize){

            case R.drawable.smallest:

                this.smallestGrid = new boolean[7][6];

                for(int i = 0 ; i < this.smallestDimenX ; i++){
                    this.gameButtons.add(new GameButton(context, rL, tempButtonDispl, i));
                    tempButtonDispl += GameButton.smallestSeperation;

                }
                break;

            case R.drawable.medium:

                this.mediumGrid = new boolean[8][7];

                for(int i = 0 ; i < this.mediumDimenX ; i++){
                    this.gameButtons.add(new GameButton(context, rL, tempSecondaryButtonDispl, i));
                    tempSecondaryButtonDispl += GameButton.mediumSeperation;

                }
                break;

            case R.drawable.largest:

                this.largestGrid = new boolean[10][7];

                for(int i = 0 ; i < this.largestDimenX ; i++){
                    this.gameButtons.add(new GameButton(context, rL, tempTertiaryButtonDispl, i));
                    tempTertiaryButtonDispl += GameButton.largestSeperation;

                }
                break;
        }
        gamePieces.addFirst(new GamePiece(context, relativeLayout));
        firstMove = true;
    } // Creator Function for GameLoop and instantiates all the buttons

    @Override
    public void run() {
        myActivity.runOnUiThread(
                new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
                    @Override
                    public void run() {//Implement the run stuff here

                        if(resetTriggered){
                            clearGrid();
                            createPiece();
                            resetTriggered = false;
                        }

                        if(GamePiece.movementCompleted){    //If the movement has been completed then do this
                            if(!GamePiece.noPossibleMovement) {
                                if (isPlayerOne && changeText) {
                                    playerDisplay.setText("CURRENT PLAYER: " + "PLAYER ONE");
                                    isPlayerOne = false;
                                    changeText = false;
                                }
                                else if(!isPlayerOne && changeText){
                                    playerDisplay.setText("CURRENT PLAYER: " + "PLAYER TWO");
                                    isPlayerOne = true;
                                    changeText = false;
                                }

                                if (madeOnce) {
                                    if (!firstMove) {       //If you were in the first move, then don't do this yet
                                        createPiece();
                                    }
                                    madeOnce = false;
                                }
                                GamePiece.oneTime = true;
                            }


                        }
                        else{                            //If the movement hasn't been completed then do this, this would be triggerd by a button event
                            gamePieces.getLast().move();
                            madeOnce = true;
                            firstMove = false;
                            changeText = true;
                        }

                    }
                }
        );

    }

    private void clearGrid(){

        switch( MainActivity.boardSize) {
            case R.drawable.smallest:
                for(int xAxis = 0; xAxis < smallestDimenX; xAxis++){
                    for(int yAxis = 0; yAxis < 6; yAxis++){
                        smallestGrid[xAxis][yAxis] = false;
                    }
                }
                break;

            case R.drawable.medium:
                for(int xAxis = 0; xAxis < mediumDimenX; xAxis++){
                    for(int yAxis = 0; yAxis < 7; yAxis++){
                        mediumGrid[xAxis][yAxis] = false;
                    }
                }

                break;

            case R.drawable.largest:
                for(int xAxis = 0; xAxis < largestDimenX; xAxis++){
                    for(int yAxis = 0; yAxis < 7; yAxis++){
                        largestGrid[xAxis][yAxis] = false;
                    }
                }

                break;

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void createPiece(){
        gamePieces.add(new GamePiece(myContext, relativeLayout));
    }

}
