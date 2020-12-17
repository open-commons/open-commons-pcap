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
 * Date  : 2020. 12. 15. 오후 4:16:53
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.pcap.listener;

import java.util.Arrays;
import java.util.Collection;
import java.util.Vector;
import java.util.function.Consumer;

import org.pcap4j.core.PacketListener;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.UdpPacket;
import org.pcap4j.packet.UdpPacket.UdpHeader;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.packet.namednumber.IpNumber;

import open.commons.concurrent.DefaultThreadFactory;
import open.commons.concurrent.FixedThreadPoolService;
import open.commons.pcap.dhcp.DHCPPacket;
import open.commons.utils.ByteUtils;

/**
 * 
 * @since 2020. 12. 15.
 * @version _._._
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class DHCPListener implements PacketListener {

    private final FixedThreadPoolService executor;

    private Vector<Consumer<DHCPPacket>> listeners = new Vector<>();

    /**
     * 
     * @since 2020. 12. 15.
     */
    public DHCPListener() {
        this(64, "dhcp-listener");
    }

    /**
     * @param executor
     * @since 2020. 12. 15.
     */
    public DHCPListener(FixedThreadPoolService executor) {
        this.executor = executor;
    }

    /**
     * @param poolSize
     *            ThreadPool Size
     * @param monitor
     *            Thread Group Name
     * @since 2020. 12. 15.
     */
    public DHCPListener(int poolSize, String monitor) {
        this(new FixedThreadPoolService(poolSize, new DefaultThreadFactory(monitor)));
    }

    /**
     * DHCP Packet 리스너를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param listener
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public void addListener(Consumer<DHCPPacket> listener) {
        if (listener == null) {
            return;
        }

        if (this.listeners == null) {
            this.listeners = new Vector<>();
        }

        this.listeners.add(listener);
    }

    /**
     * DHCP Packet 리스너를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param listeners
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public void addListeners(Collection<Consumer<DHCPPacket>> listeners) {
        if (listeners == null) {
            return;
        }

        if (this.listeners == null) {
            this.listeners = new Vector<>();
        }

        this.listeners.addAll(listeners);
    }

    /**
     * DHCP Packet 리스너를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param listeners
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    @SuppressWarnings("unchecked")
    public void addListeners(Consumer<DHCPPacket>... listeners) {
        if (listeners == null) {
            return;
        }

        this.addListeners(Arrays.asList(listeners));
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param packet
     *
     * @since 2020. 12. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see org.pcap4j.core.PacketListener#gotPacket(org.pcap4j.packet.Packet)
     */
    @Override
    public void gotPacket(Packet packet) {
        // System.out.println("Timestamp : " + handle.getTimestamp());

        EthernetPacket ethPacket = (EthernetPacket) packet;

        EtherType type = ethPacket.getHeader().getType();
        switch (type.value()) {
            // IPV4
            case 0x0800:
                IpV4Packet ipv4Packet = (IpV4Packet) ethPacket.getPayload();
                IpNumber n = ipv4Packet.getHeader().getProtocol();
                switch (n.value()) {
                    // UDP
                    case 0x0011:
                        UdpPacket udpPkt = (UdpPacket) ipv4Packet.getPayload();
                        UdpHeader udpHd = udpPkt.getHeader();
                        switch (udpHd.getSrcPort().valueAsInt()) {
                            // Bootstrap Protocol Server
                            case 67:
                            case 69:
                                System.out.println(">>>" + ByteUtils.hexBinString(udpPkt.getPayload().getRawData()) + "<< ");
                                DHCPPacket dhcpPkt = new DHCPPacket(udpPkt.getPayload().getRawData());
                                System.out.println(dhcpPkt);
                                break;
                            default:
                                System.out.println(" * * * * * * * * NO DHCP * * * * * * * * ");
                                break;
                        }

                        break;
                }
                break;
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 15.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 15.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public boolean remove(Consumer<DHCPPacket> listener) {
        return this.listeners.remove(listener);
    }
}
