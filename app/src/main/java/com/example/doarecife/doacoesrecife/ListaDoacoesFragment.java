package com.example.doarecife.doacoesrecife;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doarecife.doacoesrecife.models.Categoria;
import com.example.doarecife.doacoesrecife.models.Doacao;
import com.example.doarecife.doacoesrecife.models.Itemdoacao;
import com.google.gson.Gson;

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

    @BindView(R.id.empety)
            View mEmpety;

    DoacaoTask mDoacaoTask;

    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipe;

    List<Itemdoacao> mItemdoacaoList;
    ArrayAdapter<Itemdoacao> mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mItemdoacaoList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_lista_doacoes, container, false);
        ButterKnife.bind(this, layout);

        mAdapter = new ItemAdapter(getContext(), mItemdoacaoList);

        mListView.setEmptyView(mEmpety);

        mListView.setAdapter(mAdapter);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              baixarJson();
            }
        });
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mItemdoacaoList.size() == 0 && mDoacaoTask == null) {
            baixarJson();
        } else if (mDoacaoTask != null && mDoacaoTask.getStatus() == AsyncTask.Status.RUNNING) {
            showProgress();
        }
    }

    private void baixarJson(){
        ConnectivityManager cm = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo inf = cm.getActiveNetworkInfo();
        if(inf != null && inf.isConnected()){

            mDoacaoTask = new DoacaoTask();
            mDoacaoTask.execute();   
        }else {
            mSwipe.setRefreshing(false);
            Toast.makeText(getActivity(), R.string.sem_conexao, Toast.LENGTH_SHORT).show();
        }

    }

    private void showProgress() {
        mSwipe.post(new Runnable() {
            @Override
            public void run() {
                mSwipe.setRefreshing(true);
            }
        });
    }
        @OnItemClick(R.id.list_itemdoacao)
        void OnItemSelected ( int position){
            Itemdoacao itemdoacao = mItemdoacaoList.get(position);
            if (getActivity() instanceof CliqueiNoItemListener) {
                CliqueiNoItemListener listener = (CliqueiNoItemListener) getActivity();
                listener.itemFoiClicado(itemdoacao);
            }
        }

        class DoacaoTask extends AsyncTask<Void, Void, Doacao> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showProgress();
            }

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
                    Gson gson = new Gson();
                    Doacao doacao = gson.fromJson(jsonString, Doacao.class);
                    return doacao;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Doacao doacao) {
                super.onPostExecute(doacao);
                if (doacao != null) {
                    mItemdoacaoList.clear();
                    for (Categoria categoria : doacao.getCategorias()
                            ) {
                        mItemdoacaoList.addAll(categoria.getItens());
                        if(getResources().getBoolean(R.bool.tablet)
                                && mItemdoacaoList.size()>0){
                            OnItemSelected(0);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }
                mSwipe.setRefreshing(false);

            }
        }
    }