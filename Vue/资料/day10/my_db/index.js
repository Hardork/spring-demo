import mysql from 'mysql2'

const pool = mysql.createPool({
  host:'127.0.0.1',
  port: 3306,
  database: 'my_db_01',//填写数据库的名称
  user:'root',//登入数据库的用户名
  password:'admin123'//登录数据库的密码
})

export default pool.promise()