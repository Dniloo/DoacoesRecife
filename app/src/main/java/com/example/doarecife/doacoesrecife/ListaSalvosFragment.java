package com.example.doarecife.doacoesrecife;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.doarecife.doacoesrecife.database.DoacaoDAO;
import com.example.doarecife.doacoesrecife.models.Itemdoacao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class ListaSalvosFragment extends Fragment {

    @BindView(R.id.list_itemdoacao)
    ListView mListView;

    List<Itemdoacao> mItemdoacaoList;
    ArrayAdapter<Itemdoacao> mAdapter;

    DoacaoDAO mDao;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mDao =  new DoacaoDAO(getActivity());
        mItemdoacaoList = mDao.listar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_lista_salvos, container, false);
        ButterKnife.bind(this, layout);

        mAdapter = new ItemAdapter(getContext(), mItemdoacaoList);
        mListView.setAdapter(mAdapter);
        return layout;
    }

        @OnItemClick(R.id.list_itemdoacao)
        void OnItemSelected ( int position){
            Itemdoacao itemdoacao = mItemdoacaoList.get(position);
            if (getActivity() instanceof CliqueiNoItemListener) {
                CliqueiNoItemListener listener = (CliqueiNoItemListener) getActivity();
                listener.itemFoiClicado(itemdoacao);
            }
        }

    }