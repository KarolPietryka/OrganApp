package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader.ITreeLoader;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;
import java.awt.*;

public class DiaryTree extends JTree implements IDiaryTree {

    private ITreeLoader diaryTreeLoader;
    private DefaultTreeModel diaryTreeModel;

    public DiaryTree(DefaultTreeModel diaryTreeModel,
                     ITreeLoader diaryTreeLoader,
                     TreeSelectionListener dailyNoteEditorPane) {
        super(diaryTreeModel);
        this.addTreeSelectionListener(dailyNoteEditorPane);

        this.diaryTreeModel = diaryTreeModel;
        this.diaryTreeLoader = diaryTreeLoader;

        initTree();
        loadTree();
    }


    @Override
    public ITreeElement getLastSelectedTreeElement() {
        MutableTreeNode lastSelectedNode = (DefaultMutableTreeNode) getLastSelectedPathComponent();
        if (lastSelectedNode == null)
            lastSelectedNode = this.getRoot();
        return (ITreeElement) lastSelectedNode;
    }

    public void insertIntoTree(ITreeElement diaryTreeElementToInsert, ITreeElement parentElement) {
        diaryTreeModel.insertNodeInto(diaryTreeElementToInsert, parentElement, parentElement.getChildCount());
    }

    public void removeFromTree(ITreeElement diaryTreeElementToRemove) {
        diaryTreeModel.removeNodeFromParent(diaryTreeElementToRemove);
    }

    public ITreeElement getRoot() {
        return (ITreeElement) diaryTreeModel.getRoot();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void loadTree(){
        try {
            this.diaryTreeLoader.loadTree(this);
        }
        catch (NoSuchFieldException exc) {
            exc.getMessage();
        }
    }

    private void initTree() {
        this.setEditable(true);
        this.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        this.setPreferredSize(new Dimension(220, 50));
        this.setInvokesStopCellEditing(true);

        boolean playWithLineStyle = false;
        String lineStyle = "Horizontal";
        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            this.putClientProperty("JTree.lineStyle", lineStyle);
        }
    }
}
