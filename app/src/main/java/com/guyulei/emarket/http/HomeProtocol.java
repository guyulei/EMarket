package com.guyulei.emarket.http;

import com.guyulei.emarket.bean.AppInfo;
import com.guyulei.emarket.utils.LogUtils;
import com.guyulei.emarket.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by A on 2017/8/25.
 */

public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {
    @Override
    public String getUrlKey() {
        return "home";
    }

    @Override
    public String getUrlParams() {
        return "";//没有参数
    }

    @Override
    public ArrayList<AppInfo> parseResultData(String result) {
        if (!StringUtils.isEmpty(result)) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray list = jsonObject.getJSONArray("list");
                ArrayList<AppInfo> appInfos = new ArrayList<>();
                for (int i = 0; i < list.length(); i++) {
                    JSONObject jsonObject1 = list.getJSONObject(i);
                    AppInfo appInfo = new AppInfo();
                    appInfo.setDes(jsonObject1.getString("des"));
                    appInfo.setDownloadUrl(jsonObject1.getString("downloadUrl"));
                    appInfo.setIconUrl(jsonObject1.getString("iconUrl"));
                    appInfo.setId(jsonObject1.getInt("id"));
                    appInfo.setName(jsonObject1.getString("name"));
                    appInfo.setPackageName(jsonObject1.getString("packageName"));
                    appInfo.setSize(jsonObject1.getInt("size"));
                    appInfo.setStars(jsonObject1.getInt("stars"));
                    appInfos.add(appInfo);
                }
                //
                ArrayList<String> pics = new ArrayList<>();
                JSONArray picture = jsonObject.getJSONArray("picture");
                for (int i = 0; i < picture.length(); i++) {
                    String pic = picture.getString(i);
                    pics.add(pic);
                }
                return appInfos;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            LogUtils.e("result == null");
        }
        return null;
    }
}
