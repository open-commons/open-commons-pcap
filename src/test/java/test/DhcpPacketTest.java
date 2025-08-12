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
 * Date  : 2020. 12. 15. 오후 3:07:45
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package test;

import org.pcap4j.packet.IllegalRawDataException;

import open.commons.core.utils.ByteUtils;
import open.commons.pcap.dhcp.DhcpPacket;

/**
 * 
 * @since 2020. 12. 15.
 * @version _._._
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DhcpPacketTest {

    /**
     * 
     * @since 2020. 12. 15.
     */
    public DhcpPacketTest() {
    }

    public static void main(String[] args) throws IllegalRawDataException {
        String dhcpPktHexStr = "020106009EA03D7A0000000000000000C0A8FC81C0A8FCFE00000000000C29AAC43B00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000638253633501053604C0A8FCFE3304000007080104FFFFFF001C04C0A8FCFF0304C0A8FC020F0B6C6F63616C646F6D61696E0604C0A8FC022C04C0A8FC02FF00";
        byte[] rawData = ByteUtils.hexBinStringToByteArray(dhcpPktHexStr);
        // DHCPPacket dhcpPkt = new DHCPPacket(rawData);
        // System.out.println(dhcpPkt);

        DhcpPacket packet = DhcpPacket.newPacket(rawData, 0, rawData.length);
        System.out.println(packet);
    }
}
