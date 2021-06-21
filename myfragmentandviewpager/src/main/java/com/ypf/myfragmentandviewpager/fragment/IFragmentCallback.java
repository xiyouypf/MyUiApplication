package com.ypf.myfragmentandviewpager.fragment;

public interface IFragmentCallback {
    void sendMsgToActivity(String msg);

    String getMsgFromActivity();
}
