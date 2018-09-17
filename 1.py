import hmac
import hashlib
import base64
import qrcode
import os

print("啊！我的朋友！你！是想要我帮你做什么事情呢？")
print("   1. 计算SHA256")
print("   2. Base64编码")
print("   3. Base64解码")
print("   4. 生成二维码")
s = input("请输入你要的功能的序号吧: ")
while  (not (s in {"1","2","3","4"})) :
    print("\n\n啊！朋友！你是在逗我吧？")
    print("   1. 计算SHA256")
    print("   2. Base64编码")
    print("   3. Base64解码")
    print("   4. 生成二维码")
    s = input("请重新输入你要的功能的序号吧: ")

while s!="0" :
    if s=="1" :
        print("我的朋友，请你告诉我你想要计算什么内容的SHA256信息摘要吧！")
        a=input().encode("utf-8")
        hashObj = hashlib.sha256(a)
        print("嘿！我的朋友！我算出来啦！")
        print("你想要的内容的SHA256信息摘要是: ",hashObj.hexdigest().upper())
    elif s=="2" :
        print("我的朋友，请你告诉我你想要把什么东西给使用Base64编码吧！")
        a=input().encode("utf-8")
        print("嘿！我的朋友！我算出来啦！")
        print("你想要的内容Base64编码之后的内容是: ",str(base64.b64encode(a),"utf-8"))
    elif s=="3" :
        print("我的朋友，请你告诉我你想要把什么东西给使用Base64解码吧！")
        a=input().encode("utf-8")
        try:
            back=str(base64.b64decode(a),"utf-8")  
        except BaseException :
            print("啊！非常抱歉！我亲爱的朋友，我没有算出这个内容的Base64的解码结果")
        else :
            print("嘿！我的朋友！我算出来啦！")
            print("你想要的内容Base64解码之后的内容是: ",back)
    elif s=="4" :
        print("我的朋友，请你告诉我你想要生成包含什么内容的二维码吧！")
        a=input()
        qr=qrcode.QRCode()
        qr.add_data(a)
        qr.make(fit=True)
        img=qr.make_image()
        img.save("export.png")
        print("嘿！朋友！我搞定了！按下回车之后就可以自动打开这个文件啦！")
        input()
        os.system("explorer export.png")
    
    print("\n\n嘿，我的朋友")
    print("还需要让我做其他事情吗？")
    print("   0. 退出本程序")
    print("   1. 计算SHA256")
    print("   2. Base64编码")
    print("   3. Base64解码")
    print("   4. 生成二维码")
    s = input("请输入你要的功能的序号吧: ")

    while  (not (s in {"0","1","2","3","4"})) :
        print("啊！朋友！你是在逗我吧？")
        print("   0. 退出本程序")
        print("   1. 计算SHA256")
        print("   2. Base64编码")
        print("   3. Base64解码")
        print("   4. 生成二维码")
        s = input("请重新输入你要的功能的序号吧: ")
    
    if s=="0" :
        print("啊！我亲爱的朋友！再见！")
        input()