## springboot-blog

基于 SpringBoot + Mybatis + MySQL 实现个人博客的 RESTful 服务，并使用 Swagger2 实现接口文档。

## 开始

作者信息表：
```mysql
DROP TABLE IF EXISTS 'author';

CREATE TABLE `author` (
  `author_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '作者id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '住址',
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

文章信息表：
```mysql
DROP TABLE IF EXISTS 'article';

CREATE TABLE `article` (
  `article_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL COMMENT '文章标题',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文章简介',
  `publish_time` date DEFAULT NULL COMMENT '发布时间',
  `last_modify_time` date DEFAULT NULL COMMENT '最后修改时间',
  `author_id` bigint(20) unsigned DEFAULT NULL COMMENT '作者id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章内容',
  `source` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文章来源',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

文章评论表：
```mysql
DROP TABLE IF EXISTS 'comment';

CREATE TABLE `comment` (
  `comment_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论内容',
  `publish_time` date DEFAULT NULL COMMENT '评论发布时间',
  `like_count` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `article_id` bigint(20) unsigned DEFAULT NULL COMMENT '文章id',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

## 接口说明
启动应用后，访问 http://localhost:8080/swagger-ui.html 即可。

