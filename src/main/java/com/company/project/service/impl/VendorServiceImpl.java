package com.company.project.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.company.project.mapper.VendorMapper;
import com.company.project.entity.VendorEntity;
import com.company.project.service.VendorService;


@Service("vendorService")
public class VendorServiceImpl extends ServiceImpl<VendorMapper, VendorEntity> implements VendorService {


}