package almekh.com.example.android7281.casadocodigo.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import almekh.com.example.android7281.casadocodigo.R;
import almekh.com.example.android7281.casadocodigo.event.LivroEvent;
import almekh.com.example.android7281.casadocodigo.server.WebClient;
import almekh.com.example.android7281.casadocodigo.delegate.LivrosDelegate;
import almekh.com.example.android7281.casadocodigo.fragment.DetalheLivroFragment;
import almekh.com.example.android7281.casadocodigo.fragment.ListaLivrosFragment;
import almekh.com.example.android7281.casadocodigo.model.Livro;

public class MainActivity extends AppCompatActivity implements LivrosDelegate, FragmentManager.OnBackStackChangedListener{

    private ListaLivrosFragment listaLivrosFragment;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        listaLivrosFragment = new ListaLivrosFragment();
        transaction.replace(R.id.frame_principal, listaLivrosFragment);
        transaction.commit();

        new WebClient().getLivros(0, 10);

        EventBus.getDefault().register(this);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        mostrarVoltar();

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void lidaComLivrosSelecionado(Livro livro) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetalheLivroFragment detalheLivroFragment = criaDetalhesCom(livro);
        transaction.replace(R.id.frame_principal, detalheLivroFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Subscribe
    public void lidaComSucesso(LivroEvent livroEvent) {
        listaLivrosFragment.populaListaCom(livroEvent.getLivros());
    }

    @Subscribe
    public void lidaComErro(Throwable erro) {
        Toast.makeText(this, "Não foi possível carregar os livros...", Toast.LENGTH_SHORT).show();
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


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main_menu, menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {

         switch (item.getItemId()) {
             case R.id.vai_para_carrinho:
                 Intent vaiParaCarrinho = new Intent(this, CarrinhoActivity.class);
                 startActivity(vaiParaCarrinho);
                 return true;
             case R.id.logout:
                 mAuth.signOut();
                 Intent logout = new Intent(this, LoginActivity.class);
                 startActivity(logout);
                 finish();
             default:
                 return super.onOptionsItemSelected(item);
         }
    }

}
