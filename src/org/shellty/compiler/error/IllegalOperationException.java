package org.shellty.compiler.error;

import org.antlr.v4.runtime.ParserRuleContext;

public class IllegalOperationException extends ShelltyException {
    private static final long serialVersionUID = 1L;

    public IllegalOperationException() {
        super();
    }

    public IllegalOperationException(ParserRuleContext context) {
        super(context);
    }


    @Override
    public String getMessage() {
        return "illegal operation";
    }
}
