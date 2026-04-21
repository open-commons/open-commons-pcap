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
 * Date  : 2020. 12. 17. 오후 2:45:51
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.pcap.raw;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;

import open.commons.core.utils.ByteUtils;

/**
 * 
 * @since 2020. 12. 17.
 * @version _._._
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public abstract class ByteArrayValue<T> {

    protected final byte[] rawData;

    /**
     * 
     * @param rawData
     *            TODO
     * @since 2020. 12. 17.
     */
    public ByteArrayValue(byte[] rawData) {
        Objects.requireNonNull(rawData);

        this.rawData = rawData;
    }

    public abstract T expr();

    /**
     *
     * @return the rawData
     *
     * @since 2020. 12. 17.
     */
    // 아래 내용에 적용됨.
    // - Arrays.copyOf(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public byte[] getRawData() {
        return Arrays.copyOf(this.rawData, this.rawData.length);
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
     * @return
     *
     * @since 2020. 12. 17.
     *
     * @see java.lang.Object#toString()
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(expr());
        builder.append(" (");
        builder.append(ByteUtils.hexBinString("0x", this.rawData));
        builder.append(")");
        return builder.toString();
    }

    /**
     * byte 배열을 문자열로 반환한다. 단, <code>terminated</code> 캐릭터인 경우 빈문자열("")을 반환한다.
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 17.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param bytes
     * @return
     *
     * @since 2020. 12. 17.
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public static String readAsString(byte[] bytes) {
        String str = new String(bytes, Charset.forName("UTF-8"));
        if (str.trim().isEmpty()) {
            return "";
        }

        StringBuffer sb = new StringBuffer();

        for (byte b : bytes) {
            if (b != 0) {
                sb.append((char) b);
            } else {
                sb.append("@");
                sb.append(System.nanoTime());
                sb.append("-C_R_A_S_H_E_D-T_E_R_M_I_N_A_T_E_D-C_H_A_R_A_C_T_E_R-");
                break;
            }
        }

        return sb.toString();
    }
}
