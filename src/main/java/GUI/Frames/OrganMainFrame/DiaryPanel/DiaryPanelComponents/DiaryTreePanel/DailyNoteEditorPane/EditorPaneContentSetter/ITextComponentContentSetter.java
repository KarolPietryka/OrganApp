package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane.EditorPaneContentSetter;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile.NoteFile;

import javax.swing.text.JTextComponent;
import java.io.File;

public interface ITextComponentContentSetter {
    public void setContent(JTextComponent textComponent, NoteFile textFile);
}
