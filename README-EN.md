# wopihost

[![GitHub release](https://img.shields.io/github/release/ethendev/wopihost.svg)](https://github.com/ethendev/wopihost/releases)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/ethendev/wopihost/master/LICENSE)

### Introduction
WopiHost supports previewing and editing documents such as word, excel, and ppt (preview only) through Office Web Apps.

### Environment
First you need to install Office online 2016 and java 1.8.

### Usage
configure the file path in Application.properties
```
file.path=E:\\
```

#### Request Path  
http://[owas.domain]/hosting/discovery  
Open the URL above, and you can see the XML file with the following result, which has the request path of the corresponding file type. 
![Request Path](https://raw.githubusercontent.com/ethendev/data/master/wopihost/20170418114309314.png)

word preview
http://[owas.domain]/wv/wordviewerframe.aspx?WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.docx&access_token=123
![word view](https://raw.githubusercontent.com/ethendev/data/master/wopihost/20170418172425910.png)

word editor 
http://[owas.domain]/we/wordeditorframe.aspx?WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.docx&access_token=123
![word edit](https://raw.githubusercontent.com/ethendev/data/master/wopihost/20170418172534332.png)

excel preview  
http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?ui=zh-CN&rs=zh-CN&WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.xlsx&access_token=123

excel editor
http://[owas.domain]/x/_layouts/xlviewerinternal.aspx?edit=1&WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.xlsx&access_token=123

ppt preview  
http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=ReadingView&WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.pptx&access_token=123

ppt editor  
http://[owas.domain]/p/PowerPointFrame.aspx?PowerPointView=EditView&WOPISrc=http://[WopiHost.IP]:8080/wopi/files/test.pptx&access_token=123

Note：[owas.domain] is the IP or domain address of Office online 2016，[WopiHost.IP] is the address of wopihost。

### Known issues
* Word document editing supports .docx format, does not support .doc.
* Pdf files can be previewed, but can`t be edited.
* I didn't check the access_token parameter. if you need it, implement it yourself.
* If you can't preview or edit the document files, it may be a network problem between Wopi and Office Web Apps Server, or a problem with Office Web Apps Server, you can reinstall it and try again.
* There is a [bug](https://social.msdn.microsoft.com/Forums/en-US/bb2f9118-8efd-463d-b4a2-54bb2cebf882/word-online-file-unlock-bug-office-online-server-201605?forum=os_office) in word's lock, 
  the locks in the header of unlock_and_relock and putfile request are not
  the same, we need to deal with it, otherwise we won't be able to save the word document.

### License
[MIT License](https://github.com/ethendev/wopihost/blob/master/LICENSE.md)