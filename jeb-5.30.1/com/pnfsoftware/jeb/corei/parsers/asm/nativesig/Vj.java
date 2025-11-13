package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignaturePackage;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignaturePackageMetadata;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Vj implements INativeSignaturePackage {
   @SerId(1)
   private final NativeSignaturePackageMetadata q;
   @SerId(2)
   private List RF;

   public Vj(NativeSignaturePackageMetadata var1) {
      this.q = var1;
      this.RF = new ArrayList();
   }

   public void q(INativeSignature var1) {
      this.RF.add(var1);
   }

   @Override
   public List getSignatures() {
      return this.RF;
   }

   @Override
   public NativeSignaturePackageMetadata getMetadata() {
      return this.q;
   }

   @Override
   public int count() {
      return this.RF.size();
   }
}
