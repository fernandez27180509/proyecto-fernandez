package com.app.view.network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author jhon
 * @version 1.0
 **/
public class NetworkViewDisconnected extends JPanel {

    /** Empty **/
    private final JLabel messageField;

    /** Empty **/
    private final JButton connectButton;

    /** Empty **/
    public NetworkViewDisconnected() {
        super(new FlowLayout());

        this.messageField = new JLabel();
        this.add(this.messageField);

        this.connectButton = new JButton("Reintentar", new ImageIcon(Toolkit.getDefaultToolkit()
                .getImage(NetworkViewDisconnected.class.getResource("/icon/reconnect.png"))));
        this.add(this.connectButton);
    }

    /** Empty **/
    public void setMessage(String message) {
        this.messageField.setText(message);
    }

    /** Empty **/
    public String getMessage() {
        return this.messageField.getText();
    }

    /** Empty **/
    public void addConnectButtonAction(ActionListener action) {
        if (action != null) {
            this.connectButton.addActionListener(action);
        }
    }

}
