import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class StudentRegistrationForm extends JFrame {

    // Text fields
    JTextField txtFirstName, txtLastName, txtEmail, txtConfirmEmail;
    JPasswordField txtPassword, txtConfirmPassword;

    // Combo boxes
    JComboBox<Integer> cmbYear, cmbDay;
    JComboBox<String> cmbMonth, cmbDepartment;

    // Radio buttons
    JRadioButton rbMale, rbFemale;
    ButtonGroup genderGroup;

    // Buttons
    JButton btnSubmit, btnCancel;

    // Output
    JTextArea txtOutput;
    JLabel lblError;

    // ID counter storage
    static HashMap<Integer, Integer> yearCounters = new HashMap<>();

    public StudentRegistrationForm() {
        setTitle("Student Registration Form");
        setSize(950, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buildUI();
    }

    private void buildUI() {

        // MAIN CONTAINER WITH PADDING
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // LEFT FORM PANEL
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(10, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));

        // Create fields
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtEmail = new JTextField();
        txtConfirmEmail = new JTextField();
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();

        // DOB Combo boxes
        cmbYear = new JComboBox<>();
        cmbMonth = new JComboBox<>(new String[]{
                "January","February","March","April","May","June",
                "July","August","September","October","November","December"
        });
        cmbDay = new JComboBox<>();

        for (int y = LocalDate.now().getYear(); y >= 1950; y--) {
            cmbYear.addItem(y);
        }

        cmbYear.addActionListener(e -> updateDays());
        cmbMonth.addActionListener(e -> updateDays());
        updateDays();

        JPanel dobPanel = new JPanel();
        dobPanel.add(cmbYear);
        dobPanel.add(cmbMonth);
        dobPanel.add(cmbDay);

        // Gender
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);

        JPanel genderPanel = new JPanel();
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);

        // Department
        cmbDepartment = new JComboBox<>(new String[]{
                "-- Select Department --",
                "Civil","CSE","Electrical","E&C","Mechanical"
        });

        // Buttons
        btnSubmit = new JButton("Submit");
        btnCancel = new JButton("Cancel");

        btnSubmit.addActionListener(e -> handleSubmit());
        btnCancel.addActionListener(e -> clearForm());

        // Add items to form panel
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(txtFirstName);

        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(txtLastName);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(txtEmail);

        formPanel.add(new JLabel("Confirm Email:"));
        formPanel.add(txtConfirmEmail);

        formPanel.add(new JLabel("Password:"));
        formPanel.add(txtPassword);

        formPanel.add(new JLabel("Confirm Password:"));
        formPanel.add(txtConfirmPassword);

        formPanel.add(new JLabel("Date of Birth:"));
        formPanel.add(dobPanel);

        formPanel.add(new JLabel("Gender:"));
        formPanel.add(genderPanel);

        formPanel.add(new JLabel("Department:"));
        formPanel.add(cmbDepartment);

        formPanel.add(btnSubmit);
        formPanel.add(btnCancel);

        // RIGHT OUTPUT PANEL
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Your Data is Below:"));
        scrollPane.setPreferredSize(new Dimension(380, 400));

        // ERROR LABEL
        lblError = new JLabel(" ");
        lblError.setForeground(Color.RED);

        // Add everything
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.EAST);
        mainPanel.add(lblError, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Update days based on month and leap year
    private void updateDays() {
        cmbDay.removeAllItems();

        int year = (int) cmbYear.getSelectedItem();
        int month = cmbMonth.getSelectedIndex() + 1;
        int days;

        if (month == 2) {
            days = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            days = 30;
        } else {
            days = 31;
        }

        for (int d = 1; d <= days; d++) {
            cmbDay.addItem(d);
        }
    }

    private void handleSubmit() {

        lblError.setText(" ");

        String first = txtFirstName.getText().trim();
        String last = txtLastName.getText().trim();
        String email = txtEmail.getText().trim();
        String email2 = txtConfirmEmail.getText().trim();
        String pass = new String(txtPassword.getPassword());
        String pass2 = new String(txtConfirmPassword.getPassword());

        if (first.isEmpty() || last.isEmpty() || email.isEmpty() ||
            email2.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
            showError("All fields are required.");
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$") || !email.equals(email2)) {
            showError("Emails must be valid and match.");
            return;
        }

        if (!pass.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$") || !pass.equals(pass2)) {
            showError("Password rules not met.");
            return;
        }

        if (!rbMale.isSelected() && !rbFemale.isSelected()) {
            showError("Please select gender.");
            return;
        }

        if (cmbDepartment.getSelectedIndex() == 0) {
            showError("Please select department.");
            return;
        }

        LocalDate dob = LocalDate.of(
                (int) cmbYear.getSelectedItem(),
                cmbMonth.getSelectedIndex() + 1,
                (int) cmbDay.getSelectedItem()
        );

        int age = Period.between(dob, LocalDate.now()).getYears();
        if (age < 16 || age > 60) {
            showError("Age must be between 16 and 60.");
            return;
        }

        String gender = rbMale.isSelected() ? "M" : "F";
        String dept = cmbDepartment.getSelectedItem().toString();
        String id = generateID();

        String record = id + " | " + last + " " + first + " | " +
                        gender + " | " + dept + " | " +
                        LocalDate.now() + " | " + email;

        txtOutput.append(record + "\n");
        saveToCSV(record);

        JOptionPane.showMessageDialog(this, "Registration successful!");
        clearForm();
    }

    private void showError(String msg) {
        lblError.setText(msg);
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private String generateID() {
        int year = LocalDate.now().getYear();
        int count = yearCounters.getOrDefault(year, 0) + 1;
        yearCounters.put(year, count);
        return String.format("%d-%05d", year, count);
    }

    private void saveToCSV(String record) {
        try (FileWriter fw = new FileWriter("students.csv", true)) {
            fw.write(record + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "CSV file error");
        }
    }

    private void clearForm() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
        txtConfirmEmail.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        genderGroup.clearSelection();
        cmbDepartment.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRegistrationForm().setVisible(true));
    }
}
