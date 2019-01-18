
package com.wang.esjob.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class DataflowJobTest implements DataflowJob {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(DataflowJob.class);

    @Override
    public List<Foo> fetchData(final ShardingContext shardingContext) {
    	System.err.println("-------------- 抓取数据集合...--------------");
    	List<Foo> list = new ArrayList<>();
        list.add(new Foo("11","aa"));
        return list;
    }

    @Override
    public void processData(ShardingContext shardingContext, List list) {
        System.err.println("-------------- 处理数据集合...--------------");
    }

    /*@Override
    public void processData(final ShardingContext shardingContext, final List<Foo> data) {
    	System.err.println("--------------@@@@@@@@@ 处理数据集合...--------------");
    }*/
}
