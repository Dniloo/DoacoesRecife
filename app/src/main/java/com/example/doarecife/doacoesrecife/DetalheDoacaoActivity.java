package com.example.doarecife.doacoesrecife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.doarecife.doacoesrecife.models.Itemdoacao;

import org.parceler.Parcels;

public class DetalheDoacaoActivity extends AppCompatActivity {

    public static final String EXTRA_DOACAO = "itemdoacao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_doacao);
        Itemdoacao itemdoacao = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_DOACAO));

        DetalheDoacaoFragment ddf = DetalheDoacaoFragment.newInstance(itemdoacao);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detalhe, ddf, "detalhe")
                .commit();
    }
}
