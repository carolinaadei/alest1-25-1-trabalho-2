package estruturas;

public class ArvoreBinariaPesquisa {
    private class Nodo {
        private int chave;
        private Nodo esquerda;
        private Nodo direita;
        private Nodo pai;
        public Nodo(int chave) {
            this.chave = chave;
        }
    }
    private Nodo raiz;
    private int tamanho;

    public ArvoreBinariaPesquisa() {

    }

    public void adicionar(int chave) {
        Nodo n = new Nodo(chave);
        if(raiz==null) raiz = n;
        else {
            adicionarRecursivo(n, raiz);
        }
        tamanho++;
    }
    private void adicionarRecursivo(Nodo n, Nodo pai) {
        if(n.chave <= pai.chave) {
            if(pai.esquerda==null) {
                pai.esquerda = n;
                n.pai = pai;
            }
        }
        else {
            if(pai.direita==null) {
                pai.direita = n;
                n.pai = pai;
            }
        }
    }


}
