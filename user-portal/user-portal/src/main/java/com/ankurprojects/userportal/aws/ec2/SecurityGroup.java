package com.ankurprojects.userportal.aws.ec2;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.*;

public class SecurityGroup {
    private String group_name;
    private String group_desc;
    private String vpc_id;

    public SecurityGroup(String group_name, String group_desc, String vpc_id) {
        this.group_name = group_name;
        this.group_desc = group_desc;
        this.vpc_id = vpc_id;
    }

    public SecurityGroup(String group_name, String group_desc) {
        this.group_name = group_name;
        this.group_desc = group_desc;
    }

    public String createSecurityGroup(final AmazonEC2 ec2){
        CreateSecurityGroupRequest create_request = new
                CreateSecurityGroupRequest()
                .withGroupName(group_name)
                .withDescription(group_desc);
//                .withVpcId(vpc_id);

//        CreateSecurityGroupResult create_response =
//                ec2.createSecurityGroup(create_request);
//
//        System.out.printf(
//                "Successfully created security group named %s",
//                group_name);

        addIngressPolicy(ec2);

        return group_name;
    }

    private void addIngressPolicy(AmazonEC2 ec2) {
        IpRange ip_range = new IpRange()
                .withCidrIp("0.0.0.0/0");

        IpPermission ip_perm = new IpPermission()
                .withIpProtocol("tcp")
                .withToPort(3000)
                .withFromPort(3000)
                .withIpv4Ranges(ip_range);

        IpPermission ip_perm2 = new IpPermission()
                .withIpProtocol("tcp")
                .withToPort(22)
                .withFromPort(22)
                .withIpv4Ranges(ip_range);

        IpPermission ip_perm3 = new IpPermission()
                .withIpProtocol("tcp")
                .withToPort(8080)
                .withFromPort(8080)
                .withIpv4Ranges(ip_range);

        AuthorizeSecurityGroupIngressRequest auth_request = new
                AuthorizeSecurityGroupIngressRequest()
                .withGroupName(group_name)
                .withIpPermissions(ip_perm, ip_perm2, ip_perm3);

        AuthorizeSecurityGroupIngressResult auth_response =
                ec2.authorizeSecurityGroupIngress(auth_request);

        System.out.printf(
                "Successfully added ingress policy to security group %s",
                group_name);
    }
}
