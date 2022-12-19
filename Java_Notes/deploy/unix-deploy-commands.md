# Deploy commands

## getting the software on your instance
```cli
sudo yum install git # this installs git on your instance

sudo amazon-linux-extras install java-openjdk11 # this installs java 11

sudo yum install maven # this installs maven (might not be up to date)
```

## clone the repo of source code to your instance
```cli
git clone {repo link}
```

## use maven to build your app
```cli
mvn package # build plugin versions might need to be adjusted for this to work
```

## run your app
```cli
java -jar target/{jar file} # this runs the app directly in your terminal, prevents you from interacting further

nohup java -jar target/{jar file} & # this runs the app in the background and redirects all std output to a file  called nohup.out in the same directory you exceuted the command in
```

## move foreground app into background
```cli
ctr + z # this will stop the process and move it into the background
```

## move background process into forground
```cli
jobs # this will give you the job number of the background process

fg %{job number} # this will move the job from the background into the forground
```

## killing a process
```cli
ps # returns all current processes and their PID numbers

kill {pid of job to kill} # this tells unix to try and stop the process

kill -9 {pid of job to kill} # this tells unix to force the process to end. Can cause issues if there are supposed to be shutdown operations
```