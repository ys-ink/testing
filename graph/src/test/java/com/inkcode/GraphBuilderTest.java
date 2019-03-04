package com.inkcode;

import com.google.common.graph.Graph;
import org.junit.jupiter.api.Test;

@SuppressWarnings("UnstableApiUsage")
class GraphBuilderTest {

  @Test
  void simpleTest() {
    final Graph<String> graph =
        GraphBuilder.directed(String.class).addNode("MyTest").addNode("MyTest2").build();

    GraphAssert.assertThat(graph).hasNodes("MyTest", "MyTest2");
  }

  @Test
  void parentChildTest() {
    final Graph<String> graph =
        GraphBuilder.directed(String.class)
            .addNode("MyTest")
            .addNode("MyTest2")
            .connectNodes()
            .build();

    NodeAssert.assertThat(graph, "MyTest").hasExactChildren("MyTest2");
  }
}
