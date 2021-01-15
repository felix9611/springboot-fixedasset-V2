package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.LocationMapper;
import com.company.project.entity.LocationEntity;
import com.company.project.service.LocationService;


@Service("locationService")
public class LocationServiceImpl extends ServiceImpl<LocationMapper, LocationEntity> implements LocationService {


}