package org.example; // 声明代码所属的包

import java.util.Random;

// 根据桥接词生成新文本的类
public class NewTextGenerator {
    private BridgeWordFinder bridgeWordFinder; // 存储桥接词查询类的引用

    // 构造方法，接收一个桥接词查询类的实例作为参数
    public NewTextGenerator(BridgeWordFinder bridgeWordFinder) {
        this.bridgeWordFinder = bridgeWordFinder; // 将传入的桥接词查询类的实例赋值给私有变量
    }

    // 生成新文本的方法，接收一个原始文本字符串作为参数，返回生成的新文本字符串
    public String generateNewText(String inputText) {
        String[] words = inputText.toLowerCase().split("\\s+"); // 将原始文本字符串按空格分割成单词数组，并转换为小写
        StringBuilder newText = new StringBuilder(); // 创建一个 StringBuilder 对象，用于构建新文本
        Random random = new Random(); // 创建一个 Random 对象，用于生成随机数

        for (int i = 0; i < words.length - 1; i++) { // 遍历单词数组（不包括最后一个单词）
            String[] strings = new String[3]; // 创建一个长度为3的字符串数组
            newText.append(words[i]).append(" "); // 将当前单词添加到新文本中，并添加一个空格

            // 查询当前单词与下一个单词之间的桥接词
            strings = bridgeWordFinder.queryBridgeWords(words[i], words[i + 1]);

            // 如果查询结果不是空字符串或者没有查询到桥接词
            if (!strings[0].equals("EmptyString") && !strings[0].equals("NoString")) {
                String[] bridges = strings[0].split(", "); // 将查询到的桥接词按逗号分割成数组
                newText.append(bridges[random.nextInt(bridges.length)]).append(" "); // 从桥接词数组中随机选择一个添加到新文本中，并添加一个空格
            }
        }

        // 处理最后一个单词，直接添加到新文本中
        newText.append(words[words.length - 1]);

        return newText.toString(); // 返回生成的新文本字符串
    }
}
