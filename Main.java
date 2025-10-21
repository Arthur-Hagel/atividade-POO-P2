import java.util.*;
import java.util.stream.Collectors;

// ===== Classe Estudante =====
class Estudante {
    private int id;
    private String nome;

    public Estudante(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}

// ===== Classe Disciplina =====
class Disciplina implements Comparable<Disciplina> {
    private String codigo;
    private String nome;

    public Disciplina(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disciplina)) return false;
        Disciplina d = (Disciplina) o;
        return codigo.equalsIgnoreCase(d.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.toLowerCase().hashCode();
    }

    @Override
    public int compareTo(Disciplina d) {
        return this.codigo.compareToIgnoreCase(d.codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + nome;
    }
}

// ===== Classe Matricula =====
class Matricula {
    private String codigoDisciplina;
    private double nota;

    public Matricula(String codigoDisciplina, double nota) {
        this.codigoDisciplina = codigoDisciplina;
        this.nota = nota;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return codigoDisciplina + "(" + nota + ")";
    }
}

// ===== Parte A - Lista de Estudantes =====
class ListaEstudantes {
    private List<Estudante> estudantes = new ArrayList<>();

    public void adicionarEstudante(Estudante e) {
        estudantes.add(e);
    }

    public void removerEstudantePorId(int id) {
        estudantes.removeIf(e -> e.getId() == id);
    }

    public Estudante obterEstudantePorIndice(int indice) {
        return estudantes.get(indice);
    }

    public List<Estudante> buscarEstudantesPorNome(String substring) {
        return estudantes.stream()
                .filter(e -> e.getNome().toLowerCase().contains(substring.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void ordenarEstudantesPorNome() {
        estudantes.sort(Comparator.comparing(Estudante::getNome));
    }

    public List<Estudante> getEstudantes() {
        return estudantes;
    }
}

// ===== Parte B - Cadastro de Disciplinas =====
class CadastroDisciplinas {
    private Set<Disciplina> disciplinas = new LinkedHashSet<>();

    public void adicionarDisciplina(Disciplina d) {
        disciplinas.add(d);
    }

    public boolean verificarDisciplina(String codigo) {
        return disciplinas.stream().anyMatch(d -> d.getCodigo().equalsIgnoreCase(codigo));
    }

    public void removerDisciplina(String codigo) {
        disciplinas.removeIf(d -> d.getCodigo().equalsIgnoreCase(codigo));
    }

    public Set<Disciplina> obterTodasDisciplinas() {
        return disciplinas;
    }
}

// ===== Parte C - Histórico de Notas =====
class HistoricoNotas {
    private Map<Integer, List<Matricula>> historico = new HashMap<>();

    public void adicionarMatricula(int idEstudante, String codigoDisciplina, double nota) {
        historico.computeIfAbsent(idEstudante, _ -> new ArrayList<>())
                .add(new Matricula(codigoDisciplina, nota));
    }

    public List<Matricula> obterMatriculas(int idEstudante) {
        return historico.getOrDefault(idEstudante, new ArrayList<>());
    }

    public Optional<Double> obterNota(int idEstudante, String codigoDisciplina) {
        return historico.getOrDefault(idEstudante, new ArrayList<>())
                .stream()
                .filter(m -> m.getCodigoDisciplina().equalsIgnoreCase(codigoDisciplina))
                .map(Matricula::getNota)
                .findFirst();
    }

    public void removerMatricula(int idEstudante, String codigoDisciplina) {
        historico.getOrDefault(idEstudante, new ArrayList<>())
                .removeIf(m -> m.getCodigoDisciplina().equalsIgnoreCase(codigoDisciplina));
    }

    public double mediaDoEstudante(int idEstudante) {
        List<Matricula> mats = historico.getOrDefault(idEstudante, new ArrayList<>());
        return mats.stream().mapToDouble(Matricula::getNota).average().orElse(0.0);
    }

    public double mediaDaDisciplina(String codigoDisciplina) {
        List<Double> notas = new ArrayList<>();
        for (List<Matricula> mats : historico.values()) {
            mats.stream()
                    .filter(m -> m.getCodigoDisciplina().equalsIgnoreCase(codigoDisciplina))
                    .forEach(m -> notas.add(m.getNota()));
        }
        return notas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public List<Integer> topNEstudantesPorMedia(int n) {
        return historico.keySet().stream()
                .sorted((a, b) -> Double.compare(mediaDoEstudante(b), mediaDoEstudante(a)))
                .limit(n)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Matricula>> getHistorico() {
        return historico;
    }
}

// ===== Parte D - Programa Principal =====
public class Main {
    public static void main(String[] args) {
        // Criando listas e cadastros
        ListaEstudantes lista = new ListaEstudantes();
        CadastroDisciplinas cad = new CadastroDisciplinas();
        HistoricoNotas hist = new HistoricoNotas();

        // Dataset de exemplo
        lista.adicionarEstudante(new Estudante(1, "Ana"));
        lista.adicionarEstudante(new Estudante(2, "Bruno"));
        lista.adicionarEstudante(new Estudante(3, "Carla"));
        lista.adicionarEstudante(new Estudante(4, "Diego"));
        lista.adicionarEstudante(new Estudante(5, "Elisa"));

        cad.adicionarDisciplina(new Disciplina("MAT101", "Matemática"));
        cad.adicionarDisciplina(new Disciplina("PRG201", "Programação"));
        cad.adicionarDisciplina(new Disciplina("BD301", "Banco de Dados"));
        cad.adicionarDisciplina(new Disciplina("EDF110", "Educação Física"));

        hist.adicionarMatricula(1, "MAT101", 8.5);
        hist.adicionarMatricula(1, "PRG201", 9.0);
        hist.adicionarMatricula(2, "PRG201", 7.0);
        hist.adicionarMatricula(3, "BD301", 6.5);
        hist.adicionarMatricula(4, "PRG201", 8.0);
        hist.adicionarMatricula(5, "EDF110", 10.0);

        // Exibições
        System.out.println("== Lista de Estudantes (ordem de cadastro) ==");
        lista.getEstudantes().forEach(System.out::println);

        lista.ordenarEstudantesPorNome();
        System.out.println("\n== Lista de Estudantes (ordenada) ==");
        lista.getEstudantes().forEach(e -> System.out.print(e.getNome() + ", "));

        System.out.println("\n\n== Disciplinas (inserção) ==");
        cad.obterTodasDisciplinas().forEach(System.out::println);

        System.out.println("\n== Matrículas ==");
        for (Estudante e : lista.getEstudantes()) {
            List<Matricula> mats = hist.obterMatriculas(e.getId());
            double media = hist.mediaDoEstudante(e.getId());
            System.out.println(e.getNome() + ": " + mats + "  Média: " + media);
        }

        System.out.println("\n== Médias por Disciplina ==");
        for (Disciplina d : cad.obterTodasDisciplinas()) {
            System.out.println(d.getCodigo() + ": " + hist.mediaDaDisciplina(d.getCodigo()));
        }

        System.out.println("\n== Top 3 alunos por média ==");
        List<Integer> top = hist.topNEstudantesPorMedia(3);
        for (int i = 0; i < top.size(); i++) {
            int id = top.get(i);
            Estudante e = lista.getEstudantes().stream().filter(es -> es.getId() == id).findFirst().get();
            System.out.printf("%d) %s - %.2f%n", i + 1, e.getNome(), hist.mediaDoEstudante(id));
        }

        System.out.println("\n== Alunos com média >= 8.0 ==");
        lista.getEstudantes().stream()
                .filter(e -> hist.mediaDoEstudante(e.getId()) >= 8.0)
                .forEach(e -> System.out.print(e.getNome() + ", "));

        System.out.println("\n\n== Disciplinas com média < 6.0 ==");
        cad.obterTodasDisciplinas().stream()
                .filter(d -> hist.mediaDaDisciplina(d.getCodigo()) < 6.0)
                .forEach(d -> System.out.print(d.getCodigo() + ", "));
    }
}