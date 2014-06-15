package org.shellty.compiler.error;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public abstract class ShelltyException extends ParseCancellationException {
    private static final long serialVersionUID = 1L;
    private ParserRuleContext context = null;

    public ShelltyException() {
    }

    public ShelltyException(ParserRuleContext context) {
        this.context = context;
    }

    public final String generateMessage() {
        return "line " + context.getStart().getLine() +
            ":" + context.getStart().getCharPositionInLine() +
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
