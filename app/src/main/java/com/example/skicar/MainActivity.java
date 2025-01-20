package com.example.skicar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.content.DialogInterface;
import android.app.AlertDialog;


public class MainActivity extends AppCompatActivity {

    private Skicar skicarView;
    private Button btn_farba, btn_hrubka_plus, btn_hrubka_minus, btn_vymaz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    // nastavenie zobrazenia plochy na kreslenie (platna)
        skicarView = findViewById(R.id.skicar);
    // zobrazenie tlacidiel
        btn_farba = findViewById(R.id.button_farba);
        btn_hrubka_plus = findViewById(R.id.button_hrubka_plus);
        btn_hrubka_minus = findViewById(R.id.button_hrubka_minus);
        btn_vymaz = findViewById(R.id.button_vymaz);



        // zoznam farieb z ktorych je mozno jednu zvolit
        final String[] colorNames = {"Čierna", "Zelená", "Modrá", "Červená", "Žltá", "Fialová", "Tyrkysová", "Šedá" };
        final int[] colors = {Color.BLACK, Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW, Color.MAGENTA, Color.CYAN, Color.GRAY}; // Farby

        btn_farba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Zobrazenie AlertDialog so zoznamom farieb
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Vyberte farbu")
                        .setItems(colorNames, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Zmena farby po výbere
                                skicarView.changeColor(colors[which]);
                                // oznamenie o zvolenej farbe
                                Toast.makeText(MainActivity.this, "Vybrali ste farbu: " + colorNames[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        btn_hrubka_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // zvysenie hrúbky čiary
                skicarView.addStrokeWidth();
            }
        });

        btn_hrubka_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // znizenie hrúbky čiary
                skicarView.subStrokeWidth();
            }
        });

        btn_vymaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vymazanie kresby
                skicarView.clear();
            }
        });
    }
}