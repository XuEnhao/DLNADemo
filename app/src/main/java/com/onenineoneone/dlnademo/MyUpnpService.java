package com.onenineoneone.dlnademo;

import org.teleal.cling.android.AndroidUpnpServiceConfiguration;
import org.teleal.cling.android.AndroidUpnpServiceImpl;
import org.teleal.cling.model.types.ServiceType;
import org.teleal.cling.model.types.UDAServiceType;

import android.net.wifi.WifiManager;

public class MyUpnpService extends AndroidUpnpServiceImpl{
	
	public AndroidUpnpServiceConfiguration createConfiguration(WifiManager wifiManager) {
        return new AndroidUpnpServiceConfiguration(wifiManager) {

            
        public ServiceType[] getExclusiveServiceTypes() {
                return new ServiceType[] {
                        new UDAServiceType("AVTransport")
                                    ,new UDAServiceType("ConnectionManager")
                };
            }

        };
    }

}
