package org.shellty.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
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
    private String entryPoint;

    public Compiler(String outputFile, List<String> inputFiles, String entryPoint) {
        this.outputFile = outputFile;
        this.inputFiles = inputFiles;
    }

    public void launch() throws FileNotFoundException {
        try (PrintStream outStream = new PrintStream(new File(outputFile))) {
            for (String filename : inputFiles) {
                Logger.getInstance().log("Process file: " + filename);
                try {
                    String result = processFile(filename);
                    outStream.println(result);
                } catch (IOException ex) {
                    // TODO: resolve later
                }
            }
            if (entryPoint != null && entryPoint != "") {
                outStream.println(entryPoint + " $*");
            }
        }
    }

    public String processFile(String filename) throws IOException {
        ShelltyLexer lexer = new ShelltyLexer(new ANTLRInputStream(
                new FileInputStream(filename)));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShelltyParser parser = new ShelltyParser(tokens);

        ParseTree tree = parser.compilationUnit();
        
        Translator translator = new Translator();
        translator.visit(tree);
        translator.getSemanticTree().debugPrint();

        /* System.out.println(translator.getCodeGenerator().getResult()); */
        return translator.getCodeGenerator().getResult();
        /* ParseTreeWalker walker = new ParseTreeWalker(); */

        /* Translator translator = new Translator(); */

        /* walker.walk(translator, tree); */
        /* translator.getSemanticTree().debugPrint(); */
    }
}

