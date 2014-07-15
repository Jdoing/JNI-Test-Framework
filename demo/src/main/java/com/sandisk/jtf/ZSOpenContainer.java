package com.sandisk.jtf;
public class ZSOpenContainer extends JTFCommand {
    public String execute() {
        System.out.println("Call ZSOpenContainer"); // Replace this with real API call
        System.out.println("fifo_mode = " + this.args.getProperty("fifo_mode"));
        return "Fake msg returned by ZSOpenContainer";
    }
}
