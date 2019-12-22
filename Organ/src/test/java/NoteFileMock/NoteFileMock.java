package NoteFileMock;

import DirectoryFileMock.DirectoryFileMock;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile.NoteFile;

import java.io.File;
import java.nio.file.Paths;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NoteFileMock extends NoteFile {

    public NoteFileMock(String noteFileDirectory) {
        super(noteFileDirectory);
    }

    public static final class Builder {
        File noteFile;

        NoteFileMock noteFileMock = mock(NoteFileMock.class);

        public Builder noteFile(File noteFile) {

            this.noteFile = noteFile;
            when(noteFileMock.getAbsolutePath()).thenReturn(noteFile.getAbsolutePath());
            when(noteFileMock.getName()).thenReturn(noteFile.getName());
            when(noteFileMock.toPath()).thenReturn(Paths.get(noteFile.getAbsolutePath()));
            return this;
        }

        public NoteFileMock build() {
            noteFileMock.file = this.noteFile;
            return noteFileMock;
        }
    }
}

