from base64 import b64encode
from Crypto.Cipher import DES

dataHex = 'a0	d2	6c	68	33'
dataHex.replace(' ','')
bytes_data = bytes.fromhex(dataHex)

keyHex = 'f007ba11ba5eba11'
bytes_key = bytes.fromhex(keyHex)


ivHex = '746daff7'
bytes_iv = bytes.fromhex(ivHex)

cipher = DES.new(bytes_key, DES.MODE_CTR, nonce=bytes_iv)
pt = cipher.decrypt(bytes_data)
print("The message was: ", pt)
