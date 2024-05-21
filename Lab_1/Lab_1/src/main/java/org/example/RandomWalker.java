package org.example; // 声明代码所属的包

import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// 随机游走的类
public class RandomWalker {
    private DirectedGraph graph; // 存储有向图的引用

    // 构造方法，接收一个有向图对象作为参数
    public RandomWalker(DirectedGraph graph) {
        this.graph = graph; // 将传入的有向图对象赋值给私有变量
    }

    // 随机游走的方法，生成随机游走序列，并保存到文件中，最后返回生成的随机游走序列字符串
    public String randomWalk() {
        DirectedGraph graph_walk = new DirectedGraph(); // 创建一个新的有向图对象，用于记录随机游走的路径

        Random random = new Random(); // 创建一个 Random 对象，用于生成随机数
        List<String> nodes = new ArrayList<>(graph.getGraph().keySet()); // 获取有向图中的节点集合
        String current = nodes.get(random.nextInt(nodes.size())); // 随机选择一个起始节点
        Set<String> visitedEdges = new HashSet<>(); // 创建一个 Set，用于记录已经访问过的边
        StringBuilder walk = new StringBuilder(current); // 创建一个 StringBuilder 对象，用于构建随机游走序列

        while (true) {
            Map<String, Integer> neighbors = graph.getGraph().get(current); // 获取当前节点的邻居节点及其权重
            if (neighbors == null || neighbors.isEmpty()) { // 如果当前节点没有邻居节点，则结束游走
                break;
            }
            List<String> edges = new ArrayList<>(neighbors.keySet()); // 获取当前节点的邻居节点集合
            String next = edges.get(random.nextInt(edges.size())); // 随机选择一个邻居节点作为下一个节点

            graph_walk.addEdge(current, next); // 在记录游走路径的有向图中添加当前节点到下一个节点的边

            String edge = current + "->" + next; // 构建当前边的字符串表示
            if (visitedEdges.contains(edge)) { // 如果当前边已经访问过，则结束游走
                break;
            }
            visitedEdges.add(edge); // 将当前边添加到已访问边的集合中
            walk.append(" ").append(next); // 将下一个节点添加到随机游走序列中
            current = next; // 更新当前节点为下一个节点
        }
        graph_walk.showGraphVisual(); // 可视化记录的游走路径

        // 要保存的字符串
        String content = walk.toString();

        // 文件路径
        String filePath = "output.txt";

        // 使用try-with-resources语句自动关闭资源
        /*
        try (resource1; resource2; ...) {
            // 使用资源的代码块
        } catch (ExceptionType e) {
            // 异常处理代码块
        }
         */
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content); // 将游走序列写入到文件中
            System.out.println("文件保存成功！"); // 输出保存成功提示信息
        } catch (IOException e) {
            System.err.println("保存文件时发生错误：" + e.getMessage()); // 输出保存文件错误信息
        }

        return walk.toString(); // 返回生成的随机游走序列字符串
    }

}
