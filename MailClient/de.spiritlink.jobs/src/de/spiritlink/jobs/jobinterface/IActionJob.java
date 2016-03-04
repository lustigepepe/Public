/*******************************************************************************
 * Copyright (c) 2006 Spirit Link GmbH
 * All rights reserved.
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package de.spiritlink.jobs.jobinterface;

import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * 
 * @author Tom Seidel - tom.seidel@spiritlink.de
 */
public interface IActionJob extends IRunnableWithProgress {
    
    
    /**
     * total work (ticks) to be done   
     * @return
     */
    public int getTotalTime();
    
    /**
     * the name of the job shown in the progress-bar
     * @return
     */
    public String getJobName();

    /**
     * a short description of the job
     * @return
     */
    public String getJobDescription();
    
    /**
     * Returns the scheduling rule.
     * @return the scheduling rule.
     */
    public ISchedulingRule getRule();
    
    /**
     * Returns the delay
     * @return the delay.
     */
    public int getDelay();
 
}
