package cn.minqi.consumer.majorService;


import cn.minqi.consumer.constant.Constant;
import cn.minqi.consumer.entity.User;
import cn.minqi.consumer.model.BaseResponse;
import cn.minqi.consumer.repository.PictureRepository;
import cn.minqi.consumer.repository.UserRepository;
import cn.minqi.consumer.util.CommonEnum;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Service
public class EmailServiceImpl {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    public BaseResponse sendSimpleMail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("357444224@qq.com");
        message.setTo(user.getUserEmail());
        message.setSubject(Constant.MAIL_SUBJECT);
        message.setText(Constant.MAIL_CONTENT);

        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//            helper.setFrom("357444224@qq.com");
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(content, true);

//            String filePath = "";
//            FileSystemResource file = new FileSystemResource(new File(filePath));
//            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
//            helper.addAttachment(fileName, file);

            mailSender.send(message);
            log.info("邮件已经发送。");
            User insert = userRepository.insert(user);
            log.info("插入的User：" + JSONObject.toJSONString(insert));
            return CommonEnum.CODE_1000.getRespBase();
        } catch (Exception e) {
            log.error("发送邮件时发生异常！", e);
            return CommonEnum.CODE_1010.getRespBase();
        }

    }
}