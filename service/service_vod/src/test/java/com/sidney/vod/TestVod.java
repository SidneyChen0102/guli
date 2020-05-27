package com.sidney.vod;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @author Sidney
 * @data 2020/5/26  0:47
 * @description
 */
public class TestVod {
    public static void main(String[] args) throws ClientException {


    }

    public static void getPlayAuth() throws ClientException {
        //根据视频id获取视频播放凭证

        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4GEFghJRPyKy7bjwAwGE","m3WfEKCkNfkg6VC8iJVNMGlfSf0sfT");
        //创建获取视频凭证requst 和 response
        GetVideoPlayAuthRequest request= new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        //向requst设置视频id值
        request.setVideoId("b849fdc7847842389596c0fd606166fc");

        //调用初始化对象的方法得到凭证
        response = client.getAcsResponse(request);
        System.out.println("playauth:"+response.getPlayAuth());
    }


    //根据视频id获取视频播放地址
    public static void getPlayUrl() throws ClientException {
        //1.根据视频的id获取视频的地址
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI4GEFghJRPyKy7bjwAwGE","m3WfEKCkNfkg6VC8iJVNMGlfSf0sfT");


        //创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向requst对象设置视频id
        request.setVideoId("b849fdc7847842389596c0fd606166fc");

        //调用初始化对象里面的方法传递request。获取数据
        response = client.getAcsResponse(request);


        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
