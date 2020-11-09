package com.app.view;

import com.app.view.input.StudentDataInput;
import com.app.view.output.StudentDataTable;

import javax.swing.*;
import static javax.swing.JOptionPane.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {

    private StudentDataInput studentDataInput;
    private StudentDataTable studentDataTable;

    private final WindowAdapter defaultWindowAdapter = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int option = showConfirmDialog(MainWindow.this,"Esta seguro que desea salir?","Confirm",YES_NO_OPTION,WARNING_MESSAGE);
            if (option == YES_OPTION) {
                MainWindow.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            } else {
                MainWindow.this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }
        }
    };

    public MainWindow(String title) {
        super(title);
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(800,500);
        this.setLocationRelativeTo(null);
        this.addWindowListener(this.defaultWindowAdapter);
    }

    public void addDataInput(StudentDataInput input) {
        if (input != null) {
            this.studentDataInput = input;
            this.getContentPane().add(input, BorderLayout.NORTH);
        }
    }

    public void addDataOutput(StudentDataTable output) {
        if (output != null) {
            this.studentDataTable = output;
            this.getContentPane().add(output, BorderLayout.CENTER);
        }
    }

    public StudentDataInput getStudentDataInput() {
        return this.studentDataInput;
    }

    public StudentDataTable getStudentDataTable() {
        return this.studentDataTable;
    }

}
