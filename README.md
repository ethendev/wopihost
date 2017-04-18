#介绍
仓库里面是一个WopiHost demo,支持word, excel，ppt等文档的预览和编辑。

#环境
基于Office online 2016，jdk 1.8，spring boot开发。

#遗留问题
access_token没有做校验，需要的自己实现一下。

#使用案例

word文档预览
http://[owas.domain]/wv/wordviewerframe.aspx?WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.docx&access_token=[token]
word文档编辑
http://[owas.domain]/we/wordeditorframe.aspx?WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.docx&access_token=[token]

excel预览
http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?ui=zh-CN&rs=zh-CN&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.xlsx&access_token=[token]
excel编辑
http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?edit=1&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.xlsx&access_token=[token]

ppt预览
http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=ReadingView&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.pptx&access_token=[token]
ppt编辑
http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=EditView&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.pptx&access_token=[token]

备注：[owas.domain]是Office online 2016的ip地址，[WopiHost.domain]是WopiHost服务的ip
使用时替换成自己的服务地址