package com.mchain.apollo.common.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class BeanUtils{
    //merge two bean by discovering differences
    public static <M> void merge(M target, M destination){
        try {
			BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());
	        // Iterate over all the attributes
	        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
	            // Only copy writable attributes
	            if (descriptor.getWriteMethod() != null) {
	                Object originalValue;
					try {
						originalValue = descriptor.getReadMethod().invoke(target);
		                // Only copy values values where the destination values is null
		                if (originalValue == null) {
		                    Object defaultValue = descriptor.getReadMethod().invoke(destination);
		                    descriptor.getWriteMethod().invoke(target, defaultValue);
		                }
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	            }
	        }
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
