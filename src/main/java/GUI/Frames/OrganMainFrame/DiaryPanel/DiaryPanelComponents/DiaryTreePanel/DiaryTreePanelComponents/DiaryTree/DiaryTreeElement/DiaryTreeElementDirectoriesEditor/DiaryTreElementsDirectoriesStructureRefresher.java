package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementDirectoriesEditor;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;

import java.io.File;
import java.util.Enumeration;

public class DiaryTreElementsDirectoriesStructureRefresher {

    public static void refresh(IDiaryTreeElement elementToRefresh)
    {
        elementToRefresh.updateDirectories();
        if (elementToRefresh.getChildCount() > 0)
        {
            Enumeration childrenEnu = elementToRefresh.children();
            while (childrenEnu.hasMoreElements()) {
                IDiaryTreeElement child = (IDiaryTreeElement) childrenEnu.nextElement();
                refresh(child);
            }
        }
    }
}
