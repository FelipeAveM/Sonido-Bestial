package com.example.sonidobestial;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.r0adkll.slidr.Slidr;

import java.io.File;

public class PupSonidoBestial extends AppCompatActivity{

    private Button volver, reply;
    private long startTime=3*60*1000; // uno MINS IDLE TIME
    private final long interval = 1 * 1000;
    private MediaPlayer sonido;
    MyCountDownTimer countDownTimer;
    private TextView tituloSonido, descSonido;
    private int[] colores = {R.color.larva5,R.color.larva2,R.color.larva3, R.color.larva4, R.color.larva3,R.color.larva1,R.color.larva1,R.color.larva5, R.color.larva4, R.color.larva2 };
    private VideoView video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pup_sonido_bestial);
        hideNavegationBar();
        Slidr.attach(this);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        volver = (Button)findViewById(R.id.volver_btn);
        reply = (Button)findViewById(R.id.reply_btn);
        tituloSonido = (TextView)findViewById(R.id.tit_sonido);
        descSonido = (TextView)findViewById(R.id.desc_sonido);
        agregarContenido();
        setSound(SonidoBestial.getIndicadorSonido());
        getVideo();
        SonidoBestial.getIndicadorSonido();

        //Fuentes
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/maax_rounded_medium_italic.otf");
        tituloSonido.setTypeface(font);
        Typeface fontD = Typeface.createFromAsset(getAssets(), "fonts/maax_rounded.otf");
        descSonido.setTypeface(fontD);
        reply.setTypeface(font);
        volver.setTypeface(font);



    }

    public void getVideo(){

        video = (VideoView)findViewById(R.id.videoView);
        File videoFile;
        videoFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "video.mp4");
        String uriPath = String.valueOf(videoFile);
        video.setVideoURI(Uri.parse(uriPath));
        video.requestFocus();
        video.start();

    }
    //Reproducir audio del XML tiene que llamarse como el nombre del sonido en el xml ej: Eructo.mp3
    public  void setSound(int id){
        if(sonido!=null){
            sonido.release();
        }
        File soundFile;
        String soundName = InicioSonidoBestial.sonidos.get(id).getNombre();
        soundFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), soundName +".mp3");
        sonido = MediaPlayer.create(this, Uri.fromFile(soundFile));
        sonido.start();
    }
    private void agregarContenido() {

        tituloSonido.setText(InicioSonidoBestial.sonidos.get(SonidoBestial.getIndicadorSonido()).getNombre());
        tituloSonido.setTextColor(getResources().getColor(colores[SonidoBestial.getIndicadorSonido()]));
        descSonido.setText(InicioSonidoBestial.sonidos.get(SonidoBestial.getIndicadorSonido()).getDescripcion());
        volver.setBackgroundColor(getResources().getColor(colores[SonidoBestial.getIndicadorSonido()]));
        reply.setBackgroundColor(getResources().getColor(colores[SonidoBestial.getIndicadorSonido()]));

    }
    public void replySound(View view){
        getVideo();
        setSound(SonidoBestial.getIndicadorSonido());
    }

    public void aFunc(View view){
        sonido.stop();
        Intent i = new Intent(this, SonidoBestial.class);
        startActivity(i);
    }

    //Método volver pantalla de inicio después de incatividad.

    @Override
    public void onUserInteraction(){

        super.onUserInteraction();

        //Reset the timer on user interaction...
        countDownTimer.cancel();
        countDownTimer.start();
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {

            Intent i = new Intent(PupSonidoBestial.this, InicioSonidoBestial.class);
            startActivity(i);
            // CIERRA LA APP MATANDO EL PROCESO Y VUELVE A ABRIRLO.
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
