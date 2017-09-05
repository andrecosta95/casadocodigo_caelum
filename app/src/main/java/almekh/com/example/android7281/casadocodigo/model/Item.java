package almekh.com.example.android7281.casadocodigo.model;

/**
 * Created by android7281 on 05/09/17.
 */

public class Item {


    private Livro livro;
    private TipoDeCompra tipoDeCompra;

    public Item(Livro livro, TipoDeCompra tipoDeCompra) {
        this.livro = livro;
        this.tipoDeCompra = tipoDeCompra;
    }

    public Livro getLivro() {
        return livro;
    }

    public TipoDeCompra getTipoDeCompra() {
        return tipoDeCompra;
    }

    public double getValor() {
        switch (tipoDeCompra) {
            case FISICO:
                return livro.getValorFisico();

            case VIRTUAL:
                return livro.getValorVirtual();

            default:
                return livro.getValorDoisJuntos();

        }
    }
}
