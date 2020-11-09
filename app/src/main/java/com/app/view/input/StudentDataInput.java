package com.app.view.input;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class StudentDataInput extends JPanel {

    public StudentDataInput() {
        super(new BorderLayout());
        this.setBorder(new TitledBorder(
                new EmptyBorder(5,5,5,5),
                "Ingreso de datos", TitledBorder.CENTER, TitledBorder.TOP));
    }

    public void addInputs(DataInputs inputs) {
        if (inputs != null) {
            this.add(inputs, BorderLayout.CENTER);
        }
    }

    public void addInputControls(DataInputControls controls) {
        if (controls != null) {
            this.add(controls, BorderLayout.EAST);
        }
    }

}
