import db from '../my_db/index.js'

// 使用ES6按需导出语法, 将grtAllUser()方法导出
export async function getAllUser(req, res) {
   const [rows] = await db.query('select id, username, nickname from ev_users')
  // db返回的是一个promise对象,可以用async和await直接读取
  // await db... 读取到的是一个嵌套的数组，而所要的用户数据在索引为0的位置,可以使用结构赋值式,直接得到用户数据
  try {
    res.send({
      status:0,
      message:'获取用户列表成功',
      data: rows
    })
  }catch(err){
    res.send({
      status: 1,
      message: '获取用户列表失败',
      desc: err.message
    })
  }
  
}
