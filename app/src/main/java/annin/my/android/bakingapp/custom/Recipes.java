package annin.my.android.bakingapp.custom;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maino96-10022 on 12/18/2017.
 */

public class Recipes implements Parcelable {

    /**
     * Recipe id
     */
    private String recipeId;

    /**
     * Recipe name
     */
    private String recipeName;

    /**
     * Recipe image
     */
    private String recipeImage;

    /**
     * Number of servings
     */
    private String recipeServings;

    /**
     * List of ingredients
     */
    private List<Ingredients> recipeIngredients;

    /**
     * List of steps
     */
    private List<Steps> recipeSteps;

    public Recipes(String recipeId, String recipeName, String recipeImage, String recipeServings, List<Ingredients> recipeIngredients, List<Steps> recipeSteps) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.recipeServings = recipeServings;
        this.recipeIngredients = recipeIngredients;
        this.recipeSteps = recipeSteps;

    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeServings(String recipeServings) {
        this.recipeServings = recipeServings;
    }

    public String getRecipeServings() {
        return recipeServings;
    }

    protected Recipes(Parcel in) {
        recipeId = in.readString();
        recipeName = in.readString();
        recipeImage = in.readString();
        recipeServings = in.readString();
        if (in.readByte() == 0x01) {
            recipeIngredients = new ArrayList<Ingredients>();
            in.readList(recipeIngredients, Ingredients.class.getClassLoader());
        } else {
            recipeIngredients = null;
        }
        if (in.readByte() == 0x01) {
            recipeSteps = new ArrayList<Steps>();
            in.readList(recipeSteps, Steps.class.getClassLoader());
        } else {
            recipeSteps = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeId);
        dest.writeString(recipeName);
        dest.writeString(recipeImage);
        dest.writeString(recipeServings);
        if (recipeIngredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(recipeIngredients);
        }
        if (recipeSteps == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(recipeSteps);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipes> CREATOR = new Parcelable.Creator<Recipes>() {
        @Override
        public Recipes createFromParcel(Parcel in) {
            return new Recipes(in);
        }

        @Override
        public Recipes[] newArray(int size) {
            return new Recipes[size];
        }
    };
}


