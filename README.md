# Árvore Binária de Busca (BST)

Implementação genérica de uma **Árvore Binária de Busca** em Java, com suporte a qualquer tipo que implemente `Comparable<T>`.

## Arquivo principal

| Arquivo | Descrição |
|---|---|
| `ArvoreBinariaBusca.java` | Implementação completa da BST genérica |
| `Estruturas_de_Dados_Árvores.pdf` | Apresentação teórica sobre árvores binárias |

## Funcionalidades

### Operações principais
- `inserir(T valor)` — insere um valor (sem duplicatas)
- `buscar(T valor)` — retorna `true` se o valor existe na árvore
- `remover(T valor)` — remove um valor tratando os 3 casos clássicos

### Remoção — 3 casos clássicos

| Caso | Situação | Ação |
|------|----------|------|
| 1 | Nó é **folha** (sem filhos) | Remove diretamente |
| 2 | Nó tem **apenas um filho** | O filho sobe e assume o lugar |
| 3 | Nó tem **dois filhos** | Substitui pelo **sucessor** (menor da subárvore direita) e remove o sucessor |

### Percursos
- `preOrdem()` — Raiz → Esquerda → Direita
- `emOrdem()` — Esquerda → Raiz → Direita *(retorna elementos em ordem crescente)*
- `posOrdem()` — Esquerda → Direita → Raiz

### Métodos auxiliares
- `altura()` — número de arestas do caminho mais longo até uma folha
- `tamanho()` — quantidade de elementos na árvore
- `vazia()` — verifica se a árvore está vazia

## Complexidade

| Operação | Médio   | Pior caso |
|----------|---------|-----------|
| Inserção | O(log n) | O(n)     |
| Busca    | O(log n) | O(n)     |
| Remoção  | O(log n) | O(n)     |
| Percurso | O(n)     | O(n)     |

> O pior caso O(n) ocorre em árvores degeneradas (inserções em ordem crescente/decrescente).

## Como executar

```bash
javac ArvoreBinariaBusca.java
java ArvoreBinariaBusca
```

### Saída esperada

```
Inserindo: [50, 30, 70, 20, 40, 60, 80, 10]

Tamanho: 8
Altura : 3

Pré-ordem : [50, 30, 20, 10, 40, 70, 60, 80]
Em-ordem  : [10, 20, 30, 40, 50, 60, 70, 80]
Pós-ordem : [10, 20, 40, 30, 60, 80, 70, 50]

Buscar 40 -> true
Buscar 99 -> false

--- CASO 1: removendo nó FOLHA (80) ---
Em-ordem: [10, 20, 30, 40, 50, 60, 70]

--- CASO 2: removendo nó com UM FILHO (20, cujo único filho é 10) ---
Em-ordem: [10, 30, 40, 50, 60, 70]

--- CASO 3: removendo nó com DOIS FILHOS (30, filhos 10 e 40) ---
Em-ordem: [10, 40, 50, 60, 70]
```

## Licença

MIT
