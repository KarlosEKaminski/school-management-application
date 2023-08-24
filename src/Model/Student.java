package Model;
import DAO.StudentDAO;
import java.util.*;

public class Student extends Person{
    private String course;
    private int stage;
    StudentDAO studentDAO = new StudentDAO();

    public Student() {
    }

    public Student(String course, int stage) {
        this.course = course;
        this.stage = stage;
    }

    public Student(String name, int age, int id, String course, int stage) {
        super(name, age, id);
        this.course = course;
        this.stage = stage;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public List<Student> getStudent() {
        return studentDAO.listStudent();
    }

    public boolean insertStudentDB(String name, int age, int id, String course, int stage) {
        Student s1 = new Student(name, age, id, course, stage);
        return studentDAO.insertStudent(s1);
      
    }

    public void deleteStudentDB(int id) {
        studentDAO.deleteStudent(id);
    }

    public boolean updateStudentDB(String name, int age, String course, int stage, int id) {
        Student s1 = new Student(name, age, id,course, stage);
        return studentDAO.updateStudent(s1);
    }

    public void loadStudentDB(int id) {
        studentDAO.loadStudent(id);
    } 
    
    @Override
    public String toString(){
        return "\nID: " + getId() +
                "\nName: " + getName() +
                "\nAge: " + getAge() +
                "\nCourse: " + getCourse() +
                "\nStage: " + getStage() +
                "\n";
    }
}
