package GUI.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementDirectoriesEditor.ChildrenElementsDirectoriesRefresher;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementDirectoriesEditor.DiaryTreElementsDirectoriesStructureRefresher;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import GUI.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.TreeNodeChildrenEnumeration;
import ITreeElementMock.DiaryTreeElementMock;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class DiaryTreElementsDirectoriesStructureRefresherTest {

    @Test
    public void _refresh()
    {
        IDiaryTreeElement folderFirstChild = new DiaryTreeElementMock.Builder<File>()
                .children(null)
                .childCount(0).build();
        IDiaryTreeElement folderSecondChild = new DiaryTreeElementMock.Builder<File>()
                .children(null)
                .childCount(0).build();
        IDiaryTreeElement rootFirstChild = new DiaryTreeElementMock.Builder<File>()
                .children(null)
                .childCount(0).build();
        IDiaryTreeElement rootSecondChild = new DiaryTreeElementMock.Builder<File>()
                .children(null)
                .childCount(0).build();
        IDiaryTreeElement rootThirdChild =  new DiaryTreeElementMock.Builder<File>()
                .children(
                        new TreeNodeChildrenEnumeration(
                            new ArrayList<ITreeElement>(
                                    Arrays.asList(
                                            folderFirstChild,
                                            folderSecondChild
                                    )
                            )
                        )
                )
                .childCount(2).build();


        IDiaryTreeElement root = new DiaryTreeElementMock.Builder<File>()
                .children(
                    new TreeNodeChildrenEnumeration(new ArrayList<ITreeElement>(
                            Arrays.asList(
                                    rootFirstChild,
                                    rootSecondChild,
                                    rootThirdChild)))).
                userObject("Root").
                childCount(3).build();

        DiaryTreElementsDirectoriesStructureRefresher.refresh(root);
        verify(root, times(1)).updateDirectories();
        verify(rootFirstChild, times(1)).updateDirectories();
        verify(rootSecondChild, times(1)).updateDirectories();
        verify(rootThirdChild, times(1)).updateDirectories();
        verify(folderFirstChild, times(1)).updateDirectories();
        verify(folderSecondChild, times(1)).updateDirectories();

    }
}