package com.example.doarecife.doacoesrecife;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doarecife.doacoesrecife.models.Itemdoacao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jose mario on 28/11/2017.
 */

public class ItemAdapter extends ArrayAdapter<Itemdoacao> {
    public ItemAdapter(@NonNull Context context, List<Itemdoacao> itemdoacaos) {
        super(context, 0, itemdoacaos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Itemdoacao itemdoacao = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_doacao, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(getContext()).load(itemdoacao.getFoto()).into(viewHolder.imageView);
        viewHolder.textLocal.setText(itemdoacao.getLocal());
        viewHolder.textTipo.setText(itemdoacao.getTipo());
        return convertView;

    }

    static class ViewHolder {
        @BindView(R.id.imageFoto)
        ImageView imageView;
        @BindView(R.id.text_Local)
        TextView textLocal;
        @BindView(R.id.text_tipo2)
        TextView textTipo;


        public ViewHolder(View parent) {
            super();
            ButterKnife.bind(this, parent);
            parent.setTag(this);
        }
    }
}
