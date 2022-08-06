//配置webpack(在运行webpack之前会访问配置文件)
const path = require('path')

//1.导入html-webpack-plugin 这个插件,得到插件的构造函数
const HtmlPlugin = require('html-webpack-plugin')
//2.new构造函数,创建插件的实例对象
const htmlPlugin = new HtmlPlugin({
    //指定要复制哪个页面
    template:'./src/index.html',
    // 指定复制出来的文件名和存放路径
    filename:'./index.html'
})
//webpack中的默认约定
// 1.默认的打包入口文件为src->index.js
// 2.默认输出的文件路径为dist->main.js

// 注意：左侧的 { } 是解构赋值
const { CleanWebpackPlugin } = require('clean-webpack-plugin')


//使用node.js中的导出语法,导出一个webpack的配置对象
module.exports = {
    //代表webpack运行的模式,可选值有两个 development 和 production
    //将mode的模式改为production之后,运行webpack会自动压缩main.js文件
    //在开发过程中建议使用development,打包速度块
    //在产品上线时,使用production,文件体积小
    mode: 'development',
    //devtool精准定位代码错误位置
    //在开发调试阶段,建议大家把devtool改为eval-source-map
    devtool:'eval-source-map',
    //在实际发布的时候,建议将devtool改为nosources-source-map,或者关闭,避免源代码泄露
    // devtool:'nosources-source-map',
    // entry:'指定要处理哪个文件(默认index.js)'
    entry:path.join(__dirname,'./src/index1.js'),
    //output:指定生成的文件要存放在哪(默认是main.js)
    output:{
        //存放的目录
        path:path.join(__dirname,'dist'),
        //生成的文件名
        filename:'js/bundle.js'
    },
    //插件的数组,将来 webpack 在运行时,会加载这些插件
    plugins:[htmlPlugin, new CleanWebpackPlugin()],
    devServer:{
        //首次打包成功后,自动打开浏览器
        open:true,
        // 在http协议中,如果端口号是80会自动省略掉
        port:80,
        //指定运行的主机地址
        host:'127.0.0.1'
    },
    module:{//处理第三方文件模块的匹配规则
        rules:[
            //定义了不同模块对应的loader
            //1.处理.css结尾的文件
            {test:/\.css$/,use:['style-loader', 'css-loader']},
            //loader的中的use执行顺序是从右往左执行的，所以需要注意loader的次序
            //首先,把index.css这个文件交给css-loader进行处理
            //其次,当css-loader处理完之后,会把处理完的结果交给style-css
            //当style-loader处理完毕之后,发现没有loader了，于是把处理的结果,转交给了webpack
            
            //处理.less结尾的文件
            {test:/\.less$/,use:['style-loader', 'css-loader', 'less-loader']},

            //处理图片文件
            {test:/\.jpg|png|gif$/, use:'url-loader?limit=370&outputPath=images'},
            //?limit=告诉webpack多大的图片文件需要转换为base64,多个参数之间用&来分隔

            //使用babel-loader处理高级js语法
            //exclude是排除哪一些文件
            //在配置babel-loader 的时候,一定要排除node_modules目录中的js文件,因为程序员只需要关系自己的代码转换即可
            {test:/\.js$/, use:'babel-loader', exclude:'/node_modules/'},
            
        ]

    },

    resolve: {
        alias: {
          // 告诉 webpack，程序员写的代码中，@ 符号表示 src 这一层目录
          '@': path.join(__dirname, './src/')
        }
      }
}