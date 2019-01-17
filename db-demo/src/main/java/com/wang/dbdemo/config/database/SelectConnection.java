package com.wang.dbdemo.config.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectConnection {

	String dbName() default "";
	
	boolean readOnly() default false;

	String tableName() default "";
	
}
	