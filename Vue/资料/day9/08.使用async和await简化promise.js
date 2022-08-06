//### async和await是配套使用的，如果函数中使用了await,那么就必须在函数前加上async进行修饰
//### 如果一个函数返回的对象是一个promise对象,那么就可以使用await直接读取promise实例对象中的内容
// 在await之前的代码都是同步执行的,先执行同步，再执行异步,简单来说就是等所有代码(除了await之后的)执行完了,再执行await之后的语句

import thenFs from "then-fs"

console.log('B')
async function getAllFile() {
  console.log('A')

  const r1 = await thenFs.readFile('./files/1.txt', 'utf8')
  console.log(r1)

  const r2 = await thenFs.readFile('./files/2.txt', 'utf8')
  console.log(r2)

  const r3 = await thenFs.readFile('./files/3.txt', 'utf8')
  console.log(r3)

  console.log('D')
}

getAllFile()
console.log('C')