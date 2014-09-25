package com.joust.codalot.util;

public class ExitHelperImpl implements ExitHelper {

    private int exitStatus;

    @Override
    public void exit(int status){
        exitStatus = status;
        System.exit(status);
    }

    @Override
    public int getExitStatus() {
        return getExitStatus();
    }
}
