package com.app.control;

import com.app.collection.linear.SimpleLinkedList;
import com.app.model.Student;
import com.app.model.StudentDAO;
import com.app.view.MainWindow;
import com.app.view.input.DataInputs;
import com.app.view.output.DataTableControls;
import com.app.view.output.StudentDataTable;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class DataOutputControl {

    private final MainWindow owner;
    private final StudentDAO studentDAO;

    private StudentDataTable studentDataTable;
    private DataTableControls dataTableControls;

    public DataOutputControl(MainWindow owner) {
        this.initializeOutputs();
        this.owner = owner;
        this.studentDAO = new StudentDAO();
        owner.addDataOutput(this.studentDataTable);
        this.fillTable();
        this.initializeControlsAction();
    }

    private void initializeOutputs() {
        this.dataTableControls = new DataTableControls();
        this.studentDataTable = new StudentDataTable();
        this.studentDataTable.addControls(this.dataTableControls);
    }

    public StudentDataTable getStudentDataTable() {
        return this.studentDataTable;
    }

    public DataTableControls getDataTableControls() {
        return this.dataTableControls;
    }

    private void initializeControlsAction() {
        this.getDataTableControls().addSelectButtonAction(evt -> this.selectButtonAction());
        this.getDataTableControls().addRefreshButtonAction(evt -> this.refreshButtonAction());
    }

    private void selectButtonAction() {
        int selectedRow = this.studentDataTable.getTableView().getSelectedRow();
        if (selectedRow != -1) {
            DataInputs inputs = WindowsManager.localInstance.getDataInputControl().getDataInputs();
            inputs.setCode(this.studentDataTable.getTableView().getValueAt(selectedRow, 1).toString());
            inputs.setAllName(this.studentDataTable.getTableView().getValueAt(selectedRow, 2).toString());
            inputs.setEmail(this.studentDataTable.getTableView().getValueAt(selectedRow, 3).toString());
            inputs.setSelectedSchool(this.studentDataTable.getTableView().getValueAt(selectedRow, 4).toString());
        } else {
            showMessageDialog(this.owner, "No has seleccionado :(", "Error", ERROR_MESSAGE);
        }
    }

    private void refreshButtonAction() {
        this.refresh();
        JOptionPane.showMessageDialog(this.owner, "La tabla fue actualizada :)", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void refresh() {
        this.clearTable();
        this.fillTable();
    }

    private void clearTable() {
        this.getStudentDataTable().getTableModel().getRowCount();
        for (int i = this.getStudentDataTable().getTableModel().getRowCount(); i > 0; i--) {
            this.getStudentDataTable().getTableModel().removeRow(i - 1);
        }
    }

    private void fillTable() {
        SimpleLinkedList<Student> students = this.studentDAO.getStudents();
        if (students != null) {
            new Thread(() -> students.foreach(student -> {
                String studentCode = String.valueOf(student.getStudentCode());
                if (studentCode.length() == 7) {
                    studentCode = "0" + studentCode;
                }
                this.getStudentDataTable().getTableModel().addRow(new Object[] {
                        student.getStudentId(),
                        studentCode,
                        student.getStudentAllName(),
                        student.getStudentEmail(),
                        student.getStudentSchool()
                });
            })).start();
        }
    }

}
