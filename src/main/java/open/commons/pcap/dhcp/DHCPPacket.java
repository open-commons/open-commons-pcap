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
 * Date  : 2020. 12. 11. 오후 1:19:09
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.pcap.dhcp;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.pcap4j.packet.Packet.Header;

import open.commons.utils.ByteUtils;
import open.commons.utils.CollectionUtils;
import open.commons.utils.IntegerUtils;

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
 * @since 2020. 12. 11.
 * @version 0.1.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 *
 */
public class DHCPPacket {

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
     * Boot file name, null terminated string; "generic" name or null in DHCPDISCOVER, fully qualified directory-path
     * name in DHCPOFFER.
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

    /**
     * @param rawData
     * @since 2020. 12. 11.
     */
    public DHCPPacket(byte[] rawData) {
        this.rawData = rawData;

        this.op = Arrays.copyOfRange(rawData, OP_CODE_OFFSET, HW_TYPE_OFFSET);
        this.htype = Arrays.copyOfRange(rawData, HW_TYPE_OFFSET, HW_ADDR_LENGTH_OFFSET);
        this.hlen = Arrays.copyOfRange(rawData, HW_ADDR_LENGTH_OFFSET, HOPS_OFFSET);
        this.hops = Arrays.copyOfRange(rawData, HOPS_OFFSET, TRANSACTION_ID_OFFSET);
        this.xid = Arrays.copyOfRange(rawData, TRANSACTION_ID_OFFSET, SECONDS_OFFSET);
        this.secs = Arrays.copyOfRange(rawData, SECONDS_OFFSET, FLAGS_OFFSET);
        this.flags = Arrays.copyOfRange(rawData, FLAGS_OFFSET, CLIENT_IP_ADDRESS_OFFSET);
        this.ciaddr = Arrays.copyOfRange(rawData, CLIENT_IP_ADDRESS_OFFSET, YOUR_IP_ADDRESS_OFFSET);
        this.yiaddr = Arrays.copyOfRange(rawData, YOUR_IP_ADDRESS_OFFSET, SERVER_IP_ADDRESS_OFFSET);
        this.siaddr = Arrays.copyOfRange(rawData, SERVER_IP_ADDRESS_OFFSET, GATEWAY_IP_ADDRESS_OFFSET);
        this.giaddr = Arrays.copyOfRange(rawData, GATEWAY_IP_ADDRESS_OFFSET, CLIENT_HW_ADDRESS_OFFSET);
        this.chaddr = Arrays.copyOfRange(rawData, CLIENT_HW_ADDRESS_OFFSET, SERVER_NAME_OFFSET);
        this.sname = Arrays.copyOfRange(rawData, SERVER_NAME_OFFSET, BOOT_FILE_NAME_OFFSET);
        this.file = Arrays.copyOfRange(rawData, BOOT_FILE_NAME_OFFSET, OPTIONS_OFFSET);
        this.options = Arrays.copyOfRange(rawData, OPTIONS_OFFSET, rawData.length);
    }

    private String file(byte[] bytes) {
        StringBuffer sb = new StringBuffer(read(bytes));
        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    private String flags(byte[] bytes) {
        StringBuffer sb = new StringBuffer(String.valueOf(ByteUtils.toInt(bytes)));
        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    /**
     *
     * @return the chaddr
     *
     * @since 2020. 12. 11.
     */
    public byte[] getChaddr() {
        return chaddr;
    }

    /**
     *
     * @return the ciaddr
     *
     * @since 2020. 12. 11.
     */
    public byte[] getCiaddr() {
        return ciaddr;
    }

    /**
     *
     * @return the file
     *
     * @since 2020. 12. 11.
     */
    public byte[] getFile() {
        return file;
    }

    /**
     *
     * @return the flags
     *
     * @since 2020. 12. 11.
     */
    public byte[] getFlags() {
        return flags;
    }

    /**
     *
     * @return the giaddr
     *
     * @since 2020. 12. 11.
     */
    public byte[] getGiaddr() {
        return giaddr;
    }

    /**
     *
     * @return the hlen
     *
     * @since 2020. 12. 11.
     */
    public byte[] getHlen() {
        return hlen;
    }

    /**
     *
     * @return the hops
     *
     * @since 2020. 12. 11.
     */
    public byte[] getHops() {
        return hops;
    }

    /**
     *
     * @return the htype
     *
     * @since 2020. 12. 11.
     */
    public byte[] getHtype() {
        return htype;
    }

    /**
     *
     * @return the op
     *
     * @since 2020. 12. 11.
     */
    public byte[] getOp() {
        return op;
    }

    /**
     *
     * @return the options
     *
     * @since 2020. 12. 11.
     */
    public byte[] getOptions() {
        return options;
    }

    /**
     *
     * @return the rawData
     *
     * @since 2020. 12. 11.
     */
    public byte[] getRawData() {
        return rawData;
    }

    /**
     *
     * @return the secs
     *
     * @since 2020. 12. 11.
     */
    public byte[] getSecs() {
        return secs;
    }

    /**
     *
     * @return the siaddr
     *
     * @since 2020. 12. 11.
     */
    public byte[] getSiaddr() {
        return siaddr;
    }

    /**
     *
     * @return the sname
     *
     * @since 2020. 12. 11.
     */
    public byte[] getSname() {
        return sname;
    }

    /**
     *
     * @return the xid
     *
     * @since 2020. 12. 11.
     */
    public byte[] getXid() {
        return xid;
    }

    /**
     *
     * @return the yiaddr
     *
     * @since 2020. 12. 11.
     */
    public byte[] getYiaddr() {
        return yiaddr;
    }

    private String hlen(byte[] bytes) {
        StringBuffer sb = new StringBuffer(String.valueOf(ByteUtils.toInt(bytes)));
        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    private String hops(byte[] bytes) {
        StringBuffer sb = new StringBuffer(String.valueOf(ByteUtils.toInt(bytes)));
        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    /**
     * Reference: <a href="http://www.tcpipguide.com/free/aa1d8775.png">http://www.tcpipguide.com/free/aa1d8775.png</a>
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     * @return
     *
     * @since 2020. 12. 11.
     * @version 0.1.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    private String htype(byte[] bytes) {
        int value = ByteUtils.toInt(bytes);
        StringBuffer sb = new StringBuffer("");
        sb.append(value);
        switch (value) {
            case 1:
                sb.append(" (Ethernet (10 Mb))");
                break;
            case 6:
                sb.append(" (IEEE 802 Networks)");
                break;
            case 7:
                sb.append(" (ARCNET)");
                break;
            case 11:
                sb.append(" (LocalTalk)");
                break;
            case 12:
                sb.append(" (LocalNet (IBM PCNet or SYTEK LocalNET))");
                break;
            case 14:
                sb.append(" (SMDS)");
                break;
            case 15:
                sb.append(" (Frame Relay)");
                break;
            case 16:
                sb.append(" (Asynchronous Transfer Mode (ATM))");
                break;
            case 17:
                sb.append(" (HDLC)");
                break;
            case 18:
                sb.append(" (Fibre Channel)");
                break;
            case 19:
                sb.append(" (Asynchronous Transfer Mode (ATM))");
                break;
            case 20:
                sb.append(" (Serial Line)");
                break;
            default:
                break;
        }

        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    private String ipv4Addr(byte[] bytes) {
        List<String> str = CollectionUtils.toList(CollectionUtils.newList(bytes), b -> String.valueOf(ByteUtils.toInt(b)));

        StringBuffer sb = new StringBuffer(String.join(".", str));
        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    private String macAddr(byte[] bytes) {
        List<String> str = CollectionUtils.toList(CollectionUtils.newList(bytes), b -> IntegerUtils.toHexString(ByteUtils.toInt(b), 2));

        StringBuffer sb = new StringBuffer(String.join(":", str));
        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 11.		박준홍			최초 작성
     * </pre>
     *
     * @param bytes
     * @return
     *
     * @since 2020. 12. 11.
     * @version 0.1.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    private String op(byte[] bytes) {
        int value = ByteUtils.toInt(htype);
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        switch (value) {
            case 1:
                sb.append(" (DHCPREQUEST)");
                break;
            case 2:
                sb.append(" (DHCPRESPONSE)");
                break;
            default:
                sb.append(" (unknown)");
                break;
        }

        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    private String options(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        sb.append(bytes.length);
        sb.append(" bytes (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    private String read(byte[] bytes) {
        String str = new String(bytes, Charset.forName("UTF-8"));
        return str.trim().isEmpty() ? "" : str;
    }

    private String secs(byte[] bytes) {
        StringBuffer sb = new StringBuffer(String.valueOf(ByteUtils.toInt(bytes)));
        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    private String sname(byte[] bytes) {
        StringBuffer sb = new StringBuffer(read(bytes));
        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 11.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 11.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final String FORMAT = "  %-9s %-15s: %s\n";
        StringBuilder builder = new StringBuilder();
        builder.append("[DHCP Packet (");
        builder.append(this.rawData.length);
        builder.append(" bytes)]");
        builder.append("\n");
        builder.append(String.format(FORMAT, "(op)", "OP Code", op(this.op)));
        builder.append(String.format(FORMAT, "(htype)", "H/W Type", htype(this.htype)));
        builder.append(String.format(FORMAT, "(hlen)", "H/W Addr. Len", hlen(this.hlen)));
        builder.append(String.format(FORMAT, "(hops)", "HOPS", hops(this.hops)));
        builder.append(String.format(FORMAT, "(xid)", "Transaction ID", xid(this.xid)));
        builder.append(String.format(FORMAT, "(sec)", "Seconds", secs(this.secs)));
        builder.append(String.format(FORMAT, "(flags)", "Flags", flags(this.flags)));
        builder.append(String.format(FORMAT, "(ciaddr)", "Client IP", ipv4Addr(this.ciaddr)));
        builder.append(String.format(FORMAT, "(yiaddr)", "Your IP", ipv4Addr(this.yiaddr)));
        builder.append(String.format(FORMAT, "(siaddr)", "Server IP", ipv4Addr(this.siaddr)));
        builder.append(String.format(FORMAT, "(giaddr)", "Gateway IP", ipv4Addr(this.giaddr)));
        builder.append(String.format(FORMAT, "(chaddr)", "Client H/W", macAddr(this.chaddr)));
        builder.append(String.format(FORMAT, "(sname)", "Server Name", sname(this.sname)));
        builder.append(String.format(FORMAT, "(file)", "Boot File Name", file(this.file)));
        builder.append(String.format(FORMAT, "(options)", "Options", options(this.options)));

        return builder.toString();
    }

    private String xid(byte[] bytes) {
        StringBuffer sb = new StringBuffer(String.valueOf(ByteUtils.toInt(bytes)));
        sb.append(" (0x");
        sb.append(ByteUtils.hexBinString(bytes));
        sb.append(")");

        return sb.toString();
    }
    

    public static interface DHCPHeader extends Header {
        
    }
    
}
