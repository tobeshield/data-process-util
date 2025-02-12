# Data Processor Utility 多数据源数据处理工具



## Features 功能特性
- 支持多数据库配置（源库/目标库）
- 生成结果文件

## Prerequisites 环境要求
- JDK 8+
- Maven 3.2+
- MySQL 5.6+

## Quick Start 快速开始
### 1. 配置数据库
application.properties

源数据库配置
source.datasource.url=jdbc:mysql://localhost:3306/source_db
source.datasource.username=root
source.datasource.password=123456

目标数据库配置
target.datasource.url=jdbc:mysql://localhost:3306/target_db
target.datasource.username=root
target.datasource.password=123456

### 2. 配置运行参数
编写数据处理逻辑
org.oss.processor.DataProcessor#processData

### 3. 启动应用



## File Structure 文件结构
```bash
src/
├── main/
│   ├── java/org/oss/
│   │   ├── processor/       # 业务处理核心模块
│   │   ├── config/          # 数据源配置
│   │   └── SpringBootSimpleUtilApplication.java  # 启动类
│   └── resources/
│       └── application.properties  # 配置文件
```
