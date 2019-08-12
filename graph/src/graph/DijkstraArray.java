package graph;

import java.util.ArrayList;

public class DijkstraArray {
	private ArrayList<DijksdraNode> Data;

	public DijkstraArray(ArrayList<DijksdraNode> data) {
		Data = data;
	}
	
	/**
	 * Adiciona o nó no final do Array
	 * @param node - nó a ser guardado
	 */
	public void addInEnd(DijksdraNode node) {
		Data.add(node);
	}
	/**
	 * Verifica se um dado nó está no array que armazena
	 * o grafo segundo a visão que o algoritmo de Dijkstra
	 * tem.
	 * @param node
	 * @return
	 */
	public boolean isInArray(Node node) {
		int index = getIndex(node);
		if (index == -1) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Retorna o número de nós existente entre o nó passado e 
	 * o nó de origem.
	 * @param node
	 * @return
	 */
	public Double getNodesToOrigin(Node node) {
		if (isInArray(node)) {
			return Data.get(getIndex(node)).getNodesFronOrigin();
		}else {
			return null;
		}
	}
	
	/**
	 * Define a distância válida e passível de ser comparada de um nó
	 * até o nó de origem.
	 * @param node - nó que sofrerá a atualização no valor
	 * @param value - novo valor
	 * @return Verdadeiro, caso a operação possa ser executada: null, caso
	 * o nó não exista.
	 */
	public boolean setCompDistanceUntilOrigin(Node node, Double value) {
		if (isInArray(node)) {
			Data.get(getIndex(node)).setComparableDistanceUntilOrigin(value);
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Retorna a distância válida e passível de ser comparada de um nó
	 * até o nó de origem.
	 * @param node - nó do qual se quer saber a distância
	 * @return Double - se o nó existir; null, caso o nó não exista.
	 */
	public Double getCompDistanceUntilOrigin(Node node) {
		if (isInArray(node)) {
			return Data.get(getIndex(node)).getComparableDistanceUntilOrigin();
		}else {
			return null;
		}
	}
	
	/**
	 * Retona DijksdraNode, que representa um nó 
	 * segundo a visão do algoritmo de Dijksdra .
	 * @param node
	 * @return DijksdraNode do Node passado
	 */
	public DijksdraNode getNode(Node node) {
		if (isInArray(node)) {
			return Data.get(getIndex(node));
		}else {
			return null;
		}
	}
	
	/**
	 * Retona o nó com a menor distância.
	 * @param node
	 * @return DijksdraNode do Node passado ou null, 
	 * caso DijkstraArray esteja vazia
	 */
	public DijksdraNode getNodeWithLeastDistance() {
		if (!isEmpty()) {
			DijksdraNode leastDistance = Data.get(0);
			for (DijksdraNode search : Data) {
				if (search.getDistanceFronOrigin() < leastDistance.getDistanceFronOrigin()) {
					leastDistance = search;
				}
			}
			return leastDistance;
		}else {
			return null;
		}
	}
	
	/**
	 * Retorna o indice do vertice armazenado
	 * @param node
	 * @return indice ou -1, caso o nó não seja encontrado
	 */
	public int getIndex(Node node) {
		int index = 0;
		for (DijksdraNode dinode : Data) {
			if (dinode.getNode().equals(node)) {
				return index;
			}else {
				++index;
			}
		}
		return -1;
		
	}
	
	/**
	 * Define um novo predecessor do nó. 
	 * @param node - nó que receberá a atualização na predecessor
	 * @param previous - nova predecessor do nó
	 * @return boolean - Se a operação foi implementada com sucesso.
	 */
	public boolean setPrevious(Node node, DijksdraNode previous) {
		if (isInArray(node)) {
			Data.get(getIndex(node)).setPrevious(previous);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean setNodesUntilOrigin(Node node, Double quant) {
		if (isInArray(node)) {
			Data.get(getIndex(node)).setNodesFronOrigin(quant);
			return true;
		}
		return false;
	}
	
	/**
	 * Define uma nova distância do nó de origem para o respectivo nó. 
	 * @param node - nó que receberá a atualização na distância
	 * @param distance - nova distância até o nó de origem
	 * @return boolean - Se a operação foi implementada.
	 */
	public boolean setDistance(Node node, Double distance) {
		if (isInArray(node)) {
			Data.get(getIndex(node)).setDistanceFronOrigin(distance);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Recupera a distância do nó de origem para o respectivo nó. 
	 * @param node - nó do qual a distância será recuperada
	 * @return Double - Caso o nó exista; null, caso não exista.
	 */
	public Double getDistance(Node node) {
		if (isInArray(node)) {
			return Data.get(getIndex(node)).getDistanceFronOrigin();
		}else {
			return null;
		}
	}
	
	/**
	 * Remove um nó do Array de nós, retornando um boolean 
	 * indicando o sucesso da operação.
	 * @param node - nó a ser removido
	 * @return boolean - True se a operação foi bem sucedida. 
	 * Caso o nó não esteja no Array, Falso será retornado
	 */
	public boolean removeNode(DijksdraNode node) {
		if (isInArray(node.getNode())) {
			Data.remove(getIndex(node.getNode()));
			return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		return Data.isEmpty();
	}
}
