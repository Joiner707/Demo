package com.nit.task;

import com.nit.anno.IdentifyCheck;
import com.nit.vo.MockDataVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncTaskDemo {

    @Async
    @IdentifyCheck(value = "executionTask")
    public void processExecutionTask(MockDataVO mockDataVO) throws Exception{
        String traceId = MDC.get("traceId");
        log.info("traceId:{}",traceId);
        //todo
    }

}
