package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

/**
 * Created by Getry on 5/11/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragItemAdapter;
import com.woxthebox.draglistview.DragListView;
import com.woxthebox.draglistview.swipe.ListSwipeHelper;
import com.woxthebox.draglistview.swipe.ListSwipeItem;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

import a00907981.comp3717.bcit.ca.tabtest.Database.dao.App;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.IngredientDao;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.RecipeDao;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe_Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe_IngredientDao;
import a00907981.comp3717.bcit.ca.tabtest.R;

public class Ingred extends Fragment {

    private ArrayList<Pair<Long, String>> mItemArray;
    private DragListView mDragListView;
    private ListSwipeHelper mSwipeHelper;
    private MySwipeRefreshLayout mRefreshLayout;
    private String recipeName;
    private long recipePK;
    private long ingredPos;

    private RecipeDao recipeDao;
    private Recipe_IngredientDao recipeIngDao;
    private IngredientDao ingredientDao;

    public static Ingred newInstance(String name) {
        Ingred temp = new Ingred();
        temp.setRecipeName(name);
        return temp;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setRecipePK() {
        DaoSession daoSession = ((App)getActivity().getApplication()).getDaoSession();
        recipeDao = daoSession.getRecipeDao();

        Query<Recipe> recipeQuery = recipeDao.queryBuilder().where(RecipeDao.Properties.Recipe_name.eq(recipeName.toString())).build();

        Recipe recipe = recipeQuery.unique();
        recipePK =recipe.getRecipe_id();
        Toast.makeText(getContext(), "IN RecipeQuesry pk is " + recipePK, Toast.LENGTH_SHORT).show();
    }

    public long getIngredPos(long ingredID) {
        DaoSession daoSession = ((App)getActivity().getApplication()).getDaoSession();
        recipeIngDao = daoSession.getRecipe_IngredientDao();
        QueryBuilder<Recipe_Ingredient> recipeIngQuery = recipeIngDao.queryBuilder();
        recipeIngQuery.where(Recipe_IngredientDao.Properties.Recipe_id_FK.eq(recipePK),Recipe_IngredientDao.Properties.Ingre_id_FK.eq(ingredID));
        Recipe_Ingredient recIng = recipeIngQuery.unique();
        return recIng.getOrder();



    }

    public void queryDB(){


        mItemArray = new ArrayList<>();

        DaoSession daoSession = ((App)getActivity().getApplication()).getDaoSession();
        ingredientDao = daoSession.getIngredientDao();
        QueryBuilder<Ingredient> ingredientQuery = ingredientDao.queryBuilder();
        ingredientQuery.join(Recipe_Ingredient.class, Recipe_IngredientDao.Properties.Recipe_id_FK).where(Recipe_IngredientDao.Properties.Recipe_id_FK.eq(recipePK));




        long i = 0;
        for(Ingredient ingred : ingredientQuery.list()){
            mItemArray.add(new Pair<Long, String>(i++, ingred.getIngredient_name()));
        }

        Toast.makeText(getContext(), "IN QUERYDB", Toast.LENGTH_SHORT).show();
    }

    public void setRecipeName(String name) {
        recipeName = name;
    }

    public boolean onSupportNavigateUp(){

        if (getFragmentManager().getBackStackEntryCount() > 0){
            boolean done = getFragmentManager().popBackStackImmediate();
            return done;
        }
        return true;
    }
    public void onBackPressed() {
        // your code.
        if (getFragmentManager().getBackStackEntryCount() > 0){
            boolean done = getFragmentManager().popBackStackImmediate();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_layout, container, false);
        mRefreshLayout = (MySwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mDragListView = (DragListView) view.findViewById(R.id.drag_list_view);
        mDragListView.getRecyclerView().setVerticalScrollBarEnabled(true);
        Button button = (Button) view.findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.container, Ingred_List.newInstance(recipePK, ingredPos), "fragment").commit();



            }
        });


        mDragListView.setDragListListener(new DragListView.DragListListenerAdapter() {
            @Override
            public void onItemDragStarted(int position) {
                mRefreshLayout.setEnabled(false);
                Toast.makeText(mDragListView.getContext(), "Start - position: " + position, Toast.LENGTH_SHORT).show();
            }
            public void onBackPressed()
            {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }

            @Override
            public void onItemDragEnded(int fromPosition, int toPosition) {
                mRefreshLayout.setEnabled(true);
                if (fromPosition != toPosition) {
                    Toast.makeText(mDragListView.getContext(), "End - position: " + toPosition, Toast.LENGTH_SHORT).show();
                }
            }
        });
        ingredPos = 0;
        setRecipePK();
        queryDB();
        /**
        mItemArray = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            mItemArray.add(new Pair<>((long) i, "Item " + i));
        }
         */
        mRefreshLayout.setScrollingView(mDragListView.getRecyclerView());
        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.app_color));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        mDragListView.setSwipeListener(new ListSwipeHelper.OnSwipeListenerAdapter() {
            @Override
            public void onItemSwipeStarted(ListSwipeItem item) {
                mRefreshLayout.setEnabled(false);
            }

            @Override
            public void onItemSwipeEnded(ListSwipeItem item, ListSwipeItem.SwipeDirection swipedDirection) {
                mRefreshLayout.setEnabled(true);

                if (swipedDirection == ListSwipeItem.SwipeDirection.LEFT) {

                    Pair<Long, String> adapterItem = (Pair<Long, String>) item.getTag();
                    int pos = mDragListView.getAdapter().getPositionForItem(adapterItem);
                    FragmentManager fm = getActivity().getSupportFragmentManager();


                    IngredCreator rNameCreate = IngredCreator.newInstance(mItemArray.get(pos).second);
                    rNameCreate.show(fm,"ingreddialog");

                }

                // Swipe to delete on Right
                if (swipedDirection == ListSwipeItem.SwipeDirection.RIGHT) {
                    Pair<Long, String> adapterItem = (Pair<Long, String>) item.getTag();
                    int pos = mDragListView.getAdapter().getPositionForItem(adapterItem);
                    mDragListView.getAdapter().removeItem(pos);
                }
            }
        });

        setupListRecyclerView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Ingredients");
    }


    public void setupListRecyclerView() {
        mDragListView.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemAdapter listAdapter = new ItemAdapter(mItemArray, R.layout.list_item, R.id.image, false);
        mDragListView.setAdapter(listAdapter, true);
        mDragListView.setCanDragHorizontally(false);
        mDragListView.setCustomDragItem(new MyDragItem(getContext(), R.layout.list_item));
    }


    private static class MyDragItem extends DragItem {

        MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
            CharSequence text = ((TextView) clickedView.findViewById(R.id.text)).getText();
            ((TextView) dragView.findViewById(R.id.text)).setText(text);
            dragView.findViewById(R.id.item_layout).setBackgroundColor(dragView.getResources().getColor(R.color.list_item_background));
        }
    }
    class ItemAdapter extends DragItemAdapter<Pair<Long, String>, ItemAdapter.ViewHolder> {

        private int mLayoutId;
        private int mGrabHandleId;
        private boolean mDragOnLongPress;

        ItemAdapter(ArrayList<Pair<Long, String>> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {
            mLayoutId = layoutId;
            mGrabHandleId = grabHandleId;
            mDragOnLongPress = dragOnLongPress;
            setHasStableIds(true);
            setItemList(list);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            String text = mItemList.get(position).second;
            holder.mText.setText(text);
            holder.itemView.setTag(mItemList.get(position));
        }

        @Override
        public long getItemId(int position) {
            return mItemList.get(position).first;
        }

        class ViewHolder extends DragItemAdapter.ViewHolder {
            TextView mText;

            ViewHolder(final View itemView) {
                super(itemView, mGrabHandleId, mDragOnLongPress);
                mText = (TextView) itemView.findViewById(R.id.text);
            }

            @Override
            public void onItemClicked(View view) {
                Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClicked(View view) {
                Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
    }

}
