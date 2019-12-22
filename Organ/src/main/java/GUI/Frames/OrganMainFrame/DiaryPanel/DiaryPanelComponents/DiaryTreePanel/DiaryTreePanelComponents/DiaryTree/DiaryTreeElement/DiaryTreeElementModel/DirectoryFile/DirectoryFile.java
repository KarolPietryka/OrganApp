package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Path;

public class DirectoryFile{

    protected File file;

    public DirectoryFile(File parentDirectory, String directoryName, boolean createFileDirectory) {
        file = new File(parentDirectory.getAbsolutePath() + "\\" + directoryName);
        if (createFileDirectory)
        createDirectory();
    }
    public DirectoryFile(String directoryFileAbsolutePath, boolean createFileDirectory) {
        file = new File(directoryFileAbsolutePath);
        if (createFileDirectory)
        createDirectory();
    }


    public void setDirectoryFile(File directoryFile) {
        file = directoryFile;
    }
    public File getFile(){
        return file;
    }
    public String getAbsolutePath(){return file.getAbsolutePath();}
    public String getName(){return file.getName();}
    public Path toPath(){return file.toPath();}

    private void createDirectory() {
        if (!FilenameUtils.getBaseName(file.getAbsolutePath()).isEmpty() && !file.exists()) {
            file.mkdirs();
        }
    }
}
