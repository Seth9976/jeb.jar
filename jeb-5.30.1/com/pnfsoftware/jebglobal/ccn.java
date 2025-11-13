package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexDecompilerEvent;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.util.collect.Sets;
import java.util.HashSet;
import java.util.Set;

public class ccn extends AbstractDOptimizer {
   private static final Set q = Sets.newHashSet("[I", "[J", "[F", "[D", "[Ljava/lang/Object;");
   private volatile boolean RF;
   private volatile boolean xK;

   @Override
   public int perform() {
      if (!this.RF) {
         this.RF = true;
         this.xK = false;
         if (!this.ctx.getMethod().getName().equals("<init>")) {
            return 0;
         }

         IDexType var1 = this.ctx.getMethod().getClassType();
         if (var1 == null) {
            return 0;
         }

         IDexClass var2 = var1.getImplementingClass();
         if (var2 == null) {
            return 0;
         }

         HashSet var3 = new HashSet(q);

         for (IDexField var5 : var2.getFields()) {
            if (var5.getData().isPrivate() && var5.getData().isFinal()) {
               String var6 = var5.getFieldTypeSignature(false);
               var3.remove(var6);
            }
         }

         if (!var3.isEmpty()) {
            return 0;
         }

         this.xK = true;
      }

      if (!this.xK) {
         return 0;
      } else {
         HashSet var8 = new HashSet(q);
         int var9 = -1;

         for (IDInstruction var11 : this.cfg.instructions()) {
            if (var11.getOpcode() == DOpcodeType.IR_ASSIGN && var11.getOperand1() instanceof IDInstanceField && var11.getOperand2() instanceof IDNewArrayInfo) {
               IDNewArrayInfo var12 = (IDNewArrayInfo)var11.getOperand2();
               if (var12.getSize() instanceof IDImm) {
                  int var13 = (int)((IDImm)var12.getSize()).getRawValue();
                  if (var13 <= 0) {
                     return 0;
                  }

                  String var7 = var12.getType().getSignature();
                  if (var8.remove(var7)) {
                     if (var9 < 0) {
                        var9 = var13;
                     } else if (var13 != var9) {
                        return 0;
                     }
                  }
               }
            }
         }

         if (!var8.isEmpty()) {
            return 0;
         } else {
            this.decomp
               .recordDecompilationEvent(new DexDecompilerEvent.Message("Found Virtualized routine handler (P-Code VM)", this.ctx.getMethodSignature()));
            this.xK = false;
            return 0;
         }
      }
   }
}
