package cn.minqi.consumer.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class HtmlController {


    @RequestMapping(value = "/picture")
    public String picture() {
        log.info("picture入口");
        return "huaban";
    }
}
