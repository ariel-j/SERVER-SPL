package bgu.spl.net.packets;

public class DATA extends Packet{
    short blockNum;
    short pactSize;
    byte[] data;
    
    public DATA (byte[] bytes){
        this.len = bytes.length;
        this.bytes = bytes;
        this.pactSize = byteArrayToShort(new byte[]{bytes[2], bytes[3]});
        this.blockNum = byteArrayToShort(new byte[]{bytes[4], bytes[5]});
        this.data = new byte[len-6];
        setData();
    }

    public DATA(byte[] data, short blockNum){
        this.len = 0;
        this.bytes = new byte[data.length + 6];
        this.blockNum = blockNum;
        this.pactSize = (short)data.length;
        this.data = data;
        // Building byte array for the all packet
        buildByteArray();
        
    }
    public short getBlockNum() {
        return this.blockNum;
    }

    public short getpactSize() {
        return this.pactSize;
    }

    public byte[] getData() {
        return this.data;
    }

    public byte[] getByteArray(){
        return bytes;
    }
    
    private void setData() {
        for(int i=6; i<len; i++) data[i-6] = bytes[i];
    }

    private void buildByteArray() {
         //opCode
         enterBytes(2,new byte[]{0,3});
         //packetSize
         enterBytes(2, shortToByteArray(pactSize));
         //blockNumber 
         enterBytes(2, shortToByteArray(blockNum));
         //data
         enterBytes(pactSize, data);
    }
}
