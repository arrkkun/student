package model;

import org.springframework.beans.factory.annotation.Autowired;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import model.Student;

// 成绩管理模型类
public class GradeManagementModel {
    private List<Student> students; // 学生列表
    private static final String FILE_PATH = "C:\\Users\\Administrator.DESKTOP-GOJUSDS\\Desktop\\student.csv"; // 学生数据文件路径


    // 构造方法，初始化学生列表并从CSV文件加载学生数据
    public GradeManagementModel() {
        students = new ArrayList<>();
        loadStudentsFromCSV(FILE_PATH);
    }

    // 添加学生的方法
    /*public void addStudent(String name) {
        students.add(new Student(name));
        saveToFile();
    }*/

    public void addStudent(String id,String name) {

       /* students.add(new Student(name));*/
        students.add(new Student(id,name));
        saveToFile();
    }

    // 删除学生的方法
    public void removeStudent(String name) {
        students.removeIf(student -> student.getName().equalsIgnoreCase(name));
        saveToFile();
    }

    // 更新学生成绩的方法
    public void updateScore(String name, String subject, String score) {
        Student student = findStudent(name);
        if (student != null) {
            student.addScore(subject, score);
            saveToFile();
        }
    }

    // 查找学生的方法
    public Student findStudent(String name) {
        return students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // 获取学生列表的方法
    public List<Student> getStudents() {
        return students;
    }

    // 按总成绩排序的方法
    public void sortByTotalScore() {
        students.sort(Comparator.comparingDouble(Student::getTotalScore).reversed());
    }

    // 从CSV文件加载学生数据的方法
    public void loadStudentsFromCSV(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 2) {
                    System.out.println("行格式不正确，跳过: " + line);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();
                /*addStudent(name);*/
                addStudent(id,name);

                for (int i = 2; i < parts.length; i++) {
                    String score = parts[i].trim();
                    String subject = getSubjectByIndex(i - 2);
                    updateScore(name, subject, score);
                }
            }
        } catch (IOException e) {
            System.out.println("读取文件时出错: " + e.getMessage());
        }
    }

    // 清空所有数据的方法
    public void clearAllData() {
        students.clear();
        saveToFile(); // 清空后也写入文件以便保存空数据
    }

    // 按平均成绩排序的方法
    public void sortByAverageScore() {
        students.sort(Comparator.comparingDouble(Student::getAverageScore).reversed());
    }

   /* public  void sortBySpecificSubjectScore(String subject) {

        students.sort(Comparator.comparingDouble(student ->
                Double.parseDouble(student.getScores().getOrDefault(subject, "0"))).reversed());

    }
*/


    // 根据索引获取科目名称的方法
    private String getSubjectByIndex(int index) {
        String[] subjects = {
                "e1", "e2", "e3", "高等数学1-1", "高等数学1-2", "线性代数",
                "大学物理4-1", "信息技术导论", "高级语言程序设计",
                "高级语言程序设计实验", "面向对象程序设计", "计算机组成原理",
                "离散数学", "汇编语言程序设计", "汇编语言程序设计实验",
                "程序设计训练", "计算机组成原理课程设计", "数字系统与逻辑设计",
                "数字系统与逻辑设计实验", "JAVA语言程序设计",
                "计算机专业认知", "思想道德修养与法律基础", "中国近现代史纲要",
                "马克思主义基本原理概论", "毛泽东思想和中国特色社会主义理论体系概论（1）",
                "贵州省情", "体育1", "大学生职业生涯规划", "军事理论及军事训练",
                "大学生心理健康"
        };
        return index >= 0 && index < subjects.length ? subjects[index] : "未知科目";
    }

    // 将学生数据保存到文件的方法
   /* private void saveToFile() {
        // 使用 try-with-resources 确保资源自动关闭
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // 遍历学生列表
            for (Student student : students) {
                // 写入学生姓名
                writer.write(student.getId()+","+student.getName());
                *//*String name = student.getName();
                writer.write(name);*//*
                // 写入成绩
                Map<String, String> scores = student.getScores();
                if (scores.isEmpty()) {
                    writer.write(",无成绩"); // 如果没有成绩，写入提示
                } else {
                    // 遍历成绩并写入文件
                    for (String score : scores.values()) {
                        writer.write("," + score);
                    }
                }
                // 换行
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("写入文件时出错: " + e.getMessage());
        }
    }*/
    private void saveToFile() {
        // 使用 try-with-resources 确保资源自动关闭
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // 遍历学生列表
            for (Student student : students) {
                // 写入学生ID和姓名
                writer.write(student.getId() + "," + student.getName());

                // 写入成绩
                Map<String, String> scores = student.getScores();
                if (scores.isEmpty()) {
                    writer.write(",无成绩"); // 如果没有成绩，写入提示
                } else {
                    // 遍历成绩并写入文件
                    for (String score : scores.values()) {
                        // 转换分数为数字
                        String numericScore = convertScoreToNumeric(score);
                        writer.write("," + numericScore);
                    }
                }
                // 换行
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("写入文件时出错: " + e.getMessage());
        }
    }

    // 辅助方法：将成绩转换为相应的分数
    private String convertScoreToNumeric(String score) {
        switch (score) {
            case "优":
                return "90";
            case "良":
                return "80";
            case "中":
                return "70";
            case "差":
                return "60";
            default:
                return score; // 如果不符合转换条件，返回原始成绩
        }
    }


}

