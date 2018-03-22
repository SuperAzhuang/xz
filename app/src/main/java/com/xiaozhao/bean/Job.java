package com.xiaozhao.bean;

/**
 * Created by Administrator on 2018/3/22.
 */

public class Job  {

    private String jobName;
    private String aRea;
    private String jingYan;
    private String Xueli;
    private String Salary;

    public Job(String jobName, String aRea, String jingYan, String xueli, String salary) {
        this.jobName = jobName;
        this.aRea = aRea;
        this.jingYan = jingYan;
        Xueli = xueli;
        Salary = salary;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getaRea() {
        return aRea;
    }

    public void setaRea(String aRea) {
        this.aRea = aRea;
    }

    public String getJingYan() {
        return jingYan;
    }

    public void setJingYan(String jingYan) {
        this.jingYan = jingYan;
    }

    public String getXueli() {
        return Xueli;
    }

    public void setXueli(String xueli) {
        Xueli = xueli;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }
}
