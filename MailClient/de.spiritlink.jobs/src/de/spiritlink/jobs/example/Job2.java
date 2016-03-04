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

public class Job2 extends AbstractJob {

    public String getJobDescription() {
        return getJobName();
    }

    public String getJobName() {
        return "Job2";
    }

    public int getTotalTime() {
        return IProgressMonitor.UNKNOWN;
    }

    public void run(IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException {
        monitor.subTask("Doing something");
        Util.sleep(1500);
        monitor.subTask("Do some other things");
        if (monitor.isCanceled()) {
            return;
        }
        Util.sleep(970);
        monitor.worked(1);
        if (monitor.isCanceled()) {
            return;
        }
        Util.sleep(700);
        monitor.subTask("Doing some really important");
        if (monitor.isCanceled()) {
            return;
        }
        Util.sleep(860);
        if (this.fail) {
            throw new InvocationTargetException(new IllegalArgumentException("The real cause."),"Error executing job"); //$NON-NLS-1$
        }


    }

}
