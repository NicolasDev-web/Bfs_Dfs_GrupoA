import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Mapeamento de Estados para Índices e vice-versa
        String[] indexParaEstado = {"MA", "PI", "CE", "RN", "PB", "PE", "AL", "SE", "BA"};
        Map<String, Integer> estadoParaIndex = new HashMap<>();
        for (int i = 0; i < indexParaEstado.length; i++) {
            estadoParaIndex.put(indexParaEstado[i], i);
        }

        // 2. Carregar o Grafo (Lendo o arquivo gerado)
        // Certifique-se de que o caminho do arquivo está correto na sua IDE
        In in = new In("dados/nordeste.txt");
        Graph G = new Graph(in);

        // 3. Receber Entrada do Usuário
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o estado de origem (ex: CE): ");
        String origem = scanner.nextLine().toUpperCase();
        System.out.print("Digite o estado de destino (ex: BA): ");
        String destino = scanner.nextLine().toUpperCase();

        System.out.println("\nAnalisando o grafo...\n");

        int s = estadoParaIndex.get(origem);
        int d = estadoParaIndex.get(destino);

        // 4. Executar Algoritmos de Busca
        DepthFirstPaths dfs = new DepthFirstPaths(G, s);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        // --- RESPONDENDO ÀS PERGUNTAS DO TRABALHO ---

        // Pergunta 1: É possível alcançar o destino?
        boolean possivelDFS = dfs.hasPathTo(d);
        System.out.println("1) É possível sair de " + origem + " e chegar a " + destino + "?");
        System.out.println("R: " + (possivelDFS ? "Sim." : "Não."));

        if (possivelDFS) {
            // Pergunta 2: Caminho DFS
            System.out.print("\n2) Caminho encontrado pela DFS de " + origem + " até " + destino + ":\nR: ");
            for (int v : dfs.pathTo(d)) {
                System.out.print(indexParaEstado[v] + " ");
            }
            System.out.println();

            // Pergunta 3: Caminho BFS
            System.out.print("\n3) Caminho encontrado pela BFS de " + origem + " até " + destino + ":\nR: ");
            for (int v : bfs.pathTo(d)) {
                System.out.print(indexParaEstado[v] + " ");
            }
            System.out.println();
        }

        // Pergunta 4: Quantidade de estados alcançáveis
        int estadosAlcancaveis = 0;
        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                estadosAlcancaveis++;
            }
        }
        System.out.println("\n4) Quantos estados são alcançáveis a partir de " + origem + "?");
        System.out.println("R: " + estadosAlcancaveis + " estados (incluindo a origem).");

        // Pergunta 5: Ordem de visita DFS
        System.out.print("\n5) Ordem de visita dos estados na execução da DFS a partir de " + origem + ":\nR: ");
        for (int v : dfs.getOrdemVisita()) {
            System.out.print(indexParaEstado[v] + " ");
        }
        System.out.println();

        // Pergunta 6: Ordem de visita BFS
        System.out.print("\n6) Ordem de visita dos estados na execução da BFS a partir de " + origem + ":\nR: ");
        for (int v : bfs.getOrdemVisita()) {
            System.out.print(indexParaEstado[v] + " ");
        }
        System.out.println();

        scanner.close();
    }
}