package a00907981.comp3717.bcit.ca.tabtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import a00907981.comp3717.bcit.ca.tabtest.Database.dao.App;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.History;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.HistoryDao;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.RecipeList.Recipes;

public class TabActivity extends AppCompatActivity {

    private DaoSession daoSession;
    private HistoryDao historyDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        daoSession = ((App) getApplication()).getDaoSession();
        historyDao = daoSession.getHistoryDao();

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        createTabs();
        createChart();
        showFragment(Recipes.newInstance());

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    public void goToSettings(MenuItem item){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void createChart(){
        PieChart pchart = (PieChart)findViewById(R.id.pchart);

        /*
        Query<History> historyQuery = historyDao.queryBuilder().build();

        for(History h : historyQuery.list()){
            Log.d("myTag" , h.getDate());
        }
        */

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(15F, "Vegetables"));
        entries.add(new PieEntry(30F, "Water"));
        entries.add(new PieEntry(50F, "Meat"));
        entries.add(new PieEntry(5F, "IceCream"));

        PieDataSet set = new PieDataSet(entries, "Food Consumed");
        set.setSliceSpace(8F);
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData data = new PieData(set);
        pchart.setData(data);
        pchart.invalidate();
    }

    public void createTabs(){
        TabHost host = (TabHost) findViewById(R.id.tabhost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("Tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Feed");
        host.addTab(spec);

        spec = host.newTabSpec("Tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Recipes");
        host.addTab(spec);

        spec = host.newTabSpec("Tab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("History");
        host.addTab(spec);

        spec = host.newTabSpec("Tab4");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Alarm");
        host.addTab(spec);

        switch(getFocus()){
            case 0:
                host.setCurrentTab(0);
                break;
            case 1:
                host.setCurrentTab(1);
                break;
            case 2:
                host.setCurrentTab(2);
                break;
            case 3:
                host.setCurrentTab(3);
                break;
            default:
                host.setCurrentTab(0);
                break;
        }
    }

    public int getFocus(){
        Intent intent = getIntent();
        int focus = intent.getIntExtra("tab", 1);
        return focus;
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, "fragment").commit();
    }
}
