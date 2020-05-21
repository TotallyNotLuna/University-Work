from base64 import b64encode
from Crypto.Cipher import DES

dataHex = '64 27 63 80 7a 44 6b 98 3c 2d c9 8f 22 de 55 08 40 29 56 19 48 98 6d 1f 02 2e 03 8a 623c b3 bf 51 70 d3 2e db cd 93 1b 13 45 e7 a2 0f 16 82 d2 4d 71 55 c3 ff 12 25 38 07 cb 10 c3 80 cf73 be 6c 22 7d db 55 75 e6 df a5 fb eb 8d ec 81 6f 75 39 de a8 6b 92 cf d4 63 49 10 3d 14 4f 69 f32c 52 7f ab 28 cc cb 8c e9'
dataHex.replace(' ','')
bytes_data = bytes.fromhex(dataHex)

keyHex = 'f007ba11ba5eba11'
bytes_key = bytes.fromhex(keyHex)

cipher = DES.new(bytes_key, DES.MODE_ECB)
pt = cipher.decrypt(bytes_data)
print("The message was: ", pt)