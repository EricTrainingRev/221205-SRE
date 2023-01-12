# the name provided in the provider section (see the word in quotation marks) needs to match the name of the required
# provider in our terraform.tf file, where we defined our required_provder
provider "aws" {
  region     = "us-east-1" # this tells terraform where we want our resources created
  access_key = "my-access-key" # this is the access key
  secret_key = "my-secret-key" # this is the secret key

  # both keys should be provided by an IAM role created in aws
}