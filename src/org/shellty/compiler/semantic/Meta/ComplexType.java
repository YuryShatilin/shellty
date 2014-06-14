package org.shellty.compiler.semantic.Meta;

import org.shellty.compiler.semantic.Node;

public class ComplexType extends BasicMetaType {
    public ComplexType(String value, Node structNode) {
        super(value);
    }

    private Node structNode = null;

    public Node getStructNode() {
        return structNode;
    }

    public void setStructNode(Node structNode) {
        this.structNode = structNode;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        return ((ComplexType)other).getStructNode() == getStructNode();
    }
}

