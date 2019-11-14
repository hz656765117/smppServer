package com.hz.smsgate.base.smpp.pdu;

/*
 * #%L
 * ch-smpp
 * %%
 * Copyright (C) 2009 - 2015 Cloudhopper by Twitter
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

public class AlertNotification extends Pdu {

    protected Address sourceAddress;
    protected Address esmeAddress;

    public AlertNotification(){
        super( SmppConstants.CMD_ID_ALERT_NOTIFICATION, "alert_notification", true );
    }

    public Address getSourceAddress() {
        return this.sourceAddress;
    }

    public void setSourceAddress(Address value) {
        this.sourceAddress = value;
    }

    public Address getEsmeAddress() {
        return this.esmeAddress;
    }

    public void setEsmeAddress(Address value) {
        this.esmeAddress = value;
    }

    @Override
    protected int calculateByteSizeOfBody(){
        int bodyLength = 0;
        bodyLength += PduUtil.calculateByteSizeOfAddress(this.sourceAddress);
        bodyLength += PduUtil.calculateByteSizeOfAddress(this.esmeAddress);
        return bodyLength;
    }

    @Override
    public void readBody( ChannelBuffer buffer ) throws UnrecoverablePduException, RecoverablePduException {
        this.sourceAddress = ChannelBufferUtil.readAddress(buffer);
        this.esmeAddress = ChannelBufferUtil.readAddress(buffer);
    }

    @Override
    public void writeBody( ChannelBuffer buffer ) throws UnrecoverablePduException, RecoverablePduException{
        ChannelBufferUtil.writeAddress(buffer, this.sourceAddress);
        ChannelBufferUtil.writeAddress(buffer, this.esmeAddress);
    }

    @Override
    protected void appendBodyToString( StringBuilder buffer ){
        buffer.append("( sourceAddr [");
        buffer.append(StringUtil.toStringWithNullAsEmpty(this.sourceAddress));
        buffer.append("] esmeAddr [");
        buffer.append(StringUtil.toStringWithNullAsEmpty(this.esmeAddress));
        buffer.append("])");
    }

}
