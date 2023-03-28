package com.pradeep.api.user_validation_ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Temp {
    public static final String USER_INVITE = "USER_INVITE";
    public static final List<UUID> UUID_USER_INVITE = getUuidForGivenList(USER_INVITE);

    public static final String CODE_01 = "364urzzP23";
    public static final String CODE_02 = "npI8AXpUhk";
    public static final String CODE_03 = "73lFcGU0FN";
    public static final String CODE_04 = "OuVhPjXc2s";
    public static final String CODE_05 = "KDfv1Px5oG";
    public static final String CODE_06 = "L45HednAj8";
    public static final String CODE_07 = "gBpnb19vNh";
    public static final String CODE_08 = "r4SOvd2mKP";
    public static final String CODE_09 = "QNrVQ8XyFL";
    public static final String CODE_10 = "qLPtFfmFyI";
    public static final String CODE_11 = "q8QohXbir9";
    public static final String CODE_12 = "NUfe4iayXx";
    public static final String CODE_13 = "Gt0bMqm3D7";
    public static final String CODE_14 = "sZ1OLuBad3";
    public static final String CODE_15 = "mbR86bmg4w";
    public static final String CODE_16 = "2KX19kMhKD";
    public static final String CODE_17 = "maXjVxXABY";
    public static final String CODE_18 = "7BzDhjX29B";
    public static final String CODE_19 = "RVYPnJ67kP";
    public static final String CODE_20 = "OgPS2qSXAx";
    public static List<UUID> getUuidForGivenList(String shareType) {
        List<UUID> uuidShareType = new ArrayList<>();
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_01).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_02).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_03).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_04).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_05).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_06).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_07).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_08).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_09).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_10).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_11).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_12).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_13).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_14).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_15).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_16).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_17).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_18).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_19).getBytes()));
        uuidShareType.add(UUID.nameUUIDFromBytes((shareType + CODE_20).getBytes()));
        return uuidShareType;
    }
}
