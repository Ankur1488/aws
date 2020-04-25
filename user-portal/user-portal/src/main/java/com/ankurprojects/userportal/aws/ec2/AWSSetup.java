package com.ankurprojects.userportal.aws.ec2;

import com.amazonaws.services.dynamodbv2.xspec.S;

public class AWSSetup {
    public static void main(String[] args) {

        String keyName = "hamster_key";
        String securityGroupName = "hamster_sg";

        EC2Instance ec2Instance = new EC2Instance("hamster_ec2","ami-0470e33cd681b2476");
        ec2Instance.createEC2Instance(keyName, securityGroupName);

        System.out.println("Created EC2 isntance");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
