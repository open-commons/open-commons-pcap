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
 * Date  : 2020. 12. 17. 오후 2:58:44
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.pcap.raw;

import java.util.function.Function;

/**
 * byte 배열을 문자열로 표현하는 클래스.
 * 
 * @since 2020. 12. 17.
 * @version 1.8.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class ByteArrayString extends DefaultByteArrayValue<String> {

    private static final Function<byte[], String> REJECT_TERMINATED = bs -> {
        return ByteArrayValue.readAsString(bs);
    };

    /**
     * 기본 생성자
     * 
     * @param rawData
     * @since 2020. 12. 17.
     */
    public ByteArrayString(byte[] rawData) {
        this(rawData, REJECT_TERMINATED);
    }

    /**
     * @param rawData
     * @param expr
     *            문자로 변환하는 함수
     * @since 2020. 12. 17.
     */
    public ByteArrayString(byte[] rawData, Function<byte[], String> expr) {
        super(rawData, expr);
    }
}