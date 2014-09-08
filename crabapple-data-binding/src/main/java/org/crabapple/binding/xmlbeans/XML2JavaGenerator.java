/**   
 * @Title: XML2JavaGenerator.java 
 * @Package com.hp.steve.common.xmlbeans 
 * @Description: TODO
 * @author Yang, Wei-Peng
 * @email 244weipeng@163.com
 * @date 2012-12-11 上午11:09:59 
 * @version V1.0   
 */
 package org.crabapple.binding.xmlbeans;

import java.util.*;

import javax.wsdl.Definition;
import javax.wsdl.extensions.schema.Schema;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

 /** 
 * @ClassName: XML2JavaGenerator 
 * @Description: TODO
 * @author Yang, Wei-Peng
 * @email 244weipeng@163.com
 * @date 2012-12-11 上午11:09:59 
 */
public class XML2JavaGenerator {
	private static Logger logger = Logger.getLogger(XML2JavaGenerator.class);
	
	public static SchemaTypeSystem compileSchema(Definition def) { 
		logger.info("");
        SchemaTypeSystem sts = null;

        Map nsDefs = def.getNamespaces();
        List schematas = new ArrayList(); //ServiceUtils.getSchemata(def); 不知道这个是哪个的类??

        if (schematas == null) {
            sts = null;
        } else {
            XmlObject [] xmloa = new XmlObject[schematas.size()];
            int i = 0;
            XmlOptions xmlopts = new XmlOptions(); 
            // For imported schematas
            xmlopts.setCompileDownloadUrls();
            xmlopts.setCompileNoUpaRule();

            // For NS prefixes defined in the WSDL element, and inherited into the schema element's scope. 
            xmlopts.setLoadAdditionalNamespaces(nsDefs);

            //xmlopts.setCompileNoValidation();

            //xmlopts.setSaveImplicitNamespaces(nsDefs);
            //xmlopts.setSaveAggressiveNamespaces(); 
            //xmlopts.setSaveNamespacesFirst();
            Iterator it = schematas.iterator();
            while (it.hasNext()) {
                Schema si = (Schema) it.next();
                //addAdditionalNamespaces(si, nsDefs); 
                try {
                    logger.info("Parsing Schema: " + si.getDocumentBaseURI());
                    xmloa[i++] = XmlObject.Factory.parse(si.getElement(), xmlopts); 
                } catch (XmlException e) {
                	logger.info("compileSchema: Error parsing schema: " + si.getDocumentBaseURI(), e);
                    //throw new XmlException("compileSchema: Error parsing schema: " + si.getDocumentBaseURI(), e);
                }
            }

            try {
                logger.info("Compiling schemata..." );

                sts = XmlBeans.compileXsd(xmloa,
                        XmlBeans.getBuiltinTypeSystem(),
                        xmlopts); 

            } catch (XmlException xe) {
                //throw new Exception("compileSchema: Error compiling schemata: ", xe);
            }

        }

        return sts;
    }

}

 