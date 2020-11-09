package com.app.control;

import com.app.view.MainWindow;

import javax.swing.*;

public class WindowsManager {

    public static WindowsManager localInstance = null;

    private final MainWindow mainWindow;
    private DataInputControl dataInputControl;
    private DataOutputControl dataOutputControl;

    public WindowsManager(String title) {
        this.mainWindow  = new MainWindow(title);
    }

    public void assembleWindow() {
        this.dataInputControl = new DataInputControl(this.getMainWindow());
        this.dataOutputControl = new DataOutputControl(this.getMainWindow());
    }

    public DataInputControl getDataInputControl() {
        return this.dataInputControl;
    }

    public DataOutputControl getDataOutputControl() {
        return this.dataOutputControl;
    }

    public MainWindow getMainWindow() {
        return this.mainWindow;
    }

    public static void loadNativeLookAndFeel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        UIManager.setLookAndFeel(lookAndFeelInfos[lookAndFeelInfos.length - 1].getClassName());
    }

}
