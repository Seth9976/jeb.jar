package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.ISegmentGapVerifier;

class akx implements ISegmentGapVerifier {
   akx(akw var1, int var2) {
      this.A = var1;
      this.pC = var2;
   }

   public ISegmentGapVerifier.VerificationCode pC(Integer var1, Integer var2) {
      return var2 - var1 >= this.pC ? ISegmentGapVerifier.VerificationCode.INCLUDE_AND_EXIT : ISegmentGapVerifier.VerificationCode.SKIP_AND_CONTINUE;
   }
}
