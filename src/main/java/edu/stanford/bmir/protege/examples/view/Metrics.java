package edu.stanford.bmir.protege.examples.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.model.event.EventType;
import org.protege.editor.owl.model.event.OWLModelManagerListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSpinner;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Metrics extends JPanel {

    private OWLModelManager modelManager;


    private ActionListener refreshAction = e -> recalculate();
    
    private OWLModelManagerListener modelListener = event -> {
        if (event.getType() == EventType.ACTIVE_ONTOLOGY_CHANGED) {
            recalculate();
        }
    };
    private final JLabel lblNewLabel = new JLabel("Metrics");
    private final JLabel lblNewLabel_1 = new JLabel("Axiom");
    private final JLabel lblNewLabel_3 = new JLabel("Declaration axioms count");
    private final JLabel lblNewLabel_4 = new JLabel("Class axioms");
    private final JLabel lblNewLabel_5 = new JLabel("SubclassOf");
    private final JLabel lblNewLabel_6 = new JLabel("EquivalentClasses");
    private final JLabel lblNewLabel_7 = new JLabel("0");
    private final JLabel lblNewLabel_8 = new JLabel("0");
    private final JLabel lblNewLabel_9 = new JLabel("0");
    private final JLabel lblNewLabel_2 = new JLabel("Logical axiom count");
    private final JPanel panel = new JPanel();
    private final JSeparator separator = new JSeparator();
    private final JSeparator separator_1 = new JSeparator();
    
    public Metrics(OWLModelManager modelManager) {
    	setBackground(Color.WHITE);
    	setForeground(Color.GRAY);
    	this.modelManager = modelManager;
    	GridBagLayout gridBagLayout = new GridBagLayout();
    	gridBagLayout.columnWidths = new int[]{11, 0, 0, 0, 35, 0};
    	gridBagLayout.rowHeights = new int[]{0, 14, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
    	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
    	setLayout(gridBagLayout);
    	
    	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    	gbc_lblNewLabel.gridwidth = 2;
    	gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
    	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
    	gbc_lblNewLabel.gridx = 1;
    	gbc_lblNewLabel.gridy = 1;
    	lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
    	add(lblNewLabel, gbc_lblNewLabel);
    	
    	GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
    	gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
    	gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
    	gbc_lblNewLabel_1.gridx = 2;
    	gbc_lblNewLabel_1.gridy = 2;
    	add(lblNewLabel_1, gbc_lblNewLabel_1);
    	
    	GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
    	gbc_lblNewLabel_7.anchor = GridBagConstraints.BELOW_BASELINE;
    	gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 0);
    	gbc_lblNewLabel_7.gridx = 4;
    	gbc_lblNewLabel_7.gridy = 2;
    	add(lblNewLabel_7, gbc_lblNewLabel_7);
    	
    	GridBagConstraints gbc_panel = new GridBagConstraints();
    	gbc_panel.anchor = GridBagConstraints.NORTH;
    	gbc_panel.gridwidth = 3;
    	gbc_panel.insets = new Insets(0, 0, 5, 0);
    	gbc_panel.fill = GridBagConstraints.HORIZONTAL;
    	gbc_panel.gridx = 2;
    	gbc_panel.gridy = 3;
    	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    	flowLayout.setHgap(1);
    	flowLayout.setAlignment(FlowLayout.LEADING);
    	flowLayout.setVgap(1);
    	add(panel, gbc_panel);
    	
    	GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
    	gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
    	gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
    	gbc_lblNewLabel_2.gridx = 2;
    	gbc_lblNewLabel_2.gridy = 4;
    	add(lblNewLabel_2, gbc_lblNewLabel_2);
    	
    	GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
    	gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 0);
    	gbc_lblNewLabel_9.gridx = 4;
    	gbc_lblNewLabel_9.gridy = 4;
    	add(lblNewLabel_9, gbc_lblNewLabel_9);
    	
    	GridBagConstraints gbc_separator_1 = new GridBagConstraints();
    	gbc_separator_1.insets = new Insets(0, 0, 5, 5);
    	gbc_separator_1.gridx = 2;
    	gbc_separator_1.gridy = 5;
    	separator_1.setBackground(Color.BLACK);
    	add(separator_1, gbc_separator_1);
    	
    	GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
    	gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
    	gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
    	gbc_lblNewLabel_3.gridx = 2;
    	gbc_lblNewLabel_3.gridy = 6;
    	add(lblNewLabel_3, gbc_lblNewLabel_3);
    	
    	GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
    	gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 0);
    	gbc_lblNewLabel_8.gridx = 4;
    	gbc_lblNewLabel_8.gridy = 6;
    	add(lblNewLabel_8, gbc_lblNewLabel_8);
    	
    	GridBagConstraints gbc_separator = new GridBagConstraints();
    	gbc_separator.insets = new Insets(0, 0, 5, 5);
    	gbc_separator.gridx = 2;
    	gbc_separator.gridy = 7;
    	add(separator, gbc_separator);
    	
    	GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
    	gbc_lblNewLabel_4.gridwidth = 2;
    	gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
    	gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
    	gbc_lblNewLabel_4.gridx = 1;
    	gbc_lblNewLabel_4.gridy = 8;
    	lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 16));
    	add(lblNewLabel_4, gbc_lblNewLabel_4);
    	
    	GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
    	gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
    	gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
    	gbc_lblNewLabel_5.gridx = 2;
    	gbc_lblNewLabel_5.gridy = 9;
    	add(lblNewLabel_5, gbc_lblNewLabel_5);
    	
    	GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
    	gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
    	gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
    	gbc_lblNewLabel_6.gridx = 2;
    	gbc_lblNewLabel_6.gridy = 10;
    	add(lblNewLabel_6, gbc_lblNewLabel_6);
        recalculate();
        
        modelManager.addListener(modelListener);
    }
    
    public void dispose() {
        modelManager.removeListener(modelListener);
        //refreshButton.removeActionListener(refreshAction);
    }
    
    private void recalculate() {
        int count = modelManager.getActiveOntology().getClassesInSignature().size();
        if (count == 0) {
            count = 1;  // owl:Thing is always there.
        }
        lblNewLabel_7.setText("4");
    }
}

