package yuzhou.gits.commonUtils;

public class BytesUtils {

	public static boolean equals(byte[] bytes1, byte[] bytes2) {
		boolean equals = true;
		if (bytes1.length != bytes2.length)
			return false;
		for (int i = 0; i < bytes1.length; i++) {
			if (bytes1[i] != bytes2[i]) {
				equals = false;
				break;
			}
		}
		return equals;
	}
	
	public static byte[] longToByteArray(long s) {  
		int longLen = Long.BYTES;
        byte[] targets = new byte[longLen];
        for (int i = 0; i < longLen; i++) {  
            int offset = (targets.length - 1 - i) * longLen;  
            targets[i] = (byte) ((s >>> offset) & 0xff);  
        }  
        return targets;  
    }  

	public static int indexOf(byte[] src, int startOfSrc, byte[] pattern) {
		int idx = startOfSrc ;
		while (idx + pattern.length <= src.length) {
			int i = 0;
			for (; i < pattern.length; i++) {
				if (src[i + idx] != pattern[i]) {
					break;
				}
			}
			if (i == pattern.length) {// matched
				return idx;
			}
			idx++;
		}
		return -1;
	}
	
	public static int indexOf(BytesChunk src, int startOfSrc, byte[] pattern) {
		int idx = startOfSrc + src.getStart();
		byte[] data = src.getBytesBuf();
		while (idx + pattern.length < src.getEnd()) {
			int i = 0;
			for (; i < pattern.length; i++) {
				if (data[i + idx] != pattern[i]) {
					break;
				}
			}
			if (i == pattern.length) {// matched
				return idx-src.getStart();
			}
			idx++;
		}
		return -1;
	}
	
	public static void bytesChunkCopy(BytesChunk src,int startOfSrc,BytesChunk dest,
			int startOfDest,int length) throws ArrayIndexOutOfBoundsException{
		if(startOfSrc < src.getStart() || startOfSrc > src.getEnd() ||
				startOfDest < dest.getStart() || startOfDest > dest.getEnd()){
			throw new ArrayIndexOutOfBoundsException();
		}
		else{
			byte[] _src = src.getBytesBuf();
			byte[] _dest = dest.getBytesBuf();
			int _startOfSrc = src.getStart() + startOfSrc;
			int _destOfSrc = dest.getEnd() + startOfDest;
			System.arraycopy(_src, _startOfSrc, _dest, _destOfSrc, length);
		}
	}
	
	public static void bytesChunkCopy(BytesChunk src,int startOfSrc,byte[] dest,
			int startOfDest,int length) throws ArrayIndexOutOfBoundsException{
		/*if(startOfSrc < src.getStart() || startOfSrc > src.getEnd() ){
			throw new ArrayIndexOutOfBoundsException();
		}
		else*/{
			byte[] _src = src.getBytesBuf();
			int _startOfSrc = src.getStart() + startOfSrc;
			System.arraycopy(_src, _startOfSrc, dest, startOfDest, length);
		}
	}
	
	public static int lastIndexOf(byte[] src, byte[] pattern) {
		int idx = src.length - 1;
		while (idx > 0) {
			/*if (idx + pattern.length > src.length)
				return -1;*/
			int i = 0;
			for (; i < pattern.length; i++) {
				if (src[idx-i] != pattern[i]) {
					break;
				}
			}
			if (i == pattern.length) {// matched
				return idx;
			}
			idx--;
		}
		return -1;
	}

	public static void main(String[] args) {
		/*
		 * byte[] bytes1 = {'a','b','c','d','e','f'}; byte[] bytes2 =
		 * {'a','b','c','d','e','f'};
		 * 
		 * long s = System.currentTimeMillis(); for(int i=0;i<100000;i++){
		 * BytesUtils.equals(bytes1, bytes2);
		 * 
		 * //new String(bytes1).equals(new String(bytes2)); } long e =
		 * System.currentTimeMillis();
		 * System.out.println("time elpased:"+(e-s)/1000.f);
		 */

		byte[] bytes1 = { 'a', 'b', 'c', 'd', 'e', 'f' };
		byte[] bytes2 = { 'd', 'e', 'f' };
		long s = System.currentTimeMillis();
		/*for (int i = 0; i < 100000; i++) {
			BytesUtils.indexOf(bytes1, 0, bytes2);

			// new String(bytes1).indexOf(new String(bytes2));
		}*/
		long e = System.currentTimeMillis();
		System.out.println("time elpased:" + (e - s) / 1000.f);
	}

}
