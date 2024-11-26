package viewgui;

import model.GradeManagementModel;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AverageScoreSortUI {
    private GradeManagementModel model;
    private JFrame frame;
    private JTextArea resultArea;

    public AverageScoreSortUI(GradeManagementModel model) {
        this.model = model;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("按平均分排序");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton sortButton = new JButton("按平均分排序");
        sortButton.addActionListener(e -> sortByAverageScore());

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        frame.add(sortButton, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void sortByAverageScore() {
        model.sortByAverageScore();
        resultArea.setText(""); // 清空结果区域
        List<Student> students = model.getStudents();

        // 显示排序后的学生及其平均分
        for (Student student : students) {
            resultArea.append("姓名: " + student.getName() + ", 平均分: " + student.getAverageScore() + "\n");
        }
    }
}
