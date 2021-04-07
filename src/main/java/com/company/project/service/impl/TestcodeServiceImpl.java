package com.company.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.TestcodeMapper;
import com.company.project.entity.TestcodeEntity;
import com.company.project.service.TestcodeService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Transactional
@Service("testcodeService")
public class TestcodeServiceImpl extends ServiceImpl<TestcodeMapper, TestcodeEntity> implements TestcodeService {

    @Resource
    private TestcodeEntity testcodeEntity;

    @Resource
    private  TestcodeMapper testcodeMapper;


    @Override
    public String saveTest(TestcodeEntity vo){
        String newCode = this.getNewCode();
        vo.setTestCode(newCode);
        testcodeMapper.insert(vo);
        return String.valueOf(vo);
    }

    public String getNewCode(){
        LambdaQueryWrapper<TestcodeEntity> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.select(TestcodeEntity::getTestCode);

        List<Object> codes = testcodeMapper.selectObjs(lambdaQueryWrapper);
        AtomicReference<Integer> maxCodes = new AtomicReference<>(0);

        codes.forEach(o -> {
            String code = String.valueOf(o);
            if (code.length() >= 6) {
                Integer one = Integer.parseInt(code.substring(code.length() - 5));
                if (one > maxCodes.get()) {
                    maxCodes.set(one);
                }
            }

        });
        return padRight(maxCodes.get() + 1, 6, "0");
    }



    /**
     *
     * @param len
     * @param alexi
     * @param oriStr
     * @return
     */
    public static String padRight(int oriStr, int len, String alexi) {
        StringBuilder str = new StringBuilder();
        int strlen = String.valueOf(oriStr).length();
        if (strlen < len) {
            for (int i = 0; i < len - strlen; i++) {
                str.append(alexi);
            }
        }
        str.append(oriStr);
        return str.toString();
    }


}