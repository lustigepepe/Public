/*******************************************************************************
 * Copyright (c) 2006 Spirit Link GmbH
 * All rights reserved.
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package de.spiritlink.jobs.example;

public class Util {
    
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
