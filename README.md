# EngineeringTraining

## 接口

BaseUrl：http://localhost:8080

### 注册
**/signup**
POST

| 请求参数名    | 参数类型   |
| -------- | ------ |
| account  | string |
| username | string |
| password | string |

结果

```json
{
    "status_code": 1,
    "msg_code": 1
}
```
| status状态码 | 含义   |
| --------- | ---- |
| 0         | 注册成功 |
| 1         | 注册失败 |

| msg状态码 | 含义    |
| ------ | ----- |
| 0      | 注册成功  |
| 1      | 账号已存在 |



### 图片识别

**/recognize**

POST

| 请求参数名    | 参数类型   |
| -------- | ------ |
| account  | string |
| password | string |
|          | file   |

返回

```json
{
    "status": "ok",
    "code": 200,
    "result": [
        "蓝牌:苏EUK722"
    ]
}
```

| status | 含义   |
| ------ | ---- |
| ok     | 成功识别 |
| error  | 识别失败 |

| code | 含义   |
| ---- | ---- |
| 195  | 未登录  |
| 196  | 内部错误 |
| 197  | 非法图片 |
| 198  | 无法识别 |
| 199  | 识别失败 |
| 200  | 识别成功 |

