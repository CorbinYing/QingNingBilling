# 开发规范

<hr>

## 业务对象规范

阿里巴巴编码规约中列举了下面几个领域模型规约
DO（Data Object）：与数据库表结构一一对应，通过DAO层向上传输数据源对象<br>
DTO（Data Transfer Object）：数据传输对象，Service或Manager向外传输的对象<br>
BO（Business Object）：业务对象。由Service层输出的封装业务逻辑的对象<br>
AO（Application Object）：应用对象。在Web层与Service层之间抽象的复用对象模型，极为贴近展示层，复用度不高<br>
VO（View Object）：显示层对象，通常是Web向模板渲染引擎层传输的对象<br>

## Restful接口规范

<hr>

#### HTTP请求规范<br>

GET（SELECT）：从服务器取出资源（一项或多项）<br>
POST（CREATE）：在服务器新建一个资源<br>
PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）<br>
PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）<br>
DELETE（DELETE）：从服务器删除资源<br>

#### API版本控制

*本应用使用URI路径控制* <br>
其他举例<br>

媒体类型的版本控制: Github <br>
自定义Header:Microsoft <br>
URI路径: Twitter，百度，知乎 <br>
请求参数控制: Amazon <br>

## 统一返回数据规范

<hr>
返回统一封装结果，必须包含err_code,err_msg <br>
当且仅当err_code===0时表示服务器正常响应，此时存在result结果，否则，无result字段<br>
示例<br>

```
{
    "err_code": 0,
    "err_msg": "successed",
    "result": {}
}
```



