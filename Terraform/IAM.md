# IAM
You will need to create an IAM (Identity and Access Management) role that will allow your terraform scripts to actully make changes to your AWS resources

1. You will need to create an IAM group that will have the needed policies assigned to it so terraform can interact with the necessary resources

2. You will need to assign the necessary policies to the group you created

3. You will need to create a user who is assigned to your created group

4. You will use the access and secret keys created for the user who is assigned to your group you have assigned the necessary polices to to give your terraform scripts the necessary permissions to interact with AWS resources for you.