package com.nit.controller;

import com.nit.vo.MockDataVO;
import com.nit.vo.MockNameAndUuidVO;
import com.nit.model.MockData;
import com.nit.service.MockDataService;
import com.nit.util.Response;
import com.nit.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/mock")
public class MockDataController {

    @Autowired
    MockDataService mockDataService;

    @PostMapping("/add")
    public Response insert(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "uuid", required = true) String uuid,
            @RequestParam(value = "conditions", required = false) String conditions,
            @RequestParam(value = "result", required = false) String result
            ){
        Integer id = null;
        try {
           id =  mockDataService.insertMockData(name, uuid, conditions, result);
        }catch (Exception e){
            return new Response(ResultCode.GLOBAL_PROJECT_SYSTEM_ERROR,e.getMessage());
        }
        return new Response(ResultCode.GLOBAL_SUCCESSFUL,id,"新增成功");
    }

    @PostMapping("/delete")
    public Response insert(
            @RequestParam(value = "id", required = true) Integer id
    ){
        try {
            mockDataService.deleteMockData(id);
        }catch (Exception e){
            return new Response(ResultCode.GLOBAL_PROJECT_SYSTEM_ERROR,e.getMessage());
        }
        return new Response(ResultCode.GLOBAL_SUCCESSFUL,id,"删除成功");
    }

    @PostMapping("/queryName")
    public Response queryName(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "uuid", required = false) String uuid,
            @RequestParam(value = "currentPage", required = false) Integer currentPage,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "orderBy", required = false) String orderBy
            ){
        List<MockNameAndUuidVO> result =  new ArrayList<>();
        int count = 0;
        try{
            count  = mockDataService.queryCountByAll(name, uuid,currentPage,pageSize,orderBy);
        }catch (Exception e){
            return new Response(ResultCode.GLOBAL_PROJECT_SYSTEM_ERROR,e.getMessage());
        }
        if(count == 0){
            return new Response(ResultCode.GLOBAL_PROJECT_SYSTEM_ERROR,"查完数据");
        }
        try {
            for(MockData mockData:mockDataService.queryAll(name, uuid,currentPage,pageSize,orderBy).getList()){
                result.add(mockData.convertToNameVO());
            }
        }catch (Exception e){
            return new Response(ResultCode.GLOBAL_PROJECT_SYSTEM_ERROR,e.getMessage());
        }
        return new Response(ResultCode.GLOBAL_SUCCESSFUL,result,"查询成功",count);
    }

    @PostMapping("/queryByUuid")
    public Response queryByUuid(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "uuid", required = true) String uuid,
            @RequestParam(value = "currentPage", required = false) Integer currentPage,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "orderBy", required = false) String orderBy
    ){
        List<MockDataVO> result =   new ArrayList<>();
        try {
            for(MockData mockData: mockDataService.queryResultByNameAndUUID(name,uuid,currentPage,pageSize,orderBy).getList()){
                result.add(mockData.convertToVO());
            }
        }catch (Exception e){
            return new Response(ResultCode.GLOBAL_PROJECT_SYSTEM_ERROR,e.getMessage());
        }
        return new Response(ResultCode.GLOBAL_SUCCESSFUL,result,"查询成功",result.size());
    }


    @PostMapping("/update")
    public Response update(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "uuid", required = false) String uuid,
            @RequestParam(value = "conditions", required = false) String conditions,
            @RequestParam(value = "result", required = false) String result
    ){

        try {
            mockDataService.updateMockData(id,name, uuid, conditions, result);
        }catch (Exception e){
            return new Response(ResultCode.GLOBAL_PROJECT_SYSTEM_ERROR,e.getMessage());
        }
        return new Response(ResultCode.GLOBAL_SUCCESSFUL,id,"修改成功");
    }

}
