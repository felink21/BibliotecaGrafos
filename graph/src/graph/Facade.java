package graph;

import java.util.*;

public class Facade {

	public Facade() {}
  /**
 * Este metodo ira aplicar o algoritmo de busca em largura e retornar 
 * a representacao do grafo resultante.
 * @param graph :Grafo
 * @param s :Node (vertice) inicial
 * @return Uma string representando o grafo resultante apos ser percorrido por uma BFS.
 */ 
  public String BFS(Graph graph, Node s) {
		Queue<Node> q = new LinkedList<Node>(); // Fila para realizar bfs
		List<Aresta> arestas = graph.getArestas(); // Arestas do grafo
		Map<Node, Integer> distancias = new HashMap<Node, Integer>(); // Mapa de
																		// distancias
																		// de
																		// cada
																		// vertice
		Map<Node, Integer> parentes = new HashMap<Node, Integer>(); // Mapa de
																	// parentes
																	// de cada
																	// vertice
		q.add(s); // colocando s na fila para iniciar bfs
		distancias.put(s, 0); // definindo distancias
		while (!q.isEmpty()) {
			Node atual = q.remove();
			int minhaDistancia = distancias.get(atual);
			for (Aresta aresta : arestas) { // verificar todas as arestas para
											// encontrar adjacencias
				if (aresta.contemVertice(atual)) { // verificar se eh aresta do
													// vertice
					Node destinoAresta = aresta.getNode1(); // descobrir a
															// direcao da aresta
					if (destinoAresta.equals(atual)) // caso tenha pego a
														// direcao errado
						destinoAresta = aresta.getNode2(); // muda direcao
					if (!distancias.containsKey(destinoAresta)) { // verificando
																	// se nao
																	// tem a
																	// distancia
																	// setada
						distancias.put(destinoAresta, minhaDistancia + 1); // coloca
																			// a
																			// distancia
						parentes.put(destinoAresta, atual.getValor()); // coloca
																		// o pai
						q.add(destinoAresta); // adiciona o novo vertice a fila
					}
				}
			}
		}
		String resultado = ""; // string do resultado
		int qtdVertices = getVertexNumber(graph);
		for (int i = 1; i <= qtdVertices; i++) {
			String linhai = i + " - ";
			Node noAtual = new Node(i);
			linhai += distancias.get(noAtual)+" ";
			if (!noAtual.equals(s)) // verificando se eh diferente do no inicial
									// para pegar o parente
				linhai += parentes.get(noAtual);
			else
				linhai += "-";

			linhai += System.lineSeparator();
			resultado += linhai;
		}
		return resultado;
	}

	/**
	 * Este metodo ira aplicar o algoritmo de busca em profundidade 
	 * e retornar a representacao do grafo resultante.
	 * @param graph :Grafo
	 * @param s :Node (vertice) inicial
	 * @return Uma string representando o grafo resultante apos ser percorrido por uma DFS.
	 */
	public String DFS(Graph graph, Node s) {
		// DFS
		boolean[] visitados = 
				new boolean[graph.getVertices().size()]; // false = nao visitado, true = visitado
		Node[] pais = new Node[graph.getVertices().size()];
		int[] niveis = new int[graph.getVertices().size()];
		niveis[graph.getVertices().indexOf(s)] = 0;
		DFSRec(graph, s, visitados, pais, niveis);

		// Formatacao de saida
		String[] resultado = new String[graph.getVertices().size()];
		for (int i = 0; i < graph.getVertices().size(); i++) {
			String aux = "";
			Node no = graph.getVertices().get(i);
			aux += no.getValor() + 
					" - " + 
						niveis[graph.getVertices().indexOf(no)] + " "; // padrao 'vertice - nivel '
			if (pais[graph.getVertices().indexOf(no)] == null) { // Se o pai for null
				aux += "-";
			} else {
				aux += pais[graph.getVertices().indexOf(no)].getValor(); // Concatena o pai
			}
			resultado[i] = aux;
		}
		Arrays.sort(resultado);
		return String.join(System.lineSeparator(), resultado);
	}

	/**
	 * Metodo auxiliar para DFS(Graph graph, Node s) que ira usar recursao 
	 * para aplicar a busca em profundidade.
	 * @param grafo
	 * @param no
	 * @param visitados
	 * @param pais
	 * @param niveis
	 */
	private void DFSRec(Graph grafo, Node no, boolean[] visitados, Node[] pais, int[] niveis) {
		visitados[grafo.getVertices().indexOf(no)] = true;

		for (Node node : grafo.getAdjacentes(no)) {
			if (visitados[grafo.getVertices().indexOf(node)] == false) {
				pais[grafo.getVertices().indexOf(node)] = no;
				niveis[grafo.getVertices().indexOf(node)] = niveis[grafo.getVertices().indexOf(no)] + 1;
				DFSRec(grafo, node, visitados, pais, niveis);
			}
		}
	}

	/**
	 * Retorna o nÃºmero de vertices do grafo
	 * 
	 * @param graph
	 *            : Grafo a qual se deseja saber o nÃºmero de vÃ©rtices
	 * @return : nÃºmero de vÃ©rtices de um grafo
	 */
	public int getVertexNumber(Graph graph) {
		return graph.getVertices().size();
	}

	/**
	 * Retorna o nÃºmero de arestas de um grafo
	 * 
	 * @param graph
	 *            : Grafo a qual se deseja saber o nÃºmero de arestas
	 * @return : nÃºmero de arestas de um grafo
	 */
	public int getEdgeNumber(Graph graph) {
		return graph.getArestas().size();
	}

	/**
	 * Retorna o grau medio de um grafo. Para isso, a partir
	 * 
	 * @param graph
	 *            : Grafo a qual se deseja saber o grau mÃ©dio
	 * @return: grau mÃ©dio de um grafo
	 */
	public float getMeanEdge(Graph graph) {
		int soma = 0;
		float media = 0;
		for (int i = 0; i < graph.getVertices().size(); i++) {
			Node node = graph.getVertices().get(i);
			soma += graph.getVerticeArestas().get(node).size(); // No hashmap da
																// classe grafo,
																// a partir de
																// um vertice,
																// consulta a
																// lista de
																// arestas que
																// se conecta a
																// ele e pega a
																// quantidade
																// das mesmas e
																// soma a cada
																// iteraÃ§Ã£o

		}
		media = soma / (graph.getVertices().size()); // Divide a soma anterior 
		                                             //pela quantidade de vertices total do grafo
		return media;
	}

	/**
	 * Metodo para representar, em string, um grafo em dois tipos diferentes, 
	 * Lista de Adjacencia (AL) e Matriz de Adjacencia (AM).
	 * @param graph :Grafo
	 * @param type :Tipo de representacao
	 * @return String de uma determinada representacao do grafo escolhido.
	 */
	public String graphRepresentation(Graph graph, String type) {
		if (type.equals("AL")) {
			ListaAdjacencia exibicaoAL = new ListaAdjacencia(graph);
			return exibicaoAL.toString();
		} else if (type.equals("AM")) {
			MatrizAdjacencia exibicaoMA = new MatrizAdjacencia(graph);
			return exibicaoMA.toString();
		}
		return "";
	}
	
    /**
     * Verifica se um grafo e conectado ou nao
     * @param graph grafo a ser verificado
     * @return retorna true se for conectado ou false caso contrario.
     * */
    public boolean connected(Graph graph){
    	Set<Node> visitados = new HashSet<Node>();
    	dfs(graph.getVertices().get(0), visitados, graph);
    	
    	for(Node vertice : graph.getVertices()) {
    		if(!visitados.contains(vertice)) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    /**
     * Metodo especifico para o metodo connected, dado um vertice, ele vai visitar seus 
     * adjacentes recursivamente
     * @param node vertice onde a partir de onde comecara a dfs
     * @param visitados conjunto de vertices que ja foram visitados
     * @param grafo o grafo
     * @return se o vertice ja foi vistado retorna, caso base da recursividade do metodo
     * */
    private void dfs(Node node, Set<Node> visitados, Graph grafo) {
		if(visitados.contains(node)) {
			return;
		}
		
		visitados.add(node);
		
		for(Node adjacente : grafo.getAdjacentes(node)) {
			if(!visitados.contains(adjacente)) {
				dfs(adjacente, visitados, grafo);
			}
		}
		
	}

    /**
	 * Método que retorna o menor caminho entre 2 nós em um grafo. 
	 * A implementação do método é pelo algoritmo de Dijdstra melhorado, capaz de
	 * lidar com arestas de peso negativo.
	 * 
	 * @author Alberto Medeiros Gomes de Figueiredo
	 * @param grafo - grafo objeto de analise
	 * @param a - nó de partida
	 * @param b - nó de destino
	 * @return String com os nós que formam o menor caminho; null, caso não seja
	 *  possível de encontrar.
	 */
	public String shortestPath(Graph grafo, Node a, Node b) {
		setCorrection(grafo);
		ArrayList<Node> n = grafo.getVertices();
		DijkstraArray r = new DijkstraArray(new ArrayList<>());
		
		for (Node node : n) {
			r.addInEnd(new DijksdraNode(null, Double.POSITIVE_INFINITY, 
					Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, node));
		}
		
		r.setDistance(a, (double) 0);
		r.setNodesUntilOrigin(a, (double) 0);
		r.setCompDistanceUntilOrigin(a, null);  // Esse valor só é utilizado em comparações
		                                        // entre nós vistos como adjacentes a outro nó 
		                                        // em algum momento da execução.
		                                        // o nó de origem nunca é visto como adjacente 
		                                        // a outro nó em Dijkstra.
		while (DontFindEnd(r)) {
			DijksdraNode u = r.getNodeWithLeastDistance();
	
			if (isATargetNode(u, b)) {
				
				Stack pilha = stackingTheNodes(u);
				
				return generateResult(pilha);
			}
			for (Node neighbor : getAdjacentesDikstra(grafo, r, u)) { 
				Double alt = (r.getDistance(u.getNode()) + 
						getPesoArestaSeguro(r, grafo, u.getNode(), neighbor));
				
				Double neighborData = r.getCompDistanceUntilOrigin(neighbor);  

				if (neighborData > alt) {
					r.setDistance(neighbor, r.getDistance(u.getNode()) + 
							getPesoArestaValido(grafo, u.getNode(), neighbor));
					r.setPrevious(neighbor, u);
					r.setNodesUntilOrigin(neighbor, u.getNodesFronOrigin() + 1);
					r.setCompDistanceUntilOrigin(neighbor, alt); 
				}
			}
			
			boolean test = r.removeNode(u);
		}
		
		return null;	
	}
	
	private String generateResult(Stack pilha) {
		String result = "";
		while (!pilha.isEmpty()) {
			result += pilha.pop().toString() + " ";
		}
		
		return result;
	}
	private Stack stackingTheNodes(DijksdraNode u) {
		DijksdraNode temp = u;
		Stack pilha = new Stack<>();
		
		pilha.push(temp.getNode().getValor());
		while (temp.getPrevious() != null) {
			pilha.push(temp.getPrevious().getNode().getValor());
			temp = temp.getPrevious();
		}
		return pilha;
	}
	
	private boolean isATargetNode(DijksdraNode u, Node b) {
		if (u.getNode().compareTo(b) == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Define um valor de punição a ser acresentado na análise de um caminho quando
	 * houve ao menos 1 aresta com peso negativo. Caso contrário, nada será acrescentado.
	 * 
	 * Isso é necessário, pois, ao acrecentar uma mesma correção a todos os pesos das
	 * arestas de um grafo, caminhos com mais arestas receberam mais correções do que
	 * caminhos com menos, interferindo na escolha do menor caminho.
	 * 
	 * Assim, foi definido como padrão para comparações um caminho com 100.000 nós.
	 *  A partir dai, um acrésimo de correções é definido para que seja justo comparar 
	 * o nó passado como argumento com um nó desse tamanho.
	 * 
	 *  Essa punição também permite uma comparação justa entre caminhos com número de 
	 * arestas diferêntes e com menos de 100000 nós, bastando que acrescente a devida
	 * punição aos caminhos analisados.
	 * 
	 * @author Alberto Medeiros Gomes de Figueiredo
	 * @param data - array onde está o grafo segundo o algoritmo de Dijksdra.
	 * @param no1 - último nó do caminho a ser analizado.
	 * @return Double - punição necessária para uma comparação justa com um caminho de
	 * 100.000 nós.
	 */
	private Double penalty (DijkstraArray data, Node no1, Graph grafo) {
		Double worstCase = (double) (100000);
		return (worstCase - data.getNodesToOrigin(no1)) * grafo.getCorrecao();
	}
	
	/**
	 * Retorna o peso de uma aresta corrigido para o algoritmo de Dijksdra acrescido 
	 * de uma devida punição. 
	 * 
	 * Tal resultado permite comparar caminhos mesmo que se tenha acrescido uma mesma 
	 * correção para todas as arestas.
	 * ATENÇÃO: A ALTERAÇÃO NA ORDEM DOS NÓS PASSADOS ALTERA O RESULTADO.
	 *  
	 * @author Alberto Medeiros Gomes de Figueiredo
	 * @param data - array onde está o grafo segundo o algoritmo de Dijksdra.
	 * @param grafo - grafo onde se encontra os referidos nós.
	 * @param no1 - nó de origem
	 * @param no2 - nó de destino
	 * @return Double 
	 */
	private Double getPesoArestaSeguro(DijkstraArray data, Graph grafo, Node no1, Node no2) {
		return getPesoArestaValido(grafo, no1, no2) + penalty(data, no1, grafo);
	}
	
	private ArrayList<Node> getAdjacentesDikstra(Graph grafo, DijkstraArray data, DijksdraNode u){
		ArrayList<Node> dat = grafo.getAdjacentes(u.getNode());
		ArrayList<Node> end = new ArrayList<>();
		for (Node no : dat) {
			if (data.isInArray(no)) {
				end.add(no);
			}
		}
		return end;
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
		if (grafo.getArestas().isEmpty() || grafo.getMinimumEdgeValue(grafo) >= 0) {
			//this.correcao = (double) 0;
			grafo.setCorrecao((double) 0);
		}else {
			//this.correcao = -1 * grafo.getMinimumEdgeValue(grafo);
			grafo.setCorrecao(-1 * grafo.getMinimumEdgeValue(grafo));
			}
	}
	
	
	
	/**
	 * Retorna o peso de uma aresta acrecido de uma correção.
	 * A correção garante que todos os peso sejam positivos caso 
	 * haja alguma aresta com peso negativo e, portanto, adequados ao 
	 * algoritmo de Dijdstra. Se não houver pesos negativos, o próprio
	 * peso será retornado.
	 * 
	 * @author Alberto Medeiros Gomes de Figueiredo
	 * @param grafo - grafo onde se encontra os referidos nós
	 * @param no1 - nó de origem
	 * @param no2 - nó de destino
	 * @return Double maior ou igual a zero
	 */
	private Double getPesoArestaValido(Graph grafo, Node no1, Node no2){
		return grafo.getPesoAresta(no1, no2) + grafo.getCorrecao();
	}
	
	private boolean DontFindEnd(DijkstraArray da) {
		if (!da.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Metodo que dado um grafo conexo calcula a Minimum Spanning Tree (Arvore Geradora Minima)
	 *@param graph grafo que tera sua MST calculada
	 *@return retorna uma String com as informacoes da MST (Vertice - Nivel - Pai)
	 * */
    public String mst(Graph graph){
    	if(!this.connected(graph)) {
    		return "O grafo nao e conectado!";
    	}
    	
    	final int PAI_DA_RAIZ = -1;
    	final double DISTANCIA_INICIAL = 0.;
    	final double INFINITO = Double.MAX_VALUE;
    	final int NIVEL_INICIAL = 0;
    	
		List<Node> vertices = new ArrayList<Node>(graph.getVertices());
		Map<Node, Integer> niveis = new HashMap<Node, Integer>();
		Map<Node, Node> pais = new HashMap<Node, Node>();
		Map<Node, Double> distancias = new HashMap<Node, Double>();
		Set<Node> naoVisitados = new HashSet<Node>();
		
		//inicializa as distancias como infinito e adiciona todos os vertices ao conjunto de nao visitados
		for(int i = 0; i < vertices.size(); i++) {
			distancias.put(vertices.get(i), INFINITO);
			naoVisitados.add(vertices.get(i));
		}

		distancias.put(vertices.get(0), DISTANCIA_INICIAL); //vertice inicial
		pais.put(vertices.get(0), new Node(PAI_DA_RAIZ));
		niveis.put(vertices.get(0), NIVEL_INICIAL);
		
		//para todos os vertices, menos o ultimo
		for(int i = 0; i < (vertices.size() - 1);i++) {
			Node verticeAtual = getMinimo(naoVisitados, distancias);
			naoVisitados.remove(verticeAtual);
			
			//para cada adjacente do vertice atual
			for(Node adjacente : graph.getAdjacentes(verticeAtual)) {
				if(naoVisitados.contains(adjacente)) { //se o adjacente ainda nao foi visitado
					double pesoAresta = 
							graph.getPesoAresta(verticeAtual, adjacente); //peso da aresta que liga o verticeAtual e seu adjacente
					if(pesoAresta < distancias.get(adjacente)) { //se a distancia do verticeAtual ate seu adjacente for menor q a distancia atual do adjacente
						pais.put(adjacente, verticeAtual); //coloca o vertice atual como novo pai do adjacente
						niveis.put(adjacente, niveis.get(verticeAtual) + 1); //o nivel do adjacente agora sera o nivel do seu pai + 1
						distancias.put(adjacente, pesoAresta); //e sua nova distancia sera o peso da aresta que liga ele a seu pai
					}
				}
			}
		}
		
		return mstFormatada(pais, niveis, vertices);
		
    }
    
    /**
     * Metodo que calcula o vertice nao visitado com a menor distancia
     * @param naoVisitados conjunto de vertices nao visitados
     * @param distancias distancias de cada vertice
     * @return retorna o vertice nao visitado com a menor distancia
     * */
    private Node getMinimo(Set<Node> naoVisitados, Map<Node, Double> distancias) {
    	Node minimo = null;

    	for(Node vertice : naoVisitados) {
    		if(minimo == null) {
    			minimo = vertice;
    		} else {
    			if (distancias.get(vertice) < distancias.get(minimo)) {
    				minimo = vertice;
    			}
    		}
    	}
    	return minimo;
    }
    
    /**
     * Metodo que formata a String da mst
     * @param pais mapa que tem todos os vertices e seus respectivos pais
     * @param niveis mapa que tem todos os vertices e seus respectivos niveis
     * @param vertices lista com todos os vertices do grafo
     * @return String da mst formatada
     * */
    private String mstFormatada(Map<Node, Node> pais, Map<Node, Integer> niveis, List<Node> vertices) {
    	String saida = "";
    	final int PAI_DA_RAIZ = -1;
    	
    	Collections.sort(vertices, new ComparatorNode());

    	for(Node vertice : vertices) {
    		if(pais.get(vertice).getValor() == PAI_DA_RAIZ) {
    			saida += vertice.getValor() + " - " + niveis.get(vertice) +  " -\n";
    		} else if (vertice.equals(vertices.get(vertices.size() - 1))){
    			saida += vertice.getValor() 
    					+ " - " + niveis.get(vertice) + " " + pais.get(vertice).getValor();
    		} else {
    			saida += vertice.getValor() + 
    					" - " + niveis.get(vertice) + " " + pais.get(vertice).getValor() + "\n";
    		}
    		
    	}
    	return saida;
    }
}
