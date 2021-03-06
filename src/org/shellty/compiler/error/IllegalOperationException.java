package org.shellty.compiler.error;

import org.antlr.v4.runtime.ParserRuleContext;

public class IllegalOperationException extends ShelltyException {
    private static final long serialVersionUID = 1L;

    public IllegalOperationException(ParserRuleContext context) {
        super(context);
    }


    @Override
    public String getErrorMessage() {
        return "illegal operation";
    }
}
