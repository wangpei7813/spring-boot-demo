package com.wang.dbdemo.config.database;

import cn.hutool.core.util.StrUtil;
import com.wang.dbdemo.utils.Pair;
import com.wang.dbdemo.utils.SelectorUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SelectConnectionInterceptor implements Ordered {

	private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SelectConnectionInterceptor.class);
	
	private static final String SUFFIX_MASTER = "-master";
	
	private static final String SUFFIX_SLAVE = "-slave";
	
	@Around("@annotation(selectConnection)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, SelectConnection selectConnection) throws Throwable {
		try{
			//1 执行方法前...
			LOGGER.info("--------------- select database source ---------------");
			
			String currentDataBaseName = "";
			String curTableName = selectConnection.tableName();

			if (StrUtil.isBlank(curTableName)) {
			    throw new NullPointerException("表明不能为空");
            }
            String uuid = (String) proceedingJoinPoint.getArgs()[0];
            Pair<Integer, Integer> pair = SelectorUtil.getDataBaseAndTableNumber(uuid);

			//	如果在注解上添加了: name
			if(!StringUtils.isBlank(selectConnection.dbName())){
				currentDataBaseName = selectConnection.dbName();
			} else {
				currentDataBaseName = curTableName + pair.getDbNum();
			}

            curTableName = curTableName + "_" + pair.getTableNum();

            DBContextHolder.setTableName(curTableName);
			
			if(selectConnection.readOnly()){
				currentDataBaseName = currentDataBaseName + SUFFIX_SLAVE;
			} else {
				currentDataBaseName = currentDataBaseName + SUFFIX_MASTER;
			}
			//order3-master
			System.err.println("----Interceptor: currentDataBaseName : " + currentDataBaseName);	
			
			//string-> order2-slave
			for(DBContextHolder.DBType type: DBContextHolder.DBType.values()){
				if(!StringUtils.isBlank(currentDataBaseName)){
					String typeCode = type.getCode();
					if(typeCode.equals(currentDataBaseName)){
						DBContextHolder.setDataBaseType(type);
						System.err.println("----Interceptor: code :" + DBContextHolder.getDataBaseType().getCode());
					}
				}
			}
			
			//2 开始执行方法
			Object result = proceedingJoinPoint.proceed();
			
			//3 执行方法后
			
			return result;
		} finally {
			DBContextHolder.clearDataBaseType();
            DBContextHolder.clearTableName();
			LOGGER.info("---------------clear database connection---------------");
		}
	}
	
	
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return -1;
	}

}
