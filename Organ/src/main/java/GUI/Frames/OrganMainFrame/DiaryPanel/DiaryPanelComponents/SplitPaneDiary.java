package GUI.Frames.OrganMainFrame.DiaryPanel.DiaryPanelComponents;

import javax.swing.*;
import java.awt.*;

public class SplitPaneDiary extends JSplitPane {

    public SplitPaneDiary(JScrollPane leftComponent, JScrollPane rightComponent)
    {
        super(JSplitPane.HORIZONTAL_SPLIT);
        setPreferredSize(new Dimension(900, 600));
        setLeftComponent(leftComponent);
        setRightComponent(rightComponent);
    }
}
