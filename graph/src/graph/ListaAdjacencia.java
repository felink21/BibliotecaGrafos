package graph;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaAdjacencia {
	
	private HashMap<Node, ArrayList<Node>> listaAdj;
	private Graph grafo;
	private static Comparator<Node> comp = new ComparatorNode();

	public ListaAdjacencia(Graph grafo) {
		this.listaAdj = constroiLista(grafo);
		this.grafo = grafo;
	}
	
	/**
	 * Controi um HashMap<Node, ArrayList<Node>> a partir de um determinado grafo 
	 * @param grafo :Grafo
	 * @return HashMap<Node, ArrayList<Node>> contendo os vertices e arestas do grafo passado no parametro.
	 */
	private HashMap<Node, ArrayList<Node>> constroiLista(Graph grafo) {
    	HashMap<Node, ArrayList<Node>> listaAdj = new HashMap<>();
    	for (Node node : grafo.getVertices()) {
			listaAdj.put(node, grafo.getAdjacentes(node));
		}
		return listaAdj;
	}

	/**
	 * Retorna uma string com a representacao do grafo em Lista de Adjacencia.
	 */
	@Override
    public String toString() {
		
		String resultado = "";
		for (Node node: this.listaAdj.keySet()) {
			Collections.sort(this.listaAdj.get(node), comp);
			resultado += node.getValor() + " -";
			for (Node adj: this.listaAdj.get(node)) {
				resultado += " " + adj.getValor();
				if (this.grafo.temPeso()) {
					Double peso = this.grafo.getPesoAresta(node, adj);
					if (peso % 1 == 0) {
						resultado += "(" + peso.intValue() + ")";
					} else {
						resultado += "(" + peso + ")";
					}
				}
			}
			resultado += System.lineSeparator();
		}
        return resultado.trim();
    }
}
