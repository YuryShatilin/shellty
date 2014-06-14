package org.shellty.compiler.semantic;

public class NodeData {

    public static enum NodeType {
        DEF_FUNC,
        DEF_ENUM,
        DEF_STRUCT,

        INTEGER,
        ENUMVAR,
        STRING,
        COMPLEXVAR,
        VOID,

        UNKNOW,
        EMPTY
    }

    private String value = "";

    private int countParams = -1;
    private boolean arrayVar = false;

    private NodeType mType = NodeType.EMPTY;
    private String lexem = "";
    private NodeType returnType;

    public NodeData() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCountParams() {
        return countParams;
    }

    public void setCountParams(int countParams) {
        this.countParams = countParams;
    }

    public boolean isArrayVar() {
        return arrayVar;
    }

    public void setArrayVar(boolean arrayVar) {
        this.arrayVar = arrayVar;
    }

    public String getLexem() {
        return lexem;
    }

    public void setLexem(String lexem) {
        this.lexem = lexem;
    }

    public NodeType getReturnType() {
        return returnType;
    }

    public void setReturnType(NodeType returnType) {
        this.returnType = returnType;
    }

    public NodeType getType() {
        return mType;
    }

    public void setType(NodeType type) {
        mType = type;
    }

    @Override
    public String toString() {
        return lexem + ": " + getType() + ":p=" + getCountParams() +
            ":a=" + isArrayVar() + ":v=" + getValue();
    }

    public NodeData clone() {
        NodeData newData = new NodeData();

        newData.setType(getType());
        newData.setReturnType(getReturnType());
        newData.setCountParams(getCountParams());
        newData.setArrayVar(isArrayVar());
        newData.setLexem(getLexem());
        newData.setValue(getValue());

        return newData;
    }
}

