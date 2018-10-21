# wopihost

[![GitHub release](https://img.shields.io/github/release/ethendev/wopihost.svg)](https://github.com/ethendev/wopihost/releases)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/ethendev/wopihost/master/LICENSE)

**[English](https://github.com/ethendev/wopihost/blob/master/README-EN.md)**

### 项目介绍
基于 wopi 协议开发的 WopiHost , 支持 word, excel，ppt, pdf(仅支持预览)等文档的预览和编辑。

### 运行环境
需要安装 Office online 2016 才可以使用，基于jdk 1.8，spring boot开发。

### 使用方法
在 application.properties 中配置文档的存储路径
```
file.path=E:\\
```

word文档预览
http://[owas.domain]/wv/wordviewerframe.aspx?WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.docx
![word view](https://raw.githubusercontent.com/ethendev/data/master/wopihost/20170418172425910.png)

word文档编辑  
http://[owas.domain]/we/wordeditorframe.aspx?WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.docx
![word edit](https://raw.githubusercontent.com/ethendev/data/master/wopihost/20170418172534332.png)

excel预览  
http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?ui=zh-CN&rs=zh-CN&WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.xlsx

excel编辑   
http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?edit=1&WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.xlsx

ppt预览
http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=ReadingView&WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.pptx

ppt编辑   
http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=EditView&WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.pptx

备注：[owas.domain] 是 Office online 2016 的地址，[WopiHost.IP]是 WopiHost 服务的 IP 地址。
使用时替换成自己相应的服务地址。

### 常见问题
* word 文档编辑支持 .docx 格式，不支持 .doc 。
* pdf 仅支持预览，不支持编辑。
* 如果 wopihost 的接口没有问题，但是不能预览或者编辑文档，可能 wopi 和 Office Web Apps Server 之间的网络不能互通，也可能 Office Web Apps Server 有问题，建议重新安装后重试。
* 中文名字的文档不能预览、编辑，将中文名使用 URLEncoder.encode 进行两次编码。例如：哈哈哈.xlsx, 编码为 %25e5%2593%2588%25e5%2593%2588%25e5%2593%2588.xlsx 即可正常预览。

### License
[MIT License](https://github.com/ethendev/wopihost/blob/master/LICENSE.md)