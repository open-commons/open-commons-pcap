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
 * Date  : 2020. 12. 15. 오후 4:16:53
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.pcap.listener;

import java.util.Collection;
import java.util.Objects;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.jspecify.annotations.Nullable;
import org.pcap4j.core.PacketListener;
import org.pcap4j.packet.ArpPacket;
import org.pcap4j.packet.ArpPacket.ArpHeader;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IllegalRawDataException;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.IpV6Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.TcpPacket.TcpHeader;
import org.pcap4j.packet.UdpPacket;
import org.pcap4j.packet.UdpPacket.UdpHeader;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.packet.namednumber.IpNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.core.concurrent.DefaultThreadFactory;
import open.commons.core.concurrent.FixedThreadPoolService;
import open.commons.core.utils.ByteUtils;
import open.commons.pcap.dhcp.DhcpPacket;

/**
 * 
 * @since 2020. 12. 15.
 * @version _._._
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DHCPListener implements PacketListener {

    @SuppressWarnings("null")
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final FixedThreadPoolService executor;

    private Vector<Consumer<DhcpPacket>> listeners = new Vector<>();

    /**
     * 
     * @since 2020. 12. 15.
     */
    public DHCPListener() {
        this(64, "dhcp-listener");
    }

    /**
     * @param executor
     * 
     * @since 2020. 12. 15.
     */
    public DHCPListener(FixedThreadPoolService executor) {
        Objects.requireNonNull(executor);

        this.executor = executor;
    }

    /**
     * @param poolSize
     *            ThreadPool Size
     * @param monitor
     *            Thread Group Name
     * 
     * @since 2020. 12. 15.
     */
    public DHCPListener(int poolSize, String monitor) {
        Objects.requireNonNull(monitor);

        this(new FixedThreadPoolService(poolSize, new DefaultThreadFactory(monitor)));
    }

    /**
     * DHCP Packet 리스너를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param listener
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public void addListener(Consumer<DhcpPacket> listener) {
        Objects.requireNonNull(listener);

        this.listeners.add(listener);
    }

    /**
     * DHCP Packet 리스너를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param listeners
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    public void addListeners(Collection<@Nullable Consumer<DhcpPacket>> listeners) {
        Objects.requireNonNull(listeners);

        this.listeners.addAll(listeners.stream().filter(Objects::nonNull).toList());
    }

    /**
     * DHCP Packet 리스너를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param listeners
     *
     * @since 2020. 12. 15.
     * @version 1.8.0
     */
    @SuppressWarnings("unchecked")
    public void addListeners(Consumer<DhcpPacket>... listeners) {
        Objects.requireNonNull(listeners);

        this.listeners.addAll(Stream.of(listeners).filter(Objects::nonNull).toList());
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param packet
     *
     * @since 2020. 12. 15.
     *
     * @see org.pcap4j.core.PacketListener#gotPacket(org.pcap4j.packet.Packet)
     */
    // 아래 내용에 적용됨.
    // - Packet.getRawData()
    // [PATCH] [3rdParty-Null] 외부 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public void gotPacket(Packet packet) {
        Objects.requireNonNull(packet);
        // logger.debug("Timestamp : {}", handle.getTimestamp());

        EthernetPacket ethPacket = (EthernetPacket) packet;

        EtherType type = ethPacket.getHeader().getType();
        switch (type.value()) {
            // IPV4
            case 0x0800:
                IpV4Packet ipv4Packet = (IpV4Packet) ethPacket.getPayload();
                IpNumber hdProtocol = ipv4Packet.getHeader().getProtocol();
                switch (hdProtocol.value()) {
                    // User Datagram (UDP): 17
                    case 0x0011: // IpNumber.UDP
                        UdpPacket udpPkt = (UdpPacket) ipv4Packet.getPayload();
                        UdpHeader udpHd = udpPkt.getHeader();
                        switch (udpHd.getSrcPort().valueAsInt()) {
                            // Bootstrap Protocol Server
                            case 67:
                            case 69:
                                logger.debug(">>>" + ByteUtils.hexBinString(udpPkt.getPayload().getRawData()) + "<< ");
                                byte[] udpPayload = udpPkt.getPayload().getRawData();
                                DhcpPacket dhcpPkt;
                                try {
                                    dhcpPkt = DhcpPacket.newPacket(udpPayload, 0, udpPayload.length);
                                    logger.debug("{}", dhcpPkt);
                                } catch (IllegalRawDataException e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                logger.debug(" * * * * * * * * NO DHCP * * * * * * * * ");
                                break;
                        }

                        break;
                    // Transmission Control (TCP): 6
                    case 0x0006: // IpNumber.TCP
                        TcpPacket tcpPkt = (TcpPacket) ipv4Packet.getPayload();
                        TcpHeader tcpHd = tcpPkt.getHeader();

                        logger.debug(" * * * * * * * * TCP Packet * * * * * * * * : {}", tcpHd.getSrcPort());
                        switch (tcpHd.getSrcPort().value()) {
                            // HTTPS: 443
                            case 443:
                                logger.debug("{}", tcpHd);
                                break;
                        }

                        break;
                }

                break;
            // IPv6
            case (short) 0x86dd:
                logger.debug(" * * * * * * * * IPv6 * * * * * * * * ");
                logger.debug("{}", ethPacket);

                IpV6Packet ipv6Packet = (IpV6Packet) ethPacket.getPayload();
                hdProtocol = ipv6Packet.getHeader().getProtocol();

                break;
            // ARP
            case 0x0806:
                logger.debug(" * * * * * * * * ARP * * * * * * * * ");
                logger.debug("{}", ethPacket);

                ArpPacket arpPacket = (ArpPacket) ethPacket.getPayload();
                ArpHeader arpHeader = arpPacket.getHeader();

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
     * 2020. 12. 15.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 15.
     */
    public boolean remove(Consumer<DhcpPacket> listener) {
        return this.listeners.remove(listener);
    }
}
