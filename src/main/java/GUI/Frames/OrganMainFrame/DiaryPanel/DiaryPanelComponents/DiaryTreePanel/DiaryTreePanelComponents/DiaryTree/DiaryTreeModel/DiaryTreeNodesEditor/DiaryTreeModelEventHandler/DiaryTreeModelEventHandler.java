package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeModel.DiaryTreeNodesEditor.DiaryTreeModelEventHandler;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;

import javax.swing.event.TreeModelEvent;

public class DiaryTreeModelEventHandler implements ITreeModelEventHandler{

    public IDiaryTreeElement getChangedTreeNode(TreeModelEvent e)
    {
        //Parent node
        IDiaryTreeElement node = (IDiaryTreeElement) (e.getTreePath().getLastPathComponent());
        try {
            int index = e.getChildIndices()[0];
            //Changed node
            node = (IDiaryTreeElement) (node.getChildAt(index));
        } catch (NullPointerException exc) {System.out.println(exc.getMessage());}

        return node;
    }
}
