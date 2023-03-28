import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;


public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        //fazer uma conexão http e buscar os 250 filmes melhor avaliados

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // extrair os dados que interessam (titulo, imagem, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);


        // exibir e manipular os dados (fazer o de series tbm)
        var sticker = new StickerGenerator();
        var diretorio = new File("Figurinhas/");
        diretorio.mkdir();
        String textoDaImagem = "";

        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "Figurinhas/" + titulo + ".png";


            System.out.println("\u001b[1m|Título: \u001b[m" + titulo);

            // System.out.println("\u001b[1m|Poster: " + filme.get("image"));

            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) classificacao;

            InputStream mySelf = null;
            if (classificacao >= 8) {
                textoDaImagem = "Muito bom";
                mySelf = new FileInputStream(new File("sobreposicao/Eu_resized.jpg"));
            }else {
                textoDaImagem = "Mehh";
            }

            System.out.println("" +
                    "\u001b[43m\u001b[35m" +
                    "Classificação: " +
                    classificacao +
                    " \u001b[m");

            sticker.createImage(inputStream, nomeArquivo, textoDaImagem, mySelf);

            for (int n = 1; n <= numeroEstrelinhas; n++)
                System.out.print("⭐");
            System.out.println("\n");
        }

    }

    //construir uma classe futuramente para manipular os atributos do texto e para organizar todo o código existente aqui nessa classe

}
