package GUI.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader.CurrentDayDiaryPageCreator.ICurrentDayDiaryPageCreator;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader.DiaryTreeLoader;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.IDiaryTree;
import ITreeElementMock.DiaryTreeElementMock;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.mockito.Mockito.*;

public class DiaryTreeLoaderTest {

    String filesFormat = "txt";

    @Test
    public void _1_loadTree_NoElementsInListing() throws NoSuchFieldException{

        String fileFormat = "txt";
        int numberOfFilesInListing = 0;

        IDiaryTreeElement diaryTreeRoot = new DiaryTreeElementMock.Builder<File>().numberOfElementsInListing(numberOfFilesInListing)
                .containsFileWithPath(true).build();

        ICurrentDayDiaryPageCreator currentDayDiaryPageCreator = Mockito.mock(ICurrentDayDiaryPageCreator.class);
        IDiaryTree diaryTree = Mockito.mock(IDiaryTree.class);

        DiaryTreeLoader diaryTreeLoader = Mockito.spy(new DiaryTreeLoader(diaryTreeRoot, currentDayDiaryPageCreator,fileFormat));

        diaryTreeLoader.loadTree(diaryTree);

        verify(currentDayDiaryPageCreator, times(1)).createCurrentDayDiaryPage();
    }
    @Test
    public void _1_loadTree_ThereAreElementsInListing() throws NoSuchFieldException{

        String fileFormat = "txt";
        int numberOfFilesInListing = 2;

        File[] filesList = {new File("1"), new File("2")};
        IDiaryTreeElement diaryTreeRoot = new DiaryTreeElementMock.Builder<File>().numberOfElementsInListing(numberOfFilesInListing).
                fileListing(filesList).containsFileWithPath(true).build();

        ICurrentDayDiaryPageCreator currentDayDiaryPageCreator = Mockito.mock(ICurrentDayDiaryPageCreator.class);
        IDiaryTree diaryTree = Mockito.mock(IDiaryTree.class);

        DiaryTreeLoader diaryTreeLoader = Mockito.spy(new DiaryTreeLoader(diaryTreeRoot, currentDayDiaryPageCreator,fileFormat));

        diaryTreeLoader.loadTree(diaryTree);

        verify(currentDayDiaryPageCreator, times(0)).createCurrentDayDiaryPage();
        verify(diaryTreeRoot, times(1)).getFileListing();
    }
}