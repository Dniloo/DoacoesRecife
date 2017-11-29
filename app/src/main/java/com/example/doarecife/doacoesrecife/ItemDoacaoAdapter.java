package com.example.doarecife.doacoesrecife;

import android.content.Context;
import android.support.annotation.LayoutRes;
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
 * Created by PC GAMER on 28/11/2017.
 */

public class ItemDoacaoAdapter extends ArrayAdapter<Itemdoacao> {
    public ItemDoacaoAdapter(Context context, List<Itemdoacao> itensDoacao) {
        super(context, 0, itensDoacao);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Itemdoacao item = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_doacao, parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(getContext()).load(item.getFoto()).into(viewHolder.imageView);
        viewHolder.txtTitulo.setText(item.getLocal());
        viewHolder.txtTipo.setText(item.getTipo());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.imagem_capa)
        ImageView imageView;
        @BindView(R.id.text_titulo)
        TextView txtTitulo;
        @BindView(R.id.text_tipo)
        TextView txtTipo;

        public ViewHolder(View parent) {
            ButterKnife.bind(this, parent);
            parent.setTag(this);
        }
    }
}
