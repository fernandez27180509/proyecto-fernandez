package com.app.view.input;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class DataInputControls extends JPanel {

    private final JButton agreeButton;
    private final JButton updateButton;
    private final JButton deleteButton;

    public DataInputControls() {
        super();
        this.setBorder(new EmptyBorder(5,5,5,5));
        GridBagLayout controlLayout = new GridBagLayout();
        controlLayout.columnWidths = new int[]{0, 0};
        controlLayout.rowHeights = new int[]{0, 0, 0, 0};
        controlLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        controlLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        this.setLayout(controlLayout);


        this.agreeButton = new JButton("Agregar");
        this.agreeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        GridBagConstraints addConstraints = new GridBagConstraints();
        addConstraints.fill = GridBagConstraints.HORIZONTAL;
        addConstraints.insets = new Insets(0, 0, 5, 0);
        addConstraints.gridx = 0;
        addConstraints.gridy = 0;
        this.add(this.agreeButton, addConstraints);

        this.updateButton = new JButton("Actualizar");
        this.updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        GridBagConstraints updateConstraints = new GridBagConstraints();
        updateConstraints.fill = GridBagConstraints.HORIZONTAL;
        updateConstraints.insets = new Insets(0, 0, 5, 0);
        updateConstraints.gridx = 0;
        updateConstraints.gridy = 1;
        this.add(this.updateButton, updateConstraints);

        this.deleteButton = new JButton("Eliminar");
        this.deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        GridBagConstraints deleteConstraints = new GridBagConstraints();
        deleteConstraints.fill = GridBagConstraints.HORIZONTAL;
        deleteConstraints.gridx = 0;
        deleteConstraints.gridy = 2;
        this.add(this.deleteButton, deleteConstraints);
    }

    public void addAgreeButtonAction(ActionListener action) {
        if (action != null) {
            this.agreeButton.addActionListener(action);
        }
    }

    public void addUpdateButtonAction(ActionListener action) {
        if (action != null) {
            this.updateButton.addActionListener(action);
        }
    }

    public void addDeleteButtonAction(ActionListener action) {
        if (action != null) {
            this.deleteButton.addActionListener(action);
        }
    }

}
