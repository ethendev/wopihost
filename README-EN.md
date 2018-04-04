### Introduction
WopiHost supports previewing and editing documents such as word, excel, and ppt (preview only) through Office Web Apps.

### Environment
First you need to install Office online 2016 and java 1.8.

### Usage
word preview

http://[owas.domain]/wv/wordviewerframe.aspx?WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.docx&access_token=123
![Alt text](http://img.blog.csdn.net/20170418172425910?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveXVmZWl5YW5saXU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

word editor 

http://[owas.domain]/we/wordeditorframe.aspx?WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.docx&access_token=123
![Alt text](http://img.blog.csdn.net/20170418172534332?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQveXVmZWl5YW5saXU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

excel preview  

http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?ui=zh-CN&rs=zh-CN&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.xlsx&access_token=123

excel editor

http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?edit=1&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.xlsx&access_token=123

ppt preview  

http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=ReadingView&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.pptx&access_token=123

ppt editor  

http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=EditView&WOPISrc=http://[WopiHost.domain]:8080/wopi/files/test.pptx&access_token=123

Note：[owas.domain] is the IP address of Office online 2016，[WopiHost.domain] is the address of wopihost。

### Frequently Asked Questions
* Doc files cannot be previewed and edited, but docx is ok.
* PPT files cannot be previewed and edited.
* If the document cannot be edited or previewed, make sure that the wopi server can access the office server.
* I didn't check the access_token parameter. if you need it, implement it yourself.