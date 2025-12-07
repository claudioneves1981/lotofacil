import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    static Map<Character, Integer> combinacoes = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);
    static LinkedHashSet<Integer> numeros = new LinkedHashSet<>();
    static Map<Integer, Character> reverseMap = new HashMap<>();
    static StringBuilder texto = new StringBuilder();

    public static void main(String[] args) {

        int index = 'A';
        int num1;
        int opcao1;

        System.out.println("Informe a quantidade de numeros fixos");
        opcao1 = scanner.nextInt();

        System.out.println("Digite "+opcao1+" numeros:");

        for (int i = index; i < (index + opcao1); i++) {

            num1 = scanner.nextInt();
            numeros.add(num1);

        }

        Set<Integer> numerosAleatorios = new HashSet<>();

        Random random = new Random();

        while (numerosAleatorios.size() < 11) {
            int num = random.nextInt(1, 25);

            if (!numeros.contains(num)) { // não pode ser igual aos fixos
                numerosAleatorios.add(num);    // Set garante que não repete
            }
        }

        numeros.addAll(numerosAleatorios);


        List<Integer> listNumeros = numeros.stream().toList();

        int cont = 0;
        for(int i = index; i < numeros.size()+index; i++){


            combinacoes.put((char)i,listNumeros.get(cont));
            reverseMap.put(listNumeros.get(cont),(char)i);
            cont++;

        }

        texto.append("Sequencia Base: 18 numeros\n").append(combinacoes).append("\n\n");

        mapResult();

    }


    public static void mapResult(){

        String nomeArquivo = "lotofacil_combinacoes.txt";
        int cont = 0;

        texto.append("Jogos\n");

        for (List<Integer> conjuntoJogo : quantidadeSequencias()) {

            Map<Character, Integer> mapResult = new HashMap<>();

            for (int j = 0; j < 15; j++) {

                    mapResult.put(reverseMap.get(conjuntoJogo.get(j)), conjuntoJogo.get(j));

            }

            texto.append(++cont).append("-").append(mapResult).append("\n");


        }

        gerarArquivo(texto,nomeArquivo);

    }

    public static void gerarArquivo(StringBuilder texto, String nomeArquivo){

        try (FileWriter bw = new FileWriter(nomeArquivo)) {

            bw.write(String.valueOf(texto));

            System.out.println("Dados gravados com sucesso no arquivo: " + nomeArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<Integer> gerarSequenciaAleatoria(List<Integer> numeros, int tamanho) {
        List<Integer> numerosCopia = new ArrayList<>(numeros);
        Collections.shuffle(numerosCopia, new Random());
        return numerosCopia.subList(0, tamanho);
    }

    public static List<List<Integer>> quantidadeSequencias(){

        List<List<Integer>> conjuntoJogos = new ArrayList<>();

        List<Integer> numerosFixos = numeros.stream().toList();

        System.out.println("Digite a quantidade de sequencias:");

        int tamanhoSequencia = 15;
        int quantidadeSequencias = scanner.nextInt();

        for (int i = 0; i < quantidadeSequencias; i++) {
            List<Integer> sequencia = gerarSequenciaAleatoria(numerosFixos, tamanhoSequencia);
            conjuntoJogos.add(sequencia);
        }

        return conjuntoJogos;
    }
}