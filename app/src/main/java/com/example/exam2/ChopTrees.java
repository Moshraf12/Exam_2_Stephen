package com.example.exam2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Timer;

// The ChopTrees activity should have the following...
// When the tree is clicked:
//   play audio [5 marks]
//   rotate the image 90-degrees (make it look like it is falling!) [5 marks]
//           remember... trees don't spin aroun the middle when chopped
// increment and save "chopped trees" counter [5 marks]
// shortly after the tree has fallen and the sound finishes, (timer or trigger) [5 marks]
//      clear the tree,
//      set up a new tree.

public class ChopTrees extends AppCompatActivity {
    private ImageView treeImage;
    private MediaPlayer mediaPlayer;


    private int treeNumber = 0;
    TextView treeTextView;
    private static String privateDirectory="private";
    private static String filename = "treeNumber.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chop_trees);

        // set up the activitiy here!
        // for play sound
        mediaPlayer = MediaPlayer.create(this,R.raw.treefalling);
        mediaPlayer.setLooping(false);
        mediaPlayer.seekTo(0);
        mediaPlayer.setVolume(0.5f, 0.5f);

        // for tree rotation on 90 degree
        treeImage = findViewById(R.id.treeImage);

        // for set up counter
        treeTextView = (TextView)findViewById(R.id.numTreeFallen);
        treeNumber = readIntFormInternal(getApplicationContext());
        TextView treesNumberRead = findViewById(R.id.numTreeFallen);
        treesNumberRead.setText(Integer.toString(treeNumber));


        //
        Button button = (Button) findViewById(R.id.btnReset);
       // button.setOnClickListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                treeTextView.setText("0");
                Toast.makeText(ChopTrees.this, "The counter has been reset",Toast.LENGTH_SHORT).show();

            }
        });

        Button btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });


        }




    public void toggleTreeFalling(View v) {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(this, R.raw.treefalling);


            } else {
                mediaPlayer.start();
                Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tree_rotate);
                treeImage.startAnimation(aniRotate);

                TextView treesNumberEntered = findViewById(R.id.numTreeFallen);
                treeNumber = Integer.parseInt(String.valueOf(treesNumberEntered.getText()));
                writeIntToInternal(getApplicationContext(),treeNumber);

                treeNumber++;
                treeTextView.setText(Integer.toString(treeNumber));


            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    // need any helper functions? put them here


    public void writeIntToInternal(Context myContext, int value){
        File dir = new File(myContext.getFilesDir(), privateDirectory);
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            File outputFile = new File(dir, filename);
            FileWriter writer = new FileWriter(outputFile);

            writer.write(Integer.toString(value));
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int readIntFormInternal(Context myContext){
        int retval = 0;
        File dir = new File(myContext.getFilesDir(), privateDirectory);
        if(!dir.exists()){
            retval = 0;
        }else {
            try {
                File inputFile = new File(dir,filename);
                FileInputStream fis = new FileInputStream(inputFile);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);

                retval = Integer.parseInt(bufferedReader.readLine());

                bufferedReader.close();
                isr.close();
                fis.close();

            }catch (Exception e){
                e.printStackTrace();

            }
        }
        return retval;
    }




}