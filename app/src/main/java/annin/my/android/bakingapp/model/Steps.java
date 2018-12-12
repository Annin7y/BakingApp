package annin.my.android.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable
{
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
    private String thumbnailUrl;

    public Steps(String stepId, String stepShortDescription, String stepDescription, String videoUrl, String thumbnailUrl)
    {
        this.stepId = stepId;
        this.stepShortDescription = stepShortDescription;
        this.stepDescription = stepDescription;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setStepId(String stepId)
    {
        this.stepId = stepId;
    }

    public String getStepId()
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

    public void setStepDescription(String stepDescription)
    {
        this.stepDescription = stepDescription;
    }

    public String getStepDescription()
    {
        return stepDescription;
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
        stepId = in.readString();
        stepShortDescription = in.readString();
        stepDescription = in.readString();
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
        dest.writeString(stepId);
        dest.writeString(stepShortDescription);
        dest.writeString(stepDescription);
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


