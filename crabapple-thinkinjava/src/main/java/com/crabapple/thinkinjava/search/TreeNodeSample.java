package com.crabapple.thinkinjava.search;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class TreeNodeSample {
	private static final Logger logger = Logger.getLogger(TreeNodeSample.class);

	/**
	 * @author Yang, Wei-Peng (244weipeng@gmail.com)
	 * @date Oct 13, 2015 10:57:27 PM
	 * @param args
	 */
	public static void main(String[] args) {
		Node root = NumberNode.buildDefaultTreeNodes();
		TreeTraverserImpl impl = new TreeTraverserImpl();
		logger.debug("The depth is:" + impl.getTreeDepth(root));
		
		List<Node> deepestPath = impl.getDeepestPath(root);
		for(Iterator<Node> iter = deepestPath.iterator(); iter.hasNext();){
			logger.debug("Node:" + ((NumberNode)iter.next()).getNumber());
		}

	}

}
