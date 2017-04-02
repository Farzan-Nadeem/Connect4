package farzan_nadeem.connect4_local;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by Nadeem Amin on 2017-03-22.
 */

public class GamePiece extends ImageView {
    private final int smallestDimenX = 7;       private final int mediumDimenX = 8;     private final int largestDimenX = 10;
    private final int smallestDimenY = 6;       private final int mediumDimenY = 7;     private final int largestDimenY = 7;

    private static boolean isPlayerOne = true;      //Static so that the changes are global


    private float smallestMinX = 33f;                  private float mediumMinX = 18f;                private float largestMinX = -8f;
   // private float smallestMaxX = 1189f;               private float mediumMaxX = 1189f;               private float largestMaxX = 1230f;

    private float smallestMaxY = 1560f;               private float mediumMaxY = 1574f;               private float largestMaxY = 1517f;

    private float smallestScaling = 0.80f;            private float mediumScaling = 0.75f;            private float largestScalingX = 0.63f;
    private float smallestSpawnY = 400f;              private float spawnY = 350f;                    private float largestScalingY = 0.75f;

    private float smallestSlotSeparationY = 183f;     private float mediumSlotSeparationY = 167f;     private float largestSlotSeparationY = 167f;
    private float smallestSlotSeparationX = 192f;     private float mediumSlotSeperationX = 170f;     private float largestSlotSeparationX = 138f;

    public static boolean movementCompleted = true;

    Random random = new Random();

    private int coloum = 0;
    private float targetCoordY = 0;

    private float velocity = 0;     private final float ACCELERATION = 3f;
    private float myCoordY;

    public static boolean oneTime = true;
    public static boolean noPossibleMovement = false;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public GamePiece(Context context, RelativeLayout relLay) {
        super(context);

        if(isPlayerOne) {                                                   //Sets the image resource according to the
            this.setImageResource(MainActivity.playerOne);
            isPlayerOne = false;
        }
        else{
            this.setImageResource(MainActivity.playerTwo);
            isPlayerOne = true;
        }


        if(MainActivity.boardSize == R.drawable.smallest){
            this.setY(smallestSpawnY);
        }
        else{
            this.setY(spawnY);
        }


        switch (MainActivity.boardSize){
            case R.drawable.smallest:
                this.setScaleX(smallestScaling);
                this.setScaleY(smallestScaling);

                this.setX( smallestMinX +  ( random.nextInt(smallestDimenX) * smallestSlotSeparationX) );

                this.myCoordY = smallestSpawnY;

                break;

            case R.drawable.medium:
                this.setScaleX(mediumScaling);
                this.setScaleY(mediumScaling);

                this.setX( mediumMinX + ( random.nextInt(mediumDimenX) * mediumSlotSeperationX) );

                this.myCoordY = this.spawnY;

                break;

            case R.drawable.largest:
                this.setScaleX(largestScalingX);
                this.setScaleY(largestScalingY);

                this.setX( largestMinX + ( random.nextInt(largestDimenX) * largestSlotSeparationX) );

                this.myCoordY = this.spawnY;

                break;
        }

        relLay.addView(this);

    }

    @RequiresApi (api = Build.VERSION_CODES.HONEYCOMB)
    public void move(){

        this.coloum = this.getColumn();
        movementCompleted = false;

        if(oneTime || noPossibleMovement){
            this.setDestination();
            oneTime = false;
        }


        switch (MainActivity.boardSize){
            case R.drawable.smallest:
                this.setX(this.smallestMinX + ( this.coloum * smallestSlotSeparationX) );
                break;

            case R.drawable.medium:
                this.setX(this.mediumMinX + this.coloum * mediumSlotSeperationX);
                break;

            case R.drawable.largest:
                this.setX(this.largestMinX + this.coloum * largestSlotSeparationX);
                break;
        }

        velocity += ACCELERATION;

        if( (myCoordY + velocity) <= this.targetCoordY ) {
            this.myCoordY += velocity;
            this.setY(myCoordY);
        }
        else{
            this.setY(targetCoordY);
            this.velocity = 0;
            this.myCoordY = targetCoordY;
            movementCompleted = true;
        }


    }


    public int getColumn(){
        return GameLoop.columnTriggered;
    }


    public void setDestination (){

        int xAxis = this.getColumn();
        int pieceCount = 0;

        if(noPossibleMovement)
            noPossibleMovement = false;

        switch(MainActivity.boardSize){

            case R.drawable.smallest:

                for(int i = 0; i < smallestDimenY ; i++){
                    if(GameLoop.smallestGrid[xAxis][i]){
                        pieceCount++;
                    }
                }

                if(pieceCount == smallestDimenY ){    //Meaning that if this column is full
                    noPossibleMovement = true;
                    movementCompleted = true;
                    targetCoordY = smallestSpawnY;
                }
                else {
                    this.targetCoordY = smallestMaxY - (pieceCount * smallestSlotSeparationY);

                    GameLoop.smallestGrid[xAxis][smallestDimenY - pieceCount - 1] = true;       //Updates the boolean grid
                }
                break;

            case R.drawable.medium:

                for(int i = 0; i < mediumDimenY ; i++){
                    if(GameLoop.mediumGrid[xAxis][i]){
                        pieceCount++;
                    }
                }

                if(pieceCount == mediumDimenY ) {
                    noPossibleMovement = true;
                }
                else {
                    this.targetCoordY = mediumMaxY - (pieceCount * mediumSlotSeparationY);

                    GameLoop.mediumGrid[xAxis][mediumDimenY - pieceCount - 1] = true;
                }

                break;

            case R.drawable.largest:

                for(int i = 0; i < largestDimenY ; i++){
                    if(GameLoop.largestGrid[xAxis][i]){
                        pieceCount++;
                    }
                }

                if(pieceCount ==  largestDimenY  ) {
                    noPossibleMovement = true;
                }
                else {
                    this.targetCoordY = mediumMaxY - (pieceCount * largestSlotSeparationY);

                    GameLoop.largestGrid[xAxis][largestDimenY - pieceCount - 1] = true;
                }

                break;
        }

    }



}
