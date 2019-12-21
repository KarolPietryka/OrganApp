package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementDirectoriesEditor.DiaryTreeElementDirectoriesEditor;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementDirectoriesEditor.ITreeElementDirectoriesEditor;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DiaryTreeElementModel;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.IDiaryTreeElementModel;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile.DirectoryFile;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile.NoteFile;
import org.apache.commons.io.FilenameUtils;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;

public class DiaryTreeElement extends DefaultMutableTreeNode implements IDiaryTreeElement {


    private String fileFormat;
    private IDiaryTreeElementModel diaryTreeElementModel;
    private ITreeElementDirectoriesEditor diaryTreeElementDirectoriesEditor;

    public DiaryTreeElement(File elementDirectoryFile,
                            String filesFormat)
    {
        super(elementDirectoryFile.getName());
        this.fileFormat = filesFormat;
        this.diaryTreeElementModel = new DiaryTreeElementModel(elementDirectoryFile, filesFormat);
        this.diaryTreeElementDirectoriesEditor = new DiaryTreeElementDirectoriesEditor(filesFormat, diaryTreeElementModel);
    }


    public boolean containsDirectory(String wantedFileAbsolutePath) { return diaryTreeElementModel.containsDirectory(wantedFileAbsolutePath); }
    public File[] getFileListing(){return diaryTreeElementModel.getFileListing();}
    public DirectoryFile getDirectoryFile(){return diaryTreeElementModel.getDirectoryFile();}
    public NoteFile getNoteFile(){return diaryTreeElementModel.getNoteFile();}
    public int getNumberOfElementsInListing(){return diaryTreeElementModel.getNumberOfElementsInListing();}
    public void saveTextInNoteFile(String textToSave) { diaryTreeElementModel.saveTextInNoteFile(textToSave); }
    public void deleteElement() throws IOException{ diaryTreeElementModel.deleteElement(); }
    public void renameElement(){ diaryTreeElementDirectoriesEditor.renameElement(this); }
    public boolean isNewlyCreatedElement() {
        boolean isNewlyCreatedElement = false;
        //If newlyCreated then userObject.getName() is not "" but NoteFile is ""
        String noteFileName = FilenameUtils.getBaseName(getNoteFile().getName());
        if (noteFileName.isEmpty())
        {
            isNewlyCreatedElement = true;
        }
        return isNewlyCreatedElement;
    }
    public void resetElement(){
        diaryTreeElementDirectoriesEditor.createElementDirectories(this);
        DirectoryFile elementDirectory = diaryTreeElementDirectoriesEditor.getElementDirectory();
        NoteFile noteFile = diaryTreeElementDirectoriesEditor.getNoteFile();
        diaryTreeElementModel.setDirectoryFile(elementDirectory.getFile());
        diaryTreeElementModel.setNoteFile(noteFile.getFile());
    }
    public void updateDirectories() {
        IDiaryTreeElement parent = getParent();
        diaryTreeElementModel.setDirectoryFile(new File(parent.getDirectoryFile().getAbsolutePath() + "\\" + getUserObject().toString()));
        diaryTreeElementModel.setNoteFile(new File(getDirectoryFile().getAbsolutePath() + "\\" + getDirectoryFile().getName() + "." + fileFormat));
    }
    public IDiaryTreeElement getParent() {
        return (IDiaryTreeElement)super.getParent();
    }
}
