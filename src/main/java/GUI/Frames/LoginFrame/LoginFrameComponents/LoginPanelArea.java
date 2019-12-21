package GUI.Frames.LoginFrame.LoginFrameComponents;

import javax.swing.*;
import java.awt.*;

abstract public class LoginPanelArea extends JPanel {

    //public JPanel panel;
    public String textFieldLabel;
    public Dimension textFieldDim = new Dimension(200,20);
    public void render()
    {
        //panel = new JPanel();
        setLayout(new FlowLayout());

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(textFieldDim);

        this.add(new Label(textFieldLabel));
        this.add(passwordField);
    }
}
