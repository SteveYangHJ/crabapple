package org.crabapple.binding.model.manager;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Steve Yang, 2012-8-8 16:34:04
 */
public class W3CDomUtil {
	/**
	 * 输出W3C中定义的NodelList
	 * @param nodeList
	 */
	public static StringBuffer displayW3CNodeList(NodeList nodeList){
		StringBuffer sb = new StringBuffer();
		if(null != nodeList){
			for(int i=0;i<nodeList.getLength();i++){
				sb.append(displayW3CNode(nodeList.item(i)));
			}
		}else{
			sb.append("\t nodeList is null!");
		}
		return sb;
	}
	/**
	 * 输出显示W3C中定义的Node
	 * @param node
	 */
	public static StringBuffer displayW3CNode(Node node){
		StringBuffer sb = new StringBuffer();
		if(null != node){
			sb.append("\tNode.getLocalName()= " + node.getLocalName() + "\n");
			sb.append("\tNode.getTextContent()= " + node.getTextContent() + "\n");
			sb.append("\tNode.getBaseURI()= " + node.getBaseURI() + "\n");
			sb.append("\tNode.getNamespaceURI()= " + node.getNamespaceURI() + "\n");
			sb.append("\tNode.getNodeName()= " + node.getNodeName() + "\n");
			sb.append("\tNode.getNodeValue()= " + node.getNodeValue() + "\n");
			sb.append("\tNode.getNodeType()= " + node.getNodeType() + "\n");
			sb.append("\tNode.getPrefix()= " + node.getPrefix() + "\n");
			// 输出子节点
			sb.append(displayW3CNodeList(node.getChildNodes()));
		}else{
			sb.append("\t Node is null!");
		}
		return sb;
	}
}
