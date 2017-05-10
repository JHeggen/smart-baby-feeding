package a00907981.comp3717.bcit.ca.tabtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void feedClick(View view){
        Intent intent = new Intent(this, TabActivity.class);
        intent.putExtra("tab", 0);
        startActivity(intent);
    }

    public void recipeClick(View view){
        Intent intent = new Intent(this, TabActivity.class);
        intent.putExtra("tab", 1);
        startActivity(intent);
    }

    public void historyClick(View view){
        Intent intent = new Intent(this, TabActivity.class);
        intent.putExtra("tab", 2);
        startActivity(intent);
    }

    public void clockClick(View view){
        Intent intent = new Intent(this, TabActivity.class);
        intent.putExtra("tab", 3);
        startActivity(intent);
    }
}
