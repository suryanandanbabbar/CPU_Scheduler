# Priority Scheduling

This branch implements the **Priority Scheduling** CPU Scheduling Algorithm in Java with a graphical user interface (GUI).

## Features
- **Graphical Input Panel**: Users can input process ID, arrival time, burst time and priority.
- **Gantt Chart Visualization**: Displays the process execution order.
- **Performance Metrics**: Computes **Average Waiting Time** and **Average Turnaround Time**.

## Usage
1. **Launch the Application**.
2. **Enter Process Details**: Provide Process ID, Arrival Time, Burst Time and Priority(Lower number = Higher priority).
3. **Select Priority Scheduling Algorithm**.
4. **Click Simulate** to generate the Gantt Chart and Performance Metrics.

## Example Output
### Input:
Process(Process ID, Arrival Time, Burst Time, Priority)
```
P1(1, 0, 2, 3)
P2(2, 0, 3, 2)
P3(3, 0, 1, 1)
```

### Gantt Chart:
```
[P3: 0 - 1] (Priority: 1)
[P2: 1 - 4] (Priority: 2)
[P1: 4 - 6] (Priority: 3)
```
### Metrics:
```
Average Waiting Time: 1.67
Average Turnaround Time: 3.67
```

## Contributor
Suryanandan Babbar(https://github.com/suryanandanbabbar)
