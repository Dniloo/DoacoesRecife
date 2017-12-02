package com.example.doarecife.doacoesrecife;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doarecife.doacoesrecife.database.DoacaoDAO;
import com.example.doarecife.doacoesrecife.models.Itemdoacao;

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
    @BindView(R.id.imageFoto)
    ImageView mImageFoto;

    DoacaoDAO mDAO;
    private Itemdoacao mItemdoacao;

    public static DetalheDoacaoFragment newInstance(Itemdoacao itemdoacao) {
        DetalheDoacaoFragment fragment = new DetalheDoacaoFragment();
        Bundle args = new Bundle();
        Parcelable p = Parcels.wrap(itemdoacao);
        args.putParcelable(EXTRA_DOACAO, p);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDAO = new DoacaoDAO(getActivity());
        if (getArguments() != null) {
            Parcelable p = getArguments().getParcelable(EXTRA_DOACAO);
            mItemdoacao = Parcels.unwrap(p);
        }
    }

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe_doacao, container, false);
        unbinder = ButterKnife.bind(this, view);
        mTextLocal.setText(mItemdoacao.getLocal());
        mTextTipo.setText(mItemdoacao.getTipo());
        Glide.with(getActivity()).load(mItemdoacao.getFoto()).into(mImageFoto);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fab_favorito)
    public void favoritoClick() {
        if(mDAO.isFavorito(mItemdoacao)) {
            mDAO.delete(mItemdoacao);
        } else {
            mDAO.inserir(mItemdoacao);
        }

        ((DoacaoAPP)getActivity().getApplication()).getEventBus().post(mItemdoacao);
    }
}