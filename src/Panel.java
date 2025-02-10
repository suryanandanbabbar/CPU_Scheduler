import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Panel extends JFrame {
    private JPanel inputPanel;
    private JComboBox<String> algorithmSelector;
    private JTextArea resultArea;
    private ArrayList<Process> processes;

    public Panel() {
        setTitle("CPU Scheduler Simulator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        processes = new ArrayList<>();

        // Input Panel
        inputPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Process Input"));

        JLabel idLabel = new JLabel("Process ID");
        JLabel arrivalLabel = new JLabel("Arrival Time");
        JLabel burstLabel = new JLabel("Burst Time");

        inputPanel.add(idLabel);
        inputPanel.add(arrivalLabel);
        inputPanel.add(burstLabel);

        addProcessRow();

//        JButton addProcessButton = new JButton("Add Process");
//        addProcessButton.addActionListener(e -> addProcessRow());
//        add(addProcessButton, BorderLayout.NORTH);

        add(inputPanel, BorderLayout.NORTH);

        // Algorithm Selector
        JPanel algorithmPanel = new JPanel();
        algorithmPanel.setBorder(BorderFactory.createTitledBorder("Algorithm"));

        algorithmSelector = new JComboBox<>(new String[]{"FCFS"});
        algorithmPanel.add(new JLabel("Select Algorithm: "));
        algorithmPanel.add(algorithmSelector);

        add(algorithmPanel, BorderLayout.CENTER);

        // Result Area
        resultArea = new JTextArea(10, 50);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Results"));

        add(scrollPane, BorderLayout.SOUTH);

        // Simulate Button
        JButton addProcessButton = new JButton("Add Process");
        addProcessButton.addActionListener(e -> addProcessRow());
        add(addProcessButton, BorderLayout.WEST);

        JButton simulateButton = new JButton("Simulate");
        simulateButton.addActionListener(e -> simulate());
        add(simulateButton, BorderLayout.EAST);
    }

    private void addProcessRow() {
        JTextField idField = new JTextField();
        idField.setToolTipText("Enter Process ID");
//        idField.setText("Process ID");
//        idField.setForeground(Color.BLUE);

        JTextField arrivalField = new JTextField();
        arrivalField.setToolTipText("Enter Arrival Time");
//        arrivalField.setText("Arrival Time");
//        arrivalField.setForeground(Color.GREEN);

        JTextField burstField = new JTextField();
        burstField.setToolTipText("Enter Burst Time");
//        burstField.setText("Burst Time");
//        burstField.setForeground(Color.RED);

        inputPanel.add(idField);
        inputPanel.add(arrivalField);
        inputPanel.add(burstField);

        revalidate();
        repaint();
    }

    private void simulate() {
        try {
            processes.clear();

            // Collect data only from JTextField rows added for processes
            Component[] components = inputPanel.getComponents();
            for (int i = 3; i < components.length; i += 3) { // Skip the header row
                if (components[i] instanceof JTextField &&
                        components[i + 1] instanceof JTextField &&
                        components[i + 2] instanceof JTextField) {

                    int id = Integer.parseInt(((JTextField) components[i]).getText().trim());
                    int arrival = Integer.parseInt(((JTextField) components[i + 1]).getText().trim());
                    int burst = Integer.parseInt(((JTextField) components[i + 2]).getText().trim());
                    processes.add(new Process(id, arrival, burst));
                }
            }

            // Debugging
            System.out.println("Collected Processes:");
            processes.forEach(p -> System.out.println("ID: " + p.id + ", Arrival: " + p.arrivalTime + ", Burst: " + p.burstTime));

            // Select algorithm and simulate
            String algorithm = (String) algorithmSelector.getSelectedItem();
            if ("FCFS".equals(algorithm)) {
                FCFS fcfs = new FCFS(processes);
                resultArea.setText(fcfs.simulate());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}