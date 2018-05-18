# wopihost

[![GitHub stars](https://img.shields.io/github/stars/ethendev/wopihost.svg)](https://github.com/ethendev/wopihost/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/ethendev/wopihost.svg)](https://github.com/ethendev/wopihost/network)
[![GitHub issues](https://img.shields.io/github/issues/ethendev/wopihost.svg)](https://github.com/ethendev/wopihost/issues)
[![GitHub release](https://img.shields.io/github/release/ethendev/wopihost.svg)](https://github.com/ethendev/wopihost/releases)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/ethendev/wopihost/master/LICENSE)

**[English](https://github.com/ethendev/wopihost/blob/master/README-EN.md)**

### 项目介绍
基于wopi协议开发的WopiHost, 支持word, excel，ppt(仅支持预览)等文档的预览和编辑。

### 运行环境
需要安装Office online 2016才可以使用，基于jdk 1.8，spring boot开发。

### 使用案例
word文档预览   

http://[owas.domain]/wv/wordviewerframe.aspx?WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.docx&access_token=123
![Alt text](http://img.blog.csdn.net/20170418172425910?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveXVmZWl5YW5saXU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

word文档编辑  

http://[owas.domain]/we/wordeditorframe.aspx?WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.docx&access_token=123
![Alt text](http://img.blog.csdn.net/20170418172534332?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveXVmZWl5YW5saXU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

excel预览  

http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?ui=zh-CN&rs=zh-CN&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.xlsx&access_token=123

excel编辑   

http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?edit=1&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.xlsx&access_token=123

ppt预览  

http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=ReadingView&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.pptx&access_token=123

ppt编辑   

http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=EditView&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.pptx&access_token=123

备注：[owas.domain]是Office online 2016的ip地址，[WopiHost.domain]是WopiHost服务的ip。
使用时替换成自己的服务地址，将test.docx换成自己对应文件路径下的文件名。

### 常见问题
* word文档仅支持.docx，不支持.doc格式。
* ppt仅支持预览，不支持编辑。
* 如果wopihost的接口都没有问题，但是不能预览或者编辑文档，可能wopi和Office Web Apps Server之间的网络不能互通，也可能Office Web Apps Server有问题，建议重新安装后重试。
* access_token没有做校验，需要的自己实现一下。
* 中文名字的文档不能预览、编辑，将中文名使用URLEncoder.encode进行两次编码。例如：哈哈哈.xlsx, 使用中文编码后的名字作为参数 .../wopi/files/%25e5%2593%2588%25e5%2593%2588%25e5%2593%2588.xlsx即可正常预览。

### License
[MIT License](https://github.com/ethendev/wopihost/blob/master/LICENSE.md)