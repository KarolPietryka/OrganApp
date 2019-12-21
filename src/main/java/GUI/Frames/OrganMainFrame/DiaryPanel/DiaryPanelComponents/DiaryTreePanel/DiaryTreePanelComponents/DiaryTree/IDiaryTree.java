package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

public interface IDiaryTree {

    public Object getLastSelectedPathComponent();
    public Object getRoot();
    public void insertIntoTree(ITreeElement diaryTreeElementToInsert, ITreeElement parentElement);
    public ITreeElement getLastSelectedTreeElement() ;
    public void startEditingAtPath(TreePath treePath);
    public void removeFromTree(ITreeElement diaryTreeElementToRemove);



}
