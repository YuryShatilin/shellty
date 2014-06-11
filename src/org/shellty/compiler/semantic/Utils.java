package org.shellty.compiler.semantic;

import org.shellty.compiler.semantic.NodeData.NodeType;
import org.shellty.compiler.semantic.Meta.*;

public class Utils {
    public static NodeData.NodeType getType(int tokenType, String tokenName,
            Tree semanticTree) {

        switch (tokenType) {
        case 14:
            return NodeData.NodeType.INTEGER;
        case 20:
            return NodeData.NodeType.STRING;
        case 67: {
            Node node = semanticTree.findUp(tokenName);
            if (node != null) {
                if (node.getData().getType() == NodeData.NodeType.DEF_ENUM
                        && node.getData().getType() == NodeData.NodeType.DEF_STRUCT) {
                    return node.getData().getType();
                }
            }
        }
        case 18:
                 return NodeData.NodeType.VOID;
        default:
            return null;
        }
    }


    public static BasicMetaType toMetaType(NodeType type) {
        switch (type) {
        case ENUMVAR:
            return new EnumType();
        case INTEGER:
            return new IntegerType();
        case STRING:
            return new StringType();
        case VOID:
            return new VoidType();
        default:
            break;
        }
        return null;
    }
}
