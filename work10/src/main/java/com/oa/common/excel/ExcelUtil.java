package com.oa.common.excel;

import java.text.DecimalFormat ;
import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;
import org.apache.poi.ss.usermodel.Cell ;
import org.apache.poi.ss.usermodel.CellStyle ;
import org.apache.poi.ss.usermodel.DateUtil ;
import org.apache.poi.ss.usermodel.Font ;
import org.apache.poi.ss.usermodel.Row ;
import org.apache.poi.ss.usermodel.Sheet ;
import org.apache.poi.ss.usermodel.Workbook ;
import org.apache.poi.ss.util.CellRangeAddress ;

 

public class ExcelUtil {
	
	//excel文件的后缀 = .xls
	public static final String EXCEL_FILE_SUFFIX = ".xls" ;

	//默认的excel列宽=20
	public static final int DEFAULT_COLUMN_WIDTH = 250 ;
	
	/**
	 * 读取Excel列表
	 * @param wsheet
	 * @param startIndex
	 * @param fields
	 * @return
	 * @see
	 * @since
	 */
	public List< Map< String , Object >> readSimple ( Sheet wsheet ,SimpleReadParameter readParameter) {
		List< Map< String , Object >> dataList = new ArrayList< Map< String , Object >> () ;
		//row.getRowNum =0,1,2,3
		for ( Row row : wsheet ) {
			// 从数据行开始读  从第三行开始读  （下标2）
			if ( row.getRowNum () >= readParameter.getStartIndex() ) {
				
				Map< String , Object > rowMap = new HashMap< String , Object > () ;
				//startIndex为2，row为第三行时，第二个for循环完成时rowMap里的数据
				String fields[] = readParameter.getFieldsId();
				//row 行  Cell：单元格
				
				for ( Cell cell : row ) {
					//System.out.println("单元格------"+cell.getColumnIndex());
					// fields代表的列以后的数据忽略  fields.length=6
					if ( cell.getColumnIndex () < fields.length ) {
						//得到excel单元格中的内容
						Object value = getValue ( cell );
						
						String key = fields[cell.getColumnIndex()];
						
						//System.out.println(key+"----     ---------"+value);
						rowMap.put ( key , value ) ;
					//	rowMap.put("userSex", 1);
					}
				}
				//System.out.println(rowMap+"-----------------------------rowMap");
				
				dataList.add ( rowMap ) ;
			}
		}
		return dataList ;
	}
	
	/**
	 * 得到Excel中单元格的值
	 * @param cell
	 * @return
	 * @see
	 * @since
	 */
	private Object getValue ( Cell cell ) {
		Object value = null ;
		DecimalFormat df = new DecimalFormat ( "#.####" ) ;
		switch ( cell.getCellType () ) {
			case Cell.CELL_TYPE_STRING ://字符串类型
				value = cell.getRichStringCellValue ().getString () ;
				break ;
			case Cell.CELL_TYPE_NUMERIC ://日期类型或者 数值类型
				if ( DateUtil.isCellDateFormatted ( cell ) ) {
					value = CalendarHelper.formatDatetime ( cell.getDateCellValue () ) ;
				} else {
					value = df.format ( cell.getNumericCellValue () ) ;
				}
				break ;
			case Cell.CELL_TYPE_BOOLEAN :
				value = cell.getBooleanCellValue () ;
				break ;
			case Cell.CELL_TYPE_FORMULA :
				value = cell.getCellFormula () ;
				break ;
			default :
				value = cell.getStringCellValue () ;
		}
		return value ;
	}
	
	/**
	 * 导出excel格式的文件
	 * @param wsheet
	 * @param parameters
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @see
	 * @since
	 */
	public void simpleExport ( Workbook wwb , Sheet wsheet , SimpleExportParameter parameters ) {
		//填充头部  标题行
		fillHeaders ( wwb , wsheet , parameters ) ;
		//填充内容
		fillContent ( wwb , wsheet , parameters ) ;
	}
	/**
	 * 千锋员工信息						
姓名	年龄	邮箱	省份	性别	电话	生日
	 * @param wwb
	 * @param wsheet
	 * @param parameters
	 */
	private void fillHeaders ( Workbook wwb , Sheet wsheet , SimpleExportParameter parameters ) {
		//合并单元格
		wsheet.addMergedRegion ( new CellRangeAddress ( 0 , 0 , 0 , parameters.getFieldsName ().length - 1 ) ) ;
		//创建行
		Row titleRow = wsheet.createRow ( 0 ) ;// 标题行
		//单元格
		Cell titileCell = titleRow.createCell ( 0 ) ;
		
		
		Font titleFont = wwb.createFont () ;// 标题字体
		titleFont.setFontHeightInPoints ( ( short ) 30 ) ;
		titleFont.setFontName ( "Courier New" ) ;
		titleFont.setBoldweight ( Font.BOLDWEIGHT_BOLD ) ;//加粗
		
		CellStyle titileStyle = wwb.createCellStyle () ;// 标题单元格格式：居中，底对齐
		titileStyle.setAlignment ( CellStyle.ALIGN_CENTER ) ;
		titileStyle.setVerticalAlignment ( CellStyle.VERTICAL_BOTTOM ) ;//底对齐
		titileStyle.setFont ( titleFont ) ;
		
		
		titileCell.setCellStyle ( titileStyle ) ;
		titileCell.setCellValue ( parameters.getTitle () ) ;//填充单元格的内容
		

	  wsheet.setDefaultColumnWidth ( 20 * DEFAULT_COLUMN_WIDTH ) ;//5000/512
	  
	  //第二行部分
	  if ( parameters.getWidths () != null ) {
		   for ( int i = 0 ; i < parameters.getWidths ().length ; ++i ) {
				wsheet.setColumnWidth ( i , Integer.parseInt ( parameters.getWidths ()[i] ) * DEFAULT_COLUMN_WIDTH ) ;
			}
		}

		
		Row columnRow = wsheet.createRow ( 1 ) ;// 列标题行
		
		Font font = wwb.createFont () ;// 列标题行字体
		font.setFontHeightInPoints ( ( short ) 15 ) ;
		font.setFontName ( "Courier New" ) ;
		font.setBoldweight ( Font.BOLDWEIGHT_BOLD ) ;
		
		CellStyle cellStyle = wwb.createCellStyle () ;// 列标题行单元格格式：居中，底对齐
		cellStyle.setAlignment ( CellStyle.ALIGN_CENTER ) ;
		cellStyle.setVerticalAlignment ( CellStyle.VERTICAL_BOTTOM ) ;
		cellStyle.setFont ( font ) ;
		
		
		for ( int i = 0 ; i < parameters.getFieldsName ().length ; ++i ) {
			Cell cell = columnRow.createCell ( i ) ;
			cell.setCellStyle ( cellStyle ) ;
			cell.setCellValue ( parameters.getFieldsName ()[i] ) ;
		}
		
	}
	
	/**
	 * 把数据库内容 填充到excel文件中
	 * @param wwb
	 * @param wsheet
	 * @param parameters
	 * map---》行   map的值---》单元格的值
	 */
	private void fillContent ( Workbook wwb , Sheet wsheet , SimpleExportParameter parameters ) {
		List< Map< String , Object >> list = parameters.getDataList () ;
		
		String value = "" ;
		String[] field = parameters.getFieldsId () ;//列英文名称
		
		for ( int i = 0 ; i < list.size () ; i++ ) {
			
			Map< String , Object > originMap = list.get ( i );
			Row row = wsheet.createRow ( i + 2 ) ;//+2  从第三行开始填充员工信息
			
			for ( int j = 0 ; j < field.length ; j++ ) {

				Object origin = originMap.get ( field[j] ) ;// 原始数据
				Cell cell = row.createCell ( j ) ;//单元格
				
				if ( origin instanceof java.sql.Date ) {//yyyy-MM-dd HH:mm:ss   yyyy/mm/dd
					java.sql.Date d = ( java.sql.Date ) origin ;
					value = CalendarHelper.formatDatetime ( d ) ;
				} else {
					value = String.valueOf ( origin ) ;
				}
				if ( value == null || value.equalsIgnoreCase ( "null" ) ) {
					value = "" ;
				}
		
				cell.setCellValue ( value ) ;//填充单元格的值
			}
		}
	}
}
