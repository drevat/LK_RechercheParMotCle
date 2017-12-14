package classes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



/** LE LIEN CLIQUABLE DU FICHIER EXCEL ME DONNE UN MESSAGE D'ERREUR : RESTRICTION EN VIGUEUR SUR CET ORDINATEUR */

public class ResultLinkedIn {

	 WebDriver driver;
			 
	 @FindBy(xpath="//ul[@class='results-list ember-view']//li")
	 List<WebElement> personneFactory;
	 
	 @FindBy(xpath="//ul[@class='results-list ember-view']//span[@class='name actor-name']")
	 List<WebElement> nomFactory;
	 
	 @FindBy(xpath="//ul[@class='results-list ember-view']//p[@class='subline-level-1 Sans-15px-black-85% search-result__truncate']")
	 List<WebElement> fonctionFactory;
	 
	 @FindBy(xpath="//ul[@class='results-list ember-view']//p[@class='subline-level-2 Sans-13px-black-55% search-result__truncate']")
	 List <WebElement> regionFactory;
	 
	 @FindBy(xpath="//ul[@class='results-list ember-view']//p[@class='search-result__snippets mt2 Sans-13px-black-55% ember-view']")
	 List<WebElement> posteActuelFactory;
	 
	 @FindBy(xpath="//div[@class='search-result__info pt3 pb4 ph0']//a[@class='search-result__result-link ember-view']")
	 List<WebElement> urlFactory;
	 	
	@SuppressWarnings("deprecation")
	int dJour = new Date().getDate();
	@SuppressWarnings("deprecation")
	int dMois = new Date().getMonth()+1;
	@SuppressWarnings("deprecation")
	int dAnnee = new Date().getYear()+1900;
	String date = dAnnee + "" + dMois + "" + dJour;	 

	 public ResultLinkedIn( WebDriver driver){
	 this.driver = driver;
	 }
	 
	public void SetResult(WebDriver webDriver){
		PageFactory.initElements(driver, this);
	}	

	public void ResultList(WebDriver driver, String key) throws InterruptedException{
	
		// A Modifier
		String FILE_NAME = System.getProperty("user.dir")+"\\Extracts\\"+date+"_LK_"+key+".xls";
		
        HSSFWorkbook workbook = new HSSFWorkbook();
        // Setting cell style 
        HSSFSheet sheet = workbook.createSheet("Resultat LinkedIn");

        int nbpage=1;

		this.SetResult(driver);
		JavascriptExecutor js=(JavascriptExecutor) driver;	
		int rowNum = 1;
		//Style font
		HSSFFont font= workbook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("Arial");
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        font.setItalic(false);
        
        //Style des cellule de titre
        HSSFCellStyle style= workbook.createCellStyle();
        
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(font);

        // Ecriture du titre des colones
    	Row row = sheet.createRow(0);

    	Cell cellTitre1 = row.createCell(0);
    		 cellTitre1.setCellValue("IDENTITE");
    		 cellTitre1.setCellStyle(style);
    		 
        Cell cellTitre2 = row.createCell(1); 
   		 	 cellTitre2.setCellValue("POSTE");
   		 	 cellTitre2.setCellStyle(style);
   		 	 
     	Cell cellTitre3 = row.createCell(2); 
     		 cellTitre3.setCellValue("REGION");
     		 cellTitre3.setCellStyle(style);

        Cell cellTitre4 = row.createCell(3); 
    		 cellTitre4.setCellValue("URL PROFIL");
    		 cellTitre4.setCellStyle(style);
// Boucle de parcours des pages + actions a réaliser.     		 
		do{
			js.executeScript("window.scrollTo(0,500)");
			js.executeScript("window.scrollTo(0,1000)");	
			js.executeScript("window.scrollTo(0,1500)");	
			Thread.sleep(2000);
			
			int nb = personneFactory.size();
		//	System.out.println("Nombre de personneFactory: "+nb);
			nb = nomFactory.size();
		//	System.out.println("Nombre de nomFactory: "+nb);
			nb = urlFactory.size();
		//	System.out.println("Nombre de urlFactory: "+nb);

	   //     System.out.println("Writing in Excel");
        	
            for (int i=0;i<nb;i++) {
            	Row row2 = sheet.createRow(rowNum);
            	for(int a=0;a<nb;a++){
            		int colNum = 0;
            		Cell cell1 = row2.createCell(colNum);           
            		cell1.setCellValue(nomFactory.get(i).getText());
            		sheet.autoSizeColumn(colNum, true);
            		colNum++;
            		Cell cell2 = row2.createCell(colNum);
            		cell2.setCellValue(fonctionFactory.get(i).getText());
            		sheet.autoSizeColumn(colNum, true);
            		colNum++;
            		Cell cell3 = row2.createCell(colNum);
            		cell3.setCellValue(regionFactory.get(i).getText());
            		sheet.autoSizeColumn(colNum, true);
            		colNum++;
            		Cell cell4 = row2.createCell(colNum);
            		cell4.setCellValue(urlFactory.get(i).getAttribute("href"));
            		sheet.autoSizeColumn(colNum, true);
                }

            	rowNum++;
            //	System.out.println(nomFactory.get(i).getText()+"\t");
            //	System.out.println(urlFactory.get(i).getAttribute("href"));
	
	        }
            //System.out.println("Done");
			Thread.sleep(1000);
			driver.findElement(By.className("next")).click();
			nbpage++;
		}while(driver.findElement(By.className("next"))!= null);
		//while (nbpage<=2);		
	try {
		 sheet.setAutoFilter(CellRangeAddress.valueOf("A1:D1"));
	       FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
	       workbook.write(outputStream);
	       workbook.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}	
}