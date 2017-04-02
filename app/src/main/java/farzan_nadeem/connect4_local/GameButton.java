package farzan_nadeem.connect4_local;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by Nadeem Amin on 2017-03-24.
 */

public class GameButton extends Button {


    private RelativeLayout relativeLayout;
    private Context context;

    private final float smallestSetY = 400;           private final float mediumSetY = 350;           private final float largestSetY = 350;
    public static final int smallestDimenX = 100;     public static final int mediumDimenX = 100;     public static final int largestDimenX = 100;
    private final int smallestDimenY = 1700;          private final int mediumDimenY = 1700;           private final int largestDimenY = 1700;

    public final static float smallestSeperation = 193f;       public final static float mediumSeperation = 170f;
    public final static float largestSeperation = 138f;

    private int index;


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public GameButton(Context cT, RelativeLayout relLay, float sX, int ind) {
        super(cT);
        this.context = cT;
        this.relativeLayout = relLay;

        this.index = ind;


        this.setX(sX);

        switch (MainActivity.boardSize) {
            case R.drawable.smallest:                   //Case completed
                this.setY(smallestSetY);
                this.setHeight(smallestDimenY);
                this.setWidth(smallestDimenX);

                this.setScaleX(0.5f);
                this.setScaleY(0.75f);
                this.setVisibility(VISIBLE);
                this.setBackgroundColor(Color.TRANSPARENT);

                break;

            case R.drawable.medium:             //Case Completed
                this.setY(mediumSetY);
                this.setHeight(mediumDimenY);
                this.setWidth(mediumDimenX);

                this.setScaleX(0.4f);
                this.setScaleY(0.75f);
                this.setVisibility(VISIBLE);
                this.setBackgroundColor(Color.TRANSPARENT);

                break;

            case R.drawable.largest:                //Case Completed
                this.setY(largestSetY);
                this.setHeight(largestDimenY);
                this.setWidth(largestDimenX);

                this.setScaleX(0.27f);
                this.setScaleY(0.75f);
                this.setVisibility(VISIBLE);
                this.setBackgroundColor(Color.TRANSPARENT);

                break;

        }

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GamePiece.movementCompleted) {
                    setColumn();
                    GamePiece.movementCompleted = false;
                }
            }
        });

        relLay.addView(this);
    }

    public void setColumn(){
        GameLoop.columnTriggered = this.index;
    }


}




