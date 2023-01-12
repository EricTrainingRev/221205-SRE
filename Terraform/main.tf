# the name provided in the provider section (see the word in quotation marks) needs to match the name of the required
# provider in our terraform.tf file, where we defined our required_provder
provider "aws" {
  region     = "us-east-1" # this tells terraform where we want our resources created
  access_key = var.access_key # this is the access key variable
  secret_key = var.secret_key # this is the secret key variable

  # both keys should be provided by an IAM role created in aws
}

module "ec2-instance" {
  source  = "terraform-aws-modules/ec2-instance/aws"
  version = "~> 3.0"

  name = "terraform-demo-instance"

  ami                    = "ami-0b5eea76982371e91"
  instance_type          = "t2.micro"
  key_name               = "showing-off-keys"
  monitoring             = false
  vpc_security_group_ids = ["sg-0725af0b3384cc1d0"] # you can place multiple security groups in your list

  tags = {
    Terraform   = "true"
    Environment = "dev"
  }
}