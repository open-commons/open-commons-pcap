/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2020. 12. 10. 오후 5:20:00
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package test;

import java.io.IOException;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.EthernetPacket.EthernetHeader;
import org.pcap4j.packet.IllegalRawDataException;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.Packet.Header;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.TcpPacket.TcpHeader;
import org.pcap4j.packet.UdpPacket;
import org.pcap4j.packet.UdpPacket.UdpHeader;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.packet.namednumber.IpNumber;
import org.pcap4j.util.NifSelector;

import open.commons.core.utils.ByteUtils;
import open.commons.pcap.dhcp.DhcpPacket;

/**
 * 
 * @since 2020. 12. 10.
 * @version _._._
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 * @origin https://www.devdungeon.com/content/packet-capturing-java-pcap4j
 */
public class App {

    static PcapNetworkInterface getNetworkDevice() {
        PcapNetworkInterface device = null;
        try {
            device = new NifSelector().selectNetworkInterface();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return device;
    }

    public static void main(String[] args) throws PcapNativeException, NotOpenException {
        // The code we had before
        PcapNetworkInterface device = getNetworkDevice();
        System.out.println("You chose: " + device);

        // New code below here
        if (device == null) {
            System.out.println("No device chosen.");
            System.exit(1);
        }

        // Open the device and get a handle
        int snapshotLength = 65536; // in bytes
        int readTimeout = 50; // in milliseconds
        final PcapHandle handle;
        handle = device.openLive(snapshotLength, PromiscuousMode.PROMISCUOUS, readTimeout);

        // Create a listener that defines what to do with the received packets
        PacketListener listener = new PacketListener() {
            @Override
            public void gotPacket(Packet packet) {
                // System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                // System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                System.out.println("Timestamp   : " + handle.getTimestamp());

                EthernetPacket ethPacket = (EthernetPacket) packet;

                EtherType type = ethPacket.getHeader().getType();
                // System.out.println(">>>>>>>>>>>>>>>>>>>>>>> " + type);
                // System.out.println(">>>>>>>>>>>>>>>>>>>>>>> " + type);
                // System.out.println(">>>>>>>>>>>>>>>>>>>>>>> " + type);

                switch (type.value()) {
                    case 0x0800: // EtherType.IPV4
                        IpV4Packet ipv4Packet = (IpV4Packet) ethPacket.getPayload();
                        IpNumber n = ipv4Packet.getHeader().getProtocol();
                        switch (n.value()) {
                            // User Datagram (UDP): 17
                            case 0x0011: // IpNumber.UDP
                                UdpPacket udpPkt = (UdpPacket) ipv4Packet.getPayload();
                                UdpHeader udpHd = udpPkt.getHeader();
                                switch (udpHd.getSrcPort().valueAsInt()) {
                                    case 67: // UdpPort.BOOTPS, Bootstrap Protocol Server
                                    case 68: // UdpPort.BOOTPC, Bootstrap Protocol Client
                                        System.out.println(">>>" + ByteUtils.hexBinString(udpPkt.getPayload().getRawData()) + "<< ");
                                        byte[] udpPayload = udpPkt.getPayload().getRawData();
                                        DhcpPacket dhcpPkt;
                                        try {
                                            dhcpPkt = DhcpPacket.newPacket(udpPayload, 0, udpPayload.length);
                                            System.out.println(dhcpPkt);
                                        } catch (IllegalRawDataException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    default:
                                        System.out.println(" * * * * * * * * NO DHCP * * * * * * * * ");
                                        System.out.println(udpHd);
                                        break;
                                }

                                break;
                            // Transmission Control (TCP): 6
                            case 0x0006: // IpNumber.TCP
                                TcpPacket tcpPkt = (TcpPacket) ipv4Packet.getPayload();
                                TcpHeader tcpHd = tcpPkt.getHeader();

                                System.out.println(" * * * * * * * * TCP Packet * * * * * * * * : " + tcpHd.getSrcPort());
                                switch (tcpHd.getSrcPort().value()) {
                                    // HTTPS: 443
                                    case 443:
                                        System.out.println(tcpHd);
                                        break;
                                }

                                break;
                        }
                        break;
                    // IPv6
                    case (short) 0x86dd:
                        System.out.println(" * * * * * * * * IPv6 * * * * * * * * ");
                        System.out.println(ethPacket);
                        break;
                    // ARP
                    case 0x0806:
                        System.out.println(" * * * * * * * * ARP * * * * * * * * ");
                        System.out.println(ethPacket);
                        break;
                }
                // Override the default gotPacket() function and process packet
                // showPacket(ethPacket, 2);
            }
        };

        // Tell the handle to loop using the listener we created
        try {
            int maxPackets = -1; // 50;
            handle.loop(maxPackets, listener);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Cleanup when complete
        handle.close();
    }

    private static void showPacket(Packet packet, int osiLevel) {
        Header header = packet.getHeader();
        if (header == null) {
            System.out.println(packet);
            return;
        }

        System.out.println("=-=-=-=-=-=-=-=-=-=-= OSI '" + osiLevel + "' Layer =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(String.format("Packet / Header = %s / %s ", packet.getClass(), header.getClass()));
        System.out.println(header);

        long t = 0x0800;
        switch (header.getClass().toString()) {
            case "class org.pcap4j.packet.EthernetPacket$EthernetHeader":
                EtherType type = ((EthernetHeader) header).getType();
                break;
            case "class org.pcap4j.packet.IpV4Packet$IpV4Header":
                break;
            case "class org.pcap4j.packet.TcpPacket$TcpHeader":
                break;
            case "class org.pcap4j.packet.UpdPacket$UpdHeader":
                break;
            default:
                break;
        }

        Packet payload = packet.getPayload();
        if (payload != null) {
            showPacket(payload, osiLevel + 1);
        } else {
            System.out.println("raw-data  : 0x" + ByteUtils.hexBinString(packet.getRawData()));
        }
    }
}
