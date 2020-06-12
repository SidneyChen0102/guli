package com.sidney.eduservice.client;

import com.sidney.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Sidney
 * @data 2020/5/29  8:26
 * @description
 */

@Component
public class VodFileDegradeFeignClient implements VodClient {
    //出错之后会执行
    @Override
    public R removeAlyVide(String id) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除视频出错");
    }
}
