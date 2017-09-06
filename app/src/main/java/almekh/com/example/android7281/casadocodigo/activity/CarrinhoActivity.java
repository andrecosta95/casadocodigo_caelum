package almekh.com.example.android7281.casadocodigo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import almekh.com.example.android7281.casadocodigo.R;
import almekh.com.example.android7281.casadocodigo.adapter.ItensAdapter;
import almekh.com.example.android7281.casadocodigo.model.Carrinho;
import almekh.com.example.android7281.casadocodigo.model.Item;
import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by android7281 on 06/09/17.
 */

public class CarrinhoActivity extends AppCompatActivity{


    @BindView(R.id.lista_itens_carrinho)
    RecyclerView listaItens;

    @BindView(R.id.valor_carrinho)
    TextView valorTotal;

    private Carrinho carrinho = new Carrinho();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        ButterKnife.bind(this);
    }

    public void carregaLista() {
        listaItens.setAdapter(new ItensAdapter(carrinho.getItens(), this));
        listaItens.setLayoutManager(new LinearLayoutManager(this));

        double total = 0;
        for (Item item : carrinho.getItens()) {
            total += item.getValor();
        }
        valorTotal.setText("R$ " + total);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
}
