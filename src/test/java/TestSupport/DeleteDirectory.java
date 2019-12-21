package TestSupport;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile.DirectoryFile;
import org.apache.commons.io.FileUtils;
import org.junit.rules.ErrorCollector;

import java.io.File;
import java.io.IOException;

public final class DeleteDirectory {

    private DeleteDirectory(){};

    public static void deleteTestCreatedFolder(DirectoryFile directoryFile)
    {
        String name = directoryFile.getName();
        File directory = directoryFile.getFile();
        if (directory != new File("") && directory.isDirectory() && !directory.getName().equals("Organ")) {
            try {
                FileUtils.deleteDirectory(directory);
            } catch (IOException t) {
                t.printStackTrace();
            }
        }
    }

}
