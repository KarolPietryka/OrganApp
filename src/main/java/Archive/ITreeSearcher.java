package Archive;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;

import javax.swing.tree.MutableTreeNode;

public interface ITreeSearcher {
    public ITreeElement searchElementStartingFromStartNode(
            MutableTreeNode lastSelectedNode,
            ITreeElement searchingStartNode)throws NoSuchFieldException;
}
