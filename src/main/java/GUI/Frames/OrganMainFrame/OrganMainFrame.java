package GUI.Frames.OrganMainFrame;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanel;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DailyNoteEditorPane.DailyNoteEditorPane;
import GUI.Frames.OrganAppFrame;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class OrganMainFrame extends OrganAppFrame {

    private DiaryPanel diaryPanel;
    private DailyNoteEditorPane e;
    private JMenu editMenu;

    public OrganMainFrame(DiaryPanel diaryPanel,
                          JMenu editMenu,
                          DailyNoteEditorPane e) {
        super();
        this.diaryPanel = diaryPanel;
        this.e          = e;
        this.editMenu = editMenu;
        this.getContentPane().add(diaryPanel);

        setMenuBar();
    }


    @Override
    public void updateObservers() {//TODO: nextFrame after main frame if needed
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setMenuBar()
    {
        JMenuBar mb = new JMenuBar();
        mb.add(editMenu);
        setJMenuBar(mb);
    }
}