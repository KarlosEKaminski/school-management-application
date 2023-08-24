package DAO;
import Controller.Conn;
import Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class StudentDAO {
    private final Connection conn;

    public StudentDAO() {
        new Conn();
        this.conn = Conn.getConnection();
    }

    public List<Student> listStudent() {
        String sql = "SELECT * FROM student";
        List<Student> returns = new ArrayList<>();
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Student s1 = new Student();
                s1.setId(result.getInt("id_student"));
                s1.setName(result.getString("name_student"));
                s1.setAge(result.getInt("age_student"));
                s1.setCourse(result.getString("course_student"));
                s1.setStage(result.getInt("stage_student"));

                returns.add(s1);
                System.out.println("\n[ ID: " + s1.getId() + "\nName: " + s1.getName() + "\nAge: " + s1.getAge() + "\nCourse: " + s1.getCourse() + "\nStage: " + s1.getStage() + " ]");
            }
        } catch (Exception e) {
            System.out.println("Error to list: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return returns;
    }

    public boolean insertStudent(Student student) {
        String sql = "INSERT INTO student(id_student, name_student, age_student, course_student, stage_student)" + "VALUES (?, ?, ?, ?,?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getAge());
            stmt.setString(4, student.getCourse());
            stmt.setInt(5, student.getStage());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Inserted successfully!");
            return true;
        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public void deleteStudent(int id_student) {
        String sql = "DELETE FROM student WHERE id_student=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id_student);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Deleted successfully!");
        } catch (Exception e) {
            System.out.printf("Error to delete: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE student SET name_student=?, age_student=?, course_student=?, stage_student=? WHERE id_student=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getStage());
            stmt.setInt(5, student.getId());
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Updated successfully!");
            return true;
        } catch (Exception e) {
            System.out.println("Error to update: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public Student loadStudent(int id_student) {
        String sql = "SELECT * FROM student WHERE id_student=?";
        Student s1 = new Student();
        s1.setId(id_student);
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, s1.getId());
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                s1.setId(result.getInt("id_student"));
                s1.setName(result.getString("name_student"));
                s1.setAge(result.getInt("age_student"));
                s1.setCourse(result.getString("course_student"));
                s1.setStage(result.getInt("stage_student"));
                System.out.println("\n[ ID: " + s1.getId() + "\nName: " + s1.getName() + "\nAge: " + s1.getAge() + "\nCourse: " + s1.getCourse() + "\nStage: " + s1.getStage() + " ]");
            } else {
                System.out.println("No data found for ID: " + id_student);
            }
        } catch (Exception e) {
            System.out.println("Error to load: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return s1;
    }
}
