package com.doorknocker.doorknocker.app;

/**
 * Created by nutjung on 3/15/14.
 */
public interface DormList {
    public void Create(boolean ro, boolean flip);
    public String getDorm();
    public int getFloor();
    public int getWing();
}
