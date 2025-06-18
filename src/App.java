import estruturas.ArvoreBinariaPesquisa;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App {

    public void rodarAplicacao() {
        ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();

        String[] linhas = new String[1000];
        int totalLinhas = 0;

        try (BufferedReader leitor = new BufferedReader(new FileReader("entrada.txt"))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linhas[totalLinhas++] = linha.trim();
            }
        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo: " + erro.getMessage());
            return;
        }

        for (int indice = 0; indice < totalLinhas - 1; indice++) {
            int valor = Integer.parseInt(linhas[indice]);
            arvore.adicionar(valor);
        }

        int chaveBusca = Integer.parseInt(linhas[totalLinhas - 1]);

        escreverArquivo("preordem.txt", arvore.preOrdem());
        escreverArquivo("posordem.txt", arvore.posOrdem());
        escreverArquivo("central.txt", arvore.central());
        escreverArquivo("largura.txt", arvore.largura());

        ArvoreBinariaPesquisa.ResultadoBusca resultadoBusca = arvore.buscar(chaveBusca);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("resultado.txt"))) {
            for (int i = 0; i < resultadoBusca.visitados; i++) {
                escritor.write(resultadoBusca.percurso[i] + "\n");
            }
            escritor.write("Nodos visitados: " + resultadoBusca.visitados + "\n");
            escritor.write(resultadoBusca.achou ? "Achou\n" : "Não Achou\n");
        } catch (IOException erro) {
            System.out.println("Erro ao escrever resultado.txt");
        }
    }

    private void escreverArquivo(String nomeArquivo, int[] dados) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (int i = 0; i < dados.length; i++) {
                escritor.write(dados[i] + "\n");
            }
        } catch (IOException erro) {
            System.out.println("Erro ao escrever " + nomeArquivo);
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.rodarAplicacao();
    }
}