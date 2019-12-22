package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile.DirectoryFile;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile.NoteFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;

public interface IDiaryTreeElement extends ITreeElement{

    public boolean containsDirectory(String wantedFileAbsolutePath);
    public File[] getFileListing();
    public NoteFile getNoteFile();
    public DirectoryFile getDirectoryFile();
    public int getNumberOfElementsInListing();
    public boolean isNewlyCreatedElement();
    public void updateDirectories();
    public void saveTextInNoteFile(String textToSave);
}
