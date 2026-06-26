# Árvores Binárias

Implementações e exemplos de estruturas de dados baseadas em Árvores Binárias em C/C++.

## Estruturas Implementadas

- **Árvore Binária de Busca (BST)** — inserção, remoção e busca em O(log n) médio
- **Percursos** — pré-ordem, em-ordem e pós-ordem
- **Operações auxiliares** — altura, contagem de nós, verificação de balanceamento

## Estrutura do Projeto

```
ArvoresBinaria/
├── README.md
├── src/
│   ├── bst.c          # Implementação da BST
│   ├── bst.h          # Cabeçalho
│   └── main.c         # Exemplos de uso
└── Makefile
```

## Como Compilar

```bash
make
./arvore
```

Ou manualmente:

```bash
gcc src/main.c src/bst.c -o arvore
./arvore
```

## Conceitos Abordados

| Operação     | Complexidade (médio) | Complexidade (pior) |
|--------------|----------------------|----------------------|
| Inserção     | O(log n)             | O(n)                |
| Busca        | O(log n)             | O(n)                |
| Remoção      | O(log n)             | O(n)                |
| Percurso     | O(n)                 | O(n)                |

## Licença

MIT
