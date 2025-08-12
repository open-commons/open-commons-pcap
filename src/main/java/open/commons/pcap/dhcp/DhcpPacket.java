/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 *
 * This file is generated under this project, "open-commons-pcap".
 *
 * Date  : 2020. 12. 16. 오후 5:33:05
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.pcap.dhcp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.pcap4j.packet.AbstractPacket;
import org.pcap4j.packet.ChecksumBuilder;
import org.pcap4j.packet.IllegalRawDataException;
import org.pcap4j.packet.LengthBuilder;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.namednumber.ArpHardwareType;
import org.pcap4j.util.ByteArrays;

import open.commons.core.utils.ArrayUtils;
import open.commons.core.utils.ByteUtils;
import open.commons.core.utils.ExceptionUtils;
import open.commons.pcap.raw.ByteArrayInteger;
import open.commons.pcap.raw.ByteArrayMAC;
import open.commons.pcap.raw.ByteArrayString;

/**
 * 
 * @since 2020. 12. 16.
 * @version _._._
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DhcpPacket extends AbstractPacket {

    private static final long serialVersionUID = 6846747913243110164L;

    private final DhcpHeader header;
    private final DhcpOptions payload;

    private DhcpPacket(Builder builder) {
        this.header = new DhcpHeader(builder, builder.options);
        this.payload = builder.options != null //
                ? DhcpOptions.newPacket(builder.options, 0, builder.options.length) //
                : null;
    }

    private DhcpPacket(byte[] rawData, int offset, int length) throws IllegalRawDataException {
        this.header = new DhcpHeader(rawData, offset, length);
        int payloadLen = rawData.length - header.length() - offset;
        if (payloadLen < 0) {
            throw ExceptionUtils.newException(IllegalRawDataException.class, "The length of payload seems to be wrong. raw.length=%,d, offset=%,d, header.length=%,d",
                    rawData.length, offset, header.length());
        }

        this.payload = DhcpOptions.newPacket(rawData, offset + DhcpHeader.OPTIONS_OFFSET, length - DhcpHeader.OPTIONS_OFFSET);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 16.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.pcap4j.packet.AbstractPacket#getBuilder()
     */
    @Override
    public Builder getBuilder() {
        return new Builder(this);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.pcap4j.packet.AbstractPacket#getHeader()
     */
    @Override
    public Header getHeader() {
        return this.header;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.pcap4j.packet.AbstractPacket#getPayload()
     */
    @Override
    public Packet getPayload() {
        return this.payload;
    }

    /**
     * DHCP 패킷을 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param rawData
     *            raw data
     * @param offset
     *            offset
     * @param length
     *            length
     * @return a new DHCP Packet
     * @throws IllegalRawDataException
     *
     * @since 2020. 12. 16.
     * @version _._._
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static DhcpPacket newPacket(byte[] rawData, int offset, int length) throws IllegalRawDataException {
        ByteArrays.validateBounds(rawData, offset, length);
        return new DhcpPacket(rawData, offset, length);
    }

    public static final class Builder extends AbstractBuilder implements LengthBuilder<DhcpPacket>, ChecksumBuilder<DhcpPacket> {

        /**
         * <pre>
         * Message op code / message type.
         * 1 = BOOTREQUEST, 2 = BOOTREPLY
         * </pre>
         */
        private DhcpOpCode op;
        /**
         * Hardware address type, see ARP section in "Assigned Numbers" RFC; e.g., '1' = 10mb ethernet.
         */
        private ArpHardwareType htype;
        /**
         * Hardware address length (e.g. '6' for 10mb ethernet).
         */
        private DhcpHardwareLength hlen;
        /**
         * Client sets to zero, optionally used by relay agents when booting via a relay agent.
         */
        private ByteArrayInteger hops;
        /**
         * Transaction ID, a random number chosen by the client, used by the client and server to associate messages and
         * responses between a client and a server.
         */
        private ByteArrayInteger xid;
        /**
         * Filled in by client, seconds elapsed since client began address acquisition or renewal process.
         */
        private ByteArrayInteger secs;

        /**
         * <pre>
                              1 1 1 1 1 1
          0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5
          +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
          |B|             MBZ             |
          +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
        
          B:  BROADCAST flag
        
          MBZ:  MUST BE ZERO (reserved for future use)
        
          Figure 2:  Format of the 'flags' field
         * </pre>
         */
        private ByteArrayInteger flags;
        /**
         * Client IP address; only filled in if client is in BOUND, RENEW or REBINDING state and can respond to ARP
         * requests.
         */
        private ByteArrayString ciaddr;
        /**
         * 'your' (client) IP address.
         */
        private ByteArrayString yiaddr;
        /**
         * IP address of next server to use in bootstrap; returned in DHCPOFFER, DHCPACK by server.
         */
        private ByteArrayString siaddr;
        /**
         * Relay agent IP address, used in booting via a relay agent.
         */
        private ByteArrayString giaddr;
        /**
         * Client hardware address.
         */
        private ByteArrayMAC chaddr;
        /**
         * Optional server host name, null terminated string.
         */
        private ByteArrayString sname;
        /**
         * Boot file name, null terminated string; "generic" name or null in DHCPDISCOVER, fully qualified
         * directory-path name in DHCPOFFER.
         */
        private ByteArrayString file;

        /**
         * Optional parameters field. See the options documents for a list of defined options.<br>
         * payloadRaw bytes array
         */
        private byte[] options;

        public Builder(DhcpPacket packet) {
            this.op = packet.header.op;
            this.htype = packet.header.htype;
            this.hlen = packet.header.hlen;
            this.hops = packet.header.hops;
            this.xid = packet.header.xid;
            this.secs = packet.header.secs;
            this.flags = packet.header.flags;
            this.ciaddr = packet.header.ciaddr;
            this.yiaddr = packet.header.yiaddr;
            this.siaddr = packet.header.siaddr;
            this.giaddr = packet.header.giaddr;
            this.chaddr = packet.header.chaddr;
            this.sname = packet.header.sname;
            this.file = packet.header.file;
            this.options = packet.header.options;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 16.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2020. 12. 16.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see org.pcap4j.packet.LengthBuilder#build()
         */
        @Override
        public DhcpPacket build() {
            return new DhcpPacket(this);
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param chaddr
         *            the chaddr to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #chaddr
         */
        public Builder chaddr(ByteArrayMAC chaddr) {
            this.chaddr = chaddr;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param ciaddr
         *            the ciaddr to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #ciaddr
         */
        public Builder Ciaddr(ByteArrayString ciaddr) {
            this.ciaddr = ciaddr;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 16.		박준홍			최초 작성
         * </pre>
         *
         * @param correctChecksumAtBuild
         * @return
         *
         * @since 2020. 12. 16.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see org.pcap4j.packet.ChecksumBuilder#correctChecksumAtBuild(boolean)
         */
        @Override
        public ChecksumBuilder<DhcpPacket> correctChecksumAtBuild(boolean correctChecksumAtBuild) {
            // Not supported
            return null;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 16.		박준홍			최초 작성
         * </pre>
         *
         * @param correctLengthAtBuild
         * @return
         *
         * @since 2020. 12. 16.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see org.pcap4j.packet.LengthBuilder#correctLengthAtBuild(boolean)
         */
        @Override
        public LengthBuilder<DhcpPacket> correctLengthAtBuild(boolean correctLengthAtBuild) {
            // Not supported
            return null;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param file
         *            the file to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #file
         */
        public Builder file(ByteArrayString file) {
            this.file = file;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param flags
         *            the flags to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #flags
         */
        public Builder flags(ByteArrayInteger flags) {
            this.flags = flags;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param giaddr
         *            the giaddr to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #giaddr
         */
        public Builder giaddr(ByteArrayString giaddr) {
            this.giaddr = giaddr;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param hlen
         *            the hlen to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #hlen
         */
        public Builder hlen(DhcpHardwareLength hlen) {
            this.hlen = hlen;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param hops
         *            the hops to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #hops
         */
        public Builder hops(ByteArrayInteger hops) {
            this.hops = hops;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param htype
         *            the htype to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #htype
         */
        public Builder htype(ArpHardwareType htype) {
            this.htype = htype;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param xid
         *            the xid to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #xid
         */
        public Builder kid(ByteArrayInteger xid) {
            this.xid = xid;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param op
         *            the op to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #op
         */
        public Builder op(DhcpOpCode op) {
            this.op = op;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param options
         *            the options to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #options
         */
        public Builder options(byte[] options) {
            this.options = options;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param secs
         *            the secs to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #secs
         */
        public Builder secs(ByteArrayInteger secs) {
            this.secs = secs;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param siaddr
         *            the siaddr to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #siaddr
         */
        public Builder siaddr(ByteArrayString siaddr) {
            this.siaddr = siaddr;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param sname
         *            the sname to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #sname
         */
        public Builder sname(ByteArrayString sname) {
            this.sname = sname;
            return this;
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 23.		박준홍			최초 작성
         * </pre>
         *
         * @param yiaddr
         *            the yiaddr to set
         *
         * @since 2020. 12. 23.
         * 
         * @see #yiaddr
         */
        public Builder yiaddr(ByteArrayString yiaddr) {
            this.yiaddr = yiaddr;
            return this;
        }
    }

    /**
     * References:
     * <ul>
     * <li><a href="https://tools.ietf.org/html/rfc2131">https://tools.ietf.org/html/rfc2131</a>
     * <li><a href=
     * "http://www.tcpipguide.com/free/t_DHCPMessageFormat.htm">http://www.tcpipguide.com/free/t_DHCPMessageFormat.htm</a>
     * </ul>
     * 
     * <pre>
     *    0                   1                   2                   3
       0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
       +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
       |     op (1)    |   htype (1)   |   hlen (1)    |   hops (1)    |
       +---------------+---------------+---------------+---------------+
       |                            xid (4)                            |
       +-------------------------------+-------------------------------+
       |           secs (2)            |           flags (2)           |
       +-------------------------------+-------------------------------+
       |                          ciaddr  (4)                          |
       +---------------------------------------------------------------+
       |                          yiaddr  (4)                          |
       +---------------------------------------------------------------+
       |                          siaddr  (4)                          |
       +---------------------------------------------------------------+
       |                          giaddr  (4)                          |
       +---------------------------------------------------------------+
       |                                                               |
       |                          chaddr  (16)                         |
       |                                                               |
       |                                                               |
       +---------------------------------------------------------------+
       |                                                               |
       |                          sname   (64)                         |
       +---------------------------------------------------------------+
       |                                                               |
       |                          file    (128)                        |
       +---------------------------------------------------------------+
       |                                                               |
       |                          options (variable)                   |
       +---------------------------------------------------------------+
    
                      Figure 1:  Format of a DHCP message
     * </pre>
     * 
     * 
     * <pre>
     *    FIELD      OCTETS       DESCRIPTION
       -----      ------       -----------
    
       op            1  Message op code / message type.
                        1 = BOOTREQUEST, 2 = BOOTREPLY
       htype         1  Hardware address type, see ARP section in "Assigned
                        Numbers" RFC; e.g., '1' = 10mb ethernet.
       hlen          1  Hardware address length (e.g.  '6' for 10mb
                        ethernet).
       hops          1  Client sets to zero, optionally used by relay agents
                        when booting via a relay agent.
       xid           4  Transaction ID, a random number chosen by the
                        client, used by the client and server to associate
                        messages and responses between a client and a
                        server.
       secs          2  Filled in by client, seconds elapsed since client
                        began address acquisition or renewal process.
       flags         2  Flags (see figure 2).
       ciaddr        4  Client IP address; only filled in if client is in
                        BOUND, RENEW or REBINDING state and can respond
                        to ARP requests.
       yiaddr        4  'your' (client) IP address.
       siaddr        4  IP address of next server to use in bootstrap;
                        returned in DHCPOFFER, DHCPACK by server.
       giaddr        4  Relay agent IP address, used in booting via a
                        relay agent.
       chaddr       16  Client hardware address.
       sname        64  Optional server host name, null terminated string.
       file        128  Boot file name, null terminated string; "generic"
                        name or null in DHCPDISCOVER, fully qualified
                        directory-path name in DHCPOFFER.
       options     var  Optional parameters field.  See the options
                        documents for a list of defined options.
    
               Table 1:  Description of fields in a DHCP message
     * </pre>
     * 
     * @since 2020. 12. 16.
     * @version 0.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static final class DhcpHeader extends AbstractHeader {

        private static final long serialVersionUID = 2776739875025709004L;

        /** Header Length: OP Code (op) / 1 bytes (8 bits) */
        public static final int OP_CODE_HEADER_SIZE = 0x01;
        /** Header Length: Hardware Type (htype) / 1 bytes (8 bits) */
        public static final int HW_TYPE_HEADER_SIZE = 0x01;
        /** Header Length: Hardware Address Length (hlen) / 1 bytes (8 bits) */
        public static final int HW_ADDR_LENGTH_HEADER_SIZE = 0x01;
        /** Header Length: HOPS (hops) / 1 bytes (8 bits) */
        public static final int HOPS_HEADER_SIZE = 0x01;
        /** Header Length: Transaction ID (xid) / 4 bytes (32 bits) */
        public static final int TRANSACTION_ID_HEADER_SIZE = 0x04;
        /** Header Length: Seconds (secs) / 2 bytes (16 bits) */
        public static final int SECONDS_HEADER_SIZE = 0x02;
        /** Header Length: Flags (flags( / 2 bytes (16 bits) */
        public static final int FLAGS_HEADER_SIZE = 0x02;
        /** Header Length: Client IP Address (ciaddr) / 4 bytes (32 bits) */
        public static final int CLIENT_IP_ADDRESS_HEADER_SIZE = 0x04;
        /** Header Length: Your IP Address (yiaddr) / 4 bytes (32 bits) */
        public static final int YOUR_IP_ADDRESS_HEADER_SIZE = 0x04;
        /** Header Length: Server IP Address (siaddr) / 4 bytes (32 bits) */
        public static final int SERVER_IP_ADDRESS_HEADER_SIZE = 0x04;
        /** Header Length: Gateway IP Address (giaddr) / 4 bytes (32 bits) */
        public static final int GATEWAY_IP_ADDRESS_HEADER_SIZE = 0x04;
        /** Header Length: Client Hardware Address (chaddr) / 16 bytes (128 bites) */
        public static final int CLIENT_HW_ADDRESS_HEADER_SIZE = 0x10;
        /** Header Length: Server Name (sname) (sname) / 64 bytes (512 bits) */
        public static final int SERVER_NAME_HEADER_SIZE = 0x40;
        /** Header Length: Boot File Name (file) / 128 bytes (1,024 bits) */
        public static final int BOOT_FILE_NAME_HEADER_SIZE = 0x80;

        /** Header Index: OP Code (op) / 1 bytes (8 bits) */
        public static final int OP_CODE_OFFSET = 0x00;
        /** Header Index: Hardware Type (htype) / 1 bytes (8 bits) */
        public static final int HW_TYPE_OFFSET = OP_CODE_OFFSET + OP_CODE_HEADER_SIZE;
        /** Header Index: Hardware Address Length (hlen) / 1 bytes (8 bits) */
        public static final int HW_ADDR_LENGTH_OFFSET = HW_TYPE_OFFSET + HW_TYPE_HEADER_SIZE;
        /** Header Index: HOPS (hops) / 1 bytes 8 bits (1 bytes) */
        public static final int HOPS_OFFSET = HW_ADDR_LENGTH_OFFSET + HW_ADDR_LENGTH_HEADER_SIZE;
        /** Header Index: Transaction ID (xid) / 4 bytes (32 bits) */
        public static final int TRANSACTION_ID_OFFSET = HOPS_OFFSET + HOPS_HEADER_SIZE;
        /** Header Index: Seconds (secs) / 2 bytes (16 bits) */
        public static final int SECONDS_OFFSET = TRANSACTION_ID_OFFSET + TRANSACTION_ID_HEADER_SIZE;
        /** Header Index: Flags (flags( / 2 bytes (16 bits) */
        public static final int FLAGS_OFFSET = SECONDS_OFFSET + SECONDS_HEADER_SIZE;
        /** Header Index: Client IP Address (ciaddr) / 4 bytes (32 bits) */
        public static final int CLIENT_IP_ADDRESS_OFFSET = FLAGS_OFFSET + FLAGS_HEADER_SIZE;
        /** Header Index: Your IP Address (yiaddr) / 4 bytes (32 bits) */
        public static final int YOUR_IP_ADDRESS_OFFSET = CLIENT_IP_ADDRESS_OFFSET + CLIENT_IP_ADDRESS_HEADER_SIZE;
        /** Header Index: Server IP Address (siaddr) / 4 bytes (32 bits) */
        public static final int SERVER_IP_ADDRESS_OFFSET = YOUR_IP_ADDRESS_OFFSET + YOUR_IP_ADDRESS_HEADER_SIZE;
        /** Header Index: Gateway IP Address (giaddr) / 4 bytes (32 bits) */
        public static final int GATEWAY_IP_ADDRESS_OFFSET = SERVER_IP_ADDRESS_OFFSET + SERVER_IP_ADDRESS_HEADER_SIZE;
        /** Header Index: Client Hardware Address (chaddr) / 16 bytes (128 bites) */
        public static final int CLIENT_HW_ADDRESS_OFFSET = GATEWAY_IP_ADDRESS_OFFSET + GATEWAY_IP_ADDRESS_HEADER_SIZE;
        /** Header Index: Server Name (sname) (sname) / 64 bytes (512 bits) */
        public static final int SERVER_NAME_OFFSET = CLIENT_HW_ADDRESS_OFFSET + CLIENT_HW_ADDRESS_HEADER_SIZE;
        /** Header Index: Boot File Name (file) / 128 bytes (1,024 bits) */
        public static final int BOOT_FILE_NAME_OFFSET = SERVER_NAME_OFFSET + SERVER_NAME_HEADER_SIZE;
        /** Header Index: Options (options) / up to ... */
        public static final int OPTIONS_OFFSET = BOOT_FILE_NAME_OFFSET + BOOT_FILE_NAME_HEADER_SIZE;

        /**
         * <pre>
         * Message op code / message type.
         * 1 = BOOTREQUEST, 2 = BOOTREPLY
         * </pre>
         * 
         * @see #OP_CODE_HEADER_SIZE
         * @see #OP_CODE_OFFSET
         */
        private final DhcpOpCode op;
        /**
         * Hardware address type, see ARP section in "Assigned Numbers" RFC; e.g., '1' = 10mb ethernet.
         * 
         * @see #HW_TYPE_HEADER_SIZE
         * @see #HW_TYPE_OFFSET
         */
        private final ArpHardwareType htype;
        /**
         * Hardware address length (e.g. '6' for 10mb ethernet).
         * 
         * @see #HW_ADDR_LENGTH_HEADER_SIZE
         * @see #HW_ADDR_LENGTH_OFFSET
         */
        private final DhcpHardwareLength hlen;
        /**
         * Client sets to zero, optionally used by relay agents when booting via a relay agent.
         * 
         * @see #HOPS_HEADER_SIZE
         * @see #HOPS_OFFSET
         */
        private final ByteArrayInteger hops;
        /**
         * Transaction ID, a random number chosen by the client, used by the client and server to associate messages and
         * responses between a client and a server.
         * 
         * @see #TRANSACTION_ID_HEADER_SIZE
         * @see #TRANSACTION_ID_OFFSET
         */
        private final ByteArrayInteger xid;
        /**
         * Filled in by client, seconds elapsed since client began address acquisition or renewal process.
         * 
         * @see #SECONDS_HEADER_SIZE
         * @see #SECONDS_OFFSET
         */
        private final ByteArrayInteger secs;

        /**
         * <pre>
                              1 1 1 1 1 1
          0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5
          +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
          |B|             MBZ             |
          +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
        
          B:  BROADCAST flag
        
          MBZ:  MUST BE ZERO (reserved for future use)
        
          Figure 2:  Format of the 'flags' field
         * </pre>
         * 
         * @see #FLAGS_HEADER_SIZE
         * @see #FLAGS_OFFSET
         */
        private final ByteArrayInteger flags;
        /**
         * Client IP address; only filled in if client is in BOUND, RENEW or REBINDING state and can respond to ARP
         * requests.
         * 
         * @see #CLIENT_IP_ADDRESS_HEADER_SIZE
         * @see #CLIENT_IP_ADDRESS_OFFSET
         */
        private final ByteArrayString ciaddr;
        /**
         * 'your' (client) IP address.
         * 
         * @see #YOUR_IP_ADDRESS_HEADER_SIZE
         * @see #YOUR_IP_ADDRESS_OFFSET
         */
        private final ByteArrayString yiaddr;
        /**
         * IP address of next server to use in bootstrap; returned in DHCPOFFER, DHCPACK by server.
         * 
         * @see #SERVER_IP_ADDRESS_HEADER_SIZE
         * @see #SERVER_IP_ADDRESS_OFFSET
         */
        private final ByteArrayString siaddr;
        /**
         * Relay agent IP address, used in booting via a relay agent.
         * 
         * @see #GATEWAY_IP_ADDRESS_HEADER_SIZE
         * @see #GATEWAY_IP_ADDRESS_OFFSET
         */
        private final ByteArrayString giaddr;
        /**
         * Client hardware address.
         * 
         * @see #CLIENT_HW_ADDRESS_HEADER_SIZE
         * @see #CLIENT_HW_ADDRESS_OFFSET
         */
        private final ByteArrayMAC chaddr;
        /**
         * Optional server host name, null terminated string.
         * 
         * @see #SERVER_NAMER_HEADER_SIZE
         * @see #SERVER_NAME_OFFSET
         */
        private final ByteArrayString sname;
        /**
         * Boot file name, null terminated string; "generic" name or null in DHCPDISCOVER, fully qualified
         * directory-path name in DHCPOFFER.
         * 
         * @see #BOOT_FILE_NAME_HEADER_SIZE
         * @see #BOOT_FILE_NAME_OFFSET
         */
        private final ByteArrayString file;

        /**
         * Optional parameters field. See the options documents for a list of defined options.
         * 
         * @see #OPTIONS_OFFSET
         */
        private final byte[] options;

        /** raw bytes array */
        private final byte[] rawData;

        private DhcpHeader(Builder builder, byte[] payload) {

            this.op = builder.op;
            this.htype = builder.htype;
            this.hlen = builder.hlen;
            this.hops = builder.hops;
            this.xid = builder.xid;
            this.secs = builder.secs;
            this.flags = builder.flags;
            this.ciaddr = builder.ciaddr;
            this.yiaddr = builder.yiaddr;
            this.siaddr = builder.siaddr;
            this.giaddr = builder.giaddr;
            this.chaddr = builder.chaddr;
            this.sname = builder.sname;
            this.file = builder.file;
            this.options = payload;

            this.rawData = ArrayUtils.merge( //
                    new byte[] { this.op.value() } //
                    , ByteArrays.toByteArray(this.htype.value()) //
                    , new byte[] { this.hlen.value() } //
                    , this.hops.getRawData() //
                    , this.xid.getRawData() //
                    , this.secs.getRawData() //
                    , this.flags.getRawData() //
                    , this.ciaddr.getRawData() //
                    , this.yiaddr.getRawData() //
                    , this.siaddr.getRawData() //
                    , this.giaddr.getRawData() //
                    , this.chaddr.getRawData() //
                    , this.sname.getRawData() //
                    , this.file.getRawData() //
                    , payload //
            );
        }

        private DhcpHeader(byte[] rawData, int offset, int length) throws IllegalRawDataException {
            if (length < OPTIONS_OFFSET) {
                StringBuilder sb = new StringBuilder(80);
                sb.append("The data is too short to build a DHCP header(").append(OPTIONS_OFFSET).append(" bytes). data: ").append(ByteArrays.toHexString(rawData, " "))
                        .append(", offset: ").append(offset).append(", length: ").append(length);
                throw new IllegalRawDataException(sb.toString());
            }

            this.rawData = Arrays.copyOfRange(rawData, offset, offset + length);

            this.op = DhcpOpCode.getInstance(ByteArrays.getByte(Arrays.copyOfRange(this.rawData, OP_CODE_OFFSET, HW_TYPE_OFFSET), 0));
            this.htype = ArpHardwareType.getInstance((short) ByteArrays.getByte(Arrays.copyOfRange(this.rawData, HW_TYPE_OFFSET, HW_ADDR_LENGTH_OFFSET), 0));
            this.hlen = DhcpHardwareLength.getInstance(ByteArrays.getByte(Arrays.copyOfRange(this.rawData, HW_ADDR_LENGTH_OFFSET, HOPS_OFFSET), 0));
            this.hops = new ByteArrayInteger(Arrays.copyOfRange(this.rawData, HOPS_OFFSET, TRANSACTION_ID_OFFSET));
            this.xid = new ByteArrayInteger(Arrays.copyOfRange(this.rawData, TRANSACTION_ID_OFFSET, SECONDS_OFFSET));
            this.secs = new ByteArrayInteger(Arrays.copyOfRange(this.rawData, SECONDS_OFFSET, FLAGS_OFFSET));
            this.flags = new ByteArrayInteger(Arrays.copyOfRange(this.rawData, FLAGS_OFFSET, CLIENT_IP_ADDRESS_OFFSET));
            this.ciaddr = new ByteArrayString(Arrays.copyOfRange(this.rawData, CLIENT_IP_ADDRESS_OFFSET, YOUR_IP_ADDRESS_OFFSET), ByteUtils::toIPv4Expr);
            this.yiaddr = new ByteArrayString(Arrays.copyOfRange(this.rawData, YOUR_IP_ADDRESS_OFFSET, SERVER_IP_ADDRESS_OFFSET), ByteUtils::toIPv4Expr);
            this.siaddr = new ByteArrayString(Arrays.copyOfRange(this.rawData, SERVER_IP_ADDRESS_OFFSET, GATEWAY_IP_ADDRESS_OFFSET), ByteUtils::toIPv4Expr);
            this.giaddr = new ByteArrayString(Arrays.copyOfRange(this.rawData, GATEWAY_IP_ADDRESS_OFFSET, CLIENT_HW_ADDRESS_OFFSET), ByteUtils::toIPv4Expr);
            this.chaddr = new ByteArrayMAC(Arrays.copyOfRange(this.rawData, CLIENT_HW_ADDRESS_OFFSET, SERVER_NAME_OFFSET));
            this.sname = new ByteArrayString(Arrays.copyOfRange(this.rawData, SERVER_NAME_OFFSET, BOOT_FILE_NAME_OFFSET));
            this.file = new ByteArrayString(Arrays.copyOfRange(this.rawData, BOOT_FILE_NAME_OFFSET, OPTIONS_OFFSET));
            this.options = Arrays.copyOfRange(this.rawData, OPTIONS_OFFSET, this.rawData.length);
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 17.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2020. 12. 17.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see org.pcap4j.packet.AbstractPacket.AbstractHeader#buildString()
         */
        @Override
        protected String buildString() {
            final String FORMAT = "  %-9s %-15s: %s\n";
            StringBuilder builder = new StringBuilder();
            builder.append("[DHCP Packet (");
            builder.append(this.rawData.length);
            builder.append(" bytes)]");
            builder.append("\n");
            builder.append(String.format(FORMAT, "(op)", "OP Code", this.op));
            builder.append(String.format(FORMAT, "(htype)", "H/W Type", this.htype));
            builder.append(String.format(FORMAT, "(hlen)", "H/W Addr. Len", this.hlen));
            builder.append(String.format(FORMAT, "(hops)", "HOPS", this.hops));
            builder.append(String.format(FORMAT, "(xid)", "Transaction ID", this.xid));
            builder.append(String.format(FORMAT, "(sec)", "Seconds", this.secs));
            builder.append(String.format(FORMAT, "(flags)", "Flags", this.flags));
            builder.append(String.format(FORMAT, "(ciaddr)", "Client IP", this.ciaddr));
            builder.append(String.format(FORMAT, "(yiaddr)", "Your IP", this.yiaddr));
            builder.append(String.format(FORMAT, "(siaddr)", "Server IP", this.siaddr));
            builder.append(String.format(FORMAT, "(giaddr)", "Gateway IP", this.giaddr));
            builder.append(String.format(FORMAT, "(chaddr)", "Client H/W", this.chaddr));
            builder.append(String.format(FORMAT, "(sname)", "Server Name", this.sname));
            builder.append(String.format(FORMAT, "(file)", "Boot File Name", this.file));
            builder.append(String.format(FORMAT, "(options)", "Options", ByteUtils.hexBinString("0x", this.options)));

            return builder.toString();
        }

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 16.		박준홍			최초 작성
         * </pre>
         *
         * @return
         *
         * @since 2020. 12. 16.
         * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
         *
         * @see org.pcap4j.packet.AbstractPacket.AbstractHeader#getRawFields()
         */
        @Override
        protected List<byte[]> getRawFields() {
            List<byte[]> rawFields = new ArrayList<byte[]>();

            rawFields.add(Arrays.copyOfRange(this.rawData, OP_CODE_OFFSET, HW_TYPE_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, HW_TYPE_OFFSET, HW_ADDR_LENGTH_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, HW_ADDR_LENGTH_OFFSET, HOPS_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, HOPS_OFFSET, TRANSACTION_ID_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, TRANSACTION_ID_OFFSET, SECONDS_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, SECONDS_OFFSET, FLAGS_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, FLAGS_OFFSET, CLIENT_IP_ADDRESS_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, CLIENT_IP_ADDRESS_OFFSET, YOUR_IP_ADDRESS_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, YOUR_IP_ADDRESS_OFFSET, SERVER_IP_ADDRESS_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, SERVER_IP_ADDRESS_OFFSET, GATEWAY_IP_ADDRESS_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, GATEWAY_IP_ADDRESS_OFFSET, CLIENT_HW_ADDRESS_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, CLIENT_HW_ADDRESS_OFFSET, SERVER_NAME_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, SERVER_NAME_OFFSET, BOOT_FILE_NAME_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, BOOT_FILE_NAME_OFFSET, OPTIONS_OFFSET));
            rawFields.add(Arrays.copyOfRange(this.rawData, OPTIONS_OFFSET, this.rawData.length));

            return rawFields;
        }
    }
}
