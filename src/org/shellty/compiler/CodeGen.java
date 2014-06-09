package org.shellty.compiler;

public class CodeGen {
    private String mResult = "";

    public void insertLine(String line) {
        mResult += "\n" + line;
    }

    public void insertLine() {
        insertLine("");
    }

    public void insertSymbols(String symbols) {
        mResult += symbols;
    }

    public void insertStringLiteral(String str) {
        insertSymbols("\"" + str + "\"");
    }

    public String getResult() {
        return mResult;
    }
}

