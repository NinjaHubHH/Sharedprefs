package com.example.ninja.sharedprefs;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_save, btn_read, btn_delete;
    EditText et1;
    TextView tv1;

    String eingabeTxt;

    //key variable
    final String KEY1 = "key1";

    String ausgelesen;

    boolean testbool = true;
    boolean ausgelesenbool;

    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        btn_read = (Button) findViewById(R.id.btn_read);
        btn_read.setOnClickListener(this);

        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);

        et1 = (EditText) findViewById(R.id.editText);
        tv1 = (TextView) findViewById(R.id.textView);

        //String name der XML datei und danach der Zugriff, wer darf drauf zugreifen: world - andere Apps oder private, nur die eine
        prefs = this.getSharedPreferences("prefsDatei", MODE_PRIVATE);
        prefsEditor = prefs.edit();

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btn_save: {
                if(et1.getText().length() > 0){
                    eingabeTxt = et1.getText().toString();

                    //eingabe speichern. eingabeTxt wird unter KEY1 gespeichert
                    prefsEditor.putString(KEY1,eingabeTxt);

                    prefsEditor.putBoolean("key2", testbool);

                    //laden in die XML Datei
                    prefsEditor.commit();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Kein Text", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btn_read: {
                //was wird ausgegeben wenn nichts ausgegeben wird: Kein Text gespeichert
                ausgelesen = prefs.getString(KEY1, "Kein Text gespeichert");
                //false ausgeben wenn nichts drin stand
                ausgelesenbool = prefs.getBoolean("key2", false);
                Toast.makeText(getApplicationContext(), Boolean.toString(ausgelesenbool), Toast.LENGTH_SHORT).show();

                tv1.setText(ausgelesen);

                break;
            }
            case R.id.btn_delete: {

                prefsEditor.remove(KEY1);
                prefsEditor.remove("key2");
                prefsEditor.commit();               //updated
                tv1.setText(null);

                break;
            }

        }

    }
}
