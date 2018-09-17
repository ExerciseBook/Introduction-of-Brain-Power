#coding=utf-8
import qrcode
s="Let the bass kick O-oooooooooo AAAAE-A-A-I-A-U- JO-oooooooooooo AAE-O-A-A-U-U-A- E-eee-ee-eee AAAAE-A-E-I-E-A- JO-ooo-oo-oo-oo EEEEO-A-AAA-AAAA".encode("utf-8")
qr=qrcode.QRCode()
qr.add_data(s)
qr.make(fit=True)
img=qr.make_image()
img.save("test.png")
