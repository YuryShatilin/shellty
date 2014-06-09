package org.shellty.compiler;

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
        return null;
    }

    @Override
    public MetaType visitFunctionDefinition(ShelltyParser.FunctionDefinitionContext ctx) {
        NodeType returnType = Utils.getType(ctx.typeDeclarator().getStart().getType(),
                ctx.typeDeclarator().getText(), getSemanticTree());

        Node retNode = getSemanticTree().functionInclude(ctx.Identifier().getText(), returnType);

        codeGenerator.insertLine(String.format("function %s () {", ctx.Identifier().getText()));

        if (ctx.parameterTypeList() != null) 
            visit(ctx.parameterTypeList());

        getSemanticTree().calcParametrCount(retNode);

        visit(ctx.compoundStatement());
        getSemanticTree().setCurrentNode(retNode);

        codeGenerator.insertLine(String.format("}\n"));

        return null;
    }

    @Override 
    public MetaType visitParameterList(ShelltyParser.ParameterListContext ctx) {
        visitChildren(ctx);
        return null;
    }

    @Override
    public MetaType visitParameterDeclaration(ShelltyParser.ParameterDeclarationContext ctx) {
        NodeType paramType = Utils.getType(ctx.typeDeclarator().getStart().getType(), 
                ctx.typeDeclarator().getText(), getSemanticTree());
        getSemanticTree().varInclude(ctx.Identifier().getText(), paramType);
        return null;
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

        NodeType type = Utils.getType(ctx.typeSpecifier().getStart().getType(),
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
        codeGenerator.insertLine(")\n");

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
    public MetaType visitBashExtension(ShelltyParser.BashExtensionContext ctx) {
        for (TerminalNode stringLiteral : ctx.StringLiteral()) {
            String insertion = stringLiteral.getSymbol().getText();
            insertion = insertion.replaceAll("\"", "");
            codeGenerator.insertLine(insertion);
        }
        return null;
    }

    @Override
    public MetaType defaultResult() {
        return null;
    }

    public Tree getSemanticTree() {
        return semanticTree;
    }

    public CodeGen getCodeGenerator() {
        return codeGenerator;
    }
}
