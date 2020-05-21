import json
from base64 import b64encode
from Crypto.Cipher import DES

dataHex = '05 f3 5d b4 83 26 64 39 cf b4 b4 c0 89 5e 21 94 95 87 c6 ff 0c 00 a9 29 70 fa ef 21 2d b82e 70 74 9a 98 05 37 6f e0 9d 44 6e 8d 10 20 cc 35 87 61 d9 6e 40 be 29 98 3c 5e bc 4c d5 47 5018 ca 01 a3 73 fd cc a2 45 52 77 2b e2 86 a1 98 1b 41 0e 16 31 ff f9 dd 47 85 27 1d 72 c9 fc a0 711f de 46 ef 88 03 04 7b 66 82 ac 71 7b ee 35 e0 ea 59 6b a1 d5 e8 fb bb 1f 55 82 2f 6f 81 fa c3 7cdc f5 a2 a5 08 95 b9 8f 9b e4 5e eb 7c 96 20 c5 7a 8b bb 26 88 ef 03 80 35 ef f5 79 18 42 3b 16 112a 16 28 2b 51 ce bf e1 03 3c e4 c8 35 24 04 93 a0 a5 e1 e8 a4 b9 1d 93 f0 7f d3 61 13 0a cd 64 512c 51 32 3b 4d d7 29 cd d6 22 27 c9 5f 98 d3 b8 90 24 cc 44 03 b4 8f 5e 10 96 d4 1b ff 8c 43 9b 442a fa 89 4c 52 36 d3 20 98 b2 fc 97 04 52 73 22 06 39 2a 09 45 7a 91 b7 05 40 6b 01 42'
dataHex.replace(' ','')
bytes_data = bytes.fromhex(dataHex)

keyHex = 'f007ba11ba5eba11'
bytes_key = bytes.fromhex(keyHex)


ivHex = '39db1a2d187e4e3a'
bytes_iv = bytes.fromhex(ivHex)

cipher = DES.new(bytes_key, DES.MODE_CBC, iv=bytes_iv)
pt = cipher.decrypt(bytes_data)
print("The message was: ", pt)
