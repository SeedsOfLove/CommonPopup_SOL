package com.bluewater.commonpopuplib.Wheel.lib;

public class OnItemSelectedRunnable implements Runnable
{
    final WheelView loopView;

    OnItemSelectedRunnable(WheelView loopview)
    {
        loopView = loopview;
    }

    @Override
    public final void run()
    {
        loopView.onItemSelectedListener.onItemSelected(loopView.getCurrentItem());
    }
}
