# wopihost

[![GitHub release](https://img.shields.io/github/release/ethendev/wopihost.svg)](https://github.com/ethendev/wopihost/releases)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/ethendev/wopihost/master/LICENSE)

### Introduction
WopiHost supports previewing and editing documents such as word, excel, and ppt (preview only) through Office Web Apps.

### Environment
First you need to install Office online 2016 and java 1.8.

### Usage
word preview

http://[owas.domain]/wv/wordviewerframe.aspx?WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.docx&access_token=123
![word view](https://raw.githubusercontent.com/ethendev/data/master/wopihost/20170418172425910.png)

word editor 

http://[owas.domain]/we/wordeditorframe.aspx?WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.docx&access_token=123
![word edit](https://raw.githubusercontent.com/ethendev/data/master/wopihost/20170418172534332.png)

excel preview  

http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?ui=zh-CN&rs=zh-CN&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.xlsx&access_token=123

excel editor

http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?edit=1&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.xlsx&access_token=123

ppt preview  

http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=ReadingView&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.pptx&access_token=123

ppt editor  

http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=EditView&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.pptx&access_token=123

Note：[owas.domain] is the IP address of Office online 2016，[WopiHost.domain] is the address of wopihost。

### Known issues
* Doc files can`t be previewed and edited, but docx is ok.
* PPT files can be previewed, but can`t be edited.
* I didn't check the access_token parameter. if you need it, implement it yourself.

### License
[MIT License](https://github.com/ethendev/wopihost/blob/master/LICENSE.md)