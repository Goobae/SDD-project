package edu.carthage.johnson.grant.aerophile;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Grant on 11/22/2015.
 */
public class Project implements Serializable, Parcelable{
    private String projectName;
    private int partnerCount = 0;
    private String filepath;

    public Project()
    {

    }
    public Project(String projectName,String filepath){
        this.projectName = projectName;
        this.filepath = filepath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getPartnerCount() {
        return partnerCount;
    }

    public void setPartnerCount(int partnerCount) {
        this.partnerCount = partnerCount;
    }

    public String getFilepath() { return filepath; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(projectName);
        dest.writeInt(partnerCount);
        dest.writeString(filepath);
    }

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    private Project(Parcel in)
    {
        String test = in.readString();
        projectName = in.readString();
        partnerCount = in.readInt();
        filepath = in.readString();
    }
}
