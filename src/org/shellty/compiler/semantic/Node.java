package org.shellty.compiler.semantic;

import org.shellty.compiler.semantic.NodeData.NodeType;

public class Node {

    private Node mLeftNode = null;
    private Node mRightNode = null;
    private Node mParentNode = null;
    private NodeData mData = new NodeData();

    public Node(Node leftNode, Node rightNode, Node parentNode, NodeData data) {
        mLeftNode = leftNode;
        mRightNode = rightNode;
        mParentNode = parentNode;
        mData = data;
    }

    public Node() {
    }

    public Node(Node mLeftNode, Node mRightNode, Node mParentNode) {
        this.mLeftNode = mLeftNode;
        this.mRightNode = mRightNode;
        this.mParentNode = mParentNode;
    }

    public static Node createEmptyNode() {
        Node node = new Node();
        node.getData().setType(NodeType.EMPTY);
        return node;
    }

    public Node getLeftNode() {
        return mLeftNode;
    }

    public void setLeftNode(Node leftNode) {
        mLeftNode = leftNode;
    }

    public Node getRightNode() {
        return mRightNode;
    }

    public void setRightNode(Node rightNode) {
        mRightNode = rightNode;
    }

    public Node getParentNode() {
        return mParentNode;
    }

    public void setParentNode(Node parentNode) {
        mParentNode = parentNode;
    }

    public NodeData getData() {
        return mData;
    }

    public void setData(NodeData data) {
        mData = data;
    }

}
