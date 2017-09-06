package almekh.com.example.android7281.casadocodigo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

import almekh.com.example.android7281.casadocodigo.R;
import almekh.com.example.android7281.casadocodigo.activity.CarrinhoActivity;
import almekh.com.example.android7281.casadocodigo.delegate.LivrosDelegate;
import almekh.com.example.android7281.casadocodigo.model.Item;
import almekh.com.example.android7281.casadocodigo.model.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItensAdapter extends RecyclerView.Adapter{

    private List<Item> items;

    public ItensAdapter(List<Item> itens, CarrinhoActivity carrinhoActivity) {
        this.items = itens;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Item item = items.get(position);
        viewHolder.nome.setText(item.getLivro().getNome());
        viewHolder.valor.setText(String.valueOf(item.getValor()));

        Picasso.with(viewHolder.foto.getContext())
                .load(item.getLivro().getUrlFoto())
                .placeholder(R.drawable.livro)
                .into(viewHolder.foto);

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nome_item_comprado)
        TextView nome;
        @BindView(R.id.imagem_item_comprado)
        ImageView foto;
        @BindView(R.id.valor_pago_item_comprado)
        TextView valor;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_carrinho, parent, false);

        return new ViewHolder(view);
    }
}
