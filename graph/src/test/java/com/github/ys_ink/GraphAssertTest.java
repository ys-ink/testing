package com.github.ys_ink;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import org.junit.jupiter.api.Test;

@SuppressWarnings("UnstableApiUsage")
class GraphAssertTest {

  @Test
  void hasNodeTest() {
    final MutableGraph<String> graph = GraphBuilder.directed().build();
    graph.addNode("MyTest");

    GraphAssert.assertThat(graph).hasNode("MyTest");
  }

  @Test
  void hasNodesExactlyTest() {
    final MutableGraph<String> graph = GraphBuilder.directed().build();
    graph.addNode("MyTest");
    graph.addNode("MyTest2");

    GraphAssert.assertThat(graph).hasNodesExactly("MyTest", "MyTest2");
  }

  @Test
  void hasNodesTest() {
    final MutableGraph<String> graph = GraphBuilder.directed().build();
    graph.addNode("MyTest");
    graph.addNode("MyTEst2");

    GraphAssert.assertThat(graph).hasNodes("MyTest");
  }

  @Test
  void hasNodeExactOrder() {
    final MutableGraph<String> graph = GraphBuilder.directed().build();
    final String rootNode = "root";
    final String childNode = "child";

    graph.addNode(rootNode);
    graph.addNode(childNode);
    graph.putEdge(rootNode, childNode);
    GraphAssert.assertThat(graph).hasNodesExactOrder(rootNode, childNode);
  }
}
