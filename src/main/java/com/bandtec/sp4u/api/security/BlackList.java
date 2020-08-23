package com.bandtec.sp4u.api.security;

import java.util.ArrayList;
import java.util.List;

public class BlackList {
    private static List list = new ArrayList<String>();

    public void addToList(String token){
        list.add(token);
    }

    public boolean isOnList(String token){
        return !list.contains(token);
    }
}
