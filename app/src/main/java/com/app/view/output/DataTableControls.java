package com.app.view.output;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DataTableControls extends JPanel {

    private final JButton selectButton;
    private final JButton refreshButton;

    public DataTableControls() {
        super(new FlowLayout());

        this.selectButton = new JButton("Seleccionar", new ImageIcon(Toolkit.getDefaultToolkit()
                .getImage(DataTableControls.class.getResource("/icon/select.png"))));
        this.selectButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(this.selectButton);

        this.refreshButton = new JButton("Recargar", new ImageIcon(Toolkit.getDefaultToolkit()
                .getImage(DataTableControls.class.getResource("/icon/refresh.png"))));
        this.refreshButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(this.refreshButton);
    }

    public void addSelectButtonAction(ActionListener action) {
        if (action != null) {
            this.selectButton.addActionListener(action);
        }
    }

    public void addRefreshButtonAction(ActionListener action) {
        if (action != null) {
            this.refreshButton.addActionListener(action);
        }
    }

}
