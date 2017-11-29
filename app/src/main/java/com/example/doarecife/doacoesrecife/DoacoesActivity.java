package com.example.doarecife.doacoesrecife;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.doarecife.doacoesrecife.models.Itemdoacao;

import org.parceler.Parcels;

public class DoacoesActivity extends AppCompatActivity
        implements ListaDoacoesFragment.CliqueiNoItemListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doacoes);
    }

    @Override
    public void itemFoiClicado(Itemdoacao itemdoacao) {
       if (getResources().getBoolean(R.bool.tablet)) {
           DetalheDoacaoFragment ddf = DetalheDoacaoFragment.newInstance(itemdoacao);
           getSupportFragmentManager()
                   .beginTransaction()
                   .replace(R.id.detalhe, ddf, "detalhe")
                    .commit();
       }else {
           Intent it = new Intent(this, DetalheDoacaoActivity.class);
           Parcelable p = Parcels.wrap(itemdoacao);
           it.putExtra(DetalheDoacaoActivity.EXTRA_DOACAO, p);
           startActivity(it);
       }
    }
}
