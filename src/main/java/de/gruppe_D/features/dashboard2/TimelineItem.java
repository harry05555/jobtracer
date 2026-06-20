package de.gruppe_D.features.dashboard2;

import java.awt.Color;
import java.time.LocalDate;

public class TimelineItem {
    public String firma;
    public LocalDate start, end, reminder;
    public Color color;
    public boolean isInterview;

    public TimelineItem(String firma, LocalDate start, LocalDate end, LocalDate reminder, Color color, boolean isInterview) {
        this.firma = firma; this.start = start; this.end = end;
        this.reminder = reminder; this.color = color; this.isInterview = isInterview;
    }
}