package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MatrizAdjacencia {
	private String[][] matriz;
	private ArrayList<Node> vertices;
	private static Comparator<Node> comp = new ComparatorNode();
	
	public MatrizAdjacencia(Graph grafo) {
		this.vertices = new ArrayList<Node>();
		this.vertices.addAll(grafo.getVertices());
		this.matriz = constroiMatriz(grafo);
	}

	/**
	 * Constroi um mapa (String[][]) dos vertices de um grafo e os pesos de cada aresta existente.
	 * @param grafo
	 * @return String[][] dos vertices com os valores de peso de cada aresta.
	 */
	private String[][] constroiMatriz(Graph grafo) {
		Collections.sort(this.vertices, comp);
		String[][] newMatriz = new String[grafo.getVertices().size()][grafo.getVertices().size()];
		for (int i = 0; i < newMatriz.length; i++) {
			for (int j = 0; j < newMatriz.length; j++) {
				newMatriz[i][j] = ""+0;
			}
		}
		for (Aresta aresta : grafo.getArestas()) {
			if (grafo.temPeso()) {
				Double peso = grafo.getPesoAresta(aresta.getNode1(),aresta.getNode2());
				if (peso % 1 == 0) {
					newMatriz[this.vertices.indexOf(aresta.getNode1())][this.vertices.indexOf(aresta.getNode2())] = ""+peso.intValue();
					newMatriz[this.vertices.indexOf(aresta.getNode2())][this.vertices.indexOf(aresta.getNode1())] = ""+peso.intValue();
				} else {
					newMatriz[this.vertices.indexOf(aresta.getNode1())][this.vertices.indexOf(aresta.getNode2())] = ""+peso;
					newMatriz[this.vertices.indexOf(aresta.getNode2())][this.vertices.indexOf(aresta.getNode1())] = ""+peso;
				}
			} else {
				newMatriz[this.vertices.indexOf(aresta.getNode1())][this.vertices.indexOf(aresta.getNode2())] = ""+1;
				newMatriz[this.vertices.indexOf(aresta.getNode2())][this.vertices.indexOf(aresta.getNode1())] = ""+1;
			}
		}
		return newMatriz;
	}
	
	/**
	 * Retorna uma string com a representacao do grafo em Matriz de Adjacencia.
	 */
    @Override
    public String toString() {
    	String[] resultado = new String[this.matriz.length+1];
    	String aux = "";
    	for (Node node : this.vertices) {
			aux += " " + node.getValor();
		}
    	resultado[0] = aux;
    	for (int i = 1; i < resultado.length; i++) {
			resultado[i] = ""+this.vertices.get(i-1);
		}
    	for (int i = 0; i < this.matriz.length; i++) {
    		aux = "";
			for (int j = 0; j < this.matriz.length; j++) {
				aux += " "+this.matriz[i][j];
			}
			resultado[i+1] += aux;
		}
        return String.join(System.lineSeparator(), resultado);
    }
}
