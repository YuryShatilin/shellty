package org.shellty.compiler.error;

import org.antlr.v4.runtime.ParserRuleContext;

public class NotSupportedException extends ShelltyException {
    private static final long serialVersionUID = 1L;

    public NotSupportedException() {
        super();
    }

    public NotSupportedException(ParserRuleContext context, String addMessage) {
        super(context);
        this.addMessage = addMessage;
    }

    private String addMessage = "";


    @Override
    public String getMessage() {
        return "addMessage " + addMessage;
    }
}
