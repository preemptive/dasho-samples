/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package dasho.samples;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class TokenSource {
    static Reader getToken() {
        final InputStream stream
            = TokenSource.class.getClassLoader().getResourceAsStream("ShelfLifeToken.dat");
        if (stream == null) {
            throw new RuntimeException("Could not read token ShelfLifeToken.dat");
        }
        return new InputStreamReader(stream);
    }
}
