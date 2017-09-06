package almekh.com.example.android7281.casadocodigo.event;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by android7281 on 06/09/17.
 */

public abstract class EndlessListListener extends RecyclerView.OnScrollListener {

    private int quantidadeTotalItens;
    private int quantidadeItemVisivel;
    private int primeiroItemVisivel;
    private int totalAnterior = 0;
    private boolean carregando;

    @Override
    public void onScrolled(RecyclerView recyclerView, int qtdSrollHorizontal, int qtdScrollVertical) {
        super.onScrolled(recyclerView, qtdSrollHorizontal, qtdScrollVertical);

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        quantidadeTotalItens = layoutManager.getItemCount();
        primeiroItemVisivel = layoutManager.findFirstVisibleItemPosition();
        quantidadeItemVisivel = recyclerView.getChildCount();

        int indiceLimiteParaCarregar = quantidadeTotalItens - quantidadeItemVisivel - 5;

        if (carregando) {
            if (quantidadeTotalItens > totalAnterior) {
                totalAnterior = quantidadeTotalItens;
                carregando = false;
            }
        }

        if (!carregando && primeiroItemVisivel >= indiceLimiteParaCarregar) {
            carregaMaisItens();
            carregando = true;
        }
    }

    protected abstract void carregaMaisItens();


}
