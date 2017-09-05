package almekh.com.example.android7281.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import almekh.com.example.android7281.casadocodigo.R;
import almekh.com.example.android7281.casadocodigo.adapter.LivroAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import almekh.com.example.android7281.casadocodigo.model.Livro;

/**
 * Created by android7281 on 04/09/17.
 */

public class ListaLivrosFragment extends Fragment {

    private List<Livro> livros = new ArrayList<>();

    @BindView(R.id.lista_livros)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        ButterKnife.bind(this, view);
        recyclerView.setAdapter(new LivroAdapter(livros));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        return view;
    }

    public void populaListaCom(List<Livro> livros) {
        this.livros.clear();
        this.livros.addAll(livros);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
