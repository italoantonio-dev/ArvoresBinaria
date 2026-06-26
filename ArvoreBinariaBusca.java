import java.util.ArrayList;
import java.util.List;



public class ArvoreBinariaBusca<T extends Comparable<T>> {

    //  Classe interna  representa um nó da árvore 
    private class No {
        T valor;
        No esquerda;
        No direita;

        No(T valor) {
            this.valor = valor;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private No raiz;     // referencia para o nó raiz da árvore
    private int tamanho; // quantidade de elementos 

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.tamanho = 0;
    }

    //INSERÇÃO
  
    public void inserir(T valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No atual, T valor) {
        if (atual == null) {
            tamanho++;
            return new No(valor); // posicao correta encontrada cria o nó
        }
        int cmp = valor.compareTo(atual.valor);
        if (cmp < 0) {
            atual.esquerda = inserirRecursivo(atual.esquerda, valor);
        } else if (cmp > 0) {
            atual.direita = inserirRecursivo(atual.direita, valor);
        }
        // se cmp == 0 o valor ja existe -> nao insere duplicado
        return atual;
    }

    //BUSCA 
    
    public boolean buscar(T valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(No atual, T valor) {
        if (atual == null) return false; // chegou ao fim sem achar
        int cmp = valor.compareTo(atual.valor);
        if (cmp == 0) return true;
        return cmp < 0
                ? buscarRecursivo(atual.esquerda, valor)
                : buscarRecursivo(atual.direita, valor);
    }

    //REMOÇÃO 3 casoss
    /**
    Remove um valor da árvore. Existem 3 casos clássicos:
     
      CASO 1 - Nó é FOLHA (sem filhos):
           basta removê-lo; o pai passa a apontar para null no lugar dele.
     
       CASO 2 - Nó tem APENAS UM FILHO:
           o filho único "sobe" e assume o lugar do nó removido.
     
       CASO 3 - Nó tem DOIS FILHOS:
           não dá para simplesmente removê-lo (perderíamos uma subárvore
           inteira). Estratégia: copiar para esse nó o valor do seu
           SUCESSOR (o menor valor da subárvore direita) e depois
      remover o sucessor de lá remoção que sempre cai no
        caso 1 ou no caso 2.
     
      Complexidade: O(h)
     */
    public void remover(T valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    private No removerRecursivo(No atual, T valor) {
        if (atual == null) return null; // valor não está na árvore

        int cmp = valor.compareTo(atual.valor);

        if (cmp < 0) {
            atual.esquerda = removerRecursivo(atual.esquerda, valor);
        } else if (cmp > 0) {
            atual.direita = removerRecursivo(atual.direita, valor);
        } else {
            // Achamos o nó a ser removido

            // CASO 1: folha sem filhos
            if (atual.esquerda == null && atual.direita == null) {
                tamanho--;
                return null;
            }

            // CASO 2: apenas um filho
            if (atual.esquerda == null) {
                tamanho--;
                return atual.direita; // filho direito 
            }
            if (atual.direita == null) {
                tamanho--;
                return atual.esquerda; // filho esquerdo 
            }

            //caso 3: dois filhos
            // busca o sucessor: menor valor da sub arvore direita
            No sucessor = encontrarMinimo(atual.direita);
            atual.valor = sucessor.valor; // copia o valor do sucessor para este no
            // remove o sucessor de dentro da subárvore direita
            // essa chamada recai no caso 1 ou no caso 2, nunca no caso 3 de novo
            atual.direita = removerRecursivo(atual.direita, sucessor.valor);
        }
        return atual;
    }

    private No encontrarMinimo(No nodo) {
        while (nodo.esquerda != null) {
            nodo = nodo.esquerda;
        }
        return nodo;
    }

    //PERCURSOS
    /** Pré-ordem: Raiz -> Esquerda -> Direita (útil para recriar/copiar a árvore) */
    public List<T> preOrdem() {
        List<T> resultado = new ArrayList<>();
        preOrdemRecursivo(raiz, resultado);
        return resultado;
    }

    private void preOrdemRecursivo(No atual, List<T> resultado) {
        if (atual == null) return;
        resultado.add(atual.valor);                    // 1. visita a raiz
        preOrdemRecursivo(atual.esquerda, resultado);   // 2. esquerda
        preOrdemRecursivo(atual.direita, resultado);    // 3. direita
    }

    /** Em-ordem: Esquerda -> Raiz -> Direita. */
    public List<T> emOrdem() {
        List<T> resultado = new ArrayList<>();
        emOrdemRecursivo(raiz, resultado);
        return resultado;
    }

    private void emOrdemRecursivo(No atual, List<T> resultado) {
        if (atual == null) return;
        emOrdemRecursivo(atual.esquerda, resultado);    // 1. esquerda
        resultado.add(atual.valor);                     // 2. raiz
        emOrdemRecursivo(atual.direita, resultado);      // 3. direita
    }

    /** Pos ordem: Esquerda -> Direita -> Raiz (uyil para liberar/deletar a árvore) */
    public List<T> posOrdem() {
        List<T> resultado = new ArrayList<>();
        posOrdemRecursivo(raiz, resultado);
        return resultado;
    }

    private void posOrdemRecursivo(No atual, List<T> resultado) {
        if (atual == null) return;
        posOrdemRecursivo(atual.esquerda, resultado);   // 1. esquerda
        posOrdemRecursivo(atual.direita, resultado);     // 2. direita
        resultado.add(atual.valor);                      // 3. raiz
    }

    // ================= MÉTODOS AUXILIARES =================

    /** Altura da árvore: número de arestas do caminho mais longo até uma folha */
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(No nodo) {
        if (nodo == null) return -1; // convenção: árvore vazia tem altura -1
        return 1 + Math.max(alturaRecursiva(nodo.esquerda), alturaRecursiva(nodo.direita));
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean vazia() {
        return raiz == null;
    }

    //DEMONSTRAÇÃO
    public static void main(String[] args) {
        ArvoreBinariaBusca<Integer> arvore = new ArvoreBinariaBusca<>();

        int[] valores = {50, 30, 70, 20, 40, 60, 80, 10};
        System.out.println("Inserindo: " + java.util.Arrays.toString(valores));
        for (int v : valores) {
            arvore.inserir(v);
        }

        System.out.println("\nTamanho: " + arvore.tamanho());
        System.out.println("Altura : " + arvore.altura());

        System.out.println("\nPré-ordem : " + arvore.preOrdem());
        System.out.println("Em-ordem  : " + arvore.emOrdem());
        System.out.println("Pós-ordem : " + arvore.posOrdem());

        System.out.println("\nBuscar 40 -> " + arvore.buscar(40));
        System.out.println("Buscar 99 -> " + arvore.buscar(99));

        System.out.println("\n--- CASO 1: removendo nó FOLHA (80) ---");
        arvore.remover(80);
        System.out.println("Em-ordem: " + arvore.emOrdem());

        System.out.println("\n--- CASO 2: removendo nó com UM FILHO (20, cujo único filho é 10) ---");
        arvore.remover(20);
        System.out.println("Em-ordem: " + arvore.emOrdem());

        System.out.println("\n--- CASO 3: removendo nó com DOIS FILHOS (30, filhos 10 e 40) ---");
        arvore.remover(30);
        System.out.println("Em-ordem: " + arvore.emOrdem());
    }
}
