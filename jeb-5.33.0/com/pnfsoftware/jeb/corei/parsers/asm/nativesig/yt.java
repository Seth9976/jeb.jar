package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEvent;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureDBManager;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignaturePackageEntry;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import com.pnfsoftware.jebglobal.Tw;
import com.pnfsoftware.jebglobal.auu;
import com.pnfsoftware.jebglobal.ko;

@SerDisabled
public class yt implements ko {
   private final NativeSignatureDBManager pC;
   private final INativeCodeAnalyzer A;
   private final int kS;
   private int wS = 0;

   public yt(INativeCodeAnalyzer var1, NativeSignatureDBManager var2, int var3) {
      this.A = var1;
      this.pC = var2;
      this.kS = var3;
   }

   @Override
   public void onModelUpdate(MemoryModelEvent var1) {
      Assert.a(this.pC.getUserSelectedPackage(this.A) != null, "signature user package not selected");
      switch (var1.getType()) {
         case COMMENT_UPDATE:
         case LABEL_UPDATE:
            if (var1.getDetails() instanceof Long) {
               long var2 = (Long)var1.getDetails();
               if (!var1.getType().equals(MemoryModelEventType.LABEL_UPDATE) || ((Tw)this.A.getModel()).E(var2) == null) {
                  ((Tw)this.A.getModel()).E(var2);

                  for (long var6 : this.A.getModel().getContainedRoutineAddresses(var2)) {
                     auu var8 = ((Tw)this.A.getModel()).E(var6);
                     if (var8 != null) {
                        this.pC(var8);
                     }
                  }
               }
            }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      Assert.a(this.pC.getUserSelectedPackage(this.A) != null, "signature user package not selected");
      if (var1.getItem() instanceof auu var3 && (var1.getType() == NativeItemEventType.MODIFIED || var1.getType() == NativeItemEventType.UPDATED)) {
         this.pC(var3);
      }
   }

   private void pC(INativeMethodItem var1) {
      NativeSignaturePackageEntry var2 = this.pC.getUserSelectedPackage(this.A);
      INativeSignature var3 = this.pC.getSignatureGenerator().generateSignature(this.A, var1, null, null);
      var2.addSignatureToWrite(var3);
      this.wS++;
      if (this.wS == this.kS) {
         this.pC.updateOnDiskPackages(true);
         this.wS = 0;
      }
   }
}
