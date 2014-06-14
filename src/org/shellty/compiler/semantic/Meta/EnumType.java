package org.shellty.compiler.semantic.Meta;

import org.shellty.compiler.semantic.Node;

public class EnumType extends BasicMetaType {
    public EnumType(String value, Node enumNode) {
        super(value);
        this.enumNode = enumNode;
    }

    public EnumType() {
    }

    private Node enumNode = null;

    public Node getEnumNode() {
        return enumNode;
    }

    public void setEnumNode(Node enumNode) {
        this.enumNode = enumNode;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        return ((EnumType)other).getEnumNode() == getEnumNode();
    }
}

