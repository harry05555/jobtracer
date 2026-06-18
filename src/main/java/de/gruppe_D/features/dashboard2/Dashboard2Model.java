package de.gruppe_D.features.dashboard2;

import java.util.List;

public class Dashboard2Model {
    private int activeCount, criticalCount, interviewCount;
    private double successRate;
    private int successCount, waitingCount, rejectedCount;
    private List<TimelineItem> timelineItems;

    // Getter & Setter
    public int getActiveCount() { return activeCount; }
    public void setActiveCount(int activeCount) { this.activeCount = activeCount; }

    public int getCriticalCount() { return criticalCount; }
    public void setCriticalCount(int criticalCount) { this.criticalCount = criticalCount; }

    public int getInterviewCount() { return interviewCount; }
    public void setInterviewCount(int interviewCount) { this.interviewCount = interviewCount; }

    public double getSuccessRate() { return successRate; }
    public void setSuccessRate(double successRate) { this.successRate = successRate; }

    public int getSuccessCount() { return successCount; }
    public void setSuccessCount(int successCount) { this.successCount = successCount; }

    public int getWaitingCount() { return waitingCount; }
    public void setWaitingCount(int waitingCount) { this.waitingCount = waitingCount; }

    public int getRejectedCount() { return rejectedCount; }
    public void setRejectedCount(int rejectedCount) { this.rejectedCount = rejectedCount; }

    public List<TimelineItem> getTimelineItems() { return timelineItems; }
    public void setTimelineItems(List<TimelineItem> timelineItems) { this.timelineItems = timelineItems; }
}