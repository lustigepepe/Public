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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.progress.UIJob;

import de.spiritlink.jobs.Activator;
import de.spiritlink.jobs.jobinterface.ui.ErrorHandler;

/**
 * 
 * @author Tom Seidel - tom.seidel@spiritlink.de
 */
public class ActionUIJobWrapper extends UIJob {

    private IActionJob actionJob = null;

    /**
     * @param name
     */
    public ActionUIJobWrapper(IActionJob job) {
        super(job.getJobName());
        this.actionJob = job;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public IStatus runInUIThread(IProgressMonitor monitor) {
        IStatus returnValue = Status.OK_STATUS;
        IStatus exceptionStatus = null;
        setPriority(Job.INTERACTIVE);       
        monitor.beginTask(this.actionJob.getJobDescription(), this.actionJob.getTotalTime());           
        try{                        
            this.actionJob.run(monitor);                      
        }
        catch(InvocationTargetException ex)
        {
            exceptionStatus = ErrorHandler.solveException(ex.getCause(),Activator.PLUGIN_ID);
            returnValue = new MultiStatus(Activator.PLUGIN_ID,
                    500,new IStatus[]{exceptionStatus},ex.getMessage(),null);
        }
        catch (InterruptedException e) {
            exceptionStatus = ErrorHandler.solveException(e,Activator.PLUGIN_ID);
            returnValue = new MultiStatus(Activator.PLUGIN_ID,
                    500,new IStatus[]{exceptionStatus},e.getMessage(),null);
        }
        finally{
            monitor.done();
        }
        if (monitor.isCanceled() )
            returnValue = Status.CANCEL_STATUS;
        return returnValue;
    }

}
