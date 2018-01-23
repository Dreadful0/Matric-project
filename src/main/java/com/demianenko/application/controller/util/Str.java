package com.demianenko.application.controller.util;

/**
 * This class are used for checking input parameters from jsp pages
 */
public class Str {

    /**
     * Simple class method name to improve code visibility
     *
     * @param strings parameters to check
     * @return true if at least one input parameter are null void or whitespace
     */
    public static boolean isEmpty(String... strings){
        for (String str: strings) {
            if(str==null || str.trim().isEmpty())return true;
        }
        return false;
    }
}
