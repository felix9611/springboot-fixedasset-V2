package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.TestcodeEntity;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2021-01-17 17:06:23
 */
public interface TestcodeService extends IService<TestcodeEntity> {

    String saveTest(TestcodeEntity vo);
}

