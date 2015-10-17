package com.crabapple.sample.chart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 根据统计值  生成图片
 *
 */
public class ChartGraphics {
	BufferedImage image;

	/**
	 * 生成图片
	 * @param fileLocation
	 */
	public void createImage(String fileLocation) {
		try {
			FileOutputStream fos = new FileOutputStream(fileLocation);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(image);
			bos.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	/**
	 * 把实际值 转换成生成图片的柱状高度
	 */
	public int[] changeValue(double[] money){
		
		int[] height = new int[money.length];
		
		//求统计值中最大的值
		int maxMoney = (int)money[0];
		for(int i = 1; i < money.length-1; i++){
			if(money[i+1] > money[i]){
				maxMoney = (int)money[i+1];
			}
		}
		
		if (maxMoney >= 0 && maxMoney < 10) {
			if(maxMoney >= 0 && maxMoney < 1){
				for (int i = 0; i < money.length; i++) {
					height[i] = (int) money[i] * 10000;
				}
			}else{
				for (int i = 0; i < money.length; i++) {
					height[i] = (int) money[i] * 10;
				}
			}
		} else {
			int flag = 1;
			for (int i = 100; flag == 1; i = i * 10) {
				if ((maxMoney / i) == 0 ) {
					if(maxMoney <= 4*i){
						for (int j = 0; j < money.length; j++) {
							height[j] = (int) (money[j] / i * 700);
						}
						flag = 0;
					}
					if(maxMoney > 4*i){
						for (int j = 0; j < money.length; j++) {
							height[j] = (int)( money[j] / i * 30);
						}
						flag = 0;
					}
				}
			}
		}
		   
		for(int i = 0; i < height.length; i++){
			System.out.println("height["+i+"]==="+height[i]);	
		}
		return height;
	}
	
	
	/**
	 * 把实际值 转换成生成图片的柱状高度 操作对象是double数组
	 */
	public int[][] changeValueTwo(double[][] money,String[] vipLevels){
		int[][] height = new int[money.length][vipLevels.length];
	
		
		//求统计值中最大的值
		int maxMoney = (int)money[0][0];
		
		
		if (maxMoney >= 0 && maxMoney < 10) {
			for (int i = 0; i < money.length; i++) {
				for(int j = 0; j < money[i].length; j++){
					
					height[i][j] = (int) money[i][j] * 10;
				}
			}
		} else {
			int flag = 1;
			for (int i = 100; flag == 1; i = i * 10) {
				if ((maxMoney / i) == 0 ) {
					if(maxMoney <= 4*i){
						for (int j = 0; j < money.length; j++) {
							for(int k = 0; k < money[i].length; k++){
							    height[j][k] = (int) money[j][k] / (i / 700);
							}
						}
						flag = 0;
					}
					if(maxMoney > 4*i){
						for (int j = 0; j < money.length; j++) {
							for(int k = 0; k < money[i].length; k++){
								height[j][k] = (int) height[j][k] / (i / 30);
							}
						}
						flag = 0;
					}
				}
			}
		}
		   
		
		return height;
	}
	
	/**
	 * 形成柱状图 并生成图片
	 * @param money
	 */
	public void graphicsGeneration(String imageName,double[] money,String[] time,String x) {
		//对值进行截取前两位
		NumberFormat   doubleFormat   =   new   DecimalFormat( "0.00 "); 
		String[] moneyChange = new String[money.length];
		for(int i = 0; i < money.length; i++){
			moneyChange[i] = doubleFormat.format(money[i]);
		}
		ChartGraphics chartGraphics = new ChartGraphics();
		int[] height = chartGraphics.changeValue(money);
		
		final int X = 10;
		int imageWidth;
		if(money.length<5){
			imageWidth = 350;
		}else if(money.length>=5 && money.length<=9){
	    imageWidth = money.length*80;// 图片的宽度
		}else{
			imageWidth = money.length*70;
		}
		int imageHeight = 400;// 图片的高度
		int columnWidth = 40;// 柱的宽度
		int columnHeight = 350;// 柱的最大高度 + 10
		int columnSpace = 20; // 柱子之间的间隔

		
		chartGraphics.image = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = chartGraphics.image.createGraphics(); // 返回一个与该图像相关的
																	// Graphics2D
																	// 对象
		graphics.setColor(Color.white); //背景色
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		// 画坐标轴
		graphics.setColor(Color.BLUE);
		graphics.drawLine(25, imageHeight-40, 25, 20);  //y 坐标
		graphics.drawArc(0, 0, 2, 2, 2, 2);
		graphics.drawString("统 ", 5, 35);
		graphics.drawString("计 ", 5, 50);
		graphics.drawString("值 ", 5, 65);
		
		graphics.drawLine(25, imageHeight-40, imageWidth-15, imageHeight-40); //x 坐标
		graphics.drawString(x, imageWidth-45, imageHeight-25);


		
		for (int i = 0; i < height.length; i++) {
			if(i == 0){ //第一根柱子 
				//柱子顶部显示 统计值
				graphics.setColor(Color.BLUE);
				graphics.drawString(moneyChange[i]+"", 40,columnHeight- height[i] - 4);
				graphics.setColor(Color.red);
				graphics.drawRect(40, (imageHeight-40)- height[i] - 1, columnWidth, height[i]);
				graphics.fillRect(40, (imageHeight-40)- height[i] - 1, columnWidth, height[i]);
				graphics.setColor(Color.BLUE);
				graphics.drawString(time[i], 40,imageHeight-25);
			}
			//graphics.drawRect(arg0, arg1, arg2, arg3);
			//arg0 :柱子最左顶点 x坐标
			//arg1 :柱子最左顶点 y坐标
			//柱顶 = x坐标的‘y’值（(imageHeight-40)） - 柱子高度（height[i]）
			//柱子顶部显示 统计值
			graphics.setColor(Color.BLUE);
			graphics.drawString(moneyChange[i]+"", i * (columnWidth + columnSpace)+40,columnHeight- height[i] - 4);
			graphics.setColor(Color.red); //柱子颜色
			graphics.drawRect(i * (columnWidth + columnSpace)+40, (imageHeight-40)- height[i] - 1, columnWidth, height[i]);
			graphics.fillRect(i * (columnWidth + columnSpace)+40, (imageHeight-40)- height[i] - 1, columnWidth, height[i]);
			graphics.setColor(Color.BLUE);
			graphics.drawString(time[i], i * (columnWidth + columnSpace)+40,imageHeight-25);
		}
		chartGraphics.createImage(imageName); // chart.jpg应包含生成的jpg文件的路径
	}
	
	
	
	
	/**
	 * 形成柱状图 并生成图片 此方法是生成不同时间段 不同客户的消费统计图片
	 * @param money
	 */
	public void graphicsGeneration1(String imageName,double[][] money,String[] time,String[] vipLevels,String x) {
		//对值进行截取前两位
		NumberFormat   doubleFormat   =   new   DecimalFormat( "0.00 "); 
		String[][] moneyChange = new String[time.length][vipLevels.length];
		for(int i = 0; i < money.length; i++){
			for(int j = 0; j < money[i].length; j++){
				moneyChange[i][j] = doubleFormat.format(money[i][j]);
			}
		}
		ChartGraphics chartGraphics = new ChartGraphics();
		int[][] height = chartGraphics.changeValueTwo(money, vipLevels); 
		
		final int X = 10;
		int imageWidth;
		if(money.length<5){
			imageWidth = 700;
		}else if(money.length>=5 && money.length<=9){
	    imageWidth = money.length*150;// 图片的宽度
		}else{
			imageWidth = money.length*150;
		}
		int imageHeight = 400;// 图片的高度
		int columnWidth = 30;// 柱的宽度
		int columnHeight = 350;// 柱的最大高度 + 10
		int columnSpace = 20; // 柱子之间的间隔

		
		chartGraphics.image = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = chartGraphics.image.createGraphics(); // 返回一个与该图像相关的
																	// Graphics2D
																	// 对象
		graphics.setColor(Color.white); //背景色
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		// 画坐标轴
		graphics.setColor(Color.BLUE);
		graphics.drawLine(25, imageHeight-40, 25, 20);  //y 坐标
		graphics.drawArc(0, 0, 2, 2, 2, 2);
		graphics.drawString("统 ", 5, 35);
		graphics.drawString("计 ", 5, 50);
		graphics.drawString("值 ", 5, 65);
		
		graphics.drawLine(25, imageHeight-40, imageWidth-15, imageHeight-40); //x 坐标
		graphics.drawString(x, imageWidth-45, imageHeight-25);


		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.red);
		colors.add(Color.blue);
		colors.add(Color.lightGray);
		colors.add(Color.GREEN);
		for (int i = 0; i < height.length; i++) {
			
			if(i == 0){ 
				
				for(int j = 0; j < height[i].length; j++){
					graphics.setColor(Color.BLUE);
					graphics.drawString(moneyChange[i][j]+"", 40+j*columnWidth,columnHeight- height[i][j] - 4);
					graphics.setColor(colors.get(j));
					graphics.drawRect(40+j*columnWidth, (imageHeight-40)- height[i][j] - 1, columnWidth, height[i][j]);
					graphics.fillRect(40+j*columnWidth, (imageHeight-40)- height[i][j] - 1, columnWidth, height[i][j]);
					
				}
				graphics.setColor(Color.BLUE);
				graphics.drawString(time[i], 40,imageHeight-25);
			}
		
            for(int j = 0; j < height[i].length; j++){
            	graphics.setColor(Color.BLUE);
            	graphics.drawString(moneyChange[i][j]+"", i * (columnWidth*4 + columnSpace)+40+j*columnWidth,columnHeight- height[i][j] - 4);
            	graphics.setColor(colors.get(j)); //柱子颜色
            	graphics.drawRect(i * (columnWidth*4 + columnSpace)+40+j*columnWidth, (imageHeight-40)- height[i][j] - 1, columnWidth, height[i][j]);
            	graphics.fillRect(i * (columnWidth*4 + columnSpace)+40+j*columnWidth, (imageHeight-40)- height[i][j] - 1, columnWidth, height[i][j]);
            	
			}
            graphics.setColor(Color.BLUE);
            graphics.drawString(time[i], i * (columnWidth*4 + columnSpace)+40,imageHeight-25);
		}
		chartGraphics.createImage(imageName); // chart.jpg应包含生成的jpg文件的路径
	}

}
