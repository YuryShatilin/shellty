package org.shellty.compiler.error;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class ShelltyException extends Exception {
    private static final long serialVersionUID = 1L;
    private ParserRuleContext context = null;

    public ShelltyException() {
    }

    public ShelltyException(ParserRuleContext context) {
        this.context = context;
    }

    public final String generateMessage() {
        return "line:" + context.getStart().getLine() +
            ",p:" + context.getStart().getCharPositionInLine() +
            "\t" + getMessage();
    }

    public abstract String getMessage();

    public ParserRuleContext getContext() {
        return context;
    }

    public void setContext(ParserRuleContext context) {
        this.context = context;
    }
}
