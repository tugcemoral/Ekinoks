import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.biff.formula.ParseContext;
import jxl.read.biff.BiffException;

public class reader {

	private String inputFile;
	private String year="2009";
	private String season="2";

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void read_water() throws IOException  {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		DBconnection dbcon=new DBconnection();
		dbcon.connectDB();
		
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			
			
			// Loop over first 10 column and lines
			
			String sqlHead="insert into DENEME_EXCEL(";
			String sql="";
			String sql2="";
			String handler="";
			Cell cell2=null;
			
			ArrayList arr=new ArrayList();
			ArrayList arr2=new ArrayList();
			
			/*for(int r=0;r<sheet.getColumns();r++)
			{
				Cell cell = sheet.getCell(r, 0);
				
				if(r==sheet.getColumns()-1)
				{
					sqlHead+=cell.getContents()+")";
					System.out.println(sqlHead);
				}
				else
				{
					sqlHead+=cell.getContents()+",";
					
				}
			}*/
			
			for (int j = 3; j < sheet.getRows(); j++) {
				Cell cell = sheet.getCell(0, j);
				handler=cell.getContents();
				if(handler!=""){
					
					arr.clear();
					cell = sheet.getCell(0, j);
					ResultSet rs1=dbcon.stmt.executeQuery("select STATION_ID from DSS5_STATION_2 where NAME = '"+cell.getContents()+"'");
					rs1.next();
					arr.add(rs1.getInt(1));
					System.out.println(rs1.getInt(1));
					
					for (int i = 1; i < 7; i++) {
						cell = sheet.getCell(i, j);
						arr.add(cell.getContents());

					}
					
				}
				else{
					
						cell = sheet.getCell(7, j);
						
						sql2="insert into DSS5_SAMPLE_2 VALUES(SEQ_SAMPLE.nextval,"+arr.get(0)+",3,"+year+",TO_DATE('"+arr.get(6)+"','DD/MM/YY'),'"+arr.get(1)+"',"+cell.getContents()+","+season+")";
						System.out.println(sql2);
						dbcon.stmt.executeUpdate(sql2);
					for (int i = 8; i < sheet.getColumns(); i++) {
						cell = sheet.getCell(i, j);
						cell2 = sheet.getCell(i, 0);
						if(cell.getContents()=="")
						{
							sql="insert into DSS5_SAMPLE_PARAM_2(SAMPLE_PARAM_ID,SAMPLE_ID,PARAMETER_CATEGORY_ID,PARAM_ID) VALUES(SEQ_SAMPLE_PARAM.nextval,SEQ_SAMPLE.currval,7,"+cell2.getContents()+")";
						}
						else
						{
							sql="insert into DSS5_SAMPLE_PARAM_2 VALUES(SEQ_SAMPLE_PARAM.nextval,SEQ_SAMPLE.currval,'"+cell.getContents()+"',7,"+cell2.getContents()+")";
						}
							
							System.out.println(sql);
							dbcon.stmt.executeUpdate(sql);
						
					}
					
					/*try {
						//dbcon.stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					arr2.clear();
				}
				
				
				sql="";
				

			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	public void read2_sendiment() throws IOException  {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		DBconnection dbcon=new DBconnection();
		dbcon.connectDB();
		
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			
			
			// Loop over first 10 column and lines
			
			
			String sql="";
			String sql2="";
			
			Cell cell2=null;
			Cell cell=null;
			ArrayList arr=new ArrayList();
			ArrayList arr2=new ArrayList();
			
			
			
			for (int j = 3; j < sheet.getRows(); j++) {
				
				
				arr.clear();
				cell = sheet.getCell(0, j);
				
				ResultSet rs1=dbcon.stmt.executeQuery("select STATION_ID from DSS5_STATION_2 where NAME = '"+cell.getContents()+"'");
				rs1.next();
				arr.add(rs1.getInt(1));
				System.out.println(rs1.getInt(1));
					
				for (int i = 1; i < 10; i++) {
					cell = sheet.getCell(i, j);
					arr.add(cell.getContents());
				}
				
				
					
				cell = sheet.getCell(9, j);
						
				sql2="insert into DSS5_SAMPLE_2 VALUES(SEQ_SAMPLE.nextval,"+arr.get(0)+",3,"+year+",TO_DATE('"+arr.get(6)+"/"+arr.get(5)+"/"+arr.get(4)+"','DD/MM/YY'),'"+arr.get(1)+"',0,"+season+")";
				System.out.println(sql2);
				dbcon.stmt.executeUpdate(sql2);
				for (int i = 7; i < sheet.getColumns(); i++) {
					cell = sheet.getCell(i, j);
					cell2 = sheet.getCell(i, 0);
					if(cell.getContents()=="")
					{
						sql="insert into DSS5_SAMPLE_PARAM_2(SAMPLE_PARAM_ID,SAMPLE_ID,PARAMETER_CATEGORY_ID,PARAM_ID) VALUES(SEQ_SAMPLE_PARAM.nextval,SEQ_SAMPLE.currval,10,"+cell2.getContents()+")";
					}
					else
					{
						sql="insert into DSS5_SAMPLE_PARAM_2 VALUES(SEQ_SAMPLE_PARAM.nextval,SEQ_SAMPLE.currval,'"+cell.getContents()+"',10,"+cell2.getContents()+")";
					}
							
						System.out.println(sql);
						dbcon.stmt.executeUpdate(sql);
						
					}
					
					/*try {
						//dbcon.stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					arr2.clear();
				
				
				
					sql="";
				}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void read_biota() throws IOException  {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		DBconnection dbcon=new DBconnection();
		dbcon.connectDB();
		
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			
			
			// Loop over first 10 column and lines
			
			String sqlHead="insert into DENEME_EXCEL(";
			String sql="";
			String sql2="";
			String handler="";
			Cell cell2=null;
			
			ArrayList arr=new ArrayList();
			ArrayList arr2=new ArrayList();
			
			/*for(int r=0;r<sheet.getColumns();r++)
			{
				Cell cell = sheet.getCell(r, 0);
				
				if(r==sheet.getColumns()-1)
				{
					sqlHead+=cell.getContents()+")";
					System.out.println(sqlHead);
				}
				else
				{
					sqlHead+=cell.getContents()+",";
					
				}
			}*/
			
			for (int j = 4; j < sheet.getRows(); j++) {
				Cell cell = sheet.getCell(0, j);
				handler=cell.getContents();
				if(handler!=""){
					
					arr.clear();
					cell = sheet.getCell(0, j);
					ResultSet rs1=dbcon.stmt.executeQuery("select STATION_ID from DSS5_STATION_2 where NAME = '"+cell.getContents()+"'");
					rs1.next();
					arr.add(rs1.getInt(1));
					System.out.println(rs1.getInt(1));
					
					for (int i = 1; i < 7; i++) {
						cell = sheet.getCell(i, j);
						arr.add(cell.getContents());

					}
					
				}
				else{
					
						cell = sheet.getCell(7, j);
						
						sql2="insert into DSS5_SAMPLE_2 VALUES(SEQ_SAMPLE.nextval,"+arr.get(0)+",3,"+year+",TO_DATE('"+arr.get(6)+"','DD/MM/YY'),'"+arr.get(1)+"',"+cell.getContents()+","+season+")";
						System.out.println(sql2);
						dbcon.stmt.executeUpdate(sql2);
					for (int i = 8; i < sheet.getColumns(); i++) {
						cell = sheet.getCell(i, j);
						cell2 = sheet.getCell(i, 0);
						if(cell.getContents()=="")
						{
							sql="insert into DSS5_SAMPLE_PARAM_2(SAMPLE_PARAM_ID,SAMPLE_ID,PARAMETER_CATEGORY_ID,PARAM_ID) VALUES(SEQ_SAMPLE_PARAM.nextval,SEQ_SAMPLE.currval,9,"+cell2.getContents()+")";
						}
						else
						{
							sql="insert into DSS5_SAMPLE_PARAM_2 VALUES(SEQ_SAMPLE_PARAM.nextval,SEQ_SAMPLE.currval,'"+cell.getContents()+"',9,"+cell2.getContents()+")";
						}
							
							System.out.println(sql);
							dbcon.stmt.executeUpdate(sql);
						
					}
					
					/*try {
						//dbcon.stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					arr2.clear();
				}
				
				
				sql="";
				

			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void read2_biota_pol() throws IOException  {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		DBconnection dbcon=new DBconnection();
		dbcon.connectDB();
		
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			
			
			// Loop over first 10 column and lines
			
			
			String sql="";
			String sql2="";
			
			Cell cell2=null;
			Cell cell=null;
			ArrayList arr=new ArrayList();
			ArrayList arr2=new ArrayList();
			
			
			
			for (int j = 4; j < sheet.getRows(); j++) {
				
				
				arr.clear();
				cell = sheet.getCell(0, j);
				
				ResultSet rs1=dbcon.stmt.executeQuery("select STATION_ID from DSS5_STATION_2 where NAME = '"+cell.getContents()+"'");
				rs1.next();
				arr.add(rs1.getInt(1));
				System.out.println(rs1.getInt(1));
					
				for (int i = 1; i < 7; i++) {
					cell = sheet.getCell(i, j);
					arr.add(cell.getContents());
				}
				
				
					
				cell = sheet.getCell(7, j);
						
				sql2="insert into DSS5_SAMPLE_2 VALUES(SEQ_SAMPLE.nextval,"+arr.get(0)+",3,"+year+",TO_DATE('"+arr.get(6)+"','DD/MM/YY'),'"+arr.get(1)+"',0,"+season+")";
				System.out.println(sql2);
				System.out.println(arr.get(6)+"");
				dbcon.stmt.executeUpdate(sql2);
				for (int i = 7; i < sheet.getColumns(); i++) {
					cell = sheet.getCell(i, j);
					cell2 = sheet.getCell(i, 0);
					if(cell.getContents()=="")
					{
						sql="insert into DSS5_SAMPLE_PARAM_2(SAMPLE_PARAM_ID,SAMPLE_ID,PARAMETER_CATEGORY_ID,PARAM_ID) VALUES(SEQ_SAMPLE_PARAM.nextval,SEQ_SAMPLE.currval,10,"+cell2.getContents()+")";
					}
					else
					{
						sql="insert into DSS5_SAMPLE_PARAM_2 VALUES(SEQ_SAMPLE_PARAM.nextval,SEQ_SAMPLE.currval,'"+cell.getContents()+"',10,"+cell2.getContents()+")";
					}
							
						System.out.println(sql);
						dbcon.stmt.executeUpdate(sql);
						
					}
					
					/*try {
						//dbcon.stmt.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					arr2.clear();
				
				
				
					sql="";
				}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void read_last() throws IOException  {
		File inputWorkbook = new File(inputFile);
		Workbook w;
		DBconnection dbcon=new DBconnection();
		dbcon.connectDB();
		
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			
			
			// Loop over first 10 column and lines
			
			String sqlHead="insert into DENEME_EXCEL(";
			String sql="";
			String sql2="";
			String handler="";
			Cell cell2=null;
			
			ArrayList arr=new ArrayList();
			ArrayList arr2=new ArrayList();
			
			/*for(int r=0;r<sheet.getColumns();r++)
			{
				Cell cell = sheet.getCell(r, 0);
				
				if(r==sheet.getColumns()-1)
				{
					sqlHead+=cell.getContents()+")";
					System.out.println(sqlHead);
				}
				else
				{
					sqlHead+=cell.getContents()+",";
					
				}
			}*/
			
			for (int j = 4; j < sheet.getRows(); j++) {
				Cell cell = sheet.getCell(0, j);
				handler=cell.getContents();
				if(handler!=""){
					
					arr.clear();
					cell = sheet.getCell(0, j);
					ResultSet rs1=dbcon.stmt.executeQuery("select STATION_ID from DSS5_STATION_2 where NAME = '"+cell.getContents()+"'");
					rs1.next();
					arr.add(rs1.getInt(1));
					System.out.println(rs1.getInt(1));
					
					for (int i = 1; i < 7; i++) {
						cell = sheet.getCell(i, j);
						arr.add(cell.getContents());

					}
					
					
					cell = sheet.getCell(16, j);
					
					
					
					if(cell.getContents()=="")
					{
					}
					else
					{
						cell = sheet.getCell(7, j);
						
						sql2="insert into DSS5_SAMPLE_2 VALUES(SEQ_SAMPLE.nextval,"+arr.get(0)+",3,"+year+",TO_DATE('"+arr.get(6)+"','DD/MM/YY'),'"+arr.get(1)+"',-1,"+season+")";
						System.out.println(sql2);
						dbcon.stmt.executeUpdate(sql2);
						
						sql="insert into DSS5_SAMPLE_PARAM_2 VALUES(SEQ_SAMPLE_PARAM.nextval,SEQ_SAMPLE.currval,'"+cell.getContents()+"',9,327)";
						System.out.println(sql);
						dbcon.stmt.executeUpdate(sql);
					}
					
					arr2.clear();
					
				}
				else{
					
						
					
				}
				
				
				sql="";
				

			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public void param_adder()
	{
		File inputWorkbook = new File(inputFile);
		Workbook w;
		DBconnection dbcon=new DBconnection();
		dbcon.connectDB();
		int seq_param=452;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			Cell cell=null;
			for(int i=0;i<sheet.getColumns();i++){
				
				cell=sheet.getCell(i, 0);
				
				String sql2="insert into DSS5_PARAM_2 VALUES("+seq_param+",'"+cell.getContents()+"','"+cell.getContents()+"','DOUBLE','')";
				
				System.out.println(sql2);
				dbcon.stmt.executeUpdate(sql2);
				seq_param++;
			}
			
			
			
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	public static void main(String[] args) throws IOException {
		reader test = new reader();
		test.setInputFile("C:/Documents and Settings/Datron/Belgelerim/Downloads/Hotmail/aktarim.xls");
		test.read2_sendiment();
	}

}
