// import java.util.*;
// class RoundRobin {
//     private final ArrayList<Process> processes;
//     private int timeQuantum;

//     public RoundRobin(ArrayList<Process> processes, int timeQuantum){
//         this.processes = new ArrayList<>(processes);
//         this.timeQuantum = timeQuantum;
//     }
    
// }
import java.util.*;

class RoundRobin {
    private final ArrayList<Process> processes;
    private final int timeQuantum;

    public RoundRobin(ArrayList<Process> processes, int timeQuantum) {
        this.processes = new ArrayList<>(processes);
        this.timeQuantum = timeQuantum;
    }

    public String simulate() {
        if (processes == null || processes.isEmpty()) {
            return "Error: No processes available for scheduling.";
        }
    
        StringBuilder result = new StringBuilder();
        Queue<Process> queue = new LinkedList<>();
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
    
        // Sort processes by arrival time
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
    
        // Clone processes with remaining burst time
        Map<Integer, Integer> remainingBurstTime = new HashMap<>();
        for (Process p : processes) {
            if (p == null) {
                return "Error: Invalid process data (null process found).";
            }
            remainingBurstTime.put(p.id, p.burstTime);
        }
    
        int index = 0;
    
        // Check if there are any processes before accessing them
        if (!processes.isEmpty()) {
            queue.add(processes.get(index++));
        } else {
            return "Error: No processes available.";
        }
    
        result.append("Gantt Chart:\n");
    
        Map<Integer, Integer> completionTime = new HashMap<>();
        Map<Integer, Integer> startTime = new HashMap<>();
    
        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();
    
            if (!startTime.containsKey(currentProcess.id)) {
                startTime.put(currentProcess.id, Math.max(currentTime, currentProcess.arrivalTime));
            }
    
            int executeTime = Math.min(timeQuantum, remainingBurstTime.get(currentProcess.id));
            result.append("[P").append(currentProcess.id).append(": ").append(currentTime).append(" - ")
                  .append(currentTime + executeTime).append("]\n");
    
            currentTime += executeTime;
            remainingBurstTime.put(currentProcess.id, remainingBurstTime.get(currentProcess.id) - executeTime);
    
            // Add new processes that have arrived
            while (index < processes.size() && processes.get(index).arrivalTime <= currentTime) {
                queue.add(processes.get(index++));
            }
    
            // If the process is not finished, re-add it to the queue
            if (remainingBurstTime.get(currentProcess.id) > 0) {
                queue.add(currentProcess);
            } else {
                completionTime.put(currentProcess.id, currentTime);
            }
        }
    
        for (Process process : processes) {
            int turnaroundTime = completionTime.get(process.id) - process.arrivalTime;
            int waitingTime = turnaroundTime - process.burstTime;
            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
        }
    
        double avgWaitingTime = (double) totalWaitingTime / processes.size();
        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.size();
    
        result.append("\nAverage Waiting Time: ").append(String.format("%.2f", avgWaitingTime));
        result.append("\nAverage Turnaround Time: ").append(String.format("%.2f", avgTurnaroundTime));
    
        return result.toString();
    }
    
}
