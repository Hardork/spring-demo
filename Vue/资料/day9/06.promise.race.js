import thenFs from "then-fs"

const promiseAll = [
  thenFs.readFile('./files/1.txt', 'utf8'),
  thenFs.readFile('./files/2.txt', 'utf8'),
  thenFs.readFile('./files/3.txt', 'utf8')
]

// Promise.race()方法,一旦有异步操作结束了,就执行.then()中的回调函数,拿到的是最先执行完的异步操作的结果（赛跑机制）
Promise.race(promiseAll).then( result => {
  console.log(result)
})