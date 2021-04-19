package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.ActionRecordMapper;
import com.company.project.entity.ActionRecordEntity;
import com.company.project.service.ActionRecordService;


@Service("actionRecordService")
public class ActionRecordServiceImpl extends ServiceImpl<ActionRecordMapper, ActionRecordEntity> implements ActionRecordService {


}