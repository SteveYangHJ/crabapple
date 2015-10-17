package com.crabapple.sample.search;

import java.util.LinkedList;
import java.util.List;

public class NumberNode implements Node {
	private long number;
	private Node parent;
	private List<Node> children;
	
	// Constructors
	NumberNode(long number){
		this(null, null, number);
	}

	NumberNode(Node parent, List<Node> children, long number){
		this.number = number;
		this.parent = parent;
		this.children = children;
	}

	public Node getParent() {
		return this.parent;
	}

	public List<Node> getChildren() {
		return this.children;
	}
	
	/**
	 * tree node:
	 * 		            root(5656)
	 *          level1Node1(2545)          level1Node2(35)
	 * level2Node11(4) level2Node12(85424)     level2Node21(234)
	 *                                            level3Node211(123)
	 * @author Yang, Wei-Peng (244weipeng@gmail.com)
	 * @date Oct 13, 2015 10:44:04 PM
	 * @return
	 */
	public static Node buildDefaultTreeNodes(){
		
		// Root level
		List<Node> rootChildren = new LinkedList<Node>();
		Node root = new NumberNode(null, rootChildren, 5656);
		
		// Level 1
		List<Node> Level1Node1Children = new LinkedList<Node>();
		Node level1Node1 = new NumberNode(root, Level1Node1Children, 2545);
		
		List<Node> Level1Node2Children = new LinkedList<Node>();
		Node level1Node2 = new NumberNode(root, Level1Node2Children, 35);
		
		// Level2
		//List<Node> Level2Node11Children = new LinkedList<Node>();
		Node level2Node11 = new NumberNode(level1Node1, null, 4);
		
		//List<Node> Level2Node12children = new LinkedList<Node>();
		Node level2Node12 = new NumberNode(level1Node1, null, 85424);
		
		List<Node> Level2Node21Children = new LinkedList<Node>();
		Node level2Node21 = new NumberNode(level1Node2, Level2Node21Children, 234);
		
		// Level3
		Node level3Node211 = new NumberNode(level2Node21, null, 123);
		
		Level2Node21Children.add(level3Node211);
		Level1Node2Children.add(level2Node21);
		Level1Node1Children.add(level2Node11);
		Level1Node1Children.add(level2Node12);
		rootChildren.add(level1Node1);
		rootChildren.add(level1Node2);
		
		return root;
	}

	// Getter/Setter
	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

}
