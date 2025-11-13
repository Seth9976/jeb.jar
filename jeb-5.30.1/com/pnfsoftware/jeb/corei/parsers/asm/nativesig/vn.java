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
import com.pnfsoftware.jebglobal.aaf;
import com.pnfsoftware.jebglobal.aam;
import com.pnfsoftware.jebglobal.axp;

@SerDisabled
public class vn implements aam {
   private final NativeSignatureDBManager q;
   private final INativeCodeAnalyzer RF;
   private final int xK;
   private int Dw = 0;

   public vn(INativeCodeAnalyzer var1, NativeSignatureDBManager var2, int var3) {
      this.RF = var1;
      this.q = var2;
      this.xK = var3;
   }

   @Override
   public void onModelUpdate(MemoryModelEvent var1) {
      Assert.a(this.q.getUserSelectedPackage(this.RF) != null, "signature user package not selected");
      switch (var1.getType()) {
         case COMMENT_UPDATE:
         case LABEL_UPDATE:
            if (var1.getDetails() instanceof Long) {
               long var2 = (Long)var1.getDetails();
               if (!var1.getType().equals(MemoryModelEventType.LABEL_UPDATE) || ((aaf)this.RF.getModel()).oW(var2) == null) {
                  ((aaf)this.RF.getModel()).oW(var2);

                  for (long var6 : this.RF.getModel().getContainedRoutineAddresses(var2)) {
                     axp var8 = ((aaf)this.RF.getModel()).oW(var6);
                     if (var8 != null) {
                        this.q((INativeMethodItem)var8);
                     }
                  }
               }
            }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      Assert.a(this.q.getUserSelectedPackage(this.RF) != null, "signature user package not selected");
      if (var1.getItem() instanceof axp var3 && (var1.getType() == NativeItemEventType.MODIFIED || var1.getType() == NativeItemEventType.UPDATED)) {
         this.q((INativeMethodItem)var3);
      }
   }

   private void q(INativeMethodItem var1) {
      NativeSignaturePackageEntry var2 = this.q.getUserSelectedPackage(this.RF);
      INativeSignature var3 = this.q.getSignatureGenerator().generateSignature(this.RF, var1, null, null);
      var2.addSignatureToWrite(var3);
      this.Dw++;
      if (this.Dw == this.xK) {
         this.q.updateOnDiskPackages(true);
         this.Dw = 0;
      }
   }

   @Override
   public void q(axp var1) {
   }

   @Override
   public void RF(axp var1) {
   }
}
