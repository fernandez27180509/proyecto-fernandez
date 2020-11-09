package com.app.view.input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class DataInputs extends JPanel {

    private JTextField codeInput;
    private JTextField allNameInput;
    private JTextField emailInput;
    private JComboBox<String> schoolSelector;

    public DataInputs() {
        super();
        GridBagLayout inputsPanelLayout = new GridBagLayout();
        inputsPanelLayout.columnWidths = new int[]{0, 0, 0};
        inputsPanelLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
        inputsPanelLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        inputsPanelLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        this.setLayout(inputsPanelLayout);

        this.initComponents();
    }

    private void initComponents() {
        {
            JLabel codeInputLabel = new JLabel("Código");
            GridBagConstraints codeLabelConstraints = new GridBagConstraints();
            codeLabelConstraints.insets = new Insets(0, 0, 5, 5);
            codeLabelConstraints.anchor = GridBagConstraints.WEST;
            codeLabelConstraints.gridx = 0;
            codeLabelConstraints.gridy = 0;
            this.add(codeInputLabel, codeLabelConstraints);

            this.codeInput = new JTextField();
            this.codeInput.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char character = e.getKeyChar();
                    if ( ((character < '0') || (character > '9')) && (character != '\b')) {
                        e.consume();
                        getToolkit().beep();
                    }
                }
            });
            this.codeInput.setToolTipText("Ingrese aqui el codigo de estudiante");
            GridBagConstraints codeInputConstraints = new GridBagConstraints();
            codeInputConstraints.insets = new Insets(0, 0, 5, 0);
            codeInputConstraints.gridx = 1;
            codeInputConstraints.gridy = 0;
            codeInputConstraints.fill = GridBagConstraints.HORIZONTAL;
            this.add(this.codeInput, codeInputConstraints);
        }
        {
            JLabel nameInputLabel = new JLabel("Apellidos y nombres");
            GridBagConstraints nameLabelConstraints = new GridBagConstraints();
            nameLabelConstraints.anchor = GridBagConstraints.WEST;
            nameLabelConstraints.insets = new Insets(0, 0, 5, 5);
            nameLabelConstraints.gridx = 0;
            nameLabelConstraints.gridy = 1;
            this.add(nameInputLabel, nameLabelConstraints);

            this.allNameInput = new JTextField();
            this.allNameInput.setToolTipText("Ingrese aqui los nombres y apellidos");
            GridBagConstraints nameInputConstraints = new GridBagConstraints();
            nameInputConstraints.insets = new Insets(0, 0, 5, 0);
            nameInputConstraints.gridx = 1;
            nameInputConstraints.gridy = 1;
            nameInputConstraints.fill = GridBagConstraints.HORIZONTAL;
            this.add(this.allNameInput, nameInputConstraints);
        }
        {
            JLabel emailInputLabel = new JLabel("Correo");
            GridBagConstraints emailLabelConstraints = new GridBagConstraints();
            emailLabelConstraints.anchor = GridBagConstraints.WEST;
            emailLabelConstraints.insets = new Insets(0, 0, 5, 5);
            emailLabelConstraints.gridx = 0;
            emailLabelConstraints.gridy = 2;
            this.add(emailInputLabel, emailLabelConstraints);

            this.emailInput = new JTextField();
            this.emailInput.setToolTipText("Ingrese qui el correo electronico");
            GridBagConstraints emailInputConstraints = new GridBagConstraints();
            emailInputConstraints.insets = new Insets(0, 0, 5, 0);
            emailInputConstraints.gridx = 1;
            emailInputConstraints.gridy = 2;
            emailInputConstraints.fill = GridBagConstraints.HORIZONTAL;
            this.add(this.emailInput, emailInputConstraints);
        }
        {
            JLabel schoolInputLabel = new JLabel("Escuela");
            GridBagConstraints schoolLabelConstraints = new GridBagConstraints();
            schoolLabelConstraints.anchor = GridBagConstraints.WEST;
            schoolLabelConstraints.insets = new Insets(0, 0, 0, 5);
            schoolLabelConstraints.gridx = 0;
            schoolLabelConstraints.gridy = 3;
            this.add(schoolInputLabel, schoolLabelConstraints);

            this.schoolSelector = new JComboBox<>();
            this.schoolSelector.setToolTipText("Seleccione la escuela");
            GridBagConstraints schoolInputConstraints = new GridBagConstraints();
            schoolInputConstraints.fill = GridBagConstraints.HORIZONTAL;
            schoolInputConstraints.gridx = 1;
            schoolInputConstraints.gridy = 3;
            this.add(this.schoolSelector, schoolInputConstraints);
        }
    }

    public JComboBox<String> getSchoolSelector() {
        return this.schoolSelector;
    }

    public void clearFields() {
        this.codeInput.setText("");
        this.allNameInput.setText("");
        this.emailInput.setText("");
        this.schoolSelector.setSelectedIndex(0);
    }

    public String getCode() {
        return this.codeInput.getText();
    }

    public String getAllName() {
        return this.allNameInput.getText();
    }

    public String getEmail() {
        return this.emailInput.getText();
    }

    public String getSchool() {
        return Objects.requireNonNull(this.schoolSelector.getSelectedItem()).toString();
    }

}
