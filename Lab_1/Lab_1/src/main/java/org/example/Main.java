package org.example; // 声明代码所属的包

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// 主类
public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 1) { // 如果命令行参数长度小于1，即没有提供文件路径
                System.out.println("Usage: java Main <file_path>"); // 提示用户正确的使用方法
                return; // 程序结束
            }
            // 读取并处理文本文件
            List<String> words = TextProcessor.processTextFile(args[0]); // 调用TextProcessor类的静态方法，处理文本文件，并返回单词列表
            DirectedGraph graph = new DirectedGraph(); // 创建一个有向图对象

            // 构建有向图，将单词列表中相邻的单词作为边添加到有向图中
            for (int i = 0; i < words.size() - 1; i++) {
                graph.addEdge(words.get(i), words.get(i + 1));
            }

            // 初始化功能类，创建各种功能对象，例如BridgeWordFinder、NewTextGenerator、ShortestPathCalculator和RandomWalker
            BridgeWordFinder bridgeWordFinder = new BridgeWordFinder(graph);
            NewTextGenerator newTextGenerator = new NewTextGenerator(bridgeWordFinder);
            ShortestPathCalculator shortestPathCalculator = new ShortestPathCalculator(graph);
            RandomWalker randomWalker = new RandomWalker(graph);

            // 提供用户交互菜单
            Scanner scanner = new Scanner(System.in); // 创建Scanner对象，用于接收用户输入
            while (true) { // 循环显示菜单，直到用户选择退出
                System.out.println("Choose an option:");
                System.out.println("1. Show Directed Graph");
                System.out.println("2. Query Bridge Words");
                System.out.println("3. Generate New Text");
                System.out.println("4. Calculate Shortest Path");
                System.out.println("5. Random Walk");
                System.out.println("6. Exit");
                int choice = scanner.nextInt(); // 读取用户输入的选项
                scanner.nextLine(); // 读取换行符

                // 根据用户选择执行相应的操作
                switch (choice) {
                    case 1: // 显示有向图的信息
                        graph.displayGraph();
                        graph.showGraphVisual();
                        break;
                    case 2: // 查询桥接词
                        System.out.print("Enter word1: ");
                        String word1 = scanner.nextLine();
                        System.out.print("Enter word2: ");
                        String word2 = scanner.nextLine();
                        System.out.println(bridgeWordFinder.printBridgeWords(word1, word2));
                        break;
                    case 3: // 生成新文本
                        System.out.print("Enter new text: ");
                        String inputText = scanner.nextLine();
                        System.out.println(newTextGenerator.generateNewText(inputText));
                        break;
                    case 4: // 计算最短路径
                        System.out.print("Enter word1: ");
                        word1 = scanner.nextLine();
                        System.out.print("Enter word2: ");
                        word2 = scanner.nextLine();
                        System.out.println(shortestPathCalculator.calcShortestPath(word1, word2));
                        break;
                    case 5: // 随机游走
                        System.out.println(randomWalker.randomWalk());
                        break;
                    case 6: // 退出程序
                        return;
                    default: // 无效选项
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } catch (IOException e) { // 捕获可能出现的 IO 异常
            e.printStackTrace(); // 打印异常信息
        }
    }
}
