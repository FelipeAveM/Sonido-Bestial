package com.example.sonidobestial;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;

public class PupSonidoBestial extends AppCompatActivity{

    private Button volver;
    private long startTime=3*60*1000; // uno MINS IDLE TIME
    private final long interval = 1 * 1000;
    MyCountDownTimer countDownTimer;
    private TextView tituloSonido, descSonido;
    private int[] colores = {R.color.larva1,R.color.larva2,R.color.larva3, R.color.larva4, R.color.larva5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pup_sonido_bestial);
        hideNavegationBar();
        Slidr.attach(this);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        volver = (Button)findViewById(R.id.volver_btn);
        tituloSonido = (TextView)findViewById(R.id.tit_sonido);
        descSonido = (TextView)findViewById(R.id.desc_sonido);
        agregarContenido();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/maax_rounded_medium_italic.otf");
        tituloSonido.setTypeface(font);

        Typeface fontD = Typeface.createFromAsset(getAssets(), "fonts/maax_rounded.otf");
        descSonido.setTypeface(fontD);

        volver.setTypeface(font );



    }

    private void agregarContenido() {

        tituloSonido.setText(InicioSonidoBestial.sonidos.get(SonidoBestial.getIndicadorSonido()).getNombre());
        tituloSonido.setTextColor(getResources().getColor(colores[SonidoBestial.getIndicadorSonido()]));
        descSonido.setText(InicioSonidoBestial.sonidos.get(SonidoBestial.getIndicadorSonido()).getDescripcion());
        volver.setBackgroundColor(getResources().getColor(colores[SonidoBestial.getIndicadorSonido()]));

    }

    public void aFunc(View view){
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
