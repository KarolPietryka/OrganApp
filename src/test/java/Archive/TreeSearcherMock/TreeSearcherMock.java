package Archive.TreeSearcherMock;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import Archive.ITreeSearcher;
import org.mockito.Mockito;

import javax.swing.tree.MutableTreeNode;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TreeSearcherMock implements ITreeSearcher {

    private ITreeElement searchedElement;

    public ITreeElement searchElementStartingFromStartNode(
            MutableTreeNode lastSelectedNode,
            ITreeElement searchingStartNode) throws NoSuchFieldException
    { return searchedElement;}

    public static final class Builder<T>
    {
        private ITreeElement searchedElement;

        public Builder searchedElement(ITreeElement searchedElement)
        {
            this.searchedElement = searchedElement;
            return this;
        }

        public TreeSearcherMock build() throws NoSuchFieldException
        {
            TreeSearcherMock treeSearcherMock = Mockito.mock(TreeSearcherMock.class);
            when(treeSearcherMock.searchElementStartingFromStartNode(any(), any())).thenReturn(this.searchedElement);
            treeSearcherMock.searchedElement = this.searchedElement;

            return treeSearcherMock;

        }
    }
}
