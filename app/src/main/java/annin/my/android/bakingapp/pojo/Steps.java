package annin.my.android.bakingapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable
{
    /**
     * Step id
     */
    private int stepId;

    /**
     * Short description
     */
    private String stepShortDescription;

    /**
     * Detailed description
     */
    private String stepLongDescription;

    /**
     * Video URL
     */
    private String videoUrl;

    /**
     * Thumbnail URL
     */
    private String thumbnailUrl;

    public Steps(int stepId, String stepShortDescription, String stepLongDescription, String videoUrl, String thumbnailUrl)
    {
        this.stepId = stepId;
        this.stepShortDescription = stepShortDescription;
        this.stepLongDescription = stepLongDescription;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setStepId(int stepId)
    {
        this.stepId = stepId;
    }

    public int getStepId()
    {
        return stepId;
    }

    public void setStepShortDescription(String stepShortDescription)
    {
        this.stepShortDescription = stepShortDescription;
    }

    public String getStepShortDescription()
    {
        return stepShortDescription;
    }

    public void setStepLongDescription(String stepLongDescription)
    {
        this.stepLongDescription = stepLongDescription;
    }

    public String getStepLongDescription()
    {
        return stepLongDescription;
    }

    public void setVideoUrl(String stepShortDescription)
    {
        this.stepShortDescription = stepShortDescription;
    }

    public String getVideoUrl()
    {
        return videoUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl)
    {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl()
    {
        return thumbnailUrl;
    }

    protected Steps(Parcel in)
    {
        stepId = in.readInt();
        stepShortDescription = in.readString();
        stepLongDescription = in.readString();
        videoUrl = in.readString();
        thumbnailUrl = in.readString();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(stepId);
        dest.writeString(stepShortDescription);
        dest.writeString(stepLongDescription);
        dest.writeString(videoUrl);
        dest.writeString(thumbnailUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Steps> CREATOR = new Parcelable.Creator<Steps>()
    {
        @Override
        public Steps createFromParcel(Parcel in)
        {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size)
        {
            return new Steps[size];
        }
    };
}


