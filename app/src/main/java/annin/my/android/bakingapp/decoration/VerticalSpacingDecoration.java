package annin.my.android.bakingapp.decoration;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class VerticalSpacingDecoration extends RecyclerView.ItemDecoration
{
    private int spacing;

    public VerticalSpacingDecoration(int spacing)
    {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        outRect.bottom = spacing;
        outRect.left = spacing;
        outRect.right = spacing;
    }
}




