# SJF

This branch implements the **Shortest Job First(SJF) [Non-Preemptive]** and **Shortest Running Time First(SRTF)** CPU Scheduling Algorithm in Java with a graphical user interface (GUI).

## Features
- **Graphical Input Panel**: Users can input process ID, arrival time, and burst time.
- **Algorithm Selection**: Currently supports **SJF and SRTF**.
- **Gantt Chart Visualization**: Displays the process execution order.
- **Performance Metrics**: Computes **Average Waiting Time** and **Average Turnaround Time**.

## Usage
1. **Launch the Application**.
2. **Enter Process Details**: Provide Process ID, Arrival Time, and Burst Time.
3. **Select SJF Algorithm** (SJF or SRTF).
4. **Click Simulate** to generate the Gantt Chart and Performance Metrics.

##Example Output

##SJF (Non-Preemptive) Example

Process(Process ID, Arrival Time, Burst Time)
Input:
```
P1(1, 0, 2)
P2(2, 0, 3)
P3(3, 0, 1)
```
### Gantt Chart:
```
[P3: 0 - 1]
[P1: 1 - 3]
[P2: 3 - 6]
```

### Metrics:
```
Average Waiting Time: 1.33
Average Turnaround Time: 3.33
```

## SRTF (Preemptive) Example
Process(Process ID, Arrival Time, Burst Time)
### Input:
```
P1(1, 0, 7)
P2(2, 2, 4)
P3(3, 4, 1)
P4(4, 5, 4)
```

### Gantt Chart:
```
[P1: 0 - 2]
[P2: 2 - 4]
[P3: 4 - 5]
[P2: 5 - 8]
[P4: 8 - 12]
[P1: 12 - 16]
```

### Metrics:
```
Average Waiting Time: 5.75
Average Turnaround Time: 10.75
```

### Contributor
Praneet Kaur(https://gihub.com/iamPraneetkaur).
