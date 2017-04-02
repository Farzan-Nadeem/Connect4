package farzan_nadeem.connect4_local;

import android.content.Intent;
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

public class openingActivity extends AppCompatActivity {

    public void sendMessageToMain (View view){
        Intent startMainActivity = new Intent(this, MainActivity.class);
        startActivity(startMainActivity);
    }

    public void sendMessageToSettings (View view){
        Intent startSettingsActivity = new Intent(this, Settings.class);
        startActivity(startSettingsActivity);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_opening);

        TextView gameTitle = new TextView(getApplicationContext());
        gameTitle.setText("CONNECT 4");
        gameTitle.setTextSize(35f);
        gameTitle.setTextColor(Color.BLACK);
        gameTitle.setY(575f);
        gameTitle.setX(400f);
        relativeLayout.addView(gameTitle);

        ActionBar action = getSupportActionBar();
        action.hide();

    }


}