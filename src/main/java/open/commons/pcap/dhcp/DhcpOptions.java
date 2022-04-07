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
 * Date  : 2020. 12. 17. 오후 4:02:23
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.pcap.dhcp;

import java.util.Arrays;

import org.pcap4j.packet.AbstractPacket;
import org.pcap4j.util.ByteArrays;

import open.commons.core.utils.ByteUtils;
import open.commons.pcap.raw.ByteArrayValue;

/**
 * 
 * @since 2020. 12. 17.
 * @version _._._
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DhcpOptions extends AbstractPacket {

    /**
     *
     * @since 2020. 12. 21.
     */
    private static final long serialVersionUID = 8447984915514600000L;
    private final byte[] rawData;

    /**
     * 
     * @since 2020. 12. 17.
     */
    private DhcpOptions(byte[] rawData, int offset, int length) {
        this.rawData = Arrays.copyOfRange(rawData, offset, offset + length);
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
     * @see org.pcap4j.packet.AbstractPacket#buildString()
     */
    @Override
    protected String buildString() {

        StringBuffer sb = new StringBuffer();

        sb.append("\n[DHCP Options (");
        sb.append(this.rawData.length);
        sb.append(" bytes)]");
        sb.append("\n  Hex stream: ");
        sb.append(ByteUtils.hexBinString(true, this.rawData));
        sb.append("\n  Readable: ");
        sb.append(ByteArrayValue.readAsString(this.rawData));

        return sb.toString();
    };

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
     * @see org.pcap4j.packet.AbstractPacket#getBuilder()
     */
    @Override
    public Builder getBuilder() {
        return null;
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
     * @see org.pcap4j.packet.AbstractPacket#getRawData()
     */
    @Override
    public byte[] getRawData() {
        return Arrays.copyOf(this.rawData, 0);
    }

    public static DhcpOptions newPacket(byte[] rawData, int offset, int length) {
        ByteArrays.validateBounds(rawData, offset, length);
        return new DhcpOptions(rawData, offset, length);
    }
}
