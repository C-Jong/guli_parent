import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.vod.util.AliyunVodSDKUtils;
import com.atguigu.vod.util.ConstantPropertiesUtil;
import org.junit.Test;

public class Test01 {
        @Test
        public void getVideoPlayAuth() throws Exception {

            //获取阿里云存储相关常量
            String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

            //初始化
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient("LTAI5tL8qjmmtG7ZW6Yzkntr", "VjrkBv9NIoT4mbhrqSisszNg1Jwi3G");

            //请求
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId("be50b11b482e40ccaab111fdebb532f4");

            //响应
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);

            //得到播放凭证
            String playAuth = response.getPlayAuth();
            System.out.println(playAuth);


        }


}
