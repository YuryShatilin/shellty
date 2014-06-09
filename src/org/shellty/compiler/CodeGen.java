package org.shellty.compiler;

import java.util.List;

import org.shellty.compiler.semantic.Node;
import org.shellty.compiler.semantic.NodeData.NodeType;
import org.shellty.compiler.semantic.Tree;

public class CodeGen {
    private String mResult = "";
    private int indent = 0;

    public int incIndent() {
        return ++indent;
    }

    public int decIndent() {
        return --indent;
    }

    private String indents() {
        String res = "";
        for (int i = 0; i < indent ; i++) {
            res += "\t";
        }
        return res;
    }

    public void insertLine(String line) {
        mResult += "\n" + indents() + line;
    }

    public void insertLine() {
        mResult += "\n";
    }

    public void insertSymbols(String symbols) {
        mResult += indents() + symbols;
    }

    public void insertVarDeclaration(String varName, Node varNode, String value) {
        if (varNode.getData().isArrayVar()) {
            insertLine("local -A " + varName);
            return;    
        }

        switch (varNode.getData().getType()) {
        case COMPLEXVAR:
            break;
        case ENUMVAR:
        case STRING:
            insertLine(String.format("local ", varName));
            if (!value.isEmpty()) {
                insertSymbols("=" + value);
            }
            return;
        case INTEGER:
            insertLine(String.format("local -i ", varName));
            if (!value.isEmpty()) {
                insertSymbols("=" + value);
            }
            return;
        default:
            // TODO: wtf ?!
            return;
        }

        Node structNode = varNode.getRightNode().getParentNode();
        if (structNode == null) {
            // TODO: generate exception
        }

        List<Node> fields = Tree.getFiledStruction(structNode);
        String fieldsString = "";
        for (Node field : fields) {
            fieldsString += String.format("[%s]%s", field.getData().getLexem() ,"") + " ";
        }
        insertLine("declare -a " + varName + "=(" + fieldsString + ")");
    }

    public void insertParametrDeclaration(Node parametrNode, int number_parameter) {
        final String formatDeclare = "local %s %s=%s";
        if (parametrNode.getData().isArrayVar()) {
            insertLine(String.format(formatDeclare, "-a",
                        parametrNode.getData().getLexem(),
                        "(\"${!" + number_parameter + "}\")"));
                          
        }
        switch (parametrNode.getData().getType()) {
        case COMPLEXVAR:
           insertLine("eval \"declare -A " + parametrNode.getData().getLexem() + "\""+
                   "=${" + number_parameter + "#*=}");

        case INTEGER:
            insertLine(String.format(formatDeclare, "-i",
                        parametrNode.getData().getLexem(), 
                        "$" + number_parameter));
            return;
        case ENUMVAR:
        case STRING:
            insertLine(String.format(formatDeclare, "",
                        parametrNode.getData().getLexem(), 
                        "$" + number_parameter));
            return;
        default:
            return;
        }

/* #     eval "declare -A func_assoc_array="${1#*=}  */
    }

    public void insertStringLiteral(String str) {
        insertSymbols("\"" + str + "\"");
    }

    public String getResult() {
        return mResult;
    }

}

