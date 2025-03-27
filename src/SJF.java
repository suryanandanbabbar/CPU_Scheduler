import java.util.*;
class SJF {
    private final ArrayList<Process> processes;

    public SJF(ArrayList<Process> processes) {
        this.processes = new ArrayList<>(processes);
        // Sort processes by arrival time first
        this.processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
    }
    ;

    public String simulate() {
        StringBuilder result = new StringBuilder();
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        boolean[] completed = new boolean[processes.size()];
        int completedProcesses = 0;

        result.append("Gantt Chart:\n");

        while (completedProcesses < processes.size()) {
            int idx = -1;
            int minBurst = Integer.MAX_VALUE;
            
            for (int i = 0; i < processes.size(); i++) {
                if (!completed[i] && processes.get(i).arrivalTime <= currentTime && processes.get(i).burstTime < minBurst) {
                    minBurst = processes.get(i).burstTime;
                    idx = i;
                }
            }
            
            if (idx == -1) {
                // If no process is available, jump to the next earliest arrival time
                int nextArrival = Integer.MAX_VALUE;
                for (Process p : processes) {
                    if (!completed[processes.indexOf(p)]) {
                        nextArrival = Math.min(nextArrival, p.arrivalTime);
                    }
                }
                currentTime = nextArrival; // Jump to next process arrival time
            }
             else {
                Process p = processes.get(idx);
                result.append("[P").append(p.id).append(": ").append(currentTime).append(" - ")
                        .append(currentTime + p.burstTime).append("]\n");
                
                totalWaitingTime += (currentTime - p.arrivalTime);
                totalTurnaroundTime += (currentTime + p.burstTime - p.arrivalTime);
                currentTime += p.burstTime;
                completed[idx] = true;
                completedProcesses++;
            }
        }

        double avgWaitingTime = (double) totalWaitingTime / processes.size();
        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.size();

        result.append("\nAverage Waiting Time: ").append(String.format("%.2f", avgWaitingTime));
        result.append("\nAverage Turnaround Time: ").append(String.format("%.2f", avgTurnaroundTime));

        return result.toString();
    }
}
