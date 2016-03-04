/*******************************************************************************
 * Copyright (c) 2006 Spirit Link GmbH
 * All rights reserved.
 * 
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package de.spiritlink.jobs.example;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class Job1Rule implements ISchedulingRule {

    public boolean contains(ISchedulingRule rule) {
        return rule.getClass() == Job1Rule.class;
    }

    public boolean isConflicting(ISchedulingRule rule) {
        return rule.getClass() == Job1Rule.class;
    }

}
