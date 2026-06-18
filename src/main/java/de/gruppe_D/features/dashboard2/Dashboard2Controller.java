package de.gruppe_D.features.dashboard2;

import de.gruppe_D.app.Router;

public class Dashboard2Controller {

    private final Dashboard2View view;
    private final Dashboard2Service dashboardService;
    private final Router router;

    public Dashboard2Controller(Dashboard2View view, Dashboard2Service dashboardService, Router router) {
        this.view = view;
        this.dashboardService = dashboardService;
        this.router = router;

        initListeners();
        loadData();
    }

    private void initListeners() {
        view.btnNavDashboard.addActionListener(e -> router.showDashboard2());
        view.btnLogout.addActionListener(e -> router.showAuth());
    }

    private void loadData() {
        // Zieht die fertig berechneten Daten aus dem Service
        Dashboard2Model model = dashboardService.calculateDashboardData();

        view.lblActiveVal.setText(String.valueOf(model.getActiveCount()));
        view.lblCriticalVal.setText(String.valueOf(model.getCriticalCount()));
        view.lblInterviewVal.setText(String.valueOf(model.getInterviewCount()));
        view.lblSuccessVal.setText(String.format(java.util.Locale.US, "%.1f %%", model.getSuccessRate()));

        view.timelineChart.updateData(model.getTimelineItems());
        view.donutChart.updateData(model.getSuccessCount(), model.getWaitingCount(), model.getRejectedCount());
    }
}