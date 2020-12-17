/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.pcap.osi.application;

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

import open.commons.utils.ByteUtils;
import open.commons.utils.ExceptionUtils;

/**
 * 
 * @since 2020. 12. 16.
 * @version _._._
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class DhcpPacket extends AbstractPacket {

    private final DhcpHeader header;

    /**
     * <pre>
     * Message op code / message type.
     * 1 = BOOTREQUEST, 2 = BOOTREPLY
     * </pre>
     */
    private final DhcpOpCode op;

    /** Hardware address type, see ARP section in "Assigned Numbers" RFC; e.g., '1' = 10mb ethernet. */
    private final ArpHardwareType htype;
    /** Hardware address length (e.g. '6' for 10mb ethernet). */
    private final DhcpHardwareLength hlen;
    /** Client sets to zero, optionally used by relay agents when booting via a relay agent. */
    private final byte hops;
    /**
     * Transaction ID, a random number chosen by the client, used by the client and server to associate messages and
     * responses between a client and a server.
     */
    private final int xid;
    /** Filled in by client, seconds elapsed since client began address acquisition or renewal process. */
    private final short secs;
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
    private final short flags;
    /**
     * Client IP address; only filled in if client is in BOUND, RENEW or REBINDING state and can respond to ARP
     * requests.
     */
    private final String ciaddr;
    /** 'your' (client) IP address. */
    private final String yiaddr;
    /** IP address of next server to use in bootstrap; returned in DHCPOFFER, DHCPACK by server. */
    private final String siaddr;
    /** Relay agent IP address, used in booting via a relay agent. */
    private final String giaddr;
    /** Client hardware address. */
    private final String chaddr;
    /** Optional server host name, null terminated string. */
    private final ByteArrayString sname;
    /**
     * Boot file name, null terminated string; "generic" name or null in DHCPDISCOVER, fully qualified directory-path
     * name in DHCPOFFER.
     */
    private final ByteArrayString file;

    /** Optional parameters field. See the options documents for a list of defined options. */
    // private final byte[] options;
    // private final Packet payload;

    public DhcpPacket(byte[] rawData, int offset, int length) throws IllegalRawDataException {
        this.header = new DhcpHeader(rawData, offset, length);
        int payloadLen = rawData.length - header.length() - offset;
        if (payloadLen < 0) {
            throw ExceptionUtils.newException(IllegalRawDataException.class, "The length of payload seems to be wrong. raw.length=%,d, offset=%,d, header.length=%,d",
                    rawData.length, offset, header.length());
        }

        this.op = DhcpOpCode.getInstance(ByteArrays.getByte(header.op, 0));
        this.htype = ArpHardwareType.getInstance((short) ByteArrays.getByte(header.htype, 0));
        this.hlen = DhcpHardwareLength.getInstance(ByteArrays.getByte(header.hlen, 0));
        this.hops = ByteArrays.getByte(header.hops, 0);
        this.xid = ByteArrays.getInt(header.xid, 0);
        this.secs = ByteArrays.getShort(header.secs, 0);
        this.flags = ByteArrays.getShort(header.flags, 0);
        this.ciaddr = ByteUtils.toIPv4Expr(header.ciaddr);
        this.yiaddr = ByteUtils.toIPv4Expr(header.yiaddr);
        this.siaddr = ByteUtils.toIPv4Expr(header.siaddr);
        this.giaddr = ByteUtils.toIPv4Expr(header.giaddr);
        this.chaddr = ByteUtils.toMACExpr(header.chaddr);
        this.sname = new ByteArrayString(header.sname);
        this.file = new ByteArrayString(header.file);
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see org.pcap4j.packet.AbstractPacket#getPayload()
     */
    @Override
    public Packet getPayload() {
        return super.getPayload();
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see org.pcap4j.packet.AbstractPacket#toString()
     */
    @Override
    public String toString() {
        return super.toString();
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static DhcpPacket newPacket(byte[] rawData, int offset, int length) throws IllegalRawDataException {
        ByteArrays.validateBounds(rawData, offset, length);
        return new DhcpPacket(rawData, offset, length);
    }

    public static final class Builder extends AbstractBuilder implements LengthBuilder<DhcpPacket>, ChecksumBuilder<DhcpPacket> {

        public Builder(DhcpPacket packet) {

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
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         *
         * @see org.pcap4j.packet.LengthBuilder#build()
         */
        @Override
        public DhcpPacket build() {
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
         * @param correctChecksumAtBuild
         * @return
         *
         * @since 2020. 12. 16.
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         *
         * @see org.pcap4j.packet.ChecksumBuilder#correctChecksumAtBuild(boolean)
         */
        @Override
        public ChecksumBuilder<DhcpPacket> correctChecksumAtBuild(boolean correctChecksumAtBuild) {
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
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         *
         * @see org.pcap4j.packet.LengthBuilder#correctLengthAtBuild(boolean)
         */
        @Override
        public LengthBuilder<DhcpPacket> correctLengthAtBuild(boolean correctLengthAtBuild) {
            return null;
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public static final class DhcpHeader extends AbstractHeader {

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
        private final byte[] op;
        /**
         * Hardware address type, see ARP section in "Assigned Numbers" RFC; e.g., '1' = 10mb ethernet.
         * 
         * @see #HW_TYPE_HEADER_SIZE
         * @see #HW_TYPE_OFFSET
         */
        private final byte[] htype;
        /**
         * Hardware address length (e.g. '6' for 10mb ethernet).
         * 
         * @see #HW_ADDR_LENGTH_HEADER_SIZE
         * @see #HW_ADDR_LENGTH_OFFSET
         */
        private final byte[] hlen;
        /**
         * Client sets to zero, optionally used by relay agents when booting via a relay agent.
         * 
         * @see #HOPS_HEADER_SIZE
         * @see #HOPS_OFFSET
         */
        private final byte[] hops;
        /**
         * Transaction ID, a random number chosen by the client, used by the client and server to associate messages and
         * responses between a client and a server.
         * 
         * @see #TRANSACTION_ID_HEADER_SIZE
         * @see #TRANSACTION_ID_OFFSET
         */
        private final byte[] xid;
        /**
         * Filled in by client, seconds elapsed since client began address acquisition or renewal process.
         * 
         * @see #SECONDS_HEADER_SIZE
         * @see #SECONDS_OFFSET
         */
        private final byte[] secs;

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
        private final byte[] flags;
        /**
         * Client IP address; only filled in if client is in BOUND, RENEW or REBINDING state and can respond to ARP
         * requests.
         * 
         * @see #CLIENT_IP_ADDRESS_HEADER_SIZE
         * @see #CLIENT_IP_ADDRESS_OFFSET
         */
        private final byte[] ciaddr;
        /**
         * 'your' (client) IP address.
         * 
         * @see #YOUR_IP_ADDRESS_HEADER_SIZE
         * @see #YOUR_IP_ADDRESS_OFFSET
         */
        private final byte[] yiaddr;
        /**
         * IP address of next server to use in bootstrap; returned in DHCPOFFER, DHCPACK by server.
         * 
         * @see #SERVER_IP_ADDRESS_HEADER_SIZE
         * @see #SERVER_IP_ADDRESS_OFFSET
         */
        private final byte[] siaddr;
        /**
         * Relay agent IP address, used in booting via a relay agent.
         * 
         * @see #GATEWAY_IP_ADDRESS_HEADER_SIZE
         * @see #GATEWAY_IP_ADDRESS_OFFSET
         */
        private final byte[] giaddr;
        /**
         * Client hardware address.
         * 
         * @see #CLIENT_HW_ADDRESS_HEADER_SIZE
         * @see #CLIENT_HW_ADDRESS_OFFSET
         */
        private final byte[] chaddr;
        /**
         * Optional server host name, null terminated string.
         * 
         * @see #SERVER_NAMER_HEADER_SIZE
         * @see #SERVER_NAME_OFFSET
         */
        private final byte[] sname;
        /**
         * Boot file name, null terminated string; "generic" name or null in DHCPDISCOVER, fully qualified
         * directory-path name in DHCPOFFER.
         * 
         * @see #BOOT_FILE_NAME_HEADER_SIZE
         * @see #BOOT_FILE_NAME_OFFSET
         */
        private final byte[] file;

        /**
         * Optional parameters field. See the options documents for a list of defined options.
         * 
         * @see #OPTIONS_OFFSET
         */
        private final byte[] options;

        /** raw bytes array */
        private final byte[] rawData;

        private DhcpHeader(byte[] rawData, int offset, int length) throws IllegalRawDataException {
            if (length < OPTIONS_OFFSET) {
                StringBuilder sb = new StringBuilder(80);
                sb.append("The data is too short to build a DHCP header(").append(OPTIONS_OFFSET).append(" bytes). data: ").append(ByteArrays.toHexString(rawData, " "))
                        .append(", offset: ").append(offset).append(", length: ").append(length);
                throw new IllegalRawDataException(sb.toString());
            }

            this.rawData = Arrays.copyOfRange(rawData, offset, length);

            this.op = Arrays.copyOfRange(this.rawData, OP_CODE_OFFSET, HW_TYPE_OFFSET);
            this.htype = Arrays.copyOfRange(this.rawData, HW_TYPE_OFFSET, HW_ADDR_LENGTH_OFFSET);
            this.hlen = Arrays.copyOfRange(this.rawData, HW_ADDR_LENGTH_OFFSET, HOPS_OFFSET);
            this.hops = Arrays.copyOfRange(this.rawData, HOPS_OFFSET, TRANSACTION_ID_OFFSET);
            this.xid = Arrays.copyOfRange(this.rawData, TRANSACTION_ID_OFFSET, SECONDS_OFFSET);
            this.secs = Arrays.copyOfRange(this.rawData, SECONDS_OFFSET, FLAGS_OFFSET);
            this.flags = Arrays.copyOfRange(this.rawData, FLAGS_OFFSET, CLIENT_IP_ADDRESS_OFFSET);
            this.ciaddr = Arrays.copyOfRange(this.rawData, CLIENT_IP_ADDRESS_OFFSET, YOUR_IP_ADDRESS_OFFSET);
            this.yiaddr = Arrays.copyOfRange(this.rawData, YOUR_IP_ADDRESS_OFFSET, SERVER_IP_ADDRESS_OFFSET);
            this.siaddr = Arrays.copyOfRange(this.rawData, SERVER_IP_ADDRESS_OFFSET, GATEWAY_IP_ADDRESS_OFFSET);
            this.giaddr = Arrays.copyOfRange(this.rawData, GATEWAY_IP_ADDRESS_OFFSET, CLIENT_HW_ADDRESS_OFFSET);
            this.chaddr = Arrays.copyOfRange(this.rawData, CLIENT_HW_ADDRESS_OFFSET, SERVER_NAME_OFFSET);
            this.sname = Arrays.copyOfRange(this.rawData, SERVER_NAME_OFFSET, BOOT_FILE_NAME_OFFSET);
            this.file = Arrays.copyOfRange(this.rawData, BOOT_FILE_NAME_OFFSET, OPTIONS_OFFSET);
            this.options = Arrays.copyOf(this.rawData, OPTIONS_OFFSET);
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
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
            builder.append(String.format(FORMAT, "(op)", "OP Code", DhcpOpCode.getInstance(ByteArrays.getByte(this.op, 0))));
            builder.append(String.format(FORMAT, "(htype)", "H/W Type", ArpHardwareType.getInstance((short) ByteArrays.getByte(this.htype, 0))));
            builder.append(String.format(FORMAT, "(hlen)", "H/W Addr. Len", DhcpHardwareLength.getInstance(ByteArrays.getByte(this.hlen, 0))));
            builder.append(String.format(FORMAT, "(hops)", "HOPS", new ByteArrayInteger(this.hops)));
            builder.append(String.format(FORMAT, "(xid)", "Transaction ID", new ByteArrayInteger(this.xid)));
            builder.append(String.format(FORMAT, "(sec)", "Seconds", new ByteArrayInteger(this.secs)));
            builder.append(String.format(FORMAT, "(flags)", "Flags", new ByteArrayInteger(this.flags)));
            builder.append(String.format(FORMAT, "(ciaddr)", "Client IP", new ByteArrayString(this.ciaddr, ByteUtils::toIPv4Expr)));
            builder.append(String.format(FORMAT, "(yiaddr)", "Your IP", new ByteArrayString(this.yiaddr, ByteUtils::toIPv4Expr)));
            builder.append(String.format(FORMAT, "(siaddr)", "Server IP", new ByteArrayString(this.siaddr, ByteUtils::toIPv4Expr)));
            builder.append(String.format(FORMAT, "(giaddr)", "Gateway IP", new ByteArrayString(this.giaddr, ByteUtils::toIPv4Expr)));
            builder.append(String.format(FORMAT, "(chaddr)", "Client H/W", new ByteArrayString(this.chaddr, ByteUtils::toMACExpr)));
            builder.append(String.format(FORMAT, "(sname)", "Server Name", new ByteArrayString(this.sname)));
            builder.append(String.format(FORMAT, "(file)", "Boot File Name", new ByteArrayString(this.file)));

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
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         *
         * @see org.pcap4j.packet.AbstractPacket.AbstractHeader#getRawFields()
         */
        @Override
        protected List<byte[]> getRawFields() {
            List<byte[]> rawFields = new ArrayList<byte[]>();

            rawFields.add(Arrays.copyOf(this.op, 0));
            rawFields.add(Arrays.copyOf(this.htype, 0));
            rawFields.add(Arrays.copyOf(this.hlen, 0));
            rawFields.add(Arrays.copyOf(this.hops, 0));
            rawFields.add(Arrays.copyOf(this.xid, 0));
            rawFields.add(Arrays.copyOf(this.secs, 0));
            rawFields.add(Arrays.copyOf(this.flags, 0));
            rawFields.add(Arrays.copyOf(this.ciaddr, 0));
            rawFields.add(Arrays.copyOf(this.yiaddr, 0));
            rawFields.add(Arrays.copyOf(this.siaddr, 0));
            rawFields.add(Arrays.copyOf(this.giaddr, 0));
            rawFields.add(Arrays.copyOf(this.chaddr, 0));
            rawFields.add(Arrays.copyOf(this.sname, 0));
            rawFields.add(Arrays.copyOf(this.file, 0));

            return rawFields;
        }

        /**
         * 
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 12. 17.		박준홍			최초 작성
         * </pre>
         *
         * @param rawData
         *            데이터
         * @param offset
         *            읽을 기준 위치
         * @param from
         *            읽을 시작 위치
         * @param to
         *            읽을 끝 위치
         * @return
         *
         * @since 2020. 12. 17.
         * @version _._._
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         */
        private static byte[] copy(byte[] rawData, int offset, int from, int to) {
            return Arrays.copyOfRange(rawData, offset + from, offset + to);
        }
    }
}
