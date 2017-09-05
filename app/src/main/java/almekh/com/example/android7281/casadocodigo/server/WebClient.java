package almekh.com.example.android7281.casadocodigo.server;

import java.util.List;

import almekh.com.example.android7281.casadocodigo.delegate.LivrosDelegate;
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
    private LivrosDelegate delegate;
    
    public WebClient(LivrosDelegate delegate){
        this.delegate = delegate;
    }
    
    public void getLivros() {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(new LivroServiceConverterFactory())
                .build();

        LivrosService service = client.create(LivrosService.class);

        Call<List<Livro>> call = service.listLivros();

        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                delegate.lidaComSucesso(response.body());
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                delegate.lidaComErro(t);
            }
        });

    }
}
