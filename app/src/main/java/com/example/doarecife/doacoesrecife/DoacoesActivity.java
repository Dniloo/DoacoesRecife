package com.example.doarecife.doacoesrecife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.doarecife.doacoesrecife.models.ItemDoacao;

public class DoacoesActivity extends AppCompatActivity
        implements ListaDoacoesFragment.CliqueiNoItemListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doacoes);
    }

    @Override
    public void itemFoiClicado(ItemDoacao itemDoacao) {
        DetalheDoacaoFragment ddf = DetalheDoacaoFragment.newInstance(itemDoacao);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detalhe, ddf, "detalhe")
                .commit();

    }
}
