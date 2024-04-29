package ed.tree;

public class Node {

	private String nodeId;
	private String nodeDame;
	private String nodeDesc;
	private int level;

	public Node(String nodeId, String nodeName, String nodeDesc) {
		this.nodeId = nodeId;
		this.nodeDame = nodeName;
		this.nodeDesc = nodeDesc;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId.trim().toUpperCase();
	}

	public String getNodeName() {
		return nodeDame;
	}

	public void setNodeName(String nodeName) {
		this.nodeDame = nodeName.trim().toUpperCase();
	}

	public String getNodeDesc() {
		return nodeDesc;
	}

	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
