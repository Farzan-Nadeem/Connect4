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

public class Settings extends AppCompatActivity {

    private RelativeLayout relativeLayout;

    Button gridOne;      Button white1;      Button white2;
    Button gridTwo;      Button pink1;       Button pink2;
    Button gridThree;    Button purple1;     Button purple2;
                         Button blue1;       Button blue2;
                         Button green1;      Button green2;
                         Button black1;      Button black2;

    private void textViewCreator(TextView textView){
        textView.setTextColor(Color.BLACK);
        textView.setPadding(0,10,0,10);
        this.relativeLayout.addView(textView);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void buttonCreator(Button button, int id, int id2, int id3){
        if(MainActivity.boardSize == id3)
            button.setBackgroundResource(id2);
        else
            button.setBackgroundResource(id);

        relativeLayout.addView(button);

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();

        relativeLayout = (RelativeLayout) findViewById(R.id.activity_settings);


        TextView settingsTitle = new TextView(getApplicationContext());
        textViewCreator(settingsTitle);
        settingsTitle.setText("Settings");
        settingsTitle.setY(-3.0f);
        settingsTitle.setTextSize(30f);


        final TextView gameBoardSizeSelection = new TextView(getApplicationContext());
        textViewCreator(gameBoardSizeSelection);
        gameBoardSizeSelection.setText("Choose a Grid Size\n");
        gameBoardSizeSelection.setTextSize(22f);
        gameBoardSizeSelection.setY(200f);

        TextView sixBySeven = new TextView(getApplicationContext());
        textViewCreator(sixBySeven);
        sixBySeven.setText("( 6 x 7 )");
        sixBySeven.setTextSize(16f);
        sixBySeven.setY(330f);
        sixBySeven.setX(95f);

        TextView sevenByEight = new TextView(getApplicationContext());
        textViewCreator(sevenByEight);
        sevenByEight.setText("( 7 x 8 )");
        sevenByEight.setTextSize(16f);
        sevenByEight.setY(330f);
        sevenByEight.setX(555f);

        TextView sevenByTen = new TextView(getApplicationContext());
        textViewCreator(sevenByTen);
        sevenByTen.setText("( 7 x 10 )");
        sevenByTen.setTextSize(16f);
        sevenByTen.setY(330f);
        sevenByTen.setX(1000f);


        gridOne = new Button(getApplicationContext());
        buttonCreator(gridOne, R.drawable.smallesticon, R.drawable.smallesticonselected, R.drawable.smallest);
        gridOne.setY(400f);

        gridOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.boardSize = R.drawable.smallest;

                if(MainActivity.boardSize == R.drawable.smallest) {
                    gridOne.setBackgroundResource(R.drawable.smallesticonselected);
                    gridTwo.setBackgroundResource(R.drawable.mediumicon);
                    gridThree.setBackgroundResource(R.drawable.largesticon);
                }
                else
                    gridOne.setBackgroundResource(R.drawable.smallesticon);

            }
        });



        gridTwo = new Button(getApplicationContext());      //Button for grid 7 x 8
        buttonCreator(gridTwo, R.drawable.mediumicon, R.drawable.mediumselect, R.drawable.medium);         //Would have a different one
        gridTwo.setY(400f);
        gridTwo.setX(450f);

        gridTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.boardSize = R.drawable.medium;           //Would be middle

                if(MainActivity.boardSize == R.drawable.medium) {
                    gridTwo.setBackgroundResource(R.drawable.mediumselect);
                    gridOne.setBackgroundResource(R.drawable.smallesticon);
                    gridThree.setBackgroundResource(R.drawable.largesticon);
                }
                else
                    gridTwo.setBackgroundResource(R.drawable.mediumicon);


            }
        });

        gridThree = new Button(getApplicationContext());
        buttonCreator(gridThree, R.drawable.largesticon, R.drawable.largesticonselect, R.drawable.largest);     //Woudl be  a different one
        gridThree.setY(400f);
        gridThree.setX(900f);

        gridThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.boardSize = R.drawable.largest;           //Would be Large

                if(MainActivity.boardSize == R.drawable.largest) {
                    gridThree.setBackgroundResource(R.drawable.largesticonselect);
                    gridOne.setBackgroundResource(R.drawable.smallesticon);
                    gridTwo.setBackgroundResource(R.drawable.mediumicon);
                }
                else
                    gridThree.setBackgroundResource(R.drawable.largesticon);


            }
        });


        TextView playerOneIconTitle = new TextView(getApplicationContext());
        textViewCreator(playerOneIconTitle);
        playerOneIconTitle.setText("Player One Icon");
        playerOneIconTitle.setY(850f);
        playerOneIconTitle.setTextSize(22f);

        white1 = new Button(getApplicationContext());   createIcon1(white1,       -70, R.drawable.white, R.drawable.whiteselect);
        pink1  = new Button(getApplicationContext());   createIcon1(pink1,     220-70, R.drawable.pink, R.drawable.pinkselect);
        purple1= new Button(getApplicationContext());   createIcon1(purple1,   440-70, R.drawable.purple, R.drawable.purpleselect);
        blue1  = new Button(getApplicationContext());   createIcon1(blue1,     660-70, R.drawable.blue, R.drawable.blueselect);
        green1 = new Button(getApplicationContext());   createIcon1(green1,    880-70, R.drawable.green, R.drawable.greenselect);
        black1 = new Button(getApplicationContext());   createIcon1(black1,   1100-70, R.drawable.black, R.drawable.blackselect);


        TextView playerTwoIconTitle = new TextView(getApplicationContext());
        textViewCreator(playerTwoIconTitle);
        playerTwoIconTitle.setText("Player Two Icon");
        playerTwoIconTitle.setY(1300f);
        playerTwoIconTitle.setTextSize(22f);

        white2 = new Button(getApplicationContext());   createIcon2(white2,       -70, R.drawable.white, R.drawable.whiteselect);
        pink2  = new Button(getApplicationContext());   createIcon2(pink2,     220-70, R.drawable.pink, R.drawable.pinkselect);
        purple2= new Button(getApplicationContext());   createIcon2(purple2,   440-70, R.drawable.purple, R.drawable.purpleselect);
        blue2  = new Button(getApplicationContext());   createIcon2(blue2,     660-70, R.drawable.blue, R.drawable.blueselect);
        green2 = new Button(getApplicationContext());   createIcon2(green2,    880-70, R.drawable.green, R.drawable.greenselect);
        black2 = new Button(getApplicationContext());   createIcon2(black2,   1100-70, R.drawable.black, R.drawable.blackselect);


    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void createIcon1(final Button button, float x, final int id, final int id2){
        button.setY(950f);
        button.setX(x);
        button.setScaleX(0.6f);

        if(MainActivity.playerOne == id)
            button.setBackgroundResource(id2);
        else
            button.setBackgroundResource(id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id != MainActivity.playerTwo) {
                    MainActivity.playerOne = id;
                    resetAll1();
                    button.setBackgroundResource(id2);
                }

            }
        });

        this.relativeLayout.addView(button);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void createIcon2(final Button button, float x, final int id, final int id2){
        button.setY(1400f);
        button.setX(x);
        button.setScaleX(0.6f);

        if(MainActivity.playerTwo == id)
            button.setBackgroundResource(id2);
        else
            button.setBackgroundResource(id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id != MainActivity.playerOne) {
                    MainActivity.playerTwo = id;
                    resetAll2();
                    button.setBackgroundResource(id2);
                }

            }
        });

        this.relativeLayout.addView(button);
    }

    private void resetAll1(){
        white1.setBackgroundResource(R.drawable.white);
        pink1.setBackgroundResource(R.drawable.pink);
        purple1.setBackgroundResource(R.drawable.purple);
        blue1.setBackgroundResource(R.drawable.blue);
        green1.setBackgroundResource(R.drawable.green);
        black1.setBackgroundResource(R.drawable.black);
    }

    private void resetAll2(){
        white2.setBackgroundResource(R.drawable.white);
        pink2.setBackgroundResource(R.drawable.pink);
        purple2.setBackgroundResource(R.drawable.purple);
        blue2.setBackgroundResource(R.drawable.blue);
        green2.setBackgroundResource(R.drawable.green);
        black2.setBackgroundResource(R.drawable.black);
    }
}
