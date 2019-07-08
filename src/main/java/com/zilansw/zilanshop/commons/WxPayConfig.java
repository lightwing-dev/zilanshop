package com.zilansw.zilanshop.commons;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

/**
 * 微信支付数据设置
 */
public class WxPayConfig implements WXPayConfig {
    @Override
    public String getAppID() {
        return null;
    }

    @Override
    public String getMchID() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }

    public String getAppSecret()
    {
        return null;
    }
}
