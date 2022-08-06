import thenFs from "then-fs"

// 故意将1.txt改为11.txt
thenFs.readFile('./files/11.txt', 'utf8')
// 通过.catch()可以捕获到链式调用的错误,.catch()方法会获取这个语句之前的错误,之后的语句无法检查到
.catch((err)=>{
  console.log(err.message)
})
.then((r1) => {
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

