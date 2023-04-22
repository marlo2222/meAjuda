package com.meAjuda.integration.config;

import com.meAjuda.integration.base.GlobalParameters;
import org.apache.http.client.config.RequestConfig;
import org.testng.annotations.BeforeMethod;

public abstract class BaseConfig {

    public BaseConfig(){
        new GlobalParameters();
    }

    @BeforeMethod
    public void configuration(){
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .build();
    }
}
