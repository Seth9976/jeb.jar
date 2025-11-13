package com.pnfsoftware.jeb.util.collect;

public interface ISegmentGapVerifier {
   ISegmentGapVerifier.VerificationCode verify(Object var1, Object var2);

   public static enum VerificationCode {
      INCLUDE_AND_CONTINUE,
      INCLUDE_AND_EXIT,
      SKIP_AND_CONTINUE,
      SKIP_AND_EXIT;
   }
}
