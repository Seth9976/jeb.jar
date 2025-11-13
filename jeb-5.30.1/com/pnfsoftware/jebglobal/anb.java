package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.ISegmentGapVerifier;

class anb implements ISegmentGapVerifier {
   anb(ana var1, int var2) {
      this.RF = var1;
      this.q = var2;
   }

   public ISegmentGapVerifier.VerificationCode q(Integer var1, Integer var2) {
      return var2 - var1 >= this.q ? ISegmentGapVerifier.VerificationCode.INCLUDE_AND_EXIT : ISegmentGapVerifier.VerificationCode.SKIP_AND_CONTINUE;
   }
}
