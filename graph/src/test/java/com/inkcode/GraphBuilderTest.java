package com.inkcode;

import com.google.common.graph.Graph;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("UnstableApiUsage")
class GraphBuilderTest {

  @Test
  void simpleTest() {
    final Graph<String> graph =
        GraphBuilder.directed(String.class)
            .addNode("MyTest")
            .done()
            .addNode("MyTest2")
            .done()
            .build();

    GraphAssert.assertThat(graph).hasNodes("MyTest", "MyTest2");
  }

  @Test
  void parentChildTest() {
    final Graph<String> graph =
        GraphBuilder.directed(String.class)
            .addNode("MyTest")
            .addNode("MyTest2")
            .done()
            .done()
            .build();

    NodeAssert.assertThat(graph, "MyTest").hasExactChildren("MyTest2");
  }

  @Test
  void twoNodesTest() {
    final Graph<String> graph =
        GraphBuilder.directed(String.class)
            .addNode("MyTest")
            .done()
            .addNode("MyTest2")
            .done()
            .build();

    GraphAssert.assertThat(graph).hasNodesExactly("MyTest", "MyTest2");
    NodeAssert.assertThat(graph, "MyTest").hasNoChildren();
  }

  @Test
  void graphAlreadyBuilt() {
    final GraphBuilder<String> builder = GraphBuilder.directed();
    builder.build();
    Assertions.assertThatThrownBy(builder::build)
        .isInstanceOf(UnsupportedOperationException.class)
        .hasMessage("Graph already built!");
  }

  @Test
  void testDoneUsedCorrectly() {
    final GraphBuilder<String> builder = GraphBuilder.directed();
    builder.addNode("Test");

    Assertions.assertThatThrownBy(builder::build)
        .isInstanceOf(UnsupportedOperationException.class)
        .hasMessage("done() not called enough. Expected 1 more done() call");
  }

  @Test
  void complexNodeSetup() {
    final GraphBuilder<String> builder = GraphBuilder.directed();

    builder.addNode("Parent1").addNode("Child1").addNode("Child1-1").done().done().done();
    builder.addNode("Parent2").addNode("Child2").addNode("Child2-1").done().done().done();
    final Graph<String> graph = builder.build();

    GraphAssert.assertThat(graph)
        .hasNodesExactly("Parent1", "Parent2", "Child1", "Child1-1", "Child2", "Child2-1");
    NodeAssert.assertThat(graph, "Parent1").hasExactChildren("Child1");
    NodeAssert.assertThat(graph, "Child1").hasExactChildren("Child1-1");
    NodeAssert.assertThat(graph, "Parent2").hasExactChildren("Child2");
    NodeAssert.assertThat(graph, "Child2").hasExactChildren("Child2-1");
  }

  @Test
  void testParentTwoChildren() {
    final GraphBuilder<String> builder = GraphBuilder.directed();

    final Graph<String> graph = builder.build();
    builder.addNode("Parent").addNode("Child1").done().addNode("Child2");
    GraphAssert.assertThat(graph).hasNodesExactly("Parent", "Child1", "Child2");
    NodeAssert.assertThat(graph, "Parent").hasExactChildren("Child1", "Child2");
    NodeAssert.assertThat(graph, "Child1").hasNoChildren();
    NodeAssert.assertThat(graph, "Child2").hasNoChildren();
  }
}
