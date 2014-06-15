package org.shellty.compiler;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.shellty.antlr.ShelltyBaseVisitor;
import org.shellty.antlr.ShelltyParser;
import org.shellty.compiler.error.*;
import org.shellty.compiler.semantic.Meta.*;
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
    private BasicMetaType switchType = null;
    private boolean inCycle = false;

    public Translator() {
        semanticTree = new Tree();        
        codeGenerator = new CodeGen();
    }

    @Override
    public BasicMetaType visitCompoundStatement(ShelltyParser.CompoundStatementContext ctx) {
        codeGenerator.incIndent();
        BasicMetaType ret = visitChildren(ctx);
        codeGenerator.decIndent();
        return ret;
    }

    @Override
    public BasicMetaType visitExternalDeclaration(ShelltyParser.ExternalDeclarationContext ctx) {
        codeGenerator.insertLine();
        return visitChildren(ctx);
    }

    private int currNumberParam = 0;
    @Override
    public BasicMetaType visitFunctionDefinition(ShelltyParser.FunctionDefinitionContext ctx) {
        NodeType returnType = Utils.getType(ctx.typeDeclarator().getStart().getType(),
                ctx.typeDeclarator().getText(), getSemanticTree());

        if (returnType == NodeType.COMPLEXVAR) {
            throw new NotSupportedException(ctx, "return complex var");
        }

        Node retNode = getSemanticTree().functionInclude(ctx.Identifier().getText(), returnType);
        if (retNode == null) {
            throw new RedefinitionException(ctx, ctx.Identifier().getText());
        }

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

        if (varNode == null) {
            throw new NotSupportedException(ctx, "");
        }

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
        if (retNode == null) {
            throw new NotSupportedException(ctx, "");
        }
        visit(ctx.getChild(3));

        semanticTree.setCurrentNode(retNode);
        return null;
    }

    @Override
    public BasicMetaType visitStructFieldInitializer(ShelltyParser.StructFieldInitializerContext ctx) {
        Node lastVar = getSemanticTree().getCurrentNode();
        if (ctx.Constant() != null && lastVar.getData().getType() != NodeType.INTEGER ||
            ctx.StringLiteral() != null && lastVar.getData().getType() != NodeType.STRING) {
            // TODO: error init
            throw new IllegalOperationException(ctx);
        }

        lastVar.getData().setValue(ctx.getChild(1).getText());
        /* if (ctx.Constant() != null)  { */
        /*     if (getSemanticTree().getCurrentNode().getData().getType() !=  */
        /*             NodeType.INTEGER) { */
        /*         // TODO: error */
        /*         Logger.getInstance().log("error"); */
        /*         return null; */
        /*     } */
        /*     getSemanticTree().getCurrentNode().getData().setValue(ctx.Constant().getText()); */
        /* } */
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
            throw new NotSupportedException(ctx, "");
        }

        saveType = type;

        /* Logger.getInstance().log(ctx.getChild(1).getText()); */
        return visit(ctx.structDeclaratorList());
        /* return visit(ctx.getChild(1)); */
    }

    @Override
    public BasicMetaType visitStructDeclarator(ShelltyParser.StructDeclaratorContext ctx) {
        /* Logger.getInstance().log(ctx.getChild(0).getText()); */
        Node varNode = semanticTree.varInclude(ctx.getChild(0).getText(), saveType);
        if (varNode == null) {
            throw new NotSupportedException(ctx, "");
        }
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitDeclarator(ShelltyParser.DeclaratorContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public BasicMetaType visitEnumSpecifier(ShelltyParser.EnumSpecifierContext ctx) {
        String enumName = ctx.Identifier().getText();
        if (getSemanticTree().enumInclude(enumName) == null) {
            throw new NotSupportedException(ctx, "");
        }

        codeGenerator.insertSymbols(enumName + "=(");

        visit(ctx.enumeratorList());

        /* getSemanticTree().setCurrentNode(retNode); */
        codeGenerator.insertSymbols(")");
        codeGenerator.insertLine();

        return null;
    }

    @Override
    public BasicMetaType visitEnumerator(ShelltyParser.EnumeratorContext ctx) {
        String val = ctx.enumerationConstant().getText();
        Node varNode = getSemanticTree().varInclude(val, NodeType.ENUMNAME);
        if (varNode == null) {
            throw new NotSupportedException(ctx, "");
        }

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
        Logger.getInstance().log(ctx.getText());
        if (ctx.typeDeclarator() != null) {
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
            throw new RedefinitionException(ctx, varName);
        }
        

        getSemanticTree().getCurrentNode().getData().setLexem(varName);
        if (ctx.declarator().directDeclarator().getChildCount() > 1) {
            Logger.getInstance().log(varName + " is Array");
            getSemanticTree().getCurrentNode().getData().setArrayVar(true);
            if (getSemanticTree().getCurrentNode().getData().getType() == NodeType.COMPLEXVAR) {
                throw new NotSupportedException(ctx, " arrays of complex vars");
            }
        }


        Node varNode = getSemanticTree().getCurrentNode();
        if (ctx.initializer() != null) {
            if (varNode.getData().getType() != NodeType.INTEGER && 
                    varNode.getData().getType() != NodeType.STRING &&
                    varNode.getData().getType() != NodeType.ENUMVAR ) {
                // TODO: error init string and integer only
                throw new NotSupportedException(ctx, ". Only integer, string and enum type init");
            }
            BasicMetaType in = visit(ctx.initializer());
            if (varNode.getData().getType() != NodeType.ENUMVAR) {
                if (!Utils.toMetaType(varNode.getData().getType()).equals(in)) {
                    // TODO: error init
                    throw new IllegalOperationException(ctx);
                }
            } else {
                EnumType etype = new EnumType("",varNode.getRightNode());
                if (!etype.equals(in)) {
                    return null;
                }
            }
            varNode.getData().setValue(in.getValue());
        } 

        codeGenerator.insertVarDeclaration(getSemanticTree().getCurrentNode());
        Logger.getInstance().log("this");
        getSemanticTree().setCurrentNode(futureVarNode);

        return null;
    }


    @Override
    public BasicMetaType visitExpressionStatement(ShelltyParser.ExpressionStatementContext ctx) {
        codeGenerator.insertLine();
        /* BasicMetaType result = visitChildren(ctx); */
        if (ctx.expression() != null) {
            BasicMetaType result = visit(ctx.expression());
            if (result.getValue().startsWith("$")) {
                result.setValue(result.getValue().replaceFirst("\\$",""));
            }
            codeGenerator.insertLine(result.getValue());
            return result;
        }
        return null;
    }

    @Override
    public BasicMetaType visitExpression(ShelltyParser.ExpressionContext ctx) {
        return visit(ctx.assignmentExpression());
    }

    private Node functionNode;
    private int argCount = 0;
    // Expressions
    //
    public BasicMetaType visitArgumentExpressionList(ShelltyParser.ArgumentExpressionListContext ctx) {
        if (functionNode != null && argCount > functionNode.getData().getCountParams()) {
            // TODO: mismatch functions arguments 
            throw new SimpleErrorException(ctx, "mismatch arguments function " +
                    functionNode.getData().getLexem());
        }

        BasicMetaType arguments = new VoidType();
        if (ctx.argumentExpressionList() != null) {
            arguments.setValue(visit(ctx.argumentExpressionList()).getValue());
        }

        BasicMetaType argument = visit(ctx.conditionalExpression());
        Logger.getInstance().log(argument.getValue());
        if (functionNode != null) {
            Node parametrNode = getSemanticTree().getParametr(functionNode, argCount);
            Logger.getInstance().log(argCount + " " + argument.getValue()); 
            Logger.getInstance().log(parametrNode.getData().getLexem());
            BasicMetaType parametr = Utils.toMetaType(parametrNode.getData().getType());
            if (!argument.equals(parametr) && 
                    (parametrNode.getData().getType() == NodeType.INTEGER 
                    && argument instanceof BoolType)) {
                // TODO: mismathc argument function 
                throw new SimpleErrorException(ctx, "mismatch arguments function " +
                        functionNode.getData().getLexem());
            }
        }

        argCount += 1;
        arguments.setValue(arguments.getValue() + " " + argument.getValue());

        Logger.getInstance().log(arguments.getValue());
        return arguments;
    }

    @Override
    public BasicMetaType visitPrimaryExpression(ShelltyParser.PrimaryExpressionContext ctx) {
        Logger.getInstance().log(ctx.getText());
        // TODO: translate brackets
        if (ctx.getChild(0) instanceof TerminalNode && 
                ((TerminalNode)ctx.getChild(0)).
                getSymbol().getType() == ShelltyParser.LeftParen) {

            BasicMetaType expr = visit(ctx.expression());
            if (expr instanceof IntegerType) {
                expr.setValue("(" + expr.getValue() + ")");
                return expr;
            } else {
                // TODO: error. not supported
                throw new IllegalOperationException(ctx);
            }
            /* return visit(ctx.expression()); */
        }

        if (ctx.getChild(1) instanceof TerminalNode) {
            TerminalNode terminal = (TerminalNode)ctx.getChild(1);            
            String name = ctx.Identifier(0).getText();
            Node targetNode = getSemanticTree().findUp(name);
            switch (terminal.getSymbol().getType()) {
                case 21:{ // =( call function 
                    Node saveFunctionNode = functionNode;
                    functionNode = targetNode;
                    if (functionNode == null) {
                        Logger.getInstance().log("external function");
                    }
                    int saveArgCount = argCount;
                    argCount = 0;
                    BasicMetaType argResult = null;

                    if (ctx.argumentExpressionList() != null) {
                        argResult = visit(ctx.argumentExpressionList());
                    } else {
                        argResult = new VoidType();
                        argResult.setValue("");
                    }

                    /* codeGenerator.insertSymbols("$(" + name); */
                    if (functionNode != null
                            && functionNode.getData().getCountParams() != argCount) {
                        // TODO: arguments count mismatch
                        throw new IllegalOperationException();
                        /* Logger.getInstance().log("err " + functionNode.getData().getCountParams() + " "  */
                        /*         + argCount); */
                        /* return null; */
                    }
                    argCount = saveArgCount;
                    /* codeGenerator.insertSymbols(")"); */
                    BasicMetaType retType;
                    if (functionNode != null) {
                        retType= Utils.toMetaType(functionNode.getData().getReturnType());
                    } else {
                        retType = new StringType();
                    }

                    if (functionNode != null && functionNode.getData().getReturnType() != NodeType.VOID ) {
                        retType.setValue("$(" + name + " " + argResult.getValue() + " ; echo $?)");
                    } else {
                        retType.setValue("$(" + name + " " + argResult.getValue() + " )");
                    }
                    Logger.getInstance().log(retType.getValue());

                    functionNode = saveFunctionNode;

                    return retType;
                }
                case 23:{ // element array
                    if (targetNode == null) {
                        // TODO: error not found var
                        throw new UndefinedException(ctx, ctx.Identifier(0).getText());
                    }
                    if (!targetNode.getData().isArrayVar()) {
                        // TODO: var is not array
                        throw new SimpleErrorException(ctx, ctx.Identifier(0).getText() + " is not array");
                    }
                    /* codeGenerator.insertSymbols("${" + name +"["); */
                    BasicMetaType index = visit(ctx.expression());
                    /* codeGenerator.insertSymbols("]}"); */
                    BasicMetaType retType = Utils.toMetaType(targetNode.getData().getType());
                    retType.setValue("${" + name + "[" + index.getValue() + "]}");
                    if (targetNode.getData().getType() == NodeType.INTEGER) {
                        retType.setValue("$((" + retType.getValue() + "))");
                    }
                    return retType;
                }
                case 65:{ // struct field
                    if (targetNode == null) {
                        // TODO: error not found var
                        throw new UndefinedException(ctx, ctx.Identifier(0).getText());
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
                        return null;
                    }
                    /* codeGenerator.insertSymbols("${" + name + "[" + fieldName + "]}"); */
                    BasicMetaType retType = Utils.toMetaType(varNode.getData().getType());
                    retType.setValue("${" + name + "[" + fieldName + "]}");
                    return retType;
                }
            }
        } else if (ctx.Identifier(0) != null) {
            Node varNode = getSemanticTree().findUp(ctx.Identifier(0).getText());
            if (varNode == null) {
                // TODO: undefined var
                throw new UndefinedException(ctx, ctx.Identifier(0).getText());
            }
            Logger.getInstance().log(ctx.Identifier(0).getText());
            Logger.getInstance().log(varNode.getData());
            if (varNode.getData().getType() == NodeType.ENUMNAME) {
                Node enumNode = getSemanticTree().findUpDefEnum(varNode);
                return new EnumType(varNode.getData().getLexem(), enumNode);
            }
            /* codeGenerator.insertSymbols("${" + varNode.getData().getLexem() + "}"); */
            BasicMetaType retType = null;
            if (varNode.getData().isArrayVar()) {
                retType = new ArrayType();
                retType.setValue("\"$(declare -p " + varNode.getData().getLexem() + ")\"");
                /* retType.setValue(varNode.getData().getLexem() + "[@]"); */
                 /* "$(declare -p assoc_array)"  */
            } else  if (varNode.getData().getType() == NodeType.COMPLEXVAR) {
                retType = new ComplexType("\"$(declare -p " + varNode.getData().getLexem() + ")\"", 
                        varNode.getRightNode());
            } else if (varNode.getData().getType() == NodeType.ENUMVAR) {
                retType = new EnumType("${" + varNode.getData().getLexem() + "}",
                        varNode.getRightNode());
            } else {
                retType = Utils.toMetaType(varNode.getData().getType());
                retType.setValue("${" + varNode.getData().getLexem() + "}");
            }
            return retType;
        }

        if (ctx.Constant() != null) {
            String value = ctx.Constant().getText();
            return new IntegerType(value);
        }

        if (ctx.StringLiteral() != null) {
            String value = "";
            Logger.getInstance().log("String literal");
            for (TerminalNode literal : ctx.StringLiteral()) {
                value += literal.getText().replaceAll("\"","");
            }
            value = "\"" + value + "\"";
            return new StringType(value);
        }


        Logger.getInstance().log("error - " + ctx.getText());

        return null;
    }

    @Override
    public BasicMetaType visitUnaryExpression(ShelltyParser.UnaryExpressionContext ctx) {
        if (ctx.postfixExpression() != null) {
            return visit(ctx.postfixExpression());
        }

        // TODO: not suported
        throw new NotSupportedException(ctx, "unary operation");
        /* Logger.getInstance().log("not suported"); */
        /* return null; */

        /* BasicMetaType right = visit(ctx.unaryExpression()); */
        /* if (ctx.unaryOperator() != null) { */
        /*     int terminalType = ((TerminalNode)ctx.getChild(0).getChild(0)).getSymbol().getType();  */
        /*     if (terminalType == ShelltyParser.Not) { */
        /*         if (right instanceof BoolType) { */
        /*             return new BoolType("$((!" + right.getValue() + "))"); */
        /*         } else { */
        /*             // TODO: error operation */
        /*             Logger.getInstance().log("error"); */
        /*             return null; */
        /*         } */
        /*     } */
        /* } */
        /*  */
        /* if (right instanceof IntegerType) { */
        /*     return new IntegerType("$(("+ctx.getChild(0).getText() + right.getValue() + "))"); */
        /* } else { */
        /*     // TODO: error operation */
        /*     Logger.getInstance().log("error"); */
        /*     return null; */
        /* } */
    }

    @Override
    public BasicMetaType visitCastExpression(ShelltyParser.CastExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.castExpression() != null) {
            TerminalNode terminal = (TerminalNode)ctx.getChild(1);
            BasicMetaType rightType = visit(ctx.castExpression());
            if (rightType instanceof ComplexType) {
                // TODO: error cast
                throw new IllegalOperationException();
            }

            if (rightType instanceof EnumType) {
                // TODO: error cast
                throw new IllegalOperationException();
            }

            if (terminal.getSymbol().getType() == ShelltyParser.Int) {
                return new IntegerType(rightType.getValue());
            } else {
                return new StringType(rightType.getValue());
            }
        }
        return visit(ctx.unaryExpression());
    }

    @Override
    public BasicMetaType visitAssignmentExpression(ShelltyParser.AssignmentExpressionContext ctx) {
        /* assignmentExpression */
        /*     :   conditionalExpression */
        /*     |   leftValueAssigment assignmentOperator conditionalExpression */
        Logger.getInstance().log(null);
        if (ctx.leftValueAssigment() == null) {
            return visit(ctx.conditionalExpression());
        }
        BasicMetaType left = visit(ctx.leftValueAssigment());
        BasicMetaType right = visit(ctx.conditionalExpression());
        if (!left.equals(right)) {
            // TODO: get erorr
            throw new IllegalOperationException();
        }
        left.setValue(left.getValue() + "=" + right.getValue());
        return left;
    }

    @Override
    public BasicMetaType visitLeftValueAssigment(ShelltyParser.LeftValueAssigmentContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.getChild(1) instanceof TerminalNode) {
            TerminalNode terminal = (TerminalNode)ctx.getChild(1);            
            String name = ctx.Identifier(0).getText();
            Node targetNode = getSemanticTree().findUp(name);
            if (targetNode == null) {
                // TODO: error not found var
                throw new UndefinedException(ctx, name);
            }
            switch (terminal.getSymbol().getType()) {
                case 23:{ // element array
                    if (!targetNode.getData().isArrayVar()) {
                        // TODO: var is not array
                        throw new SimpleErrorException(ctx, ctx.Identifier(0).getText() + " is not array");
                    }
                    /* codeGenerator.insertSymbols("${" + name +"["); */
                    BasicMetaType index = visit(ctx.expression());
                    /* codeGenerator.insertSymbols("]}"); */
                    BasicMetaType retType = Utils.toMetaType(targetNode.getData().getType());
                    retType.setValue(name + "[" + index.getValue() + "]");
                    return retType;
                }
                case 65:{ // struct field
                    if (targetNode.getData().getType() == NodeType.COMPLEXVAR) {
                        // TODO: var is not array
                        throw new SimpleErrorException(ctx, ctx.Identifier(0).getText() + " is not complex var");
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
                        throw new UndefinedException(ctx, fieldName);
                    }
                    /* codeGenerator.insertSymbols("${" + name + "[" + fieldName + "]}"); */
                    BasicMetaType retType = Utils.toMetaType(varNode.getData().getType());
                    retType.setValue(name + "[" + fieldName + "]");
                    return retType;
                }
            }
        } else if (ctx.Identifier(0) != null) {
            Node varNode = getSemanticTree().findUp(ctx.Identifier(0).getText());
            if (varNode == null) {
                // TODO: undefined var
                throw new UndefinedException(ctx, ctx.Identifier(0).getText());
            }
            /* codeGenerator.insertSymbols("${" + varNode.getData().getLexem() + "}"); */
            BasicMetaType retType = Utils.toMetaType(varNode.getData().getType());
            retType.setValue(varNode.getData().getLexem());
            return retType;
        }
        
        // never, but compiler get error =(
        return null;
    }
    
    @Override
    public BasicMetaType visitConditionalExpression(ShelltyParser.ConditionalExpressionContext ctx) {
        Logger.getInstance().log(null);
        return visit(ctx.logicalOrExpression());
    }

    @Override
    public BasicMetaType visitLogicalOrExpression(ShelltyParser.LogicalOrExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.logicalOrExpression() == null) {
            return visit(ctx.logicalAndExpression());
        } else {
            BasicMetaType left = visit(ctx.logicalOrExpression());
            BasicMetaType right = visit(ctx.logicalAndExpression());
            if (left.equals(right) && left instanceof BoolType) {
                return new BoolType("$((" + left.getValue() + " || " + right.getValue()+ "))");
            } else {
                // TODO: not boolean value
                throw new IllegalOperationException(ctx);
            }
        }
    }

    @Override
    public BasicMetaType visitLogicalAndExpression(ShelltyParser.LogicalAndExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.logicalAndExpression() == null) {
            return visit(ctx.inclusiveOrExpression());
        }
        BasicMetaType right = visit(ctx.inclusiveOrExpression());
        BasicMetaType left = visit(ctx.logicalAndExpression());
        if (left.equals(right) && left instanceof BoolType) {
            return new BoolType("$((" + left.getValue() + " && " + right.getValue() + "))");
        } else {
            // TODO: not boolean value
            throw new IllegalOperationException(ctx);
        }
    }

    @Override
    public BasicMetaType visitInclusiveOrExpression(ShelltyParser.InclusiveOrExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.inclusiveOrExpression() == null) {
            return visit(ctx.exclusiveOrExpression());
        }
        BasicMetaType left = visit(ctx.inclusiveOrExpression());
        BasicMetaType right = visit(ctx.exclusiveOrExpression());
        if (left.equals(right) && left instanceof IntegerType) {
            return new IntegerType(left.getValue() + " | " + right.getValue());
        } else {
            // TODO: not integer value
            throw new IllegalOperationException(ctx);
        }
    }

    @Override
    public BasicMetaType visitExclusiveOrExpression(ShelltyParser.ExclusiveOrExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.exclusiveOrExpression() == null) {
            return visit(ctx.andExpression());
        }
        BasicMetaType left = visit(ctx.exclusiveOrExpression());
        BasicMetaType right = visit(ctx.andExpression());
        if (left.equals(right) && left instanceof IntegerType) {
            return new IntegerType(left.getValue() + " ^ " + right.getValue());
        } else {
            // TODO: not integer value
            throw new IllegalOperationException(ctx);
        }
    }

    @Override
    public BasicMetaType visitAndExpression(ShelltyParser.AndExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.andExpression() == null) {
            return visit(ctx.equalityExpression());
        }
        BasicMetaType left = visit(ctx.andExpression());
        BasicMetaType right = visit(ctx.equalityExpression());
        if (left.equals(right) && left instanceof IntegerType) {
            return new IntegerType(left.getValue() + " & " + right.getValue());
        } else {
            // TODO: not integer value
            throw new IllegalOperationException(ctx);
        }
    }

    @Override
    public BasicMetaType visitEqualityExpression(ShelltyParser.EqualityExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.equalityExpression() == null) {
            return visit(ctx.relationalExpression());
        }
        BasicMetaType left = visit(ctx.equalityExpression());
        BasicMetaType right = visit(ctx.relationalExpression());
        if (!left.equals(right)) {
            // TODO: invalid equality 
            throw new IllegalOperationException(ctx);
        }
        int terminalType = ((TerminalNode)ctx.getChild(1)).getSymbol().getType(); // 62 63 !=
        if (left instanceof StringType || left instanceof EnumType) {
            if (terminalType == 62) {
                return new BoolType("$((!(($(test " + 
                        left.getValue() + " == " + 
                       right.getValue() + " ; echo $?)))))");
                /* return new BoolType("$(" + left.getValue() + " -eq " */
                /*         + right.getValue() + ")"); */
            } else {
                return new BoolType("$((!(($(test " + 
                        left.getValue() + " != " + 
                       right.getValue() + " ; echo $?)))))");
            }
        } else if (left instanceof IntegerType) {
            if (terminalType == 62) {
                return new BoolType("$((" + left.getValue() + "=="
                        + right.getValue() + "))");
            } else {
                return new BoolType("$((" + left.getValue() + "!="
                        + right.getValue() + "))");
            }
        } else {
            // TODO: invalid equality
            Logger.getInstance().log("error " + left);
            throw new IllegalOperationException(ctx);
        }
    }

    @Override
    public BasicMetaType visitRelationalExpression(ShelltyParser.RelationalExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.relationalExpression() == null) {
            return visit(ctx.shiftExpression());
        }
        BasicMetaType left = visit(ctx.relationalExpression());
        BasicMetaType right = visit(ctx.shiftExpression());
        if (!left.equals(right) || !(left instanceof IntegerType)){
            // TODO: not valide relation operation
            throw new IllegalOperationException(ctx);
        }
        return new BoolType("$((" + left.getValue() + ctx.getChild(1).getText() + right.getValue() + "))");
    }

    @Override
    public BasicMetaType visitShiftExpression(ShelltyParser.ShiftExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.shiftExpression() == null) {
            return visit(ctx.additiveExpression());
        }
        BasicMetaType left = visit(ctx.shiftExpression());
        BasicMetaType right = visit(ctx.additiveExpression());
        if (!left.equals(right)){
            // TODO: not valide relation operation
            throw new IllegalOperationException(ctx);
        }
        
        if (left instanceof IntegerType) {
            return new IntegerType("$((" + left.getValue() + ctx.getChild(1).getText() + right.getValue() + "))");
        } else if (left instanceof StringType) {
            return new StringType(left.getValue() + right.getValue());
        }

        // TODO: error
        throw new IllegalOperationException(ctx);
    }

    @Override
    public BasicMetaType visitAdditiveExpression(ShelltyParser.AdditiveExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.additiveExpression() != null) {
            int typeOperation = ((TerminalNode) ctx.getChild(1)).getSymbol().getType();
            BasicMetaType leftOperand = visit(ctx.additiveExpression());
            BasicMetaType rightOperand = visit(ctx.multiplicativeExpression());

            Logger.getInstance().log(leftOperand);
            Logger.getInstance().log(rightOperand);
            Logger.getInstance().log(rightOperand.getValue());
            if (!leftOperand.equals(rightOperand)) {
                // TODO: not legal operation
                Logger.getInstance().log("error");
                throw new IllegalOperationException(ctx);
            }

            BasicMetaType retType = null;

            if (leftOperand instanceof IntegerType) {
                retType = new IntegerType();
                if (typeOperation == 33) {
                    retType.setValue("$((" + leftOperand.getValue() + "+" + rightOperand.getValue() + "))");
                } else {
                    retType.setValue("$((" + leftOperand.getValue() + "-" + rightOperand.getValue() + "))");
                }
            } else if (leftOperand instanceof StringType) {
                retType = new StringType();
                if (typeOperation == 33) {
                    retType.setValue(leftOperand.getValue() + rightOperand.getValue());
                } else {
                    throw new IllegalOperationException(ctx);
                }
            } else {
                // TODO: compiler error
                throw new IllegalOperationException(ctx);
            }

            return retType;
        } else {
            return visitChildren(ctx);
        }
    }

    @Override
    public BasicMetaType visitMultiplicativeExpression(ShelltyParser.MultiplicativeExpressionContext ctx) {
        Logger.getInstance().log(null);
        if (ctx.multiplicativeExpression() == null) {
            return visit(ctx.castExpression());
        }
        BasicMetaType left = visit(ctx.multiplicativeExpression());
        BasicMetaType right = visit(ctx.castExpression());
        if (!left.equals(right) || !(left instanceof IntegerType)){
            // TODO: not valide relation operation
            throw new IllegalOperationException(ctx);
        }
        return new IntegerType("$((" + left.getValue() + ctx.getChild(1).getText() + right.getValue() + "))");
    }

    @Override
    public BasicMetaType visitPostfixExpression(ShelltyParser.PostfixExpressionContext ctx) {
        if (ctx.primaryExpression() != null) {
            return visit(ctx.primaryExpression());
        }
        // TODO: error not suported
        Logger.getInstance().log("not support");
        throw new NotSupportedException(ctx, " postfix operation");
        /* BasicMetaType left = visit(ctx.postfixExpression()); */
        /* if (left instanceof IntegerType) { */
        /*     return new IntegerType("$((" + left.getValue() + ctx.getChild(1).getText() + "))"); */
        /* } else { */
        /*     // TODO: error */
        /*     return null; */
        /* } */
    }

    @Override
    public BasicMetaType visitJumpStatement(ShelltyParser.JumpStatementContext ctx) {
        // return
        int terminalType = ((TerminalNode)ctx.getChild(0)).getSymbol().getType();
        if ( terminalType == ShelltyParser.Return) {
            Node funcNode = getSemanticTree().findUpFunction();
            if (ctx.expression() == null)  {
                if (funcNode.getData().getReturnType() == NodeType.VOID) {
                    codeGenerator.insertLine("return");
                    return null;
                } else {
                    // TODO: error not valid return expression
                    throw new SimpleErrorException(ctx, " wrong return expression");
                }
            } 
            BasicMetaType result = visit(ctx.expression());
            /* codeGenerator.insertLine("echo " + result.getValue()); */
            codeGenerator.insertLine("return " + result.getValue());
            return null;
        } 

        if (!inCycle) {
            // TODO: error
            Logger.getInstance().log("error");
            throw new IllegalOperationException(ctx);
        }
        codeGenerator.insertLine(ctx.getChild(0).getText());
        
        return null;
    }
    

    @Override
    public BasicMetaType visitSelectionStatement(ShelltyParser.SelectionStatementContext ctx) {
        int typeTerminal = ((TerminalNode)ctx.getChild(0)).getSymbol().getType();
        
        // if
        if (typeTerminal == ShelltyParser.If){
            BasicMetaType condition = visit(ctx.expression());
            Logger.getInstance().log(ctx.getText());
            Logger.getInstance().log(condition.getValue());
            if (condition.getValue().startsWith("$")) {
                condition.setValue(condition.getValue().replaceFirst("\\$", ""));
            }
            codeGenerator.insertLine("if " + condition.getValue());
            codeGenerator.insertLine("then");
            visit(ctx.statement(0));
            if (ctx.statement(1) != null) {
                codeGenerator.insertLine("else");
                visit(ctx.statement(1));
            }
            codeGenerator.insertLine("fi");
            return null;
        }

        // switch
        if (typeTerminal == ShelltyParser.Switch) {
            BasicMetaType condition = visit(ctx.expression());
            switchType = condition;
            codeGenerator.insertLine("case " + condition.getValue() + " in");
            visit(ctx.switchStatement());
            codeGenerator.insertLine("esac");
            return null;
        }

        return null;
    }

    @Override
    public BasicMetaType visitLabeledStatement(ShelltyParser.LabeledStatementContext ctx) {
        int terminalType = ((TerminalNode)ctx.getChild(0)).getSymbol().getType();
        // default
        if (terminalType == 7) {
            codeGenerator.insertLine("*)");
        } else {
            // case 
            BasicMetaType expr = visit(ctx.constantExpression());
            if (!switchType.equals(expr)) {
                // TODO: error
                Logger.getInstance().log("error " + expr);
                throw new SimpleErrorException(ctx, " wrong case expression");
            }
            codeGenerator.insertLine(expr.getValue() + ")");
        }

        visit(ctx.statement());
        codeGenerator.insertLine(";;");
        return null;
        /* if (switchType == null) { */
        /*     Logger.getInstance().log("error"); */
        /*     return null; */
        /* } */
        /* BasicMetaType saveSwitchType = switchType; */
        /* switchType = null; */
        /*  */
        /* if (ctx.constantExpression() != null) { */
        /*     BasicMetaType expr = visit(ctx.constantExpression()); */
        /*     if (!expr.equals(saveSwitchType)) { */
        /*         // TODO: error */
        /*         Logger.getInstance().log("type mismatch"); */
        /*         return null; */
        /*     }  */
        /*  */
        /* } else { */
        /* } */

        /* switchType = saveSwitchType; */
    }

    @Override
    public BasicMetaType visitIterationStatement(ShelltyParser.IterationStatementContext ctx) {
        int terminalNode = ((TerminalNode)ctx.getChild(0)).getSymbol().getType();
        // while 
        boolean save = inCycle;
        inCycle = true;
        if (terminalNode == ShelltyParser.While) {
            BasicMetaType condition = visit(ctx.expression(0));
            if (condition.getValue().startsWith("$")) {
                condition.setValue(condition.getValue().replaceFirst("\\$",""));
            }
            codeGenerator.insertLine("while " + condition.getValue());
            codeGenerator.insertLine("do");
            visit(ctx.statement());
            codeGenerator.insertLine("done");
            inCycle = save;
            return null;
        } 
        // for parse
        String forExpression = "";
        boolean first = true;
        for (ShelltyParser.ExpressionContext expr : ctx.expression()) {
            if (!first) {
                forExpression += " ;";
            }
            if (expr != null) {
                forExpression += visit(expr).getValue();
            }
            first = false;
        }
        codeGenerator.insertLine("for (( " + forExpression + "))");
        codeGenerator.insertLine("do");
        visit(ctx.statement());
        codeGenerator.insertLine("done");
        inCycle = save;
        /* String forExpression = ""; */
        /* BasicMetaType expr = null; */
        /* if (ctx.expression(0) != null) { */
        /*     expr = visit(ctx.expression(0)); */
        /*     forExpression += expr.getValue(); */
        /* }  */
        /* forExpression += " ;"; */

        return null;
    }

    @Override
    public BasicMetaType visitInitializer(ShelltyParser.InitializerContext ctx) {
        if (ctx.conditionalExpression() == null) {
            // TODO: not suppoted
            Logger.getInstance().log("not suppoted");
            throw new NotSupportedException(ctx, " struct initilizer not supported");
        }
        return visit(ctx.conditionalExpression());
    }

    @Override
    public BasicMetaType visitErrorNode(ErrorNode error) {
        throw new IllegalOperationException();
    }

    public Tree getSemanticTree() {
        return semanticTree;
    }

    public CodeGen getCodeGenerator() {
        return codeGenerator;
    }
}

