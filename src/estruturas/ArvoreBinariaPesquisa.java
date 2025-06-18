package estruturas;

public class ArvoreBinariaPesquisa {

    private class Nodo {
        int chave;
        Nodo esquerda, direita, pai;

        public Nodo(int chave) {
            this.chave = chave;
        }
    }

    private Nodo raiz;
    private int tamanho;

    public ArvoreBinariaPesquisa() {
        raiz = null;
        tamanho = 0;
    }

    public void adicionar(int chave) {
        Nodo novoNodo = new Nodo(chave);
        if (raiz == null) {
            raiz = novoNodo;
        } else {
            adicionarRecursivamente(novoNodo, raiz);
        }
        tamanho++;
    }

    private void adicionarRecursivamente(Nodo novoNodo, Nodo nodoAtual) {
        if (novoNodo.chave <= nodoAtual.chave) {
            if (nodoAtual.esquerda == null) {
                nodoAtual.esquerda = novoNodo;
                novoNodo.pai = nodoAtual;
            } else {
                adicionarRecursivamente(novoNodo, nodoAtual.esquerda);
            }
        } else {
            if (nodoAtual.direita == null) {
                nodoAtual.direita = novoNodo;
                novoNodo.pai = nodoAtual;
            } else {
                adicionarRecursivamente(novoNodo, nodoAtual.direita);
            }
        }
    }

    public int[] preOrdem() {
        int[] resultado = new int[tamanho];
        int[] indiceAtual = {0};
        preOrdemRecursiva(raiz, resultado, indiceAtual);
        return resultado;
    }

    private void preOrdemRecursiva(Nodo nodoAtual, int[] resultado, int[] indiceAtual) {
        if (nodoAtual == null) return;
        resultado[indiceAtual[0]++] = nodoAtual.chave;
        preOrdemRecursiva(nodoAtual.esquerda, resultado, indiceAtual);
        preOrdemRecursiva(nodoAtual.direita, resultado, indiceAtual);
    }

    public int[] posOrdem() {
        int[] resultado = new int[tamanho];
        int[] indiceAtual = {0};
        posOrdemRecursiva(raiz, resultado, indiceAtual);
        return resultado;
    }

    private void posOrdemRecursiva(Nodo nodoAtual, int[] resultado, int[] indiceAtual) {
        if (nodoAtual == null) return;
        posOrdemRecursiva(nodoAtual.esquerda, resultado, indiceAtual);
        posOrdemRecursiva(nodoAtual.direita, resultado, indiceAtual);
        resultado[indiceAtual[0]++] = nodoAtual.chave;
    }

    public int[] central() {
        int[] resultado = new int[tamanho];
        int[] indiceAtual = {0};
        centralRecursiva(raiz, resultado, indiceAtual);
        return resultado;
    }

    private void centralRecursiva(Nodo nodoAtual, int[] resultado, int[] indiceAtual) {
        if (nodoAtual == null) return;
        centralRecursiva(nodoAtual.esquerda, resultado, indiceAtual);
        resultado[indiceAtual[0]++] = nodoAtual.chave;
        centralRecursiva(nodoAtual.direita, resultado, indiceAtual);
    }

    public int[] largura() {
        if (raiz == null) return new int[0];

        Nodo[] fila = new Nodo[tamanho];
        int[] resultado = new int[tamanho];
        int indiceFilaInicio = 0, indiceFilaFim = 0, indiceResultado = 0;

        fila[indiceFilaFim++] = raiz;

        while (indiceFilaInicio < indiceFilaFim) {
            Nodo nodoAtual = fila[indiceFilaInicio++];
            resultado[indiceResultado++] = nodoAtual.chave;
            if (nodoAtual.esquerda != null) fila[indiceFilaFim++] = nodoAtual.esquerda;
            if (nodoAtual.direita != null) fila[indiceFilaFim++] = nodoAtual.direita;
        }

        return resultado;
    }

    public ResultadoBusca buscar(int chaveBuscada) {
        int[] percursoBusca = new int[tamanho];
        int quantidadeVisitados = 0;
        Nodo nodoAtual = raiz;

        while (nodoAtual != null) {
            percursoBusca[quantidadeVisitados++] = nodoAtual.chave;

            if (chaveBuscada == nodoAtual.chave) {
                return new ResultadoBusca(percursoBusca, quantidadeVisitados, true);
            } else if (chaveBuscada < nodoAtual.chave) {
                nodoAtual = nodoAtual.esquerda;
            } else {
                nodoAtual = nodoAtual.direita;
            }
        }

        return new ResultadoBusca(percursoBusca, quantidadeVisitados, false);
    }

    public class ResultadoBusca {
        public int[] percurso;
        public int visitados;
        public boolean achou;

        public ResultadoBusca(int[] percursoOriginal, int totalVisitados, boolean encontrou) {
            this.percurso = new int[totalVisitados];
            System.arraycopy(percursoOriginal, 0, this.percurso, 0, totalVisitados);
            this.visitados = totalVisitados;
            this.achou = encontrou;
        }
    }
}