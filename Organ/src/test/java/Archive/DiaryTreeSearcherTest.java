package Archive;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import GUI.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.TreeNodeChildrenEnumeration;
import ITreeElementMock.DiaryTreeElementMock;
import org.junit.Test;
import org.mockito.Mockito;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiaryTreeSearcherTest {

    private DefaultMutableTreeNode getDefaultMutableTreeNodeMock(String toStringReturn)
    {
        DefaultMutableTreeNode defaultMutableTreeNode = mock(DefaultMutableTreeNode.class);
        when(defaultMutableTreeNode.toString()).thenReturn(toStringReturn);
        return defaultMutableTreeNode;
    }

    @Test
    public void _1_1_searchElementThatRepresentsNode_CheckIfFoundElementIsLastSelectedElement() throws NoSuchFieldException {

        ITreeElement lastSelectedElement = new DiaryTreeElementMock.Builder<File>().children(
                null).
                userObject(("FileToFind")).
                childCount(0).build();

        MutableTreeNode lastSelectedNode = lastSelectedElement;

        ArrayList<ITreeElement> rootChildren =
                new ArrayList<ITreeElement>(
                        Arrays.asList(
                                new DiaryTreeElementMock.Builder<File>().children(//first child of the root
                                        null).
                                        userObject("J").
                                        childCount(0).build(),
                                new DiaryTreeElementMock.Builder<File>().children(//second child of the root
                                        null).
                                        userObject("K").
                                        childCount(0).build(),
                                lastSelectedElement
                        )
                );
        ITreeElement root = new DiaryTreeElementMock.Builder<File>().children(
                new TreeNodeChildrenEnumeration(rootChildren)).
                userObject("Root").
                childCount(3).build();

        ITreeSearcher diaryTreeSearcher = Mockito.spy(new DiaryTreeSearcher());

        assertEquals(diaryTreeSearcher.searchElementStartingFromStartNode(lastSelectedNode, root), lastSelectedElement);

    }

    @Test
    public void _1_2_searchElementThatRepresentsNode_CheckIfFoundElementIsLastSelectedElement() throws NoSuchFieldException {

        ITreeElement lastSelectedElement = new DiaryTreeElementMock.Builder<File>().children(
                null).
                userObject("FileToFind").
                childCount(0).build();

        MutableTreeNode lastSelectedNode = lastSelectedElement;

        ArrayList<ITreeElement> rootChildren =
                new ArrayList<ITreeElement>(
                        Arrays.asList(
                                new DiaryTreeElementMock.Builder<File>().children(//first child of the root
                                        null).
                                        userObject("J").
                                        childCount(0).build(),
                                new DiaryTreeElementMock.Builder<File>().children(//second child of the root
                                        null).
                                        userObject("K").
                                        childCount(0).build(),
                                new DiaryTreeElementMock.Builder<File>().children(//third child of the root which contains two other children
                                        new TreeNodeChildrenEnumeration(//declaration of third child of the root children
                                            new ArrayList<ITreeElement>(
                                                    Arrays.asList(
                                                            new DiaryTreeElementMock.Builder<File>().children(//first child
                                                                    null).
                                                                    userObject("Z").
                                                                    childCount(0).build(),
                                                            lastSelectedElement//second child
                                                            )
                                                    )
                                            )
                                    ).userObject("Folder").childCount(2).build()
                        )
                );
        ITreeElement root = new DiaryTreeElementMock.Builder<File>().children(
                new TreeNodeChildrenEnumeration(rootChildren)).
                userObject("Root").
                childCount(3).build();

        ITreeSearcher diaryTreeSearcher = Mockito.spy(new DiaryTreeSearcher());

        assertEquals(diaryTreeSearcher.searchElementStartingFromStartNode(lastSelectedNode, root), lastSelectedElement);

    }

    @Test (expected = NoSuchFieldException.class)
    public void _1_searchElementThatRepresentsNode_NoSearchingElementInTheTree() throws NoSuchFieldException
    {
        ITreeElement lastSelectedElement = new DiaryTreeElementMock.Builder<File>().children(
                null).
                userObject("NoSuchFile").
                childCount(0).build();

        MutableTreeNode lastSelectedNode = getDefaultMutableTreeNodeMock("S");

        ArrayList<ITreeElement> rootChildren =
                new ArrayList<ITreeElement>(
                        Arrays.asList(
                                new DiaryTreeElementMock.Builder<File>().children(//first child of the root
                                        null).
                                        userObject("J").
                                        childCount(0).build(),
                                new DiaryTreeElementMock.Builder<File>().children(//second child of the root
                                        null).
                                        userObject("K").
                                        childCount(0).build(),
                                new DiaryTreeElementMock.Builder<File>().children(//third child of the root which contains two other children
                                        new TreeNodeChildrenEnumeration(//declaration of third child of the root children
                                                new ArrayList<ITreeElement>(
                                                        Arrays.asList(
                                                                new DiaryTreeElementMock.Builder<File>().children(//first child
                                                                        null).
                                                                        userObject("Z").
                                                                        childCount(0).build(),
                                                                lastSelectedElement//second child
                                                        )
                                                )
                                        )
                                ).userObject("Folder").childCount(2).build()
                        )
                );
        ITreeElement root = new DiaryTreeElementMock.Builder<File>().children(
                new TreeNodeChildrenEnumeration(rootChildren)).
                userObject("Root").
                childCount(3).build();

        ITreeSearcher diaryTreeSearcher = Mockito.spy(new DiaryTreeSearcher());

        assertEquals(diaryTreeSearcher.searchElementStartingFromStartNode(lastSelectedNode, root), lastSelectedElement);

    }
    @Test (expected = NoSuchFieldException.class)
    public void _1_searchElementThatRepresentsNode_NoNodsInTree() throws NoSuchFieldException
    {

        ITreeElement lastSelectedElement = new DiaryTreeElementMock.Builder<File>().children(
                null).
                userObject("NoSuchFile").
                childCount(0).build();

        MutableTreeNode lastSelectedNode = lastSelectedElement;

        ArrayList<ITreeElement> rootChildren =
                new ArrayList<ITreeElement>(
                        Arrays.asList());

        ITreeElement root = new DiaryTreeElementMock.Builder<File>().children(
                new TreeNodeChildrenEnumeration(rootChildren)).
                userObject("Root").
                childCount(3).build();

        ITreeSearcher diaryTreeSearcher = Mockito.spy(new DiaryTreeSearcher());

        assertEquals(diaryTreeSearcher.searchElementStartingFromStartNode(lastSelectedNode, root), lastSelectedElement);

    }
}