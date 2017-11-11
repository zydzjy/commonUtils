package yuzhou.gits.commonUtils;

import java.io.UnsupportedEncodingException;

public class BytesChunk {
	
	private byte[] bytesBuf = null;
	private int start = 0;
	private int end = -1;
	
	public BytesChunk(){
		
	}
	
	public final static BytesChunk EMPTY_CHUNK = new BytesChunk();
	
	public BytesChunk(byte[] bytesBuf,int start,int end){
		this.bytesBuf = bytesBuf;
		this.start = start;
		this.end = end;
	}

	public byte[] getBytesBuf() {
		return bytesBuf;
	}

	public void setBytesBuf(byte[] bytesBuf) {
		this.bytesBuf = bytesBuf;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	protected int hash = 0;
	public int hashCode() {
        int h = hash;
        if (h == 0 && this.end > this.start) {
            for (int i = this.start; i <= this.end; i++) {
                h = 31 * h + this.bytesBuf[i];
            }
            hash = h;
        }
        return h;
    }
	
	public int getLength(){
		return this.end - this.start + 1;
	}
	
	public boolean equals(Object other){
		BytesChunk _otherBytesChunk = (BytesChunk)other;
		boolean equals = false;
		if(this == other){
			equals = true;
		}
		else{
			int len1 = this.end - this.start + 1;
			int len2 = _otherBytesChunk.end - _otherBytesChunk.start + 1;
			int i=0;
			if(len1 == len2){
				for(;i<len1;i++){
					if(this.bytesBuf[i] != _otherBytesChunk.bytesBuf[i]){
						break;
					}
				}
				if(i==len1){
					equals = true;
				}
			}
		}
		return equals;
	}
	
	public String getString(String encode) throws UnsupportedEncodingException{
		return new String(this.bytesBuf,this.start,this.end - this.start + 1,encode);
	}
	
	public String toString(){
		return new String(this.bytesBuf,this.start,this.end-this.start + 1);
	}
}
