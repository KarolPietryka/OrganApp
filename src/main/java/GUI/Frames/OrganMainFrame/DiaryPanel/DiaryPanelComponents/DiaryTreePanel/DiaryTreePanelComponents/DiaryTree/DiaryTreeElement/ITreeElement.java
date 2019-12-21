package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement;


import javax.swing.tree.MutableTreeNode;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;

public interface ITreeElement extends MutableTreeNode {
    public void deleteElement() throws IOException;
    public void resetElement();
    public void renameElement() throws FileSystemException;
    public ITreeElement getParent();
    public Object getUserObject();
}
