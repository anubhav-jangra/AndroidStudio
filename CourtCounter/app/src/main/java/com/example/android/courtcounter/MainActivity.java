package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int scoreA = 0;
    int scoreB = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreA);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menumenu){
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItemitem){
//        int id = item.getItemId();
//
//        if(id == R.id.action_settings){
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void threePointsA(View view){
        scoreA += 3;
        displayForTeamA(scoreA);
    }

    public void twoPointsA(View view){
        scoreA += 2;
        displayForTeamA(scoreA);
    }

    public void freeThrowA(View view){
        scoreA += 1;
        displayForTeamA(scoreA);
    }

    public void displayForTeamA(int score){
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void threePointsB(View view){
        scoreB += 3;
        displayForTeamB(scoreB);
    }

    public void twoPointsB(View view){
        scoreB += 2;
        displayForTeamB(scoreB);
    }

    public void freeThrowB(View view){
        scoreB += 1;
        displayForTeamB(scoreB);
    }

    public void displayForTeamB(int score){
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void reset(View view){
        displayForTeamA(0);
        displayForTeamB(0);
    }
}
