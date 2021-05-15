package com.company.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.company.project.entity.Place2Entity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 *
 * @author wenbin
 * @email *****@mail.com
 * @date 2020-11-15 18:53:25
 */
@Service
public interface Place2Service extends IService<Place2Entity> {

    void updateActive(Place2Entity entity);

    List<Place2Entity> selectNameAndCode(Place2Entity place2Entity);
}

