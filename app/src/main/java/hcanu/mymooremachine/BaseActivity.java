package hcanu.mymooremachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import hcanu.mymooremachine.Mealey.MealeyMainActivity;
import hcanu.mymooremachine.Moore.MooreMainActivity;

/**
 * Created by stava on 12.11.2017.
 */

public class BaseActivity extends AppCompatActivity {

    Button moore,mealey;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        moore = (Button) findViewById(R.id.Moore);
        mealey = (Button) findViewById(R.id.Mealey);


        moore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MooreMainActivity.class);
                startActivity(i);
                //finish();
            }
        });

        mealey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MealeyMainActivity.class);
                startActivity(i);
            }
        });
    }
}
