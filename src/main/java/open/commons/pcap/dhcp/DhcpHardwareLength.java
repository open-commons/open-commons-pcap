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
 * Date  : 2020. 12. 17. 오전 11:38:27
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.pcap.dhcp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.jspecify.annotations.Nullable;
import org.pcap4j.packet.namednumber.NamedNumber;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.IntegerUtils;
import open.commons.core.utils.NumberUtils;

/**
 * 
 * @since 2020. 12. 17.
 * @version _._._
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DhcpHardwareLength extends NamedNumber<Byte, DhcpHardwareLength> {

    private static final long serialVersionUID = -5603754988845346170L;

    public static final DhcpHardwareLength ETHERNET_10MB = new DhcpHardwareLength((byte) 6, "10mb ethernet");

    private static final Map<Byte, DhcpHardwareLength> registry = new HashMap<>();

    static {
        registry.put(ETHERNET_10MB.value(), ETHERNET_10MB);
    }

    /**
     * @param value
     * @param name
     * @since 2020. 12. 17.
     */
    protected DhcpHardwareLength(Byte value, String name) {
        AssertUtils2.notNulls(value, name);

        super(value, name);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 12. 17.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param o
     * @return
     *
     * @since 2020. 12. 17.
     *
     * @see org.pcap4j.packet.namednumber.NamedNumber#compareTo(org.pcap4j.packet.namednumber.NamedNumber)
     */
    @Override
    public int compareTo(@Nullable DhcpHardwareLength o) {
        if (o == null) {
            return -1;
        }
        return value().compareTo(o.value());
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 17.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 17.
     *
     * @see org.pcap4j.packet.namednumber.NamedNumber#valueAsString()
     */
    // 아래 내용에 적용됨.
    // - String.valueOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
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
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 17.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param value
     * @return
     *
     * @since 2020. 12. 17.
     * @version 0.1.0
     */
    // 아래 내용에 적용됨.
    // - Map.get(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static DhcpHardwareLength getInstance(Byte value) {
        Objects.requireNonNull(value);

        if (registry.containsKey(value)) {
            return registry.get(value);
        } else {
            return new DhcpHardwareLength(value, NumberUtils.hex(IntegerUtils.toHexString(value, 2)));
        }
    }

    /**
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 17.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param opcode
     * @return
     *
     * @since 2020. 12. 17.
     * @version 0.1.0
     */
    public static @Nullable DhcpHardwareLength register(DhcpHardwareLength opcode) {
        Objects.requireNonNull(opcode);

        return registry.put(opcode.value(), opcode);
    }

}
