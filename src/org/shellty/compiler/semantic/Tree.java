package org.shellty.compiler.semantic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

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
            // TODO: generate error
            return null;
        }

        Node node = new Node(null, null, mCurrentNode);
        node.getData().setType(NodeType.DEF_STRUCT);
        node.getData().setLexem(structName);

        mCurrentNode.setLeftNode(node);

        Node rightNode = Node.createEmptyNode();
        rightNode.setParentNode(node);
        node.setRightNode(rightNode);

        mCurrentNode = rightNode;

        return node;
    }

    public Node functionInclude(String functionName, NodeData.NodeType returnType) {
        Node node = new Node(null, null, mCurrentNode);
        mCurrentNode.setLeftNode(node);

        node.getData().setType(NodeType.DEF_FUNC);
        node.getData().setLexem(functionName);
        node.getData().setReturnType(returnType);
       
        Node rightNode = Node.createEmptyNode();
        rightNode.setParentNode(node);
        node.setRightNode(rightNode);
        mCurrentNode = rightNode; 

        return node;
    }

    public boolean varInclude(String varName, NodeType varType) {
        Node node = new Node(null, null, mCurrentNode);
        mCurrentNode.setLeftNode(node);
        node.getData().setLexem(varName);
        node.getData().setType(varType);
        
        mCurrentNode = node;
        return true;
    }

    public void calcParametrCount(Node funcNode) {
        Node curr = getCurrentNode();
        List<NodeType> varTypes = Arrays.asList(new NodeType[] {
                NodeType.COMPLEXVAR, NodeType.INTEGER, NodeType.STRING,
                NodeType.ENUMVAR });
        int countParams = 0;
        while (varTypes.contains(curr.getData().getType())) {
            countParams += 1;
            curr = curr.getParentNode();
        }

        funcNode.getData().setCountParams(countParams);
        /* while (curr.getData().getType() == NodeType.COMPLEXVAR && */
        /* curr.getData().getType(). */
    }

    public boolean checkDuplicate(String name) {
        Node curr = getCurrentNode();
        while (curr != null) {
            if (curr.getData().getLexem() == name) {
                return false;
            }
            if (curr.getParentNode() != null
                    && curr.getParentNode().getRightNode() == curr) {
                break;
            }
            curr = curr.getParentNode();
        }
        return true;
    }

    public Node findUp(String name) {
        Node curr = getCurrentNode();
        while (curr != null) {
            if (curr.getData().getLexem() == name) {
                return curr;
            }
            curr = curr.getParentNode();
        }
        return curr;
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

    public Node enumInclude(String enumName) {
        if (inStruct()) {
            // TODO: generate error
            return null;
        }

        Node node = new Node(null, null, mCurrentNode);
        node.getData().setType(NodeType.DEF_ENUM);
        node.getData().setLexem(enumName);

        mCurrentNode.setLeftNode(node);

        Node rightNode = Node.createEmptyNode();
        node.setRightNode(rightNode);

        mCurrentNode = rightNode;

        return node;
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

