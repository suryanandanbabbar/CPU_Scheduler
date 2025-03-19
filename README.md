# FCFS

This branch implements the **First Come, First Served (FCFS)** CPU Scheduling Algorithm in Java with a graphical user interface (GUI).

## Features
- **Graphical Input Panel**: Users can input process ID, arrival time, and burst time.
- **Algorithm Selection**: Currently supports **FCFS**.
- **Gantt Chart Visualization**: Displays the process execution order.
- **Performance Metrics**: Computes **Average Waiting Time** and **Average Turnaround Time**.

## Usage
1. **Launch the Application**.
2. **Enter Process Details**: Provide Process ID, Arrival Time, and Burst Time.
3. **Select FCFS Algorithm** (default).
4. **Click Simulate** to generate the Gantt Chart and Performance Metrics.

## Example Output
Process(Process ID, Arrival Time, Burst Time, Priority)
### Input:
```
P1(1, 0, 2)
P2(2, 0, 3)
P3(3, 0, 1)
```

### Gantt Chart:
```
[P1: 0 - 2]
[P2: 2 - 5]
[P3: 5 - 6]
```
### Metrics:
```
Average Waiting Time: 2.33
Average Turnaround Time: 4.33
```

## Contributor
Suryanandan Babbar(https://github.com/suryanandanbabbar)
