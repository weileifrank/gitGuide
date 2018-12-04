### 安装git(Windows平台)

- git下载地址 https://git-scm.com/downloads
- 右键Git  Bash通过git命令查看是否成功
- 右键Git  Bash通过git version查看版本
```$xslt
 git version 
 
 git version 2.19.2.windows.1
```
### 自我介绍给git
每次 Git 提交时都会引用这两条信息，说明是谁提交了更新

```
git config --global user.name "frank
git config --global user.email "1138289316@qq.com"
```

| **   | 如果您希望能够为不同的项目使用不同的用户名和电子邮件，请在没有*--global*选项的情况下从项目目录运行上述命令。 |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

- 查看用户信息`git config --list`
```$xslt
$ git config --list
core.symlinks=false
core.autocrlf=true
core.fscache=true
color.diff=auto
color.status=auto
color.branch=auto
color.interactive=true
help.format=html
rebase.autosquash=true
http.sslcainfo=C:/Program Files/Git/mingw64/ssl/certs/ca-bundle.crt
http.sslbackend=openssl
diff.astextplain.textconv=astextplain
filter.lfs.clean=git-lfs clean -- %f
filter.lfs.smudge=git-lfs smudge -- %f
filter.lfs.process=git-lfs filter-process
filter.lfs.required=true
credential.helper=manager
user.email=1138289316@qq.com                     
user.name=frank
core.repositoryformatversion=0
core.filemode=false
core.bare=false
core.logallrefupdates=true
core.symlinks=false
core.ignorecase=true

```
### 创建版本库repository

版本库:可以简单理解成一个目录,这个目录里的所有文件都可以被git管理起来,每个文件的修改、删除，Git都能跟踪，以便任何时刻都可以追踪历史，或者在将来某个时刻可以“还原”。

- 首先创建一个项目的目录

- 目录下执行git init

```$xslt
$ git init
 Initialized empty Git repository in D:/GoProjects/src/gitdemo/.git/
```
 
  通过`git init`命令把这个目录变成Git可以管理的仓库：

  目录下多了一个`.git`的目录，这个目录是Git来跟踪管理版本库的

  如果没有看到`.git`目录，因为这个目录默认是隐藏的，用`ls -ah`命令即可看见
### 忽略文件

编译的中间文件不需要上传到git,就可以通过忽略文件来实现

- 工程目录下执行`vi .gitignore`生成忽略文件

- 在文件中定义忽略的内容,写法如下

  https://github.com/github/gitignore

- 忽略的标准

1. 忽略操作系统自动生成的文件，比如缩略图等；
2. 忽略编译生成的中间文件、可执行文件等，也就是如果一个文件是通过另一个文件自动生成的，那自动生成的文件就没必要放进版本库；
3. 忽略你自己的带有敏感信息的配置文件，比如存放口令的配置文件

### 状态命令

- git  status

  查看自从我上次向Git存储库提交更改以来修改了哪些文件

  ![](imgs/1.png)

  红色的文件是未跟踪的文件

注意:

> 版本控制系统只能跟踪文本文件的改动
>
> 二进制文件没法跟踪文件的变化(只知道改变了大小,不知道具体改了什么)

### 添加命令(文件内容到索引)添加到购物车

- 使用`git add`

  ```
  $ git add main.go
  ```

  ​

- 一次添加多个`git add --all` 或者`git add .`

  ```
  $ git add --all
  ```



添加完之后,可以再次执行`git status`命令查看状态

![](imgs/2.png)

### 提交命令(购物车统一结账)

- `git commit -m 'master-第一次初始化代码`

  注:`-m`后面的是这一次的提交说明

  ![](imgs/3.png)

### 提交记录查看

- `git log`查看完整提交记录

  ![](imgs/4.png)

- `git log -n`查看最近n次提交记录

  ![](imgs/5.png)

  注:commit后面的一串字符是`commit id`版本号,SHA1计算 ​
  
  ### 版本回退
  
  - 当前版本往上回退版本`git reset --hard HEAD^`
  ![](imgs/6.png)
  
  - 往上回退n个版本`git reset --hard HEAD~n`
  ![](imgs/7.png)
  
  - 回退到某一个版本:`git reset  --hard commit id`
  ![](imgs/8.png)
  
  - 如果回退之后后悔,想恢复到最新版本
  
    - 通过`git reflog`查看每一次记录
    - 回退到指定的`commit id`
  ![](imgs/9.png)
  
## 创建与删除分支

- 查看当前分支`git branch`

  ```
  $ git branch
  * master
  ```

- 创建并切换分支`git checkout -b dev `

  ```
  $ git checkout -b dev
  Switched to a new branch 'dev'
  ```

  这个相当于两个命令

  `git branch dev`创建dev分支

  `git checkout dev`切换到dev分支

- 删除分支`git branch -d dev`

  ```
  $ git branch -d dev
  Deleted branch dev (was 80b3178).
  ```

  ​