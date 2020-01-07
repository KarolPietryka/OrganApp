package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.Actions.SaveBehaviour;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;

public class SaveDailyNoteBehaviour extends AbstractAction implements ISaveBehaviour, DocumentListener {

    private IDiaryTreeElement diaryTreeElementToSave;
    private String textToSave;

    @Override //AbstractAction
    public void actionPerformed(ActionEvent e) {
        saveElement();
    }
    @Override //DocumentListener
    public void insertUpdate(DocumentEvent e) {
        updateTextToSave(e);
    }
    @Override //DocumentListener
    public void removeUpdate(DocumentEvent e) {
        updateTextToSave(e);
    }
    @Override //DocumentListener
    public void changedUpdate(DocumentEvent e) {
        updateTextToSave(e);
    }
    public void setElementToSave(Object elementToSave){
        diaryTreeElementToSave = (IDiaryTreeElement)elementToSave;
    }
    public void setTextToSave(String textToSave){
        this.textToSave = textToSave;
    }
    public void save(Object elementToSave, String textToSave) {
        diaryTreeElementToSave = (IDiaryTreeElement)elementToSave ;
        this.textToSave = textToSave;
        saveElement();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void updateTextToSave(DocumentEvent e) {
        int length = e.getDocument().getLength();
        try {
            textToSave = e.getDocument().getText(0, length);
        } catch (BadLocationException badLocationException) {
            System.out.println("Contents: Unknown");
        }
    }
    private void saveElement() {
        diaryTreeElementToSave.saveTextInNoteFile(textToSave);
    }
}
