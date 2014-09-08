package com.hp.it.wstax.binding.castor;

import java.io.IOException;
import java.text.ParseException;


import org.crabapple.binding.castor.XML2JavaGenerator;
import org.crabapple.binding.model.Article;
import org.crabapple.binding.model.Customer;
import org.crabapple.binding.model.manager.ModelInitializer;
import org.crabapple.common.utils.file.CommonFileUtils;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.apache.log4j.Logger;


public class XML2JavaGeneratorTest {
	private static final Logger logger = Logger.getLogger(XML2JavaGeneratorTest.class);
	
	@Test
	public void testMarshal() throws ParseException, MarshalException, ValidationException, IOException, MappingException{
		// 1, Customer have no annotation 'XmlRootElement'
		Customer customer = ModelInitializer.getCustomersList().get(0);
		String xml = XML2JavaGenerator.marshal(customer);
		logger.debug("Customer1|" + xml);
		
		// 1.1 
		xml = XML2JavaGenerator.marshal(ModelInitializer.getCustomersList());
		logger.debug("Customer2|" + xml);
		
		// 2, Article have annotation 'XmlRootElement' and 'XmlElement'
		Article arl = ModelInitializer.initializeArticle();
		// 2.1 Non-use Mapping
		xml = XML2JavaGenerator.marshal(arl);
		logger.debug("article2|" + xml);
		
		// 2.2 Use Mapping
		Mapping articleMapping = new Mapping();
		String mappingPath = "/castor-mapping/article-mapping.xml";
		InputSource is = new InputSource(CommonFileUtils.getInputStreamByFilePath(mappingPath));
		articleMapping.loadMapping(is);
		xml = XML2JavaGenerator.marshal(ModelInitializer.initializeArticle(), articleMapping);
		logger.debug("article|" + xml);
		
		// 3, Test unmarshal
		Article article = (Article)XML2JavaGenerator.unmarshal(Article.class, xml, articleMapping);
		Assert.assertNotNull(article);
		Assert.assertEquals(arl.getTitle(), article.getTitle());
		Assert.assertEquals(arl.getAuthor().getFirstName(), article.getAuthor().getFirstName());
		// TODO: Date convert have error
		// Assert.assertEquals(arl.getPublishDate().getTime(),article.getPublishDate().getTime());
	}

}
