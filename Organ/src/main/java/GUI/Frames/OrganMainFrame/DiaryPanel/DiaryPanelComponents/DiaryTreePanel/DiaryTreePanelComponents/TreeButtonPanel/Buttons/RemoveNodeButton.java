package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.TreeButtonPanel.Buttons;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTree;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import GUI.GUIInterfaces.IOrganButton;

import javax.swing.tree.*;

public class RemoveNodeButton implements IOrganButton {

    DiaryTree diaryTree;

    public RemoveNodeButton(DiaryTree diaryTree)
    {
        this.diaryTree = diaryTree;
    }
    public void action()
    {
        ITreeElement elementToRemove;
        TreeNode parentOfElementToRemove;

        elementToRemove = diaryTree.getLastSelectedTreeElement();
        parentOfElementToRemove = elementToRemove.getParent();

        TreePath parentPath = new TreePath(((DefaultMutableTreeNode) elementToRemove.getParent()).getPath());

        if (elementToRemove != diaryTree.getRoot())
        {
            diaryTree.removeFromTree(elementToRemove);
        }
        diaryTree.setSelectionPath(parentPath);
    }
}
