package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
	private Double correcao;
	private ArrayList<Node> vertices;
	private ArrayList<Aresta> arestas;
	private HashMap<Node, ArrayList<Aresta>> verticeArestas; // * Hashmap que
																// representa o
																// grafo da
																// seguinte
																// forma: o no é
																// a chave e o
																// valor é a
																// lista de
																// arestas que
																// conectam
																// aquele no a
																// outros nós
																// *\\

	public Graph() {
		this.vertices = new ArrayList<Node>();
		this.arestas = new ArrayList<Aresta>();
		this.verticeArestas = new HashMap<Node, ArrayList<Aresta>>();
		setCorrection(this);
	}

	/**
	 * @return the correcao
	 */
	public Double getCorrecao() {
		return correcao;
	}

	public ArrayList<Aresta> getArestas() {
		return this.arestas;
	}

	public ArrayList<Node> getVertices() {
		return this.vertices;
	}

	public HashMap<Node, ArrayList<Aresta>> getGrafo() {
		return verticeArestas;
	}

	public HashMap<Node, ArrayList<Aresta>> getVerticeArestas() {
		return verticeArestas;
	}

	/**
	 * Metódo criado para ler um arquivo texto e a partir dele gerar o grafo,
	 * que possui uma lista de arestas e uma lista de vertices e um hasmap que
	 * faz a associação: vertices e as arestas que conectam a cada vertice.
	 * 
	 * @param path
	 *            : Caminho do arquivo texto que possui os dados necessários
	 *            para criar um grafo
	 * @throws IOException
	 */
	public void readGraph(File path) throws IOException {
		FileReader arq = new FileReader(path); // Ler o arquivo
		BufferedReader lerArq = new BufferedReader(arq); // Prepara um buffer
															// para a leitura de
															// arquivo
		String linha;
		linha = lerArq.readLine(); // Ler a primeira linha do arquivo
		int qtdVertices = Integer.parseInt(linha);
		ArrayList<Aresta> arestasGrafo; // ArrayList que define para cada
										// vertice do grafo, as arestas que o
										// conectam a outros vertices

		while ((linha = lerArq.readLine()) != null) {
			// primeiro elemento (Node no hasmap): vertice inicial
			// demais elementos(lista de Arestas no hasmap): elementos que se
			// conectam o vertice inicial
			String[] aux = linha.split(" "); // A partir de cada linha do
												// arquivo, cria um array coms
												// os dados da mesma
			Node node1 = new Node(Integer.parseInt(aux[0])); // Cria o primeiro
																// nó (Para
																// definição da
																// aresta)
			Node node2 = new Node(Integer.parseInt(aux[1])); // Cria o segundo
																// nó (Para
																// definição da
																// aresta)
			Aresta aresta = new Aresta(node1, node2); // Cria a aresta
			this.arestas.add(aresta); // Adiciona aresta no ArrayList de todas
										// as arestas existentes no grafo
			// Se o Hashmap grafo nao possui como chave o vertice(node1), ele
			// cria um arraylist que irá salvar todas as arestas que aquele
			// vertice e conectado e a adiciona na lista
			if (!verticeArestas.containsKey(node1)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.verticeArestas.put(node1, arestasGrafo);

			} else { // Caso contrário, apenas adiciona a aresta
				this.verticeArestas.get(node1).add(aresta);
			}
			// Se o Hashmap grafo nao possui como chave o vertice2(node2), ele
			// cria um arraylist que irá salvar todas as arestas que aquele
			// vertice e conectado
			if (!verticeArestas.containsKey(node2)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.verticeArestas.put(node2, arestasGrafo);
			} else { // Caso contrário adiciona apenas a aresta
				this.verticeArestas.get(node2).add(aresta);
			}
			// Verifica se o vertice já está adicionado na lista de vertices do
			// grafo e adiciona caso não esteja
			if (!getVertices().contains(node1)) {
				getVertices().add(node1);
			}
			if (!getVertices().contains(node2)) {
				getVertices().add(node2);
			}

		}
		arq.close();
		lerArq.close(); // Fim da leitura do arquivo

	}

	/**
	 * Metódo criado para ler um arquivo texto e a partir dele gerar o grafo,
	 * que possui uma lista de arestas e uma lista de vertices e um hasmap que
	 * faz a associação: vertices e as arestas que conectam a cada vertice. Como
	 * este grafo possui pesos nas arestas, há a adição dessa informação na
	 * criação do objeto aresta.
	 * 
	 * @param path
	 *            :Caminho do arquivo texto que possui os dados necessários para
	 *            criar um grafo
	 * @throws IOException
	 */
	public void readWeightedGraph(File path) throws IOException {
		FileReader arq = new FileReader(path);// Prepara um buffer
		// para a leitura de
		// arquivo
		BufferedReader lerArq = new BufferedReader(arq); // Ler a primeira linha
															// do arquivo
		String linha;
		linha = lerArq.readLine();
		int qtdVertices = Integer.parseInt(linha);
		ArrayList<Aresta> arestasGrafo;// ArrayList que define para cada
		// vertice do grafo, as arestas que o
		// conectam a outros vertices

		while ((linha = lerArq.readLine()) != null) {
			// primeiro elemento (Node no hasmap): vertice inicial
			// demais elementos(lista de Arestas no hasmap): elementos que se
			// conectam o vertice inicial
			String[] aux = linha.split(" ");// A partir de cada linha do
			// arquivo, cria um array coms
			// os dados da mesma
			Node node1 = new Node(Integer.parseInt(aux[0]));// Cria o primeiro
			// nó (Para
			// definição da
			// aresta)
			Node node2 = new Node(Integer.parseInt(aux[1])); // Cria o segundo
			// nó (Para
			// definição da
			// aresta)
			Double valorAresta = Double.parseDouble(aux[2]); // Valor do peso da
																// aresta
			Aresta aresta = new Aresta(node1, node2, valorAresta); // Cria a
																	// aresta
			this.arestas.add(aresta); // Adiciona aresta no ArrayList de todas
			// as arestas existentes no grafo
			// Se o Hashmap grafo nao possui como chave o vertice(node1), ele
			// cria um arraylist que irá salvar todas as arestas que aquele
			// vertice e conectado e a adiciona na lista
			if (!verticeArestas.containsKey(node1)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.verticeArestas.put(node1, arestasGrafo);

			} else {// Caso contrário, apenas adiciona a aresta
				this.verticeArestas.get(node1).add(aresta);
			}
			// Se o Hashmap grafo nao possui como chave o vertice2(node2), ele
			// cria um arraylist que irá salvar todas as arestas que aquele
			// vertice e conectado
			if (!verticeArestas.containsKey(node2)) {
				arestasGrafo = new ArrayList<Aresta>();
				arestasGrafo.add(aresta);
				this.verticeArestas.put(node2, arestasGrafo);

			} else { // Caso contrário, apenas adiciona a aresta
				this.verticeArestas.get(node2).add(aresta);
			}

			// Verifica se o vertice já está adicionado na lista de vertices do
			// grafo e adiciona caso não esteja
			if (!getVertices().contains(node1)) {
				getVertices().add(node1);
			}
			if (!getVertices().contains(node2)) {
				getVertices().add(node2);
			}

		}
		arq.close();
		lerArq.close(); // Fim da leitura do arquivo
		setCorrection(this);

	}

	/**
	 * Este metodo ira pegar todos os vertices adjacentes ao Node passado como parametro.
	 * @param no :Node
	 * @return Retorna um ArrayList<Node> com todos os vertices adjacentes de um determinado Node.
	 */
	public ArrayList<Node> getAdjacentes(Node no) {
		ArrayList<Node> adjacentes = new ArrayList<Node>();
		for (Aresta aresta : this.arestas) {
			if (aresta.getNode1().equals(no)) {
				adjacentes.add(aresta.getNode2());
			} else if (aresta.getNode2().equals(no)) {
				adjacentes.add(aresta.getNode1());
			}
		}
		return adjacentes;
	}

	/**
	 * Metodo para verificar se o grafo tem peso.
	 * @return True caso o grafo tenha peso positivo ou negativo. False caso o grafo nao tenha peso. 
	 */
	public boolean temPeso() {
		for (Aresta aresta : arestas) {
			if (aresta.getvalor() > 0 || aresta.getvalor() < 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Este metodo pega o peso da aresta que liga dois vertices.
	 * @param no1 :Node
	 * @param no2 :Node
	 * @return O peso (double) de uma aresta que liga dois Nodes. Null se nao 
	 * houver uma aresta que liga os Nodes.
	 */
	public Double getPesoAresta(Node no1, Node no2) {
		for (Aresta aresta : arestas) {
			if ((aresta.getNode1().equals(no1) && aresta.getNode2().equals(no2))
					|| (aresta.getNode1().equals(no2) && aresta.getNode2().equals(no1))) {
				return aresta.getvalor();
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Graph [grafo=" + verticeArestas + "]";
	}
	
	/**
	 * Método que define um valor de correção.
	 * Necessário em shortestPath, é o módulo do valor da
	 * menor aresta no grafo, caso seja negativa. Caso não
	 * entre no caso anterior, será igual a zero.
	 * 
	 * @author Alberto Medeiros Gomes de Figueiredo
	 * @param grafo
	 */
	private void setCorrection(Graph grafo) {
		if (grafo.getArestas().isEmpty() || getMinimumEdgeValue(grafo) >= 0) {
			this.correcao = (double) 0;
		}else {
			this.correcao = -1 * getMinimumEdgeValue(grafo);
			}
	}
	
	/**
	 * Retorna o valor da aresta de menor peso de um grafo.
	 * 
	 * @author Alberto Medeiros Gomes de Figueiredo 
	 * @param graph - grafo a ser analizado
	 * @return menor peso em um grafo
	 */
	Double getMinimumEdgeValue(Graph graph) {
		ArrayList<Aresta> edges = graph.getArestas();
		
		Double minimum = Double.POSITIVE_INFINITY;
		for (Aresta aresta : edges) {
			if (aresta.getvalor() < minimum) {
				minimum = aresta.getvalor();
			}
		}
		return minimum;
	}

	/**
	 * @param correcao the correcao to set
	 */
	public void setCorrecao(Double correcao) {
		this.correcao = correcao;
	}
}
