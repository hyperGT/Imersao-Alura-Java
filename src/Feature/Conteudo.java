package Feature;

public class Conteudo {

    private String titulo;
    private String urlImagem;

    private String imdbRating;

    public Conteudo(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public Conteudo(String titulo, String urlImagem) {
        this.titulo = titulo;
        this.urlImagem = urlImagem;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }
}
