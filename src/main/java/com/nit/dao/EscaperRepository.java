package com.nit.dao;

import com.nit.entity.Escaper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EscaperRepository extends ElasticsearchRepository<Escaper,String> {

    Escaper queryEmployeeById(String id);

}
