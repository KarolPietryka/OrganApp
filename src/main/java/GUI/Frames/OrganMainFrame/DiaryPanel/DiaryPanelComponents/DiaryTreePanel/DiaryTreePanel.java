package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTree;

import javax.swing.*;
import java.awt.*;

public class DiaryTreePanel extends JPanel {

    private DiaryTree tree;
    private JComponent treeButtonsPanel;

    public DiaryTreePanel(DiaryTree _tree,
                          JComponent _treeButtonsPanel) {
        super();
        tree                = _tree;
        treeButtonsPanel    = _treeButtonsPanel;

        this.setLayout(new BorderLayout());
        setBackground(Color.white);

        //Construction of panel
        add(BorderLayout.CENTER, tree);
        add(BorderLayout.SOUTH, treeButtonsPanel);
    }
}
