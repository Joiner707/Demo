package com.nit.controller;

import cn.hutool.json.JSONUtil;
import com.nit.dao.EscaperRepository;
import com.nit.entity.Escaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("es")
public class EscaperController {

    @Autowired
    private EscaperRepository escaperRepository;

    /**
     * 添加
     * @return
     */
    @RequestMapping("add")
    public String add() {
        Escaper escaper = new Escaper();
        escaper.setId("1");
        escaper.setFirstName("xuxu");
        escaper.setLastName("zh");
        escaper.setAge(26);
        escaper.setAbout("i am in peking");
        escaperRepository.save(escaper);
        System.err.println("add a obj");
        return "success";
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping("delete")
    public String delete() {
        Escaper employee = escaperRepository.queryEmployeeById("1");
        escaperRepository.delete(employee);
        return "success";
    }

    /**
     * 局部更新
     * @return
     */
    @RequestMapping("update")
    public String update() {
        Escaper employee = escaperRepository.queryEmployeeById("1");
        employee.setFirstName("哈哈");
        escaperRepository.save(employee);
        System.err.println("update a obj");
        return "success";
    }
    /**
     * 查询
     * @return
     */
    @RequestMapping("query")
    public Escaper query() {
        Escaper accountInfo = escaperRepository.queryEmployeeById("1");
        System.err.println(JSONUtil.toJsonStr(accountInfo));
        return accountInfo;
    }
}
