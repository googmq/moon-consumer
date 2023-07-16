package cn.minqi.consumer.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;

/**
 * MainClass
 *
 * @author minqi
 */
public class MainClass {

    /**
     * main方法
     *
     * @param args 主方法参数
     * @throws IOException IO异常
     */
    public static void main(String[] args) throws IOException {

        reverseFile("/Users/minqi/Desktop/reverse");

    }

    /**
     * 文件处理
     *
     * @param fileDir 文件路径
     * @throws IOException IO异常
     */
    private static void reverseFile(String fileDir) throws IOException {
        //读取文件
        List<String> lines = Files.readAllLines(Paths.get(fileDir + "/A"),
                StandardCharsets.UTF_8);

        //创建输出文件
        Path reversePath = Paths.get(fileDir + "/AReverse");
        if (Files.exists(reversePath)) {
            Files.delete(reversePath);
        }
        Files.createFile(reversePath);

        //处理其他情况
        if (CollectionUtils.isEmpty(lines) || 1 == lines.size()) {
            //输出文件
            Files.write(reversePath, lines, StandardOpenOption.APPEND);
            return;
        }

        //处理读取的字符串列表
        List<String> reverseLines = reverse(lines);

        //输出文件
        Files.write(reversePath, reverseLines, StandardOpenOption.APPEND);

    }

    /**
     * 按规则处理字符串列表
     *
     * @param lines 字符串列表
     * @return 处理后的字符串列表
     */
    private static List<String> reverse(List<String> lines) {
        //找到带引号的行
        List<Integer> reverseLine = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("\"")) {
                reverseLine.add(i);
            }
        }
        Optional<Integer> max = reverseLine.stream().max(Comparator.naturalOrder());
        Optional<Integer> min = reverseLine.stream().min(Comparator.naturalOrder());

        for (int left = 0, right = lines.size() - 1; left < right; ++left, --right) {
            //不动的行跳过
            if (max.isPresent() && !max.get().equals(min.get())) {
                if ((left >= min.get() && left <= max.get())
                        || (right >= min.get() && right <= max.get())) {
                    continue;
                }
            }
            //按行内次序反转
            String tmp = lines.get(left);
            lines.set(left, lines.get(right));
            lines.set(right, tmp);
        }

        //非不动的行 反转字符串
        for (int i = 0; i < lines.size(); i++) {
            if (!(max.isPresent() && i >= min.get() && i <= max.get())) {
                lines.set(i, stringReverse(lines.get(i)));
            }
        }
        return lines;
    }

    /**
     * 字符串反转
     *
     * @param str 字符串
     * @return 反转后的字符串
     */
    public static String stringReverse(String str) {
        char[] chars = str.toCharArray();
        for (int left = 0, right = chars.length - 1; left < right; ++left, --right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
        }
        return String.valueOf(chars);
    }

}
