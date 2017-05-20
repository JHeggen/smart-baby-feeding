package a00907981.comp3717.bcit.ca.tabtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import a00907981.comp3717.bcit.ca.tabtest.Database.dao.App;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.History;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.HistoryDao;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.RecipeDao;
import a00907981.comp3717.bcit.ca.tabtest.RecipeList.Recipes;

public class TabActivity extends AppCompatActivity {

    private DaoSession daoSession;
    private HistoryDao historyDao;
    private RecipeDao  recipeDao;

    private EditText   editWeightVal;
    private double     btWeightVal = -1;

    private double     feTotal;
    private double     fatTotal;
    private double     caTotal;
    private double     protTotal;
    private double     enerTotal;

    private double     startWeight;
    private double     endWeight;
    private double     netWeight;

    private boolean startNotNeg;
    private boolean endNotNeg;

    private Timer timer;

    final int freq = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        daoSession = ((App) getApplication()).getDaoSession();
        historyDao = daoSession.getHistoryDao();
        recipeDao = daoSession.getRecipeDao();

        editWeightVal = (EditText) findViewById(R.id.editText);
        editWeightVal.setHint("Enter Weight");

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        createTabs();
        createChart();
        showFragment(Recipes.newInstance());
        refreshSpinner();
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

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {


                switch(tabId) {
                    case "Tab1":
                        refreshSpinner();
                        //createTimer();
                        break;
                    case "Tab2":
                        //killTimer();
                        break;
                    case "Tab3":
                        //killTimer();
                        break;
                    case "Tab4":
                        //killTimer();
                        break;
                    default:
                        //killTimer();
                        break;
                }

            }
        });
    }

    public void refreshSpinner() {
        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        Query<Recipe> recipeQuery = recipeDao.queryBuilder().build();

        List<String> spinnerList = new ArrayList<>();

        for(Recipe item : recipeQuery.list()){
            spinnerList.add(item.getRecipe_name());
        }
 
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, spinnerList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

    }

    public void createTimer() {
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(btWeightVal > 0) {
                    editWeightVal.setText("" + btWeightVal);
                }
            }
        }, 0, freq);
    }

    public void killTimer(){
        timer.purge();
        timer.cancel();
    }


    public void setStartWeight(View v) {


        if(editWeightVal.getText().toString().equals("")) {
            editWeightVal.setError("Please Enter Number");
        } else {
            startWeight = Double.parseDouble(editWeightVal.getText().toString());
        }

        editWeightVal.setText("");

        startNotNeg = (startWeight < 0) ? false : true;


        if(!startNotNeg){
            editWeightVal.setError("Negative Number");
        }

    }

    public void setEndWeight(View v) {

        if(editWeightVal.getText().toString().equals("")) {
            editWeightVal.setError("Please Enter Number");
        } else {
            endWeight = Double.parseDouble(editWeightVal.getText().toString());
        }

        editWeightVal.setText("");

        endNotNeg = (endWeight < 0) ? false : true;

        if(!endNotNeg){
            editWeightVal.setError("Negative Number");
        }

        getNetWeight();
        setNutritionRows();
    }

    public void getNetWeight() {
        netWeight = startWeight - endWeight;
        if (!startNotNeg) {
            editWeightVal.setError("Negative starting value");
        } else if (!endNotNeg) {
            editWeightVal.setError("Negative ending value");
        } else if(netWeight < 0) {
            editWeightVal.setError("Negative weight value");
        }else {
            editWeightVal.setText("Net Weight: " + netWeight);
            btWeightVal = netWeight;
        }
    }

    public void setNutritionRows() {
        TableRow    tableRow1 = (TableRow) findViewById(R.id.row1);
        TableRow    tableRow2 = (TableRow) findViewById(R.id.row2);
        TableRow    tableRow3 = (TableRow) findViewById(R.id.row3);
        TableRow    tableRow4 = (TableRow) findViewById(R.id.row4);
        TableRow    tableRow5 = (TableRow) findViewById(R.id.row5);

        Query<Recipe> nutritionQuery = recipeDao.queryBuilder().build();

        for(Recipe nutrient : nutritionQuery.list()) {

            //use where to specify which recipe it is?
            feTotal   = (btWeightVal * nutrient.getIron_total());
            caTotal   = (btWeightVal * nutrient.getCa_total());
            fatTotal  = (btWeightVal * nutrient.getFat_total());
            protTotal = (btWeightVal * nutrient.getProt_total());
            enerTotal = (btWeightVal * nutrient.getEnergy_total());
        }

        TextView tv;
        // Fill out our cells
        tv = (TextView) tableRow1.findViewById(R.id.item1);
        tv.setText("Iron: " + feTotal);

        tv = (TextView) tableRow2.findViewById(R.id.item2);
        tv.setText("Calcium: " + caTotal);

        tv = (TextView) tableRow3.findViewById(R.id.item3);
        tv.setText("Fat: " + fatTotal);

        tv = (TextView) tableRow4.findViewById(R.id.item4);
        tv.setText("Protein: " + protTotal);

        tv = (TextView) tableRow5.findViewById(R.id.item5);
        tv.setText("Energy: " + enerTotal);
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
