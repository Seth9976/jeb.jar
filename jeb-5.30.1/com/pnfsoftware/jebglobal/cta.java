package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.CFBytesTrie;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class cta implements CFBytesTrie.IKeyExtractor {
   public byte[] q(ctc var1) {
      return var1.nf;
   }
}
