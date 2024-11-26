package view;

import lombok.var;
import model.Student;

import javax.swing.*;
import java.util.List;

// 该类用于管理和展示学生成绩相关的视图
public class GradeManagementView {
    // 显示学生的姓名和总分
    public void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println("姓名: " + student.getName() + ", 总分: " + student.getTotalScore());
        }
    }

    // 显示特定学生的详细成绩信息
    public void displayStudentScores(Student student) {
        System.out.println("姓名: " + student.getName());
        System.out.println("各科成绩: ");
        for (var entry : student.getScores().entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue() != null ? entry.getValue() : "无成绩");
        }
        System.out.printf("总分: %.2f\n", student.getTotalScore());
        System.out.printf("平均分: %.2f\n", student.getAverageScore());
    }

    // 显示学生的平均分排名
    public void displayAverageScoreRanking(List<Student> students) {
        System.out.println("按平均分排名:");
        for (Student student : students) {
            System.out.printf("姓名: %s, 平均分: %.2f\n", student.getName(), student.getAverageScore());
        }
    }

    // 显示特定科目的成绩排名

    /*public void displaySpecificSubjectRanking(List<Student> students, String subject) {
        System.out.println("按科目 \"" + subject + "\" 成绩排名:");
        for (Student student : students) {
            System.out.printf("姓名: %s, %s成绩: %s\n", student.getName(), subject, student.getScores().getOrDefault(subject, "无成绩"));
        }
    }*/
    public void displaySpecificSubjectRanking(List<Student> students, String subject) {
        // 根据成绩对学生进行排序
        students.sort((s1, s2) -> {
            String scoreStr1 = s1.getScores().getOrDefault(subject, "0");
            String scoreStr2 = s2.getScores().getOrDefault(subject, "0");

            double score1 = scoreStr1.isEmpty() ? 0 : Double.parseDouble(scoreStr1); // 如果为空，则为0
            double score2 = scoreStr2.isEmpty() ? 0 : Double.parseDouble(scoreStr2); // 如果为空，则为0

            return Double.compare(score2, score1); // 降序排序
        });

        System.out.println("按科目 \"" + subject + "\" 成绩排名:");
        for (Student student : students) {
            String scoreStr = student.getScores().getOrDefault(subject, "0"); // 默认值为0
            double score = scoreStr.isEmpty() ? 0 : Double.parseDouble(scoreStr); // 如果为空，则为0
            System.out.printf("姓名: %s, %s成绩: %.2f\n", student.getName(), subject, score);
        }
    }



    // 显示信息
    public void showMessage(String message) {
        System.out.println(message);
    }
    // 显示特定学生的详细成绩信息到 JTextArea
    public void displayStudentScoresInTextArea(Student student, JTextArea textArea) {
        textArea.append("姓名: " + student.getName() + "\n");
        textArea.append("各科成绩:\n");
        for (var entry : student.getScores().entrySet()) {
            textArea.append(entry.getKey() + ": " + (entry.getValue() != null ? entry.getValue() : "无成绩") + "\n");
        }
        textArea.append("总分: " + student.getTotalScore() + "\n");
        textArea.append("平均分: " + student.getAverageScore() + "\n\n");
    }

    // 显示学生列表到 JTextArea
    public void displayStudentsInTextArea(List<Student> students, JTextArea textArea) {
        textArea.append("学生列表:\n");
        for (Student student : students) {
            textArea.append("姓名: " + student.getName() + ", 总分: " + student.getTotalScore() + "\n");
        }
        textArea.append("\n");
    }



}




