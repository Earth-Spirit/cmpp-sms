package com.iwhalecloud.nmyd.cmppsms.cmpp;

import com.iwhalecloud.nmyd.cmppsms.cmpp.enums.CMPPCommandIdEnum;
import com.iwhalecloud.nmyd.cmppsms.cmpp.msg.CMPPHeader;
import com.iwhalecloud.nmyd.cmppsms.cmpp.util.CMPPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.DataOutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class CMPPSender implements Callable {

    private DataOutputStream out;
    private ConcurrentHashMap<Integer, Object> msgMap;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private boolean callback = false;

    private byte[] data;

    public CMPPSender(DataOutputStream out, ConcurrentHashMap msgMap, byte[] data, boolean callback) {
        this.out = out;
        this.msgMap = msgMap;
        this.data = data;
        this.callback = callback;
    }

    @Override
    public Object call() throws Exception {

        CMPPHeader header = new CMPPHeader(data);
        Integer sequenceId = header.getSequenceId();
        Integer length = header.getTotalLength();
        log.info("send msg {}, length={},[{}]", CMPPCommandIdEnum.getMessage(header.getCommandId()), header.getTotalLength(), CMPPUtil.toPrintHexString(data));
        out.write(data, 0, data.length);

        //如果不需要返回结果直接返回
        if(!callback){
            return null;
        }
        while (true) {

            if (msgMap.get(sequenceId) != null) {
                Object obj = msgMap.get(sequenceId);
                msgMap.remove(sequenceId);
                return obj;
            }
        }
    }
}
