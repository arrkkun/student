package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {

    @Insert("insert into student(student_id, student_name) values(#{id},#{name})")
    void addStudent(String id, String name);

}
