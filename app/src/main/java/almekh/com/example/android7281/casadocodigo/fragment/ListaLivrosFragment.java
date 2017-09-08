package almekh.com.example.android7281.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import almekh.com.example.android7281.casadocodigo.CasaDoCodigoApplication;
import almekh.com.example.android7281.casadocodigo.R;
import almekh.com.example.android7281.casadocodigo.adapter.LivroAdapter;
import almekh.com.example.android7281.casadocodigo.event.EndlessListListener;
import almekh.com.example.android7281.casadocodigo.server.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import almekh.com.example.android7281.casadocodigo.model.Livro;

/**
 * Created by android7281 on 04/09/17.
 */

public class ListaLivrosFragment extends Fragment {

    private List<Livro> livros = new ArrayList<>();
    @Inject
    FirebaseRemoteConfig config;

    @BindView(R.id.lista_livros)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        ButterKnife.bind(this, view);

        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getActivity().getApplication();
        app.getComponent().inject(this);

        config.fetch(30).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    config.activateFetched();
                    boolean listaSimples = config.getBoolean("lista.simples");
                    configuraLista(listaSimples);
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        return view;
    }

    private void configuraLista(boolean listaSimples) {
        recyclerView.setAdapter(new LivroAdapter(livros, listaSimples));
    }

    public void populaListaCom(final List<Livro> livros) {
        //this.livros.clear();
        this.livros.addAll(livros);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.addOnScrollListener(new EndlessListListener() {
            @Override
            protected void carregaMaisItens() {
                new WebClient().getLivros(livros.size(), 10);
            }
        });
    }
}
