package Config;

import Feature.Extrator_Conteudo_Imdb;
import Feature.Extrator_Conteudo_Nasa;
import Interface.Extrator_Conteudo;

public enum API {

    IMDB_TOP_FILMES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new Extrator_Conteudo_Imdb()),
    NASA_APOD("https://api.nasa.gov/planetary/apod?api_key=VwhksOhb4HB3W34sEsWcsKFkuGYzR5pcpNatAumL&start_date=2022-06-12&end_date=2022-06-14", new Extrator_Conteudo_Nasa());

    private final String url;
    private final Extrator_Conteudo extrator;

    API(String url, Extrator_Conteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return url;
    }

    public Extrator_Conteudo getExtrator() {
        return extrator;
    }
}
