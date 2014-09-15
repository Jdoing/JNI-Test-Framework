package com.sandisk.zs.type;

public enum ContainerMode {
    BTREEMODE, HASHMODE;
    public static ContainerMode valueOf(int value) {
        switch (value) {
        case 0:
            return BTREEMODE;
        case 1:
            return HASHMODE;
        default:
            return null;
        }
    }
}
