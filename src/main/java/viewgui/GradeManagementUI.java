package viewgui;

import model.GradeManagementModel;
import model.Student;
import view.GradeManagementView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GradeManagementUI {

    private GradeManagementModel model;
    private GradeManagementView view;

    private JFrame frame;
    private JTabbedPane tabbedPane;

    // Add Student Panel Components
    private JTextField addNameField;
    private JTextField addIdField;

    // Remove Student Panel Components
    private JTextField removeNameField;

    // Update Score Panel Components
    private JTextField updateNameField;
    private JTextField updateSubjectField;
    private JTextField updateScoreField;

    // Query Student Panel Components
    private JTextField queryNameField;
    private JTextArea queryResultArea;

    // Display Students Panel Components
    private JTextArea displayResultArea;

    // Sort and Rank Panel Components
    private JTextField rankSubjectField;
    private JTextArea rankResultArea;
    private JButton sortByTotalButton;
    private JButton sortByAverageButton;

    public GradeManagementUI(GradeManagementModel model, GradeManagementView view) {
        this.model = model;
        this.view = view;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("学生成绩管理系统");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();

        createAddStudentPanel();
        createRemoveStudentPanel();
        createUpdateScorePanel();
        createQueryStudentPanel();
        createDisplayStudentsPanel();
        createSortAndRankPanel();

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private void createAddStudentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        addNameField = new JTextField(15);
        addIdField = new JTextField(15);

        JButton addButton = new JButton("添加学生");
        addButton.addActionListener(e -> addStudent());

        panel.add(new JLabel("学生姓名:"));
        panel.add(addNameField);
        panel.add(new JLabel("学生学号:"));
        panel.add(addIdField);
        panel.add(addButton);

        tabbedPane.addTab("添加学生", panel);
    }

    private void createRemoveStudentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        removeNameField = new JTextField(15);
        JButton removeButton = new JButton("删除学生");
        removeButton.addActionListener(e -> removeStudent());

        panel.add(new JLabel("学生姓名:"));
        panel.add(removeNameField);
        panel.add(removeButton);

        tabbedPane.addTab("删除学生", panel);
    }

    private void createUpdateScorePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        updateNameField = new JTextField(15);
        updateSubjectField = new JTextField(15);
        updateScoreField = new JTextField(15);

        JButton updateButton = new JButton("更新成绩");
        updateButton.addActionListener(e -> updateScore());

        panel.add(new JLabel("学生姓名:"));
        panel.add(updateNameField);
        panel.add(new JLabel("科目:"));
        panel.add(updateSubjectField);
        panel.add(new JLabel("成绩:"));
        panel.add(updateScoreField);
        panel.add(updateButton);

        tabbedPane.addTab("更新成绩", panel);
    }

    private void createQueryStudentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        queryNameField = new JTextField(15);
        queryResultArea = new JTextArea();
        queryResultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(queryResultArea);

        JButton queryButton = new JButton("查询成绩");
        queryButton.addActionListener(e -> queryStudent());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("学生姓名:"));
        inputPanel.add(queryNameField);
        inputPanel.add(queryButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("查询成绩", panel);
    }

    private void createDisplayStudentsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayResultArea = new JTextArea();
        displayResultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayResultArea);

        JButton displayButton = new JButton("显示所有学生");
        displayButton.addActionListener(e -> displayStudents());

        panel.add(displayButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("显示所有学生", panel);
    }

    private void createSortAndRankPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        rankSubjectField = new JTextField(15);
        rankResultArea = new JTextArea();
        rankResultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(rankResultArea);

        // 排序按钮
        sortByTotalButton = new JButton("按总分排序");
        sortByAverageButton = new JButton("按平均分排序");
        JButton rankButton = new JButton("按科目排名");

        // 按总分排序的事件
        sortByTotalButton.addActionListener(e -> sortByTotalScore());
        // 按平均分排序的事件
        sortByAverageButton.addActionListener(e -> sortByAverageScore());
        // 按科目排名的事件
        rankButton.addActionListener(e -> rankBySubject());

        JPanel inputPanel = new JPanel();
        inputPanel.add(rankButton);
        inputPanel.add(new JLabel("科目:"));
        inputPanel.add(rankSubjectField);
        inputPanel.add(sortByTotalButton);
        inputPanel.add(sortByAverageButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("排序与排名", panel);
    }

    private void addStudent() {
        String name = addNameField.getText();
        String id = addIdField.getText();
        model.addStudent(id, name);
        showMessage("学生添加成功!");
    }

    private void removeStudent() {
        String name = removeNameField.getText();
        model.removeStudent(name);
        showMessage("学生删除成功!");
    }

    private void updateScore() {
        String name = updateNameField.getText();
        String subject = updateSubjectField.getText();
        String score = updateScoreField.getText();
        model.updateScore(name, subject, score);
        showMessage("成绩更新成功!");
    }

    private void queryStudent() {
        String name = queryNameField.getText();
        Student student = model.findStudent(name);
        queryResultArea.setText("");
        if (student != null) {
            view.displayStudentScoresInTextArea(student, queryResultArea);
        } else {
            queryResultArea.setText("学生未找到!");
        }
    }

    private void displayStudents() {
        displayResultArea.setText("");
        List<Student> students = model.getStudents();
        view.displayStudentsInTextArea(students, displayResultArea);
    }

    private void sortByTotalScore() {
        model.sortByTotalScore();
        displayStudents(); // 显示排序后的学生列表
        showMessage("按总分排序成功!");
    }

    private void sortByAverageScore() {
        model.sortByAverageScore();
        displayStudents(); // 显示排序后的学生列表
        showMessage("按平均分排序成功!");
    }

    private void rankBySubject() {
        String subject = rankSubjectField.getText();
        List<Student> students = model.getStudents();

        students.sort((s1, s2) -> {
            double score1 = parseScore(s1.getScores().getOrDefault(subject, ""));
            double score2 = parseScore(s2.getScores().getOrDefault(subject, ""));
            return Double.compare(score2, score1); // 降序排列
        });

        rankResultArea.setText("按科目 \"" + subject + "\" 成绩排名:\n");
        for (Student student : students) {
            String score = student.getScores().getOrDefault(subject, "无成绩");
            rankResultArea.append("姓名: " + student.getName() + ", " + subject + "成绩: " + score + "\n");
        }
    }

    private double parseScore(String score) {
        if (score.isEmpty()) {
            return 0.0; // 如果为空，则返回0
        }
        try {
            return Double.parseDouble(score);
        } catch (NumberFormatException e) {
            // 如果无法解析，返回0
            return 0.0;
        }
    }


    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public static void main(String[] args) {
        GradeManagementModel model = new GradeManagementModel();
        GradeManagementView view = new GradeManagementView();
        new GradeManagementUI(model, view);
    }
}
