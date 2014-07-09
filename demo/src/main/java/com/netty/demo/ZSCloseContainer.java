package com.netty.demo;
public class ZSCloseContainer extends JTFCommand
{
    public String execute() {
        System.out.println("Call ZSCloseContainer");
        System.out.println("cguid = " + this.args.getProperty("cguid"));
        return "Fake msg returned by ZSCloseContainer";
    }
}
