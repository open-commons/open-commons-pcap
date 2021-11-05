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
 * Date  : 2020. 12. 17. 오전 11:16:43
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.pcap.dhcp;

import java.util.HashMap;
import java.util.Map;

import org.pcap4j.packet.namednumber.NamedNumber;

import open.commons.utils.IntegerUtils;
import open.commons.utils.NumberUtils;

/**
 * DHCP Operation Code
 * 
 * @since 2020. 12. 17.
 * @version 0.1.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 * @see <a href="https://tools.ietf.org/html/rfc2131#page-8">RFC 2131</a>
 */
public class DhcpOpCode extends NamedNumber<Byte, DhcpOpCode> {

    private static final long serialVersionUID = 7652864601110102270L;

    /** REQUEST: 1 */
    public static final DhcpOpCode REQUEST = new DhcpOpCode((byte) 1, "BOOTREQUEST");
    /** REPLY: 2 */
    public static final DhcpOpCode REPLY = new DhcpOpCode((byte) 2, "BOOTREPLY");

    private static final Map<Byte, DhcpOpCode> registry = new HashMap<>();
    static {
        registry.put(REQUEST.value(), REQUEST);
        registry.put(REPLY.value(), REPLY);
    }

    /**
     * @param value
     * @param name
     * @since 2020. 12. 17.
     */
    public DhcpOpCode(Byte value, String name) {
        super(value, name);
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
     * @param o
     * @return
     *
     * @since 2020. 12. 17.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see org.pcap4j.packet.namednumber.NamedNumber#compareTo(org.pcap4j.packet.namednumber.NamedNumber)
     */
    @Override
    public int compareTo(DhcpOpCode o) {
        return value().compareTo(o.value());
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
     * @see org.pcap4j.packet.namednumber.NamedNumber#valueAsString()
     */
    @Override
    public String valueAsString() {
        return String.valueOf(value() & 0xFF);
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
     * @param value
     * @return
     *
     * @since 2020. 12. 17.
     * @version 0.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static DhcpOpCode getInstance(Byte value) {
        if (registry.containsKey(value)) {
            return registry.get(value);
        } else {
            return new DhcpOpCode(value, NumberUtils.hex(IntegerUtils.toHexString(value, 2)));
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
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @param opcode
     * @return
     *
     * @since 2020. 12. 17.
     * @version 0.1.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public static DhcpOpCode register(DhcpOpCode opcode) {
        return registry.put(opcode.value(), opcode);
    }

}
