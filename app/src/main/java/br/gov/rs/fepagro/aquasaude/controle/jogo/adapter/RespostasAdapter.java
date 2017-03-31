package br.gov.rs.fepagro.aquasaude.controle.jogo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;

import java.util.List;

import br.gov.rs.fepagro.aquasaude.R;
import br.gov.rs.fepagro.aquasaude.modelo.Resposta;

/**
 * Created by eduardo-pooch on 31/03/2017.
 */
public class RespostasAdapter extends BaseAdapter {

    private List<Resposta> respostas;
    private Context context;
    private int selectedPosition;

    public RespostasAdapter(Context context, List<Resposta> respostas) {
        this.respostas = respostas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return respostas.size();
    }

    @Override
    public Object getItem(int position) {
        return respostas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return respostas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Resposta resposta = respostas.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutTipoResposta = getLayoutTipoResposta(resposta);
        View view = convertView;
        if (view == null) view = inflater.inflate(layoutTipoResposta, parent, false);

        RadioButton radioButton = (RadioButton) view.findViewById(R.id.radio_resposta);
        radioButton.setChecked(position == selectedPosition);
        radioButton.setTag(position);
        radioButton.setOnClickListener(this::radioClicado);
        view.setOnClickListener(v -> radioClicado(radioButton));

        ImageView imageView = (ImageView) view.findViewById(R.id.foto_resposta);
        int resIdFoto = resposta.getFotoResId();
        Glide.with(context).load(resIdFoto).centerCrop().into(imageView);

        return view;
    }

    private void radioClicado(View v) {
        selectedPosition = (Integer) v.getTag();
        notifyDataSetChanged();
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    private int getLayoutTipoResposta(Resposta resposta) {
        if (resposta.getTipo() == Resposta.TIPO_IMAGEM) {
            return R.layout.respostas_imagem;
        } else {
            return R.layout.respostas_texto;
        }
    }
}
