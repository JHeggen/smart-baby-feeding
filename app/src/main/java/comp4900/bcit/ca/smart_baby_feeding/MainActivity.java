package comp4900.bcit.ca.smart_baby_feeding;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.greenrobot.greendao.query.Query;

public class MainActivity extends AppCompatActivity {

    private RecipeDao recipeDao;
    private Query<Recipe> recipeQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession daoSession = ((App)getApplication()).getDaoSession();
        recipeDao = daoSession.getRecipeDao();
    }
}
