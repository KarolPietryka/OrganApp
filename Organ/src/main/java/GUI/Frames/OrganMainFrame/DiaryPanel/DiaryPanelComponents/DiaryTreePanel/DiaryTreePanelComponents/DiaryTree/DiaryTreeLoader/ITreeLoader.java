package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.IDiaryTree;

public interface ITreeLoader {
    public void loadTree(IDiaryTree diaryTree) throws NoSuchFieldException;
}
