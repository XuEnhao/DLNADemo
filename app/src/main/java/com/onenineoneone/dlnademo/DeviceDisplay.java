package com.onenineoneone.dlnademo;

import org.teleal.cling.model.meta.Device;
import java.net.URLDecoder;

public class DeviceDisplay { 
    Device device; 
 
    public DeviceDisplay(Device device) { 
        this.device = device; 
    } 
 
    public Device getDevice() { 
        return device; 
    } 
 
    @Override 
    public boolean equals(Object o) { 
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false; 
        DeviceDisplay that = (DeviceDisplay) o; 
        return device.equals(that.device); 
    } 
 
    @Override 
    public int hashCode() { 
        return device.hashCode(); 
    } 
 
    @Override 
    public String toString() { 
    	String display;
		if (device.getDetails().getFriendlyName() != null){
			display = device.getDetails().getFriendlyName();
			try{
			   display = URLDecoder.decode(display, "UTF-8");  
			   display = URLDecoder.decode(display, "iso-8859-1");  
			}catch(Exception e){
			   e.printStackTrace(); 
			}
		}else{
			display = device.getDisplayString();
			try{
			   display = URLDecoder.decode(display, "UTF-8");  
			   display = URLDecoder.decode(display, "iso-8859-1");  
			}catch(Exception e){
			   e.printStackTrace(); 
			}
        }
		return device.isFullyHydrated() ? display : display + " *";
    } 
} 


