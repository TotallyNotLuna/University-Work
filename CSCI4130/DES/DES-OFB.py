from base64 import b64encode
from Crypto.Cipher import DES

dataHex = 'b7 4f 7a af a1 b9 0b fd 66 8c a5 66 c6 bf 03 8c 1f 04 58 6c dd 16 6c 36 57 ce 1c 4d 05 5d 0b 7b 5e 95 eb e1 02 0e 11 68 73 63 51 0f 4e 1c 95 4f 45 ec ed 21 1f 92 a6 97 4f 7b d3 4c f5 64 ce 5f b6 9c 16 73 66 a7 53 5b 2f e3 e8 25 2f 70 3d db 32 6e 71 2b d9 a6 54 50 da 3c 2d ba e2 b9 7c ea 1a a9 f9 ab 1f b6 16 90 ca 62 cf 4b 86 ce 09 06 47 b3 57 5b f2 37 59 5e 1e a3 5a 91 7f a7 fd 5a f5 ec 0c 31 b5 78 b3 75 32 eb 47 20 e7 a7 0a 32 35 38 5a 1c 63 52 77 94'
dataHex.replace(' ','')
bytes_data = bytes.fromhex(dataHex)

keyHex = 'f007ba11ba5eba11'
bytes_key = bytes.fromhex(keyHex)


ivHex = 'bb2dda8bcc8d6b63 '
bytes_iv = bytes.fromhex(ivHex)

cipher = DES.new(bytes_key, DES.MODE_OFB, iv=bytes_iv)
pt = cipher.decrypt(bytes_data)
print("The message was: ", pt)