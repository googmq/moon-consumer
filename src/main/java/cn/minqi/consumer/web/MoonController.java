package cn.minqi.consumer.web;

import cn.minqi.consumer.constant.Constant;
import cn.minqi.consumer.entity.Picture;
import cn.minqi.consumer.entity.User;
import cn.minqi.consumer.majorService.EmailServiceImpl;
import cn.minqi.consumer.majorService.MoonService;
import cn.minqi.consumer.model.BaseResponse;
import cn.minqi.consumer.model.request.PicturePageParam;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class MoonController {

    private MoonService moonService;

    private EmailServiceImpl emailService;

    @RequestMapping(value = "/queryDesc", method = RequestMethod.POST)
    public BaseResponse queryTitle(@RequestBody Picture model) {
        log.info("queryDesc入口");
        log.info("queryDesc入参 :" + JSONObject.toJSONString(model));
        BaseResponse response = moonService.queryDesc(model);
        log.info("queryDesc返回 :" + JSONObject.toJSONString(response));
        return response;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse add(@RequestBody Picture model) {
        log.info("add入口");
        log.info("add入参 :" + JSONObject.toJSONString(model));
        BaseResponse response = moonService.add(model);
        log.info("add返回 :" + JSONObject.toJSONString(response));
        return response;
    }

    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    public BaseResponse findPage(@RequestBody PicturePageParam request) {
        log.info("findPage入口");
        PageRequest pageRequest = new PageRequest(request.getPageNumber()-1,request.getPageSize());

        log.info("findPage入参 :" + JSONObject.toJSONString(request));
        BaseResponse response = moonService.queryAllByPage(pageRequest);
        log.info("findPage返回 :" + JSONObject.toJSONString(response));
        return response;
    }

    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public BaseResponse mail(@RequestBody User user) {
        log.info("mail入口");
        log.info("mail入参 :" + JSONObject.toJSONString(user));
        return emailService.sendSimpleMail(user);
    }
}
