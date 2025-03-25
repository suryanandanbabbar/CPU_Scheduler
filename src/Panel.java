import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JFrame {
    private JPanel inputPanel;
    private JComboBox<String> algorithmSelector;
    private JTextArea resultArea;
    private ArrayList<Process> processes;
    private ArrayList<JTextField> priorityFields;
    private JTextField timeQuantumField;

    public Panel() {
        setTitle("CPU Scheduler Simulator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        processes = new ArrayList<>();
        priorityFields = new ArrayList<>();

        // Algorithm Selector
        JPanel algorithmPanel = new JPanel();
        algorithmPanel.setBorder(BorderFactory.createTitledBorder("Algorithm"));

        algorithmSelector = new JComboBox<>(new String[]{"FCFS", "Priority Scheduling","RR"});
        algorithmPanel.add(new JLabel("Select Algorithm: "));
        algorithmPanel.add(algorithmSelector);
        
        JLabel timeQuantumLabel = new JLabel("Time Quantum Value: ");

        timeQuantumField = new JTextField(10);
        timeQuantumField.setToolTipText("Enter Time Quantum: ");
        
        timeQuantumLabel.setVisible(false);
        timeQuantumField.setVisible(false);

        algorithmPanel.add(timeQuantumLabel);
        algorithmPanel.add(timeQuantumField);

        add(algorithmPanel, BorderLayout.CENTER);
        
        algorithmSelector.addActionListener(c -> {
            if("RR".equals(algorithmSelector.getSelectedItem())){
                timeQuantumLabel.setVisible(true);
                timeQuantumField.setVisible(true);
            }else{
                timeQuantumLabel.setVisible(false);
                timeQuantumField.setVisible(false);
            }
        });

        // Input Panel
        inputPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Process Input"));

        JLabel idLabel = new JLabel("Process ID");
        JLabel arrivalLabel = new JLabel("Arrival Time");
        JLabel burstLabel = new JLabel("Burst Time");
        JLabel priorityLabel = new JLabel("Priority");

        inputPanel.add(idLabel);
        inputPanel.add(arrivalLabel);
        inputPanel.add(burstLabel);
        inputPanel.add(priorityLabel);

        add(inputPanel, BorderLayout.NORTH);

        addProcessRow();

        // Result Area
        resultArea = new JTextArea(10, 50);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Results"));

        add(scrollPane, BorderLayout.SOUTH);

        // Add Process Button
        JButton addProcessButton = new JButton("Add Process");
        addProcessButton.addActionListener(e -> addProcessRow());
        add(addProcessButton, BorderLayout.WEST);

        // Simulate Button
        JButton simulateButton = new JButton("Simulate");
        simulateButton.addActionListener(e -> simulate());
        add(simulateButton, BorderLayout.EAST);

        // Listener to update priority visibility
        algorithmSelector.addActionListener(e -> updatePriorityVisibility());
        updatePriorityVisibility();
    }

    private void addProcessRow() {
        // Process ID
        JTextField idField = new JTextField();
        idField.setToolTipText("Enter Process ID");

        // Arrival Time
        JTextField arrivalField = new JTextField();
        arrivalField.setToolTipText("Enter Arrival Time");

        // Burst Time
        JTextField burstField = new JTextField();
        burstField.setToolTipText("Enter Burst Time");

        // Priority
        JTextField priorityField = new JTextField();
        priorityField.setToolTipText("Enter Priority (Lower = Higher Priority)");

        inputPanel.add(idField);
        inputPanel.add(arrivalField);
        inputPanel.add(burstField);
        inputPanel.add(priorityField);
        priorityFields.add(priorityField);

        revalidate();
        repaint();

        updatePriorityVisibility();
    }

    private void updatePriorityVisibility() {
        boolean isPriorityScheduling = "Priority Scheduling".equals(algorithmSelector.getSelectedItem());

        // Get all components inside inputPanel
        Component[] components = inputPanel.getComponents();
        for (int i = 4; i < components.length; i += 4) { // Skip header row
            if (components[i + 3] instanceof JTextField) {
                components[i + 3].setEnabled(isPriorityScheduling);
            }
        }
    }
    
    private void simulate() {
        try {
            processes.clear();

            Component[] components = inputPanel.getComponents();
            boolean isPriorityScheduling = algorithmSelector.getSelectedItem().equals("Priority Scheduling");

            // Skipping the header row
            for (int i = 4; i < components.length; i += 4) { // Skip the header row
                if (components[i] instanceof JTextField &&
                        components[i + 1] instanceof JTextField &&
                        components[i + 2] instanceof JTextField &&
                        components[i + 3] instanceof JTextField) {

                    int id = Integer.parseInt(((JTextField) components[i]).getText().trim());
                    int arrival = Integer.parseInt(((JTextField) components[i + 1]).getText().trim());
                    int burst = Integer.parseInt(((JTextField) components[i + 2]).getText().trim());
                    int priority = isPriorityScheduling ? Integer.parseInt(((JTextField) components[i + 3]).getText().trim()) : 0;

                    processes.add(new Process(id, arrival, burst, priority));
                }
            }

            // Debugging
            System.out.println("Collected Processes:");
            processes.forEach(p -> System.out.println("ID: " + p.id + ", Arrival: " + p.arrivalTime + ", Burst: " + p.burstTime));
            
            // Select algorithm and simulate
            String algorithm = (String) algorithmSelector.getSelectedItem();
            if("FCFS".equals(algorithm)) {
                FCFS fcfs = new FCFS(processes);
                resultArea.setText(fcfs.simulate());
            } else if ("Priority Scheduling".equals(algorithm)) {
                PriorityScheduling ps = new PriorityScheduling(processes);
                resultArea.setText(ps.simulate());
            } else if ("RR".equals(algorithm)) {
                int timeQuantum = Integer.parseInt(timeQuantumField.getText().trim());
                RoundRobin rr = new RoundRobin(processes, timeQuantum);
                resultArea.setText(rr.simulate());
            }
        } catch (Exception e) {
            // ERROR Message Dialog Box
            JOptionPane.showMessageDialog(this, "Invalid Input: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
