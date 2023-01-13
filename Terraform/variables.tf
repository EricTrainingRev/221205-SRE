variable "access_key" {}

variable "secret_key" {}

# this tells terraform that the script will use two variables, one called acces_key, the other secret_key
# however, because we did not define their values here, terraform needs to look in another file for their actual values
# this will be found inside a .tfvars file

# tfvars files should look something like this:
# access_key = "tbd"
# secret_key = "tbd"