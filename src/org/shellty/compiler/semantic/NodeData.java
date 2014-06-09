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
    private int countParams = -1;

    private NodeType mType = NodeType.EMPTY;
    private String lexem = "";
    private NodeType returnType;

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

    /**
     * @return the countParams
     */
    public int getCountParams() {
        return countParams;
    }

    /**
     * @param countParams the countParams to set
     */
    public void setCountParams(int countParams) {
        this.countParams = countParams;
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
        return lexem + ": " + getType() + ":p=" + getCountParams();
    }
}

