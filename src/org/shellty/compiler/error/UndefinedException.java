package org.shellty.compiler.error;

import org.antlr.v4.runtime.ParserRuleContext;

public class UndefinedException extends ShelltyException {
    private static final long serialVersionUID = 1L;

    public UndefinedException() {
        super();
    }

    public UndefinedException(ParserRuleContext context, String name) {
        super(context);
        this.name = name;
    }

    public String name = "";

    @Override
    public String getErrorMessage() {
        return "undefined declaration " + name;
    }
}
