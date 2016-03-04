/*******************************************************************************
 * Copyright (c) 2006 Spirit Link GmbH
 * All rights reserved.
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package de.spiritlink.jobs.example;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;

public class Job1 extends AbstractJob{

    
    public String getJobDescription() {
        return getJobName();
    }

    public String getJobName() {
        return "Job1";
    }

    
    public int getTotalTime() {
        return 5;
    }

    public void run(IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException {
        monitor.subTask("Init");
        Util.sleep(2000);
        monitor.subTask("Processing");
        if (monitor.isCanceled()) {
            return;
        }
        monitor.worked(1);
        Util.sleep(1000);
        monitor.worked(1);
        if (monitor.isCanceled()) {
            return;
        }
        Util.sleep(500);
        monitor.subTask("Validating");
        if (monitor.isCanceled()) {
            return;
        }
        Util.sleep(500);
        monitor.worked(1);
        monitor.subTask("Validating");
        if (monitor.isCanceled()) {
            return;
        }
        Util.sleep(1000);
        monitor.worked(1);
        monitor.subTask("Finishing");
        if (monitor.isCanceled()) {
            return;
        }
        Util.sleep(500);
        monitor.worked(1);
        
        if (this.fail) {
            throw new InvocationTargetException(new IllegalArgumentException("The real cause."),"Error executing job"); //$NON-NLS-1$
        }

    }

    

}
