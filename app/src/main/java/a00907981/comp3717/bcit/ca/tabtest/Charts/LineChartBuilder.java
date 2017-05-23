package a00907981.comp3717.bcit.ca.tabtest.Charts;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.History;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.HistoryDao;

/**
 * Created by Pkao on 2017-05-23.
 */

public class LineChartBuilder {
    private DaoSession daoSession;
    private HistoryDao historyDao;
    private LineChart lineChart;

    public LineChartBuilder(LineChart lineChart, DaoSession daoSession){
        this.lineChart = lineChart;
        this.daoSession = daoSession;
        historyDao = daoSession.getHistoryDao();
    }

    public void createChart(){
        Query<History> historyQuery = historyDao.queryBuilder().build();

        List<Entry> entries = new ArrayList<Entry>();

        for (History h : historyQuery.list()) {
            float date = (float) h.getDate();
            float time = (float) h.getTime();

            Entry temp = new Entry(time, date);

            entries.add(temp);
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "Frequency of feedings");

        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
        Description description = new Description();
        description.setText("");

        lineChart.setDescription(description);

        Legend legend = lineChart.getLegend();
        legend.setWordWrapEnabled(true);

        lineChart.invalidate();
    }
}
