package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignaturePackage;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignaturePackageMetadata;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class qt implements INativeSignaturePackage {
   @SerId(1)
   private final NativeSignaturePackageMetadata pC;
   @SerId(2)
   private List A;

   public qt(NativeSignaturePackageMetadata var1) {
      this.pC = var1;
      this.A = new ArrayList();
   }

   public void pC(INativeSignature var1) {
      this.A.add(var1);
   }

   @Override
   public List getSignatures() {
      return this.A;
   }

   @Override
   public NativeSignaturePackageMetadata getMetadata() {
      return this.pC;
   }

   @Override
   public int count() {
      return this.A.size();
   }
}
