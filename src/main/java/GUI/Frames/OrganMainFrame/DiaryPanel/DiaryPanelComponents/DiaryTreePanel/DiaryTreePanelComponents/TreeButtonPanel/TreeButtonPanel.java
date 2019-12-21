package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.TreeButtonPanel;

import GUI.GUIInterfaces.IOrganButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreeButtonPanel extends JPanel{

    private IOrganButton addButton;
    private IOrganButton removeButton;

    public TreeButtonPanel(IOrganButton _addButton,
                            IOrganButton _removeButton)
    {
        addButton = _addButton;
        removeButton = _removeButton;

        JButton addNodeButton = new JButton("Add new day");
        JButton removeNodeButton = new JButton("Remove the day");
        addNodeButton.addActionListener(new AddButtonListener());
        removeNodeButton.addActionListener(new RemoveButtonListener());

        this.setLayout(new FlowLayout());
        this.setBackground(Color.white);
        this.add(addNodeButton);
        this.add(removeNodeButton);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class AddButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) { addButton.action();}
    }

    class RemoveButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) { removeButton.action();}
    }
}
