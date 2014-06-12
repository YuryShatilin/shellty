// Generated from Shellty.g4 by ANTLR 4.2.1
package org.shellty.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShelltyParser}.
 */
public interface ShelltyListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ShelltyParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull ShelltyParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull ShelltyParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterDeclarator(@NotNull ShelltyParser.DeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitDeclarator(@NotNull ShelltyParser.DeclaratorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(@NotNull ShelltyParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(@NotNull ShelltyParser.ExpressionStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(@NotNull ShelltyParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(@NotNull ShelltyParser.UnaryExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#designation}.
	 * @param ctx the parse tree
	 */
	void enterDesignation(@NotNull ShelltyParser.DesignationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#designation}.
	 * @param ctx the parse tree
	 */
	void exitDesignation(@NotNull ShelltyParser.DesignationContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration(@NotNull ShelltyParser.ParameterDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration(@NotNull ShelltyParser.ParameterDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#parameterTypeList}.
	 * @param ctx the parse tree
	 */
	void enterParameterTypeList(@NotNull ShelltyParser.ParameterTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#parameterTypeList}.
	 * @param ctx the parse tree
	 */
	void exitParameterTypeList(@NotNull ShelltyParser.ParameterTypeListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#designator}.
	 * @param ctx the parse tree
	 */
	void enterDesignator(@NotNull ShelltyParser.DesignatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#designator}.
	 * @param ctx the parse tree
	 */
	void exitDesignator(@NotNull ShelltyParser.DesignatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(@NotNull ShelltyParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(@NotNull ShelltyParser.PrimaryExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclarator(@NotNull ShelltyParser.InitDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclarator(@NotNull ShelltyParser.InitDeclaratorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#typeQualifierList}.
	 * @param ctx the parse tree
	 */
	void enterTypeQualifierList(@NotNull ShelltyParser.TypeQualifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#typeQualifierList}.
	 * @param ctx the parse tree
	 */
	void exitTypeQualifierList(@NotNull ShelltyParser.TypeQualifierListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#structDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclarator(@NotNull ShelltyParser.StructDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#structDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclarator(@NotNull ShelltyParser.StructDeclaratorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#enumerator}.
	 * @param ctx the parse tree
	 */
	void enterEnumerator(@NotNull ShelltyParser.EnumeratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#enumerator}.
	 * @param ctx the parse tree
	 */
	void exitEnumerator(@NotNull ShelltyParser.EnumeratorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(@NotNull ShelltyParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(@NotNull ShelltyParser.ParameterListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#declarationList}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationList(@NotNull ShelltyParser.DeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#declarationList}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationList(@NotNull ShelltyParser.DeclarationListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(@NotNull ShelltyParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(@NotNull ShelltyParser.ShiftExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#blockItemList}.
	 * @param ctx the parse tree
	 */
	void enterBlockItemList(@NotNull ShelltyParser.BlockItemListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#blockItemList}.
	 * @param ctx the parse tree
	 */
	void exitBlockItemList(@NotNull ShelltyParser.BlockItemListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#typedefName}.
	 * @param ctx the parse tree
	 */
	void enterTypedefName(@NotNull ShelltyParser.TypedefNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#typedefName}.
	 * @param ctx the parse tree
	 */
	void exitTypedefName(@NotNull ShelltyParser.TypedefNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull ShelltyParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull ShelltyParser.DeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(@NotNull ShelltyParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(@NotNull ShelltyParser.AssignmentExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(@NotNull ShelltyParser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(@NotNull ShelltyParser.SelectionStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#argumentExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentExpressionList(@NotNull ShelltyParser.ArgumentExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#argumentExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentExpressionList(@NotNull ShelltyParser.ArgumentExpressionListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(@NotNull ShelltyParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(@NotNull ShelltyParser.AdditiveExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#declarationSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationSpecifier(@NotNull ShelltyParser.DeclarationSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#declarationSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationSpecifier(@NotNull ShelltyParser.DeclarationSpecifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(@NotNull ShelltyParser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(@NotNull ShelltyParser.PostfixExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#bashExtension}.
	 * @param ctx the parse tree
	 */
	void enterBashExtension(@NotNull ShelltyParser.BashExtensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#bashExtension}.
	 * @param ctx the parse tree
	 */
	void exitBashExtension(@NotNull ShelltyParser.BashExtensionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull ShelltyParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull ShelltyParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveOrExpression(@NotNull ShelltyParser.ExclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveOrExpression(@NotNull ShelltyParser.ExclusiveOrExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(@NotNull ShelltyParser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(@NotNull ShelltyParser.UnaryOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(@NotNull ShelltyParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(@NotNull ShelltyParser.FunctionDefinitionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(@NotNull ShelltyParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(@NotNull ShelltyParser.ConstantExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#structDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclarationList(@NotNull ShelltyParser.StructDeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#structDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclarationList(@NotNull ShelltyParser.StructDeclarationListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#initializerList}.
	 * @param ctx the parse tree
	 */
	void enterInitializerList(@NotNull ShelltyParser.InitializerListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#initializerList}.
	 * @param ctx the parse tree
	 */
	void exitInitializerList(@NotNull ShelltyParser.InitializerListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#declarationSpecifiers}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationSpecifiers(@NotNull ShelltyParser.DeclarationSpecifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#declarationSpecifiers}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationSpecifiers(@NotNull ShelltyParser.DeclarationSpecifiersContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaration(@NotNull ShelltyParser.StructDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaration(@NotNull ShelltyParser.StructDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#enumSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterEnumSpecifier(@NotNull ShelltyParser.EnumSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#enumSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitEnumSpecifier(@NotNull ShelltyParser.EnumSpecifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(@NotNull ShelltyParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(@NotNull ShelltyParser.MultiplicativeExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(@NotNull ShelltyParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(@NotNull ShelltyParser.AssignmentOperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#declarationSpecifiers2}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationSpecifiers2(@NotNull ShelltyParser.DeclarationSpecifiers2Context ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#declarationSpecifiers2}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationSpecifiers2(@NotNull ShelltyParser.DeclarationSpecifiers2Context ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(@NotNull ShelltyParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(@NotNull ShelltyParser.CompilationUnitContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#directDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterDirectDeclarator(@NotNull ShelltyParser.DirectDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#directDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitDirectDeclarator(@NotNull ShelltyParser.DirectDeclaratorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterDirectAbstractDeclarator(@NotNull ShelltyParser.DirectAbstractDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitDirectAbstractDeclarator(@NotNull ShelltyParser.DirectAbstractDeclaratorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(@NotNull ShelltyParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(@NotNull ShelltyParser.IdentifierListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpecifier(@NotNull ShelltyParser.TypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpecifier(@NotNull ShelltyParser.TypeSpecifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(@NotNull ShelltyParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(@NotNull ShelltyParser.ConditionalExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void enterTranslationUnit(@NotNull ShelltyParser.TranslationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void exitTranslationUnit(@NotNull ShelltyParser.TranslationUnitContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(@NotNull ShelltyParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(@NotNull ShelltyParser.AndExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#leftValueAssigment}.
	 * @param ctx the parse tree
	 */
	void enterLeftValueAssigment(@NotNull ShelltyParser.LeftValueAssigmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#leftValueAssigment}.
	 * @param ctx the parse tree
	 */
	void exitLeftValueAssigment(@NotNull ShelltyParser.LeftValueAssigmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void enterLabeledStatement(@NotNull ShelltyParser.LabeledStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void exitLabeledStatement(@NotNull ShelltyParser.LabeledStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(@NotNull ShelltyParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(@NotNull ShelltyParser.RelationalExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#enumerationConstant}.
	 * @param ctx the parse tree
	 */
	void enterEnumerationConstant(@NotNull ShelltyParser.EnumerationConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#enumerationConstant}.
	 * @param ctx the parse tree
	 */
	void exitEnumerationConstant(@NotNull ShelltyParser.EnumerationConstantContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(@NotNull ShelltyParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(@NotNull ShelltyParser.IterationStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#typeQualifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeQualifier(@NotNull ShelltyParser.TypeQualifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#typeQualifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeQualifier(@NotNull ShelltyParser.TypeQualifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#enumeratorList}.
	 * @param ctx the parse tree
	 */
	void enterEnumeratorList(@NotNull ShelltyParser.EnumeratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#enumeratorList}.
	 * @param ctx the parse tree
	 */
	void exitEnumeratorList(@NotNull ShelltyParser.EnumeratorListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(@NotNull ShelltyParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(@NotNull ShelltyParser.CompoundStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(@NotNull ShelltyParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(@NotNull ShelltyParser.JumpStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void enterBlockItem(@NotNull ShelltyParser.BlockItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void exitBlockItem(@NotNull ShelltyParser.BlockItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(@NotNull ShelltyParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(@NotNull ShelltyParser.LogicalAndExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(@NotNull ShelltyParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(@NotNull ShelltyParser.TypeNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(@NotNull ShelltyParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(@NotNull ShelltyParser.LogicalOrExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterInclusiveOrExpression(@NotNull ShelltyParser.InclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitInclusiveOrExpression(@NotNull ShelltyParser.InclusiveOrExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(@NotNull ShelltyParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(@NotNull ShelltyParser.EqualityExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#structDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaratorList(@NotNull ShelltyParser.StructDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#structDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaratorList(@NotNull ShelltyParser.StructDeclaratorListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(@NotNull ShelltyParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(@NotNull ShelltyParser.CastExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#typeDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclarator(@NotNull ShelltyParser.TypeDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#typeDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclarator(@NotNull ShelltyParser.TypeDeclaratorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#specifierQualifierList}.
	 * @param ctx the parse tree
	 */
	void enterSpecifierQualifierList(@NotNull ShelltyParser.SpecifierQualifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#specifierQualifierList}.
	 * @param ctx the parse tree
	 */
	void exitSpecifierQualifierList(@NotNull ShelltyParser.SpecifierQualifierListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#externalDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExternalDeclaration(@NotNull ShelltyParser.ExternalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#externalDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExternalDeclaration(@NotNull ShelltyParser.ExternalDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer(@NotNull ShelltyParser.InitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer(@NotNull ShelltyParser.InitializerContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclaratorList(@NotNull ShelltyParser.InitDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclaratorList(@NotNull ShelltyParser.InitDeclaratorListContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShelltyParser#structSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStructSpecifier(@NotNull ShelltyParser.StructSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShelltyParser#structSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStructSpecifier(@NotNull ShelltyParser.StructSpecifierContext ctx);
}