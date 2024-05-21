package org.example; // 声明代码所属的包

import java.util.*;

// 计算最短路径的类
public class ShortestPathCalculator {
    private DirectedGraph graph; // 存储有向图的引用

    // 构造方法，接收一个有向图对象作为参数
    public ShortestPathCalculator(DirectedGraph graph) {
        this.graph = graph; // 将传入的有向图对象赋值给私有变量
    }

    // 计算最短路径的方法，接收两个单词作为参数，返回最短路径字符串
    public String calcShortestPath(String word1, String word2) {
//        System.out.println(graph.getGraph());
        if (!graph.getGraph().containsKey(word1)) { // 如果有向图中不包含起始单词，则返回无路径信息
            return "No path between " + word1 + " and " + word2 + "!";
        }

        Map<String, Integer> distances = new HashMap<>(); // 存储节点到起始节点的距离
        Map<String, String> previous = new HashMap<>(); // 存储节点的前一个节点
        Set<String> visited = new HashSet<>(); // 存储已访问过的节点

        PriorityQueue<String> nodes = new PriorityQueue<>(Comparator.comparingInt(distances::get)); // 优先队列用于选择距离最近的节点
        distances.put(word1, 0); // 将起始节点的距离设置为0
        nodes.add(word1); // 将起始节点加入优先队列

        while (!nodes.isEmpty()) {
            String closest = nodes.poll(); // 获取距离最近的节点
            if (closest.equals(word2)) { // 如果当前节点是目标节点，则跳出循环
                break;
            }
            visited.add(closest); // 将当前节点标记为已访问

            // 获取当前节点的邻居节点及其距离
            Map<String, Integer> neighbors = graph.getGraph().getOrDefault(closest, Collections.emptyMap());
            for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) {
                String next = neighbor.getKey(); // 获取邻居节点
                int distance = neighbor.getValue(); // 获取当前节点到邻居节点的距离
                if (!visited.contains(next)) { // 如果邻居节点未访问过
                    int alt = distances.get(closest) + distance; // 计算从起始节点到邻居节点的距离
                    if (alt < distances.getOrDefault(next, Integer.MAX_VALUE)) { // 如果新的距离小于已知的距离
                        distances.put(next, alt); // 更新距离
                        previous.put(next, closest); // 更新前一个节点
                        nodes.add(next); // 将邻居节点加入优先队列
                    }
                }
            }
        }

        List<String> path = new ArrayList<>(); // 存储最短路径节点
        for (String at = word2; at != null; at = previous.get(at)) { // 根据前一个节点反向遍历构建路径
            path.add(at);
        }
        Collections.reverse(path); // 反转路径，使其按起始节点到目标节点的顺序排列
        if (path.size() == 1) { // 如果路径长度为1，说明无法到达目标节点
            return "No path between " + word1 + " and " + word2 + "!";
        }
        return "Shortest path: " + String.join(" -> ", path); // 返回最短路径字符串
    }
}
