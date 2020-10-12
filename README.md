# IA

1º Criar um estado INITIAL a partir da configuração inicial.
2° Criar uma fila com prioridade ABERTOS para estados não testados (Prioridade tem relação com o menor custo).
3° Criar uma lista FECHADOS para estados já testados.
4º Inserir o estado INITIAL em ABERTOS.
5º Repetir os comandos abaixo até que uma solução seja encontrada e enquanto houver memória.
  6º Se a fila ABERTOS estiver vazia, sair com fracasso (Lança uma excessão).
  7º Estado ACTUAL = 1º da fila ABERTOS.
  8º Remover ACTUAL de ABERTOS.
  9º Se a configuração do estado ACTUAL for igual à configuração GOAL.
    - Mostrar a sequência desde INITIAL até GOAL.
  10° Senão.
    - SUCESSORES = todos os filhos do estado ACTUAL.
    - Inserir ACTUAL na lista FECHADOS.
    - Para cada SUCESSOR em SUCESSORES.
      - Se SUCESSOR não existir em FECHADOS.
        - Inserir SUCESSOR em ABERTOS.
