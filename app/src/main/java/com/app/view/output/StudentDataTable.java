package com.app.view.output;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentDataTable extends JPanel {

    private final DefaultTableModel tableModel;
    private final JTable tableView;

    public StudentDataTable() {
        super(new BorderLayout());
        this.tableModel = new DefaultTableModel(null, new String[] {"Id", "C\u00F3digo", "Apellidos y nombres", "Correo", "Escuela"});
        this.tableView = new JTable(this.tableModel);
        this.add(new JScrollPane(this.tableView), BorderLayout.CENTER);
    }

    public void addControls(DataTableControls controls) {
        if (controls != null) {
            this.add(controls, BorderLayout.NORTH);
        }
    }

    public DefaultTableModel getTableModel() {
        return this.tableModel;
    }

    public JTable getTableView() {
        return this.tableView;
    }

}
