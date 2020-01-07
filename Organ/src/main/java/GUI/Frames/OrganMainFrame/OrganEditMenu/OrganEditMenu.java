package GUI.Frames.OrganMainFrame.OrganEditMenu;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.ETextComponentActions;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.TextComponentActions;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.util.HashMap;

public class OrganEditMenu extends JMenu {

    private TextComponentActions textComponentActions;

    public OrganEditMenu(TextComponentActions textComponentActions)
    {
        super("Edit");
        this.textComponentActions = textComponentActions;
        buildMenu();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void buildMenu()
    {
        this.add(textComponentActions.getActionByName(ETextComponentActions.Undo.toString()));
        this.add(textComponentActions.getActionByName(ETextComponentActions.Redo.toString()));
        this.addSeparator();
        this.add(textComponentActions.getActionByName(DefaultEditorKit.cutAction));
        this.add(textComponentActions.getActionByName(DefaultEditorKit.copyAction));
        this.add(textComponentActions.getActionByName(DefaultEditorKit.pasteAction));
        this.addSeparator();
        this.add(textComponentActions.getActionByName(DefaultEditorKit.selectAllAction));
    }
}
