import AppRequest.StickerGenerator;
import Client.ClienteHttp;
import Client.MyException;
import Config.API;
import Feature.Conteudo;
import Feature.Extrator_Conteudo_Imdb;
import Interface.Extrator_Conteudo;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class App {

    public static void main(String[] args) throws IOException, MyException {

        //"https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"
        //"https://api.nasa.gov/planetary/apod?api_key=VwhksOhb4HB3W34sEsWcsKFkuGYzR5pcpNatAumL&start_date=2022-06-12&end_date=2022-06-14"

        API apiNasa = API.NASA_APOD;
        API apiImdb = API.IMDB_TOP_FILMES;

        //pega a url
        //String url = apiNasa.getUrl();
        //String url = apiImdb.getUrl();

        String url = "http://localhost:8080/language";
        Extrator_Conteudo extrator = new Extrator_Conteudo_Imdb();

        //fazer uma conexão http e buscar os 250 filmes melhor avaliados
        //Extrator_Conteudo extratorN = apiNasa.getExtrator();
        //Extrator_Conteudo extratorI = apiImdb.getExtrator();



        var http = new ClienteHttp();
        String json = http.buscaDados(url);
        //String jsonIm = http.buscaDados(urlImdb);

        // exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var sticker = new StickerGenerator();
        var diretorio = new File("Figurinhas/");
        diretorio.mkdir();
        String textoDaImagemNasa;

        for (int i = 0; i < 4; i++) {

            Conteudo conteudo;
            conteudo = conteudos.get(i);


            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "Figurinhas/" + conteudo.titulo() + ".png";


           // System.out.println("\u001b[1m|Título: \u001b[m" + conteudo.getTitulo());

            System.out.println(conteudo.titulo());

            //System.out.println("\u001b[1m|Poster: " + conteudo.getUrlImagem());


            textoDaImagemNasa = conteudo.titulo();
            //InputStream mySelf = new FileInputStream(new File("sobreposicaoTeste/Eu_resized.jpg"));
            sticker.createImage(inputStream, nomeArquivo, textoDaImagemNasa);

            //double classificacao = Double.parseDouble(conteudo.getImdbRating());
            //int numeroEstrelinhas = (int) classificacao;
            /*InputStream mySelf = null;
            if (classificacao >= 8) {
                textoDaImagem = "Muito bom";
                //mySelf = new FileInputStream(new File("sobreposicao/Eu_resized.jpg"));
            }else {
                textoDaImagem = "Mehh";
            }

            System.out.println("" +
                    "\u001b[43m\u001b[30m" +
                    "Classificação: " +
                    classificacao +
                    " \u001b[m");

            for (int n = 1; n <= numeroEstrelinhas; n++)
                System.out.print("⭐");
            System.out.println("\n");
*/
        }

    }

    //todo: construir uma classe futuramente para manipular os atributos do texto e para organizar todo o código existente aqui nessa classe

}
