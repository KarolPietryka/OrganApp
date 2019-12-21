package GUI.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

public class TreeNodeChildrenEnumeration extends DefaultMutableTreeNode implements Enumeration<TreeNode> {

    private ArrayList<ITreeElement> children;
    private Iterator <ITreeElement> childrenIterator;
    public TreeNodeChildrenEnumeration(ArrayList<ITreeElement> children)
    {
        this.children = children;
        this.childrenIterator = children.iterator();
    }

    @Override
    public boolean hasMoreElements() {
        return childrenIterator.hasNext();
    }

    @Override
    public TreeNode nextElement() {
        return childrenIterator.next();
    }
}
