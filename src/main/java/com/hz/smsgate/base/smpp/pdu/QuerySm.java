package com.hz.smsgate.base.smpp.pdu;

/*
 * #%L
 * ch-smpp
 * %%
 * Copyright (C) 2009 - 2013 Cloudhopper by Twitter
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.cloudhopper.commons.util.StringUtil;
import com.hz.smsgate.base.smpp.constants.SmppConstants;
import com.hz.smsgate.base.smpp.exception.RecoverablePduException;
import com.hz.smsgate.base.smpp.exception.UnrecoverablePduException;
import com.hz.smsgate.base.smpp.pojo.Address;
import com.hz.smsgate.base.smpp.utils.ChannelBufferUtil;
import com.hz.smsgate.base.smpp.utils.PduUtil;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * SMPP query_sm implementation.
 *
 * @author chris.matthews <idpromnut@gmail.com>
 */
public class QuerySm extends PduRequest<QuerySmResp> {

    private String messageId;
    private Address sourceAddress;

    public QuerySm() {
        super(SmppConstants.CMD_ID_QUERY_SM, "query_sm");
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String value) {
        this.messageId = value;
    }

    public Address getSourceAddress() {
        return this.sourceAddress;
    }

    public void setSourceAddress(Address value) {
        this.sourceAddress = value;
    }


    @Override
    public void readBody(ChannelBuffer buffer) throws UnrecoverablePduException, RecoverablePduException {
        this.messageId = ChannelBufferUtil.readNullTerminatedString(buffer);
        this.sourceAddress = ChannelBufferUtil.readAddress(buffer);
    }

    @Override
    public int calculateByteSizeOfBody() {
        int bodyLength = 0;
        bodyLength += PduUtil.calculateByteSizeOfNullTerminatedString(this.messageId);
        bodyLength += PduUtil.calculateByteSizeOfAddress(this.sourceAddress);
        return bodyLength;
    }

    @Override
    public void writeBody(ChannelBuffer buffer) throws UnrecoverablePduException, RecoverablePduException {
        ChannelBufferUtil.writeNullTerminatedString(buffer, this.messageId);
        ChannelBufferUtil.writeAddress(buffer, this.sourceAddress);
    }

    @Override
    public void appendBodyToString(StringBuilder buffer) {
        buffer.append("(messageId [");
        buffer.append(StringUtil.toStringWithNullAsEmpty(this.messageId));
        buffer.append("] sourceAddr [");
        buffer.append(StringUtil.toStringWithNullAsEmpty(this.sourceAddress));
        buffer.append("])");

    }

    @Override
    public QuerySmResp createResponse() {
        QuerySmResp resp = new QuerySmResp();
        resp.setSequenceNumber(this.getSequenceNumber());
        return resp;
    }

    @Override
    public Class<QuerySmResp> getResponseClass() {
        return QuerySmResp.class;
    }

}
