package org.example; // 包名，代码所在的包路径

import java.io.IOException; // 导入 IOException 类
import java.nio.file.Files; // 导入 Files 类
import java.nio.file.Paths; // 导入 Paths 类
import java.util.ArrayList; // 导入 ArrayList 类
import java.util.Arrays; // 导入 Arrays 类
import java.util.List; // 导入 List 类

// 处理文本文件的类
public class TextProcessor {
    // 读取文本文件并处理文本，返回处理后的单词列表
    public static List<String> processTextFile(String filePath) throws IOException {
        List<String> words = new ArrayList<>(); // 创建一个用于存储单词的列表

        // 读取文件内容
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        // 替换所有非字母字符为空格，并将文本转换为小写
        content = content.replaceAll("[^a-zA-Z\\s]", " ").replaceAll("\\s+", " ").toLowerCase();
        // ^ 上方括号内表示取反，即匹配非字母字符，[^a-zA-Z\\s] 表示匹配非字母字符和空格
        // replaceAll 方法将所有非字母字符替换为空格
        // replaceAll("\\s+", " ") 将连续多个空格替换为单个空格
        // toLowerCase 方法将所有字母转换为小写

        // 将处理后的内容分割成单词
        words.addAll(Arrays.asList(content.split(" ")));
        // 使用空格作为分隔符将处理后的内容分割成单词，并添加到单词列表中

        return words; // 返回处理后的单词列表
    }
}
