package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane.EditorPaneContentSetter.ITextComponentContentSetter;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane.KeyBindingSetter.IKeyBindingSetter;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane.TextComponentActions.TextComponentActions;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import java.awt.*;
import java.io.*;


public class DailyNoteEditorPane extends JEditorPane implements TreeSelectionListener {

    private ITextComponentContentSetter editorPaneContentSetter;

    public DailyNoteEditorPane(TextComponentActions textComponentActions,
                               ITextComponentContentSetter editorPaneContentSetter,
                               IKeyBindingSetter dailyNoteBindingSetter) {
        super();
        this.editorPaneContentSetter = editorPaneContentSetter;
        setEditable(true);
        setMinimumSize(new Dimension(400, 300));

        dailyNoteBindingSetter.addBindings(this);

        Document dailyNoteEditorPaneDocument = this.getDocument();
        dailyNoteEditorPaneDocument.addUndoableEditListener(textComponentActions.getOrganFrameUndoableEditListener());
    }


    public void valueChanged(TreeSelectionEvent e) {
        if (e.getOldLeadSelectionPath() != null && e.getNewLeadSelectionPath()!= null) {
            IDiaryTreeElement oldLastSelectedDiaryTreeElement = (IDiaryTreeElement) e.getOldLeadSelectionPath().getLastPathComponent();
            IDiaryTreeElement newLastSelectedDiaryTreeElement = (IDiaryTreeElement) e.getNewLeadSelectionPath().getLastPathComponent();
            save(oldLastSelectedDiaryTreeElement);
            editorPaneContentSetter.setContent(this, newLastSelectedDiaryTreeElement.getNoteFile());
        }
        else if(e.getOldLeadSelectionPath() == null)
        {
            IDiaryTreeElement newLastSelectedDiaryTreeElement = (IDiaryTreeElement) e.getNewLeadSelectionPath().getLastPathComponent();
            editorPaneContentSetter.setContent(this, newLastSelectedDiaryTreeElement.getNoteFile());
        }
        else{// Remove node case. Just return called, because valueChanged will be call once again in the moment.
            return;}
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void save(IDiaryTreeElement elementToSave) {
        elementToSave.saveTextInNoteFile(this.getText());
    }
}
