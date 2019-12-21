package GUI.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeNodesEditor;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeModel.DiaryTreeNodesEditor.DiaryTreeNodesEditor;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeModel.DiaryTreeNodesEditor.ITreeNodesEditor;
import ITreeElementMock.DiaryTreeElementMock;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.nio.file.FileSystemException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DiaryTreeNodesEditorTest {


    @Test
    public void _1_nodesChanged_CheckIfNodeWasResetWhenItsNewlyCreated_IsNewlyCreated() throws FileSystemException{

        IDiaryTreeElement changedNode = new DiaryTreeElementMock.Builder<File>().isNewlyCreatedElement(true).build();
        ITreeNodesEditor diaryTreeNodesEditor = Mockito.spy(new DiaryTreeNodesEditor());
        diaryTreeNodesEditor.nodesChanged(changedNode);

        verify(changedNode,times(1)).resetElement();
        verify(changedNode, times(0)).renameElement();

    }
    @Test
    public void _1_nodesChanged_CheckIfNodeWasResetWhenItsNewlyCreated_IsNotNewlyCreated(){

        IDiaryTreeElement changedNode = new DiaryTreeElementMock.Builder<File>().isNewlyCreatedElement(false).build();
        ITreeNodesEditor diaryTreeNodesEditor = Mockito.spy(new DiaryTreeNodesEditor());
        diaryTreeNodesEditor.nodesChanged(changedNode);

        verify(changedNode,times(0)).resetElement();
    }

    @Test
    public void _1_nodesChanged_ChangedElementIsNewlyCreatedAndNotToRemove_resetElementMethodWasCalled() throws FileSystemException
    {
        IDiaryTreeElement changedNode = new DiaryTreeElementMock.Builder<File>().isNewlyCreatedElement(false).build();
        ITreeNodesEditor diaryTreeNodesEditor = Mockito.spy(new DiaryTreeNodesEditor());
        diaryTreeNodesEditor.nodesChanged(changedNode);

        verify(changedNode, times(1)).renameElement();

    }
}