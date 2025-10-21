# Integrantes
Arthur Hagel e Gabriel Leal
# Breve Descrição do Projeto
- O programa tem um sistema simples para gerenciar estudantes,disciplinas e notas usando coleções java
- Ele cadastra/remove/ordena estudantes
- Cadastra disciplinas evitando duplicadas
- Registrar matriculas para cada estudante (disciplina)

- Segue 4 partes do enunciado: A-List,B-Set,C- Map,D-Integração1)


Gerar relatórios: lista de estudantes (original e ordenada), disciplinas (ordem de inserção), matrículas com médias, médias por disciplina, top N por média, alunos com média ≥ 8.0, disciplinas com média < 6.0 e detecção de duplicatas ao importar.
O código segue as partes A (List), B (Set), C (Map) e D (integração) com dataset de exemplo do PDF.



---

2) Justificativa das escolhas de coleções e implementações

List<Estudante> — ArrayList<Estudante>
Mantém ordem de inserção e permite acesso rápido por índice. É simples de ordenar.
Alternativa: LinkedList seria útil apenas com muitas inserções/remoções no meio.

Set<Disciplina> — LinkedHashSet<Disciplina>
Evita duplicatas e mantém a ordem de inserção, como exigido.
Alternativa: TreeSet ordenaria automaticamente, mas o enunciado pede manter a ordem original.

Map<Integer, List<Matricula>> — HashMap
Modela bem a relação estudante → matrículas. Inserção e acesso rápidos.
Alternativa: Map<Integer, Map<String, Double>> facilitaria buscas diretas, mas List<Matricula> simplifica a iteração e segue o enunciado.



---

3) Como executar o programa

1. Compilar
No terminal, na pasta do arquivo Main.java:

Linux/macOS: javac Main.java

Windows: javac Main.java



2. Executar

Mostrar no terminal: java Main

Ou salvar a saída em arquivo:

Linux/macOS: java Main > output.txt

Windows: java Main > output.txt






---

4) Desafios encontrados

1. Duplicatas em disciplinas: resolvido sobrescrevendo equals e hashCode com toLowerCase().


2. Médias sem notas: uso de average().orElse(0.0) para evitar erros.


3. Estrutura do Map: escolha entre lista simples ou mapa interno — priorizou-se simplicidade.


4. Formatação de saída: padronização com printf/String.format.


5. Extras (Pokémons e receitas): exigem apenas novos atributos e 
1) Breve descrição do projeto
O programa é um sistema simples para gerenciar estudantes, disciplinas e notas usando coleções Java.
Principais funcionalidades:

Cadastrar, remover e ordenar estudantes.

Cadastrar disciplinas evitando duplicatas.

Registrar matrículas (disciplina + nota) por estudante.

Gerar relatórios: lista de estudantes (original e ordenada), disciplinas (ordem de inserção), matrículas com médias, médias por disciplina, top N por média, alunos com média ≥ 8.0, disciplinas com média < 6.0 e detecção de duplicatas ao importar.
O código segue as partes A (List), B (Set), C (Map) e D (integração) com dataset de exemplo do PDF.



---

2) Justificativa das escolhas de coleções e implementações

List<Estudante> — ArrayList<Estudante>
Mantém ordem de inserção e permite acesso rápido por índice. É simples de ordenar.
Alternativa: LinkedList seria útil apenas com muitas inserções/remoções no meio.

Set<Disciplina> — LinkedHashSet<Disciplina>
Evita duplicatas e mantém a ordem de inserção, como exigido.
Alternativa: TreeSet ordenaria automaticamente, mas o enunciado pede manter a ordem original.

Map<Integer, List<Matricula>> — HashMap
Modela bem a relação estudante → matrículas. Inserção e acesso rápidos.
Alternativa: Map<Integer, Map<String, Double>> facilitaria buscas diretas, mas List<Matricula> simplifica a iteração e segue o enunciado.



---

3) Como executar o programa

1. Compilar
No terminal, na pasta do arquivo Main.java:

Linux/macOS: javac Main.java

Windows: javac Main.java



2. Executar

Mostrar no terminal: java Main

Ou salvar a saída em arquivo:

Linux/macOS: java Main > output.txt

Windows: java Main > output.txt






---

4) Desafios encontrados

1. Duplicatas em disciplinas: resolvido sobrescrevendo equals e hashCode com toLowerCase().


2. Médias sem notas: uso de average().orElse(0.0) para evitar erros.


3. Estrutura do Map: escolha entre lista simples ou mapa interno — priorizou-se simplicidade.


4. Formatação de saída: padronização com printf/String.format.


5. Extras (Pokémons e receitas): exigem apenas novos atributos e listas simples.


6. Validação: foram tratadas apenas situações básicas; em produção seria necessário validar entradas e exceções.



