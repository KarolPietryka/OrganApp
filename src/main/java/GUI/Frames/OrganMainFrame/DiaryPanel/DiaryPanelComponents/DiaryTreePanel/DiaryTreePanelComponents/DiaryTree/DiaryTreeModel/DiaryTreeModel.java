package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeModel;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeModel.DiaryTreeNodesEditor.DiaryTreeModelEventHandler.ITreeModelEventHandler;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeModel.DiaryTreeNodesEditor.ITreeNodesEditor;
import System.DelayThread;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.io.IOException;

public class DiaryTreeModel extends DefaultTreeModel {

    public DiaryTreeModel(ITreeElement root,
                          ITreeNodesEditor diaryTreeNodesEditor,
                          ITreeModelEventHandler diaryTreeModelEventHandler) {
        super(root);
        this.addTreeModelListener(new DiaryTreeModelListener(diaryTreeNodesEditor, diaryTreeModelEventHandler));
    }

    @Override
    public void removeNodeFromParent(MutableTreeNode diaryTreeElement)
    {
        ITreeElement diaryTreeElementToRemove = (ITreeElement) diaryTreeElement;
        try{
            diaryTreeElementToRemove.deleteElement();
        }
        catch (IOException exc) {
            String dialogMessage = "Can not delete tree element. Directory doesn't exist.";
            JOptionPane.showMessageDialog(null, dialogMessage, "InfoBox: " + "Tree error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        super.removeNodeFromParent(diaryTreeElementToRemove);
    }

    class DiaryTreeModelListener implements TreeModelListener {

        private ITreeNodesEditor diaryTreeNodesEditor;
        private ITreeModelEventHandler diaryTreeModelEventHandler;

        public DiaryTreeModelListener(ITreeNodesEditor diaryTreeNodesEditor,
                                      ITreeModelEventHandler diaryTreeModelEventHandler)
        {
            this.diaryTreeNodesEditor = diaryTreeNodesEditor;
            this.diaryTreeModelEventHandler = diaryTreeModelEventHandler;
        }

        public void treeNodesChanged(TreeModelEvent e) {

            //delay();
            IDiaryTreeElement changedNode = diaryTreeModelEventHandler.getChangedTreeNode(e);

            if (changedNode.getUserObject().toString().isEmpty()) {
                removeNodeFromParent(changedNode);
            }
            else {
                diaryTreeNodesEditor.nodesChanged(changedNode);
            }
        }
        public void treeNodesInserted(TreeModelEvent e) { }
        public void treeNodesRemoved(TreeModelEvent e) { }
        public void treeStructureChanged(TreeModelEvent e) { }

        private void delay() {
            Runnable delay = new DelayThread(100);
            Thread delayThread = new Thread(delay);
            delayThread.start();
        }
    }
}
