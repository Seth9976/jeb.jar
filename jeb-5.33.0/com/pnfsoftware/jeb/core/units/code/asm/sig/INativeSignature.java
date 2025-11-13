package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface INativeSignature {
   String getTargetName();

   List getAlternateNames();

   List getPossibleNames();

   List getAttributes();

   NativeSignatureFlags getFlags();

   INativeSignature.ConfidenceLevel getConfidenceLevel();

   boolean match(INativeSignature var1);

   boolean matchExactly(INativeSignature var1);

   @Ser
   public static enum ConfidenceLevel {
      UNKNOWN,
      LOW,
      MEDIUM,
      HIGH;
   }
}
