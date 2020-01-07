package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.KeyBindingSetter.IKeyBindingSetter;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.Actions.SaveBehaviour.ISaveBehaviour;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.Actions.SaveBehaviour.SaveDailyNoteBehaviour;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.ETextComponentActions;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.TextComponentActions;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.EditorPaneContentSetter.ITextComponentContentSetter;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import java.awt.*;


public class DailyNoteEditorPane extends JEditorPane implements TreeSelectionListener{

    private ITextComponentContentSetter editorPaneContentSetter;
    private TextComponentActions textComponentActions;
    private ISaveBehaviour saveDailyNoteBehaviour;

    public DailyNoteEditorPane(TextComponentActions textComponentActions,
                               ITextComponentContentSetter editorPaneContentSetter,
                               IKeyBindingSetter dailyNoteBindingSetter) {
        super();
        this.textComponentActions = textComponentActions;
        this.editorPaneContentSetter = editorPaneContentSetter;
        this.saveDailyNoteBehaviour = getSaveBehaviourFromActionsList();

        setEditable(true);
        setMinimumSize(new Dimension(400, 300));

        dailyNoteBindingSetter.addBindings(this);

        Document dailyNoteEditorPaneDocument = this.getDocument();
        dailyNoteEditorPaneDocument.addUndoableEditListener(textComponentActions.getOrganFrameUndoableEditListener());
        dailyNoteEditorPaneDocument.addDocumentListener((SaveDailyNoteBehaviour)getSaveBehaviourFromActionsList());
    }

    public void valueChanged(TreeSelectionEvent e) {
        if (e.getOldLeadSelectionPath() != null && e.getNewLeadSelectionPath()!= null) {
            IDiaryTreeElement oldLastSelectedDiaryTreeElement = (IDiaryTreeElement) e.getOldLeadSelectionPath().getLastPathComponent();
            IDiaryTreeElement newLastSelectedDiaryTreeElement = (IDiaryTreeElement) e.getNewLeadSelectionPath().getLastPathComponent();
            saveElement(oldLastSelectedDiaryTreeElement, newLastSelectedDiaryTreeElement);
            editorPaneContentSetter.setContent(this, newLastSelectedDiaryTreeElement.getNoteFile());//Load new fileText to Document from noteFile
            saveDailyNoteBehaviour.setTextToSave(this.getText());
        }
        else if(e.getOldLeadSelectionPath() == null)//For root case
        {
            IDiaryTreeElement newLastSelectedDiaryTreeElement = (IDiaryTreeElement) e.getNewLeadSelectionPath().getLastPathComponent();
            editorPaneContentSetter.setContent(this, newLastSelectedDiaryTreeElement.getNoteFile());
        }
        else{// Remove node case. Just return called, because valueChanged will be call once again in the moment.
            return;}
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void saveElement(IDiaryTreeElement diaryTreeElementToSave, IDiaryTreeElement newLastSelectedDiaryTreeElement) {
        saveDailyNoteBehaviour.save(diaryTreeElementToSave, this.getText());

        saveDailyNoteBehaviour.setElementToSave(newLastSelectedDiaryTreeElement);
    }
    private ISaveBehaviour getSaveBehaviourFromActionsList()
    {
        ISaveBehaviour saveDailyNote = null;
        Action saveAction = textComponentActions.getActionByName(ETextComponentActions.Save.toString());
        try{
            saveDailyNote = (ISaveBehaviour)saveAction;
        }
        catch(ClassCastException ex){
            ex.printStackTrace();
        }
        return saveDailyNote;
    }
}
