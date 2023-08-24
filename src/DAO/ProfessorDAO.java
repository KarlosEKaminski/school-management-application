package DAO;
import Controller.Conn;
import Model.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProfessorDAO {
    private final Connection conn;

    public ProfessorDAO() {
        new Conn();
        this.conn = Conn.getConnection();
    }

    public List<Professor> listProfessor() {
        String sql = "SELECT * FROM professor";
        List<Professor> returns = new ArrayList<>();
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                Professor p1 = new Professor();
                p1.setId(result.getInt("id_professor"));
                p1.setName(result.getString("name_professor"));
                p1.setAge(result.getInt("age_professor"));
                p1.setWage(result.getInt("wage_professor"));
                p1.setDiscipline(result.getString("discipline_professor"));

                returns.add(p1);
                System.out.println("\n[ ID: " + p1.getId() + "\nName: " + p1.getName() + "\nAge: " + p1.getAge() + "\nWage: " + p1.getWage() + "\nDiscipline: " + p1.getDiscipline() + " ]");
            }
        } catch (Exception e) {
            System.out.println("Error to list: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return returns;
    }

    public boolean insertProfessor(Professor professor) {
        String sql = "INSERT INTO professor(id_professor, name_professor, age_professor, wage_professor, discipline_professor)" + "VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, professor.getId());
            stmt.setString(2, professor.getName());
            stmt.setInt(3, professor.getAge());
            stmt.setInt(4, professor.getWage());
            stmt.setString(5, professor.getDiscipline());
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

    public void deleteProfessor(int id_professor) {
        String sql = "DELETE FROM professor WHERE id_professor=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id_professor);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Deleted successfully!");
        } catch (Exception e) {
            System.out.printf("Error to delete: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public boolean updateProfessor(Professor professor) {
        String sql = "UPDATE professor SET name_professor=?, age_professor=?, wage_professor=?, discipline_professor=? WHERE id_professor=?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, professor.getName());
            stmt.setInt(2, professor.getAge());
            stmt.setInt(3, professor.getWage());
            stmt.setString(4, professor.getDiscipline());
            stmt.setInt(5, professor.getId());
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

    public Professor loadProfessor(int id_professor){
        String sql = "SELECT * FROM professor WHERE id_professor=?";
        Professor p1 = new Professor();
        p1.setId(id_professor);
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, p1.getId());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                p1.setId(result.getInt("id_professor"));
                p1.setName(result.getString("name_professor"));
                p1.setAge(result.getInt("age_professor"));
                p1.setWage(result.getInt("wage_professor"));
                p1.setDiscipline(result.getNString("discipline_professor"));
                System.out.println("\n[ ID: " + p1.getId() + "\nName: " + p1.getName() + "\nAge: " + p1.getAge() + "\nWage: " + p1.getWage() + "\nDiscipline: " + p1.getDiscipline() + " ]");
            } else {
                System.out.println("No data found for ID: " + id_professor);
            }
        } catch (Exception e) {
            System.out.println("Error to load: " + e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return p1;
    }
}
