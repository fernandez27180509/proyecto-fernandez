package com.app.model;

import com.app.collection.linear.SimpleLinkedList;
import org.tinylog.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author jhon
 * @version 1.0
 **/
public class SchoolDAO extends DataBaseConnection {

    /** Empty **/
    public SchoolDAO() {super();}

    /** Empty **/
    public SimpleLinkedList<String> getSchools() {
        try {
            if (this.open()) {
                Statement statement = null;
                try {
                    statement = this.getConnection().createStatement();
                    ResultSet resultSet = null;
                    try {
                        resultSet = statement.executeQuery("SELECT escuela.nombre FROM escuela;");
                        SimpleLinkedList<String> schools = new SimpleLinkedList<>();
                        while (resultSet.next()) {
                            schools.add(resultSet.getString("nombre"));
                        }
                        return schools;
                    } catch (SQLException ex) {
                        Logger.error(":(");
                    } finally {
                        if (resultSet != null) {
                            try {
                                resultSet.close();
                                Logger.info("Result Set closed");
                            } catch (SQLException ex) {
                                Logger.error(ex, ":(");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.error(ex, ":(");
                } finally {
                    if (statement != null) {
                        try {
                            statement.close();
                            Logger.info("Statement closed :)");
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
        return null;
    }

}
