package com.example.doarecife.doacoesrecife;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.doarecife.doacoesrecife.models.ItemDoacao;

import org.parceler.Parcels;

public class DetalheDoacaoActivity extends AppCompatActivity {

    public static final String EXTRA_DOACAO = "itemDoacao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_doacao);
        ItemDoacao itemDoacao = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_DOACAO));

        DetalheDoacaoFragment ddf = DetalheDoacaoFragment.newInstance(itemDoacao);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detalhe, ddf, "detalhe")
                .commit();
    }
}
