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

    private String stringValue = "";
    private int intValue = -1;
    private boolean constValue = false;

    private NodeType mType = NodeType.EMPTY;
    private String lexem = "";

    public NodeData() {
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        constValue = true;
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        constValue = true;
        this.intValue = intValue;
    }

    public boolean isConstValue() {
        return constValue;
    }

    public String getLexem() {
        return lexem;
    }

    public void setLexem(String lexem) {
        this.lexem = lexem;
    }

    public NodeType getType() {
        return mType;
    }

    public void setType(NodeType type) {
        mType = type;
    }

    @Override
    public String toString() {
        return lexem + ": " + getType();
    }
}

