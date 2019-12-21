package App;

import GUI.AppFramesObserverPattern.EFrames;
import GUI.Frames.OrganAppFrame;
import GUI.AppFramesObserverPattern.IAppFramesObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RunApp implements IAppFramesObserver {

    private OrganAppFrame startFrame;
    private HashMap<EFrames, OrganAppFrame> framesOrganAppFrameHashMap;

    @Autowired
    public RunApp(HashMap<EFrames, OrganAppFrame> framesOrganAppFrameHashMap) {
        this.framesOrganAppFrameHashMap = framesOrganAppFrameHashMap;
        this.startFrame     = framesOrganAppFrameHashMap.get(EFrames.LoginFrame);

        for(Map.Entry<EFrames, OrganAppFrame> entry : framesOrganAppFrameHashMap.entrySet()) {
            OrganAppFrame frame = entry.getValue();
            frame.addObserver(this);
        }
    }

    public void run()
    {
        startFrame.startFrame();
    }

    @Override
    public void update(EFrames nextFrameToRun)
    {
        OrganAppFrame frameToRun = framesOrganAppFrameHashMap.get(nextFrameToRun);
        frameToRun.startFrame();
    }
}