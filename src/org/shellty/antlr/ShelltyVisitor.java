// Generated from Shellty.g4 by ANTLR 4.2.1
package org.shellty.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ShelltyParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ShelltyVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ShelltyParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull ShelltyParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#declarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarator(@NotNull ShelltyParser.DeclaratorContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(@NotNull ShelltyParser.ExpressionStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(@NotNull ShelltyParser.UnaryExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#designation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesignation(@NotNull ShelltyParser.DesignationContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclaration(@NotNull ShelltyParser.ParameterDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#parameterTypeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterTypeList(@NotNull ShelltyParser.ParameterTypeListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#designator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesignator(@NotNull ShelltyParser.DesignatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(@NotNull ShelltyParser.PrimaryExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#initDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitDeclarator(@NotNull ShelltyParser.InitDeclaratorContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#typeQualifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeQualifierList(@NotNull ShelltyParser.TypeQualifierListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#structDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclarator(@NotNull ShelltyParser.StructDeclaratorContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#enumerator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumerator(@NotNull ShelltyParser.EnumeratorContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(@NotNull ShelltyParser.ParameterListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#declarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationList(@NotNull ShelltyParser.DeclarationListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#shiftExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftExpression(@NotNull ShelltyParser.ShiftExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#blockItemList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItemList(@NotNull ShelltyParser.BlockItemListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#typedefName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedefName(@NotNull ShelltyParser.TypedefNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(@NotNull ShelltyParser.DeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(@NotNull ShelltyParser.AssignmentExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#selectionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStatement(@NotNull ShelltyParser.SelectionStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#argumentExpressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentExpressionList(@NotNull ShelltyParser.ArgumentExpressionListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(@NotNull ShelltyParser.AdditiveExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#declarationSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationSpecifier(@NotNull ShelltyParser.DeclarationSpecifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpression(@NotNull ShelltyParser.PostfixExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#bashExtension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBashExtension(@NotNull ShelltyParser.BashExtensionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull ShelltyParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExclusiveOrExpression(@NotNull ShelltyParser.ExclusiveOrExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#unaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(@NotNull ShelltyParser.UnaryOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(@NotNull ShelltyParser.FunctionDefinitionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(@NotNull ShelltyParser.ConstantExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#structDeclarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclarationList(@NotNull ShelltyParser.StructDeclarationListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#initializerList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializerList(@NotNull ShelltyParser.InitializerListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#declarationSpecifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationSpecifiers(@NotNull ShelltyParser.DeclarationSpecifiersContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#structDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaration(@NotNull ShelltyParser.StructDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#enumSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumSpecifier(@NotNull ShelltyParser.EnumSpecifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(@NotNull ShelltyParser.MultiplicativeExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(@NotNull ShelltyParser.AssignmentOperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#declarationSpecifiers2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationSpecifiers2(@NotNull ShelltyParser.DeclarationSpecifiers2Context ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(@NotNull ShelltyParser.CompilationUnitContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#directDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectDeclarator(@NotNull ShelltyParser.DirectDeclaratorContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectAbstractDeclarator(@NotNull ShelltyParser.DirectAbstractDeclaratorContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#typeSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSpecifier(@NotNull ShelltyParser.TypeSpecifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(@NotNull ShelltyParser.IdentifierListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(@NotNull ShelltyParser.ConditionalExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#translationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranslationUnit(@NotNull ShelltyParser.TranslationUnitContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(@NotNull ShelltyParser.AndExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#labeledStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabeledStatement(@NotNull ShelltyParser.LabeledStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(@NotNull ShelltyParser.RelationalExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#enumerationConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumerationConstant(@NotNull ShelltyParser.EnumerationConstantContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStatement(@NotNull ShelltyParser.IterationStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#typeQualifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeQualifier(@NotNull ShelltyParser.TypeQualifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#enumeratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumeratorList(@NotNull ShelltyParser.EnumeratorListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(@NotNull ShelltyParser.CompoundStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStatement(@NotNull ShelltyParser.JumpStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#blockItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItem(@NotNull ShelltyParser.BlockItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(@NotNull ShelltyParser.LogicalAndExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(@NotNull ShelltyParser.TypeNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(@NotNull ShelltyParser.LogicalOrExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclusiveOrExpression(@NotNull ShelltyParser.InclusiveOrExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(@NotNull ShelltyParser.EqualityExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#structDeclaratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaratorList(@NotNull ShelltyParser.StructDeclaratorListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#castExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(@NotNull ShelltyParser.CastExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#typeDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclarator(@NotNull ShelltyParser.TypeDeclaratorContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#specifierQualifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecifierQualifierList(@NotNull ShelltyParser.SpecifierQualifierListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#externalDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternalDeclaration(@NotNull ShelltyParser.ExternalDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializer(@NotNull ShelltyParser.InitializerContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#initDeclaratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitDeclaratorList(@NotNull ShelltyParser.InitDeclaratorListContext ctx);

	/**
	 * Visit a parse tree produced by {@link ShelltyParser#structSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructSpecifier(@NotNull ShelltyParser.StructSpecifierContext ctx);
}