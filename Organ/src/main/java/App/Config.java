package App;

import GUI.Frames.LoginFrame.LoginFrame;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanel;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.DailyNoteEditorPane;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.EditorPaneContentSetter.EditorPaneContentSetter;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.EditorPaneContentSetter.ITextComponentContentSetter;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.KeyBindingSetter.DailyNoteKeyBindingSetter;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.KeyBindingSetter.IKeyBindingSetter;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.Actions.SaveBehaviour.SaveDailyNoteBehaviour;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.ETextComponentActions;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DailyNoteEditorPane.TextComponentActions.TextComponentActions;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanel;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.IDiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader.CurrentDayDiaryPageCreator.CurrentDayDiaryPageCreator;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader.DiaryTreeLoader;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader.ITreeLoader;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeModel.DiaryTreeModel;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeModel.DiaryTreeModelEventHandler.DiaryTreeModelEventHandler;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeModel.DiaryTreeModelEventHandler.ITreeModelEventHandler;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.TreeButtonPanel.Buttons.AddNodeButton;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTree;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.TreeButtonPanel.Buttons.RemoveNodeButton;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.TreeButtonPanel.TreeButtonPanel;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.SplitPaneDiary;
import GUI.Frames.OrganMainFrame.OrganEditMenu.OrganEditMenu;
import GUI.Frames.OrganMainFrame.OrganMainFrame;
import GUI.AppFramesObserverPattern.EFrames;
import GUI.Frames.OrganAppFrame;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.io.File;
import java.util.HashMap;

@Configuration
//@ComponentScan(
public class Config {

    private String filesFormat = "txt";

    @Bean
    IKeyBindingSetter dailyNoteBindingSetter() {return new DailyNoteKeyBindingSetter(textComponentActions());}
    @Bean
    ITextComponentContentSetter editorPaneContentSetter() {return new EditorPaneContentSetter();}
    @Bean
    DailyNoteEditorPane dailyNoteEditorPane() {return new DailyNoteEditorPane(textComponentActions(), editorPaneContentSetter(), dailyNoteBindingSetter());}
    @Bean
    RemoveNodeButton removeNodeButton(){return new RemoveNodeButton(diaryTree());}
    @Bean
    AddNodeButton addNodeButton(){return new AddNodeButton(diaryTree(), filesFormat);}
    @Bean
    TreeButtonPanel treeButtonPanel(){return new TreeButtonPanel(
            addNodeButton(),
            removeNodeButton());}
    @Bean
    File diaryDirectory() {
        File diariesDirectory = new File("Diary");
        if (!diariesDirectory.exists())
        {
            diariesDirectory.mkdir();
        }
        if (diariesDirectory.isDirectory()) {
            return diariesDirectory;
        }
        else
            throw new NullPointerException("Path for directory doesn't exist");
    }
    @Bean
    CurrentDayDiaryPageCreator currentDayDiaryPageCreator() {return new CurrentDayDiaryPageCreator(filesFormat, diaryDirectory());}
    @Bean
    IDiaryTreeElement root() { return new DiaryTreeElement(diaryDirectory(), filesFormat);}
    @Bean
    ITreeLoader diaryTreeLoader(){return new DiaryTreeLoader(root(), currentDayDiaryPageCreator(), filesFormat);}
    @Bean
    ITreeModelEventHandler diaryTreeModelEventHandler(){return new DiaryTreeModelEventHandler();}
    @Bean
    DiaryTreeModel diaryTreeModel(){ return  new DiaryTreeModel(root(), diaryTreeModelEventHandler());}
    @Bean
    DiaryTree diaryTree(){ return new DiaryTree(diaryTreeModel(), diaryTreeLoader(), dailyNoteEditorPane());}
    @Bean
    DiaryTreePanel diaryTreePanel() {return new DiaryTreePanel(
            diaryTree(),
            treeButtonPanel());}
    @Bean
    JScrollPane noteEditorScrollPane(){return new JScrollPane(dailyNoteEditorPane());}
    @Bean
    JScrollPane diaryTreeScrollPanel(){return new JScrollPane(
            diaryTreePanel());}
    @Bean
    SplitPaneDiary splitPaneDiary() {return new SplitPaneDiary(
            diaryTreeScrollPanel(),
            noteEditorScrollPane());}
    @Bean
    DiaryPanel diaryPanel() {return  new DiaryPanel(splitPaneDiary());};
    @Bean
    UndoManager undoManager(){return new UndoManager();}
    @Bean
    SaveDailyNoteBehaviour saveDailyNote(){return new SaveDailyNoteBehaviour();}
    @Bean
    TextComponentActions textComponentActions() {return new TextComponentActions(undoManager(), additionalTextComponentActionsList());}
    @Bean
    OrganEditMenu organEditMenu(){return new OrganEditMenu(textComponentActions());}
    @Bean
    OrganMainFrame organMainFrame() {return new OrganMainFrame(diaryPanel(), organEditMenu(), dailyNoteEditorPane());}
    @Bean
    LoginFrame loginFrame(){return new LoginFrame();}
    @Bean
    RunApp runApp() {

        HashMap<EFrames, OrganAppFrame> framesOrganAppFrameHashMap = new HashMap<>();
        framesOrganAppFrameHashMap.put(EFrames.LoginFrame, loginFrame());
        framesOrganAppFrameHashMap.put(EFrames.OrganMainFrame, organMainFrame());
        return new RunApp(framesOrganAppFrameHashMap);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    HashMap<Object, Action> additionalTextComponentActionsList()
    {
        HashMap<Object, Action> additionalTextComponentActionsList = new HashMap<Object, Action>();
        additionalTextComponentActionsList.put(ETextComponentActions.Save, saveDailyNote());

        return additionalTextComponentActionsList;
    }

}
