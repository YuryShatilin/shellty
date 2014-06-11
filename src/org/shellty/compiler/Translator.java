package org.shellty.compiler;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.shellty.antlr.ShelltyBaseVisitor;
import org.shellty.antlr.ShelltyParser;
import org.shellty.compiler.semantic.Meta.BasicMetaType;
import org.shellty.compiler.semantic.Meta.ComplexType;
import org.shellty.compiler.semantic.Meta.EnumType;
import org.shellty.compiler.semantic.Meta.IntegerType;
import org.shellty.compiler.semantic.Meta.StringType;
import org.shellty.compiler.semantic.Node;
import org.shellty.compiler.semantic.NodeData;
import org.shellty.compiler.semantic.NodeData.NodeType;
import org.shellty.compiler.semantic.Tree;
import org.shellty.compiler.semantic.Utils;
import org.shellty.utils.Logger;

import java.util.List;

class Translator extends ShelltyBaseVisitor<BasicMetaType> {
    private Tree semanticTree;
    private CodeGen codeGenerator;

    public Translator() {
        semanticTree = new Tree();        
        codeGenerator = new CodeGen();
    }

    @Override
    public BasicMetaType visitTerminal(TerminalNode terminal) {
        return null;
    }

    @Override
    public BasicMetaType visitCompoundStatement(ShelltyParser.CompoundStatementContext ctx) {
        codeGenerator.incIndent();
        BasicMetaType ret = visitChildren(ctx);
        codeGenerator.decIndent();
        return ret;
    }

    private int currNumberParam = 0;
    @Override
    public BasicMetaType visitFunctionDefinition(ShelltyParser.FunctionDefinitionContext ctx) {
        NodeType returnType = Utils.getType(ctx.typeDeclarator().getStart().getType(),
                ctx.typeDeclarator().getText(), getSemanticTree());

        if (returnType == NodeType.COMPLEXVAR) {
            // TODO: return complex var not supported
        }

        Node retNode = getSemanticTree().functionInclude(ctx.Identifier().getText(), returnType);

        codeGenerator.insertLine(String.format("function %s () {\n :", ctx.Identifier().getText()));

        currNumberParam = 0;
        if (ctx.parameterTypeList() != null) 
            visit(ctx.parameterTypeList());
        getSemanticTree().calcParametrCount(retNode);
        
        visit(ctx.compoundStatement());
        getSemanticTree().setCurrentNode(retNode);

        codeGenerator.insertLine(String.format("}\n"));

        return null;
    }


    @Override
    public BasicMetaType visitParameterDeclaration(ShelltyParser.ParameterDeclarationContext ctx) {
        currNumberParam += 1;
        Node varNode = getSemanticTree().varInclude(ctx.Identifier().getText(), ctx.typeDeclarator().getStart().getType(),
                ctx.typeDeclarator().getText());

        if (ctx.getChildCount() > 2) {
            varNode.getData().setArrayVar(true);
        }

        codeGenerator.insertParametrDeclaration(varNode,
                currNumberParam);
        return null;
    }

    @Override
    public BasicMetaType visitStructSpecifier(ShelltyParser.StructSpecifierContext ctx) {
        Logger.getInstance().log(ctx.getChild(1));
        String structName = ctx.Identifier().getText();

        Node retNode = semanticTree.structInclude(structName);
        visit(ctx.getChild(3));

        semanticTree.setCurrentNode(retNode);
        return null;
    }

    NodeData.NodeType saveType;
    @Override
    public BasicMetaType visitStructDeclaration(ShelltyParser.StructDeclarationContext ctx) {
        /* Logger.getInstance().log(ctx.getChildCount()); */
        /* Logger.getInstance().log(ctx.getChild(0).getClass()); */
        /* String toLog = ctx.typeSpecifier().getText() + " " + ctx.typeSpecifier().getStart().getType(); */

        NodeType type = Utils.getType(ctx.typeSpecifier().getStart().getType(),
                ctx.typeSpecifier().getText(), getSemanticTree());

        if (type != NodeType.INTEGER && type != NodeType.STRING) {
            // TODO: struct contents integer or string only
        }

        saveType = type;

        /* Logger.getInstance().log(ctx.getChild(1).getText()); */
        return visit(ctx.structDeclaratorList());
        /* return visit(ctx.getChild(1)); */
    }

    @Override
    public BasicMetaType visitStructDeclarator(ShelltyParser.StructDeclaratorContext ctx) {
        /* Logger.getInstance().log(ctx.getChild(0).getText()); */
        semanticTree.varInclude(ctx.getChild(0).getText(), saveType);
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitDeclarator(ShelltyParser.DeclaratorContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitEnumSpecifier(ShelltyParser.EnumSpecifierContext ctx) {
        String enumName = ctx.Identifier().getText();
        Node retNode = getSemanticTree().enumInclude(enumName);

        codeGenerator.insertSymbols(enumName + "=(");

        visit(ctx.enumeratorList());

        getSemanticTree().setCurrentNode(retNode);
        codeGenerator.insertSymbols(")");
        codeGenerator.insertLine();

        return null;
    }

    @Override
    public BasicMetaType visitEnumerator(ShelltyParser.EnumeratorContext ctx) {
        String val = ctx.enumerationConstant().getText();
        getSemanticTree().varInclude(val, NodeType.EMPTY);

        codeGenerator.insertStringLiteral(val);
        codeGenerator.insertSymbols(" ");

        return null;
    }

    @Override
    public BasicMetaType visitBashExtension(ShelltyParser.BashExtensionContext ctx) {
        for (TerminalNode stringLiteral : ctx.StringLiteral()) {
            String insertion = stringLiteral.getSymbol().getText();
            insertion = insertion.replaceAll("\"", "");
            codeGenerator.insertLine(insertion);
        }
        return null;
    }

    @Override
    public BasicMetaType visitDeclaration(ShelltyParser.DeclarationContext ctx) {
        if (ctx.getParent() instanceof ShelltyParser.BlockItemContext) {
            int tokenType = ctx.typeDeclarator().getStart().getType();
            String tokenText = ctx.typeDeclarator().getText();
            /* int tokenType = ctx.declarationSpecifiers().getStart().getType(); */
            /* ctx.declarationSpecifiers().declarationSpecifier(); */
            /* String tokenText = ctx.declarationSpecifiers().declarationSpecifier().getText(); */
            getSemanticTree().varInclude("", tokenType, tokenText);

            visit(ctx.initDeclaratorList());

            getSemanticTree().getCurrentNode().getParentNode().setLeftNode(null);
            getSemanticTree().setCurrentNode(getSemanticTree().getCurrentNode().getParentNode());
            return null;
        }
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitInitDeclarator(ShelltyParser.InitDeclaratorContext ctx) {
        Node futureVarNode = new Node(null, null, null, 
                getSemanticTree().getCurrentNode().getData().clone());
        futureVarNode.getData().setArrayVar(false);
        futureVarNode.setParentNode(getSemanticTree().getCurrentNode());
        getSemanticTree().getCurrentNode().setLeftNode(futureVarNode);

        String varName = ctx.declarator().directDeclarator().Identifier().getText();
        if (!getSemanticTree().checkDuplicate(varName)) {
            // TODO: throw redefinition error
        }
        
        if (ctx.initializer() != null) {
            visit(ctx.initializer());
        }

        getSemanticTree().getCurrentNode().getData().setLexem(varName);
        if (ctx.declarator().directDeclarator().getChildCount() > 1) {
            Logger.getInstance().log(varName + " is Array");
            getSemanticTree().getCurrentNode().getData().setArrayVar(true);
        }

        codeGenerator.insertVarDeclaration(getSemanticTree().getCurrentNode());

        getSemanticTree().setCurrentNode(futureVarNode);

        return null;
    }

    private Node functionNode;
    private int argCount;
    // Expressions

    @Override
    public BasicMetaType visitPrimaryExpression(ShelltyParser.PrimaryExpressionContext ctx) {
        Logger.getInstance().log(ctx.getText());
        // TODO: translate brackets
        /* if (ctx.expression() != null) { */
        /*     return visit(ctx.expression()); */
        /* } */

        if (ctx.getChild(1) instanceof TerminalNode) {
            TerminalNode terminal = (TerminalNode)ctx.getChild(1);            
            String name = ctx.Identifier(0).getText();
            Node targetNode = getSemanticTree().findUp(name);
            switch (terminal.getSymbol().getType()) {
                case 21: // =( call function 
                    functionNode = targetNode;
                    if (functionNode == null) {
                        Logger.getInstance().log("external function");
                    }
                    argCount = 0;
                    visit(ctx.argumentExpressionList());
                    codeGenerator.insertSymbols("$(" + name);
                    if (functionNode != null
                            && functionNode.getData().getCountParams() != argCount) {
                        // TODO: arguments count mismatch
                    }
                    codeGenerator.insertSymbols(")");
                    return Utils.toMetaType(functionNode.getData().getReturnType());
                case 23: // element array
                    if (targetNode == null) {
                        // TODO: error not found var
                    }
                    if (!targetNode.getData().isArrayVar()) {
                        // TODO: var is not array
                    }
                    codeGenerator.insertSymbols("${" + name +"[");
                    visit(ctx.expression());
                    codeGenerator.insertSymbols("]}");
                    return Utils.toMetaType(targetNode.getData().getType());
                case 65: // struct field
                    if (targetNode == null) {
                        // TODO: error not found var
                    }
                    if (targetNode.getData().getType() == NodeType.COMPLEXVAR) {
                        // TODO: var is not array
                    }
                    String fieldName = ctx.Identifier(1).getText();
                    Logger.getInstance().log(fieldName);
                    List<Node> fields = Tree.getFieldsStruction(targetNode.getRightNode());
                    Node varNode = null;
                    for (Node field : fields) {
                        Logger.getInstance().log(field.getData().getLexem());
                        if (field.getData().getLexem().equals(fieldName)) {
                            varNode = field;
                            break;
                        }
                    }
                    if (varNode == null) {
                        // TODO: field not found 
                    }
                    codeGenerator.insertSymbols("${" + name + "[" + fieldName + "]}");
                    return Utils.toMetaType(varNode.getData().getType());
            }
        } else if (ctx.Identifier(0) != null) {
            Node varNode = getSemanticTree().findUp(ctx.Identifier(0).getText());
            if (varNode == null) {
                // TODO: undefined var
            }
            codeGenerator.insertSymbols("${" + varNode.getData().getLexem() + "}");
            return Utils.toMetaType(varNode.getData().getType());
        }

        // TODO: generate code

        if (ctx.StringLiteral() != null) {
            String value = ctx.getText().replaceAll("\"","");
            return new StringType(value);
        }

        if (ctx.Constant() != null) {
            String value = ctx.Constant().getText();
            return new IntegerType(value);
        }

        return null;
    }


    @Override
    public BasicMetaType visitCastExpression(ShelltyParser.CastExpressionContext ctx) {
        if (ctx.castExpression() != null) {
            TerminalNode terminal = (TerminalNode)ctx.getChild(1);
            BasicMetaType rightType = visit(ctx.castExpression());
            if (terminal.getSymbol().getType() == 14) {
                return new IntegerType(rightType.getValue());
            } else {
                return new StringType(rightType.getValue());
            }
        }
        return visit(ctx.unaryExpression());
    }

    @Override
    public BasicMetaType visitAssignmentExpression(ShelltyParser.AssignmentExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitConditionalExpression(ShelltyParser.ConditionalExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitLogicalOrExpression(ShelltyParser.LogicalOrExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitLogicalAndExpression(ShelltyParser.LogicalAndExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitInclusiveOrExpression(ShelltyParser.InclusiveOrExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitExclusiveOrExpression(ShelltyParser.ExclusiveOrExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitAndExpression(ShelltyParser.AndExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitEqualityExpression(ShelltyParser.EqualityExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitRelationalExpression(ShelltyParser.RelationalExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitShiftExpression(ShelltyParser.ShiftExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitAdditiveExpression(ShelltyParser.AdditiveExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitMultiplicativeExpression(ShelltyParser.MultiplicativeExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitPostfixExpression(ShelltyParser.PostfixExpressionContext ctx) {
        return visitChildren(ctx);
    }
    
    @Override
    public BasicMetaType visitInitializer(ShelltyParser.InitializerContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType defaultResult() {
        return null;
    }

    public Tree getSemanticTree() {
        return semanticTree;
    }

    public CodeGen getCodeGenerator() {
        return codeGenerator;
    }
}

