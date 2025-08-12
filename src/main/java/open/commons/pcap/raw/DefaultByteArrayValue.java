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
 * Date  : 2020. 12. 17. 오후 3:20:19
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.pcap.raw;

import java.util.function.Function;

/**
 * byte 배열을 표현하는 기본 클래스.
 * 
 * @since 2020. 12. 17.
 * @version 1.8.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class DefaultByteArrayValue<T> extends ByteArrayValue<T> {

    protected final Function<byte[], T> exprFunc;

    /**
     * @param rawData
     * @param expr
     *            TODO
     * @since 2020. 12. 17.
     */
    public DefaultByteArrayValue(byte[] rawData, Function<byte[], T> expr) {
        super(rawData);
        this.exprFunc = expr;
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
     * @see open.commons.pcap.raw.ByteArrayValue#expr()
     */
    @Override
    public T expr() {
        return this.exprFunc.apply(this.rawData);
    }
}
