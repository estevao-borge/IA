# IA

#Script
1º Criar um estado INITIAL(1º elemento) e GOAL(2º elemento) a partir da configuração inicial.
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
        
#Observações
- Preparar uma implementação do algoritmo de forma bem geral, para que possa ser usado em qualquer tipo de problema. Pode ser resolvido definindo uma classe para resolver cada tipo de problema.
- Criar uma classe Node onde terá os seguintes argumentos: CONFIG(sua respetiva configuração), COST(o seu custo, que é o custo do pai +1) e REFERENCE(a referência para o próprio pai, o pai do estado inicial é null).
- Estados que se encontram nas diagonais possuem no máximo 2 filhos. Estados que se encontram entre diagonais possuem no máximo 3 filhos. Estados que se encontram no meio possuem no máximo 4 filhos.
