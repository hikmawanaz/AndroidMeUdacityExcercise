package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {
 private int headindex;
    private int bodyindex;
    private int legindex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this,"position"+position,Toast.LENGTH_SHORT).show();
    int bodypartnumber = position/12;

        int listindex = position - 12*bodypartnumber;

        switch(bodypartnumber){
            case 0 :
                headindex = listindex;
                break;
            case 1 :
                bodyindex = listindex;
                break;
            case 2 :
                legindex = listindex;
        }


        Bundle b = new Bundle();

        b.putInt("headIndex", headindex);
        b.putInt("bodyIndex", bodyindex);
        b.putInt("legIndex", legindex);

        final Intent intent = new Intent(this,AndroidMeActivity.class);

        intent.putExtras(b);


        Button button = (Button) findViewById(R.id.button_next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });




    }



}
