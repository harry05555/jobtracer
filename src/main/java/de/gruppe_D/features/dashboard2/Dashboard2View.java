package de.gruppe_D.features.dashboard2;

import de.gruppe_D.app.utils.UIUtils;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Dashboard2View extends JPanel {

    // --- SIDEBAR BUTTONS (Public für den Controller) ---
    public JButton btnNavDashboard, btnNavUebersicht, btnNavDokumente, btnNavEinstellungen, btnNavBewerbungAnlegen, btnLogout;

    // --- DASHBOARD LABELS & CHARTS ---
    public JLabel lblActiveVal = new JLabel("0");
    public JLabel lblCriticalVal = new JLabel("0");
    public JLabel lblInterviewVal = new JLabel("0");
    public JLabel lblSuccessVal = new JLabel("0.0 %");
    public TimelineChart timelineChart;
    public DonutChart donutChart;

    public Dashboard2View() {
        setLayout(new BorderLayout());
        setBackground(UIUtils.BG_DARK);

        // =====================================================================
        // 1. HARDCODIERTE SIDEBAR
        // =====================================================================
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(UIUtils.SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(250, 900));
        sidebar.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel navLabel = new JLabel("Navigation");
        navLabel.setForeground(UIUtils.TEXT_WHITE);
        navLabel.setFont(new Font("Arial", Font.BOLD, 18));
        sidebar.add(navLabel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        btnNavDashboard = createNavButton("📊 Dashboard");
        btnNavUebersicht = createNavButton("📋 Übersicht");
        btnNavDokumente = createNavButton("📁 Meine Unterlagen");
        btnNavEinstellungen = createNavButton("⚙ Einstellungen");

        btnNavBewerbungAnlegen = new JButton("- Neue Bewerbung anlegen");
        btnNavBewerbungAnlegen.setForeground(UIUtils.TEXT_WHITE);
        btnNavBewerbungAnlegen.setBackground(UIUtils.ACCENT_COLOR);
        btnNavBewerbungAnlegen.setBorderPainted(false);
        btnNavBewerbungAnlegen.setFocusPainted(false);
        btnNavBewerbungAnlegen.setHorizontalAlignment(SwingConstants.LEFT);
        btnNavBewerbungAnlegen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnNavBewerbungAnlegen.setFont(new Font("Arial", Font.BOLD, 14));
        btnNavBewerbungAnlegen.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogout = createNavButton("🚪 Logout / Trennen");

        sidebar.add(btnNavDashboard); sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnNavUebersicht); sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnNavDokumente); sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnNavEinstellungen); sidebar.add(Box.createRigidArea(new Dimension(0, 30)));
        sidebar.add(btnNavBewerbungAnlegen); sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST); // Sidebar links anheften

        // =====================================================================
        // 2. DASHBOARD CONTENT (Mitte)
        // =====================================================================
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(UIUtils.BG_DARK);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        contentPanel.add(UIUtils.createHeadTextLabel("📊 Start-Dashboard"), BorderLayout.NORTH);

        JPanel contentGrid = new JPanel(new BorderLayout(0, 30));
        contentGrid.setBackground(UIUtils.BG_DARK);
        contentGrid.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JPanel kpiPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        kpiPanel.setBackground(UIUtils.BG_DARK);
        kpiPanel.add(createKPICard("Aktive Bewerbungen", lblActiveVal, "Laufende Prozesse", UIUtils.COLOR_GREEN));
        kpiPanel.add(createKPICard("Kritische Fristen", lblCriticalVal, "Handlung erforderlich", UIUtils.COLOR_RED));
        kpiPanel.add(createKPICard("Geplante Interviews", lblInterviewVal, "Diesen Monat", UIUtils.COLOR_BLUE));
        kpiPanel.add(createKPICard("Erfolgsquote", lblSuccessVal, "Einladungen vs. Absagen", UIUtils.ACCENT_COLOR));
        contentGrid.add(kpiPanel, BorderLayout.NORTH);

        timelineChart = new TimelineChart();
        donutChart = new DonutChart();
        JPanel chartArea = new JPanel(new BorderLayout(30, 0));
        chartArea.setBackground(UIUtils.BG_DARK);
        chartArea.add(timelineChart, BorderLayout.CENTER);
        chartArea.add(donutChart, BorderLayout.EAST);

        contentGrid.add(chartArea, BorderLayout.CENTER);
        contentPanel.add(contentGrid, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }

    // --- Helper für die Sidebar ---
    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setForeground(UIUtils.TEXT_GREY);
        btn.setBackground(UIUtils.SIDEBAR_BG);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setFont(new Font("Arial", Font.PLAIN, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel createKPICard(String title, JLabel valueLabel, String subtitle, Color subColor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(UIUtils.SIDEBAR_BG);
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(50, 52, 60), 1, true), BorderFactory.createEmptyBorder(15, 20, 15, 20)));

        JLabel lblTitle = new JLabel(title); lblTitle.setForeground(UIUtils.TEXT_GREY);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 13)); lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        valueLabel.setForeground(UIUtils.TEXT_WHITE); valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel lblSub = new JLabel(subtitle); lblSub.setForeground(subColor);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 11)); lblSub.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(lblTitle); card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(valueLabel); card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(lblSub);
        return card;
    }

    // --- DIAGRAMME (Inner Classes) ---
    public class TimelineChart extends JPanel {
        List<TimelineItem> items = new ArrayList<>();
        public TimelineChart() { setBackground(UIUtils.SIDEBAR_BG); setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60))); }
        public void updateData(List<TimelineItem> newItems) { this.items = newItems; repaint(); }

        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth(); int height = getHeight(); int padding = 20; int labelWidth = 150;
            int todayX = labelWidth + (int) ((width - labelWidth) * 0.3);
            g2.setColor(UIUtils.TEXT_WHITE); g2.setFont(new Font("Arial", Font.BOLD, 18));
            g2.drawString("📅 Dringlichkeit: Fristen & Interviews", padding, padding + 10);

            if (items == null || items.isEmpty()) {
                g2.setFont(new Font("Arial", Font.PLAIN, 12)); g2.setColor(UIUtils.TEXT_GREY);
                g2.drawString("Aktuell keine aktiven Prozesse.", padding, 70); return;
            }

            int barHeight = 15; int rowHeight = 45; int pixelsPerDay = 8; LocalDate today = LocalDate.now();
            g2.setFont(new Font("Arial", Font.PLAIN, 12));

            for (int i = 0; i < items.size(); i++) {
                TimelineItem item = items.get(i);
                int y = 70 + (i * rowHeight);
                g2.setColor(UIUtils.TEXT_WHITE);
                String displayFirma = item.firma.length() > 15 ? item.firma.substring(0, 12) + "..." : item.firma;
                String dateStr = item.end != null ? item.end.format(DateTimeFormatter.ofPattern("dd.MM.")) : "";
                String labelText = displayFirma + (item.isInterview ? " (Interview: " + dateStr + ")" : " (Frist: " + dateStr + ")");
                g2.drawString(labelText, padding, y + 5);
                g2.setColor(new Color(60, 60, 60)); g2.drawLine(labelWidth, y, width - padding, y);

                long daysStart = ChronoUnit.DAYS.between(today, item.start);
                long daysEnd = ChronoUnit.DAYS.between(today, item.end);
                int startX = todayX + (int) (daysStart * pixelsPerDay);
                int endX = todayX + (int) (daysEnd * pixelsPerDay);
                startX = Math.max(labelWidth, startX); endX = Math.min(width - padding, endX);
                if (endX < labelWidth) endX = labelWidth;

                if (!item.isInterview) {
                    if (endX > startX) {
                        g2.setColor(item.color); g2.fillRect(startX, y - barHeight / 2, endX - startX, barHeight);
                    } else if (endX == startX && endX > labelWidth) {
                        g2.setColor(item.color); g2.fillOval(startX - barHeight / 2, y - barHeight / 2, barHeight, barHeight);
                    }
                }
                if (item.isInterview) {
                    int mWidth = 12; g2.setColor(UIUtils.COLOR_BLUE);
                    g2.fillRect(endX - mWidth/2, y - barHeight/2 - 2, mWidth, barHeight + 4);
                    g2.setColor(new Color(20, 70, 140)); g2.setStroke(new BasicStroke(2)); g2.drawLine(endX, y - barHeight/2 - 4, endX, y + barHeight/2 + 4);
                } else if (item.reminder != null) {
                    long daysReminder = ChronoUnit.DAYS.between(today, item.reminder);
                    int remX = todayX + (int)(daysReminder * pixelsPerDay);
                    if (remX >= labelWidth && remX <= width - padding) {
                        g2.setColor(UIUtils.TEXT_WHITE); g2.setStroke(new BasicStroke(2)); g2.drawLine(remX, y - barHeight/2 - 2, remX, y + barHeight/2 + 2);
                    }
                }
            }
            Stroke dashed = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
            g2.setStroke(dashed); g2.setColor(UIUtils.COLOR_RED);
            int bottomY = height - padding - 20; g2.drawLine(todayX, 50, todayX, bottomY);
            g2.setFont(new Font("Arial", Font.PLAIN, 10)); g2.setColor(UIUtils.TEXT_GREY);
            g2.drawString("Vor 2 Wochen", todayX - (14 * pixelsPerDay) - 35, bottomY + 15);
            g2.drawString("HEUTE", todayX - 15, bottomY + 15);
            g2.drawString("In 2 Wochen", todayX + (14 * pixelsPerDay) - 30, bottomY + 15);
            g2.drawString("In 4 Wochen", todayX + (28 * pixelsPerDay) - 30, bottomY + 15);
        }
    }

    public class DonutChart extends JPanel {
        int success, waiting, rejected;
        public DonutChart() { setBackground(UIUtils.SIDEBAR_BG); setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60))); setPreferredSize(new Dimension(350, 0)); }
        public void updateData(int s, int w, int r) { success = s; waiting = w; rejected = r; repaint(); }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g); Graphics2D g2 = (Graphics2D) g; g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(UIUtils.TEXT_WHITE); g2.setFont(new Font("Arial", Font.BOLD, 18)); g2.drawString("Status Übersicht", 20, 30);
            int total = success + waiting + rejected;
            if (total == 0) { g2.setFont(new Font("Arial", Font.PLAIN, 12)); g2.setColor(UIUtils.TEXT_GREY); g2.drawString("Keine Daten vorhanden.", 20, 70); return; }
            int width = getWidth(); int height = getHeight(); int legendWidth = 140; int padding = 40;
            int size = Math.min(width - legendWidth - padding, height - padding - 40); if (size < 10) size = 10;
            int x = (width - legendWidth - size) / 2 + 10; int y = (height - size) / 2 + 15;

            double startAngle = 90;
            double sAngle = ((double) success / total) * 360; double wAngle = ((double) waiting / total) * 360; double rAngle = ((double) rejected / total) * 360;

            if (success > 0) { g2.setColor(UIUtils.COLOR_GREEN); g2.fill(new Arc2D.Double(x, y, size, size, startAngle, -sAngle, Arc2D.PIE)); startAngle -= sAngle; }
            if (waiting > 0) { g2.setColor(UIUtils.COLOR_YELLOW); g2.fill(new Arc2D.Double(x, y, size, size, startAngle, -wAngle, Arc2D.PIE)); startAngle -= wAngle; }
            if (rejected > 0) { g2.setColor(UIUtils.COLOR_RED); g2.fill(new Arc2D.Double(x, y, size, size, startAngle, -rAngle, Arc2D.PIE)); }

            int innerSize = size / 2; int innerX = x + (size - innerSize) / 2; int innerY = y + (size - innerSize) / 2;
            g2.setColor(UIUtils.SIDEBAR_BG); g2.fillOval(innerX, innerY, innerSize, innerSize);

            int legendX = x + size + 20; int legendY = height / 2 - 25; g2.setFont(new Font("Arial", Font.PLAIN, 11));
            drawLegendItem(g2, legendX, legendY, UIUtils.COLOR_GREEN, "Erfolg (" + success + ")");
            drawLegendItem(g2, legendX, legendY + 25, UIUtils.COLOR_YELLOW, "Auslaufen (" + waiting + ")");
            drawLegendItem(g2, legendX, legendY + 50, UIUtils.COLOR_RED, "Misserfolg (" + rejected + ")");
        }
        private void drawLegendItem(Graphics2D g2, int x, int y, Color color, String text) {
            g2.setColor(color); g2.fillRect(x, y - 8, 10, 10); g2.setColor(UIUtils.TEXT_WHITE); g2.drawString(text, x + 20, y);
        }
    }
}