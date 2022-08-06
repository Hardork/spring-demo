// promise 是一个构造函数
const p = new Promise()
// new出来的promise实例对象,代表一个 异步操作
// premise.prototype上包含一个.then()方法
// .then()方法用于预先指定成功和失败的回调函数
// p.then(成功的回调函数,失败的回调函数)
// 成功的回调函数是必选的,失败的回调函数是可选的

// p.then(result=>{},error=>{})