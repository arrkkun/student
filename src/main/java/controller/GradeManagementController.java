package controller;

import model.*;
import view.*;
import java.util.Scanner;

public class GradeManagementController {
    private GradeManagementModel model;
    private GradeManagementView view;

    public GradeManagementController(GradeManagementModel model, GradeManagementView view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. 添加学生\n2. 删除学生\n3. 更新成绩\n4. 查询成绩\n5. 按总分排序显示\n6. 从 CSV 文件加载学生数据\n7. 清空系统数据\n8. 按平均分排名\n9. 按科目排名\n0. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    /*System.out.print("请输入学生姓名: ");
                    String nameToAdd = scanner.nextLine();
                    model.addStudent(nameToAdd);
                    break;*/
                    System.out.print("请输入学生姓名: ");
                    String nameToAdd = scanner.nextLine();
                    System.out.print("请输入学生学号: ");
                    String studentId = scanner.nextLine();
                    model.addStudent( studentId, nameToAdd);
                    break;
                case 2:
                    System.out.print("请输入要删除的学生姓名: ");
                    String nameToRemove = scanner.nextLine();
                    model.removeStudent(nameToRemove);
                    break;
                case 3:
                    System.out.print("请输入学生姓名: ");
                    String nameToUpdate = scanner.nextLine();
                    System.out.print("请输入科目: ");
                    String subject = scanner.nextLine();
                    System.out.print("请输入成绩: ");
                    String score = scanner.nextLine();
                    model.updateScore(nameToUpdate, subject, score);
                    break;
                case 4:
                    System.out.print("请输入学生姓名: ");
                    String nameToQuery = scanner.nextLine();
                    Student student = model.findStudent(nameToQuery);
                    if (student != null) {
                        view.displayStudentScores(student);
                    } else {
                        view.showMessage("学生未找到!");
                    }
                    break;
                case 5:
                    model.sortByTotalScore();
                    view.displayStudents(model.getStudents());
                    break;
                case 6:
                    System.out.print("请输入CSV文件路径: ");
                    String csvPath = scanner.nextLine();
                    model.loadStudentsFromCSV(csvPath);
                    break;
                case 7:
                    model.clearAllData();
                    view.showMessage("系统数据已清空!");
                    break;
                case 8:
                    model.sortByAverageScore();
                    view.displayAverageScoreRanking(model.getStudents());
                    break;
                case 9:
                    System.out.print("请输入科目: ");
                    String subjectForRanking = scanner.nextLine();
                    /*model.sortBySpecificSubjectScore(subjectForRanking);*/
                    view.displaySpecificSubjectRanking(model.getStudents(), subjectForRanking);
                    break;
                case 0:
                    System.out.println("退出系统");
                    scanner.close();
                    return;
                default:
                    view.showMessage("无效的选择，请重新输入。");
            }
        }
    }
}
