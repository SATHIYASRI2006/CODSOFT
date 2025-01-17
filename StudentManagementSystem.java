import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

class Student {
    private int rollNumber;
    private String name;
    private String grade;

    public Student(int rollNumber, String name, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grade = grade;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return rollNumber + ", " + name + ", " + grade;
    }
}

public class StudentManagementSystem extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();
    private DefaultTableModel tableModel;

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Roll Number", "Name", "Grade"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton editButton = new JButton("Edit Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton saveButton = new JButton("Save to File");
        JButton loadButton = new JButton("Load from File");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addStudent());
        editButton.addActionListener(e -> editStudent(table));
        deleteButton.addActionListener(e -> deleteStudent(table));
        saveButton.addActionListener(e -> saveToFile());
        loadButton.addActionListener(e -> loadFromFile());
    }

    private void addStudent() {
        JTextField rollField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField gradeField = new JTextField();

        Object[] fields = {
            "Roll Number:", rollField,
            "Name:", nameField,
            "Grade:", gradeField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Add Student", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int roll = Integer.parseInt(rollField.getText().trim());
                String name = nameField.getText().trim();
                String grade = gradeField.getText().trim();

                if (name.isEmpty() || grade.isEmpty()) {
                    throw new IllegalArgumentException("Fields cannot be empty.");
                }

                Student student = new Student(roll, name, grade);
                students.add(student);
                tableModel.addRow(new Object[]{roll, name, grade});
                JOptionPane.showMessageDialog(this, "Student added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void editStudent(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to edit.");
            return;
        }

        int roll = (int) tableModel.getValueAt(selectedRow, 0);
        Student student = students.stream().filter(s -> s.getRollNumber() == roll).findFirst().orElse(null);

        if (student != null) {
            JTextField nameField = new JTextField(student.getName());
            JTextField gradeField = new JTextField(student.getGrade());

            Object[] fields = {
                "Name:", nameField,
                "Grade:", gradeField
            };

            int result = JOptionPane.showConfirmDialog(this, fields, "Edit Student", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    String name = nameField.getText().trim();
                    String grade = gradeField.getText().trim();

                    if (name.isEmpty() || grade.isEmpty()) {
                        throw new IllegalArgumentException("Fields cannot be empty.");
                    }

                    student.setName(name);
                    student.setGrade(grade);

                    tableModel.setValueAt(name, selectedRow, 1);
                    tableModel.setValueAt(grade, selectedRow, 2);

                    JOptionPane.showMessageDialog(this, "Student updated successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                }
            }
        }
    }

    private void deleteStudent(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            return;
        }

        int roll = (int) tableModel.getValueAt(selectedRow, 0);
        students.removeIf(s -> s.getRollNumber() == roll);
        tableModel.removeRow(selectedRow);

        JOptionPane.showMessageDialog(this, "Student deleted successfully!");
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            oos.writeObject(students);
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage());
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"))) {
            students = (ArrayList<Student>) ois.readObject();
            tableModel.setRowCount(0);
            for (Student student : students) {
                tableModel.addRow(new Object[]{student.getRollNumber(), student.getName(), student.getGrade()});
            }
            JOptionPane.showMessageDialog(this, "Data loaded successfully!");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementSystem app = new StudentManagementSystem();
            app.setVisible(true);
        });
    }
}
