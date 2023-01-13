# Terraform
With docker we learned how to continerize our applications and deploy one or more linked containers with a docker compose file. Then, with Kubernetes, we learned how we could use manifest files to update our cluster, and then with Jenkins we learned how to use a Jenkinsfile to facilitate our DevOps pipeline. The only resources we have yet to create any configurations for now are the cloud provided tools we use (ec2/rds instances, vpcs, etc). Terraform gives us the option to implement Infrastructure as Code, similar to Configuration as Code in that we create configurations that, in this case, control our cloud infrastructure that are tracked alongside our source code.

Similar to Kubernetes, Terraform keeps track of the desired state of your cloud infrastructure (as determined by your terraform files) and when changes are detected it adjusts the current state to match the desired state. This is handled Terraform updating a "terraform.tfstate" file, which can be saved locally or in a remote location (like an S3 bucket).

## Terraform Configuration
Terraform requires three configuration sections to correctly set the state of your infrastructure: a terraform, provider, and resource configuration.

### Terraform
The terraform section of your configuration is where you tell Terraform what "required providers" are necessary for your resources to be managed, and you can also indicate where the "backend" is located. A backend is where you can tell Terraform to store state information remotely (useful if working with a team).
```
terraform {
    required_providers {
        provider = {
            source = "some source from terraform registry"
            version = "0.0.0"
        }
    }

    backend "backend-name" {
        properties = "values"
    }
}
```

### Provider
The provider section tells Terraform what credentials and settings to use when interacting with resources of the required providers. You can set information in this section about credentials, regions for resources to be deployed in, and more

```
provider "provider-name" {
    credentials = "creds"
    region = "preferred-region"
}
```

### Resource
The resource sections in terraform files comprise the majority of the information Terraform needs to work. These sections are where you tell Terraform what resources you want to work with and how you want to configure your cloud resources. When working directly with resources provided by your choosen cloud provider you can use the resource block, indicate what resource you are working with, and give the resource a reference name in your configuration
```
resource "google_compute_network" "vpc_network" {
    name = "terraform-network"
}
```

That being said, working directly with cloud resources can be time consuming, and if you don't get all the configurations right then terraform may constantly fail to set the desired state of your inffrastructure. You can avoid this problem by making use of "modules". These are collections of resources pre-configured for you to use: instead of manually setting all the details you can pick and choose the specific ones you need to adjust, and let the module provide default values for the rest

```
// this module lets us set the required values to make the ec2 instance free tier eligible without us having to
// manually determine things like the vpc network/subnets to assign the instance
module "ec2-instance" {
  source  = "terraform-aws-modules/ec2-instance/aws"
  version = "4.3.0"

  name = "Terraform-Instance"

  ami                    = "ami-0b5eea76982371e91" # ami we used for ec2 demo
  instance_type          = "t2.micro"
  key_name               = "some-key"
  vpc_security_group_ids = ["security-group-id"]

  tags = {
    Terraform   = "true"
    Environment = "dev"
  }
}
```

## Terraform Commands
```cli
terraform show # desplays the state of the resources managed by terraform

terraform init # downloads provider data into cwd, needed for the apply command to work

terraform plan # will print out the execution plan for the module in the CWD

terraform apply # terraform will show an execution plan and ask confirmation to execute commands to create your given infrastructure desires

terraform destroy # terraform terminates all resources assocaited with the current project
```
