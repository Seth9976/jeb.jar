package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.util.collect.CFBytesTrie;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class hR implements CFBytesTrie.IKeyExtractor {
   public byte[] pC(vh var1) {
      return var1.A;
   }
}
