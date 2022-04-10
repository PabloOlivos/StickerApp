package com.stickermaker.app.services;

public interface CallBackBilling {
    void onPurchase();
    void onNotPurchase();
    void onNotLogin();
}
