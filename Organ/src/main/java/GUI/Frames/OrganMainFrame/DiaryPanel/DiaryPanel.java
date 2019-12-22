package GUI.Frames.OrganMainFrame.DiaryPanel;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.SplitPaneDiary;

import javax.swing.*;

public class DiaryPanel extends JPanel  {

    SplitPaneDiary splitPane;
    public DiaryPanel(SplitPaneDiary _splitPane ) {

        super();
        splitPane = _splitPane;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Create SplitPane that contains Tree and DailyNoteTextArea
        add(splitPane);
    }
}