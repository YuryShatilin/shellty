package org.shellty.compiler.error;

import org.antlr.v4.runtime.ParserRuleContext;

public class RedefinitionException extends ShelltyException { 
    private static final long serialVersionUID = 1L;

    public RedefinitionException(ParserRuleContext ctx, String name) {
        super(ctx);
        this.name = name;
    }

    String name = "";
    @Override
    public String getErrorMessage() {
        return "redefinition declaration " + name;
    }
}

