//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.*;

class Student {
  private String name;
  private int rollNumber;
  private String grade;

  public Student(String name, int rollNumber, String grade) {
    this.name = name;
    this.rollNumber = rollNumber;
    this.grade = grade;
  }

  public String getName() {
    return name;
  }

  public int getRollNumber() {
    return rollNumber;
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
    return rollNumber + "," + name + "," + grade;
  }
}

public class Main {

  static ArrayList<Student> students = new ArrayList<>();
  static Scanner sc = new Scanner(System.in);
  static final String FILE_NAME = "students.txt";

  public static void main(String[] args) {

    loadStudents();

    int choice;

    do {
      System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
      System.out.println("1. Add Student");
      System.out.println("2. Display All Students");
      System.out.println("3. Search Student");
      System.out.println("4. Edit Student");
      System.out.println("5. Remove Student");
      System.out.println("6. Save Data");
      System.out.println("7. Exit");
      System.out.print("Enter Choice: ");

      choice = sc.nextInt();

      switch (choice) {
        case 1:
          addStudent();
          break;

        case 2:
          displayStudents();
          break;

        case 3:
          searchStudent();
          break;

        case 4:
          editStudent();
          break;

        case 5:
          removeStudent();
          break;

        case 6:
          saveStudents();
          break;

        case 7:
          saveStudents();
          System.out.println("Thank You!");
          break;

        default:
          System.out.println("Invalid Choice!");
      }

    } while (choice != 7);
  }

  static void addStudent() {

    sc.nextLine();

    System.out.print("Enter Name: ");
    String name = sc.nextLine();

    if (name.isEmpty()) {
      System.out.println("Name cannot be empty!");
      return;
    }

    System.out.print("Enter Roll Number: ");
    int roll = sc.nextInt();

    sc.nextLine();

    System.out.print("Enter Grade: ");
    String grade = sc.nextLine();

    students.add(new Student(name, roll, grade));

    System.out.println("Student Added Successfully!");
  }

  static void displayStudents() {

    if (students.isEmpty()) {
      System.out.println("No Students Found!");
      return;
    }

    System.out.println("\n----- Student Records -----");

    for (Student s : students) {
      System.out.println(
              "Roll No: " + s.getRollNumber()
                      + " | Name: " + s.getName()
                      + " | Grade: " + s.getGrade());
    }
  }

  static void searchStudent() {

    System.out.print("Enter Roll Number: ");
    int roll = sc.nextInt();

    for (Student s : students) {

      if (s.getRollNumber() == roll) {

        System.out.println("Student Found");
        System.out.println("Name : " + s.getName());
        System.out.println("Grade: " + s.getGrade());
        return;
      }
    }

    System.out.println("Student Not Found!");
  }

  static void editStudent() {

    System.out.print("Enter Roll Number: ");
    int roll = sc.nextInt();

    sc.nextLine();

    for (Student s : students) {

      if (s.getRollNumber() == roll) {

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Grade: ");
        String grade = sc.nextLine();

        s.setName(name);
        s.setGrade(grade);

        System.out.println("Student Updated!");
        return;
      }
    }

    System.out.println("Student Not Found!");
  }

  static void removeStudent() {

    System.out.print("Enter Roll Number: ");
    int roll = sc.nextInt();

    Iterator<Student> iterator = students.iterator();

    while (iterator.hasNext()) {

      Student s = iterator.next();

      if (s.getRollNumber() == roll) {
        iterator.remove();
        System.out.println("Student Removed!");
        return;
      }
    }

    System.out.println("Student Not Found!");
  }

  static void saveStudents() {

    try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {

      for (Student s : students) {
        writer.println(s);
      }

      System.out.println("Data Saved Successfully!");

    } catch (IOException e) {
      System.out.println("Error Saving File!");
    }
  }

  static void loadStudents() {

    File file = new File(FILE_NAME);

    if (!file.exists()) {
      return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {

      String line;

      while ((line = br.readLine()) != null) {

        String[] data = line.split(",");

        students.add(
                new Student(
                        data[1],
                        Integer.parseInt(data[0]),
                        data[2]
                )
        );
      }

    } catch (Exception e) {
      System.out.println("Error Loading File!");
    }
  }
}
