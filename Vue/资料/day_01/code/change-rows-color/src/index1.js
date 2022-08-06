// 1.使用ES6导入语法(这种高级语法有一些浏览器无法识别这种语法),需要使用webpack进行降级处理,让浏览器识别语法
import $ from 'jquery'

//导入样式,在webpack中一切皆是模块,都可以通过ES6导入语法进行导入和使用
//当webpack发现了处理不了的文件的时候,会去webpack.config.js中的module.rules中寻找是否配置了该文件对应的loader
//导入.css文件后,需要配置loader,因为webpack默认只能处理.js结尾的文件,让webpack知道还需要哪些文件
//如果某个模块中,使用from接受到的成员为undefined,则没必要接受
import '@/css/index.css'
import '@/css/index.less'

//导入src/js/test/info.js
import '@/js/test/info.js'

//1.导入图片,得到图片文件
import logo from '@images/logo.jpg'
//2.给img标签的src动态赋值
$('.box').attr('src',logo)

//2.定义jquery的入口函数
$(function(){
    //实现奇偶行的变色
    $('li:odd').css('background-color','red')
    $('li:even').css('background-color','pink')
})

//定义装饰器函数
function info(target) {
    target.info = 'person info.'
}


//定义一个普通的类
@info
class person {}

console.log(person.info);
