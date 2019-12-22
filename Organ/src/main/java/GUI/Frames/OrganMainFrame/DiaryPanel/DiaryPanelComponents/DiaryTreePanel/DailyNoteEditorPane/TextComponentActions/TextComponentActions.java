package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane.TextComponentActions;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class TextComponentActions {

    private UndoManager undoManager;
    private UndoAction undo;
    private RedoAction redo;
    private OrganFrameUndoableEditListener organFrameUndoableEditListener;
    private HashMap<Object, Action> actions;


    public TextComponentActions(UndoManager undoManager)
    {
        this.undoManager = undoManager;
        actions = createActionTable(new JTextPane());

        organFrameUndoableEditListener = new OrganFrameUndoableEditListener();
        undo = new UndoAction();
        redo = new RedoAction();
    }

    public OrganFrameUndoableEditListener getOrganFrameUndoableEditListener() {
        return organFrameUndoableEditListener;
    }
    public UndoAction getUndo() {
        return undo;
    }
    public RedoAction getRedo() {
        return redo;
    }
    public HashMap<Object, Action> getActions() {
        return actions;
    }

    private HashMap<Object, Action> createActionTable(JTextComponent textComponent) {
        HashMap<Object, Action> actions = new HashMap<Object, Action>();
        Action[] actionsArray = textComponent.getActions();
        for (int i = 0; i < actionsArray.length; i++) {
            Action a = actionsArray[i];
            actions.put(a.getValue(Action.NAME), a);
        }
        return actions;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class OrganFrameUndoableEditListener
            implements UndoableEditListener {
        public void undoableEditHappened(UndoableEditEvent e) {
            //Remember the edit and update the menus.
            undoManager.addEdit(e.getEdit());
            undo.updateUndoState();
            redo.updateRedoState();
        }
    }


    public class UndoAction extends AbstractAction{

        public UndoAction() {
            super("Undo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undoManager.undo();
            } catch (CannotUndoException ex) {
                System.out.println("Unable to undo: " + ex);
                ex.printStackTrace();
            }
            updateUndoState();
            redo.updateRedoState();
        }

        public void updateUndoState() {
            if (undoManager.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, undoManager.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }
    }


    public class RedoAction extends AbstractAction{


        public RedoAction() {
            super("Redo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undoManager.redo();
            } catch (CannotRedoException ex) {
                System.out.println("Unable to redo: " + ex);
                ex.printStackTrace();
            }
            updateRedoState();
            undo.updateUndoState();
        }

        public void updateRedoState() {
            if (undoManager.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, undoManager.getRedoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }
}
