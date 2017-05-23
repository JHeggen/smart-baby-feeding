package a00907981.comp3717.bcit.ca.tabtest.Charts;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.History;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.HistoryDao;

/**
 * Created by Pkao on 2017-05-18.
 */

public class PieChartBuilder {

    private PieChart pieChart;
    private DaoSession daoSession;
    private HistoryDao historyDao;

    private float energy;
    private float prot;
    private float cho;
    private float fat;
    private float na;
    private float k;
    private float cl;
    private float ca;
    private float po;
    private float mg;
    private float iron;
    private float vit_a;
    private float vit_d;
    private float folic_acid;
    private float mosm_l;
    private float mosm_kg;

    private float total_nutrients;

    public PieChartBuilder(PieChart pieChart, DaoSession daoSession){
        this.pieChart = pieChart;
        this.daoSession = daoSession;
        historyDao = daoSession.getHistoryDao();
    }

    public void createChart(){

        Query<History> historyQuery = historyDao.queryBuilder().build();

        for(History h : historyQuery.list()){
            energy += h.getNet_energy();
            prot += h.getNet_prot();
            cho += h.getNet_cho();
            fat += h.getNet_fat();
            na += h.getNet_na();
            k += h.getNet_k();
            cl += h.getNet_cl();
            ca += h.getNet_ca();
            po += h.getNet_po();
            mg += h.getNet_mg();
            iron += h.getNet_iron();
            vit_a += h.getNet_vit_a();
            vit_d += h.getNet_vit_d();
            folic_acid += h.getNet_folic_acid();
        }

        total_nutrients = energy + prot + fat + ca + iron;

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(energy / total_nutrients, "Energy"));
        entries.add(new PieEntry(prot / total_nutrients, "Protein"));
        //entries.add(new PieEntry(cho / total_nutrients, "Carbohydrates"));
        entries.add(new PieEntry(fat / total_nutrients, "Fat"));
        //entries.add(new PieEntry(na / total_nutrients, "Sodium"));
        //entries.add(new PieEntry(k / total_nutrients, "Potasium"));
        //entries.add(new PieEntry(cl / total_nutrients, "Chlorine"));
        entries.add(new PieEntry(ca / total_nutrients, "Calcium"));
        //entries.add(new PieEntry(po / total_nutrients, "Polonium?"));
        //entries.add(new PieEntry(mg / total_nutrients, "Magnesium"));
        entries.add(new PieEntry(iron / total_nutrients, "Iron"));
        //entries.add(new PieEntry(vit_a / total_nutrients, "Vitamin A"));
        //entries.add(new PieEntry(vit_d / total_nutrients, "Vitamin D"));
        //entries.add(new PieEntry(folic_acid / total_nutrients, "Folic Acid"));

        PieDataSet set = new PieDataSet(entries, "Nutrients Consumed");
        set.setSliceSpace(0F);
        set.setSelectionShift(18F);
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData data = new PieData(set);

        pieChart.setUsePercentValues(true);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.setData(data);
        pieChart.invalidate();
    }
}
