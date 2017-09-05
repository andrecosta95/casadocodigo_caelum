package fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import almekh.com.example.android7281.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import utils.Autor;
import utils.Livro;
import utils.LivroAdapter;

/**
 * Created by android7281 on 04/09/17.
 */

public class ListaLivrosFragment extends Fragment {

    @BindView(R.id.lista_livros)
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        List<Livro> livros = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Autor autor = new Autor();
            autor.setNome("Autor " + i);
            Livro livro = new Livro("Livro " + i, "Descrição " + i, Arrays.asList(autor));
            livros.add(livro);
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.lista_livros);
        recyclerView.setAdapter(new LivroAdapter(livros));
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        ButterKnife.bind(this, view);
        return view;
    }
}
