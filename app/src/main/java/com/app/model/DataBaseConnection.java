package com.app.model;

import org.tinylog.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

/**
 * @author jhon
 * @version 1.0
 **/
public abstract class DataBaseConnection {

    /**
     * A Connection object is stored here
     **/
    private Connection dataBaseConnection;

    /**
     * Here the state of the driver load is stored
     **/
    private boolean driverState;

    /**
     * Default constructor
     **/
    public DataBaseConnection() {
        this.driverState = this.loadDriver();
    }

    /**
     * Method to load the database driver.
     **/
    public boolean loadDriver() {
        try (DataBaseCredentialsLoader loader = new DataBaseCredentialsLoader("/db/driver.properties")) {
            Logger.warn("Loading data base driver :v");
            Logger.info(loader.get("db.name"));
            Class.forName(loader.get("db.driver"));
            Logger.info("Data base driver loaded :)");
            loader.close();
            this.driverState = true;
            return true;
        } catch (ClassNotFoundException cnf) {
            Logger.error(cnf, ":(");
            this.driverState = false;
            return false;
        }
    }

    /**
     * Method used to check if the driver loaded or not.
     **/
    public boolean isDriverLoaded() {
        return this.driverState;
    }

    /**
     * Method to open connection to the database.
     **/
    public boolean open() throws SQLException {
        if (this.isDriverLoaded()) {
            DataBaseCredentialsLoader loader = new DataBaseCredentialsLoader("/db/credentials.properties");
            Logger.warn("Opening connection :v");
            this.dataBaseConnection = DriverManager.getConnection(loader.get("db.url"), loader.get("db.user"), loader.get("db.password"));
            Logger.info("Connection opened :)");
            loader.close();
            return this.dataBaseConnection != null;
        } else {
            Logger.error("The database driver was not loaded :(");
            return false;
        }
    }

    /**
     * Method to close the connection to the database.
     **/
    public boolean close() throws SQLException {
        if (this.isConnectionOpened()) {
            Logger.warn("Closing connection :v");
            this.dataBaseConnection.close();
            Logger.info("Connection closed :)");
            this.dataBaseConnection = null;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to check if the connection is open.
     **/
    public boolean isConnectionOpened() {
        return this.dataBaseConnection != null;
    }

    /**
     * Method to check if the connection is closed.
     **/
    public boolean isConnectionClosed() {
        return this.dataBaseConnection == null;
    }

    /**
     * Method to get an instance of the Connection object.
     **/
    protected Connection getConnection() {
        return this.dataBaseConnection;
    }

    /**
     *
     **/
    protected static class DataBaseCredentialsLoader implements DataLoader<String> {

        /**
         * Empty
         **/
        private Properties resourceFile;

        /**
         * Empty
         **/
        private final String resourcePath;

        /**
         * Empty
         **/
        private Reader resourceReader;

        /**
         * Default constructor
         **/
        DataBaseCredentialsLoader(String resource) {
            this.resourcePath = DataBaseCredentialsLoader.class.getResource(resource).getPath();
            try {
                this.load();
            } catch (IOException ex) {
                Logger.error(ex, ":(");
            }
        }

        /**
         * Empty
         **/
        @Override
        public void load() throws IOException {
            this.resourceFile = new Properties();
            this.resourceReader = new FileReader(new File(this.resourcePath));
            this.resourceFile.load(this.resourceReader);
        }

        /**
         * Empty
         **/
        @Override
        public String get(String param) {
            if (this.resourceFile != null) {
                return this.resourceFile.getProperty(param);
            } else {
                return null;
            }
        }

        /**
         * Empty
         **/
        @Override
        public void close() {
            if (this.resourceReader != null) {
                try {
                    this.resourceReader.close();
                    this.resourceReader = null;
                } catch (IOException io) {
                    Logger.error(io, ":(");
                }
            }
        }

    }

}
