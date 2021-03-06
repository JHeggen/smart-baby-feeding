package a00907981.comp3717.bcit.ca.tabtest.RecipeList;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragItemAdapter;
import com.woxthebox.draglistview.DragListView;
import com.woxthebox.draglistview.swipe.ListSwipeHelper;
import com.woxthebox.draglistview.swipe.ListSwipeItem;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import a00907981.comp3717.bcit.ca.tabtest.Database.dao.App;
import a00907981.comp3717.bcit.ca.tabtest.Database.tables.*;
import a00907981.comp3717.bcit.ca.tabtest.R;
import a00907981.comp3717.bcit.ca.tabtest.TabActivity;

/**
 * Created by Getry on 5/11/2017.
 */

public class Recipes extends Fragment {
    private ArrayList<Pair<Long, String>> mItemArray;
    private DragListView mDragListView;
    private MySwipeRefreshLayout mRefreshLayout;

    private DaoSession daoSession;
    private RecipeDao recipeDao;
    private Recipe_IngredientDao recipe_ingredientDao;

    private long ingPos;


    public static Recipes newInstance() {
        return new Recipes();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        daoSession = ((App)getActivity().getApplication()).getDaoSession();

    }

    public void queryDB(){
        mItemArray = new ArrayList<>();


        recipeDao = daoSession.getRecipeDao();

        Query<Recipe> recipeQuery = recipeDao.queryBuilder().orderAsc(RecipeDao.Properties.Recipe_name).build();
        long i = 0;
        for(Recipe recipe : recipeQuery.list()){
            mItemArray.add(new Pair<Long, String>(i++, recipe.getRecipe_name()));
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_layout, container, false);
        mRefreshLayout = (MySwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mDragListView = (DragListView) view.findViewById(R.id.drag_list_view);
        mDragListView.getRecyclerView().setVerticalScrollBarEnabled(true);


        queryDB();

        Button button = (Button) view.findViewById(R.id.add_button);



        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                // do something
                FragmentManager fm = getActivity().getSupportFragmentManager();


                RecipeNameCreator rNameCreate = new RecipeNameCreator();
                rNameCreate.show(fm,"Dialog");
                rNameCreate.getFragmentManager().executePendingTransactions();
                queryDB();

                setupListRecyclerView();
            }
        });

        mDragListView.setDragListListener(new DragListView.DragListListenerAdapter() {
            @Override
            public void onItemDragStarted(int position) {
                mRefreshLayout.setEnabled(false);
                Toast.makeText(mDragListView.getContext(), "Start - position: " + position, Toast.LENGTH_SHORT).show();
            }
            private void showFragment(Fragment fragment) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment, "fragment").commit();
            }

            @Override
            public void onItemDragEnded(int fromPosition, int toPosition) {
                mRefreshLayout.setEnabled(true);
                if (fromPosition != toPosition) {
                    Toast.makeText(mDragListView.getContext(), "End - position: " + toPosition, Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                queryDB();

                setupListRecyclerView();
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
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.container, Ingred.newInstance(mItemArray.get(pos).second), "fragment").commit();

                }

                if (swipedDirection == ListSwipeItem.SwipeDirection.RIGHT) {
                    Pair<Long, String> adapterItem = (Pair<Long, String>) item.getTag();
                    int pos = mDragListView.getAdapter().getPositionForItem(adapterItem);

                    recipe_ingredientDao = daoSession.getRecipe_IngredientDao();

                    Recipe recipeQuery = recipeDao.queryBuilder().where(RecipeDao.Properties.Recipe_name.eq(mItemArray.get(pos).second)).build().unique();

                    long recipeID = recipeQuery.getRecipe_id();

                    DeleteQuery<Recipe_Ingredient> recipe_ingredientDeleteQuery = recipe_ingredientDao.queryBuilder().where(Recipe_IngredientDao.Properties.Recipe_id_FK.eq(recipeID)).buildDelete();
                    recipe_ingredientDeleteQuery.executeDeleteWithoutDetachingEntities();

                    DeleteQuery<Recipe> deleteQuery = recipeDao.queryBuilder().where(RecipeDao.Properties.Recipe_id.eq(recipeID)).buildDelete();
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
                ingPos  = getAdapterPosition();
                FragmentManager fm = getActivity().getSupportFragmentManager();

                Bundle args = new Bundle();
                args.putInt("pos", getAdapterPosition());

                Make_or_Use_dialog make_or_use_dialog = Make_or_Use_dialog.newInstance(mItemList.get((int)ingPos).second);
                make_or_use_dialog.setArguments(args);
                make_or_use_dialog.show(fm, "mou_dialog");

            }

            @Override
            public boolean onItemLongClicked(View view) {
                //Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
                return true;
            }

        }
    }
}
