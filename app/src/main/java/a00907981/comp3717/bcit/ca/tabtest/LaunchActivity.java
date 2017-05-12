package a00907981.comp3717.bcit.ca.tabtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.greendao.database.Database;

import java.util.Scanner;

import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoMaster;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Ingredient;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "sbf-db");
        Database db = helper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();
        //DaoSession daoSession = ((App) getApplication()).getDaoSession();
        populateDB(daoSession);
    }

    public void feedClick(View view){
        Intent intent = new Intent(this, TabActivity.class);
        intent.putExtra("tab", 0);
        startActivity(intent);
    }

    public void recipeClick(View view){
        Intent intent = new Intent(this, TabActivity.class);
        intent.putExtra("tab", 1);
        startActivity(intent);
    }

    public void historyClick(View view){
        Intent intent = new Intent(this, TabActivity.class);
        intent.putExtra("tab", 2);
        startActivity(intent);
    }

    public void clockClick(View view){
        Intent intent = new Intent(this, TabActivity.class);
        intent.putExtra("tab", 3);
        startActivity(intent);
    }

    public void populateDB(DaoSession sesh){
        sesh.clear();
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.dummydata));
        while(scan.hasNext()){
            String input = scan.nextLine();
            String[] arr = input.split(",");

            String tempName = arr[0];
            double tempEnergy = Double.parseDouble(arr[1]);
            double tempProt = Double.parseDouble(arr[2]);
            double tempCho = Double.parseDouble(arr[3]);
            double tempFat = Double.parseDouble(arr[4]);
            double tempNA = Double.parseDouble(arr[5]);
            double tempK = Double.parseDouble(arr[6]);
            double tempCL = Double.parseDouble(arr[7]);
            double tempCA = Double.parseDouble(arr[8]);
            double tempPO = Double.parseDouble(arr[9]);
            double tempMG = Double.parseDouble(arr[10]);
            double tempIRON = Double.parseDouble(arr[11]);
            double tempVit_a = Double.parseDouble(arr[12]);
            double tempVit_d = Double.parseDouble(arr[13]);
            double tempFolic_acid = Double.parseDouble(arr[14]);
            double tempMosm_l = Double.parseDouble(arr[15]);
            double tempMosm_kg = Double.parseDouble(arr[16]);

            insertIngre(tempName,
                        tempEnergy,
                        tempProt,
                        tempCho,
                        tempFat,
                        tempNA,
                        tempK,
                        tempCL,
                        tempCA,
                        tempPO,
                        tempMG,
                        tempIRON,
                        tempVit_a,
                        tempVit_d,
                        tempFolic_acid,
                        tempMosm_l,
                        tempMosm_kg);
        }

    }

    public void insertIngre(String name,
                             double energy,
                             double prot,
                             double cho,
                             double fat,
                             double na,
                             double k,
                             double cl,
                             double ca,
                             double po,
                             double mg,
                             double iron,
                             double vit_a,
                             double vit_d,
                             double folic_acid,
                             double mosm_l,
                             double mosm_kg){

        Ingredient ingre = new Ingredient();

        ingre.setIngredient_name(name);
        ingre.setEnergy(energy);
        ingre.setProt(prot);
        ingre.setCho(cho);
        ingre.setFat(fat);
        ingre.setNa_mmol_l(na);
        ingre.setK_mmol_l(k);
        ingre.setCl_mmol_l(cl);
        ingre.setCa_mmol_l(ca);
        ingre.setPo_mmol_l(po);
        ingre.setMg_mmol_l(mg);
        ingre.setIron_mg(iron);
        ingre.setVit_a_ug(vit_a);
        ingre.setVit_d_ug(vit_d);
        ingre.setFolic_acid_ug(folic_acid);
        ingre.setMosm_l(mosm_l);
        ingre.setMosm_kg(mosm_kg);

    }
}
