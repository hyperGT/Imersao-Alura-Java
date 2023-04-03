package Feature;

import Interface.Extrator_Conteudo;
import Util.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Extrator_Conteudo_Imdb implements Extrator_Conteudo {

    //extração de dados da API Nasa APOD
    public List<Conteudo> extraiConteudos(String json) {

        // extrair os dados que interessam (titulo, imagem, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        //uso de stream e lambda para diminuir as linhas de código e popular a lista de conteudos
        return listaDeAtributos.stream()
                .map((atributos) -> new Conteudo(atributos.get("title"), atributos.get("image")))
                .toList();

    }
}
