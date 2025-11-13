package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFGVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CfgVerificationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import java.util.List;

public class aji extends CFGVerifier {
   private IERoutineContext pC;

   public aji(IERoutineContext var1) {
      super(var1.getCfg());
      this.pC = var1;
   }

   public aji(CFG var1) {
      super(var1);
   }

   public IERoutineContext pC() {
      return this.pC;
   }

   protected boolean pC(IEStatement var1) {
      return var1.isConditionalJump() || var1.isConditionalJumpFar();
   }

   @Override
   protected void customVerification() throws CfgVerificationException {
      if (!acm.pC(this.cfg)) {
         throw new CfgVerificationException("Found re-used IR objects");
      } else {
         List var1 = this.cfg.getBlocksView();

         for (BasicBlock var3 : var1) {
            for (IEStatement var5 : var3) {
               try {
                  var5.visitDepthPost(new ajj(this));
               } catch (IllegalIntermediateExpressionException var8) {
                  CfgVerificationException var7 = new CfgVerificationException("EStatement %s in block @%08x is invalid", var5, var3.getFirstAddress());
                  var7.addSuppressed(var8);
                  throw var7;
               }
            }
         }

         for (BasicBlock var11 : var1) {
            for (int var12 = 0; var12 < var11.size() - 1; var12++) {
               IEStatement var14 = (IEStatement)var11.get(var12);
               if (var14 instanceof IECall && ((IECall)var14).getReturnLocation() != null) {
                  throw new CfgVerificationException("Non block terminator ECall still carries a return-location in block %s", var11);
               }
            }
         }

         long var10 = ((BasicBlock)var1.get(0)).getEndAddress();

         for (int var13 = 1; var13 < var1.size(); var13++) {
            BasicBlock var15 = (BasicBlock)var1.get(var13);
            long var6 = var15.getFirstAddress() - var10;
            if (var6 != 0L) {
               throw new CfgVerificationException("Gap found in CFG, before block %s", var15);
            }

            var10 = var15.getEndAddress();
         }
      }
   }
}
