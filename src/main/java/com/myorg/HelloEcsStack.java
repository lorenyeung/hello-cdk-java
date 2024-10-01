package com.myorg;

import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.CfnOutput;

import software.amazon.awscdk.App;

import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecs.Cluster;

import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.amazon.awscdk.services.ec2.VpcLookupOptions;
import software.amazon.awscdk.services.ec2.IVpc;

public class HelloEcsStack extends Stack {
    public HelloEcsStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public HelloEcsStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // Create a VPC for the ECS cluster
        // Vpc vpc = Vpc.Builder.create(this, "MyVpc")
        //         .maxAzs(2) // Default is all AZs in the region
        //         .build();

        
        

        IVpc existingVpc = Vpc.fromLookup(this, "ExistingVpc", VpcLookupOptions.builder()
        .region(System.getenv("CDK_TARGET_REGION"))
        .vpcId(System.getenv("CDK_TARGET_VPC")) // Replace with your actual VPC ID
        .build());

        // Create an ECS cluster using Fargate
        Cluster cluster = Cluster.Builder.create(this, "MyFargateClusterTwo")
                .vpc(existingVpc)
                .clusterName("MyFargateClusterTwo")
                .enableFargateCapacityProviders(true)
                .build();
        
        // this bit should be defined in harness
        // Create a Fargate task definition
        // FargateTaskDefinition taskDefinition = FargateTaskDefinition.Builder.create(this, "MyTaskDefinition")
        //         .memoryLimitMiB(512)
        //         .cpu(256)
        //         .build();

        // // Define a container in the task definition
        // taskDefinition.addContainer("MyContainer", software.amazon.awscdk.services.ecs.ContainerDefinitionOptions.builder()
        //         .image(ContainerImage.fromRegistry("amazon/amazon-ecs-sample"))
        //         .portMappings(List.of(software.amazon.awscdk.services.ecs.PortMapping.builder()
        //                 .containerPort(80)
        //                 .protocol(Protocol.TCP)
        //                 .build()))
        //         .build());

        // // Create the Application Load Balanced Fargate Service
        // ApplicationLoadBalancedFargateService service = ApplicationLoadBalancedFargateService.Builder.create(this, "MyFargateService")
        //         .cluster(cluster)  // Assign the service to the ECS cluster
        //         .taskDefinition(taskDefinition)  // Use the task definition created
        //         .desiredCount(2)  // Number of service tasks desired
        //         .publicLoadBalancer(true)  // The load balancer will be accessible from the internet
        //         .build();

        // Output the Cluster Name

        CfnOutput.Builder.create(this, "ClusterNameOutput")
        .value(cluster.getClusterName())
        .description("ECS Cluster Name")
        .build();
        
        // Output the AWS Region
        CfnOutput.Builder.create(this, "RegionOutput")
        .value(this.getRegion())
        .description("AWS Region")
        .build();
    }
}
