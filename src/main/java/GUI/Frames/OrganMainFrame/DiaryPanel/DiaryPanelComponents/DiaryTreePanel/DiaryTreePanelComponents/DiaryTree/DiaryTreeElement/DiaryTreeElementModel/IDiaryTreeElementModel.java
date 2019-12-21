package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile.DirectoryFile;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile.NoteFile;

import java.io.*;

public interface IDiaryTreeElementModel {

    public DirectoryFile getDirectoryFile();
    public NoteFile getNoteFile();
    public void setDirectoryFile(File elementDirectory);
    public void setNoteFile(File noteFile);

    public boolean containsDirectory(String wantedFileAbsolutePath);
    public File[] getFileListing();
    public int getNumberOfElementsInListing();
    public void deleteElement() throws IOException ;
    public void saveTextInNoteFile(String textToSave) ;
}
