package com.miex.shepherds.core.domain;

import javax.validation.constraints.NotNull;

public class Job {

    /**
     * 文件的crc 16
     */
    @NotNull
    Long index;

    @NotNull
    String fullName;
    String identification;
    String version;
    String insideVersion;
    String obtainTime;
    String jobStatus;

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInsideVersion() {
        return insideVersion;
    }

    public void setInsideVersion(String insideVersion) {
        this.insideVersion = insideVersion;
    }

    public String getObtainTime() {
        return obtainTime;
    }

    public void setObtainTime(String obtainTime) {
        this.obtainTime = obtainTime;
    }

    @Override
    public String toString() {
        return "Job{" +
                "index=" + index +
                ", fullName='" + fullName + '\'' +
                ", identification='" + identification + '\'' +
                ", version='" + version + '\'' +
                ", insideVersion='" + insideVersion + '\'' +
                ", obtainTime='" + obtainTime + '\'' +
                ", jobStatus='" + jobStatus + '\'' +
                '}';
    }
}
