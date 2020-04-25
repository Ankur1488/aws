package com.ankurprojects.userportal.aws.ec2;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;

import java.io.PrintStream;

public class KeyPair {

    private String key_name;

    public KeyPair(String key_name) {
        this.key_name = key_name;
    }

    public String createKeyPair(AmazonEC2 ec2){
        CreateKeyPairRequest request = new CreateKeyPairRequest()
                .withKeyName(key_name);

        CreateKeyPairResult response = ec2.createKeyPair(request);

        System.out.printf(
                "Successfully created key pair named %s",
                key_name);

        return key_name;
    }
}
