package System;

public class DelayThread implements Runnable{

    int delayInMillis;
    public DelayThread(int millis) {
        this.delayInMillis = millis;
    }
    private void sleep()
    {
        try{
            Thread.sleep(delayInMillis);
        }
        catch (InterruptedException exc)
        {
            exc.printStackTrace();
        }
    }
    public void run()
    {
        sleep();
    }
}
