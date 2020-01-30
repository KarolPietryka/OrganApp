package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.DirectoryFile.DirectoryFile;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile.NoteFile;
import org.apache.commons.io.FileUtils;

import javax.xml.crypto.Data;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryTreeElementModel implements IDiaryTreeElementModel{

    private DirectoryFile elementDirectory;
    private NoteFile noteFile;
    private Date noteDate;

    public DiaryTreeElementModel(File elementDirectoryFile,
                                 String filesFormat)
    {
        this.elementDirectory = new DirectoryFile(elementDirectoryFile.getAbsolutePath(), true);
        this.noteFile = new NoteFile(elementDirectoryFile, elementDirectoryFile.getName(), filesFormat);
        setNoteDateFromString(elementDirectoryFile.getName());
    }

    public DirectoryFile getDirectoryFile(){return elementDirectory;}
    public NoteFile getNoteFile(){return noteFile;}
    public Date getNoteDate(){return noteDate;}
    public void setDirectoryFile(File elementDirectory){
        this.elementDirectory.setDirectoryFile(elementDirectory);
        setNoteDateFromString(elementDirectory.getName());
    }
    public void setNoteFile(File noteFile){
        this.noteFile.setNoteFile(noteFile);
    }

    public boolean containsDirectory(String wantedFileAbsolutePath) {
        File[] filesListingInDirectory = getDirectoryFile().getFile().listFiles();

        boolean containFile = false;
        for(File file : filesListingInDirectory)
        {
            if ((file.getAbsolutePath().equals(wantedFileAbsolutePath)) && file.isDirectory())
            {
                containFile = true;
            }
        }
        return containFile;
    }
    public File[] getFileListing(){return getDirectoryFile().getFile().listFiles();}
    public int getNumberOfElementsInListing(){return getDirectoryFile().getFile().listFiles().length;}
    public void deleteElement() throws IOException {

        FileUtils.deleteDirectory(getDirectoryFile().getFile());
    }
    public void saveTextInNoteFile(String textToSave) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(getNoteFile().getAbsolutePath()), "utf-8"));
            writer.write(textToSave);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }

    private void setNoteDate(Date date){
        this.noteDate = date;
    }

    /**
     * Always refreshed with directory
     * @param date data in format yyyy-mm-dd
     */
    public void setNoteDateFromString(String date){
        try {
            setNoteDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        }
        catch(java.text.ParseException exception) {
            setNoteDate(new Date(2000,01,01));
        }
    }



}
