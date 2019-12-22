package GUI.Frames.OrganMainFrame.OrganEditMenu;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane.TextComponentActions.TextComponentActions;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.util.HashMap;

public class OrganEditMenu extends JMenu {

    private HashMap<Object, Action> actions;
    private TextComponentActions textComponentActions;

    public OrganEditMenu(TextComponentActions textComponentActions)
    {
        super("Edit");
        this.textComponentActions = textComponentActions;
        this.actions = textComponentActions.getActions();
        buildMenu();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void buildMenu()
    {
        this.add(textComponentActions.getUndo());
        this.add(textComponentActions.getRedo());
        this.addSeparator();
        this.add(getActionByName(DefaultEditorKit.cutAction));
        this.add(getActionByName(DefaultEditorKit.copyAction));
        this.add(getActionByName(DefaultEditorKit.pasteAction));
        this.addSeparator();
        this.add(getActionByName(DefaultEditorKit.selectAllAction));
    }

    private Action getActionByName(String name) {
        return actions.get(name);
    }
}
