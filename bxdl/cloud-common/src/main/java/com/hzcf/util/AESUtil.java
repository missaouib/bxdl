package com.hzcf.util;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 *<dl>
 *<dt>类名：SystemMessage.java</dt>
 *<dd>
 *		描述: 加密类，
 *		用于“CRM系统”和“综合业务理财端”的接口对接
 *</dd> 
 *<dd>创建时间： 2017年8月10日 下午2:18:36</dd>
 *<dd>创建人： peigaoxiang</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年8月10日 下午2:18:36    peigaoxiang       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public class AESUtil {

	public final static String AES_KEY = "6166d8gb40jc991bjj78b5k505k7a52j";
	public static final String MD5_KEY = "18d6d9b9-3159-091d-a101-6502c8fb8601";
	
	/**
	 * 加密函数
	 * @param content   加密的内容
	 * @param strKey    密钥
	 * @return  		返回二进制字符数组
	 * @throws Exception
	 */
	public static String enCrypt(String content,String strKey) throws Exception{
		KeyGenerator keygen;		
		SecretKey desKey;
		Cipher c;		
		byte[] cByte;
		String str = content;
		//解决LINUX下KEY不一致  生成密匙，可用多种方法来保存密匙
		keygen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
        secureRandom.setSeed(strKey.getBytes());  
        keygen.init(128,secureRandom);  
		desKey = keygen.generateKey();		
		c = Cipher.getInstance("AES");
		
		c.init(Cipher.ENCRYPT_MODE, desKey);
		
		cByte = c.doFinal(str.getBytes("UTF-8"));		
		//加密过的二进制数组转化成16进制的字符串
		return parseByte2HexStr(cByte);
	}
	
	/** 解密函数
	 * @param src   加密过的二进制字符数组
	 * @param strKey  密钥
	 * @return
	 * @throws Exception
	 */
	public static String deCrypt (String src,String strKey) throws Exception{
		KeyGenerator keygen;		
		SecretKey desKey;
		Cipher c;		
		byte[] cByte;	
		
		keygen = KeyGenerator.getInstance("AES");
		//解决LINUX下KEY不一致
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
	    secureRandom.setSeed(strKey.getBytes());  
	    keygen.init(128,secureRandom);  
		
		desKey = keygen.generateKey();
		c = Cipher.getInstance("AES");
		
		c.init(Cipher.DECRYPT_MODE, desKey);
		//将16进制转换为二进制
		byte[] encrytByte = AESUtil.parseHexStr2Byte(src);		
		cByte = c.doFinal(encrytByte);	
		
		return new String(cByte,"UTF-8");
	}
	
	
	/**2进制转化成16进制
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
				}
			sb.append(hex.toUpperCase());
			}
		return sb.toString();
		}
	
	
	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */ 	
	public static byte[] parseHexStr2Byte(String hexStr) { 
	        if (hexStr.length() < 1) 
	                return null; 
	        byte[] result = new byte[hexStr.length()/2]; 
	        for (int i = 0;i< hexStr.length()/2; i++) { 
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16); 
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16); 
	                result[i] = (byte) (high * 16 + low); 
	        } 
	        return result; 
	}

	public static void main(String[] args)throws Exception {
		String src = "0EF0AF8DAAD6EB18C1CD1601C243612E45CC0C2140C9E471C9BC05E6A97C6A1ACF50087A54694F480344A8C5B5380951637801B840140701F9B01E75E820CCBFF83F9447A3498756D032608A966853E080A93E6F60C28D0CEC0D1D3768DC26BB3477F7BA5EA959BFEF2742F38AECD3087836AA5252EF2E90702E0E4FB48DEFE753BA50BAC8BFED552DC981222FD0CE4BC3F248B89F84FB271599C620EE5025DC1526E86CE20ECE43F9D0C43734C961FBF58283CB5EDE0724609E9DC0EC87863ED976029CFF8169932A443D112AD7610ABE89BB8408022148C8A4057EE346E9A53ED5368CA448042C3A0B312BA5154F33";
		String key = "21b4e0f1e42842f1bb66d29b6becf79c";
		String desStr = AESUtil.deCrypt(src, key);
		System.out.println("args = [" + desStr + "]");
	}
}
