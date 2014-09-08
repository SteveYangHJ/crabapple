package org.crabapple.binding.jaxb;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 * 
 * @author Steve Yang, 2013-08-22
 *
 */
public class XML2JavaGenerator {
	
	public final static String DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * @Title: unmarshal
	 * @Description: unmarshal XML to Java object
	 * @author Yang, Wei-Peng
	 * @email 244weipeng@163.com
	 * @date 2012-12-18 PM. 03:13:11 
	 * @param contextPath  list of java package names that contain schema 
	 *                     derived class and/or java to schema (JAXB-annotated) mapped classes
	 * @param xmlStream  InputStream which read XML data
	 * @return object  the type of object defined by the contextPath
	 * @throws JAXBException
	 */
	public static Object unmarshal(String contextPath, InputStream xmlStream)
			throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(contextPath);
		Unmarshaller u = jc.createUnmarshaller();
		return u.unmarshal(xmlStream);
	}
	
	/**
	 * @Title: unmarshal
	 * @Description: unmarshal XML to Java object
	 * @author Yang, Wei-Peng
	 * @email 244weipeng@163.com
	 * @date 2012-12-18 PM. 03:13:11 
	 * @param contextPath  list of java package names that contain schema 
	 *                     derived class and/or java to schema (JAXB-annotated) mapped classes
	 * @param reader  Reader which read XML data
	 * @return object  the type of object defined by the contextPath
	 * @throws JAXBException
	 */
	public static Object unmarshal(String contextPath, Reader reader)
			throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(contextPath);
		Unmarshaller u = jc.createUnmarshaller();
		return u.unmarshal(reader);
	}
	
	/**
	 * @Title: unmarshal
	 * @Description: unmarshal XML to Java object
	 * @author Yang, Wei-Peng
	 * @email 244weipeng@163.com
	 * @date 2013-08-27 PM. 17:13:11 
	 * @param clazz  The class XML will unmarshal to
	 * @param reader  reader to save XML data
	 * @return
	 * @throws JAXBException
	 */
	public static Object unmarshal(Class clazz, Reader reader)
			throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz.getClass().getCanonicalName());
		Unmarshaller u = jc.createUnmarshaller();
		return u.unmarshal(reader);
	}
	
	/**
	 * @Title: unmarshal
	 * @Description: unmarshal XML to Java object
	 * @author Yang, Wei-Peng
	 * @email 244weipeng@163.com
	 * @date 2013-08-27 PM. 17:13:11 
	 * @param clazz  The class XML will unmarshal to
	 * @param content  XML date
	 * @return
	 * @throws JAXBException
	 */
	public static Object unmarshal(Class clazz, String content)
			throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz.getClass().getCanonicalName());
		Unmarshaller u = jc.createUnmarshaller();
		return u.unmarshal(new StringReader(content));
	}

	/**
	 * @param contextPath  list of java package names that contain schema 
	 *                     derived class and/or java to schema (JAXB-annotated) mapped classes
	 * @param obj
	 * @param stream
	 * @throws JAXBException
	 */
	public static void marshal(String contextPath, Object obj, OutputStream stream) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(contextPath);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, DEFAULT_ENCODING);
		m.marshal(obj, stream);
	}
	
	/**
	 * @param contextPath
	 * @param obj
	 * @param writer
	 * @throws JAXBException
	 */
	public static void marshal(String contextPath, Object obj, Writer writer) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(contextPath);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, DEFAULT_ENCODING);
		m.marshal(obj, writer);
	}
	
	/**
	 * @Description 
	 * 		If the Object is POJO, and DO NOT have ObjectFactory.java class, you should use this method
	 * @author Steve Yang (244weipeng@163.com)
	 * @Date 2013-08-22 14:22:00 PM
	 * @param Clazzs  Class array
	 * @param obj     Object need to be marshal to XML data
	 * @param writer  Save XML data
	 * @throws JAXBException
	 */
	public static void marshal(Class[] clazzs, Object obj, Writer writer) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazzs);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, DEFAULT_ENCODING);
		m.marshal(obj, writer);
	}
}
