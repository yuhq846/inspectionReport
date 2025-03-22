package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/wx/jsapi/{appid}")
public class WeixinMPController extends BaseController {
    @Autowired
    private  WxMpService wxService;


    @GetMapping("/getJsapiTicket")
    public AjaxResult getJsapiTicket(@PathVariable String appid, @RequestParam String url) throws WxErrorException {
        final WxJsapiSignature jsapiSignature = this.wxService.switchoverTo(appid).createJsapiSignature(url);
        System.out.println(jsapiSignature);
        //return this.wxService.getJsapiTicket(true);

        return success(jsapiSignature);
    }
    /*@GetMapping("/signature")
    public Map<String, String> getSignature(@PathVariable String appid,@RequestParam String url) {
        String nonceStr = UUID.randomUUID().toString().replace("-", "");
        long timestamp = System.currentTimeMillis() / 1000;

        String string1 = "jsapi_ticket=" + ticket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;

        String signature = DigestUtils.sha1Hex(string1); // 计算 SHA1 签名

        Map<String, String> map = new HashMap<>();
        map.put("appId", APP_ID);
        map.put("nonceStr", nonceStr);
        map.put("timestamp", String.valueOf(timestamp));
        map.put("signature", signature);
        return map;
    }*/
}
