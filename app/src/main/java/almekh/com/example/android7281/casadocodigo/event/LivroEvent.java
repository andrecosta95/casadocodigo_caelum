package almekh.com.example.android7281.casadocodigo.event;

import java.util.List;

import almekh.com.example.android7281.casadocodigo.model.Livro;

/**
 * Created by android7281 on 05/09/17.
 */

public class LivroEvent {

    private final List<Livro> livros;

    public LivroEvent(List<Livro> livros){
        this.livros = livros;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
