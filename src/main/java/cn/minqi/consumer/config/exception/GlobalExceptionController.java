package cn.minqi.consumer.config.exception;

import cn.minqi.consumer.bo.base.RespBase;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * web全局http请求错误处理
 *
 * @author minqi
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
@Slf4j
public class GlobalExceptionController extends AbstractErrorController {

    @Value("${server.error.path:${error.path:/error}}")
    private String errorPath = "/error";

    public GlobalExceptionController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    /**
     * 处理接口访问错误，例如404等
     *
     * @param req 请求
     * @return JSON错误信息
     */
    @RequestMapping
    @ResponseBody
    public ResponseEntity<RespBase> error(HttpServletRequest req) {
        HttpStatus status = getStatus(req);
        log.error("请求[{}]失败，http状态: {}", req.getRequestURI(), status);

        RespBase respBase = new RespBase<>(RespBase.ERROR, "请求异常:" + status);
        return new ResponseEntity<>(respBase, status);
    }

    /**
     * 拦截接口异常消息，统一返回JSON错误信息
     *
     * @param req http请求
     * @param e   平台业务异常
     * @return JSON错误信息
     */
    @ExceptionHandler(value = {ServiceException.class})
    @ResponseStatus(code = HttpStatus.OK)
    public RespBase<?> serviceErrorHandler(HttpServletRequest req, Exception e) {
        log.error("请求[{}]失败: {}", req.getRequestURI(), e.getMessage());
        return new RespBase<>(e);
    }


    /**
     * 拦截接口异常消息，统一返回JSON错误信息
     *
     * @param req http请求
     * @param e   未知系统异常
     * @return JSON错误信息
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.OK)
    public RespBase<?> defaultErrorHandler(HttpServletRequest req, Exception e) {
        log.error("请求[{}]失败: {}", req.getRequestURI(), e.getMessage(), e);
        return new RespBase<>(e);
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
