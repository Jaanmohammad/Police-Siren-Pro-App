package com.jksurajpuriya.policesiren;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.jksurajpuriya.policesiren.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    boolean mediaRun1, mediaRun2;
    MediaPlayer mediaPlayer1, mediaPlayer2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Police Siren Pro");


        binding.btnOn1.setOnClickListener(v -> {
            if (mediaRun2==true){
                mediaPlayer2.stop();
                mediaRun2=false;
                binding.btnOn2.setVisibility(View.VISIBLE);
                binding.btnOff2.setVisibility(View.INVISIBLE);
                playMediaOne();
                binding.btnOn1.setVisibility(View.INVISIBLE);
                binding.btnOff1.setVisibility(View.VISIBLE);
            }else if (mediaRun1==false){
                playMediaOne();
                mediaRun1=true;
                binding.btnOn1.setVisibility(View.INVISIBLE);
                binding.btnOff1.setVisibility(View.VISIBLE);
            }
        });

        binding.btnOff1.setOnClickListener(v -> {

            if (mediaRun1==true){
                mediaPlayer1.stop();
                mediaRun1=false;
                binding.btnOff1.setVisibility(View.INVISIBLE);
                binding.btnOn1.setVisibility(View.VISIBLE);

            }

        });


        binding.btnOn2.setOnClickListener(v -> {
            if (mediaRun1==true){
                mediaPlayer1.stop();
                mediaRun1=false;
                binding.btnOn1.setVisibility(View.VISIBLE);
                binding.btnOff1.setVisibility(View.INVISIBLE);
                playMediaTwo();
                binding.btnOn2.setVisibility(View.INVISIBLE);
                binding.btnOff2.setVisibility(View.VISIBLE);
            }else if (mediaRun2==false){
                playMediaTwo();
                mediaRun2=true;
                binding.btnOn2.setVisibility(View.INVISIBLE);
                binding.btnOff2.setVisibility(View.VISIBLE);
            }
        });

        binding.btnOff2.setOnClickListener(v -> {
            if (mediaRun2==true){
                mediaPlayer2.stop();
                mediaRun2=false;
                binding.btnOff2.setVisibility(View.INVISIBLE);
                binding.btnOn2.setVisibility(View.VISIBLE);

            }

        });





    }


    private void playMediaOne() {
        mediaRun1=true;
        mediaPlayer1= MediaPlayer.create(this,R.raw.police_siren);
        mediaPlayer1.start();
        mediaPlayer1.setLooping(true);

    }
    private void playMediaTwo() {
        mediaRun2=true;
        mediaPlayer2=MediaPlayer.create(this,R.raw.police_siren_two);
        mediaPlayer2.start();
        mediaPlayer2.setLooping(true);

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaRun1==true){
            mediaPlayer1.stop();


        }else if (mediaRun2==true){
            mediaPlayer2.stop();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mediaRun1==true){
            binding.btnOff1.setVisibility(View.INVISIBLE);
            binding.btnOn1.setVisibility(View.VISIBLE);
            mediaPlayer1.stop();
            mediaRun1=false;


        }else if (mediaRun2==true){
            mediaPlayer2.stop();
            binding.btnOff2.setVisibility(View.INVISIBLE);
            binding.btnOn2.setVisibility(View.VISIBLE);
            mediaRun2=false;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:

                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT,"Share App");
                    String shareMessage="https://play.google.com/store/apps/details?id=com.jksurajpuriya.policesiren";
                    intent.putExtra(Intent.EXTRA_TEXT,shareMessage);
                    startActivity(Intent.createChooser(intent,"Share by"));
                }catch (Exception e){
                    Toast.makeText(this, "Send to Unable App", Toast.LENGTH_SHORT).show();
                }

                break;
        }


        return super.onOptionsItemSelected(item);

    }

}