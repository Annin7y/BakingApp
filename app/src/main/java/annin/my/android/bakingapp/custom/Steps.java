package annin.my.android.bakingapp.custom;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maino96-10022 on 12/14/2017.
 */

public class Steps implements Parcelable {

    /**
     * Step id
     */
    private String stepId;

    /**
     * Short description
     */
    private String stepShortDescription;

    /**
     * Detailed description
     */
    private String stepDescription;

    /**
     * Video URL
     */
    private String videoUrl;


    /**
     * Thumbnail URL
     */
    private String posterUrl;


    public Steps(String ingredientQuantity, String ingredientMeasure, String ingredientName) {
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientMeasure = ingredientMeasure;
        this.ingredientName = ingredientName;
    }

    public void setIngredientQuantity(String ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public String getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientMeasure(String ingredientMeasure) {
        this.ingredientMeasure = ingredientQuantity;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }

    public void setName(String ingredientMeasure) {
        this.ingredientMeasure = ingredientQuantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    protected Ingredients(Parcel in) {
        ingredientQuantity = in.readString();
        ingredientMeasure = in.readString();
        ingredientName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ingredientQuantity);
        dest.writeString(ingredientMeasure);
        dest.writeString(ingredientName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ingredients> CREATOR = new Parcelable.Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}


