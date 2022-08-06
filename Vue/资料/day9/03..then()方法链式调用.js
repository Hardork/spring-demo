import thenFs from "then-fs"

thenFs.readFile('./files/1.txt', 'utf8').then((r1) => {
  console.log(r1)
  return thenFs.readFile('./files/2.txt', 'utf8')
})
.then((r2)=> {
  console.log(r2)
  return thenFs.readFile('./files/3.txt', 'utf8')
})
.then((r3)=> {
  console.log(r3)
})

// .then()方法的特性
// 如果上一个.then()方法中返回了一个promise实例对象,则可以通过下一个.then()方法继续进行处理
// 通过.then()方法的链式调用,就解决了回调地狱的问题