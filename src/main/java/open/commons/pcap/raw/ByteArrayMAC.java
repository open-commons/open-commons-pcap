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
 * Date  : 2020. 12. 17. 오후 5:30:20
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.pcap.raw;

import java.util.Arrays;
import java.util.function.Function;

import open.commons.core.utils.ByteUtils;

/**
 * 
 * @since 2020. 12. 17.
 * @version _._._
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ByteArrayMAC extends DefaultByteArrayValue<String> {

    public static final Function<byte[], String> EXPR_MAC = bs -> {
        return ByteUtils.toMACExpr(Arrays.copyOfRange(bs, 0, Math.min(6, bs.length)));
    };

    /**
     * @param rawData
     * @param expr
     * @since 2020. 12. 17.
     */
    public ByteArrayMAC(byte[] rawData) {
        super(rawData, EXPR_MAC);
    }
}
