/*******************************************************************************
 * Copyright (c) 2006 Spirit Link GmbH
 * All rights reserved.
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package de.spiritlink.jobs.example;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

import de.spiritlink.jobs.jobinterface.IActionJob;

public abstract class AbstractJob implements IActionJob {

    protected boolean fail = false;
    
    protected ISchedulingRule rule = null;
    
    protected int delay = 0;

    public void setFail(boolean fail) {
        this.fail = fail;
    }

    public void setRule(ISchedulingRule rule) {
        this.rule = rule;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    public int getDelay() {
        return this.delay;
    }
    
    public ISchedulingRule getRule() {
        return this.rule;
    }


}
