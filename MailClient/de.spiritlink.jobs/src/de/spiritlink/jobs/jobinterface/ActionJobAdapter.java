/*******************************************************************************
 * Copyright (c) 2006 Spirit Link GmbH
 * All rights reserved.
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package de.spiritlink.jobs.jobinterface;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * 
 * @author Tom Seidel - tom.seidel@spiritlink.de
 */
public abstract class ActionJobAdapter implements IActionJob {

    /* (non-Javadoc)
     * @see de.spiritlink.ecore.jobinterface.jobs.IEcoreActionJob#getTotalTime()
     */
    public int getTotalTime() {
        return IProgressMonitor.UNKNOWN;
    }

    /* (non-Javadoc)
     * @see de.spiritlink.ecore.jobinterface.jobs.IEcoreActionJob#getJobName()
     */
    public String getJobName() {
        return ""; //$NON-NLS-1$
    }
    /* (non-Javadoc)
     * @see de.spiritlink.ecore.jobinterface.jobs.IEcoreActionJob#getJobDescription()
     */
    public String getJobDescription() {
        return getJobName();
    }

    /* (non-Javadoc)
     * @see de.spiritlink.ecore.jobinterface.jobs.IEcoreActionJob#getRule()
     */
    public ISchedulingRule getRule() {
         return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        // implemented by client.
    }
}
