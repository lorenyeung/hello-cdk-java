package com.myorg;

import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;

import software.amazon.awscdk.Environment;
import software.amazon.awscdk.Stack;

import software.constructs.Construct;

public class HelloCdkApp {
    public static void main(final String[] args) {
        App app = new App();

        // String stack1Name = (String)app.getNode().tryGetContext("stack1_name");
        // new HelloCdkStack(app, stack1Name, StackProps.builder()
        //         // If you don't specify 'env', this stack will be environment-agnostic.
        //         // Account/Region-dependent features and context lookups will not work,
        //         // but a single synthesized template can be deployed anywhere.

        //         // Uncomment the next block to specialize this stack for the AWS Account
        //         // and Region that are implied by the current CLI configuration.
        //         /*
        //         .env(Environment.builder()
        //                 .account(System.getenv("CDK_DEFAULT_ACCOUNT"))
        //                 .region(System.getenv("CDK_DEFAULT_REGION"))
        //                 .build())
        //         */

        //         // Uncomment the next block if you know exactly what Account and Region you
        //         // want to deploy the stack to.
        //         /*
        //         .env(Environment.builder()
        //                 .account("123456789012")
        //                 .region("us-east-1")
        //                 .build())
        //         */

        // For more information, see https://docs.aws.amazon.com/cdk/latest/guide/environments.html
        String stack2Name = (String)app.getNode().tryGetContext("stack2_name");
        new HelloCdkStack2(app, stack2Name, StackProps.builder()
            .env(Environment.builder()
                .account(System.getenv("CDK_DEFAULT_ACCOUNT"))
                .region(System.getenv("CDK_DEFAULT_REGION"))
            .build())
        .build());


        String stack3Name = (String)app.getNode().tryGetContext("stack3_name");
        new HelloEcsStack(app, stack3Name, StackProps.builder()
            .env(Environment.builder()
                .account(System.getenv("CDK_TARGET_ACCOUNT"))
                .region(System.getenv("CDK_TARGET_REGION"))
            .build())
        .build());        
        app.synth();
    }
}

