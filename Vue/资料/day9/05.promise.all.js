import thenFs from "then-fs"

const promiseAll = [
  thenFs.readFile('./files/1.txt', 'utf8'),
  thenFs.readFile('./files/2.txt', 'utf8'),
  thenFs.readFile('./files/3.txt', 'utf8')
]

// Promise.all()方法,会发起并行的Promise异步操作,等所有的异步操作全部结束后才会执行下一步的.then()操作(等待机制)
Promise.all(promiseAll).then( result => {
  console.log(result)
})