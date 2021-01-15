package com.company.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.company.project.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 操作日志 Mapper
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Mapper
@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {
}