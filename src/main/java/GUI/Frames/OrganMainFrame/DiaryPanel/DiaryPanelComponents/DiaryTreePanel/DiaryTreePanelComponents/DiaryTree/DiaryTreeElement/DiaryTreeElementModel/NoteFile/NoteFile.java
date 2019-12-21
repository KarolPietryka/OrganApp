package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class NoteFile{

    protected File file;

    public NoteFile(File elementDirectory, String noteName, String filesFormat)
    {
        file = new File(elementDirectory.getAbsolutePath() + "\\"  + noteName + "." + filesFormat);

        if (!noteName.isEmpty() && !file.exists())
        {
            createNoteFile();
        }
    }
    public NoteFile(String noteFileDirectory)
    {
        file = new File(noteFileDirectory);
    }


    public void setNoteFile(File noteFile){
        file = noteFile;
    }
    public File getFile(){
        return file;
    }
    public String getAbsolutePath(){return file.getAbsolutePath();}
    public Path toPath(){return file.toPath();}
    public String getName(){return file.getName();}

    private void createNoteFile() {
        Charset charset = Charset.forName("US-ASCII");
        try
        {
            BufferedWriter writer = Files.newBufferedWriter(file.toPath(), charset);
            writer.close();
        }
        catch (IOException x) {
            x.printStackTrace();
        }
    }
}
