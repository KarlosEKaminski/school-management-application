package Model;
import DAO.ProfessorDAO;
import java.util.List;

public class Professor extends Person{
    private int wage;
    private String discipline;
    ProfessorDAO professorDAO = new ProfessorDAO();

    public Professor() {
    }

    public Professor(int wage, String discipline) {
        this.wage = wage;
        this.discipline = discipline;
    }

    public Professor(String name, int age, int id, int wage, String discipline) {
        super(name, age, id);
        this.wage = wage;
        this.discipline = discipline;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public List<Professor> getProfessor() {
        return professorDAO.listProfessor();
    }

    public boolean insertProfessorDB(String name, int age, int id, int wage, String discipline) {
        Professor p1 = new Professor(name, age, id, wage,discipline);
        return professorDAO.insertProfessor(p1);
    }

    public void deleteProfessorDB(int id) {
        professorDAO.deleteProfessor(id);
    }

    public boolean updateProfessorDB(String name, int age, int id, int wage, String discipline) {
        Professor p1 = new Professor(name, age, id, wage,discipline);
        return professorDAO.updateProfessor(p1);
    }

    public void loadProfessorDB(int id) {
        professorDAO.loadProfessor(id);
    }

    @Override
    public String toString() {
        return "\nID: " + getId() +
                "\nName: " + getName() +
                "\nAge: " + getAge() +
                "\nWage: " + getWage() +
                "\nDiscipline: " + getDiscipline() +
                "\n";
    }
}
