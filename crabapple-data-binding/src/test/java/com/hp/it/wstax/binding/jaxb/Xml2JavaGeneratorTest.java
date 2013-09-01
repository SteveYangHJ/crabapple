package com.hp.it.wstax.binding.jaxb;

import java.io.StringWriter;
import java.text.ParseException;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.hp.it.wstax.binding.model.Address;
import com.hp.it.wstax.binding.model.Article;
import com.hp.it.wstax.binding.model.Customer;
import com.hp.it.wstax.binding.model.manager.ModelInitializer;


public class Xml2JavaGeneratorTest {
	private static final Logger logger = Logger.getLogger(Xml2JavaGeneratorTest.class);
	private static final String contextPath = "org.crabapple.binding.model";
	
	@Test(expected=JAXBException.class)
	public void testMarshalArticle() throws JAXBException{
		// 1, 
		Article article = ModelInitializer.initializeArticle();
		StringWriter sw = new StringWriter();
		XML2JavaGenerator.marshal(contextPath, article, sw);
		logger.debug("1|" + sw.toString());
	}
	
	@Test
	public void testMarshalArticle1() throws JAXBException{
		// 1, 
		Article article = ModelInitializer.initializeArticle();
		StringWriter sw = new StringWriter();
		XML2JavaGenerator.marshal(new Class[]{Article.class}, article, sw);
		logger.debug("1|" + sw.toString());
	}
	
	@Test(expected=JAXBException.class)
	public void testMarshalAddress() throws JAXBException, ParseException{
		// 1, 
		Address address = ModelInitializer.getCustomersList().get(0).getHouseAddress();
		StringWriter sw = new StringWriter();
		XML2JavaGenerator.marshal(new Class[]{Address.class}, address, sw);
		logger.debug("1|" + sw.toString());
	}
	
	@Test
	public void testMarshalCustomer() throws JAXBException, ParseException{
		// 1, 
		Customer customer = ModelInitializer.getCustomersList().get(0);
		StringWriter sw = new StringWriter();
		XML2JavaGenerator.marshal(new Class[]{Customer.class}, customer, sw);
		logger.debug("1|" + sw.toString());
	}
	
}
