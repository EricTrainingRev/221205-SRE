# Unix Study Guide

## Common Commands
for space seperated names either wrap in single/double quotes or use an escape character with the white space. Also, all files in unix are just files: the extensions are not considered (even directories are files)
### ls (list files/directories)
```cli
ls [options] [location]
```

### cd (change directory)
```cli
cd {location}
```

### pwd (print working directory)
```cli
pwd
```

### man (manual)
"q" will exit the manual
```cli
man {command}
```

### mkdir(make directory)
```cli
mkdir [options] {directory name}
```

### rmdir (remove directory)
this command will fail if there is content in the folder. Add the -r flag if you want to delete the directory and sub files (please don't delete necessary files with this command: -ri is a safer option, because you will be prompted to confirm deleting every file/folder). NOTE: THERE IS NO UNDO OPTION, DELETE FILES AT YOUR PERIL
```cli
rmdir [options] {directory name}
```

### touch (create file)
this command does more than just create files, but it can be used to do so
```cli
touch [options] {file name}
```

### cp (copy)
```cli
cp [option] {source path} {destination path}
```

### mv (move)
note that moving the file to the same location with a new name will just rename the file
```cli
mv [option] {starting file} {new file}
```

### rm (remove)
```cli
rm [options] <file>
```

### cat (concatenate)
```cli
cat <file>
```

### grep (tool for searching for patterns in lines of one or more files)
```grep
grep [option] {pattern} {file/s}
```

### cut
useful if you've made columns in your file: can indicate which field (column) you want shown and the character/s used to seperate the columns. There are other options with this command
```cli
cut -f {column number, seperate multiple via comma} -d [seperator, tab is default] {path/to/file or content}
```

## Redirecting

### > (save to file/overwrite)
the greater-than operator will redirect the output of an operation to a file with a name you provide. If the file already exists the content of the file will be replaced with the new content
```cli
{command/s} > {output file}
```

### >> (redirect to file)
double greater than operators will append the content to a file if it exists instead of saving over the previous content
```cli
{command/s} >> {output file}
```

### | (piping)
"|" is used to chain operations together and send the results from one operation to another
```cli
echo "1 2 3 4 5" | cut -f 1 -d " " # will return 1
```

## OS commands

### top
this commmand returns an overview of systme resources being used and processes running, and will look something like the example below. Keys to know:
- Tasks: the amount of processes running
- Cpu(/s): cpu usage
- MiB Mem & Swap: Ram and virtual memory usage
- PID: process identifier. Useful for ending processes
- User: tells us who owns the process
```cli
top - 10:44:41 up 12 min,  0 users,  load average: 0.52, 0.58, 0.59
Tasks:   4 total,   1 running,   3 sleeping,   0 stopped,   0 zombie
%Cpu(s):  5.5 us,  3.3 sy,  0.0 ni, 89.6 id,  0.0 wa,  1.6 hi,  0.0 si,  0.0 st
MiB Mem :  16232.1 total,   9072.9 free,   6935.2 used,    224.0 buff/cache
MiB Swap:  30365.0 total,  30326.6 free,     38.4 used.   9166.2 avail Mem

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND
    1 root      20   0    8948    400    328 S   0.0   0.0   0:00.07 init
    8 root      20   0    9304    236    180 S   0.0   0.0   0:00.00 init
    9 revature  20   0   17320   4108   4004 S   0.0   0.0   0:00.29 bash
  124 revature  20   0   18820   2248   1588 R   0.0   0.0   0:00.10 top
```

### ps
this command will show the current working processes. Alone it doesn't provide much detail
```cli
ps
  PID TTY          TIME CMD
    9 tty1     00:00:00 bash
  144 tty1     00:00:00 ps
```
you can add -aux to the command then we will get far more detail. A typical practice is to pipe the grep command to find any processes that are causing system issues.
```cli
ps -aux
USER       PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND
root         1  0.0  0.0   8948   400 ?        Ssl  10:32   0:00 /init
root         8  0.0  0.0   9304   236 tty1     Ss   10:32   0:00 /init
revature     9  0.0  0.0  17320  4108 tty1     S    10:32   0:00 -bash
revature   143  0.0  0.0  18808  2116 tty1     R    10:49   0:00 ps -aux
```

### kill
A simple way to kill a process is to use the kill command and provide the PID number of the process. By default the command sends the signal "1", you can think of this as a "polite" way of requesting the process end
```cli
kill {PID}
```
This does not always work: when this process fails you can send signal "9" with the command: this is the force quit command, and you should always try the default kill command before this one
```cli
kill -9 {PID}
```
Keep in mind regular users can only kill processes they own: the root user may kill all processes

### jobs
returns all working processes, both foreground and background.

### & (move to background)
adding "&" to a command moves it to the background, which allows you to continue working in the terminal you are in. This is useful if starting an app, running a command that will take a long time to process, or if the operation is intended to work in the background. You can also manually move a process to the background by pressing CTR + Z, note this will also stop the job

### fg (foreground)
this command will move a process from the background to the foreground: Note that this command takes the process id found from the "jobs" command instead of a PID
```cli
fg {ID from jobs}
```

# Variables
variables are set by giving a name, equal sign (no space between name and =), then the value of the variable (no space between = and value)
```cli
variable-name=variable-value
```
to reference a variable name you use a $
```cli
echo $variable-name
```