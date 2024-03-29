### 安装git(Windows平台)

- git下载地址 https://git-scm.com/downloads
- 右键Git  Bash通过git命令查看是否成功
- 右键Git  Bash通过git version查看版本
```$xslt
 git version 
 
 git version 2.19.2.windows.1
```
### 自我介绍给git
- 每次 Git 提交时都会引用这两条信息，说明是谁提交了更新

```
git config --global user.name "frank"
git config --global user.email "1138289316@qq.com"
```

- 如果您希望能够为不同的项目使用不同的用户名和电子邮件，请在没有*--global*选项的情况下从项目目录运行上述命令。

  ```
  //cd 到某个项目的根目录下
  
  git config  user.name "frank"
  git config  user.email "1138289316@qq.com"
  ```

- 查看git全局配置信息
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
  
- `git log --pretty=oneline` 单行查看历史提交记录

- git log --graph 查看各个分支的历史提交曲线

- git reflog master 查看master分支的提交历史
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

### 创建与删除分支

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

  `git checkout v1.20'`切换到v1.20对应的记录

- 拉取远程指定分支

  ```shell
  git checkout -b develop origin/develop
  ```

  

- 创建本地分支

  ```shell
  创建本地分支：git checkout -b LocalDev origin/dev (LocalDev 为本地分支名，dev为远程分支名)
  创建本地分支：git checkout -b LocalDev1 dev 基于本地的dev分支创建localdev1,并切换过去
  ```

- 修改分支

  ```shell
  修改本地分支名称:git branch -m branch-A branch-B
  删除远程分支:git push origin :branch-A
  将本地分支与远程新分支关联起来:
  git push --set-upstream origin branch-B
  
  ```

  

- 删除分支`git branch -d dev`

  ```
  $ git branch -d dev
  Deleted branch dev (was 80b3178).
  ```
  
- 删除远程分支`git push origin --delete 远程分支名称`

  ```shell
  git push origin --delete dev
  ```

  

### 合并分支

把dev开发的代码合并到master主分支上

- master主分支执行`git merge dev`

  dev分支增加了person.go

  ![](imgs/10.png)

  没有冲突是Fast-forward快进模式
  ​
### 解决冲突

  首先dev分支提交了对main.go的修改,修改内容如下

  ```
  func main() {
  	fmt.Println("hello git")
  	str:="这是dev分支的字符串"
  	fmt.Printf("%s\n",str)
  }
  ```

  并把修改commit  

  master分支开发中也对main.go进行了修改,修改如下

  ```
  func main() {
  	fmt.Println("hello git")
  	str:="这是master分支的字符串"
  	fmt.Printf("%s\n",str)
  }
  ```

  想要把dev分支合并到主分支master,就会出问题

  ![](imgs/11.png)

  这里就会有冲突 ,可以通过<font color='#ff0000'>`git diff`</font>查看哪个文件处理了冲突

  代码中也会出现提示

  ![](imgs/12.png)

  HEAD代表当前分支版本,dev代表dev分支
  解决方案就是修改冲突并再次提交
  ![](imgs/13.png)
  ![](imgs/14.png)

### 远程仓库github

[GitHub](https://github.com/)是一个基于Web的Git仓库托管服务，在这里您可以免费创建一个共享的代码仓库。希望在GitHub上创建私有存储库的人或组织可以购买付费帐户。

http/ssh

- 第1步：创建SSH Key。

  在用户主目录下，看看有没有.ssh目录，如果有，再看看这个目录下有没有`id_rsa`和`id_rsa.pub`这两个文件。如果没有,执行下面命令，创建SSH Key：

  ```
  $ ssh-keygen -t rsa -C "1138289316@qq.com" 
  ```

  建议使用真实的邮箱地址

  `id_rsa`是私钥，`id_rsa.pub`是公钥

- 第2步：登陆GitHub，打开“settings”，“SSH Keys”页面：

  然后，点“New SSH Key”，填上任意Title，在Key文本框里粘贴`id_rsa.pub`文件的内容：

![](imgs/15.png)
![](imgs/16.png)

### 创建远程仓库

- 首先，登陆GitHub，在右上角找到“newrepository”按钮，创建一个新的仓库

  ![](imgs/17.png)

- 在Repository name填入`gitGuide`仓库名，其他保持默认设置

![](imgs/18.png)

### 添加远程仓库

目前`gitGuide`仓库还是空的，我们可以从这个仓库克隆出新的仓库，也可以把本地仓库与之关联，然后把本地仓库的内容推送到GitHub仓库。

- 本地仓库关联远程仓库  `git remote add origin SSH`

  ```
  $ git remote add origin git@github.com:weileifrank/gitGuide.git
  ```
![](imgs/19.png)

### 推送到远程仓库

- 推送到远程仓库`git push -u origin 分支`

  ```
  $ git push -u origin master
  ```

  把当前分支`master`推送到远程`master`分支。`-u`会把本地`master`分支和远程`master`分支关联起来

  下一次再提交的话就可以简便

  ```
  $ git push origin master
  ```
  
  如果要推送其他分支，比如`dev`，就改成：
  
  ```
  $ git push origin dev
  
  ```
  
  如果 `git push -u origin master`出现如下结果,说明需要先拉取 'git pull ...'
```$xslt
$ git push -u origin master
To github.com:weileifrank/gitGuide.git
 ! [rejected]        master -> master (non-fast-forward)
error: failed to push some refs to 'git@github.com:weileifrank/gitGuide.git'
hint: Updates were rejected because the tip of your current branch is behind
hint: its remote counterpart. Integrate the remote changes (e.g.
hint: 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.

```
  拉取过程也可能出现异常,看下面的案例
  如果此时与远程仓库代码起点不同,扔不能提交,也不能拉去,需要设置 `git pull --allow-unrelated-histories`
```$xslt

w1138@DESKTOP-4ETLG7Q MINGW64 /d/GoProjects/src/gitdemo (master)
$ git pull origin master
From github.com:weileifrank/gitGuide
 * branch            master     -> FETCH_HEAD
fatal: refusing to merge unrelated histories

w1138@DESKTOP-4ETLG7Q MINGW64 /d/GoProjects/src/gitdemo (master)
$ git pull --allow-unrelated-histories
Auto-merging README.md
CONFLICT (add/add): Merge conflict in README.md
Automatic merge failed; fix conflicts and then commit the result.

```

此时代码有冲突,需要到对应的文件处理冲突并提交远程仓库

```$xslt
w1138@DESKTOP-4ETLG7Q MINGW64 /d/GoProjects/src/gitdemo (master|MERGING)
$ git status
On branch master
Your branch and 'origin/master' have diverged,
and have 13 and 1 different commits each, respectively.
  (use "git pull" to merge the remote branch into yours)

You have unmerged paths.
  (fix conflicts and run "git commit")
  (use "git merge --abort" to abort the merge)

Unmerged paths:
  (use "git add <file>..." to mark resolution)

        both added:      README.md

no changes added to commit (use "git add" and/or "git commit -a")


w1138@DESKTOP-4ETLG7Q MINGW64 /d/GoProjects/src/gitdemo (master|MERGING)
$ git add .


w1138@DESKTOP-4ETLG7Q MINGW64 /d/GoProjects/src/gitdemo (master|MERGING)
$ git commit -m '解决了与远程仓库的冲突'
[master 20bbadd] 解决了与远程仓库的冲突

w1138@DESKTOP-4ETLG7Q MINGW64 /d/GoProjects/src/gitdemo (master)
$ git status
On branch master
Your branch is ahead of 'origin/master' by 14 commits.
  (use "git push" to publish your local commits)

nothing to commit, working tree clean


w1138@DESKTOP-4ETLG7Q MINGW64 /d/GoProjects/src/gitdemo (master)
$ git push -u origin master
Enumerating objects: 73, done.
Counting objects: 100% (73/73), done.
Delta compression using up to 4 threads
Compressing objects: 100% (70/70), done.
Writing objects: 100% (71/71), 509.15 KiB | 1.67 MiB/s, done.
Total 71 (delta 22), reused 0 (delta 0)
remote: Resolving deltas: 100% (22/22), done.
To github.com:weileifrank/gitGuide.git
   648518a..20bbadd  master -> master
Branch 'master' set up to track remote branch 'master' from 'origin'.

```



### 查看当前关联的远程分支url

```
git remote -v
```

### 刷新本地分支列表

- 执行下面命令后如果ide还不线上,请重启ide

```shell
git remote update origin --prune
```



###  给版本打tag

- 格式 `-a`参数来创建一个带备注的tag,备注信息有`-m`指定

  ```shell
  git tag -a tagName -m "my tag"
  
  git tag versionName
  ```

- 示例:

  ```
  git tag -a v1.0.1 -m "优化了刷新token,别的请求会出现token失效的问题了"
  
  git tag v1.20
  ```

- 查看分支

  ```shell
  git tag
  ```
  
- 查看某个tag对应的commit版本号:`git show tag版本号`

  ```
  $ git show v1.0.2
  tag v1.0.2
  Tagger: frank <123>
  Date:   Mon Jan 6 19:52:20 2020 +0800
  
  2020/01/06 晚上发布的版本,修复了重置密码不生效的bug
  
  commit 1f7a3c582f08ac082566c6ea1e4d8dd9738dc374
  Author: frank <123>
  Date:   Mon Jan 6 19:34:04 2020 +0800
  
      修改配置文件spring.profiles.active=prod  这个是线上的
  
  diff --git a/src/main/resources/application.properties b/src/main/resources/application.properties
  index 257b306..a015c68 100644
  --- a/src/main/resources/application.properties
  +++ b/src/main/resources/application.properties
  @@ -1 +1 @@
  -spring.profiles.active=dev
  \ No newline at end of file
  +spring.profiles.active=prod
  \ No newline at end of file
  
  ```

- 把分支的tag推送到远程服务器上

  ```shell
  git push origin --tags
  ```

  

### 忽略某个文件

- 参考链接

  ```
  https://blog.csdn.net/qq_28285347/article/details/81011448
  ```

  



### 参考资料

```shell

https://www.cnblogs.com/best/p/7474442.html

https://gitee.com/all-about-git   //码云提供的git学习资料
```





