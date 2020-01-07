package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.EditorPaneContentSetter;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElementModel.NoteFile.NoteFile;
import org.apache.commons.io.FileUtils;

import javax.swing.text.JTextComponent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EditorPaneContentSetter implements ITextComponentContentSetter {

    public void setContent(JTextComponent textComponent, NoteFile textFile)
    {
        try {
            String data = FileUtils.readFileToString(textFile.getFile(), "utf-8");
            textComponent.setText(data);
        } catch (FileNotFoundException ex) {//For newly created Element
            textComponent.setText("");
            return;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
