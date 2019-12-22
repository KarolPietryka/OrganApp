package DirectoryFileMock;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile.DirectoryFile;

import java.io.File;
import java.nio.file.Paths;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DirectoryFileMock extends DirectoryFile{


    private DirectoryFileMock(String pathName) {
        super(pathName, false);
    }

    public static final class Builder
    {
        File directory;

        DirectoryFileMock directoryFileMock = mock(DirectoryFileMock.class);

        public Builder directory(File directory)
        {
            this.directory = directory;
            when(directoryFileMock.getAbsolutePath()).thenReturn(directory.getAbsolutePath());
            when(directoryFileMock.getName()).thenReturn(directory.getName());
            when(directoryFileMock.toPath()).thenReturn(Paths.get(directory.getAbsolutePath()));
            when(directoryFileMock.getFile()).thenReturn(directory);
            return this;
        }
        public DirectoryFileMock build()
        {
            directoryFileMock.file = this.directory;
            return directoryFileMock;
        }
    }

}
