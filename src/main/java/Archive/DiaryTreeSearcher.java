package Archive;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import javax.swing.tree.MutableTreeNode;
import java.util.Enumeration;


public class DiaryTreeSearcher implements ITreeSearcher{//No static because it's hard to test static class. KISS

    private ITreeElement searchedElement;

    public ITreeElement searchElementStartingFromStartNode(
            MutableTreeNode lastSelectedNode,
            ITreeElement searchingStartNode) throws NoSuchFieldException
    {
        searchedElement = null;
        search(lastSelectedNode, searchingStartNode);
        return getSearchedElement();
    }

    private void search(MutableTreeNode lastSelectedNode,
                        ITreeElement searchingStartNode)
    {
        ITreeElement currentCheckedElement = searchingStartNode;
        String currentCheckedElementName = currentCheckedElement.getUserObject().toString();
        if (currentCheckedElement.equals(lastSelectedNode))
        {
            searchedElement = currentCheckedElement;
            //TODO return after find?
        }

        if (currentCheckedElement.getChildCount() > 0)
        {
            Enumeration childrenEnu = currentCheckedElement.children();
            while (childrenEnu.hasMoreElements()) {
                ITreeElement child = (ITreeElement) childrenEnu.nextElement();
                search(lastSelectedNode, child);
            }
        }
    }

    /*private void search(ITreeElement lastSelectedTreeElement,
                        ITreeElement searchingStartTreeElement)
    {
        ITreeElement currentCheckedElement = searchingStartNode;
        String currentCheckedElementName = currentCheckedElement.getUserObject().toString();
        if (currentCheckedElement.equals(lastSelectedNode))
        {
            searchedElement = currentCheckedElement;
            //TODO return after find?
        }

        if (currentCheckedElement.getChildCount() > 0)
        {
            Enumeration childrenEnu = currentCheckedElement.children();
            while (childrenEnu.hasMoreElements()) {
                ITreeElement child = (ITreeElement) childrenEnu.nextElement();
                search(lastSelectedNode, child);
            }
        }
    }*/

    private ITreeElement getSearchedElement() throws NoSuchFieldException{
        if (searchedElement == null)
        {
            throw new NoSuchFieldException("There is no such file");
        }
        return searchedElement;
    }
}
