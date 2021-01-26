package com.nit.dao;

import com.nit.model.MockData;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Component
public interface MockDataMapper extends Mapper<MockData>, MySqlMapper<MockData> {
}
