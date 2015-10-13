package com.crabapple.thinkinjava.search;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;


//Search: 广度优先(BFS) vs 深度优先(DFS) ??
/**
 * 
 * @author Yang, Wei-Peng (244weipeng@gmail.com)
 * @date Oct 13, 2015 11:10:02 PM
 */
public class TreeTraverserImpl implements ITreeTraverser {
	private static final Logger logger = Logger.getLogger(TreeTraverserImpl.class);

	public int getTreeDepth(Node root) {
		int depth = 0;
		if(root.getChildren() != null && root.getChildren().size() > 0){
			for(Iterator<Node> iter = root.getChildren().iterator(); iter.hasNext();){
				Node node = iter.next();
				depth = Math.max(depth, getTreeDepth(node));
			}
		}
		logger.debug("Depth = " + depth);
		return depth + 1;
	}
	
	public List getDeepestPath(Node root){
		List path = new LinkedList();
		int depth = 0;
		if(root.getChildren() != null && root.getChildren().size() > 0){
			for(Iterator iter = root.getChildren().iterator(); iter.hasNext();){
				Node node = (Node) iter.next();
				List newPath = getDeepestPath(node);
				int newDepth = newPath.size();
				if(newDepth > depth){
					path = newPath;
					depth = newDepth;
				}
			}
			path.add(0, root);
		}
		return path;
	}
}
