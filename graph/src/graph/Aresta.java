package graph;

public class Aresta {

	private Node node1;
    private Node node2;
    private double valor;

    public Aresta(Node v1, Node v2) {
        this.node1 = v1;
        this.node2 = v2;
        this.valor = 0;
    }

    public Aresta(Node v1, Node v2, Double valor){
        this.valor = valor;
        this.node1 = v1;
        this.node2 = v2;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return this.node2;
    }

    public double getvalor() {
        return this.valor;
    }

    public boolean temAresta(Node v1, Node v2){
        return (this.node1 == v2 && this.node2 == v1) || (this.node1 == v1 && this.node2 == v2);
    }
    public boolean contemVertice(Node v){
        return node1.equals(v) || node2.equals(v);
    }
    

    @Override
	public String toString() {
		return "Aresta que conecta os vertices : "+ node1 + ", "+node2 +" " + "Peso da arestas entre os vertices: " +valor;
	}
}
