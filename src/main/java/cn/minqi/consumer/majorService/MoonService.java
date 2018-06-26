package cn.minqi.consumer.majorService;

import cn.minqi.consumer.entity.Picture;
import cn.minqi.consumer.model.BaseResponse;
import cn.minqi.consumer.model.RespBase;
import cn.minqi.consumer.repository.PictureRepository;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class MoonService {

    @Autowired
    private PictureRepository pictureRepository;

    public BaseResponse queryDesc(Picture model) {
//        List<Picture> list =  pictureRepository.findByCategory(model.getCategory());
//        Long a = pictureRepository.count();
        Picture picture= pictureRepository.findById(model.getId().toString());
        log.info("查到的List：" + JSONObject.toJSONString(picture));
        return new RespBase(picture);
    }

    public BaseResponse add(Picture model) {
        Picture insert = pictureRepository.insert(model);
        log.info("插入的数据：" + JSONObject.toJSONString(insert));
        return new RespBase(insert);
    }

    public BaseResponse queryAllByPage(PageRequest pageRequest){
        return new RespBase(pictureRepository.findAll(pageRequest));
    }
}
