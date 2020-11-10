package com.app.control;

import com.app.collection.linear.SimpleLinkedList;
import com.app.model.SchoolDAO;
import com.app.model.Student;
import com.app.model.StudentDAO;
import com.app.view.MainWindow;
import com.app.view.input.DataInputControls;
import com.app.view.input.DataInputs;
import com.app.view.input.StudentDataInput;
import org.tinylog.Logger;

import static javax.swing.JOptionPane.*;

/**
 * @author jhon
 * @version 1.0
 **/
public class DataInputControl {

    /** Empty **/
    private final MainWindow owner;

    /** Empty **/
    private DataInputs dataInputs;

    /** Empty **/
    private DataInputControls dataInputControls;

    /** Empty **/
    private StudentDataInput studentDataInput;

    /** Empty **/
    private final SchoolDAO schoolDAO;

    /** Empty **/
    private final StudentDAO studentDAO;

    /** Empty **/
    public DataInputControl(MainWindow owner) {
        this.owner = owner;
        this.schoolDAO = new SchoolDAO();
        this.studentDAO = new StudentDAO();
        this.initializeInput();
        this.fillSchools();
        owner.addDataInput(this.getStudentDataInput());
        this.initializeControlsAction();
    }

    /** Empty **/
    private void initializeInput() {
        this.dataInputs = new DataInputs();
        this.dataInputControls = new DataInputControls();
        this.studentDataInput = new StudentDataInput();
        this.studentDataInput.addInputs(this.getDataInputs());
        this.studentDataInput.addInputControls(this.getControls());
    }

    /** Empty **/
    public DataInputControls getControls() {
        return this.dataInputControls;
    }

    /** Empty **/
    public DataInputs getDataInputs() {
        return this.dataInputs;
    }

    /** Empty **/
    public StudentDataInput getStudentDataInput() {
        return this.studentDataInput;
    }

    /** Empty **/
    private void initializeControlsAction() {
        this.getControls().addAgreeButtonAction(evt -> this.agreeButtonAction());
        this.getControls().addUpdateButtonAction(evt -> this.updateButtonAction());
        this.getControls().addDeleteButtonAction(evt -> this.deleteButtonAction());
    }

    public Student validateAndGetStudent() {
        String code = this.getDataInputs().getCode();
        if (code.length() == 8) {
            try {
                Long.parseLong(code);
                Logger.info("Codigo verificado :)");
            } catch (NumberFormatException nfe) {
                showMessageDialog(this.owner, "El codigo ingresado no es valido :(", "Error", ERROR_MESSAGE);
                return null;
            }
        } else if (code.length() == 0) {
            showMessageDialog(this.owner, "El campo de codigo no puede quedar vacio :(", "Error", ERROR_MESSAGE);
            return null;
        } else {
            showMessageDialog(this.owner,"Porfavor complete el codigo de estudiante :|","Warning",WARNING_MESSAGE);
            return null;
        }

        String allName = this.getDataInputs().getAllName();
        if (allName.isEmpty()) {
            showMessageDialog(this.owner,"El campo de nombre no puede quedar vacio :(", "Error", ERROR_MESSAGE);
            return null;
        } else {
            Logger.info("Nombre verificado :)");
        }

        String email = this.getDataInputs().getEmail();
        if (!email.isEmpty()) {
            if (email.endsWith("@unsch.edu.pe")) {
                Logger.info("Correo verificado :)");
            } else {
                showMessageDialog(this.owner, "El correo ingresado no es valido", "Error", ERROR_MESSAGE);
                return null;
            }
        } else {
            showMessageDialog(this.owner, "El campo de correo no puede quedar vacio :(", "Error", ERROR_MESSAGE);
            return null;
        }

        String school = this.getDataInputs().getSchool();
        if (school.equals("--- Seleccione ---")) {
            showMessageDialog(this.owner, "Debe de seleccionar una escuela :|", "Warning", WARNING_MESSAGE);
            return null;
        } else {
            Logger.info("Escuela verificada :)");
        }
        return new Student(allName,Long.parseLong(code),email,school);
    }

    /** Empty **/
    private void agreeButtonAction() {
        Student inputDates = this.validateAndGetStudent();
        if (inputDates != null){
            Student exist = this.studentDAO.get(inputDates.getStudentCode());
            if (exist == null) {
                if (this.studentDAO.add(inputDates)) {
                    this.dataInputs.clearFields();
                    showMessageDialog(this.owner, "Los datos fueron registrados correctamante :)", "Success", INFORMATION_MESSAGE);
                    WindowsManager.localInstance.getDataOutputControl().refresh();
                } else {
                    showMessageDialog(this.owner, "Ocurrio un error inesperado :(", "Error", ERROR_MESSAGE);
                }
            } else {
                showMessageDialog(this.owner, "El codigo ingresado ya esta registrado", "Error", ERROR_MESSAGE);
            }
        }
    }

    /** Empty **/
    private void updateButtonAction() {
        Student student = this.validateAndGetStudent();
        if (student != null) {
            Student exist = this.studentDAO.get(student.getStudentCode());
            if (exist != null) {
                if (this.studentDAO.update(student)) {
                    showMessageDialog(this.owner, "Datos actualizados :)", "Success", INFORMATION_MESSAGE);
                    this.dataInputs.clearFields();
                } else {
                    showMessageDialog(this.owner, "Ocurrio un error inesperado :(", "Error", ERROR_MESSAGE);
                }
            } else {
                showMessageDialog(this.owner, "No se puedde modificar datos de una persona que no esta registrada :|", "Warning", WARNING_MESSAGE);
            }
        }
    }

    /** Empty **/
    private void deleteButtonAction() {
        String code = this.getDataInputs().getCode();
        if (code.length() == 8) {
            try {
                Long.parseLong(code);
                Logger.info("Codigo verificado :)");
            } catch (NumberFormatException nfe) {
                showMessageDialog(this.owner, "El codigo ingresado no es valido :(", "Error", ERROR_MESSAGE);
                return;
            }
        } else if (code.length() == 0) {
            showMessageDialog(this.owner, "El campo de codigo no puede quedar vacio :(", "Error", ERROR_MESSAGE);
            return;
        } else {
            showMessageDialog(this.owner,"Porfavor complete el codigo de estudiante :|","Warning",WARNING_MESSAGE);
            return;
        }
        Student student = this.studentDAO.get(Long.parseLong(code));
        if (student != null) {
            int option = showConfirmDialog(this.owner, "Estas seguro que quieres eliminar a {" + student.getStudentAllName() + "}", "Warning", YES_NO_OPTION, WARNING_MESSAGE);
            if (option == YES_OPTION) {
                this.studentDAO.delete(student.getStudentCode());
                showMessageDialog(this.owner, "Operacion completada :)", "Success", INFORMATION_MESSAGE);
                WindowsManager.localInstance.getDataOutputControl().refresh();
                this.dataInputs.clearFields();
            }
        } else {
            showMessageDialog(this.owner, "No se encontro ningun alumno con el codigo ingresado :|", "Warning", WARNING_MESSAGE);
        }
    }

    /** Empty **/
    public void fillSchools() {
        SimpleLinkedList<String> schools = this.schoolDAO.getSchools();
        if (schools != null) {
            this.getDataInputs().getSchoolSelector().addItem("--- Seleccione ---");
            schools.foreach(item -> this.getDataInputs().getSchoolSelector().addItem(item));
        } else {
            this.getDataInputs().getSchoolSelector().addItem("Red no disponible");
        }
        this.getDataInputs().getSchoolSelector().setSelectedIndex(0);
    }

}
