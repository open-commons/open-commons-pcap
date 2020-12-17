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
 * Date  : 2020. 12. 17. 오후 2:45:51
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.pcap.raw;

import java.nio.charset.Charset;
import java.util.Arrays;

import open.commons.utils.ByteUtils;

/**
 * 
 * @since 2020. 12. 17.
 * @version _._._
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
        this.rawData = rawData;
    }

    public abstract T expr();

    /**
     *
     * @return the rawData
     *
     * @since 2020. 12. 17.
     */
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
     * 2020. 12. 17.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 12. 17.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see java.lang.Object#toString()
     */
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
     * byte 배열을 문자열로 반환한다. 단, <code>terminated</code> 캐릭터인 경우 빈문자열("")을 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 12. 17.        박준홍         최초 작성
     * </pre>
     *
     * @param bytes
     * @return
     *
     * @since 2020. 12. 17.
     * @version _._._
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
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
                sb.append("_C_R_A_S_H_E_D_T_E_R_M_I_N_A_T_E_D_C_H_A_R_A_C_T_E_R_");
                break;
            }
        }

        return sb.toString();
    }
}
