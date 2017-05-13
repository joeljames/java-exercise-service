package com.java.exercise.service.utils;

public enum SystemExitReturnValue {
    SUCCESS(0), 
    FAILURE(-1);

    private int returnCode;

    private SystemExitReturnValue(int returnCode) {
        this.returnCode = returnCode;

    }

    public int getReturnCode() {
        return this.returnCode;
    }

}
