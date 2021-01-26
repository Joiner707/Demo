package com.nit.util;

import com.nit.vo.MockDataVO;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueUtil {

    private static ArrayBlockingQueue<MockDataVO> blockingQueue = new ArrayBlockingQueue(100);

    private static Map<String,Integer> runningProject = new HashMap<>();

    public static MockDataVO getData() throws InterruptedException {
        return blockingQueue.take();
    }

    public static MockDataVO peekData() throws InterruptedException {
        return blockingQueue.peek();
    }

    public static void setData(MockDataVO compareInfoVO) throws InterruptedException {
        blockingQueue.put(compareInfoVO);
    }

    public static int getQueueSize(){
        return blockingQueue.size();
    }

    public static void setRunningProject(String project,Integer recordId){
        runningProject.put(project,recordId);
    }

    public static Integer getRunningProject(String project){
        return runningProject.get(project);
    }

}
