### 介绍
基于wopi协议开发的WopiHost, 支持word, excel，ppt(仅支持预览)等文档的预览和编辑。

### 环境
需要安装Office online 2016才可以使用，基于jdk 1.8，spring boot开发。

### 遗留问题
access_token没有做校验，需要的自己实现一下。word文档仅支持.docx，不支持.doc格式。

### 使用案例

word文档预览   

http://[owas.domain]/wv/wordviewerframe.aspx?WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.docx&access_token=123

word文档编辑  

http://[owas.domain]/we/wordeditorframe.aspx?WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.docx&access_token=123

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
