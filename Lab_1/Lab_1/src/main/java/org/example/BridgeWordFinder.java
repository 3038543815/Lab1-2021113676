package org.example; // 声明代码所属的包

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 桥接词查询的类
public class BridgeWordFinder {
    private DirectedGraph graph; // 保存有向图的引用

    // 构造方法，接收一个有向图对象作为参数
    public BridgeWordFinder(DirectedGraph graph) {
        this.graph = graph; // 将传入的有向图对象赋值给私有变量
    }

    // 查询桥接词的方法，接收两个单词作为参数，返回包含桥接词及相关信息的字符串数组
    public String[] queryBridgeWords(String word1, String word2) {
        String[] strings = new String[3]; // 创建一个包含3个元素的字符串数组，用于存储结果
        if (!graph.getGraph().containsKey(word1) || !graph.getGraph().containsKey(word2)) {
            // 如果有向图中不包含word1或word2，则说明没有查询到相关单词，返回提示信息
            strings[0] = "NoString";
            strings[1] = "No";
            strings[2] = "No";
            return strings; // 返回结果数组
        }
        Set<String> bridgeWords = new HashSet<>(); // 创建一个存储桥接词的集合
        Map<String, Integer> edgesFromWord1 = graph.getGraph().get(word1); // 获取从word1出发的边集合
        for (String intermediate : edgesFromWord1.keySet()) { // 遍历所有的中间单词
            if (graph.getGraph().getOrDefault(intermediate, Map.of()).containsKey(word2)) {
                // 如果中间单词与word2直接相连，则将其添加到桥接词集合中
                bridgeWords.add(intermediate);
            }
        }
        if (bridgeWords.isEmpty()) { // 如果桥接词集合为空，则说明没有桥接词，返回相应的提示信息
            strings[0] = "EmptyString";
            strings[1] = word1;
            strings[2] = word2;
            return strings; // 返回结果数组
        }
        // 将桥接词集合中的单词用逗号连接成字符串，存入结果数组的第一个位置，word1和word2分别存入结果数组的后两个位置
        strings[0] = String.join(", ", bridgeWords);
        strings[1] = word1;
        strings[2] = word2;
        return strings; // 返回结果数组
    }

    // 打印桥接词的方法，接收两个单词作为参数，返回包含打印信息的字符串
    public String printBridgeWords(String word1, String word2) {
        String[] strings = new String[3]; // 创建一个包含3个元素的字符串数组
        strings = queryBridgeWords(word1, word2); // 调用queryBridgeWords方法查询桥接词并存入结果数组
        // 判断查询结果，如果没有查询到word1或word2，则返回相应的提示信息
        if(strings[0] == "NoString") {
            return "No word1 or word2 in the graph!";
        }
        // 如果查询到word1和word2，但是没有桥接词，则返回相应的提示信息
        if(strings[0] == "EmptyString") {
            return "No bridge words from " + strings[1] + " to " + strings[2] + "!";
        }
        // 如果查询到桥接词，则返回桥接词信息
        return "The bridge words from " + strings[1] + " to " + strings[2] + " are: " + strings[0] + ".";
    }
}
