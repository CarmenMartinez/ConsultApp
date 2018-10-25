package com.wired.ctapp.consultall;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DetailActivity extends Activity {
    private String[] listas = {
            "Texto de Ejemplo",
            "Texto de Ejemplo",
            "Texto de Ejemplo",
            "Texto de Ejemplo",
            "Texto de Ejemplo",
            "Texto de Ejemplo",
            "Texto de Ejemplo",
            "Texto de Ejemplo",
            "Texto de Ejemplo",
            "Texto de Ejemplo"
    };
    private ListView listv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        listv1 =(ListView)findViewById(R.id.lv1);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listas);
        listv1.setAdapter(adapter);
    }

}