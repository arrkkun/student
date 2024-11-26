package model;

import mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import model.Student;
import org.springframework.stereotype.Component;
import view.GradeManagementView;

// 成绩管理模型类
@Component
public class GradeManagementModel {
//    private List<Student> students;

    @Autowired
    private StudentMapper studentMapper;// 学生列表
//    private static final String FILE_PATH = "C:\\Users\\Administrator.DESKTOP-GOJUSDS\\Desktop\\student.csv"; // 学生数据文件路径
//
//
//    // 构造方法，初始化学生列表并从CSV文件加载学生数据
//    public GradeManagementModel() {
//        students = new ArrayList<>();
//        loadStudentsFromCSV(FILE_PATH);
//    }
//
//    // 添加学生的方法
//    /*public void addStudent(String name) {
//        students.add(new Student(name));
//        saveToFile();
//    }*/
//
//    public void addStudent(String id,String name) {
//
//       /* students.add(new Student(name));*/
////        students.add(new Student(id,name));
//        //saveToFile();
//        studentMapper.addStudent(id,name);
//    }
////
//    // 删除学生的方法
//    public void removeStudent(String name) {
//        students.removeIf(student -> student.getName().equalsIgnoreCase(name));
//        //saveToFile();
//    }

//    // 更新学生成绩的方法
//    public void updateScore(String name, String subject, String score) {
//        Student student = findStudent(name);
//        if (student != null) {
//            student.addScore(subject, score);
//            saveToFile();
//        }
//    }
//
//    // 查找学生的方法
//    public Student findStudent(String name) {
//        return students.stream()
//                .filter(student -> student.getName().equalsIgnoreCase(name))
//                .findFirst()
//                .orElse(null);
//    }
//
//    // 获取学生列表的方法
//    public List<Student> getStudents() {
//        return students;
//    }
//
//    // 按总成绩排序的方法
//    public void sortByTotalScore() {
//        students.sort(Comparator.comparingDouble(Student::getTotalScore).reversed());
//    }
//
//    // 从CSV文件加载学生数据的方法
//    public void loadStudentsFromCSV(String filePath) {
//        try {
//            List<String> lines = Files.readAllLines(Paths.get(filePath));
//            for (String line : lines) {
//                if (line.trim().isEmpty()) continue;
//
//                String[] parts = line.split(",");
//                if (parts.length < 2) {
//                    System.out.println("行格式不正确，跳过: " + line);
//                    continue;
//                }
//
//                String id = parts[0].trim();
//                String name = parts[1].trim();
//                /*addStudent(name);*/
//                addStudent(id,name);
//
//                for (int i = 2; i < parts.length; i++) {
//                    String score = parts[i].trim();
//                    String subject = getSubjectByIndex(i - 2);
//                    updateScore(name, subject, score);
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("读取文件时出错: " + e.getMessage());
//        }
//    }
//
//    // 清空所有数据的方法
//    public void clearAllData() {
//        students.clear();
//        saveToFile(); // 清空后也写入文件以便保存空数据
//    }
//
//    // 按平均成绩排序的方法
//    public void sortByAverageScore() {
//        students.sort(Comparator.comparingDouble(Student::getAverageScore).reversed());
//    }
//
//   /* public  void sortBySpecificSubjectScore(String subject) {
//
//        students.sort(Comparator.comparingDouble(student ->
//                Double.parseDouble(student.getScores().getOrDefault(subject, "0"))).reversed());
//
//    }
//*/
//
//
//    // 根据索引获取科目名称的方法
//    private String getSubjectByIndex(int index) {
//        String[] subjects = {
//                "e1", "e2", "e3", "高等数学1-1", "高等数学1-2", "线性代数",
//                "大学物理4-1", "信息技术导论", "高级语言程序设计",
//                "高级语言程序设计实验", "面向对象程序设计", "计算机组成原理",
//                "离散数学", "汇编语言程序设计", "汇编语言程序设计实验",
//                "程序设计训练", "计算机组成原理课程设计", "数字系统与逻辑设计",
//                "数字系统与逻辑设计实验", "JAVA语言程序设计",
//                "计算机专业认知", "思想道德修养与法律基础", "中国近现代史纲要",
//                "马克思主义基本原理概论", "毛泽东思想和中国特色社会主义理论体系概论（1）",
//                "贵州省情", "体育1", "大学生职业生涯规划", "军事理论及军事训练",
//                "大学生心理健康"
//        };
//        return index >= 0 && index < subjects.length ? subjects[index] : "未知科目";
//    }
//
//    // 将学生数据保存到文件的方法
//   /* private void saveToFile() {
//        // 使用 try-with-resources 确保资源自动关闭
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
//            // 遍历学生列表
//            for (Student student : students) {
//                // 写入学生姓名
//                writer.write(student.getId()+","+student.getName());
//                *//*String name = student.getName();
//                writer.write(name);*//*
//                // 写入成绩
//                Map<String, String> scores = student.getScores();
//                if (scores.isEmpty()) {
//                    writer.write(",无成绩"); // 如果没有成绩，写入提示
//                } else {
//                    // 遍历成绩并写入文件
//                    for (String score : scores.values()) {
//                        writer.write("," + score);
//                    }
//                }
//                // 换行
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            System.out.println("写入文件时出错: " + e.getMessage());
//        }
//    }*/
//    private void saveToFile() {
//        // 使用 try-with-resources 确保资源自动关闭
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
//            // 遍历学生列表
//            for (Student student : students) {
//                // 写入学生ID和姓名
//                writer.write(student.getId() + "," + student.getName());
//
//                // 写入成绩
//                Map<String, String> scores = student.getScores();
//                if (scores.isEmpty()) {
//                    writer.write(",无成绩"); // 如果没有成绩，写入提示
//                } else {
//                    // 遍历成绩并写入文件
//                    for (String score : scores.values()) {
//                        // 转换分数为数字
//                        String numericScore = convertScoreToNumeric(score);
//                        writer.write("," + numericScore);
//                    }
//                }
//                // 换行
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            System.out.println("写入文件时出错: " + e.getMessage());
//        }
//    }
//
//    // 辅助方法：将成绩转换为相应的分数
//    private String convertScoreToNumeric(String score) {
//        switch (score) {
//            case "优":
//                return "90";
//            case "良":
//                return "80";
//            case "中":
//                return "70";
//            case "差":
//                return "60";
//            default:
//                return score; // 如果不符合转换条件，返回原始成绩
//        }
//    }
    /**采用连接数据库的方法
     *
     */


    private Connection connection; // 数据库连接
    private List<Student> students; // 学生列表

    // 构造方法，初始化学生列表并从数据库加载学生数据
    public GradeManagementModel() {
        students = new ArrayList<>();
        connectToDatabase();
        loadStudentsFromDatabase();
    }

    private void connectToDatabase() {
        try {
            // 加载 JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zhuoye", "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 从数据库加载学生数据的方法
//    public void loadStudentsFromDatabase() {
//        try (Statement stmt = connection.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM sc")) {
//            while (rs.next()) {
//                String id = rs.getString("id");
//                String name = rs.getString("name");
//                Student student = new Student(id, name);
//                students.add(student);
//                loadScoresForStudent(student); // 加载对应的成绩
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    private void loadStudentsFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/zhuoye";
        String user = "root";
        String password = "1234";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT student_id, student_name FROM sc")) {

            while (rs.next()) {
                String id = rs.getString("student_id");
                String name = rs.getString("student_name");
                Student student = new Student(id, name);
                students.add(student);
                loadScoresForStudent(student); // 加载对应的成绩
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 加载学生对应的成绩的方法
//    private void loadScoresForStudent(Student student) {
//        try (PreparedStatement pstmt = connection.prepareStatement("SELECT subject, score FROM sc WHERE student_id = ?")) {
//            pstmt.setString(1, student.getId() + "");
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String subject = rs.getString("subject");
//                String score = rs.getString("score");
//                student.addScore(subject, score);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    // 加载学生对应的成绩的方法
    private void loadScoresForStudent(Student student) {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT e1, e2, e3, calculus1_1, calculus1_2, linear_algebra, physics, intro_info_tech, advanced_program_design, advanced_program_design_lab, object_oriented_programming, computer_architecture, discrete_math, assembly_language, assembly_language_lab, programming_training, computer_architecture_design, digital_systems_logic_design, digital_systems_logic_lab, java_program_design, computer_profession_recognition, moral_and_legal_education, modern_chinese_history, marxist_principle, maozedong_thought, guizhou_provincial_conditions, physical_education, career_planning, military_theory_training, college_mental_health FROM sc WHERE student_id = ?")) {
            pstmt.setString(1, String.valueOf(student.getId()));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { // 只需检查一次，因为每个学生只有一行记录
                // 为了方便，使用一个循环来遍历每一门课程
                String[] subjects = {
                        "e1", "e2", "e3", "calculus1_1", "calculus1_2", "linear_algebra",
                        "physics", "intro_info_tech", "advanced_program_design",
                        "advanced_program_design_lab", "object_oriented_programming",
                        "computer_architecture", "discrete_math", "assembly_language",
                        "assembly_language_lab", "programming_training",
                        "computer_architecture_design", "digital_systems_logic_design",
                        "digital_systems_logic_lab", "java_program_design",
                        "computer_profession_recognition", "moral_and_legal_education",
                        "modern_chinese_history", "marxist_principle",
                        "maozedong_thought", "guizhou_provincial_conditions",
                        "physical_education", "career_planning", "military_theory_training",
                        "college_mental_health"
                };

                for (String subject : subjects) {
                    int score = rs.getInt(subject); // 使用 int 类型获取分数
                    student.addScore(subject,String.valueOf(score)); // 假设 addScore 方法接收整数类型分数
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 添加学生的方法
    public void addStudent(String id, String name) {
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO sc (student_id, student_name) VALUES (?, ?)")) {
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            students.add(new Student(id, name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除学生的方法
    public void removeStudent(String studentId) {
        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM sc WHERE id = ?")) {
            pstmt.setString(1, studentId);
            pstmt.executeUpdate();

            // 从内存中移除学生
            students.removeIf(student -> student.getId() == Integer.parseInt(studentId));
//            students.removeIf(student -> student.getName().equalsIgnoreCase(name))
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新学生成绩的方法
//    public void updateScore(String name, String subject, String score) {
//        Student student = findStudent(name);
//        if (student != null) {
//            student.addScore(subject, score);
//            saveScoreToDatabase( String.valueOf(student.getId()), subject, score);
//        }
//    }
//    public void updateScore(String name, String subject, String score) {
//        Student student = findStudent(name);
//        if (student != null) {
//            student.addScore(subject, score);
//            try {
//                saveScoreToDatabase(
//                        String.valueOf(student.getId()),
//                        student.getName(), // 获取学生姓名
//                        subject,
//                        Integer.parseInt(score) // 转换为 int 类型
//                );
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                // 处理转换失败的情况
//            }
//        }
//    }
//    public void updateScore(String name, String subject, String score) {
//
//        String sql = "UPDATE sc SET score = ? WHERE student_name = ? AND subject = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setInt(1, Integer.parseInt(score));
//            preparedStatement.setString(2, name);
//            preparedStatement.setString(3, subject);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // 处理数据库操作中的异常
//        }
//    }
    public void updateScore(String studentName, String subject, String score) {
        // 检查科目是否有效
        int subjectIndex = getSubjectIndex(subject);
        if (subjectIndex == -1) {
            System.out.println("无效的科目名称: " + subject);
            return;
        }

        // 创建 SQL 语句，用于更新成绩
        String sql = "UPDATE sc SET " + subject + " = ? WHERE student_name = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(score)); // 设置成绩
            pstmt.setString(2, studentName); // 设置学生姓名
            int rowsAffected = pstmt.executeUpdate(); // 执行更新操作

            // 检查更新是否成功
            if (rowsAffected > 0) {
                System.out.println("成功更新" + studentName + "的" + subject + "成绩为: " + score);
            } else {
                System.out.println("未找到名为" + studentName + "的学生，更新失败。");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 输出异常信息
        }
    }



     //保存成绩到数据库的方法
//    private void updateScore(String studentName, String subject, String score) {
//        // 创建一条 SQL 语句，使用 CASE WHEN 来动态更新相应科目的分数
//        String sql = "INSERT INTO sc (student_id, student_name, " +
//                "e1, e2, e3, calculus1_1, calculus1_2, linear_algebra, physics, " +
//                "intro_info_tech, advanced_program_design, advanced_program_design_lab, " +
//                "object_oriented_programming, computer_architecture, discrete_math, " +
//                "assembly_language, assembly_language_lab, programming_training, " +
//                "computer_architecture_design, digital_systems_logic_design, " +
//                "digital_systems_logic_lab, java_program_design, " +
//                "computer_profession_recognition, moral_and_legal_education, " +
//                "modern_chinese_history, marxist_principle, maozedong_thought, " +
//                "guizhou_provincial_conditions, physical_education, career_planning, " +
//                "military_theory_training, college_mental_health) " +
//                "VALUES (?, ?, " +
//                "?, ?, ?, ?, ?, ?, ?, ?, " +
//                "?, ?, ?, ?, ?, ?, ?, ?, " +
//                "?, ?, ?, ?, ?, ?, ?, ?, ?, " +
//                "?, ?, ?)" +
//                " ON DUPLICATE KEY UPDATE " +
//                 subject + " = ?";
//
//        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setString(1, studenName);
//            pstmt.setString(2, studentName);
//
//            // 设置其他科目分数为 null
//            for (int i = 3; i <= 34; i++) {
//                pstmt.setNull(i, java.sql.Types.INTEGER);
//            }
//
//            // 根据科目名称的位置动态设置分数
//            int subjectIndex = getSubjectIndex(subject);
//            if (subjectIndex != -1) {
//                pstmt.setInt(subjectIndex, score); // 设置实际分数
//                pstmt.setInt(subjectIndex, score); // 在 ON DUPLICATE KEY UPDATE 中设置
//            }
//
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    private void saveScoreToDatabase(String studentId, String studentName, String subject, int score) {
//        // 创建一条 SQL 语句，使用 CASE WHEN 来动态更新相应科目的分数
//        String sql = "INSERT INTO sc (student_id, student_name, " +
//                "e1, e2, e3, calculus1_1, calculus1_2, linear_algebra, physics, " +
//                "intro_info_tech, advanced_program_design, advanced_program_design_lab, " +
//                "object_oriented_programming, computer_architecture, discrete_math, " +
//                "assembly_language, assembly_language_lab, programming_training, " +
//                "computer_architecture_design, digital_systems_logic_design, " +
//                "digital_systems_logic_lab, java_program_design, " +
//                "computer_profession_recognition, moral_and_legal_education, " +
//                "modern_chinese_history, marxist_principle, maozedong_thought, " +
//                "guizhou_provincial_conditions, physical_education, career_planning, " +
//                "military_theory_training, college_mental_health) " +
//                "VALUES (?, ?, " +
//                "?, ?, ?, ?, ?, ?, ?, ?, " +
//                "?, ?, ?, ?, ?, ?, ?, ?, " +
//                "?, ?, ?, ?, ?, ?, ?, ?, ?, " +
//                "?, ?, ?)" +
//                " ON DUPLICATE KEY UPDATE " +
//                subject + " = ?";
//
//        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setString(1, studentId);
//            pstmt.setString(2, studentName);
//
//            // 设置其他科目分数为 null
//            for (int i = 3; i <= 34; i++) {
//                pstmt.setNull(i, java.sql.Types.INTEGER);
//            }
//
//            // 根据科目名称的位置动态设置分数
//            int subjectIndex = getSubjectIndex(subject);
//            if (subjectIndex != -1) {
//                pstmt.setInt(subjectIndex, score); // 设置实际分数
//                pstmt.setInt(subjectIndex, score); // 在 ON DUPLICATE KEY UPDATE 中设置
//            }
//
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    // 方法：根据科目名称获取对应的索引
    private int getSubjectIndex(String subject) {
        switch (subject) {
            case "e1": return 3;
            case "e2": return 4;
            case "e3": return 5;
            case "calculus1_1": return 6;
            case "calculus1_2": return 7;
            case "linear_algebra": return 8;
            case "physics": return 9;
            case "intro_info_tech": return 10;
            case "advanced_program_design": return 11;
            case "advanced_program_design_lab": return 12;
            case "object_oriented_programming": return 13;
            case "computer_architecture": return 14;
            case "discrete_math": return 15;
            case "assembly_language": return 16;
            case "assembly_language_lab": return 17;
            case "programming_training": return 18;
            case "computer_architecture_design": return 19;
            case "digital_systems_logic_design": return 20;
            case "digital_systems_logic_lab": return 21;
            case "java_program_design": return 22;
            case "computer_profession_recognition": return 23;
            case "moral_and_legal_education": return 24;
            case "modern_chinese_history": return 25;
            case "marxist_principle": return 26;
            case "maozedong_thought": return 27;
            case "guizhou_provincial_conditions": return 28;
            case "physical_education": return 29;
            case "career_planning": return 30;
            case "military_theory_training": return 31;
            case "college_mental_health": return 32;
            default: return -1; // 不支持的科目名称
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
        students.sort((s1, s2) -> Double.compare(s2.getTotalScore(), s1.getTotalScore()));
    }

    // 清空所有数据的方法
    public void clearAllData() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM sc");
//            stmt.executeUpdate("DELETE FROM scores");
            students.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 按平均成绩排序的方法
    public void sortByAverageScore() {
        students.sort((s1, s2) -> Double.compare(s2.getAverageScore(), s1.getAverageScore()));
    }
}



