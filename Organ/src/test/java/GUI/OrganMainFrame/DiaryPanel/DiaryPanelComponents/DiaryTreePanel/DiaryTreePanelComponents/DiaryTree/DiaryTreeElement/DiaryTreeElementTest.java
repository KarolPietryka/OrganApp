package GUI.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement;

import DirectoryFileMock.DirectoryFileMock;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile.DirectoryFile;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import ITreeElementMock.DiaryTreeElementMock;
import TestSupport.DeleteDirectory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.io.File;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class DiaryTreeElementTest {

    DirectoryFile directory = new DirectoryFileMock.Builder().directory(new File("C:\\Users\\PC\\Desktop\\Programy\\Organ\\Dir")).build();
    String filesFormat = "txt";


    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void _1_isNewlyCreatedElement_NoEmptyNoteFileName_isNotNewlyCreated()
    {
        String noteName = "noEmpty";

        IDiaryTreeElement diaryTreeElement = spy(new DiaryTreeElement(directory.getFile(), filesFormat));

        collector.checkThat(false, equalTo(diaryTreeElement.isNewlyCreatedElement()));

        DeleteDirectory.deleteTestCreatedFolder(diaryTreeElement.getDirectoryFile());

    }
    @Test
    public void _1_isNewlyCreatedElement_EmptyNoteFileNameButSpecified_isNewlyCreated()
    {
        File directory = new File("");

        IDiaryTreeElement diaryTreeElement = spy(new DiaryTreeElement(directory, filesFormat));

        collector.checkThat(true, equalTo(diaryTreeElement.isNewlyCreatedElement()));

        DeleteDirectory.deleteTestCreatedFolder(diaryTreeElement.getDirectoryFile());
    }

    @Test
    public void _2_updateDirectories_ChangeNameOfParentAndCheckIfInChildNodeItWillBeUpdate()
    {

        DirectoryFile parentDirectory = new DirectoryFileMock.Builder().directory(new File("C:\\Users\\PC\\Desktop\\Programy\\Organ\\NewDir")).build();
        DirectoryFile directory = new DirectoryFileMock.Builder().directory(new File("C:\\Users\\PC\\Desktop\\Programy\\Organ\\OldDir\\SubDir")).build();

        IDiaryTreeElement parentElement = new DiaryTreeElementMock.Builder<File>().directory(parentDirectory).build();

        IDiaryTreeElement diaryTreeElement = spy(new DiaryTreeElement(directory.getFile(), filesFormat));
        doReturn(parentElement).when(diaryTreeElement).getParent();

        diaryTreeElement.updateDirectories();

        collector.checkThat(diaryTreeElement.getDirectoryFile().getAbsolutePath(), equalTo("C:\\Users\\PC\\Desktop\\Programy\\Organ\\NewDir\\SubDir"));
        collector.checkThat(diaryTreeElement.getNoteFile().getAbsolutePath(), equalTo("C:\\Users\\PC\\Desktop\\Programy\\Organ\\NewDir\\SubDir\\SubDir.txt"));

        DeleteDirectory.deleteTestCreatedFolder(diaryTreeElement.getDirectoryFile());
        DeleteDirectory.deleteTestCreatedFolder(new DirectoryFileMock.Builder().directory(new File("C:\\Users\\PC\\Desktop\\Programy\\Organ\\OldDir")).build());
    }

}