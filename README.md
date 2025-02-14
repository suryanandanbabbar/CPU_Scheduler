## Project Title
CPU Scheduler Simulator

## Project Description
The CPU Scheduler Simulator is an interactive Java-based application designed to simulate CPU scheduling algorithms. The simulator enables users to input processes with specific arrival times, burst times, and priorities. It provides real-time visualizations such as Gantt charts and calculates performance metrics like average waiting time and average turnaround time. This simulator helps users understand the working of various CPU scheduling techniques like FCFS (First Come First Serve).

### Key Features
- Simulates FCFS (First Come First Serve) algorithm.
- Real-time Gantt chart visualization.
- Calculates average waiting time and turnaround time.
- User-friendly graphical interface using Java Swing.

### Technologies Used
- Programming Language: Java
- Libraries/Tools: Java Swing for GUI development

### Challenges and Future Enhancements
- Challenges: Designing a flexible UI to dynamically accept input rows and display accurate results.
- Future Features: Adding support for SJF (Shortest Job First), SRTF(Shortest Remaining Time First) and Round Robin.

## How to Use the Project
1. Launch the application by running the main file (use main branch).
2. Use the input panel to enter process details:
   - Process ID: A unique identifier for each process.
   - Arrival Time: Time at which the process arrives.
   - Burst Time: The execution time required by the process.
3. Select the algorithm (currently supports FCFS) from the dropdown menu.
4. Click the "Simulate" button to see the results:
   - A Gantt chart showing the scheduling timeline.
   - Performance metrics such as average waiting time and turnaround time.

### Example
#### Input:
- Process 1: Arrival Time = 0, Burst Time = 4
- Process 2: Arrival Time = 1, Burst Time = 3
- Process 3: Arrival Time = 2, Burst Time = 5

#### Output:
- Gantt Chart:
  ```
  [P1: 0 - 4]
  [P2: 4 - 7]
  [P3: 7 - 12]
  ```
- Metrics:
  - Average Waiting Time: 2.33
  - Average Turnaround Time: 7.33

---

## Credits
- Developers:
  - Archit Sharma (https://github.com/archit-sharma-10)
  - Praneet Kaur (https://github.com/iamPraneetkaur)
  - Suryanandan Babbar (https://github.com/suryanandanbabbar)
- Special Thanks: Dr. Anudeep Goraya, Professor(CSE), Lovely Professional University, Phagwara, Punjab.
