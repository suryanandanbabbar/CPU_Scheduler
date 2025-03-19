import java.util.*;

class PriorityScheduling {
    private final ArrayList<Process> processes;

    public PriorityScheduling(ArrayList<Process> processes) {
        this.processes = new ArrayList<>(processes);

        // Sorting processes by priority (lower value = higher priority)
        this.processes.sort((p1, p2) -> {
            if (p1.arrivalTime == p2.arrivalTime) {
                return Integer.compare(p1.priority, p2.priority); // Lower priority number comes first
            }
            return Integer.compare(p1.arrivalTime, p2.arrivalTime); // Sort by arrival time first
        });
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
                    .append(currentTime + process.burstTime).append("] (Priority: ").append(process.priority).append(")\n");

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
