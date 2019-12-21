package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane.KeyBindingSetter;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane.TextComponentActions.TextComponentActions;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.KeyEvent;

public class DailyNoteKeyBindingSetter implements IKeyBindingSetter{

    private TextComponentActions textComponentActions;

    public DailyNoteKeyBindingSetter(TextComponentActions textComponentActions)
    {
        this.textComponentActions = textComponentActions;
    }


    public void addBindings(JComponent dailyNoteEditorPane) {

        InputMap inputMap = dailyNoteEditorPane.getInputMap();
        ActionMap actionMap = dailyNoteEditorPane.getActionMap();
        //Ctrl-b to go backward one character
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK);
        inputMap.put(key, DefaultEditorKit.backwardAction);
        //Ctrl-f to go forward one character
        key = KeyStroke.getKeyStroke(KeyEvent.VK_F, Event.CTRL_MASK);
        inputMap.put(key, DefaultEditorKit.forwardAction);
        //Ctrl-p to go up one line
        key = KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK);
        inputMap.put(key, DefaultEditorKit.upAction);
        //Ctrl-n to go down one line
        key = KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK);
        inputMap.put(key, DefaultEditorKit.downAction);
        //Ctrl-z to undo
        key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Event.CTRL_MASK);
        inputMap.put(key, "Undo");
        actionMap.put("Undo", textComponentActions.getUndo());
        //Ctrl-y to redo
        key = KeyStroke.getKeyStroke(KeyEvent.VK_Y, Event.CTRL_MASK);
        inputMap.put(key, "Redo");
        actionMap.put("Redo", textComponentActions.getRedo());
    }
}
