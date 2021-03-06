CREATE TABLE tc_extension_log (
  id         INT PRIMARY KEY AUTO_INCREMENT
  COMMENT '主键',
  unique_key VARCHAR(64) COMMENT '业务键',
  level      VARCHAR(5)  NOT NULL
  COMMENT '级别',
  date       DATETIME(3) NOT NULL
  COMMENT '日期',
  logger     VARCHAR(40) NOT NULL
  COMMENT 'Logger名',
  thread     VARCHAR(40) NOT NULL
  COMMENT '线程名',
  file       VARCHAR(40) NOT NULL
  COMMENT '文件名',
  line       INT         NOT NULL
  COMMENT '行数',
  msg        TEXT        NOT NULL
  COMMENT '信息',
  exception  TEXT COMMENT '异常堆栈'
);
