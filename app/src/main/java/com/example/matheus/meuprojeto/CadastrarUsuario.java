package com.example.matheus.meuprojeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TabHost;

public class CadastrarUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        TabHost host =  findViewById(R.id.tabhost);
        host.setup();
        //tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab one");
        spec.setContent(R.id.TELEFONE);
        spec.setIndicator("TELEFONE");
        host.addTab(spec);
        //tab2
        spec = host.newTabSpec("Tab two");
        spec.setContent(R.id.E_MAIL);
        spec.setIndicator("E-MAIL");
        host.addTab(spec);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.collapseActionView){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
