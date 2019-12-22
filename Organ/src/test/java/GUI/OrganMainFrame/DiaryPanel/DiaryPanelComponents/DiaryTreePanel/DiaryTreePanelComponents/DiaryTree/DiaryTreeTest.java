package GUI.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane.DailyNoteEditorPane;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTree;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader.DiaryTreeLoader;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.IDiaryTree;
import ITreeElementMock.DiaryTreeElementMock;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.tree.DefaultTreeModel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DiaryTreeTest {


    private IDiaryTree getDiaryTreeSpy()
    {
        ITreeElement root = Mockito.mock(ITreeElement.class);
        DefaultTreeModel diaryTreeModel = getDiaryTreeModelMock(root);

        DiaryTreeLoader diaryTreeLoader = Mockito.mock(DiaryTreeLoader.class);
        DailyNoteEditorPane dailyNoteEditorPane = mock(DailyNoteEditorPane.class);

        IDiaryTree diaryTree = Mockito.spy(new DiaryTree(diaryTreeModel, diaryTreeLoader, dailyNoteEditorPane));
        doReturn(root).when(diaryTree).getRoot();

        return diaryTree;
    }
    private DefaultTreeModel getDiaryTreeModelMock(ITreeElement root)
    {
        DefaultTreeModel diaryTreeModel = Mockito.mock(DefaultTreeModel.class);
        when(diaryTreeModel.getRoot()).thenReturn(root);
        return diaryTreeModel;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //region getLastSelectedTreeElement
    @Test
    public void _1_getLastSelectedTreeElement_NoLastSelectedNode_VerifyIfGetLastSelectedPathComponentMethodWasCalled() {

        IDiaryTree diaryTree = getDiaryTreeSpy();
        diaryTree.getLastSelectedTreeElement();

        verify(diaryTree, times(1)).getLastSelectedPathComponent();
    }
    @Test
    public void _1_getLastSelectedTreeElement_LastSelectedNodeExist_VerifyIfGetRootMethodWasNotCalled() {

        IDiaryTree diaryTree = getDiaryTreeSpy();
        when(diaryTree.getLastSelectedPathComponent()).thenReturn(new DiaryTreeElementMock.Builder().build());
        diaryTree.getLastSelectedTreeElement();

        verify(diaryTree, times(0)).getRoot();
    }
    @Test
    public void _1_getLastSelectedTreeElement_NoLastSelectedNode_VerifyIfGetRootMethodWasCalled() {

        IDiaryTree diaryTree = getDiaryTreeSpy();
        diaryTree.getLastSelectedTreeElement();

        verify(diaryTree, times(1)).getRoot();
    }
    @Test
    public void _1_getLastSelectedTreeElement_NoLastSelectedNode_ResultEqualsRoot() {//Do not have to check if there is lastSelectedNode because method named JTree.getLastSelectedPathComponent its Java method so dont have to test it.

        ITreeElement root = Mockito.mock(ITreeElement.class);// inicialization here becouse we needed root in assert statement
        DefaultTreeModel diaryTreeModel = getDiaryTreeModelMock(root);
        DiaryTreeLoader diaryTreeLoader = Mockito.mock(DiaryTreeLoader.class);
        DailyNoteEditorPane dailyNoteEditorPane = mock(DailyNoteEditorPane.class);

        IDiaryTree diaryTree = Mockito.spy(new DiaryTree(diaryTreeModel, diaryTreeLoader, dailyNoteEditorPane));

        assertEquals(diaryTree.getLastSelectedTreeElement(), root);
    }
    //endregion


}