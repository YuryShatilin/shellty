package org.shellty.compiler;

public class CodeGen {
    private String mResult = "";

    public void insertLine(String line) {
        mResult += line + "\n";
    }

    public void insertSymbols(String symbols) {
        mResult += symbols;
    }

    public String getResult() {
        return mResult;
    }
}

