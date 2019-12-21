package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeModel.DiaryTreeNodesEditor;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;

import java.nio.file.FileSystemException;

public class DiaryTreeNodesEditor implements ITreeNodesEditor{

    public void nodesChanged(IDiaryTreeElement changedNode)
    {
        if (changedNode.isNewlyCreatedElement())
        {
            changedNode.resetElement();
        }
        else{
            try {
                changedNode.renameElement();
            }
            catch (FileSystemException exc)
            {
                exc.printStackTrace();
            }
        }
    }
}
