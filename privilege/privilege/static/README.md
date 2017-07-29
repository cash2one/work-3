# antd-demo

## Environment

```
node >= 4
npm >= 3
```

## Code Style

https://github.com/airbnb/javascript

## Develop

```
npm start
```

访问 http://127.0.0.1:8989

## Build

```
npm run build
```

## 0.环境准备
* 只有前端同学需要
  * npm@3.x
  * node@4.x

## 1.初始化
* git clone https://git.cloud.alipay.com/etrust/etrust.git 拉取代码
* cd etrust 进入目录
* git checkout dev 切换到dev分支，后续前端统一在dev分支上开发
* git pull origin dev 从远程拉取最新的dev分支代码，并合并到本地
* cd app/static 进入static目录
* npm install 安装依赖
* npm start 启动服务
* 访问 127.0.0.1:8989 进行开发

## 2.工程目录
````bash
- app/static //前端开发目录
  - src //源文件
    - components //UI组件存放的地方
      - common //公共组件，页头，页尾，导航等
    - entries //入口脚本存放的地方，每个入口脚本一个单页SPA应用，在入口文件里直接定义组件的路由信息
    - layouts //布局组件存放的地方，基本不需要动
    - services //定义数据服务
      - api.js //定义调用接口的方法  
    - mock //模拟数据接口的地方

- app/web/src/main/webapp/static 构建目标目录
  静态资源构建的目标位置，最终就是在这个目录里被应用一起部署，并提供线上访问，这里都是编译和打包之后的文件。
````

## 3.开发
* 前端和开发同学先约定好接口，如：
  ````
  GET /webapi/users
  params: page=1,pageSize=5
  return: {
    success: true[false],
    data:[{
      name:'xxx',
      pwd:'xxx',
    }]
  }
  ````
* 前端：开发阶段可以通过mock的方式，自己用假数据开发，
* 后端：直接根据接口约定提供数据


## 4.联调测试
* 前端同学
  1.前端通过mock数据将功能开发完成
  2.在static目录下执行 npm run build，然后将构建后的文件一并commit到dev分支，并push到远程仓库

* 开发同学
  1.本地下载dev分支，直接访问http://127.0.0.1:8080/static/index.html，无需安装nodejs

* 测试
  1.将dev分支部署到测试服务器进行测试
 

## 5.git操作

1.提交到本地
  * git status 查看当前本地代码的修改记录状态
  * git add -A (如果有新增文件，需要执行此命令)
  * git commit -am "commit message" （把代码提交到本地仓库）

2.合并远程仓库到本地
  * git pull origin dev （将远程仓库的代码拉取下来并与本地代码合并，根据CONFLICT提示解决冲突文件，需要打开编辑器，将冲突的代码手动合并，修改好，确保编译不出错，并且页面功能正常，**注意** 如果conflict的是构建后的压缩文件，可以直接在static下执行npm run build来覆盖冲突文件，确认无错误后执行下面两条命令）
  * git add -A
  * git commit -am "merge message"

3.提交远程仓库
  * git push origin dev （如果失败，继续从第2步再走一遍）


