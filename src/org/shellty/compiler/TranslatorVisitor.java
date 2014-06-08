package org.shellty.compiler;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.shellty.antlr.ShelltyBaseVisitor;
import org.shellty.antlr.ShelltyParser;
import org.shellty.compiler.semantic.MetaType;
import org.shellty.compiler.semantic.Node;
import org.shellty.compiler.semantic.NodeData;
import org.shellty.compiler.semantic.Tree;
import org.shellty.utils.Logger;

class TranslatorVisitor extends ShelltyBaseVisitor<MetaType> {
    private Tree semanticTree;
    private CodeGen codeGenerator;

    public TranslatorVisitor() {
        semanticTree = new Tree();        
    }

    @Override
    public MetaType visitTerminal(TerminalNode terminal) {
        return null;
    }

    @Override
    public MetaType visitFunctionDefinition(ShelltyParser.FunctionDefinitionContext ctx) {
        return visitChildren(ctx);
    }

    /* @Override */
    /* public MetaType visitStructDeclarator(ShelltyParser.StructDeclaratorContext ctx) { */
    /* } */

    @Override
    public MetaType visitStructSpecifier(ShelltyParser.StructSpecifierContext ctx) {
        Logger.getInstance().log(ctx.getChild(1));
        String structName = ctx.getChild(1).getText();
        Node retNode = semanticTree.structInclude(structName);
        /* MetaType t = visitChildren(ctx); */
        visit(ctx.getChild(3));
        /* visitChildren(ctx.getChild(2)); */
        semanticTree.setCurrentNode(retNode);
        return null;
    }

    NodeData.NodeType saveType;
    @Override
    public MetaType visitStructDeclaration(ShelltyParser.StructDeclarationContext ctx) {
        /* Logger.getInstance().log(ctx.getChild(0).getText()); */
        Logger.getInstance().log(ctx.getChildCount());
        Logger.getInstance().log(ctx.getChild(0).getText());
        /* Logger.getInstance().log(ctx.getChild(1).getText()); */
        return visit(ctx.getChild(1));
    }

    @Override
    public MetaType visitStructDeclarator(ShelltyParser.StructDeclaratorContext ctx) {
        Logger.getInstance().log(ctx.getChild(0));
        Logger.getInstance().log(ctx.getChild(0).getClass());
        return visitChildren(ctx);
    }
    
    @Override
    public MetaType defaultResult() {
        return null;
    }

    @Override
    public MetaType visitErrorNode(ErrorNode node) {
        return null;
    }

    public Tree getSemanticTree() {
        return semanticTree;
    }
}
