package com.app.model;

import com.app.collection.linear.SimpleLinkedList;
import org.tinylog.Logger;

import java.sql.*;

/**
 * @author jhon
 * @version 1.0
 **/
public class StudentDAO extends DataBaseConnection {

    /**
     * Empty
     **/
    public StudentDAO() {
        super();
    }

    /**
     * Empty
     **/
    public boolean add(Student student) {
        try {
            if (this.open()) {
                PreparedStatement statement = null;
                try {
                    statement = this.getConnection().prepareStatement(
                            "INSERT INTO estudiante (estudiante.codigo, estudiante.Anombre, estudiante.email, estudiante.escuela) values (?, ?, ?, ?);");
                    try {
                        statement.setLong(1, student.getStudentCode());
                        statement.setString(2, student.getStudentAllName().toUpperCase());
                        statement.setString(3, student.getStudentEmail());
                        statement.setString(4, student.getStudentSchool());
                        statement.executeUpdate();
                        return true;
                    } catch (SQLException ex) {
                        Logger.error(ex, ":(");
                    }
                } catch (SQLException ex) {
                    Logger.error(ex, ":(");
                } finally {
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (SQLException ex) {
                            Logger.error(ex, ":(");
                        }
                    }
                    try {
                        if (this.close()) {
                            Logger.info("Connection closed properly :)");
                        } else {
                            Logger.error("The connection could not be closed :(");
                        }
                    } catch (SQLException ex) {
                        Logger.error(ex, ":(");
                    }
                }
            } else {
                Logger.error("Cannot connect to database :(");
            }
        } catch (SQLException ex) {
            Logger.error(ex, ":(");
        }
        return false;
    }

    /**
     * Empty
     **/
    public boolean update(Student student) {
        Student st = this.get(student.getStudentCode());
        if (st != null) {
            try {
                if (this.open()) {
                    Statement statement = null;
                    try {
                        statement = this.getConnection().createStatement();
                        try {
                            Student existingStudent = this.get(student.getStudentCode());
                            if (!existingStudent.getStudentAllName().equals(student.getStudentAllName())) {
                                statement.executeUpdate("UPDATE bd_curso_estructura.estudiante " +
                                        "SET bd_curso_estructura.estudiante.Anombre=" + student.getStudentAllName() +
                                        " WHERE id=" + student.getStudentId() + ";");
                            }

                            if (!existingStudent.getStudentSchool().equals(student.getStudentSchool())) {
                                statement.executeUpdate("UPDATE bd_curso_estructura.estudiante " +
                                        "SET bd_curso_estructura.estudiante.escuela=" + student.getStudentSchool() +
                                        " WHERE id=" + student.getStudentId() + ";");
                            }

                            if (!existingStudent.getStudentEmail().equals(student.getStudentEmail())) {
                                statement.executeUpdate("UPDATE bd_curso_estructura.estudiante " +
                                        "SET bd_curso_estructura.estudiante.email=" + student.getStudentEmail() +
                                        " WHERE id=" + student.getStudentId() + ";");
                            }

                            if (existingStudent.getStudentCode() != student.getStudentCode()) {
                                statement.executeUpdate("UPDATE bd_curso_estructura.estudiante " +
                                        "SET bd_curso_estructura.estudiante.codigo=" + student.getStudentCode() +
                                        " WHERE id=" + student.getStudentId() + ";");
                            }
                            return true;
                        } catch (SQLException ex) {
                            Logger.error(ex, ":(");
                        }
                    } catch (SQLException ex) {
                        Logger.error(ex, ":(");
                    } finally {
                        this.closeStatement(statement);
                    }
                } else {
                    Logger.error("Cannot connect to database :(");
                }
            } catch (SQLException ex) {
                Logger.error(ex, ":(");
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * Empty
     **/
    public void delete(long code) {
        try {
            if (this.open()) {
                Statement statement = null;
                try {
                    statement = this.getConnection().createStatement();
                    try {
                        statement.executeUpdate("DELETE FROM estudiante WHERE estudiante.codigo = " + code + ";");
                        this.resetIndexes(this.getConnection());
                    } catch (SQLException ex) {
                        Logger.error(ex, ":(");
                    }
                } catch (SQLException ex) {
                    Logger.error(ex);
                } finally {
                    this.closeStatement(statement);
                }
            } else {
                Logger.error("Cannot connect to database :(");
            }
        } catch (SQLException ex) {
            Logger.error(ex, ":(");
        }
    }

    private void resetIndexes(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("SET @count = 0;");
            statement.execute("UPDATE estudiante SET estudiante.id = @count:= @count + 1;");
            statement.execute("ALTER TABLE estudiante AUTO_INCREMENT = 1;");
        } catch (SQLException ex) {
            Logger.error(ex, ":(");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.error(ex, ":(");
                }
            }
        }
    }

    /**
     * Empty
     **/
    public Student get(long code) {
        try {
            if (this.open()) {
                Statement statement = null;
                try {
                    statement = this.getConnection().createStatement();
                    ResultSet resultSet = null;
                    try {
                        resultSet = statement.executeQuery("SELECT * FROM `estudiante` WHERE codigo=" + code + ";");
                        try {
                            if (resultSet.next()) {
                                Student student = new Student(
                                        resultSet.getLong("id"),
                                        resultSet.getString("Anombre"),
                                        resultSet.getLong("codigo"),
                                        resultSet.getString("email"),
                                        resultSet.getString("escuela"));
                                try {
                                    resultSet.close();
                                    return student;
                                } catch (SQLException ex) {
                                    Logger.error(ex, ":(");
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.error(ex, ":(");
                        }
                    } catch (SQLException ex) {
                        Logger.error(ex, ":(");
                    } finally {
                        if (resultSet != null) {
                            try {
                                resultSet.close();
                            } catch (SQLException ex) {
                                Logger.error(ex, ":(");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.error(ex, ":(");
                } finally {
                    this.closeStatement(statement);
                }
            } else {
                Logger.error("Cannot connect to database :(");
            }
        } catch (SQLException ex) {
            Logger.error(ex, ":(");
        }
        return null;
    }

    /**
     * Empty
     **/
    public SimpleLinkedList<Student> getStudents() {
        try {
            if (this.open()) {
                Statement statement = null;
                try {
                    statement = this.getConnection().createStatement();
                    ResultSet resultSet = null;
                    try {
                        resultSet = statement.executeQuery("SELECT * FROM bd_curso_estructura.estudiante;");
                        try {
                            SimpleLinkedList<Student> students = new SimpleLinkedList<>();
                            while (resultSet.next()) {
                                System.out.println(resultSet.getRow());
                                Student st = new Student(
                                        resultSet.getLong("id"),
                                        resultSet.getString("Anombre"),
                                        resultSet.getLong("codigo"),
                                        resultSet.getString("email"),
                                        resultSet.getString("escuela"));
                                students.add(st);
                                System.out.println(st);
                            }
                            try {
                                resultSet.close();
                                return students;
                            } catch (SQLException ex) {
                                Logger.error(ex, ":(");
                            }
                        } catch (SQLException ex) {
                            Logger.error(ex, ":(");
                        }
                    } catch (SQLException ex) {
                        Logger.error(ex, ":(");
                    } finally {
                        if (resultSet != null) {
                            try {
                                resultSet.close();
                            } catch (SQLException ex) {
                                Logger.error(ex, ":(");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.error(ex, ":(");
                } finally {
                    this.closeStatement(statement);
                }
            } else {
                Logger.error("Cannot connect to database :(");
            }
        } catch (SQLException ex) {
            Logger.error(ex, ":(");
        }
        return null;
    }

    private void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
                Logger.info("Statement closed :)");
            } catch (SQLException ex) {
                Logger.error(ex);
            }
        }

        try {
            if (this.close()) {
                Logger.info("Connection closed properly :)");
            } else {
                Logger.error("The connection could not be closed :(");
            }
        } catch (SQLException ex) {
            Logger.error(ex, ":(");
        }
    }

}
