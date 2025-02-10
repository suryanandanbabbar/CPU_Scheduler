import java.util.*;

class FCFS {
    private final ArrayList<Process> processes;

    public FCFS(ArrayList<Process> processes) {
        this.processes = new ArrayList<>(processes);

        // Comparing according to ARRIVAL TIME
        this.processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
    }

    public String simulate() {
        StringBuilder result = new StringBuilder();
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        result.append("Gantt Chart:\n");
        for (Process process : processes) {
            if (currentTime < process.arrivalTime) {
                currentTime = process.arrivalTime;
            }

            result.append("[P").append(process.id).append(": ").append(currentTime).append(" - ")
                    .append(currentTime + process.burstTime).append("]\n");

            totalWaitingTime += (currentTime - process.arrivalTime);
            totalTurnaroundTime += (currentTime + process.burstTime - process.arrivalTime);
            currentTime += process.burstTime;
        }

        double avgWaitingTime = (double) totalWaitingTime / processes.size();
        double avgTurnaroundTime = (double) totalTurnaroundTime / processes.size();

        result.append("\nAverage Waiting Time: ").append(String.format("%.2f", avgWaitingTime));
        result.append("\nAverage Turnaround Time: ").append(String.format("%.2f", avgTurnaroundTime));

        return result.toString();
    }
}
