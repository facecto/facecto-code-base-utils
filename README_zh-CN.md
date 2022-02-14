# 关于 facecto-code-base-utils
facecto-code-base-utils 是一套工具集，它将聚合更多的工具。

# 快速开始
## 步骤 1: 配置 pom.xml 依赖
```
<dependency>
  <groupId>com.facecto.code</groupId>
  <artifactId>facecto-code-base-utils</artifactId>
  <version>1.1.2</version>
</dependency>
```
## 步骤 2 : 没有更多步骤了

# 关于 UploadTools
UploadTools 是基于阿里云OSS的直接上传方案。代码于2022元旦，祝新年快乐。
提供如下文件上传功能：
1. uploadFile(MultipartFile file, OSSDirectParam param)
2. uploadFile(MultipartFile file, String savePath, OSSDirectParam param)
3. uploadFile(InputStream inputStream, String savePath, OSSDirectParam param)
4. uploadFile(byte[] data, String savePath, OSSDirectParam param)

提供如下图片上传功能：（可选加水印：图片水印、文字水印）
1. upImageBase(MultipartFile file, String savePath, WatermarkType waterType, WatermarkText waterText,
   WatermarkImage waterImage, OSSDirectParam param)
2. upImageBase(InputStream inputStream, String savePath, WatermarkType waterType, WatermarkText waterText,
   WatermarkImage waterImage, OSSDirectParam param)

# 关于 OSSTools
OssTools 封装了阿里云的 STS token获取方式。

# 关于 QRCodeUtils
基于Google zxing的二维码生成和解码工具。

## 关于ID生成器
* 生成的结果总长度是定长24位字符串
* 前8位：年月日
* 第9位是微服务或app代号，取值范围0-15。显示为16进制
* 第10位是机器码，取值范围0-15。显示为16进制
* 最后14位是当天顺序码。
* 例如：20211202AB10098259438592
* 此为1.1.0新增

## 顺序码计算逻辑
```
* 0 101001001100101101111111111 1111 1111 001111111111
* 0 <-------    A    ---------> < B> < C> <---- D --->
```
* 如以上图示，总48位
* 第1位0
* A 是间戳差值长度27
* B 是应用码 长度4，取值范围0-15。可配置16个服务
* C 是机器码 长度4，取值范围0-15。可配置16台机器
* D 是顺序码 长度12，取值范围0-4095。每秒产生4095个顺序码


# 关于 Jon So
希望能和你做朋友！我的站点https://facecto.com

# 关于 facecto.com
https://facecto.com

# 文档更新时间
2020-02-14