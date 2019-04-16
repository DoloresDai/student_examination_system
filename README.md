#### 主要介绍：

设计了一个学生管理考试系统，主要支持增删改查学生表、老师表、科目表、成绩单表。

##### 数据库`student_examination_system`有以下五张表格

**student表**

| id       | name   | age  | sex  |
| -------- | ------ | ---- | ---- |
| 20190101 | 池昌旭 | 18   | 男   |
| 20190102 | 代元丽 | 18   | 女   |
| 20190103 | 宋威龙 | 18   | 男   |
| 20190104 | 张爱玲 | 18   | 女   |
| 20190105 | 邱泽   | 17   | 男   |

**Teacher表**

| id   | name   | subject_id |
| ---- | ------ | ---------- |
| 1001 | 刘昊然 | 2002       |
| 1002 | 井柏然 | 2001       |
| 1003 | 倪妮   | 2003       |

**subject表**

| id   | name | content                          |
| ---- | ---- | -------------------------------- |
| 2001 | 语文 | 考试内容较简单                   |
| 2002 | 数学 | 考试难度中等，后面大题计算较复杂 |
| 2003 | 英语 | 考试内容较难                     |

**score表**

| id   | teacher_id | student_id | score |
| ---- | ---------- | ---------- | ----- |
| 1    | 1001       | 20190101   | 88    |
| 2    | 1001       | 20190102   | 81    |
| 3    | 1001       | 20190103   | 92    |
| 4    | 1001       | 20190104   | 94    |
| 5    | 1001       | 20190105   | 82    |
| 6    | 1002       | 20190101   | 89    |
| 7    | 1002       | 20190102   | 78    |
| 8    | 1002       | 20190103   | 89    |
| 9    | 1002       | 20190104   | 90    |
| 10   | 1002       | 20190105   | 99    |
| 11   | 1003       | 20190101   | 92    |
| 12   | 1003       | 20190102   | 91    |
| 13   | 1004       | 20190103   | 89    |
| 14   | 1004       | 20190104   | 86    |
| 15   | 1003       | 20190105   | 80    |

**admin_account表**

| id   | user  | password |
| ---- | ----- | -------- |
| 1    | admin | 123      |

**登录截图**

![](http://ww1.sinaimg.cn/large/005Qcrzely1g24qv362uqj31hc0sq42c.jpg)
![](http://ww1.sinaimg.cn/large/005Qcrzely1g24qverl9aj31hc0sqn0v.jpg)
##### 目录

```
您好，超级管理员，请选择你需要进行的操作：
1. 查询
	1.1 查询学生信息以及成绩
		1.1.1 所有学生信息
		1.1.2 指定学生姓名的信息以及所有课程的成绩
		1.1.3 指定老师的所有学生及其成绩
		1.1.4 指定课程的所有学生及其成绩
	1.2 查询课程信息
	    1.2.1 所有课程信息
	    1.2.2 指定课程名称的信息
	    1.2.3 指定老师的所有课程信息
	1.3 查询老师信息
	    1. 所有老师信息
	    2. 指定老师信息
2. 新增
	2.1 新增学生信息
	2.2 新增课程信息
	2.3 新增老师信息
	2.4 给指定学生新增成绩
3. 修改
    3.1 修改指定学号的学生
    3.2 修改指定课程的信息
    3.3 修改指定老师的信息
    3.4 修改指定学生的成绩
4. 删除
	4.1 删除指定学生
	4.3 删除指定课程
	4.3 删除指定老师
```

##### 查询所有学生成绩信息1.1.1
![](http://ww1.sinaimg.cn/large/005Qcrzely1g24qw0tnydj31hc0sqn15.jpg)
##### 查询所有课程信息1.2.1

![](http://ww1.sinaimg.cn/large/005Qcrzely1g24qwa08vtj31hc0sq42n.jpg)
##### 查询所有老师信息1.3.1

![](http://ww1.sinaimg.cn/large/005Qcrzely1g24qwi7wioj31hc0sq429.jpg)
##### 当选项出错时

![](http://ww1.sinaimg.cn/large/005Qcrzely1g24qxdropnj31hc0sq0w7.jpg)
![](http://ww1.sinaimg.cn/large/005Qcrzely1g24qxmn4vgj31hc0sqadq.jpg)
![](http://ww1.sinaimg.cn/large/005Qcrzely1g24qy2oh6hj31hc0sq42n.jpg)
##### 退出系统

![](http://ww1.sinaimg.cn/large/005Qcrzely1g24qyeqhqdj31hc0sq77a.jpg)