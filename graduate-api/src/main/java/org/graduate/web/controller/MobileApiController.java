package org.graduate.web.controller;

import org.graduate.common.annotation.Anonymous;
import org.graduate.system.domain.BTeacher;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.graduate.common.core.controller.BaseController;
import org.graduate.common.core.page.TableDataInfo;

@RestController
@RequestMapping("/api")
public class MobileApiController extends BaseController
{
//    @Anonymous  该注解取消权限控制
    @GetMapping("/list")
    public TableDataInfo list(BTeacher bTeacher)
    {

        return getDataTable(null);
    }


}
