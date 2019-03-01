package com.example.sonidobestial;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InicioSonidoBestial extends AppCompatActivity {

    private ImageView imgInicio;

    //Leer por XML
    private TextView pruebaParse;
    public  static ArrayList<Sonidos> sonidos;
    private Sonidos sonido;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sonido_bestial);
        hideNavegationBar();
        Slidr.attach(this);
        imgInicio = (ImageView)findViewById(R.id.logo_inicio);

        sonidos = new ArrayList<Sonidos>();
        pruebaParse = (TextView)findViewById(R.id.pru_parse);

        InputStream is = null;
        File datos;


        //Leer por XML
        try {
            datos = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "data.xml");
            is = new FileInputStream(datos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        parse(is);
                //parseXML();

        pruebaParse.setText("");
    }



    public List<Sonidos> getSonidos(){
        return sonidos;
    }

    public List<Sonidos> parse(InputStream is){
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        try {

            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            parser = factory.newPullParser();

            parser.setInput(is, null);

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){
                String tagName = parser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:

                        if(tagName.equalsIgnoreCase("sonido")){
                            sonido = new Sonidos();
                        }
                        break;
                    case XmlPullParser.TEXT:

                        text = parser.getText();
                        break;
                     case XmlPullParser.END_TAG:

                         if(tagName.equalsIgnoreCase("sonido")){
                             sonidos.add(sonido);
                         }else if(tagName.equalsIgnoreCase("id")){
                             sonido.setId(Integer.parseInt(text));
                         }else if(tagName.equalsIgnoreCase("filaName")){
                             sonido.setFileName(text);
                         }else if(tagName.equalsIgnoreCase("nombre")){
                             sonido.setNombre(text);
                         }else if(tagName.equalsIgnoreCase("descripcion")){
                             sonido.setDescripcion(text);
                         }
                         break;
                         default:
                             break;

                }
                eventType = parser.next();
            }

        }
        catch (Exception e){

        }
        printSonidos(sonidos);
        return sonidos;
    }
    private void printSonidos(ArrayList<Sonidos> sonidos) {

        StringBuilder builder = new StringBuilder();

        for(Sonidos sonido : sonidos){
            builder.append(sonido.id).append("\n").
                    append(sonido.fileName).append("\n").
                    append(sonido.nombre).append("\n").
                    append(sonido.descripcion).append("\n\n");

        }
        pruebaParse.setText(builder.toString());
    }

    public void aFunc(View view){
        Intent i = new Intent(this, SonidoBestial.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

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
