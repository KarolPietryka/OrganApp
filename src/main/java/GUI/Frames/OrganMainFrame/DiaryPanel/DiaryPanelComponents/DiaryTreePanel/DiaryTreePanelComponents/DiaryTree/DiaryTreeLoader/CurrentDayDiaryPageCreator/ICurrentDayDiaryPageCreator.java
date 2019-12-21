package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader.CurrentDayDiaryPageCreator;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;

public interface ICurrentDayDiaryPageCreator {
    public ITreeElement createCurrentDayDiaryPage() throws NoSuchFieldException;
    //public ITreeElement createCurrentDayDiaryNote();
    public String getPageAbsolutePath();



}
