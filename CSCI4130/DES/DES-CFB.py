from base64 import b64encode
from Crypto.Cipher import DES

dataHex = 'e2 9f 0c d8 85 55 ca 72 fb 34 f1 a0 c7 cd 27 0f fb a5 64 75 2c 5a 9f 47 1c 0d db c6 98 31 20 da 63 eb 63 cd 7c cb e3 2f 65 ee df 15 8b b3 1c 31 dc 22 5b c0 bb 8d 46 4d e5 5b a8 e8 4e 8d 8f5d 27 a4 c4 2d 88 1d 1a 26 40 ed 58 b7 c9 9a 11 d7 25 5c 18 15 43 c4 c7 ee e9 44 03 68 17 4c 6c 00 73 8f d2 63 20 e9 6c 3a 63 a0 c8 a5 90 7f 87 99 dd fb 9b d7 94 4c 96 bc 72 ef 8b f8 15 cc e5 36 15 ca 5b 7e 32 cc bc 99 38 a8 80 c6 cf 1e ca 2b d2 c9 ee 69 69 28 71 c1 8c 68 e6 e2 b3 f3 f7 17 9e 46 c6 0e fd 37 00 b2 c9 32 04 cf 11 82 fa 45 24 ac b9 3e 49 c5 ac 9b a4 ba 72 02 e1 e1 64 dd 74 30 40 cf db da b9 64 a8 2c a3 0f eb 87 5f 77 ee 94 33 fa 97 f9 48 9f 96 30 d5 cc 12 c6 76 9e 62 49 e6 35 c7 80 26 f4 1f 6b a0 fa 36 c2 35 57 d1 30 65 3a 9d 57 6b d3 d2 0d 74 f0 68 ec e6 5e fc f1 15 ac 38 9f 19 e7 ab 1e f3 31 63 80 d4 4a 51 f6 fd'
dataHex.replace(' ','')
bytes_data = bytes.fromhex(dataHex)

keyHex = 'f007ba11ba5eba11'
bytes_key = bytes.fromhex(keyHex)


ivHex = '746daff7ce2056cd'
bytes_iv = bytes.fromhex(ivHex)

cipher = DES.new(bytes_key, DES.MODE_CFB, iv=bytes_iv, segment_size=64)
pt = cipher.decrypt(bytes_data)
print("The message was: ", pt)