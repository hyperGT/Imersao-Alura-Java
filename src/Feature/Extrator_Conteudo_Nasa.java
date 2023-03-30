package Feature;

import Interface.Extrator_Conteudo;
import Util.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Extrator_Conteudo_Nasa implements Extrator_Conteudo {

    //extração de dados da API Nasa APOD
    public List<Conteudo> extraiConteudos(String json){

    // extrair os dados que interessam (titulo, imagem, classificação)
    var parser = new JsonParser();
    List<Map<String, String>> listaDeAtributos = parser.parse(json);

    List<Conteudo> conteudos = new ArrayList<>();

    // popular a lista de conteúdos
    for (Map<String, String> atributos : listaDeAtributos){
        String titulo = atributos.get("title");
        String urlImagem = atributos.get("url");
        var conteudo = new Conteudo(titulo, urlImagem);

        conteudos.add(conteudo);
    }

    return conteudos;
    }
}
