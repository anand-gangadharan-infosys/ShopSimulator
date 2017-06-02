package com.anand.store.simulator.util;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.anand.store.simulator.ShopFloor;

// 1.6 Does not have BlockingQueue!!

public class BQueue<T> {

    private Queue<T> q = new LinkedList<T>();
    private int limit;
	private boolean allProducersExited = false;
    
    private static final Logger logger = LogManager.getLogger(BQueue.class);

    public BQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void put (T t) throws InterruptedException {
        while (isFull()) {
        	logger.debug("Queue full producer has to wait ");
            wait();
        }
        boolean e = isEmpty();
        q.add(t);
        logger.trace("After PUT Queue size  "+q.size());
        if (e){
        	logger.debug("Queue was empty, new item produced wake up pottential sleeping consumers ");
            notifyAll();
        }
    }


    public synchronized T get () throws InterruptedException {
        while (isEmpty()) {
        	if(allProducersExited){
        		return null;
        	}
        	logger.debug("Queue empty consumer has to wait ");
            wait();
        }
        boolean f = isFull();
        T t = q.poll();
        logger.trace("After GET Queue size  "+q.size());
        if (f){
        	logger.debug("Queue was full, one item consumed. wake up pottential sleeping producers ");
            notifyAll();
        }
        return t;
    }
    
    public synchronized void allProducersExited(){
    	this.allProducersExited = true;
    	logger.debug("All producers done, notify any sleeping consumers so they can finish execution");
    	notifyAll();
    }
    
    public synchronized boolean isAllProducersExited(){
    	return allProducersExited;
    }

    public boolean isEmpty() {
        return q.size() == 0;
    }
    private boolean isFull() {
        return q.size() == limit;
    }
    
    public synchronized int size(){
    	return q.size();
    }
}