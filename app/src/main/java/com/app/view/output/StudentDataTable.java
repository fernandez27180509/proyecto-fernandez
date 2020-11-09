package com.app.view.output;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentDataTable extends JPanel {

    private final DefaultTableModel tableModel;

    public StudentDataTable() {
        super(new BorderLayout());
        this.tableModel = new DefaultTableModel(null, new String[] {"Id", "Código", "Apellidos y nombres", "Correo", "Escuela"});
        this.add(new JScrollPane(new JTable(this.tableModel)), BorderLayout.CENTER);
    }

    public void addControls(DataTableControls controls) {
        if (controls != null) {
            this.add(controls, BorderLayout.NORTH);
        }
    }

    public DefaultTableModel getTableModel() {
        return this.tableModel;
    }

}
