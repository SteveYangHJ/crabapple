package com.crabapple.sample.search;

import java.util.List;

public interface Node {
	Node getParent();
	List<Node> getChildren();
}
