package org.shellty.compiler.error;

import org.antlr.v4.runtime.ParserRuleContext;

public class SimpleErrorException extends ShelltyException {
    private static final long serialVersionUID = 1L;

    public SimpleErrorException(ParserRuleContext ctx, String message) {
        super(ctx);
        this.message = message;
    }

    private String message = "";

    @Override
    public String getErrorMessage() {
        return message;
    }
}

