// 主类，程序的入口点
public class Main {
    // 主方法，程序执行的起始点
    public static void main(String[] args) {
        // 创建成绩管理模型实例
        model.GradeManagementModel model = new model.GradeManagementModel();
        // 创建成绩管理视图实例
        view.GradeManagementView view = new view.GradeManagementView();
        // 创建成绩管理控制器实例，并关联模型和视图
        controller.GradeManagementController controller = new controller.GradeManagementController(model, view);

        // 启动控制器
        controller.start();
    }
}

