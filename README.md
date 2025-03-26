# Round Robin

This branch implements the **Round Robin (RR)** CPU Scheduling Algorithm in Java with a graphical user interface (GUI).

## Features:
- **Graphical Input Panel**: Users can input process ID, arrival time, burst time and the time quantum.
- **Algorithm Selection**: Now supports **Round Robin** along with other scheduling algorithms.
- **Gantt Chart Visualization**: Displays the process execution order.
- **Performance Metrics**: Computes **Average Waiting Time** and **Average Turnaround Time**.

## Usage
1. **Launch the Application**.
2. **Enter Process Details**: Provide Process ID, Arrival Time, Burst Time and Time Quantum.
3. **Select Round Robin (RR) Algorithm** from the algorithm selector's dropdown.
4. **Click Simulate** to generate the Gantt Chart and Performance Metrics.

## Example Output
Process(Process ID, Arrival Time, Burst Time, Priority)

### Input:
```
P1(1, 0, 5)
P2(2, 1, 3)
P3(3, 2, 6)
Time Quantum = 2
```

### Gantt Chart:
```
[P1: 0 - 2]
[P2: 2 - 4]
[P3: 4 - 6]
[P1: 6 - 8]
[P2: 8 - 9]
[P3: 9 - 11]
[P1: 11 - 12]
[P3: 12 - 14]
```
### Metrices:
```
Average Waiting Time: 6.00
Average Turnaround Time: 10.67
```

## Contributors
- Archit Sharma(https://github.com/archit-sharma-10)
