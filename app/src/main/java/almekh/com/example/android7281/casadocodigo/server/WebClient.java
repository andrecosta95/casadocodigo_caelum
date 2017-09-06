package almekh.com.example.android7281.casadocodigo.server;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import almekh.com.example.android7281.casadocodigo.delegate.LivrosDelegate;
import almekh.com.example.android7281.casadocodigo.event.LivroEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import almekh.com.example.android7281.casadocodigo.model.Livro;
import almekh.com.example.android7281.casadocodigo.converter.LivroServiceConverterFactory;

/**
 * Created by android7281 on 05/09/17.
 */

public class WebClient {

    private static final String SERVER_URL = "http://cdcmob.herokuapp.com/";

    public void getLivros(int indicePrimeiroLivro, int qtdLivros) {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(new LivroServiceConverterFactory())
                .build();

        LivrosService service = client.create(LivrosService.class);

        Call<List<Livro>> call = service.listLivros(indicePrimeiroLivro, qtdLivros);

        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                EventBus.getDefault().post(new LivroEvent(response.body()));
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                EventBus.getDefault().post(t);
            }
        });

    }
}
