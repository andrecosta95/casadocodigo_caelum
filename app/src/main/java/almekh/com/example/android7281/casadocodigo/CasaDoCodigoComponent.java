package almekh.com.example.android7281.casadocodigo;

import javax.inject.Singleton;

import almekh.com.example.android7281.casadocodigo.activity.CarrinhoActivity;
import almekh.com.example.android7281.casadocodigo.fragment.DetalheLivroFragment;
import almekh.com.example.android7281.casadocodigo.fragment.ListaLivrosFragment;
import almekh.com.example.android7281.casadocodigo.model.CasaDoCodigoModule;
import dagger.Component;

/**
 * Created by android7281 on 08/09/17.
 */

@Singleton
@Component(modules = CasaDoCodigoModule.class)
public interface CasaDoCodigoComponent {

    void inject(DetalheLivroFragment fragment);
    void inject(ListaLivrosFragment fragment);
    void inject(CarrinhoActivity activity);
}
