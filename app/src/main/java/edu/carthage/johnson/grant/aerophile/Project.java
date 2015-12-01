package edu.carthage.johnson.grant.aerophile;

/**
 * Created by Grant on 11/22/2015.
 */
public class Project {
    private String projectName;
    private int partnerCount;
    private int projectSize;

    public Project(){

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

    public int getProjectSize() {
        return projectSize;
    }

    public void setProjectSize(int projectSize) {
        this.projectSize = projectSize;
    }
}
