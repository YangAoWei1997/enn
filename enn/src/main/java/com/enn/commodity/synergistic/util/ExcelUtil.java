package com.enn.commodity.synergistic.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enn.commodity.synergistic.dao.PurchaseOrderDao;
import com.enn.commodity.synergistic.entity.PO;
@Component
public class ExcelUtil {
	
	@Autowired
	private PurchaseOrderDao purchaseDao;
	
	public void export(HttpServletResponse response) throws IOException {
		
		PO poForQuery=new PO();
	      List<PO> poList = purchaseDao.SelectPOByObject(poForQuery);
	 
	      HSSFWorkbook wb = new HSSFWorkbook();
	 
	      HSSFSheet sheet = wb.createSheet("获取excel测试表格");
	 
	      HSSFRow row = null;
	 
	      row = sheet.createRow(0);//创建第一个单元格
	      row.setHeight((short) (26.25 * 20));
	      row.createCell(0).setCellValue("订单信息列表");//为第一行单元格设值
	 
	      /*为标题设计空间
	      * firstRow从第1行开始
	      * lastRow从第0行结束
	      *
	      *从第1个单元格开始
	      * 从第3个单元格结束
	      */
	      CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 2);
	      sheet.addMergedRegion(rowRegion);
	 
	      /*CellRangeAddress columnRegion = new CellRangeAddress(1,4,0,0);
	      sheet.addMergedRegion(columnRegion);*/
	 
	 
	      /*
	      * 动态获取数据库列 sql语句 select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='user' and table_schema='test'
	      * 第一个table_name 表名字
	      * 第二个table_name 数据库名称
	      * */
	      row = sheet.createRow(1);
	      row.setHeight((short) (22.50 * 20));//设置行高
	      row.createCell(0).setCellValue("订单Id");//为第一个单元格设值
	      row.createCell(1).setCellValue("采购企业id");//为第二个单元格设值
	      row.createCell(2).setCellValue("销售企业id");//为第三个单元格设值
	 
	      for (int i = 0; i < poList.size(); i++) {
	         row = sheet.createRow(i + 2);
	         PO po = poList.get(i);
	         row.createCell(0).setCellValue(po.getId());
	         row.createCell(1).setCellValue(po.getBuyerId());
	         row.createCell(2).setCellValue(po.getSellerId());
	      }
	      sheet.setDefaultRowHeight((short) (16.5 * 20));
	      //列宽自适应
	      for (int i = 0; i <= 13; i++) {
	         sheet.autoSizeColumn(i);
	      }
	      response.setContentType("application/vnd.ms-excel;charset=utf-8");
	      OutputStream os = response.getOutputStream();
	      response.setHeader("Content-disposition", "attachment;filename=user.xls");//默认Excel名称
	      wb.write(os);
	      os.flush();
	      os.close();
	   }


}
