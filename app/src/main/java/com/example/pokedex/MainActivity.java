package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{

    public static Activity act;
    public static TextView txtDisplay;
    public static ImageView imgPok;
    public int iterador;

    public String [] ListaTipo;             //Lista de tipos
    public String BuscaTipo;                //Buscar tipo en lista de tipos

    Button Siguiente;
    Button Anterior;
    Button Inicio;
    Button Final;

    public static ImageView [] imgType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListaTipo = getResources().getStringArray(R.array.tipos);

        act = this;
        imgType = new ImageView[2];

        txtDisplay = findViewById(R.id.txtDisplay);
        imgPok = findViewById(R.id.imgPok);
        imgType[0] = findViewById(R.id.imgType0);
        imgType[1] = findViewById(R.id.imgType1);

        Siguiente=findViewById(R.id.btnRight);
        Anterior=findViewById(R.id.btnLeft);
        Inicio=findViewById(R.id.btnInicio);
        Final=findViewById(R.id.btnFinal);



        Inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData process = new fetchData("1");
                process.execute();
            }
        });
        Final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData process = new fetchData("898");
                process.execute();
            }
        });

        Siguiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iterador >= 1118) {
                    iterador = 1118;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch);
                    process.execute();
                } else {
                    iterador++;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch);
                    process.execute();
                }
            }
        });
        Anterior.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (iterador >= 1118) {
                    iterador = 1118;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch);
                    process.execute();
                } else {
                    iterador--;
                    String pokSearch = String.valueOf(iterador);
                    fetchData process = new fetchData(pokSearch);
                    process.execute();
                }
            }
        });

        ImageButton btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTxtSearch();
            }
        });

        ImageButton btnTypes = findViewById(R.id.btnTypes);
        btnTypes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowTipos();
            }
        });

        fetchData process = new fetchData("1");
        process.execute();
    }

    public void showTxtSearch(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Search a Pokemon");

        final EditText input = new EditText(this);
        input.setHint("Pokemon");
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String pokSearch = input.getText().toString();
                fetchData process = new fetchData(pokSearch);
                process.execute();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();
    }


    public void ShowTipos(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Escoge el tipo de pokemon");
        builder.setItems(R.array.tipos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
            /*
            fetchType process1 = new fetchType("type/");
            process1.execute();
            */
                BuscaTipo = ListaTipo[which];
                fetchType process1 = new fetchType(BuscaTipo);
                process1.execute();
            }
        });
        Dialog dialog=builder.create();
        dialog.show();
    }


}