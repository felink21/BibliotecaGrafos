package graph;

public class DijksdraNode {
	private DijksdraNode previous;
	private Double distanceFronOrigin;
	private Double comparableDistanceUntilOrigin;
	private Double nodesFronOrigin;
	private Node node;


	public DijksdraNode(DijksdraNode previous, Double distanceFronOrigin, Double comparableDistanceUntilOrigin,
			Double nodesFronOrigin, Node node) {
		this.previous = previous;
		this.distanceFronOrigin = distanceFronOrigin;
		this.comparableDistanceUntilOrigin = comparableDistanceUntilOrigin;
		this.nodesFronOrigin = nodesFronOrigin;
		this.node = node;
	}

	/**
	 * @return the comparableDistanceUntilOrigin
	 */
	public Double getComparableDistanceUntilOrigin() {
		return comparableDistanceUntilOrigin;
	}

	/**
	 * @param comparableDistanceUntilOrigin the comparableDistanceUntilOrigin to set
	 */
	public void setComparableDistanceUntilOrigin(Double comparableDistanceUntilOrigin) {
		this.comparableDistanceUntilOrigin = comparableDistanceUntilOrigin;
	}

	/**
	 * @param nodesFronOrigin the nodesFronOrigin to set
	 */
	public void setNodesFronOrigin(Double nodesFronOrigin) {
		this.nodesFronOrigin = nodesFronOrigin;
	}

	/**
	 * @return the nodesFronOrigin
	 */
	public Double getNodesFronOrigin() {
		return nodesFronOrigin;
	}

	/**
	 * 
	 * @return the previous
	 */
	public DijksdraNode getPrevious() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(DijksdraNode previous) {
		this.previous = previous;
	}

	/**
	 * @return the distanceFronOrigin
	 */
	public Double getDistanceFronOrigin() {
		return distanceFronOrigin;
	}

	/**
	 * @param distanceFronOrigin the distanceFronOrigin to set
	 */
	public void setDistanceFronOrigin(Double distanceFronOrigin) {
		this.distanceFronOrigin = distanceFronOrigin;
	}

	/**
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

}
