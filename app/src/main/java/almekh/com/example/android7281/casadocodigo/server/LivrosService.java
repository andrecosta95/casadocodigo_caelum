package almekh.com.example.android7281.casadocodigo.server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import almekh.com.example.android7281.casadocodigo.model.Livro;

/**
 * Created by android7281 on 05/09/17.
 */

public interface LivrosService {

    @GET("listarLivros?indicePrimeiroLivro=0&qtdLivros=20")
    Call<List<Livro>> listLivros();
}
