package com.guyulei.emarket.http;

import com.guyulei.emarket.utils.IOUtils;
import com.guyulei.emarket.utils.StringUtils;
import com.guyulei.emarket.utils.UIUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by A on 2017/8/25.
 */

public abstract class BaseProtocol<T> {

    public static final String baseUrl = "http://127.0.0.1:8090/";

    //请求数据
    public T getData(int index) {
        //判断 是否 有缓存 有就加载 缓存
        String result = getCache(index);
        if (StringUtils.isEmpty(result)) {
            //缓存为空 调用网络
            getDataFromServer(index);
        }else {
            T t = parseResultData(result);
            return t;
        }
        return null;
    }

    //网络获取数据
    private void getDataFromServer(final int index) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(baseUrl + getUrlKey() + "?index=" + index + getUrlParams()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null) {
                    String result = response.body().string();//网络返回的结果
                    //写缓存
                    if (!StringUtils.isEmpty(result)) {
                        setCache(index, result);
                        getData(index);
                    }
                }
            }
        });
    }

    public abstract String getUrlKey();

    public abstract String getUrlParams();

    public abstract T parseResultData(String result);

    // 写缓存 url 为文件名  result为文件内容
    public void setCache(int index, String resultNet) {
        //定义缓存文件
        File cacheDir = UIUtils.getContext().getCacheDir();
        File file = new File(cacheDir, getUrlKey() + "?index=" + index + getUrlParams());
        //写缓存
        FileWriter fileWriter = null;
        try {
            long deadline = System.currentTimeMillis() + 30 * 60 * 1000;//半小时 有效期
            fileWriter = new FileWriter(file);
            fileWriter.write(deadline + "\n");//写入缓存 有效期
            fileWriter.write(resultNet);
            fileWriter.flush();//清空缓存区数据
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fileWriter);
        }
    }

    //读缓存
    public String getCache(int index) {
        //缓存文件
        File cacheDir = UIUtils.getContext().getCacheDir();
        File file = new File(cacheDir, getUrlKey() + "?index=" + index + getUrlParams());
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                String readLine = bufferedReader.readLine();
                long deadtime = Long.parseLong(readLine);
                if (System.currentTimeMillis() < deadtime) {
                    //缓存有效
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    return stringBuffer.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(bufferedReader);
            }
        }
        return null;
    }
}
