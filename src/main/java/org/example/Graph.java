package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.HorizontalAlignment;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Graph extends JFrame {
    public Graph(Map<String, Integer> map){
        init(map);
    }

    private void init(Map<String, Integer> map) {
        DefaultPieDataset dataset = createDataset(map);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        var padding = 15;
        chartPanel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        chartPanel.setBackground(Color.WHITE);
        add(chartPanel);

        pack();
        setTitle("Количество изделий по производителю");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JFreeChart createChart(DefaultPieDataset dataset) {
        var chart = ChartFactory.createPieChart(
                "Количество изделий по производителю",
                dataset);

        chart.setBackgroundPaint(Color.WHITE);

        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.CENTER);
        t.setPaint(new Color(20, 20, 20));
        t.setFont(new Font("Arial", Font.BOLD, 24));

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("Arial", Font.BOLD, 20));
        plot.setLabelLinkStroke(new BasicStroke(1.5f));
        plot.setLabelLinkPaint(Color.WHITE);
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);

        return chart;
    }

    private DefaultPieDataset createDataset(Map<String, Integer> map) {
        var dataset = new DefaultPieDataset();
        map.forEach(dataset::setValue);
        return dataset;
    }
}
