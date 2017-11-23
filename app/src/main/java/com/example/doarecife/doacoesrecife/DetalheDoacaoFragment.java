package com.example.doarecife.doacoesrecife;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doarecife.doacoesrecife.models.ItemDoacao;

import org.parceler.Parcel;
import org.parceler.Parcels;


public class DetalheDoacaoFragment extends Fragment {
    private static final String EXTRA_DOACAO = "param1";

    private ItemDoacao mItemDoacao;

    public static DetalheDoacaoFragment newInstance(ItemDoacao itemDoacao) {
        DetalheDoacaoFragment fragment = new DetalheDoacaoFragment();
        Bundle args = new Bundle();
        Parcelable p = Parcels.wrap(itemDoacao);
        args.putParcelable(EXTRA_DOACAO, p);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Parcelable p = getArguments().getParcelable(EXTRA_DOACAO);
            mItemDoacao = Parcels.unwrap(p);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe_doacao, container, false );

        TextView txt = (TextView)view.findViewById(R.id.text_doacao);
        txt.setText(mItemDoacao.toString());
        return view;
    }
}
