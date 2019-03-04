package com.github.ys_ink;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.jupiter.api.Test;

@SuppressWarnings("UnstableApiUsage")
class NodeAssertTest {

  @Test
  void nodeHasExactChildren() {
    final MutableGraph<String> graph = GraphBuilder.directed().build();
    final String rootNode = "root";
    final String childNode = "child1";
    final String childNode2 = "child2";
    graph.addNode(rootNode);
    graph.addNode(childNode);
    graph.addNode(childNode2);
    graph.putEdge(rootNode, childNode);
    graph.putEdge(rootNode, childNode2);

    NodeAssert.assertThat(graph, rootNode).hasExactChildren(childNode, childNode2);
  }
}
