package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import a00907981.comp3717.bcit.ca.tabtest.Database.dao.App;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.DaoSession;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.IngredientDao;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.RecipeDao;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe_Ingredient;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.Recipe_IngredientDao;
import a00907981.comp3717.bcit.ca.tabtest.R;

/**
 * Created by Getry on 5/18/2017.
 */

public class Ingred_List extends Fragment {
    private ArrayList<Pair<Long, String>> mItemArray;
    private DragListView mDragListView;
    private ListSwipeHelper mSwipeHelper;
    private MySwipeRefreshLayout mRefreshLayout;
    private long recipePK;
    private long ingPK;
    private long ingPos;

    DaoSession daoSession;
    private IngredientDao ingredientDao;
    private Recipe_IngredientDao recipe_ingredientDao;

    private long offset;

    public static Ingred_List newInstance(long recPK, long ingPOS) {
        Ingred_List temp = new Ingred_List();
        temp.setRecipePK(recPK);
        temp.setIngredPos(ingPOS);
        //ingPOS = ingPos + something;
        return temp;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        daoSession = ((App)getActivity().getApplication()).getDaoSession();

    }

    public long getRecipePK(){
        return recipePK;
    }

    public long getIngPK() { return ingPK; }

    public long getIngredID(String ingName) {

        ingredientDao = daoSession.getIngredientDao();

        Query<Ingredient> ingredQuery = ingredientDao.queryBuilder().where(IngredientDao.Properties.Ingredient_name.eq(ingName)).build();
        Ingredient ingPosGet = ingredQuery.unique();
        return ingPosGet.getIngre_id();
    }
    public void queryDB(){

        mItemArray = new ArrayList<>();

        DaoSession daoSession = ((App)getActivity().getApplication()).getDaoSession();
        ingredientDao = daoSession.getIngredientDao();

        Query<Ingredient> ingredQuery = ingredientDao.queryBuilder().build();
        long i = 0;
        for(Ingredient recipe : ingredQuery.list()){
            mItemArray.add(new Pair<Long, String>(i++, recipe.getIngredient_name()));
        }

        Toast.makeText(getContext(), "IN QUERYDB", Toast.LENGTH_SHORT).show();
    }

    public void setRecipePK(long rpk) {
        recipePK = rpk;
    }
    public void setIngredPos(long ipos) {ingPos = ipos;}

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
                FragmentManager fm = getActivity().getSupportFragmentManager();


                IngredCreator rNameCreate = new IngredCreator();
                rNameCreate.show(fm,"ingreddialog");



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
                    Toast.makeText(getContext(), "CANNOT EDIT", Toast.LENGTH_SHORT).show();

                }

                // Swipe to delete on Right
                if (swipedDirection == ListSwipeItem.SwipeDirection.RIGHT) {
                    Pair<Long, String> adapterItem = (Pair<Long, String>) item.getTag();
                    int pos = mDragListView.getAdapter().getPositionForItem(adapterItem);

                    recipe_ingredientDao = daoSession.getRecipe_IngredientDao();

                    long ingredientPK = ingredientDao.queryBuilder().where(IngredientDao.Properties.Ingredient_name.eq(mItemArray.get(pos).second)).build().unique().getIngre_id();

                    DeleteQuery<Recipe_Ingredient> recipe_ingredientDeleteQuery = recipe_ingredientDao.queryBuilder().where(Recipe_IngredientDao.Properties.Ingre_id_FK.eq(ingredientPK)).buildDelete();
                    recipe_ingredientDeleteQuery.executeDeleteWithoutDetachingEntities();

                    DeleteQuery<Ingredient> deleteQuery = ingredientDao.queryBuilder().where(IngredientDao.Properties.Ingre_id.eq(ingredientPK)).buildDelete();
                    deleteQuery.executeDeleteWithoutDetachingEntities();

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

                Ingredient_Amount ingredient_amount = new Ingredient_Amount();
                ingredient_amount.show(getFragmentManager(), "amount_dialog");

                ingPos  = getAdapterPosition();
                ingPK = getIngredID(mItemList.get((int)ingPos).second);

                /**
                DaoSession daoSession = ((App)getActivity().getApplication()).getDaoSession();
                recipeIngDao = daoSession.getRecipe_IngredientDao();
                Recipe_Ingredient ingred = new Recipe_Ingredient();
                ingred.setIngre_id_FK(ingPK);
                ingred.setRecipe_id_FK(recipePK);
                recipeIngDao.insert(ingred);

                FragmentManager fm = getFragmentManager();
                fm.popBackStackImmediate();

                 */
            }

            @Override
            public boolean onItemLongClicked(View view) {
                Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
    }

}

