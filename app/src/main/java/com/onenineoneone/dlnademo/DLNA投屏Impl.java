package com.onenineoneone.dlnademo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.LocalDevice;
import org.teleal.cling.model.meta.RemoteDevice;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.ServiceId;
import org.teleal.cling.model.types.UDAServiceId;
import org.teleal.cling.registry.DefaultRegistryListener;
import org.teleal.cling.registry.Registry;
import org.teleal.cling.registry.RegistryListener;
import org.teleal.cling.support.avtransport.callback.GetMediaInfo;
import org.teleal.cling.support.avtransport.callback.GetPositionInfo;
import org.teleal.cling.support.avtransport.callback.Pause;
import org.teleal.cling.support.avtransport.callback.Play;
import org.teleal.cling.support.avtransport.callback.Seek;
import org.teleal.cling.support.avtransport.callback.SetAVTransportURI;
import org.teleal.cling.support.avtransport.callback.Stop;
import org.teleal.cling.support.connectionmanager.callback.GetProtocolInfo;
import org.teleal.cling.support.connectionmanager.callback.PrepareForConnection;
import org.teleal.cling.support.model.MediaInfo;
import org.teleal.cling.support.model.PositionInfo;
import org.teleal.cling.support.model.ProtocolInfos;

/**
 * Created by xuenhao on 2018/4/12.
 */

//这个是类库的实现类
public class DLNA投屏Impl{

//    private Dialog listdialog;
//    private ListView devicelist;
//    private ArrayAdapter<DeviceDisplay> listAdapter;
//
//    private AndroidUpnpService upnpService;
//    private RegistryListener registryListener;
//    private ServiceConnection serviceConnection;
//    private String s="AVTransport";
//    private String s1="ConnectionManager";
//    String url="http://gcqq450f71eywn6bv7u.exp.bcevod.com/mda-hbqagik5sfq1jsai/mda-hbqagik5sfq1jsai.mp4";
//    private int deviceIndex=0;
//    private MainActivity mainActivity;
//    public DLNA投屏Impl(Context context) {
//        mainActivity = (MainActivity) context;
//    }
//
//
////    @Override
//    public void 初始化(){
//        registryListener = new BrowseRegistryListener();
//        serviceConnection = new ServiceConnection(){
//            public void onServiceConnected(ComponentName className, IBinder service) {
//                upnpService = (AndroidUpnpService) service;
//                for (Device device : upnpService.getRegistry().getDevices()) {
//                    ((BrowseRegistryListener) registryListener).deviceAdded(device);
//                }
//                upnpService.getRegistry().addListener(registryListener);
//                upnpService.getControlPoint().search();
//            }
//
//            public void onServiceDisconnected(ComponentName className) {
//                upnpService = null;
//            }
//
//        };
//    }
//
////    @Override
//    public void 搜索设备(){
//        showDialog();
//    }
//
//
////    @Override
//    public void 暂停播放(){
//        DeviceDisplay devicePlay=listAdapter.getItem(deviceIndex);
//        Device device= devicePlay.getDevice();
//        executePause(device);
//    }
//
////    @Override
//    public void 继续播放(){
//        DeviceDisplay devicePlay=listAdapter.getItem(deviceIndex);
//        Device device= devicePlay.getDevice();
//        executePlay(device);
//    }
//
////    @Override
//    public void 停止播放(){
//        DeviceDisplay devicePlay=listAdapter.getItem(deviceIndex);
//        Device device= devicePlay.getDevice();
//        executeStop(device);
//    }
//
//    public void showDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.getContext());
//        builder.setTitle("可选择设备⋯⋯");
//        final LayoutInflater inflater = LayoutInflater.from(mainActivity.getContext());
//        View v = inflater.inflate(R.layout.listview, null);
//
//        mainActivity.getContext().bindService(
//                new Intent(mainActivity.getContext(), MyUpnpService.class),
//                serviceConnection,
//                Context.BIND_AUTO_CREATE
//        );
//        devicelist = (ListView) v.findViewById(R.id.devicelist);
//        listAdapter = new ArrayAdapter<DeviceDisplay>(mainActivity.getContext(), android.R.layout.simple_list_item_1 );
//        devicelist.setAdapter(listAdapter);
//
//        builder.setView(v);
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        listdialog=builder.create();
//        listdialog.show();
//        devicelist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                deviceIndex=position;
//                //DeviceDisplay devicePlay=listAdapter.getItem(position);
//                //Device device= devicePlay.getDevice();
//                //Uri.parse(url);
//                //GetInfo(device);
//                //executeAVTransportURI(device,url);
//                //executePlay(device);
//                listdialog.dismiss();
//                DLNA选择完毕();
//            }
//        });
//    }
//
////    @Override
//    public void DLNA选择完毕()
//    {
////        EventDispatcher.dispatchEvent(this, "DLNA选择完毕");
//    }
//
////    @Override
//    public void 置投屏幕内容(String 内容地址){
//        this.url=内容地址;
//        if(listAdapter!=null){
//            if(deviceIndex<listAdapter.getCount()){
//                DeviceDisplay devicePlay=listAdapter.getItem(deviceIndex);
//                Device device= devicePlay.getDevice();
//                Uri.parse(url);
//                GetInfo(device);
//                executeAVTransportURI(device,url);
//                executePlay(device);
//
//            }
//        }
//
//    }
//
//
////    @Override
//    public void onDestroy(){
//        if(upnpService != null){
//            upnpService.getRegistry().removeListener(registryListener);
//        }
//        if(serviceConnection!=null){
//            mainActivity.getContext().unbindService(serviceConnection);
//        }
//    }
//
//    static class BrowseRegistryListener extends DefaultRegistryListener {
//
//        @Override
//        public void remoteDeviceDiscoveryStarted(Registry registry, RemoteDevice device) {
//            deviceAdded(device);
//        }
//
//        @Override
//        public void remoteDeviceDiscoveryFailed(Registry registry, final RemoteDevice device, final Exception ex) {
//            deviceRemoved(device);
//        }
//
//        @Override
//        public void remoteDeviceAdded(Registry registry, RemoteDevice device) {
//            deviceAdded(device);
//        }
//
//        @Override
//        public void remoteDeviceRemoved(Registry registry, RemoteDevice device) {
//            deviceRemoved(device);
//        }
//
//        @Override
//        public void localDeviceAdded(Registry registry, LocalDevice device) {
//            deviceAdded(device);
//        }
//
//        @Override
//        public void localDeviceRemoved(Registry registry, LocalDevice device) {
//            deviceRemoved(device);
//        }
//
//        public void deviceAdded(final Device device) {
//            mainActivity.getContext().runOnUiThread(new Runnable() {
//                public void run() {
//                    DeviceDisplay d = new DeviceDisplay(device);
//                    int position = listAdapter.getPosition(d);
//                    if (position >= 0) {
//                        listAdapter.remove(d);
//                        listAdapter.insert(d, position);
//                    } else {
//                        listAdapter.add(d);
//                    }
//                    listAdapter.notifyDataSetChanged();
//                }
//            });
//        }
//
//        public void deviceRemoved(final Device device) {
//            mainActivity.getContext().runOnUiThread(new Runnable() {
//                public void run() {
//                    listAdapter.remove(new DeviceDisplay(device));
//                }
//            });
//        }
//    }
//
//    public void executeAVTransportURI(Device device, String uri){
//
//        ServiceId AVTransportId = new UDAServiceId(s);
//        Service service = device.findService(AVTransportId);
//        ActionCallback callback = new SetAVTransportURI(service, uri){
//            @Override
//            public void failure(ActionInvocation arg0, UpnpResponse arg1,String arg2) {
//                Log.e("SetAVTransportURI","failed^^^^^^^");
//            }
//
//        };
//        upnpService.getControlPoint().execute(callback);
//
//    }
//    public void executePlay(Device device){
//        ServiceId AVTransportId = new UDAServiceId(s);
//        Service service = device.findService(AVTransportId);
//        ActionCallback playcallback = new Play(service){
//            @Override
//            public void failure(ActionInvocation arg0, UpnpResponse arg1,String arg2) {
//
//                Log.e("Play","failed^^^^^^^");
//            }
//
//        };
//        upnpService.getControlPoint().execute(playcallback);
//    }
//
//    public void executePause(Device device){
//        ServiceId AVTransportId = new UDAServiceId(s);
//        Service service = device.findService(AVTransportId);
//        ActionCallback pausecallback = new Pause(service){
//            @Override
//            public void failure(ActionInvocation arg0, UpnpResponse arg1,String arg2) {
//
//                Log.e("Play","failed^^^^^^^");
//            }
//
//        };
//        upnpService.getControlPoint().execute(pausecallback);
//    }
//
//    public void executeStop(Device device){
//        ServiceId AVTransportId = new UDAServiceId(s);
//        Service service = device.findService(AVTransportId);
//        ActionCallback stopcallback = new Stop(service){
//            @Override
//            public void failure(ActionInvocation arg0, UpnpResponse arg1,String arg2) {
//
//                Log.e("Play","failed^^^^^^^");
//            }
//
//        };
//        upnpService.getControlPoint().execute(stopcallback);
//    }
//
//    public void executeSeek(Device device,String mills){
//        ServiceId AVTransportId = new UDAServiceId(s);
//        Service service = device.findService(AVTransportId);
//        ActionCallback seekcallback = new Seek(service,mills){
//            @Override
//            public void failure(ActionInvocation arg0, UpnpResponse arg1,String arg2) {
//
//                Log.e("Play","failed^^^^^^^");
//            }
//
//        };
//        upnpService.getControlPoint().execute(seekcallback);
//    }
//
//    public void GetInfo(Device device){
//        ServiceId AVTransportId = new UDAServiceId(s1);
//        Service service = device.findService(AVTransportId);
//        ActionCallback getInfocallback=new GetProtocolInfo(service){
//            @Override
//            public void received(ActionInvocation actionInvocation, final ProtocolInfos sinkProtocolInfos, final ProtocolInfos sourceProtocolInfos) {
//                Log.v("sinkProtocolInfos",sinkProtocolInfos.toString());
//                Log.v("sourceProtocolInfos",sourceProtocolInfos.toString());
//
//                runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        视频获取成功(true);
//                    }
//
//                });
//            }
//
//            @Override
//            public void failure(ActionInvocation arg0, UpnpResponse arg1,final String arg2) {
//                // TODO Auto-generated method stub
//                Log.v("GetProtocolInfo","failed^^^^^^^");
//                mainActivity.getContext().runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        视频获取成功(false);
//                    }
//
//                });
//
//            }
//
//        };
//        upnpService.getControlPoint().execute(getInfocallback);
//    }
//
//    public void PrepareConn(Device device){
//        ServiceId AVTransportId = new UDAServiceId(s1);
//        Service service = device.findService(AVTransportId);
//        ActionCallback prepareConncallback=new PrepareForConnection(service,null,null,-1, null){
//
//            @Override
//            public void received(ActionInvocation invocation, int connectionID,int rcsID, int avTransportID) {
//                // TODO Auto-generated method stub
//                Log.v("avTransportID",Integer.toString(avTransportID));
//
//            }
//
//            @Override
//            public void failure(ActionInvocation arg0, UpnpResponse arg1,String arg2) {
//                // TODO Auto-generated method stub
//                Log.v("PrepareForConnection","failed^^^^^^^");
//
//            }
//
//        };
//        upnpService.getControlPoint().execute(prepareConncallback);
//    }
//
//    public void 置进度(String 进度){
//
//        if(listAdapter!=null){
//            if(deviceIndex<listAdapter.getCount()){
//                DeviceDisplay devicePlay=listAdapter.getItem(deviceIndex);
//                Device device= devicePlay.getDevice();
//                executeSeek(device,进度);
//
//            }
//        }
//    }
//
//    public void 获取进度(){
//
//        if(listAdapter!=null){
//            if(deviceIndex<listAdapter.getCount()){
//                DeviceDisplay devicePlay=listAdapter.getItem(deviceIndex);
//                Device device= devicePlay.getDevice();
//                GetPositionInfo(device);
//
//            }
//        }
//    }
//
////    @Override
//    public void 获取进度完毕(String 进度,String 时长,boolean 是否成功)
//    {
////        EventDispatcher.dispatchEvent(this, "获取进度完毕", 进度,时长,是否成功);
//    }
//
//
//    public void GetPositionInfo(Device device) {
//        if (device == null)
//            return;
//        ServiceId AVTransportId = new UDAServiceId(s);
//        Service service = device.findService(AVTransportId);
//        try {
//            ActionCallback getpositionInfo = new GetPositionInfo(service) {
//
//                @Override
//                public void received(ActionInvocation arg0,final PositionInfo arg1) {
//                    // TODO 自动生成的方法存根
//                    //arg1.getAbsTime();// 相对播放时长
//                    //	arg1.getRelTime();// 真实播放时长
//
//                    MainActivity.getContext().runOnUiThread(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            获取进度完毕(arg1.getRelTime(),arg1.getTrackDuration(),true);// 目前播放视频时长
//                        }
//
//                    });
//
//                }
//
//                @Override
//                public void failure(ActionInvocation arg0, UpnpResponse arg1,final String arg2) {
//                    // TODO 自动生成的方法存根
//                    MainActivity.getContext().runOnUiThread(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            获取进度完毕("",arg2,false);// 目前播放视频时长
//                        }
//
//                    });
//                }
//            };
//            upnpService.getControlPoint().execute(getpositionInfo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public void 获取视频长度(){
//
//        if(listAdapter!=null){
//            if(deviceIndex<listAdapter.getCount()){
//                DeviceDisplay devicePlay=listAdapter.getItem(deviceIndex);
//                Device device= devicePlay.getDevice();
//                executeGetMediaInfo(device);
//
//            }
//        }
//    }
//
//
////    @Override
//    public void 视频获取成功(boolean 是否成功)
//    {
////        EventDispatcher.dispatchEvent(this, "视频获取成功", 是否成功);
//    }
//
////    @Override
//    public void 获取视频长度完毕(String 进度,boolean 是否成功)
//    {
////        EventDispatcher.dispatchEvent(this, "获取视频长度完毕", 进度,是否成功);
//    }
//
//
//    public void executeGetMediaInfo(Device device) {
//        ServiceId AVTransportId = new UDAServiceId(s);
//        Service service = device.findService(AVTransportId);
//        ActionCallback seekcallback = new GetMediaInfo(service) {
//
//            @Override
//            public void received(ActionInvocation arg0, final MediaInfo arg1) {
//                // TODO 自动生成的方法存根
//                MainActivity.getContext().runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        获取视频长度完毕(arg1.getMediaDuration(),true);
//                    }
//
//                });
//            }
//
//            @Override
//            public void failure(ActionInvocation arg0, UpnpResponse arg1, final String arg2) {
//                // TODO 自动生成的方法存根
//                MainActivity.getContext().runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        获取视频长度完毕(arg2,false);
//                    }
//
//                });
//
//            }
//
//
//        };
//        upnpService.getControlPoint().execute(seekcallback);
//    }


}
