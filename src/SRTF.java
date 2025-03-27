import java.util.*;

class SRTF {
    private final ArrayList<Process> processes;

    public SRTF(ArrayList<Process> processes) {
        this.processes = new ArrayList<>(processes);
        // Sort processes by arrival time first
        this.processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
    }

    public String simulate() {
        StringBuilder result = new StringBuilder();
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int remainingProcesses = processes.size();
        int[] remainingTime = new int[processes.size()];
        boolean[] completed = new boolean[processes.size()];
        
        for (int i = 0; i < processes.size(); i++) {
            remainingTime[i] = processes.get(i).burstTime;
        }

        result.append("Gantt Chart:\n");

        while (remainingProcesses > 0) {
            int idx = -1;
            int minBurst = Integer.MAX_VALUE;
            
            for (int i = 0; i < processes.size(); i++) {
                if (!completed[i] && processes.get(i).arrivalTime <= currentTime && remainingTime[i] < minBurst) {
                    minBurst = remainingTime[i];
                    idx = i;
                }
            }
            
            if (idx == -1) {
                // No available process, jump to next arrival time
                int nextArrival = Integer.MAX_VALUE;
                for (Process p : processes) {
                    if (!completed[processes.indexOf(p)]) {
                        nextArrival = Math.min(nextArrival, p.arrivalTime);
                    }
                }
                currentTime = nextArrival;
            } else {
                Process p = processes.get(idx);
                result.append("[P").append(p.id).append(": ").append(currentTime).append(" - ")
                        .append(currentTime + 1).append("]\n");
                
                remainingTime[idx]--;
                currentTime++;
                
                if (remainingTime[idx] == 0) {
                    completed[idx] = true;
                    remainingProcesses--;
                    totalWaitingTime += (currentTime - p.arrivalTime - p.burstTime);
                    totalTurnaroundTime += (currentTime - p.arrivalTime);
                }
            }
        }

        double avgWaitingTime = (double) totalWaitingTime / processes.size();
        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.size();

        result.append("\nAverage Waiting Time: ").append(String.format("%.2f", avgWaitingTime));
        result.append("\nAverage Turnaround Time: ").append(String.format("%.2f", avgTurnaroundTime));

        return result.toString();
    }
}
 