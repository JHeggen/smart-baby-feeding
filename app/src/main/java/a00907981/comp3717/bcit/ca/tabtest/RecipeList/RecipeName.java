package a00907981.comp3717.bcit.ca.tabtest.RecipeList;

/**
 * Created by Getry on 5/14/2017.
 */

public class RecipeName {
    private String rName;
    private int rId;

    RecipeName(String name){
        rName = name;
    }
    public String getrName(){
        return rName;
    }
    public int getrId() {
        return rId;
    }
    public void setrId(int id) {
        rId = id;
    }
}
