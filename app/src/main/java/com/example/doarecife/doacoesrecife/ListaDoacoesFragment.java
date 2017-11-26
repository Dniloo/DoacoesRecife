package com.example.doarecife.doacoesrecife;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.doarecife.doacoesrecife.models.Doacao;
import com.example.doarecife.doacoesrecife.models.Itemdoacao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ListaDoacoesFragment extends Fragment {

    @BindView(R.id.list_itemdoacao)
            ListView mListView;

      List<Itemdoacao> mItemdoacaoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_lista_doacoes, container, false);
        ButterKnife.bind(this, layout);

        mItemdoacaoList = new ArrayList<>();
        mListView.setAdapter(new ArrayAdapter<Itemdoacao>(
                getActivity(), android.R.layout.simple_list_item_1, mItemdoacaoList));
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new DoacaoTask().execute();
    }

    @OnItemClick(R.id.list_itemdoacao)
   void OnItemSelected(int position) {
        Itemdoacao itemdoacao = mItemdoacaoList.get(position);
        if (getActivity() instanceof CliqueiNoItemListener) {
            CliqueiNoItemListener listener = (CliqueiNoItemListener) getActivity();
            listener.itemFoiClicado(itemdoacao);
        }
    }

    public interface CliqueiNoItemListener{
        void itemFoiClicado(Itemdoacao itemdoacao);
    }
    class DoacaoTask extends AsyncTask<Void, Void, Doacao> {

        @Override
        protected Doacao doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://dl.dropboxusercontent.com/s/e26jjpgyfmfnoj9/doar.Json")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String jsonString = response.body().string();
                Log.d("NGVL", jsonString);

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

}
