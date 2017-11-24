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
import android.widget.Toast;

import com.example.doarecife.doacoesrecife.models.ItemDoacao;

import org.parceler.Parcel;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class DetalheDoacaoFragment extends Fragment {
    private static final String EXTRA_DOACAO = "param1";

    @BindView(R.id.text_local)
    TextView mTextLocal;
    @BindView(R.id.text_tipo)
    TextView mTextTipo;


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

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe_doacao, container, false);
        unbinder = ButterKnife.bind(this, view);
        mTextLocal.setText(mItemDoacao.getLocal());
        mTextTipo.setText(mItemDoacao.getTipo());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}