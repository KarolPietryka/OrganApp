package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.TreeButtonPanel.Buttons;



import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.IDiaryTree;
import GUI.GUIInterfaces.IOrganButton;

import javax.swing.tree.*;
import java.io.File;

public class AddNodeButton implements IOrganButton {


    private IDiaryTree diaryTree;
    private String filesFormat;
    public AddNodeButton(IDiaryTree diaryTree,
                         String filesFormat)
    {
        this.diaryTree = diaryTree;
        this.filesFormat = filesFormat;
    }

    public void action()
    {
        //TODO: change from "def" to current date if there is no current date file in the tree
        //Gets last selected or root if there is no last selected
        IDiaryTreeElement parentElement;

        parentElement = (IDiaryTreeElement)diaryTree.getLastSelectedTreeElement();

        //TODO: if paren object is found now it have to be change on directory and file must be add to this directory right with new created file(newNodeToSelect)
        //TODO: podobna obsługa music byc w removeNode
        //Check if parentElement is directory
        File parentElementFileDirectory = parentElement.getDirectoryFile().getFile();
        if (parentElementFileDirectory.isDirectory())
        {
            ITreeElement treeElementToAdd = new DiaryTreeElement(new File(parentElementFileDirectory.getAbsolutePath() + "\\" + "new"), filesFormat);
            diaryTree.insertIntoTree(treeElementToAdd, parentElement);

            TreePath newNodeTreePath = new TreePath(((DefaultMutableTreeNode) treeElementToAdd).getPath());
            diaryTree.startEditingAtPath(newNodeTreePath);}
        }
}
