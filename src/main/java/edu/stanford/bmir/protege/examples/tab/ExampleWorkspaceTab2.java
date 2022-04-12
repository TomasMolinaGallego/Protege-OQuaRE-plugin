package edu.stanford.bmir.protege.examples.tab;

import javax.swing.JLabel;

import org.protege.editor.owl.ui.OWLWorkspaceViewsTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleWorkspaceTab2 extends OWLWorkspaceViewsTab {

	private static final Logger log = LoggerFactory.getLogger(ExampleWorkspaceTab2.class);
	
    private JLabel textComponent = new JLabel();


	public ExampleWorkspaceTab2() {
		setToolTipText("Custom tooltip text for Example Tab (2)");
		textComponent.setText("Hola mundo");
		add(textComponent);
	}

    @Override
	public void initialise() {
		super.initialise();
		log.info("Example Workspace Tab (2) initialized");
	}

	@Override
	public void dispose() {
		super.dispose();
		log.info("Example Workspace Tab (2) disposed");
	}
}
