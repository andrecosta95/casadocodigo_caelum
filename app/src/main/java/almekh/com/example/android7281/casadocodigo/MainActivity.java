package almekh.com.example.android7281.casadocodigo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import delegate.LivrosDelegate;
import fragment.DetalheLivroFragment;
import fragment.ListaLivrosFragment;
import utils.Livro;

public class MainActivity extends AppCompatActivity implements LivrosDelegate, FragmentManager.OnBackStackChangedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_principal, new ListaLivrosFragment());
        transaction.commit();

        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public void lidaComLivrosSelecionado(Livro livro) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetalheLivroFragment detalheLivroFragment = criaDetalhesCom(livro);
        transaction.replace(R.id.frame_principal, detalheLivroFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private DetalheLivroFragment criaDetalhesCom(Livro livro) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("livro", livro);
        DetalheLivroFragment detalheLivroFragment = new DetalheLivroFragment();
        detalheLivroFragment.setArguments(bundle);
        return detalheLivroFragment;
    }

    @Override
    public void onBackStackChanged() {
        mostrarVoltar();
    }

    public void mostrarVoltar(){
        boolean podeVoltar = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(podeVoltar);
    }

    @Override
    public boolean onSupportNavigateUp(){
        getSupportFragmentManager().popBackStack();;
        return true;
    }
}
