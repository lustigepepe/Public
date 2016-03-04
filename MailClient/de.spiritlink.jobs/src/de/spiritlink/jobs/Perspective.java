package de.spiritlink.jobs;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import de.spiritlink.jobs.example.View;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		
		layout.addStandaloneView(View.ID,  false, IPageLayout.TOP, 0.5f, editorArea);
		layout.addStandaloneView("org.eclipse.ui.views.ProgressView",  false, IPageLayout.TOP, 0.5f, editorArea);
	}

}
