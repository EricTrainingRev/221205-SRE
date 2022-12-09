# Git
Git is a version control software we will be using to track changes to our work and to provide rollback points in case we make catistrophic mistakes. Github is web server that uses Git to provide the same service but is hosted in the gloud. You can link your local git repository with Github repository to save changes to your work to the cloud (aka github's computers). You need to make sure you are familiar with the clone, add, commit, push, and pull commands.

# clone
You can use the clone commmand to save a github repository on your computer. You can find the link for cloning a repository on github by clicking the green "code" button and copying the HTTPS link.
```cli
git clone {git clone link}
```

# add
You use the add command to move files into staging: this tells git that the files are ready to be tracked
```cli
git add {file or directory to move to staging} {second file or directory to move to staging} {etc}

git add . # the dot operator tells git to move all eligble files to staging
```

# commit
the commit command tells Git to track the staged files and directories: once tracked, the files/directories can be rolled back to in case something goes wrong. When you commit files you need to make sure you add a message with the commit
```cli
git commit -m "your message goes in quotes"
```

# push
If your local git repository is connect to github you can use the push command to send your local changes to the github repo. In order to make sure you have the proper permission to do so you need to use the config command to set your email and username. If you are pushing to a github hosted repo for the first time you will need to indicate that the Github repo is the "remote" repository for your local repo.
```cli
git config --global user.name username
git config --global user.email email@provider.com


git push -u origin main # assuming your branch is called main, do this for the first push


git push # after the first push to the remote branch normal push commands will work
```
Note that, if no remote origin is found, you will have to specify it
```cli
git remote add origin {same https link you would use to clone repo}
```

# pull
If the github repository has any code/changes that are not reflected in your local repo you can use the pull command to update your local repo (this will be more useful when we start working with branches)
```cli
git pull
```