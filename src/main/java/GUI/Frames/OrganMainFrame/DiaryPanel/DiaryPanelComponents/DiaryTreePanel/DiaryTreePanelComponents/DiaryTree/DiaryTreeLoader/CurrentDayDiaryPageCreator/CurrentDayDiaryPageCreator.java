package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeLoader.CurrentDayDiaryPageCreator;

import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.DiaryTreeElement;
import GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents.DiaryTreePanel.DiaryTreePanelComponents.DiaryTree.DiaryTreeElement.ITreeElement;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentDayDiaryPageCreator implements ICurrentDayDiaryPageCreator{

    String currentDayDiaryPageAbsolutePath;
    String todayDate;
    String filesFormat;

    public CurrentDayDiaryPageCreator(String filesFormat, File diaryDirectory)
    {
        this.filesFormat = filesFormat;

        Calendar today = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        todayDate = format.format(today.getTime());

        this.currentDayDiaryPageAbsolutePath = diaryDirectory.getAbsolutePath() + "\\" + todayDate;
    }

    public ITreeElement createCurrentDayDiaryPage()
    {
        File diaryPageDirectory = new File(currentDayDiaryPageAbsolutePath);

        return new DiaryTreeElement(diaryPageDirectory, filesFormat);

    }

    public String getPageAbsolutePath() {
        return currentDayDiaryPageAbsolutePath;
    }


}
