package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {
    private int headindex;
    private int bodyindex;
    private int legindex;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;

            Button nextButton = (Button) findViewById(R.id.button_next);

            nextButton.setVisibility(View.GONE);

            GridView gridView = (GridView) findViewById(R.id.image_grid);

            gridView.setNumColumns(2);

            if (savedInstanceState == null) {

                // Create a new head BodyPartFragment
                BodyPartFragment headFragment = new BodyPartFragment();

                // Set the list of image id's for the head fragment and set the position to the second image in the list
                headFragment.setImageIds(AndroidImageAssets.getHeads());
                headFragment.setListIndex(headindex);

                // Add the fragment to its container using a FragmentManager and a Transaction
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                // Create and display the body and leg BodyPartFragments

                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIds(AndroidImageAssets.getBodies());
                bodyFragment.setListIndex(bodyindex);
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIds(AndroidImageAssets.getLegs());
                legFragment.setListIndex(legindex);
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit();
            }

        } else {
            mTwoPane = false;
        }


    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "position" + position, Toast.LENGTH_SHORT).show();
        int bodypartnumber = position / 12;

        int listindex = position - 12 * bodypartnumber;
        if (mTwoPane) {

            BodyPartFragment bodyPartFragment = new BodyPartFragment();

            switch (bodypartnumber){
                case 0 :
                    bodyPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setListIndex(listindex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, bodyPartFragment)
                            .commit();
                    break;
                case 1 :
                    bodyPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setListIndex(listindex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, bodyPartFragment)
                            .commit();
                    break;
                case 2 :
                    bodyPartFragment.setImageIds(AndroidImageAssets.getHeads());
                    bodyPartFragment.setListIndex(listindex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, bodyPartFragment)
                            .commit();
                    break;
            }



        } else {


            switch (bodypartnumber) {
                case 0:
                    headindex = listindex;
                    break;
                case 1:
                    bodyindex = listindex;
                    break;
                case 2:
                    legindex = listindex;
            }


            Bundle b = new Bundle();

            b.putInt("headIndex", headindex);
            b.putInt("bodyIndex", bodyindex);
            b.putInt("legIndex", legindex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);

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


}
