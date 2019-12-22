package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementDirectoriesEditor;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile.DirectoryFile;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.IDiaryTreeElementModel;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile.NoteFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DiaryTreeElementDirectoriesEditor implements ITreeElementDirectoriesEditor{

    private DirectoryFile directory;
    private NoteFile noteFile;

    private String filesFormat;

    public DiaryTreeElementDirectoriesEditor(String filesFormat, IDiaryTreeElementModel diaryTreeElementModel)
    {
        this.filesFormat = filesFormat;
        this.directory = diaryTreeElementModel.getDirectoryFile();
        this.noteFile = diaryTreeElementModel.getNoteFile();
    }

    public void renameElement(IDiaryTreeElement diaryTreeElement)
    {
        //Create new directory with new name
        IDiaryTreeElement parentElement = (IDiaryTreeElement)diaryTreeElement.getParent();
        DirectoryFile newDirectory = new DirectoryFile(parentElement.getDirectoryFile().getAbsolutePath() + "\\" + diaryTreeElement.getUserObject().toString(), false);
        //New NoteFile to the same old directory
        //Directory is Old and nodeName is changed (user changes it by edition node in the JTree)
        NoteFile newNote = new NoteFile(diaryTreeElement.getDirectoryFile().getFile() + "\\" + diaryTreeElement.getUserObject().toString() + "." + filesFormat);
        if (!newDirectory.getFile().exists() && !newNote.getFile().exists()) {//Conditions for Files.move method

            Path sourceNote = diaryTreeElement.getNoteFile().toPath();
            Path sourceDirectory = diaryTreeElement.getDirectoryFile().toPath();
            try {
                Files.move(sourceNote, sourceNote.resolveSibling(newNote.getFile().getAbsolutePath()));
                Files.move(sourceDirectory, sourceDirectory.resolveSibling(newDirectory.getFile().getAbsolutePath()));
            }
            catch (IOException exc) {
                exc.printStackTrace();
            }
        }
        directory = newDirectory;
        noteFile = newNote;
        DiaryTreElementsDirectoriesStructureRefresher.refresh(diaryTreeElement);

    }
    public DirectoryFile getElementDirectory(){return directory;}
    public NoteFile getNoteFile(){return noteFile;}
}
