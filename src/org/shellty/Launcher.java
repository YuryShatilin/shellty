package org.shellty;

import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.BooleanOptionHandler;

import org.shellty.utils.Logger;
import org.shellty.compiler.*;
import org.shellty.compiler.Compiler;

import java.util.regex.*;

public class Launcher {
    public static void main(String args[]) {
        try {
            new Launcher().launch(args);
        } catch (Exception ex) {
            // TODO: remove this
            ex.printStackTrace();
        }
    }

    // const 
    private final static String EXT = ".sh";
    private final static String DEFAULT_OUTFILENAME = "a" + EXT;

    @Option(name="-o", usage="output file, -o FILE")
    private String outFileName = "";

    @Option(name="-ep", usage="Entry point, -ep FUNCNAME")
    private String entryPoint = "";

    @Option(name="-h", handler=BooleanOptionHandler.class, usage="show help message")
    private boolean help;

    @Option(name="-debug", handler=BooleanOptionHandler.class, usage="debug flag")
    private boolean debug = false;

    @Argument
    private List<String> arguments = new ArrayList<>();

    private void printMessage(CmdLineParser parser, CmdLineException ex) {
        if (ex != null) {
            System.err.println(ex.getMessage());
        }
        printMessage(parser);
    }

    private void printMessage(CmdLineParser parser) {
        System.err.println("java Shellty.Launcher [options...] arguments...");
        parser.printUsage(System.err);
        System.err.println();
    }

    private void launch(String args[]) throws Exception {
        CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(100);
        try {
            parser.parseArgument(args);
            if (arguments.isEmpty()) {
                throw new CmdLineException(parser, " no input files");
            }
        } catch (CmdLineException ex) {
            printMessage(parser, ex);
            return;
        }

        if (help) {
            printMessage(parser);
            return;
        }

        if (outFileName.isEmpty()) {
            outFileName = DEFAULT_OUTFILENAME;
        } else {
            Pattern p = Pattern.compile(".*\\.(.*)$");
            Matcher m = p.matcher(outFileName);
            if (!m.matches()) {
                outFileName += EXT;
            }
        }

        Logger.getInstance().setLocked(!debug);
        Logger.getInstance().setOutStream(new PrintStream("out.log"));

        /* System.out.println("out file " + outFileName); */

        /* for (String arg : arguments) { */
        /*     System.out.println(arg); */
        /* } */

        Compiler compiler = new Compiler(outFileName, arguments);

        compiler.launch();
    }
}

