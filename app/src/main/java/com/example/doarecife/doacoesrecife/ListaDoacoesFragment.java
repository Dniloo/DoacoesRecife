package com.example.doarecife.doacoesrecife;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.doarecife.doacoesrecife.models.ItemDoacao;

import java.util.ArrayList;
import java.util.List;


public class ListaDoacoesFragment extends Fragment {

      List<ItemDoacao> mItemDoacaoList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemDoacaoList = new ArrayList<>();
        mItemDoacaoList.add(new ItemDoacao("Doe Aqui", "roupas"));
        mItemDoacaoList.add(new ItemDoacao("Doar é VIDA","comidas"));
        mItemDoacaoList.add(new ItemDoacao("Doar tras vida","comidas e roupas"));

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_lista_doacoes, container, false);

        ListView listView = (ListView)layout.findViewById(R.id.list_itemdoacao);
        listView.setAdapter(new ArrayAdapter<ItemDoacao>(
                getActivity(), android.R.layout.simple_expandable_list_item_1, mItemDoacaoList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ItemDoacao itemDoacao = mItemDoacaoList.get(position);
                    if (getActivity() instanceof CliqueiNoItemListener){
                        CliqueiNoItemListener listener = (CliqueiNoItemListener) getActivity();
                        listener.itemFoiClicado(itemDoacao);
                    }
                }

        });

        return layout;
    }
    interface CliqueiNoItemListener{
        void itemFoiClicado(ItemDoacao itemDoacao);
    }

}
