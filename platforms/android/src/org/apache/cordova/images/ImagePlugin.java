package org.apache.cordova.images;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchong on 16/4/1.
 */
public class ImagePlugin extends CordovaPlugin {
    CallbackContext callbackContext;
    ViewPager viewPager;
    List<String> listUrl=new ArrayList<String>();

    @Override
    public boolean execute(String action, final JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext=callbackContext;
        if ("showImages".equals(action)){
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONArray jsonArray=args.getJSONArray(0);
                        for(int i=0;i<jsonArray.length();i++){
                            Log.e("x", "" + jsonArray.get(i));
                            listUrl.add(jsonArray.getString(i));
                        }
                        showImage(listUrl);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        return true;
    }


    private void showImage(List<String> listUrl) {
        final CordovaInterface cordovaInterface = this.cordova;

            viewPager = new HackyViewPager(cordovaInterface.getActivity());
            viewPager.setAdapter(new SampleAdapter(cordovaInterface.getActivity(), listUrl));
            try {
                cordovaInterface.getActivity().runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                int backGroundColor = Color.parseColor("#000000");
                                viewPager.setBackgroundColor(backGroundColor);
                                cordovaInterface.getActivity().addContentView(viewPager, layoutParams);


                            }
                        }

                );
            } catch (Exception e) {
                Log.e("xx", e.toString());
                e.printStackTrace();
            }



    }
}
