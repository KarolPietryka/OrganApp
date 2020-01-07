package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.Actions.SaveBehaviour;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;

public interface ISaveBehaviour {
    public void save(Object elementToSave, String textToSave);
    public void setElementToSave(Object elementToSave);
    public void setTextToSave(String textToSave);
}
