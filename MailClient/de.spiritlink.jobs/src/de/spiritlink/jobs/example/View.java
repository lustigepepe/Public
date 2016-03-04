package de.spiritlink.jobs.example;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import de.spiritlink.jobs.jobinterface.ActionJobRunner;
import de.spiritlink.jobs.jobinterface.IActionJob;

public class View extends ViewPart {
	public static final String ID = "de.spiritlink.jobs.example";

	private Button runInUiButton = null;
    
    private Button runWithExclusiveRule = null;
    
    private Button failJob = null;
    
    private Button withImage = null;
    
    private Button delay = null;
    
    private Button showDialog = null;
    
    private Button start = null;

    private Button job1Radio;

    private Button job2Radio;

    private Button job3Radio;
    
	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		Composite comp = parent;
        comp.setLayoutData(new GridData(SWT.FILL, SWT.FILL,true,true));
        comp.setLayout(new GridLayout(2,false));
        
        GridData gd = new GridData(SWT.FILL,SWT.BEGINNING,true,false);
        gd.horizontalSpan = 2;
        
        Composite jobComp = new Composite(comp, SWT.NONE);
        jobComp.setLayout(new FillLayout(SWT.HORIZONTAL));
        jobComp.setLayoutData(gd);
        
        this.job1Radio = new Button(jobComp, SWT.RADIO | SWT.FLAT);
        this.job1Radio.setText("Job1");
        this.job1Radio.setSelection(true);
        this.job2Radio = new Button(jobComp, SWT.RADIO | SWT.FLAT);
        this.job2Radio.setText("Job2");
        this.job3Radio = new Button(jobComp, SWT.RADIO | SWT.FLAT);
        this.job3Radio.setText("Both");
        
        Label separator = new Label(comp, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(gd);
        
        gd = new GridData(SWT.BEGINNING,SWT.BEGINNING,false,false);
        gd.widthHint = 150;
        
        
        this.runInUiButton = new Button(comp,SWT.CHECK | SWT.FLAT);
        this.runInUiButton.setText("Run in UI");
        this.runInUiButton.setLayoutData(gd);
        
        
        this.runWithExclusiveRule = new Button(comp,SWT.CHECK | SWT.FLAT);
        this.runWithExclusiveRule.setText("Run with exclusive Rule");
        this.runWithExclusiveRule.setLayoutData(gd);
        
        this.failJob = new Button(comp,SWT.CHECK | SWT.FLAT);
        this.failJob.setText("Fail Job");
        this.failJob.setLayoutData(gd);
        
        this.withImage = new Button(comp,SWT.CHECK | SWT.FLAT);
        this.withImage.setText("Set Job-Image");
        this.withImage.setLayoutData(gd);
        
        this.delay = new Button(comp,SWT.CHECK | SWT.FLAT);
        this.delay.setText("Delay start");
        this.delay.setLayoutData(gd);
        
        this.showDialog = new Button(comp,SWT.CHECK | SWT.FLAT);
        this.showDialog.setText("Show Dialog");
        this.showDialog.setLayoutData(gd);
        
        this.start = new Button(comp,SWT.PUSH | SWT.FLAT);
        this.start.setText("Start Job");
        gd = new GridData(SWT.FILL,SWT.BOTTOM,true,true);
        gd.horizontalSpan = 2;
        this.start.setLayoutData(gd);
        
        this.start.addListener(SWT.Selection, new Listener() {
            

            public void handleEvent(Event event) {
                ImageDescriptor icon = null;
                if (View.this.withImage.getSelection()) {
                    icon = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT);
                }
                IActionJob[] jobs = buildJob();
                for (int i = 0; i < jobs.length; i++) {
                    ActionJobRunner.getInstance().runSingleAction(
                            jobs[i], View.this.showDialog.getSelection(), View.this.runInUiButton.getSelection(), icon);
                }
                
            }
            
        });
        
	}
    
    protected IActionJob[] buildJob() {
        AbstractJob[] returnValue = null;
        Job1 job1 = new Job1();
        job1.setFail(this.failJob.getSelection());
        job1.setDelay(this.delay.getSelection() ? 20000 : 0);
        job1.setRule(this.runWithExclusiveRule.getSelection() ? new Job1Rule() : null);
        Job2 job2 = new Job2();
        job2.setFail(this.failJob.getSelection());
        job2.setDelay(this.delay.getSelection() ? 20000 : 0);
        job2.setRule(this.runWithExclusiveRule.getSelection() ? new Job2Rule() : null);
        if (this.job1Radio.getSelection()) {
            returnValue = new AbstractJob[1];
            returnValue[0] = job1;
        } else if (this.job2Radio.getSelection()) {
            returnValue = new AbstractJob[1];
            returnValue[0] = job2;
        } else if (this.job3Radio.getSelection()) {
            returnValue = new AbstractJob[2];
            returnValue[0] = job1;
            returnValue[1] = job2;
        }
        return returnValue;
    }

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		this.job1Radio.setFocus();
	}
}