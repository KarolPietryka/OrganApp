package GUI.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementDirectoriesEditor;

import DirectoryFileMock.DirectoryFileMock;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementDirectoriesEditor.DiaryTreeElementDirectoriesEditor;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile.DirectoryFile;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.IDiaryTreeElementModel;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile.NoteFile;
import ITreeElementMock.DiaryTreeElementMock;
import NoteFileMock.NoteFileMock;
import TestSupport.DeleteDirectory;
import org.apache.commons.io.FilenameUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.Mockito;

import java.io.File;
import java.nio.file.FileSystemException;
import java.nio.file.Path;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

public class DiaryTreeElementDirectoriesEditorTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    DirectoryFile directory = new DirectoryFile("C:\\Users\\PC\\Desktop\\Programy\\Organ\\OldElement", true);
    DirectoryFile parentDirectory = new DirectoryFileMock.Builder().directory(new File("C:\\Users\\PC\\Desktop\\Programy\\Organ")).build();
    String filesFormat = "txt";
    String elementNewName = "newElement";



    private NoteFile getNoteFileMock(Path path)
    {
        NoteFile noteFile = mock(NoteFile.class);
        when(noteFile.getAbsolutePath()).thenReturn(path.toString());
        when(noteFile.toPath()).thenReturn(path);
        return noteFile;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private DiaryTreeElementDirectoriesEditor renameElementAction(String elementNewName){
        DiaryTreeElementMock parentElementMock = new DiaryTreeElementMock.Builder<File>().directory(parentDirectory).build();
        NoteFile noteFile = new NoteFile(directory.getFile(),  directory.getName() ,  filesFormat);
        DiaryTreeElementMock diaryTreeElementMock = new DiaryTreeElementMock.Builder<File>().//element with new name in old directory (situation when user is changing nodeName)
                parent(parentElementMock).
                userObject(elementNewName).
                directory(directory).
                noteFile(noteFile).
                build();

        IDiaryTreeElementModel diaryTreeElementModel = Mockito.mock(IDiaryTreeElementModel.class);
        when(diaryTreeElementMock.getNoteFile()).thenReturn(noteFile);
        when(diaryTreeElementMock.getDirectoryFile()).thenReturn(directory);
        DiaryTreeElementDirectoriesEditor diaryTreeElementDirectoriesEditor = spy(new DiaryTreeElementDirectoriesEditor(filesFormat, diaryTreeElementModel));

        diaryTreeElementDirectoriesEditor.renameElement(diaryTreeElementMock);
        return diaryTreeElementDirectoriesEditor;
    }
    private DiaryTreeElementDirectoriesEditor createElementDirectoriesAction(String elementNewName) {
        IDiaryTreeElement parentElementMock = new DiaryTreeElementMock.Builder<File>().directory(parentDirectory).build();
        /*IDiaryTreeElement treeElementMock = new DiaryTreeElementMock.Builder<File>().
                parent(parentElementMock).
                userObject(elementNewName).
                build();*/


        /////
        NoteFile noteFile = new NoteFile(directory.getFile(),  directory.getName() ,  filesFormat);
        DiaryTreeElementMock diaryTreeElementMock = new DiaryTreeElementMock.Builder<File>().//element with new name in old directory (situation when user is changing nodeName)
                parent(parentElementMock).
                userObject(elementNewName).
                directory(directory).
                noteFile(noteFile).
                build();

        IDiaryTreeElementModel diaryTreeElementModel = Mockito.mock(IDiaryTreeElementModel.class);
        when(diaryTreeElementModel.getNoteFile()).thenReturn(noteFile);
        when(diaryTreeElementModel.getDirectoryFile()).thenReturn(directory);

        DiaryTreeElementDirectoriesEditor diaryTreeElementDirectoriesEditor = spy(new DiaryTreeElementDirectoriesEditor(filesFormat, diaryTreeElementModel));

        diaryTreeElementDirectoriesEditor.createElementDirectories(diaryTreeElementMock);
        return diaryTreeElementDirectoriesEditor;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void _1_renameElement_CheckingDirectoryAndNoteNameOfChangedElement() throws FileSystemException
    {
        //Creates directories(folders)

        DiaryTreeElementDirectoriesEditor treeElementDirectoriesEditor = renameElementAction(elementNewName);

        collector.checkThat(FilenameUtils.getBaseName(treeElementDirectoriesEditor.getNoteFile().getName()), equalTo(elementNewName));
        collector.checkThat(FilenameUtils.getBaseName(treeElementDirectoriesEditor.getElementDirectory().getName()), equalTo(elementNewName));

        DeleteDirectory.deleteTestCreatedFolder(treeElementDirectoriesEditor.getElementDirectory());
        DeleteDirectory.deleteTestCreatedFolder(directory);

    }
    @Test
    public void _1_renameElement_CheckIfOldDirectoryExist() throws FileSystemException
    {
        //Creates directories(folders)
        ITreeElement treeElement = new DiaryTreeElement(directory.getFile(), filesFormat);

        DiaryTreeElementDirectoriesEditor treeElementDirectoriesEditor = renameElementAction(elementNewName);

        collector.checkThat(directory.getFile().exists(), equalTo(false));

        DeleteDirectory.deleteTestCreatedFolder(treeElementDirectoriesEditor.getElementDirectory());
        DeleteDirectory.deleteTestCreatedFolder(directory);

    }
    @Test
    public void _2_createElementDirectories_CheckIfDirectoriesHaveNewNamesAndIfPathsAreCorrect()
    {
        DiaryTreeElementDirectoriesEditor treeElementDirectoriesEditor = createElementDirectoriesAction(elementNewName);

        //Check Directory and NoteFile names
        collector.checkThat(FilenameUtils.getBaseName(treeElementDirectoriesEditor.getNoteFile().getName()), equalTo(elementNewName));
        collector.checkThat(FilenameUtils.getBaseName(treeElementDirectoriesEditor.getElementDirectory().getName()), equalTo(elementNewName));
        //Check Directory and NoteFile absolutePath
        collector.checkThat(treeElementDirectoriesEditor.getElementDirectory().getAbsolutePath(), equalTo("C:\\Users\\PC\\Desktop\\Programy\\Organ\\newElement"));
        collector.checkThat(treeElementDirectoriesEditor.getNoteFile().getAbsolutePath(), equalTo("C:\\Users\\PC\\Desktop\\Programy\\Organ\\newElement\\newElement.txt"));

        DeleteDirectory.deleteTestCreatedFolder(treeElementDirectoriesEditor.getElementDirectory());
        DeleteDirectory.deleteTestCreatedFolder(directory);
    }
}