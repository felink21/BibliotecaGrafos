package graph;

import java.util.Comparator;

public class ComparatorNode implements Comparator<Node> {

	@Override
	public int compare(Node n1, Node n2) {
		if (n1.getValor() > n2.getValor()) {
			return 1;
		}else if (n1.getValor() < n2.getValor()) {
			return -1;
		}
		return 0;
	}

}
