package org.shellty.utils;

import java.io.PrintStream;

public class Logger {

    private static Logger mLogger = new Logger();

    public static Logger getInstance() {
        return mLogger;
    }

    public void setOutStream(PrintStream stream) {
        mStream = stream;
    }
    
    private Logger() {
    }

    private PrintStream mStream = System.out;
    private boolean mLocked = true;
    private int mCount = 0;

    public void log(Object obj) {
        if (!isLocked()) {
            StackTraceElement[] elm = Thread.currentThread().getStackTrace();
            String info = elm[2].getClassName()+"-"+elm[2].getMethodName();
            mStream.print("[" + mCount++ + "]    " + info + ":    " + obj);        
            mStream.println();
        }
    }

    public boolean isLocked() {
        return mLocked;
    }

    public void setLocked(boolean locked) {
        this.mLocked = locked;
    }

}

