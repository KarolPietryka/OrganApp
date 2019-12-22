package ITreeElementMock;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile.DirectoryFile;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile.NoteFile;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;
import org.mockito.Mockito;

import javax.swing.tree.DefaultMutableTreeNode;

import java.io.File;
import java.util.Enumeration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DiaryTreeElementMock<T> extends DefaultMutableTreeNode implements IDiaryTreeElement {

    private boolean containsFileWithPath;
    private File[] fileListing;
    private DirectoryFile directory;
    private NoteFile noteFile;
    private int numberOfElementsInListing;
    private int childCount;
    private Object userObject;
    private boolean isNewlyCreatedElement;
    private ITreeElement parent;


    public boolean containsDirectory(String wantedFileAbsolutePath){return containsFileWithPath;}
    public File[] getFileListing(){return fileListing;}
    public DirectoryFile getDirectoryFile(){return directory;}
    public NoteFile getNoteFile(){return noteFile;}
    public int getNumberOfElementsInListing(){return numberOfElementsInListing;}
    public Enumeration children(){return children.elements();}
    public int getChildCount(){return childCount;}
    public Object getUserObject(){return userObject;}
    public void deleteElement(){}
    public boolean isNewlyCreatedElement(){return isNewlyCreatedElement;}
    public void resetElement(){}
    public void renameElement(){}
    public void updateDirectories(){}
    public void saveTextInNoteFile(String textToSave){}
    public ITreeElement getParent(){return parent;}



    private DiaryTreeElementMock(){};//private constructor to avert create of DiaryTreeElementMock

    public static final class Builder<T>
    {
        private boolean containsFileWithPath;
        private File[] fileListing;
        private DirectoryFile directory;
        private NoteFile noteFile;
        private int numberOfElementsInListing;
        private Enumeration children;
        private int childCount;
        private Object userObject;
        private boolean isNewlyCreatedElement;
        private ITreeElement parent;


        DiaryTreeElementMock diaryTreeElementMock = Mockito.mock(DiaryTreeElementMock.class);

        public Builder containsFileWithPath(boolean containsFileWithPath) {
            this.containsFileWithPath = containsFileWithPath;
            when(diaryTreeElementMock.containsDirectory(any())).thenReturn(this.containsFileWithPath);
            return this;
        }
        public Builder fileListing (File[] fileListing){
            this.fileListing = fileListing;
            when(diaryTreeElementMock.getFileListing()).thenReturn(this.fileListing);
            return this;
        }
        public Builder directory(DirectoryFile directory) {
            this.directory = directory;
            when(diaryTreeElementMock.getDirectoryFile()).thenReturn(this.directory);
            return this;
        }
        public Builder noteFile(NoteFile noteFile) {
            this.noteFile = noteFile;
            when(diaryTreeElementMock.getNoteFile()).thenReturn(this.noteFile);
            return this;
        }
        public Builder numberOfElementsInListing(int numberOfElementsInListing) {
            this.numberOfElementsInListing = numberOfElementsInListing;
            when(diaryTreeElementMock.getNumberOfElementsInListing()).thenReturn(this.numberOfElementsInListing);
            return this;
        }
        public Builder children(Enumeration children) {
            this.children = children;
            when(diaryTreeElementMock.children()).thenReturn(this.children);
            return this;
        }
        public Builder childCount(int childCount) {
            this.childCount = childCount;
            when(diaryTreeElementMock.getChildCount()).thenReturn(this.childCount);
            return this;
        }
        public Builder userObject(Object userObject) {
            this.userObject = userObject;
            when(diaryTreeElementMock.getUserObject()).thenReturn(this.userObject);
            return this;
        }
        public Builder isNewlyCreatedElement(boolean isNewlyCreatedElement) {
            this.isNewlyCreatedElement = isNewlyCreatedElement;
            when(diaryTreeElementMock.isNewlyCreatedElement()).thenReturn(this.isNewlyCreatedElement);
            return this;
        }
        public Builder parent(ITreeElement parentElement) {
            when(diaryTreeElementMock.getParent()).thenReturn(parentElement);
            return this;
        }


        public DiaryTreeElementMock<T> build()
        {
            diaryTreeElementMock.containsFileWithPath        = this.containsFileWithPath;
            diaryTreeElementMock.fileListing                 = this.fileListing;
            diaryTreeElementMock.directory                   = this.directory;
        diaryTreeElementMock.noteFile                        = this.noteFile;
            diaryTreeElementMock.numberOfElementsInListing   = this.numberOfElementsInListing;
            diaryTreeElementMock.childCount                  = this.childCount;
            diaryTreeElementMock.userObject                  = this.userObject;
            diaryTreeElementMock.isNewlyCreatedElement       = this.isNewlyCreatedElement;
            diaryTreeElementMock.parent                      = this.parent;

            return this.diaryTreeElementMock;
        }
    }


}
