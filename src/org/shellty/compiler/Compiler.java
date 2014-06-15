package org.shellty.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import org.shellty.antlr.*;
import org.shellty.compiler.error.ShelltyException;
import org.shellty.utils.Logger;
//
// antlr imports 
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

public class Compiler {
    private String outputFile;
    private List<String> inputFiles;
    private String entryPoint;

    public Compiler(String outputFile, List<String> inputFiles, String entryPoint) {
        this.outputFile = outputFile;
        this.inputFiles = inputFiles;
    }

    public void launch() throws FileNotFoundException {
        File outFile = new File(outputFile);
        outFile.delete();

        try (PrintStream outStream = new PrintStream(outFile)) {
            for (String filename : inputFiles) {
                Logger.getInstance().log("Process file: " + filename);
                try {
                    String result = processFile(filename);
                    if (result == null) {
                        return;
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            outStream.println(translator.getCodeGenerator().getResult());
            if (entryPoint != null && entryPoint != "") {
                outStream.println(entryPoint + " $*");
            }
        }
    }

    Translator translator = new Translator();

    public String processFile(String filename) throws IOException {
        ShelltyLexer lexer = new ShelltyLexer(new ANTLRInputStream(
                new FileInputStream(filename)));

        translator.getCodeGenerator().insertLine("#" + filename);
        translator.getCodeGenerator().insertLine();

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShelltyParser parser = new ShelltyParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());

        try {
            ParseTree tree = parser.compilationUnit();
            translator.visit(tree);
        } catch (ShelltyException ex) {
            System.err.print(filename + ":\t");
            System.err.println("error: " + ex.generateMessage());
            return null;
        } catch (ParseCancellationException ex) {
            RecognitionException re = (RecognitionException)ex.getCause();
            System.err.print(filename + ":\t");
            System.err.print("error: line "+re.getOffendingToken().getLine() + 
                    ":" + re.getOffendingToken().getCharPositionInLine());
            System.err.print(" Parser error. got input: "+re.getOffendingToken().getText());
            System.err.println(", expected input: "+re.getExpectedTokens().toString(parser.getTokenNames()));
            return null;
        }

        /* translator.getSemanticTree().debugPrint(); */

        return translator.getCodeGenerator().getResult();
    }
}

