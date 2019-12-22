package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader.CurrentDayDiaryPageCreator.ICurrentDayDiaryPageCreator;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.IDiaryTree;

import java.io.File;

public class DiaryTreeLoader implements ITreeLoader{

    private IDiaryTreeElement diaryTreeRootElement;
    private ICurrentDayDiaryPageCreator currentDayDiaryPageCreator;
    private String filesFormat;
    private IDiaryTree diaryTree;

    public DiaryTreeLoader(IDiaryTreeElement diaryTreeRoot,
                           ICurrentDayDiaryPageCreator currentDayDiaryPageCreator,
                           String filesFormat)
    {
        this.diaryTreeRootElement = diaryTreeRoot;
        this.currentDayDiaryPageCreator = currentDayDiaryPageCreator;
        this.filesFormat = filesFormat;
    }

    public void loadTree(IDiaryTree diaryTree) throws NoSuchFieldException
    {
        this.diaryTree = diaryTree;
        int numberOfElementsInListing = diaryTreeRootElement.getNumberOfElementsInListing();
        if (numberOfElementsInListing == 0) {
            insertCurrentDayDiaryPageIntoTree();
        }
        else {
            loadTreeNodesFromElement(diaryTreeRootElement);
            //Check if today's diary already exist in directory.
            if (!diaryTreeRootElement.containsDirectory(currentDayDiaryPageCreator.getPageAbsolutePath()))
            {
                insertCurrentDayDiaryPageIntoTree();
            }
        }
        this.diaryTree = null;
    }

    private void insertCurrentDayDiaryPageIntoTree()
    {
        try {
            ITreeElement currentDayDiaryPage = currentDayDiaryPageCreator.createCurrentDayDiaryPage();
            diaryTree.insertIntoTree(currentDayDiaryPage, diaryTreeRootElement);
        }
        catch (NoSuchFieldException exc) {
            exc.getMessage();
            return;
        }
    }
    private void loadTreeNodesFromElement(IDiaryTreeElement parentDirectory)
    {
        File [] filesListingInParentDirectory = parentDirectory.getFileListing();
        for (File file : filesListingInParentDirectory)
        {
            //If file(element of tree) is directory then call loadTreeNodesFromDirectory method in this directory(element of tree)
            if (file.isDirectory()) {
                File directory = file;
                IDiaryTreeElement subDirectory = new DiaryTreeElement(directory, filesFormat);
                loadTreeNodesFromElement(subDirectory);
                diaryTree.insertIntoTree(subDirectory, parentDirectory);
            }
        }
    }

}
