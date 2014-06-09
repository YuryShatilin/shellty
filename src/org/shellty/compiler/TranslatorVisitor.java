package org.shellty.compiler;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.shellty.antlr.ShelltyBaseVisitor;
import org.shellty.antlr.ShelltyParser;
import org.shellty.compiler.semantic.MetaType;
import org.shellty.compiler.semantic.Node;
import org.shellty.compiler.semantic.NodeData;
import org.shellty.compiler.semantic.NodeData.NodeType;
import org.shellty.compiler.semantic.Tree;
import org.shellty.compiler.semantic.Utils;
import org.shellty.utils.Logger;

class TranslatorVisitor extends ShelltyBaseVisitor<MetaType> {
    private Tree semanticTree;
    private CodeGen codeGenerator;

    public TranslatorVisitor() {
        semanticTree = new Tree();        
        codeGenerator = new CodeGen();
    }

    @Override
    public MetaType visitTerminal(TerminalNode terminal) {
        /* Logger.getInstance().log(terminal.getSymbol().getText() + ":" */
        /*         + terminal.getSymbol().getType()); */
        return null;
    }

    @Override
    public MetaType visitFunctionDefinition(ShelltyParser.FunctionDefinitionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MetaType visitStructSpecifier(ShelltyParser.StructSpecifierContext ctx) {
        Logger.getInstance().log(ctx.getChild(1));
        String structName = ctx.Identifier().getText();

        Node retNode = semanticTree.structInclude(structName);
        visit(ctx.getChild(3));

        semanticTree.setCurrentNode(retNode);
        return null;
    }

    NodeData.NodeType saveType;
    @Override
    public MetaType visitStructDeclaration(ShelltyParser.StructDeclarationContext ctx) {
        /* Logger.getInstance().log(ctx.getChildCount()); */
        /* Logger.getInstance().log(ctx.getChild(0).getClass()); */
        /* String toLog = ctx.typeSpecifier().getText() + " " + ctx.typeSpecifier().getStart().getType(); */

        NodeType type = Utils.getType(ctx.typeSpecifier().getStop().getType(),
                ctx.typeSpecifier().getText(), getSemanticTree());

        if (type != NodeType.INTEGER && type != NodeType.STRING) {
            // TODO: throw exception
        }

        saveType = type;

        /* Logger.getInstance().log(ctx.getChild(1).getText()); */
        return visit(ctx.structDeclaratorList());
        /* return visit(ctx.getChild(1)); */
    }

    @Override
    public MetaType visitStructDeclarator(ShelltyParser.StructDeclaratorContext ctx) {
        Logger.getInstance().log(ctx.getChild(0).getText());
        semanticTree.varInclude(ctx.getChild(0).getText(), saveType);
        return visitChildren(ctx);
    }

    @Override
    public MetaType visitEnumSpecifier(ShelltyParser.EnumSpecifierContext ctx) {
        String enumName = ctx.Identifier().getText();
        Node retNode = getSemanticTree().enumInclude(enumName);

        codeGenerator.insertSymbols(enumName + "=(");

        visit(ctx.enumeratorList());

        getSemanticTree().setCurrentNode(retNode);
        codeGenerator.insertSymbols(")");

        return null;
    }

    @Override
    public MetaType visitEnumerator(ShelltyParser.EnumeratorContext ctx) {
        String val = ctx.enumerationConstant().getText();
        getSemanticTree().varInclude(val, NodeType.EMPTY);

        codeGenerator.insertStringLiteral(val);
        codeGenerator.insertSymbols(" ");

        return null;
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

    /**
     * @return the codeGenerator
     */
    public CodeGen getCodeGenerator() {
        return codeGenerator;
    }
}
