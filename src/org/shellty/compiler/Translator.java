package org.shellty.compiler;

import java.util.Stack;

import org.antlr.v4.runtime.misc.NotNull;
import org.shellty.compiler.semantic.Tree;
import org.shellty.antlr.ShelltyBaseListener;
import org.shellty.antlr.ShelltyParser;
import org.shellty.compiler.semantic.Node;
import org.shellty.utils.Logger;

class Translator extends ShelltyBaseListener {
    private Stack<Node> retNodes = new Stack<>();

    private Tree semanticTree = new Tree();

    public Tree getSemanticTree() {
        return semanticTree;
    }

    @Override
    public void enterStructSpecifier(@NotNull ShelltyParser.StructSpecifierContext ctx) {
        String structName = ctx.getChild(1).getText();
        Node retNode = semanticTree.structInclude(structName);
        retNodes.push(retNode);
    }

    @Override
    public void exitStructSpecifier(@NotNull ShelltyParser.StructSpecifierContext ctx) {
        semanticTree.setCurrentNode(retNodes.pop());
    }

    @Override
    public void enterDeclarator(@NotNull ShelltyParser.DeclaratorContext ctx) {
    }

    @Override
    public void enterExpression(@NotNull ShelltyParser.ExpressionContext ctx) {
    }

}
