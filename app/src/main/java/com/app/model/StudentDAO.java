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
     * sql: UPDATE estudiante SET nombre Anombre='jhon' WHERE id=1;
     **/
    public boolean update(Student student) {
        Student st = this.get(student.getStudentCode());
        if (st != null) {
            try {
                if (this.open()) {
                    boolean state = true;
                    if (student.getStudentCode() != st.getStudentCode()) {
                        Logger.info("The codes are different");
                        state = this.executeUpdate("UPDATE estudiante SET codigo=" + student.getStudentCode() + " WHERE id=" + st.getStudentId());
                    }

                    if (!student.getStudentAllName().equals(st.getStudentAllName())) {
                        Logger.info("The names are different");
                        state = state && this.executeUpdate("UPDATE estudiante SET Anombre='" + student.getStudentAllName()+ "' WHERE id=" + st.getStudentId() + ";");
                    }

                    if (!student.getStudentEmail().equals(st.getStudentEmail())) {
                        Logger.info("The emails are different");
                        state = state && this.executeUpdate("UPDATE estudiante SET email='" + student.getStudentEmail() + "' WHERE id=" + st.getStudentId() + ";");
                    }

                    if (!student.getStudentSchool().equals(st.getStudentSchool())) {
                        Logger.info("The schools are different");
                        state = state && this.executeUpdate("UPDATE estudiante SET escuela='" + student.getStudentSchool() + "' WHERE id=" + st.getStudentId() + ";");
                    }
                    return state;
                } else {
                    Logger.error("Cannot connect to database :(");
                }
            } catch (SQLException ex) {
                Logger.error(ex, ":(");
            } finally {
                if (this.isConnectionOpened()) {
                    try {
                        if (this.close()) {
                            Logger.info("Connection closed properly :)");
                        } else {
                            Logger.error("The connection could not be closed :(");
                        }
                    } catch (SQLException ex) {
                        Logger.error(ex, ":(");
                    }
                } else {
                    Logger.info("The connection is closed :)");
                }
            }
        } else {
            return false;
        }
        return false;
    }

    private boolean executeUpdate(String sql) {
        Statement update = null;
        try {
            update = this.getConnection().createStatement();
            update.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.error(ex);
        } finally {
            if (update != null) {
                try {
                    update.close();
                } catch (SQLException ex) {
                    Logger.error(ex);
                }
            }
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
                                Student st = new Student(
                                        resultSet.getLong("id"),
                                        resultSet.getString("Anombre"),
                                        resultSet.getLong("codigo"),
                                        resultSet.getString("email"),
                                        resultSet.getString("escuela"));
                                students.add(st);
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
