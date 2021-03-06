package almekh.com.example.android7281.casadocodigo.model;

import java.io.Serializable;

/**
 * Created by android7281 on 04/09/17.
 */

public class Autor implements Serializable{

    private long id;
    private String nome;
    private String biografia;
    private String urlFoto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

}