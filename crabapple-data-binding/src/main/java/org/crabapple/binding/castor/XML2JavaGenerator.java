package org.crabapple.binding.castor;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

public class XML2JavaGenerator {
	public static final String DEFAULT_ENCODING = "UTF-8";
	
	/**
	 * @Description marshal object to XML data
	 * @author Steve Yang (wei-peng.yang@hp.com)
	 * @Date 2013-08-20 11:16:00 AM
	 * @param object  object to be marshaled
	 * @return string  XML data
	 * @throws MarshalException
	 * @throws ValidationException
	 */
	public static String marshal(Object object) throws MarshalException, ValidationException{	
		StringWriter sw = new StringWriter();
		Marshaller.marshal(object, sw);	
		return sw.toString();
	}
	
	/**
	 * @Description marshal object to XML data
	 * @author Steve Yang (wei-peng.yang@hp.com)
	 * @Date 2013-08-20 11:16:00 AM
	 * @param object  object to be marshaled
	 * @param mapping  Mapping object, define the rules of marshal
	 * @return string  XML data
	 * @throws MarshalException
	 * @throws ValidationException
	 * @throws IOException
	 * @throws MappingException
	 */
	public static String marshal(Object object, Mapping mapping) throws MarshalException, ValidationException, IOException, MappingException{	
		StringWriter sw = new StringWriter();
		Marshaller marshaller = new Marshaller(sw);
		marshaller.setEncoding(DEFAULT_ENCODING);
		marshaller.setMapping(mapping);
		marshaller.marshal(object);
		return sw.toString();
	}
	
	/**
	 * @Description marshal object to XML data
	 * @author Steve Yang (wei-peng.yang@hp.com)
	 * @Date 2013-08-20 11:16:00 AM
	 * @param clazz  the class of object which is unmarshaled from XML data
	 * @param content  XML data
	 * @return object  
	 * @throws MarshalException
	 * @throws ValidationException
	 */
	public static Object unmarshal(Class clazz, String content) throws MarshalException, ValidationException{
		StringReader sr = new StringReader(content);
		return Unmarshaller.unmarshal(clazz, sr);
	}
	
	/**
	 * @Description marshal object to XML data
	 * @author Steve Yang (wei-peng.yang@hp.com)
	 * @Date 2013-08-20 11:16:00 AM
	 * @param clazz  the class of object which is unmarshaled from XML data
	 * @param content  XML data
	 * @param mapping  Mapping object, define the rules of unmarshal
	 * @return
	 * @throws MarshalException
	 * @throws ValidationException
	 * @throws MappingException
	 */
	public static Object unmarshal(Class clazz, String content, Mapping mapping) throws MarshalException, ValidationException, MappingException{
		StringReader sr = new StringReader(content);
		Unmarshaller unmarshaller = new Unmarshaller();
		unmarshaller.setClass(clazz);
		unmarshaller.setMapping(mapping);
		return unmarshaller.unmarshal(sr);
	}

}
