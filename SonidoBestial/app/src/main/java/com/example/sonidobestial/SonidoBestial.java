package com.example.sonidobestial;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class SonidoBestial extends AppCompatActivity {

    public static int indicadorSonido;
    private ImageView larva1, larva2, larva3, larva4, larva5, larva6, larva7, larva8, larva9, larva10;
    private MediaPlayer sonido;
    private long startTime=3*60*1000;
    private final long interval = 1 * 1000;
    MyCountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonido_bestial);
        hideNavegationBar();
        larva1 = (ImageView)findViewById(R.id.larva_1);
        larva2 = (ImageView)findViewById(R.id.larva_2);
        larva3 = (ImageView)findViewById(R.id.larva_3);
        larva4 = (ImageView)findViewById(R.id.larva_4);
        larva5 = (ImageView)findViewById(R.id.larva_5);
        larva6 = (ImageView)findViewById(R.id.larva_6);
        larva7 = (ImageView)findViewById(R.id.larva_7);
        larva8 = (ImageView)findViewById(R.id.larva_8);
        larva9 = (ImageView)findViewById(R.id.larva_9);
        larva10 = (ImageView)findViewById(R.id.larva_10);
        countDownTimer = new MyCountDownTimer(startTime, interval);

        /*

        for(final Integer vi: InicioSonidoBestial.mapaImagenes.keySet()){

            InicioSonidoBestial.mapaImagenes.get(vi).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        setSound(vi);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });
        }
        */

    }

    private void setSound(int id) throws IOException {
        File soundFile;
        String soundName = InicioSonidoBestial.mapaSonidos.get(id).getFileName();
        soundFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), soundName +".mp3");
        sonido.setDataSource(getApplicationContext(), Uri.fromFile(soundFile));
        sonido.start();
    }




    //A los sonidos.

    public void aDescEructo(View view){
        if(sonido!=null){
            sonido.release();
        }
        sonido = MediaPlayer.create(this, R.raw.uno);
        sonido.start();
        indicadorSonido = 0;
        Intent i = new Intent(this, PupSonidoBestial.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void aDescPedo(View view){
        if(sonido!=null){
            sonido.release();
        }
        sonido = MediaPlayer.create(this, R.raw.dos);
        sonido.start();
        indicadorSonido = 1;
        Intent i = new Intent(this, PupSonidoBestial.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void aDescCarraspear(View view){
        if(sonido!=null){
            sonido.release();
        }
        sonido = MediaPlayer.create(this, R.raw.tres);
        sonido.start();
        indicadorSonido = 2;
        Intent i = new Intent(this, PupSonidoBestial.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void aDescPrevomito(View view){
        if(sonido!=null){
            sonido.release();
        }
        sonido = MediaPlayer.create(this, R.raw.cuatro);
        sonido.start();
        indicadorSonido = 3;
        Intent i = new Intent(this, PupSonidoBestial.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    public void aDescCucaracha(View view){
        if(sonido!=null){
            sonido.release();
        }
        sonido = MediaPlayer.create(this, R.raw.cinco);
        sonido.start();
        indicadorSonido = 4;
        Intent i = new Intent(this, PupSonidoBestial.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }



    //Para agregar los datos necesarios a la Descripción.

    public static int getIndicadorSonido() {
        return indicadorSonido;
    }

    //Método volver pantalla de inicio después de incatividad.

    @Override
    public void onUserInteraction(){

        super.onUserInteraction();
        countDownTimer.cancel();
        countDownTimer.start();
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {

            Intent i = new Intent(SonidoBestial.this, InicioSonidoBestial.class);
            startActivity(i);
            // Cerrar app
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }
    }


    //Método para ocultar barras de navegación y notificaciones
    @Override
    protected void onResume() {
        super.onResume();
        hideNavegationBar();
    }

    private void hideNavegationBar() {
        this.getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
}
