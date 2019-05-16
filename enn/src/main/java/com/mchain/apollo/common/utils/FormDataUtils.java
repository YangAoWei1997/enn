package com.mchain.apollo.common.utils;

import java.util.List;

import org.springframework.util.LinkedMultiValueMap;

import com.mchain.apollo.common.entity.base.Arg;
import com.mchain.apollo.common.entity.base.Data;
import com.mchain.apollo.common.entity.base.Error;
import com.mchain.apollo.common.entity.base.Message;

public class FormDataUtils {
	public static LinkedMultiValueMap<String, Object> getMessage(Message message){
		LinkedMultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
		formData.add("id", message.getId());
		formData.add("channel", message.getChannel());
		formData.add("tranCode", message.getTranCode());
		formData.add("type", message.getType());
		formData.add("dataType", message.getDataType());
		formData.add("tranDate", message.getTranDate());
		formData.add("traceNo", message.getTraceNo());
		formData.add("retCode", message.getRetCode());
		formData.add("debug", message.getDebug());
		formData.add("userid", message.getUserid());
		formData.add("orgid", message.getOrgid());
		List<Arg> args = message.getArgs();
		List<Data> datas = message.getData();
		List<Error> errors = message.getErrors();
		if (!args.isEmpty()) {
			for(int i =0; i<args.size(); i++) {
				formData.add("args["+i+"].name", args.get(i).getName());
				formData.add("args["+i+"].value", args.get(i).getValue());
			}
		}
		if (!datas.isEmpty()) {
			for(int i =0; i<datas.size(); i++) {
				formData.add("data["+i+"].content", datas.get(i).getContent());
				formData.add("data["+i+"].dataType", datas.get(i).getDataType());
				formData.add("data["+i+"].formatType", datas.get(i).getFormatType());
			}
		}
		if (errors!=null) {
			for(int i =0; i<errors.size(); i++) {
				formData.add("error["+i+"].statement", errors.get(i).getStatement());
				formData.add("error["+i+"].function", errors.get(i).getFunction());
				formData.add("error["+i+"].code", errors.get(i).getCode());
			}
		}
		return formData;
	}
}
