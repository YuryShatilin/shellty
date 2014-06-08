package org.shellty.compiler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.shellty.antlr.*;
import org.shellty.utils.Logger;
import org.antlr.runtime.tree.TreeWizard.Visitor;
//
// antlr imports 
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Compiler {
    private String outputFile;
    private List<String> inputFiles;

    public Compiler(String outputFile, List<String> inputFiles) {
        this.outputFile = outputFile;
        this.inputFiles = inputFiles;
    }

    public void launch() {
        for (String filename : inputFiles) {
            Logger.getInstance().log("Process file: " + filename);
            try {
                processFile(filename);
            } catch (IOException ex) {
                // TODO: resolve later
            }
        }
    }

    public void processFile(String filename) throws IOException {
        ShelltyLexer lexer = new ShelltyLexer(new ANTLRInputStream(
                new FileInputStream(filename)));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShelltyParser parser = new ShelltyParser(tokens);

        ParseTree tree = parser.compilationUnit();
        
        TranslatorVisitor translator = new TranslatorVisitor();
        translator.visit(tree);
        translator.getSemanticTree().debugPrint();

        /* ParseTreeWalker walker = new ParseTreeWalker(); */

        /* Translator translator = new Translator(); */

        /* walker.walk(translator, tree); */
        /* translator.getSemanticTree().debugPrint(); */
    }
}

