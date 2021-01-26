package com.nit.service;

import com.github.pagehelper.PageInfo;
import com.nit.model.MockData;

import java.util.List;

public interface MockDataService {

    int insertMockData(String name, String uuid, String conditions, String result) throws Exception;

    void deleteMockData(Integer id) throws Exception;

    void updateMockData(Integer id,String name, String uuid, String conditions, String result) throws Exception;

    PageInfo<MockData> queryAll(String name, String uuid, Integer currentPage, Integer pageSize, String orderBy) throws Exception;

    int queryCountByAll(String name, String uuid,Integer currentPage, Integer pageSize, String orderBy) throws Exception;

    List<MockData> queryNameAndUUID() throws Exception;

    PageInfo<MockData> queryResultByNameAndUUID(String name, String uuid,Integer currentPage, Integer pageSize, String orderBy) throws Exception;

}
