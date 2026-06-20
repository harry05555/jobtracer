package de.gruppe_D.features.dashboard2;

import de.gruppe_D.app.utils.UIUtils;
import de.gruppe_D.features.dashboard2.infrastructure.Dashboard2Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Dashboard2Service {

    private final Dashboard2Repository repository;

    public Dashboard2Service(Dashboard2Repository repository) {
        this.repository = repository;
    }

    public Dashboard2Model calculateDashboardData() {
        Dashboard2Model model = new Dashboard2Model();

        int activeCount = 0, criticalCount = 0, interviewCount = 0;
        int totalZusagenOderInterviews = 0, totalAbsagen = 0;
        int successCount = 0, waitingCount = 0, rejectedCount = 0;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate today = LocalDate.now();
        List<TimelineItem> timelineItems = new ArrayList<>();

        // 1. Hole rohe Daten
        List<String[]> rawData = repository.fetchRawApplicationData();

        // 2. Mappen und verarbeiten
        for (String[] row : rawData) {
            Dashboard2Mapper.BewerbungsEintrag bewerbung = Dashboard2Mapper.mapCsvRowToEintrag(row);
            if (bewerbung.firma == null || bewerbung.status.contains("Archiv")) continue;

            LocalDate versendetDate = today;
            LocalDate fristDate = today.plusWeeks(4);
            LocalDate erinnerungDate = null;

            try {
                if(!bewerbung.versendetAm.isEmpty()) versendetDate = LocalDate.parse(bewerbung.versendetAm, dtf);
                if(!bewerbung.frist.isEmpty()) fristDate = LocalDate.parse(bewerbung.frist, dtf);
                if (!bewerbung.erinnerung.isEmpty() && !bewerbung.erinnerung.contains("TT")) {
                    erinnerungDate = LocalDate.parse(bewerbung.erinnerung, dtf);
                }
            } catch (Exception ex) {}

            boolean isAbgesagt = bewerbung.status.contains("Absage") || bewerbung.status.contains("Abgesagt");
            boolean isInterview = bewerbung.status.contains("Interview") || bewerbung.status.contains("Angebot");

            if (isAbgesagt) rejectedCount++;
            else if (isInterview) successCount++;
            else waitingCount++;

            if (!isAbgesagt) {
                activeCount++;
                if (isInterview) {
                    interviewCount++;
                    timelineItems.add(new TimelineItem(bewerbung.firma, versendetDate, fristDate, null, UIUtils.COLOR_GREEN, true));
                } else {
                    if (fristDate != null && fristDate.isBefore(today)) {
                        criticalCount++;
                        timelineItems.add(new TimelineItem(bewerbung.firma, versendetDate, fristDate, null, UIUtils.COLOR_RED, false));
                    } else if (fristDate != null && (fristDate.isBefore(today.plusWeeks(2)) || fristDate.isEqual(today.plusWeeks(2)))) {
                        criticalCount++;
                        timelineItems.add(new TimelineItem(bewerbung.firma, versendetDate, fristDate, erinnerungDate, UIUtils.COLOR_YELLOW, false));
                    } else {
                        timelineItems.add(new TimelineItem(bewerbung.firma, versendetDate, fristDate, erinnerungDate, UIUtils.COLOR_GREEN, false));
                    }
                }
            } else {
                totalAbsagen++;
            }

            if (isInterview) totalZusagenOderInterviews++;
        }

        int closedTotal = totalZusagenOderInterviews + totalAbsagen;
        double rate = (closedTotal == 0) ? 0.0 : ((double) totalZusagenOderInterviews / closedTotal) * 100.0;

        model.setActiveCount(activeCount);
        model.setCriticalCount(criticalCount);
        model.setInterviewCount(interviewCount);
        model.setSuccessRate(rate);
        model.setSuccessCount(successCount);
        model.setWaitingCount(waitingCount);
        model.setRejectedCount(rejectedCount);

        if (timelineItems.size() > 8) timelineItems = timelineItems.subList(0, 8);
        model.setTimelineItems(timelineItems);

        return model;
    }
}