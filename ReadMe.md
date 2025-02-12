<div align="center">
  <a href="./README.md">English</a> | 
  <a href="./README_zh.md">简体中文</a>
</div>

# Multi-Data Source Data Processing Tool

## Features
- Support for multiple database configurations (source/target)
- Generate result files

## Environment Requirements
- JDK 8+
- Maven 3.2+
- MySQL 5.6+

## Quick Start
### 1. Configure the Database
application.properties

Source Database Configuration
source.datasource.url=jdbc:mysql://localhost:3306/source_db
source.datasource.username=root
source.datasource.password=123456

Target Database Configuration
target.datasource.url=jdbc:mysql://localhost:3306/target_db
target.datasource.username=root
target.datasource.password=123456

### 2. Configure the Running Parameters
Write the data processing logic
org.oss.processor.DataProcessor#processData

### 3. Start the Application



## File Structure
```bash
src/
├── main/
│   ├── java/org/oss/
│   │   ├── processor/       # Business processing core module
│   │   ├── config/          # Data source configuration
│   │   └── SpringBootSimpleUtilApplication.java  # Startup class
│   └── resources/
│       └── application.properties  # Configuration file
```
