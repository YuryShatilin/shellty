package org.shellty.compiler.semantic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.shellty.compiler.semantic.NodeData.NodeType;
import org.shellty.utils.Logger;

public class Tree {
    private Node root;
    private Node mCurrentNode;

    public Tree() {
        root = new Node(null, null, null);
        root.getData().setType(NodeType.EMPTY);
        mCurrentNode = root;
    }

    public void includeNode() {
    }

    public Node getCurrentNode() {
        return mCurrentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.mCurrentNode = currentNode;
    }


    public Node structInclude(String structName) {
        if (inStruct()) {
            Logger.getInstance().log("ss");
            // TODO: generate error
            return null;
        }

        Node node = new Node(null, null, mCurrentNode);
        node.getData().setType(NodeType.DEF_STRUCT);
        node.getData().setLexem(structName);

        mCurrentNode.setLeftNode(node);

        Node rightNode = Node.createEmptyNode();
        node.setRightNode(rightNode);

        mCurrentNode = rightNode;

        return node;
    }

    public void varInclude(String varName, NodeType varType) {
        Node node = new Node(null, null, mCurrentNode);
        node.getData().setLexem(varName);
        node.getData().setType(varType);

        mCurrentNode = node;
    }

    private boolean inStruct() {
        Node curr = getCurrentNode();
        while (curr.getParentNode() != null) {
            if (curr.getParentNode().getData().getType() == NodeType.DEF_STRUCT
                    && curr == curr.getParentNode().getRightNode()) {
                return true;
            }
            curr = curr.getParentNode();
        }

        return false;
    }

    public void debugPrint() {
        try {
            try(PrintStream ps = new PrintStream(new File("tree.out"))) {
                debugPrint(root, ps);
            }
        } catch (FileNotFoundException e) {
            // ignore
        }
    }

    private void debugPrint(Node from, PrintStream stream) {
        if (from == null) {
            return;
        }

        stream.println(from.getData());

        stream.println("-----------------");
        if (from.getLeftNode() != null) {
            stream.println("l=" + from.getLeftNode().getData().getLexem());
        }

        stream.println("----------------");
        if (from.getRightNode() != null) {
            stream.println("r="+ from.getRightNode().getData().getLexem());
        }
        stream.println("----------------");

        debugPrint(from.getLeftNode(), stream);
        debugPrint(from.getRightNode(), stream);
    }
}

