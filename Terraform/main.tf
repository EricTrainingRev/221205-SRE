# the name provided in the provider section (see the word in quotation marks) needs to match the name of the required
# provider in our terraform.tf file, where we defined our required_provder
provider "aws" {
  region     = "us-east-1" # this tells terraform where we want our resources created
  # access_key = var.access_key # this is the access key variable
  # secret_key = var.secret_key # this is the secret key variable
  profile = "demo-user"

  # both keys should be provided by an IAM role created in aws
}

# module "ec2-instance" { # you can see more of this module's info in the .terraform/modules directory
#   source  = "terraform-aws-modules/ec2-instance/aws"
#   version = "~> 3.0"

#   name = "terraform-demo-instance"

#   ami                    = "ami-0b5eea76982371e91"
#   instance_type          = "t2.micro"
#   key_name               = "showing-off-keys"
#   monitoring             = false
#   vpc_security_group_ids = ["sg-0725af0b3384cc1d0"] # you can place multiple security groups in your list

#   tags = {
#     Terraform   = "true"
#     Environment = "dev"
#   }
# }

resource "aws_s3_bucket" "terraform_state" {
  # if you get a "wrong region" error there is a good chance it is because of your bucket name
  bucket = "revature-demo-state-bucket" # this bucket name has already been taken, leaving it for now to show error message
  lifecycle {
    prevent_destroy = true # this makes sure terraform does not destroy the bucket
  }
  versioning {
    enabled = true # this makes the bucket keep track of the various objects placed inside
  }
  server_side_encryption_configuration {
    rule {
      apply_server_side_encryption_by_default {
        sse_algorithm = "AES256"
      }
    }
  }
  
}