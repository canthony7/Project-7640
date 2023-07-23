package com.hkbu.project7640.controller;

import com.hkbu.project7640.dto.ResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chet
 * @date 12/4/2023 4:14 pm
 * @description 异常处理控制器
 */
@CrossOrigin(origins = "*")
@RestController
public class FilterErrorController {

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean unauthorized() {
        return new ResponseBean(401, "Unauthorized", null);
    }
}
