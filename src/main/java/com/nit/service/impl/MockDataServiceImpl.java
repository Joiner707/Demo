package com.nit.service.impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nit.dao.MockDataMapper;
import com.nit.model.MockData;
import com.nit.service.MockDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MockDataServiceImpl implements MockDataService {


    @Autowired
    MockDataMapper mockDataMapper;

    @Override
    public int insertMockData(String name, String uuid, String conditions, String result) throws Exception {

        if(conditions == null && result == null) {
            if(name == null) {
                throw new Exception("名称不能为空");
            }else {
                MockData mockData1 = MockData.builder().uuid(uuid).type(0).build();
                List<MockData> query_result =mockDataMapper.select(mockData1);
                if(query_result != null && query_result.size() != 0){
                    throw new Exception("该uuid已经存在");
                }else {
                    DateTime now = new DateTime(System.currentTimeMillis() + 8 * 60 * 60 * 1000);
                    MockData mockData = MockData.builder().name(name).uuid(uuid).type(0).createTime(now).updateTime(now).build();
                    return mockDataMapper.insert(mockData);
                }
            }
        }else {
            MockData mockData = MockData.builder().uuid(uuid).type(0).build();
            List<MockData> query_result =mockDataMapper.select(mockData);
            if(query_result == null || query_result.size()== 0) {
                throw new Exception("该uuid不存在数据");
            }
            if (name != null && !name.equalsIgnoreCase(query_result.get(0).getName())) {
                throw new Exception("参数名称不符合，name应是："+query_result.get(0).getName());
            }else {
                DateTime now = new DateTime(System.currentTimeMillis() + 8 * 60 * 60 * 1000);
                MockData insert_mockData = MockData.builder().conditions(conditions).name(query_result.get(0).getName()).result(result).uuid(uuid).type(1).createTime(now).updateTime(now).build();
                return mockDataMapper.insert(insert_mockData);
            }

        }

    }

    @Override
    public void deleteMockData(Integer id) throws Exception {
        mockDataMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateMockData(Integer id, String name, String uuid, String conditions, String result) throws Exception {
        MockData mockData = mockDataMapper.selectByPrimaryKey(id) ;
        if(mockData == null){
            throw new Exception("记录不存在");
        }
        if(uuid != null) {
            mockData.setUuid(uuid);
        }
        if(result != null) {
            mockData.setResult(result);
        }

        if(conditions != null) {
            mockData.setConditions(conditions);
        }
        if(name != null) {
            mockData.setName(name);
        }

        DateTime now = new DateTime(System.currentTimeMillis()+8*60*60*1000);
        mockData.setUpdateTime(now);
        mockDataMapper.updateByPrimaryKey(mockData);
    }

    @Override
    public PageInfo<MockData> queryAll(String name, String uuid,Integer currentPage, Integer pageSize, String orderBy) throws Exception {
        currentPage = currentPage !=null ? currentPage : 1;
        pageSize = pageSize != null ? pageSize : 50;
        orderBy = orderBy != null ? orderBy : "id desc";

        if(!"id asc".equalsIgnoreCase(orderBy) && !"id desc".equalsIgnoreCase(orderBy)){
            log.error("orderBy 参数有误,orderby:{}",orderBy);
            throw new Exception("orderBy 参数有误");
        }
        PageHelper.startPage(currentPage, pageSize, orderBy);
        MockData mockData = null;
        List<MockData> result = new ArrayList<>();
        if(uuid == null && name != null){
            //待添加模糊查询
            Example example = new Example(MockData.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("name","%"+name+"%");
            result = mockDataMapper.selectByExample(example);
        }else{
            mockData = MockData.builder().name(name).uuid(uuid).type(0).build();
            result = mockDataMapper.select(mockData);
        }
        PageInfo<MockData> pageResult = new PageInfo<>(result);
        return pageResult;
    }

    @Override
    public int queryCountByAll(String name, String uuid, Integer currentPage, Integer pageSize, String orderBy) throws Exception {
        MockData mockData = null;
        List<MockData> result = new ArrayList<>();
        if(uuid == null && name != null){
            //待添加模糊查询
            Example example = new Example(MockData.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("name","%"+name+"%");
            result = mockDataMapper.selectByExample(example);
        }else{
            mockData = MockData.builder().name(name).uuid(uuid).type(0).build();
            result = mockDataMapper.select(mockData);
        }
        return result.size();
    }

    @Override
    public List<MockData> queryNameAndUUID() throws Exception {
        return null;
    }

    @Override
    public PageInfo<MockData> queryResultByNameAndUUID(String name, String uuid,Integer currentPage, Integer pageSize, String orderBy) throws Exception {
        currentPage = currentPage !=null ? currentPage : 1;
        pageSize = pageSize != null ? pageSize : 50;
        orderBy = orderBy != null ? orderBy : "id desc";

        if(!"id asc".equalsIgnoreCase(orderBy) && !"id desc".equalsIgnoreCase(orderBy)){
            log.error("orderBy 参数有误,orderby:{}",orderBy);
            throw new Exception("orderBy 参数有误");
        }
        PageHelper.startPage(currentPage, pageSize, orderBy);
        MockData mockData = MockData.builder().uuid(uuid).name(name).type(1).build();
        List<MockData> result = mockDataMapper.select(mockData);

        PageInfo<MockData> pageResult = new PageInfo<>(result);
        return pageResult;
    }
}
