package GUI.Frames.LoginFrame;

import GUI.Frames.OrganMainFrame.OrganMainFrame;
import GUI.Frames.LoginFrame.LoginFrameComponents.LoginPanel;
import GUI.Frames.LoginFrame.LoginFrameComponents.PasswordPanel;
import GUI.AppFramesObserverPattern.EFrames;
import GUI.AppFramesObserverPattern.IAppFramesObserver;
import GUI.Frames.OrganAppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends OrganAppFrame {

    private OrganMainFrame treeGUIPanel;

    public LoginFrame()
    {
        super();
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setResizable(false);
        //Add LoginPanel and PasswordPanel to Frame
        setLoginPanel();
        //Set mandatory stuff for Frame
        startFrame();
    }

    @Override
    public void updateObservers() {
        for(IAppFramesObserver observer:observers){
            observer.update(EFrames.OrganMainFrame);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setLoginPanel()
    {
        this.getContentPane().add(new LoginPanel());
        this.getContentPane().add(new PasswordPanel());
        this.getContentPane().add(createLoginButton());
    }
    private JButton createLoginButton()
    {
        JButton loginButton = new JButton("Log");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                updateObservers();
            }
        });
        return loginButton;
    }
}
