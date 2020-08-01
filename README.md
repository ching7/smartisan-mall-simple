# smartisan-mall-simple

#### 介绍
锤子科技官网简单demo实现。前台原模板来自 [[vue-mall](https://github.com/yucccc/vue-mall)] 原前台工程。

后台原模板使用的是mongdb实现。本项目使用springboot进行了重写，原有功能基本全部实现。

#### 层级目录

~~~properties
smartisan-mall-simple
├── DevCodes		//springboot后台实现
|  └── smartisan-mall-simple-dev
├── Sql				//数据库sql文件
|  └── mall_template.sql
└── WebCodes         //vue前台
   └── smartisan-mall-simple-web
~~~

#### 技术架构

* 前台

  vue，详情见 [[vue-mall](https://github.com/yucccc/vue-mall)]。原前台工程

* 后台

  springboot、mybatis-plus、renren逆向

#### 使用说明

**环境准备**

1. node.js
2. jdk8及以上
3. mysql

**启动**

1. clone或者download仓库到本地目录

2. 启动前台

   准备node.js环境后台

   默认前台端口为9999，后台代理端口为3333

   * 进入WebCodes/smartisan-mall-simple-web
   * npm install -U / npm run dev

3. 启动后台

   准备jdk环境，mysql环境。修改 application.yml 对应的数据库连接。