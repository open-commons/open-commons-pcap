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
 * Date  : 2020. 12. 17. 오후 3:26:00
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.pcap.raw;

import java.util.function.Function;

import open.commons.utils.ByteUtils;

/**
 * byte 배열을 정수로 표현하는 클래스.
 * 
 * @since 2020. 12. 17.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ByteArrayInteger extends DefaultByteArrayValue<Integer> {

    private static final Function<byte[], Integer> DEFAULT_EXPR_INTEGER = bs -> ByteUtils.toInt(bs);

    /**
     * @param rawData
     * @param expr
     * @since 2020. 12. 17.
     */
    public ByteArrayInteger(byte[] rawData) {
        this(rawData, DEFAULT_EXPR_INTEGER);
    }

    /**
     * @param rawData
     * @param expr
     * @since 2020. 12. 17.
     */
    public ByteArrayInteger(byte[] rawData, Function<byte[], Integer> expr) {
        super(rawData, expr);
    }
}
